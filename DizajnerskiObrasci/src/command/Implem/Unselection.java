package command.Implem;

import java.util.ArrayList;
import java.util.List;

import command.Command;
import drawing.Shape;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class Unselection implements Command{
	
	private List<Shape> shapes;
	private DrawingModel model;
	private DrawingFrame frame;
	
	public Unselection(List<Shape> shapes)
	{
		this.shapes=new ArrayList<>(shapes);
	}
	

	@Override
	public void execute() {
		for(Shape s: shapes) {
			s.setSelected(false);
			
		}
	}

	@Override
	public void unexecute() {
		for(Shape s: shapes) {
			s.setSelected(true);
		}
	}






	@Override
	public String commandToString() {
		return "Unselected [shapes=" + shapes + ", model=" + model + "]";
	}

}
