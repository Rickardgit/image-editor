package se.hh.imageEditor.gui;

import java.awt.Component;
import java.awt.image.BufferedImage;

public interface ImageArea {

	Component build();

	void setContent(BufferedImage image);

}
