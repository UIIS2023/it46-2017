package command.Implem;

import command.Command;
import drawing.Shape;
import mvc.DrawingModel;

public class Add implements Command {

	private Shape shape;
	private DrawingModel model;
	
	
	public Add(Shape shape, DrawingModel model)
	{
		this.shape=shape;
		this.model=model;
	}

	@Override
	public void execute() {
       model.add(shape);
	}

	@Override
	public void unexecute() {
	   model.remove(shape);

	}

	@Override
	public String commandToString() {
		return ( "Add_" + " " + shape.getClass().getSimpleName() + " " + shape.toString());
	}

}
