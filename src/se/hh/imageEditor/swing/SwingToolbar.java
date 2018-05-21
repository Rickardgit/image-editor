package se.hh.imageEditor.swing;

import static se.hh.imageEditor.swing.resources.IconProvider.EXIT;
import static se.hh.imageEditor.swing.resources.IconProvider.NEW;
import static se.hh.imageEditor.swing.resources.IconProvider.OPEN;
import static se.hh.imageEditor.swing.resources.IconProvider.SAVE;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import se.hh.filterApi.Filter;
import se.hh.filterApi.image.Image;
import se.hh.imageEditor.filters.ImagePresenter;
import se.hh.imageEditor.gui.Toolbar;

public final class SwingToolbar implements Toolbar {

	private static final String FILE_MENU = "File";
	private static final String EDIT_MENU = "Edit";
	private static final String FILTERS_MENU = "Filters";
	private static final String HELP_MENU = "Help";

	private static final String NEW_OPTION = "New";
	private static final String OPEN_OPTION = "Open";
	private static final String SAVE_OPTION = "Save";
	private static final String EXIT_OPTION = "Exit";

	private static final String REDO_OPTION = "Redo";
	private static final String UNDO_OPTION = "Undo";

	private JMenuItem newMenuItem;
	private JMenuItem openMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem exitMenuItem;

	private JMenu filterMenu;

	private JMenuItem undoMenuItem;
	private JMenuItem redoMenuItem;

	@Override
	public JMenuBar build() {
		JMenuBar menubar = new JMenuBar();

		JMenu fileMenu = new JMenu(FILE_MENU);
		JMenu editMenu = new JMenu(EDIT_MENU);
		filterMenu = new JMenu(FILTERS_MENU);
		JMenu helpMenu = new JMenu(HELP_MENU);

		buildFileMenuItems(fileMenu);
		buildEditMenuItems(editMenu);
		buildFilterMenuItems();
		buildHelpMenuItems(helpMenu);

		menubar.add(fileMenu);
		menubar.add(editMenu);
		menubar.add(filterMenu);
		menubar.add(helpMenu);

		return menubar;
	}

	@Override
	public void addNewListener(ActionListener listener) {
		newMenuItem.addActionListener(listener);
	}

	@Override
	public void addOpenListener(ActionListener listener) {
		openMenuItem.addActionListener(listener);
	}

	@Override
	public void addSaveListener(ActionListener listener) {
		saveMenuItem.addActionListener(listener);
	}

	@Override
	public void addExitListener(ActionListener listener) {
		exitMenuItem.addActionListener(listener);
	}

	@Override
	public void addUndoListener(ActionListener listener) {
		undoMenuItem.addActionListener(listener);
	}

	@Override
	public void addRedoListener(ActionListener listener) {
		redoMenuItem.addActionListener(listener);
	}

	@Override
	public void addFilters(List<Filter> filters, ImagePresenter presenter) {
		for (Filter filter : filters) {
			JMenuItem filterMenuItem = new JMenuItem(filter.getName());
			filterMenuItem.addActionListener(e -> {
				Image filteredImage = filter.apply(presenter.getImage());
				presenter.filterPerformed(filteredImage);
			});
			filterMenu.add(filterMenuItem);
		}
	}

	@Override
	public void addPattern() {
		// TODO Auto-generated method stub
	}

	private void buildFilterMenuItems() {
		JMenu patternsMenu = new JMenu("Image patterns");

		JMenuItem stripesPatternMenuItem = new JMenuItem("Stripes");
		JMenuItem chessPatternMenuItem = new JMenuItem("Chess");
		JMenuItem circlePatternMenuItem = new JMenuItem("Black Circle");

		filterMenu.add(patternsMenu);
		patternsMenu.add(stripesPatternMenuItem);
		patternsMenu.add(chessPatternMenuItem);
		patternsMenu.add(circlePatternMenuItem);
	}

	private void buildHelpMenuItems(JMenu helpMenu) {
		JMenuItem helpMenuItem = new JMenuItem("About");
		JMenuItem InstructionsMenuItem = new JMenuItem("Instructions");

		helpMenu.add(helpMenuItem);
		helpMenu.add(InstructionsMenuItem);
	}

	private void buildFileMenuItems(JMenu fileMenu) {
		newMenuItem = new JMenuItem(NEW_OPTION, NEW.get());
		openMenuItem = new JMenuItem(OPEN_OPTION, OPEN.get());
		saveMenuItem = new JMenuItem(SAVE_OPTION, SAVE.get());

		exitMenuItem = new JMenuItem(EXIT_OPTION, EXIT.get());

		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);
	}

	private void buildEditMenuItems(JMenu editMenu) {
		undoMenuItem = new JMenuItem(UNDO_OPTION);
		redoMenuItem = new JMenuItem(REDO_OPTION);

		editMenu.add(undoMenuItem);
		editMenu.add(redoMenuItem);
	}

}
