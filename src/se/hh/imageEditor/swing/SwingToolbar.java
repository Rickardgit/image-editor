package se.hh.imageEditor.swing;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import se.hh.filterApi.Filter;
import se.hh.imageEditor.gui.Toolbar;
import se.hh.imageEditor.swing.resources.IconProvider;

public final class SwingToolbar implements Toolbar {

	private JMenuItem newMenuItem;
	private JMenuItem openMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem exitMenuItem;

	private JMenu filterMenu;

	@Override
	public JMenuBar build() {
		JMenuBar menubar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		filterMenu = new JMenu("Filters");
		JMenu helpMenu = new JMenu("Help");

		newMenuItem = new JMenuItem("New", IconProvider.NEW.get());
		openMenuItem = new JMenuItem("Open", IconProvider.OPEN.get());
		saveMenuItem = new JMenuItem("Save", IconProvider.SAVE.get());

		exitMenuItem = new JMenuItem("Exit", IconProvider.EXIT.get());

		JMenu patternsMenu = new JMenu("Image patterns");

		JMenuItem stripesPatternMenuItem = new JMenuItem("Stripes");
		JMenuItem chessPatternMenuItem = new JMenuItem("Chess");
		JMenuItem circlePatternMenuItem = new JMenuItem("Black Circle");

		JMenuItem helpMenuItem = new JMenuItem("About");
		JMenuItem InstructionsMenuItem = new JMenuItem("Instructions");

		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);

		filterMenu.addSeparator();
		filterMenu.add(patternsMenu);
		patternsMenu.add(stripesPatternMenuItem);
		patternsMenu.add(chessPatternMenuItem);
		patternsMenu.add(circlePatternMenuItem);

		helpMenu.add(helpMenuItem);
		helpMenu.add(InstructionsMenuItem);

		menubar.add(fileMenu);
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
	public void addFilters(List<Filter> filters) {
		for (Filter filter : filters) {
			JMenuItem filterMenuItem = new JMenuItem(filter.getName());
			filterMenuItem.addActionListener(e -> {
				// filter.apply(image)
			});
			filterMenu.add(filterMenuItem);
		}
	}

	@Override
	public void addPattern() {
		// TODO Auto-generated method stub
	}

}
