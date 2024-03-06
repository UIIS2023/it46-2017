package command.Implem;

import command.Command;
import drawing.Shape;
import mvc.DrawingModel;

public class BringToBack implements Command {

	private DrawingModel drawModel;
	private Shape selectedShape;
	private int index;

	public BringToBack(DrawingModel drawModel,Shape selectedShape) {
		this.selectedShape=selectedShape;
		this.drawModel=drawModel;
	
	}
	@Override
	public void execute() {
		  index=drawModel.getIndexOfShape(selectedShape);
		  drawModel.getShapes().remove(selectedShape);
		  drawModel.getShapes().add(0, selectedShape);
		
	}

	@Override
	public void unexecute() {
		drawModel.getShapes().remove(0);
		drawModel.getShapes().add(index, selectedShape);	
		
	}

	@Override
	public String commandToString() {
		return ("Bring To Back_ " + selectedShape.getClass().getSimpleName() + selectedShape.toString());		

	}

}
