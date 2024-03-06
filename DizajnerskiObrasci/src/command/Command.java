package command;

public interface Command {
	public void execute();
	public void unexecute();
	String commandToString();
}
