package command.Implem;

import java.util.Collections;

import command.Command;
import drawing.Shape;
import mvc.DrawingModel;

public class ToFront implements Command {

	private DrawingModel drawModel;
	private Shape shape;
	private int index;
	
public ToFront(DrawingModel drawModel,Shape shape) {
		
		this.drawModel=drawModel;
		this.shape=shape;
		
	}
	
	@Override
	public void execute() {
        index=drawModel.getIndexOfShape(shape);
		
		if(index!=drawModel.getShapes().size()-1)
		{
		Collections.swap(drawModel.getShapes(), index+1, index);
		}
		
	}

	@Override
	public void unexecute() {
		if(index!=drawModel.getShapes().size()-1)
		{
		Collections.swap(drawModel.getShapes(), index, index+1);

		}

		
	}

	@Override
	public String commandToString() {
		return ("To Front_ " + shape.getClass().getSimpleName() + shape.toString());	
	}

}
