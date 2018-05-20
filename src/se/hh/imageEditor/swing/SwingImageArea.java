package se.hh.imageEditor.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import se.hh.imageEditor.gui.ImageArea;

public final class SwingImageArea implements ImageArea {

	private ImageCanvas canvas;

	public SwingImageArea() {
		this.canvas = new ImageCanvas();
	}

	@Override
	public Component build() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(canvas, BorderLayout.CENTER);
		return new JScrollPane(canvas);
	}

	@Override
	public void setContent(BufferedImage image) {
		this.canvas.setImage(image);
	}

	private static final class ImageCanvas extends JComponent {

		private static final int DEFAULT_WIDTH = 360;
		private static final int DEFAULT_HEIGHT = 250;

		private static final long serialVersionUID = 1L;

		private int width;
		private int height;

		private BufferedImage content;

		public ImageCanvas() {
			width = DEFAULT_WIDTH;
			height = DEFAULT_HEIGHT;
		}

		public void setImage(BufferedImage image) {
			if (image != null) {
				width = image.getWidth();
				height = image.getHeight();
				content = image;
				repaint();
			}
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(width, height);
		}

		@Override
		public void paintComponent(Graphics g) {
			Dimension size = getSize();
			g.clearRect(0, 0, size.width, size.height);
			if (content != null) {
				g.drawImage(content, 0, 0, null);
			}
		}
	}
}
