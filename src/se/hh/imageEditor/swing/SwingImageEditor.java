package se.hh.imageEditor.swing;

import static javax.swing.JFileChooser.APPROVE_OPTION;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import se.hh.filterApi.Filter;
import se.hh.imageEditor.filters.FilterProvider;
import se.hh.imageEditor.gui.ImageArea;
import se.hh.imageEditor.gui.ImageEditor;
import se.hh.imageEditor.gui.Toolbar;
import se.hh.imageEditor.io.ImageHandler;

public class SwingImageEditor implements ImageEditor {

	private JFrame frame;
	private ImageArea imageArea;
	private Toolbar toolbar;

	private BufferedImage image;

	private JFileChooser fileChooser;

	public SwingImageEditor(Toolbar toolbar, ImageArea imageArea) {
		frame = new JFrame("Image Viewer and manipulation");
		JPanel contentPane = (JPanel) frame.getContentPane();
		contentPane.setBorder(new EmptyBorder(40, 40, 40, 40));
		contentPane.setLayout(new BorderLayout(40, 40));

		fileChooser = new JFileChooser();

		setToolbar(toolbar);
		setImageArea(imageArea);

		frame.pack();
		frame.setVisible(true);
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
		configure(toolbar);
	}

	@Override
	public void setFilterProvider(FilterProvider provider) {
		// TODO Auto-generated method stub
	}

	private void configure(Toolbar toolbar) {
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
	}

	private void applyFilter(Filter filter) {
		// XXX image = filter.apply(image);
		imageArea.setContent(image);

		frame.repaint();
	}

}
