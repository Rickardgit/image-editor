package se.hh.imageEditor.main;

import se.hh.imageEditor.gui.ImageArea;
import se.hh.imageEditor.gui.Toolbar;
import se.hh.imageEditor.swing.SwingImageArea;
import se.hh.imageEditor.swing.SwingImageEditor;
import se.hh.imageEditor.swing.SwingToolbar;

public final class ApplicationRunner {

	public static void main(String[] args) {
		Toolbar toolbar = new SwingToolbar();
		ImageArea imageArea = new SwingImageArea();
		new SwingImageEditor(toolbar, imageArea);
	}
}
