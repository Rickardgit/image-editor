package se.hh.imageEditor.swing;

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

			int result = fileChooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {

				try {
					image = ImageHandler.readImage(fileChooser.getSelectedFile().getAbsolutePath().toString());
					imageArea.setContent(image);
					frame.pack();

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			} else if (result == JFileChooser.CANCEL_OPTION) {
				System.out.println("No File Select");
			}
		});

		toolbar.addSaveListener(e -> {
			int result = fileChooser.showSaveDialog(frame);
			if (result == JFileChooser.CANCEL_OPTION) {
				System.out.println("cancelled");
			} else {
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
