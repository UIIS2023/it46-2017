package command.Implem;

import command.Command;
import drawing.Shape;
import mvc.DrawingModel;

public class BringToFront implements Command {

	private DrawingModel drawModel;
	private Shape selectedShape;
	private int indexBefore;
	
public BringToFront(DrawingModel drawModel,Shape selectedShape) {
		
		this.drawModel=drawModel;
	    this.selectedShape=selectedShape;
	}
	
	@Override
	public void execute() {
		   indexBefore=drawModel.getIndexOfShape(selectedShape);
	       drawModel.getShapes().remove(selectedShape);
	       drawModel.getShapes().add(selectedShape);
	}

	@Override
	public void unexecute() {
		  drawModel.getShapes().remove(selectedShape);
		  drawModel.getShapes().add(indexBefore, selectedShape);
		
	}

	@Override
	public String commandToString() {
		return ("Bring To Front_ "+ selectedShape.getClass().getSimpleName() + selectedShape.toString());		

	}

}
