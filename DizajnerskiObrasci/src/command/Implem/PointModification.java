package command.Implem;

import command.Command;
import drawing.Point; 

public class PointModification implements Command{

	private Point oldValue;
	private Point newValue;
	private Point originalValue = new Point();


	
	public PointModification(Point oldValue, Point newValue) {
		this.oldValue = oldValue;
		this.newValue = newValue;
       
		
	}

	@Override
	public void execute() {
		
		originalValue=(Point) oldValue.clone();
		oldValue.setX(newValue.getX());
		oldValue.setY(newValue.getY());
		oldValue.setOuterColor(newValue.getOuterColor());
		

	}

	@Override
	public void unexecute() {
		oldValue.setX(originalValue.getX());
		oldValue.setY(originalValue.getY());
		oldValue.setOuterColor(originalValue.getOuterColor());
		
	}

	@Override
	public String commandToString() {
		return ("Modify_" + " " + originalValue.getClass().getSimpleName() + originalValue + "||" +newValue.getClass().getSimpleName() + newValue);		
	}

}
