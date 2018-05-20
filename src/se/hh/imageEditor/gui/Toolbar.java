package se.hh.imageEditor.gui;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuBar;

import se.hh.filterApi.Filter;
import se.hh.imageEditor.filters.FilterListener;

public interface Toolbar extends GUIComponent {

	JMenuBar build();

	void addNewListener(ActionListener action);

	void addOpenListener(ActionListener action);

	void addSaveListener(ActionListener action);

	void addExitListener(ActionListener action);

	void addFilters(List<Filter> filters, FilterListener filterListener);

	void addPattern();

}
