package se.hh.imageEditor.gui;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuBar;

import se.hh.filterApi.Filter;
import se.hh.imageEditor.filters.ImagePresenter;

public interface Toolbar {

	JMenuBar build();

	void addNewListener(ActionListener listener);

	void addOpenListener(ActionListener listener);

	void addSaveListener(ActionListener listener);

	void addExitListener(ActionListener listener);

	void addUndoListener(ActionListener listener);

	void addRedoListener(ActionListener listener);

	void addFilters(List<Filter> filters, ImagePresenter presenter);

	void addPattern();

}
