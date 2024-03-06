package command.Implem;

import command.Command;

public class RedoCommandWrapper implements Command {
	
	private Command command;
	
	public RedoCommandWrapper(Command command) {
		this.command = command;
	}

	@Override
	public void execute() {
		command.execute();
	}

	@Override
	public void unexecute() {
		command.unexecute();
	}

	@Override
	public String commandToString() {
		return "REDO";
	}

}
