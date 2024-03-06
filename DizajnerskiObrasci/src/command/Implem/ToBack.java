package command.Implem;

import java.util.Collections;

import command.Command;
import drawing.Shape;
import mvc.DrawingModel;

public class ToBack implements Command {

	private DrawingModel drawModel;
	private Shape shape;
	private int index;
	
	
public ToBack(DrawingModel drawModel,Shape s) {
    	
    	this.drawModel=drawModel;
    	this.shape=s;
		
	}
	@Override
	public void execute() {
		index=drawModel.getIndexOfShape(shape);
		if(index!=0)
		{
		Collections.swap(drawModel.getShapes(), index-1, index);

		}
		
	}

	@Override
	public void unexecute() {
		if(index!=0)
		{
		Collections.swap(drawModel.getShapes(), index, index-1);

		}
		
	}

	@Override
	public String commandToString() {
		return ("To Back_"+" " + shape.getClass().getSimpleName()  + shape.toString());		

	}

}
