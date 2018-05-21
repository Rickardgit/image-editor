package se.hh.imageEditor.commands;

import se.hh.filterApi.image.Image;
import se.hh.imageEditor.filters.ImagePresenter;

public final class FilterCommand implements Command {

	private ImagePresenter presenter;
	private Image original;
	private Image filteredImage;

	public FilterCommand(ImagePresenter presenter, Image filteredImage) {
		this.presenter = presenter;
		this.original = presenter.getImage();
		this.filteredImage = filteredImage;
	}

	@Override
	public void execute() {
		presenter.setImage(filteredImage);
	}

	@Override
	public void undo() {
		presenter.setImage(original);
	}

}
