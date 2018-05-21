package se.hh.imageEditor.swing.resources;

import java.net.URL;

import javax.swing.ImageIcon;

public enum IconProvider {

	NEW("new.jpg"), //
	OPEN("open.jpg"), //
	SAVE("save.jpg"), //
	EXIT("exit.jpg");

	private ImageIcon icon;

	IconProvider(String path) {
		URL resource = IconProvider.class.getClassLoader().getResource("icon/" + path);
		icon = new ImageIcon(resource.getPath());
	}

	public ImageIcon get() {
		return icon; 
	}
}
