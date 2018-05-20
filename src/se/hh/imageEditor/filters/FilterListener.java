package se.hh.imageEditor.filters;

import se.hh.filterApi.image.Image;

public interface FilterListener {

	Image getImage();

	void filterPerformed(Image image);

}
