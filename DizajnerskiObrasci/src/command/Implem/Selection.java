package command.Implem;

import java.util.ArrayList;
import java.util.List;

import command.Command;
import drawing.Circle;
import drawing.Shape;
import mvc.DrawingModel;

public class Selection implements Command {
	private Shape shape;
	
	public Selection(Shape shape)
	{
		this.shape = shape;
	}
	
	@Override
	public void execute() {
		shape.setSelected(true);
	}

	@Override
	public void unexecute() {
		shape.setSelected(false);
	}



	@Override
	public String commandToString() {
		// TODO Auto-generated method stub
		return	"Selection_" + " " + shape.getClass().getSimpleName() + shape.toString();
	}

	
	
}
