package logsParsing;

import java.awt.Color; 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import command.Command;
import command.Implem.Add;
import command.Implem.BringToBack;
import command.Implem.BringToFront;
import command.Implem.CircleModification;
import command.Implem.DonutModification;
import command.Implem.HexagonModification;
import command.Implem.LineModification;
import command.Implem.PointModification;
import command.Implem.RectangleModification;
import command.Implem.RedoCommandWrapper;
import command.Implem.Remove;
import command.Implem.Selection;
import command.Implem.ToBack;
import command.Implem.ToFront;
import command.Implem.UndoCommandWrapper;
import command.Implem.Unselection;
import drawing.Circle;
import drawing.Donut;
import drawing.HexagonAdapter;
import drawing.Line;
import drawing.Point;
import drawing.Rectangle;
import drawing.Shape;
import mvc.DrawingFrame;
import mvc.DrawingModel;



public class CommandParser implements Serializable {

	private static final long serialVersionUID = -5446880322409643478L;

	private List<Command> undoList = new ArrayList<>();
	private List<Command> redo = new ArrayList<>();
	
	public CommandParser() {
	}

	public Command parse(String s, DrawingModel model,DrawingFrame frame) throws NumberFormatException, Exception {
		if (s.toLowerCase().contains("undo")) {
			Command command = undoList.get(undoList.size() - 1);
			undoList.remove(undoList.size() - 1);
			redo.add(command);
			
			Command undo = new UndoCommandWrapper(command);
			return undo;
		} else if (s.toLowerCase().contains("redo")) {
			Command command = redo.get(redo.size() - 1);
			redo.remove(redo.size() - 1);
			undoList.add(command);
			
			Command redo = new RedoCommandWrapper(command);
			return redo;
		}
		else if (s.toLowerCase().contains("add")) {
			Command command = buildAddCommandFromString(s, model);
			undoList.add(command);
			redo.clear();
			return command;
		} else if (s.toLowerCase().contains("remove")) {
			Command command = buildRemoveCommandFromString(s, model);
			undoList.add(command);
			redo.clear();
			return command;
		} else if (s.toLowerCase().contains("modify")) {
			Command command = buildModifyCommandFromString(s, model);
			undoList.add(command);
			redo.clear();
			return command;
		}else if(s.toLowerCase().contains("selection")) {
			Command command = selectCommandCreateShape(s,model,frame);
			undoList.add(command);
			redo.clear();
			return command;
		}else if(s.toLowerCase().contains("unselected")) {
			Command command = unselectCommandCreateShape(s,model,frame);
			undoList.add(command);
			redo.clear();
			return command;
		}else if(s.toLowerCase().contains("bring to front")) {
			Command command = buildBringToFrontCommandFromString(s,model);
			undoList.add(command);
			redo.clear();
			return command;
		} else if(s.toLowerCase().contains("bring to back")) {
			Command command = buildBringToBackCommandFromString(s,model);
			undoList.add(command);
			redo.clear();
			return command;
		} else if(s.toLowerCase().contains("to back")) {
			Command command = buildToBackCommandFromString(s,model);
			undoList.add(command);
			redo.clear();
			return command;
		} else if(s.toLowerCase().contains("to front")) {
			Command command = buildToFrontCommandFromString(s,model);
			undoList.add(command);
			redo.clear();
			return command;
		}
	
		return null;
	}



	private Command unselectCommandCreateShape(String s, DrawingModel model,DrawingFrame frame) {
		return new Unselection(model.getSelectedShapes());
	}

	private Command selectCommandCreateShape(String s, DrawingModel model, DrawingFrame frame) throws Exception {
		String shapeString = s.split("_")[1];
		Shape shape = buildShape(shapeString, model);
		Shape equalShapeInModel = null;

		for (Shape sh : model.getShapes()) {
			if (sh.equals(shape)) {
				equalShapeInModel = sh;
			}
		}
		
		return new Selection(equalShapeInModel);
	}

	public Command buildAddCommandFromString(String command, DrawingModel model) throws NumberFormatException, Exception {
		String shapeString = command.split("_")[1];
		return new Add(buildShape(shapeString,model),model);
	}
	
	public Command buildModifyCommandFromString(String command, DrawingModel model) {
		String propsString = command.split("->")[2];
		Shape oldShape = model.getSelectedShape(); 
	   
		try {
			return createModifyCommand(oldShape, propsString,model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public Command buildToBackCommandFromString(String command, DrawingModel model) {
		return new ToBack(model,model.getSelectedShape());		
	}
	
	public Command buildToFrontCommandFromString(String command, DrawingModel model) {
		return new ToFront(model,model.getSelectedShape());		
	}
	
	public Command buildBringToFrontCommandFromString(String command, DrawingModel model) {
		return new BringToFront(model,model.getSelectedShape());		
	}
	
	public Command buildBringToBackCommandFromString(String command, DrawingModel model) {
		return new BringToBack(model,model.getSelectedShape());		
	}
	
	public Command buildRemoveCommandFromString(String command, DrawingModel model) {
		return new Remove(model.getSelectedShapes(), model);		
	}
	
	private Command createModifyCommand(Shape oldShape, String s1, DrawingModel model) throws NumberFormatException, Exception {
		
		if(oldShape instanceof Point) {
    		return new PointModification((Point)oldShape,buildPoint(s1));
    	}
    	if(oldShape instanceof Line) {
    		return new LineModification((Line)oldShape,buildLine(s1));
    	}
    	if(oldShape instanceof Rectangle) {
    		return new RectangleModification((Rectangle)oldShape, buildRectangle(s1));
    	}
        if(oldShape instanceof Donut) {
    		return new DonutModification((Donut)oldShape,buildDonut(s1));
    	}
    	if(oldShape instanceof Circle) {
    		return new CircleModification((Circle)oldShape,buildCircle(s1));
    	}
    	if(oldShape instanceof HexagonAdapter) {
    		return new HexagonModification((HexagonAdapter)oldShape, buildHexagon(s1));
    	}

		return null;
	}

	public Shape buildShape(String command, DrawingModel model) throws NumberFormatException, Exception {
		String shapeString = command.split("->")[1];

		if(command.trim().startsWith("Point")) {
			return buildPoint(shapeString);
		}
		else if (command.trim().startsWith("Line")) {
        	return buildLine(shapeString);
		}	
		else if(command.trim().startsWith("Rectangle")) {
        	return buildRectangle(shapeString);
		}
		else if(command.trim().startsWith("Circle")) {
        	return buildCircle(command);
		}
		else if(command.trim().startsWith("Donut")) {
        	 return buildDonut(shapeString);	
		}
		else if(command.trim().startsWith("Hexagon")) {		
        	 return buildHexagon(shapeString);
		}
        
         return null;
	}

	public Point buildPoint(String command){
		String[] props = command.split(",");
		int x=Integer.parseInt(props[0].split("=")[1]);
		int y=Integer.parseInt(props[1].split("=")[1]);
		Color col=new Color(Integer.parseInt(props[2].split("=")[1]));
		return new Point(x,y,col);
		
		
	}
	public Line buildLine(String command) {
		String[] props = command.split(",");
		int startPointX=Integer.parseInt(props[0].split("=")[1]);
		int startPointY=Integer.parseInt(props[1].split("=")[1]);
		int endPointX=Integer.parseInt(props[2].split("=")[1]);
		int endPointY=Integer.parseInt(props[3].split("=")[1]);
		Color col=new Color(Integer.parseInt(props[4].split("=")[1]));
		return new Line(new Point(startPointX,startPointY),new Point(endPointX,endPointY),col);
		
	}
	
	public Rectangle buildRectangle(String command) {
		String[] props = command.split(",");
		int upperLeftPointX=Integer.parseInt(props[0].split("=")[1]);
		int upperLeftPointY=Integer.parseInt(props[1].split("=")[1]);
		int height=Integer.parseInt(props[2].split("=")[1]);
		int width=Integer.parseInt(props[3].split("=")[1]);
		Color outerCol=new Color(Integer.parseInt(props[4].split("=")[1]));
		Color innerCol=new Color(Integer.parseInt(props[5].split("=")[1]));
		try {
			return new Rectangle(new Point(upperLeftPointX,upperLeftPointY),height,width,innerCol,outerCol);
		} catch (Exception e) {}
		return null;
	}
	public Circle buildCircle(String command) {
		String[] props = command.split(",");
		
		int centerX=Integer.parseInt(props[0].split("=")[1]);
		int centerY=Integer.parseInt(props[1].split("=")[1]);
		int radius=Integer.parseInt(props[2].split("=")[1]);
		Color outerCol=new Color(Integer.parseInt(props[3].split("=")[1]));
		Color innerCol=new Color(Integer.parseInt(props[4].split("=")[1]));
			      
		try {
			Circle circle =  new Circle(new Point(centerX,centerY),radius,innerCol,outerCol);
			return circle;
		} catch (Exception e) {

		}
		
		return null;
	}
	
	public Donut buildDonut(String command) {
		String[] props = command.split(",");
		
		int centerX=Integer.parseInt(props[0].split("=")[1]);
		int centerY=Integer.parseInt(props[1].split("=")[1]);
		int radius=Integer.parseInt(props[2].split("=")[1]);
		Color outerCol=new Color(Integer.parseInt(props[3].split("=")[1]));
		Color innerCol=new Color(Integer.parseInt(props[4].split("=")[1]));
		int innerRadius=Integer.parseInt(props[5].split("=")[1]);
		
		try {
			return new Donut(new Point(centerX,centerY),radius,innerRadius,innerCol,outerCol);
		} catch (Exception e) {
		}
		
		return null;
	}
	
	public HexagonAdapter buildHexagon(String command) {
		String[] props = command.split(",");
		int X=Integer.parseInt(props[0].split("=")[1]);
		int Y=Integer.parseInt(props[1].split("=")[1]);
		int radius=Integer.parseInt(props[2].split("=")[1]);
		Color outerCol=new Color(Integer.parseInt(props[3].split("=")[1]));	
		Color innerCol=new Color(Integer.parseInt(props[4].split("=")[1]));	
		
		try {
			return new HexagonAdapter(X,Y,radius,innerCol,outerCol);
		} catch (Exception e) {
		}
		
		return null;
	}
	
}
