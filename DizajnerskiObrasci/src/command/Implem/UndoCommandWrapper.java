package command.Implem;

import command.Command;

public class UndoCommandWrapper implements Command {
	
	private Command command;
	
	public UndoCommandWrapper(Command command) {
		this.command = command;
	}

	@Override
	public void execute() {
		command.unexecute();
	}

	@Override
	public void unexecute() {
		command.execute();
	}

	@Override
	public String commandToString() {
		return "UNDO";
	}
}
