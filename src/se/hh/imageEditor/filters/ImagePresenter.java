package se.hh.imageEditor.filters;

import se.hh.filterApi.image.Image;

public interface ImagePresenter {

	Image getImage();

	void setImage(Image image);

	void filterPerformed(Image image);

}
