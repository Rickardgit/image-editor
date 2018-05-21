package se.hh.imageEditor.commands;

import se.hh.imageEditor.commons.Stack;

public final class CommandTracker {

	private static final int CAPACITY = 20;

	private final Stack<Command> undo;
	private final Stack<Command> redo;

	public CommandTracker() {
		this.undo = new Stack<>(CAPACITY);
		this.redo = new Stack<>(CAPACITY);
	}

	public void execute(Command command) {
		undo.push(command);
		redo.clear();
		command.execute();
	}

	public void undo() {
		if (!undo.isEmpty()) {
			Command command = undo.pop();
			command.undo();
			redo.push(command);
		}
	}

	public void redo() {
		Command command = redo.pop();
		command.execute();
		undo.push(command);
	}
}