package se.hh.imageEditor.gui;

import se.hh.imageEditor.filters.FilterProvider;

public interface ImageEditor {

	void setImageArea(ImageArea imageArea);

	void setToolbar(Toolbar toolbar);

	void setFilterProvider(FilterProvider provider);

}
