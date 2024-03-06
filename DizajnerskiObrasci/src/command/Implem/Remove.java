package command.Implem;

import java.util.ArrayList;
import java.util.List;

import command.Command;
import drawing.Shape;
import mvc.DrawingModel;

public class Remove implements Command{

	private List<Shape> shapes;
	private DrawingModel model;
	private List<Integer> indexes; 
	
	
	public Remove(List<Shape> shapes, DrawingModel model)
	{
		this.shapes=new ArrayList<>(shapes);
		this.model=model;
		this.indexes = new ArrayList<Integer>();
	}
	

	@Override
	public void execute() {
		for(Shape shapeToDelete : shapes)
			for (Shape modelShape : model.getShapes()) {
			{
				if (shapeToDelete.equals(modelShape)) {
					int index = model.getIndexOfShape(shapeToDelete);
					indexes.add(index);
					model.remove(shapeToDelete);
					break;
				}
			}
		}
		
	}

	@Override
	public void unexecute() {
		for(int i = shapes.size() - 1; i >= 0; i--)
		{
			Shape s = shapes.get(i);
			int index = indexes.get(i);
			model.getShapes().add(index, s);
		}
	}
		
	

	@Override
	public String commandToString() {
		
		return ("Remove shapes" + shapes.toString() + indexes);
		
	}

}
