package command.Implem;

import command.Command;
import drawing.Circle;

public class CircleModification implements Command{

	private Circle oldValue;
	private Circle newValue;
	private Circle originalValue = new Circle();
	
	
	public CircleModification(Circle oldValue, Circle newValue) {
		this.oldValue = oldValue;
		this.newValue = newValue;
		
	}

	@Override
	public void execute() { 
		originalValue=(Circle) oldValue.clone();
		System.out.println("OriginalValue je" + originalValue.toString() );
		oldValue.getCenter().setX(newValue.getCenter().getX());
		oldValue.getCenter().setY(newValue.getCenter().getY());
		try {
			oldValue.setRadius(newValue.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldValue.setOuterColor(newValue.getOuterColor());
		oldValue.setInnerColor(newValue.getInnerColor());
//		oldValue.setSelected(true);//ne radi

		
	}

	@Override
	public void unexecute() {
		oldValue.getCenter().setX(originalValue.getCenter().getX());
		oldValue.getCenter().setY(originalValue.getCenter().getY());
		try {
			oldValue.setRadius(originalValue.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		oldValue.setOuterColor(originalValue.getOuterColor());
		oldValue.setInnerColor(originalValue.getInnerColor());

	}

	public String commandToString() {
		return ("Modify_" + " " + originalValue.getClass().getSimpleName() + originalValue+"||"+newValue.getClass().getSimpleName()+newValue);		
	}


}
