package se.hh.imageEditor.swing;

import static javax.swing.JFileChooser.APPROVE_OPTION;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import se.hh.filterApi.image.BufferedImageAdapter;
import se.hh.filterApi.image.Image;
import se.hh.imageEditor.commands.CommandTracker;
import se.hh.imageEditor.commands.FilterCommand;
import se.hh.imageEditor.filters.FilterProvider;
import se.hh.imageEditor.filters.ImagePresenter;
import se.hh.imageEditor.gui.ImageArea;
import se.hh.imageEditor.gui.ImageEditor;
import se.hh.imageEditor.gui.Toolbar;
import se.hh.imageEditor.io.ImageHandler;

public class SwingImageEditor implements ImageEditor, ImagePresenter {

	private JFrame frame;
	private ImageArea imageArea;
	private Toolbar toolbar;

	private BufferedImage image;

	private JFileChooser fileChooser;
	private FilterProvider provider;

	private CommandTracker tracker;

	public SwingImageEditor(Toolbar toolbar, ImageArea imageArea, FilterProvider provider) {
		frame = new JFrame("Image Viewer and manipulation");
		JPanel contentPane = (JPanel) frame.getContentPane();
		contentPane.setBorder(new EmptyBorder(40, 40, 40, 40));
		contentPane.setLayout(new BorderLayout(40, 40));

		fileChooser = new JFileChooser();

		setFilterProvider(provider);
		setToolbar(toolbar);
		setImageArea(imageArea);

		frame.pack();
		frame.setVisible(true);

		tracker = new CommandTracker();
	}

	@Override
	public void setImageArea(ImageArea imageArea) {
		this.imageArea = imageArea;
		frame.getContentPane().add(imageArea.build(), BorderLayout.CENTER);
	}

	@Override
	public void setToolbar(Toolbar toolbar) {
		this.toolbar = toolbar;
		frame.setJMenuBar(toolbar.build());
		configureToolbar();
	}

	@Override
	public void setFilterProvider(FilterProvider provider) {
		this.provider = provider;
	}

	private void configureToolbar() {
		toolbar.addExitListener(e -> System.exit(0));

		toolbar.addOpenListener(e -> {
			if (fileChooser.showOpenDialog(frame) == APPROVE_OPTION) {

				File selectedFile = fileChooser.getSelectedFile();
				image = ImageHandler.readImage(selectedFile.getAbsolutePath());
				imageArea.setContent(image);
				frame.pack();
			}
		});

		toolbar.addSaveListener(e -> {
			if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {

				File selectedFile = fileChooser.getSelectedFile();
				ImageHandler.saveImage(image, selectedFile);
			}
		});

		toolbar.addUndoListener(e -> tracker.undo());
		toolbar.addRedoListener(e -> tracker.redo());

		toolbar.addFilters(provider.getFilters(), this);
	}

	@Override
	public Image getImage() {
		return new BufferedImageAdapter(image);
	}

	@Override
	public void filterPerformed(Image image) {
		tracker.execute(new FilterCommand(this, image));
	}

	@Override
	public void setImage(Image image) {
		this.image = image.toBufferedImage();
		imageArea.setContent(this.image);
		frame.repaint();
	}

}
