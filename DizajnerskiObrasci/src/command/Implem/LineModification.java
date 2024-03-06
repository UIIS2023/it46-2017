package command.Implem;

import command.Command;
import drawing.Line;

public class LineModification implements Command {

	private Line oldValue;
	private Line newValue;
	private Line originalValue = new Line();

	
	public LineModification(Line oldValue, Line newValue) {
		this.oldValue = oldValue;
		this.newValue = newValue;
		
	}

	@Override
	public void execute() {
		
		originalValue=(Line) oldValue.clone();
		
		oldValue.setStartPoint(newValue.getStartPoint());
		oldValue.setEndPoint(newValue.getEndPoint());
		oldValue.setOuterColor(newValue.getOuterColor());

		
	}

	@Override
	public void unexecute() {
		oldValue.setStartPoint(originalValue.getStartPoint());
		oldValue.setEndPoint(originalValue.getEndPoint());
		oldValue.setOuterColor(originalValue.getOuterColor());

	}

	@Override
	public String commandToString() {
		return ("Modify_" + " " + originalValue.getClass().getSimpleName() + originalValue+ "||" +newValue.getClass().getSimpleName() +newValue);		
		
	}


}
