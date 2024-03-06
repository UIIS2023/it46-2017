package command;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import command.Implem.Add;
import command.Implem.BringToBack;
import command.Implem.BringToFront;
import command.Implem.CircleModification;
import command.Implem.DonutModification;
import command.Implem.HexagonModification;
import command.Implem.LineModification;
import command.Implem.PointModification;
import command.Implem.RectangleModification;
import command.Implem.Remove;
import command.Implem.ToBack;
import command.Implem.ToFront;
import drawing.Circle;
import drawing.Donut;
import drawing.HexagonAdapter;
import drawing.Line;
import drawing.Point;
import drawing.Rectangle;
import drawing.Shape;
import mvc.DrawingFrame;
import mvc.DrawingModel;



//Fali nesto
public class CommandManager {
//	private static CommandManager instance = null;
//    private Stack<Command> stackNormal;
//    private Stack<Command> stackReverse;
//    private DrawingFrame frame;
//    private DrawingModel model;
//    private int flag =-1;
//    private List<String> commandHistory;
//    private List<Shape> shapes = new ArrayList<Shape>();
//    
//    
//    /////////
//    Deque<Command> de_queNormal;
//    Deque<Command> de_queReverse;
//
//    /////////
//    public static CommandManager getInstance() {
//    if(instance == null) 
//    	instance = new CommandManager();
//        return instance;
//}
//
//private CommandManager() {
//    stackNormal = new Stack<>();
//    stackReverse = new Stack<>();
//    commandHistory = new ArrayList<>();
//   
//}
//
//public void execute(Command command) {
//	if(command ==null) {
//		System.out.println("Command je null");
//	}
//	System.out.println("CommandManager->execute()->Flag je " + " " + flag);
//	try {  
//	flag = model.getFlag();
//	}
//	catch(NullPointerException ex) {
//		  System.out.print("Caught NullPointerException"); 
//	}
////	 if(flag < model.getCommandStack().size()-1)
//	if(flag < stackNormal.size()-1)
//	   {
//		   System.out.println("Flag u execute()-u if je" + flag );
//		   model.setFlag(model.getCommandStack().size()-1);
//		  
//	   }
//    command.execute();
//    stackNormal.push(command); 
//    commandHistory.add(command.commandToString());
//    stackReverse.clear();
////    model.setFlag(this.flag+1);
//    String commandString="Execute:"+command.commandToString();
////	 frame.getDlm().addElement(commandString);
////	 model.getLogs().add(commandString);
//   
// 
//	
//	
//}
//
//public void undo() { 
//	if (!stackNormal.isEmpty()) {
//		Command command = stackNormal.pop();
//		stackReverse.push(command);
//		commandHistory.add(command.commandToString() + " - Undo");
//		command.unexecute();
//		
//	}
//	
//	
//}
//
//public void redo() {
//	if(!stackReverse.isEmpty()) {
//	Command command = stackReverse.pop();
//	stackNormal.push(command);
//	commandHistory.add(command.commandToString() + " - Redo");
//	command.execute();
//	
//	// Logging
//	}
//	
//	
//}
//
//public void clearNormal() {
//    stackNormal.clear();
//}
//
//public void clearReverse() {
//    stackReverse.clear();
//}
//
//public List<String> getActionHistory() {
//    return commandHistory;
//}
//
//public int sizeNormal() {
//	return stackNormal.size();
//}
//
//public int sizeReverse() {
//	return stackReverse.size();
//}
//
//public void setFrame(DrawingFrame frame) {
//	this.frame = frame;
//}
//
//public Stack<Command> getStackNormal() {
//	return stackNormal;
//}
//
//public void setStackNormal(Stack<Command> stackNormal) {
//	this.stackNormal = stackNormal;
//}
//
//public Stack<Command> getStackReverse() {
//	return stackReverse;
//}
//
//public void setStackReverse(Stack<Command> stackReverse) {
//	this.stackReverse = stackReverse;
//}
//
//public List<String> getCommandHistory() {
//	return commandHistory;
//}
//
//public void setCommandHistory(List<String> commandHistory) {
//	this.commandHistory = commandHistory;
//}
///////////////////
	
	
	public Command parse(String command, DrawingModel model) {
		   
		String type=parseCommand(command);
		
		if(type.contains("Add")) {
			
			return new Add(buildShape(command),model);
		}
		else if(type.contains("Remove")) {
			
			
			return new Remove(model.getSelectedShapes(),model);
             
             
			
		}
		
		else if(type.contains("Bring To Front")) {
			
			return new BringToFront(model,model.getSelectedShape());
			
		}
		else if(type.contains("Bring To Back")) {
			
			return new BringToBack(model,model.getSelectedShape());
			
		}
		else if(type.contains("To Back")) {
			
			return new ToBack(model,model.getSelectedShape());			
			
		}
		else if(type.contains("To Front")) {
			
			return new ToFront(model,model.getSelectedShape());			
		}
        else if(type.contains("Modify")) {
        	
    		String s1=command.split("->")[1]; 	
    		Shape oldShape=model.getSelectedShape(); 		
			return createModifyCommand(oldShape,s1);
		}

		return null;

	}
		
	private Command createModifyCommand(Shape oldShape, String s1) {
		Shape newShape;
		
		if(oldShape instanceof Point) {
    		
    		newShape=buildPoint(s1);
    		return new PointModification((Point)oldShape,(Point)newShape);
    	}
    	if(oldShape instanceof Line) {
    	
    		newShape=buildLine(s1);
    		return new LineModification((Line)oldShape,(Line)newShape);
    	}
    	if(oldShape instanceof Rectangle) {
    	
    		newShape=buildRectangle(s1);
    		return new RectangleModification((Rectangle)oldShape,(Rectangle)newShape);
    	}
        if(oldShape instanceof Donut) {
    		
    		newShape=buildDonut(s1);
    		return new DonutModification((Donut)oldShape,(Donut)newShape);
    	}
    	if(oldShape instanceof Circle) {
    		
    		newShape=buildCircle(s1);
    		return new CircleModification((Circle)oldShape,(Circle)newShape);
    	}
    	if(oldShape instanceof HexagonAdapter) {
    		
    		newShape=buildHexagon(s1);
    		return new HexagonModification((HexagonAdapter)oldShape,(HexagonAdapter)newShape);
    	}
		return null;
	}

	public String parseCommand(String command) {
		return command.split("\\(")[0];
		
	}


	
	public Shape buildShape(String command) {
		
		Shape shape = null;
		String type=parseCommand(command);
		if(type.contains("Point")) {
			
			shape=buildPoint(command);
			
			
						
		}
        if(type.contains("Line")) {
			
        	shape=buildLine(command);
			
							
		}	
        if(type.contains("Rectangle")) {
			
        	shape=buildRectangle(command);
					
		}
        if(type.contains("Circle")) {
			
        	shape=buildCircle(command);
		
						
		}
         if(type.contains("Donut")) {
			
        	 shape=buildDonut(command);
			
							
		}
         if(type.contains("Hexagon")) {
				
        	 shape=buildHexagon(command);
				
								
			}
        
         return shape;
	}







	public Point buildPoint(String command){
		HashMap<String,String> parsePoint=parseShape(command);
		int x=Integer.parseInt(parsePoint.get("x"));
		int y=Integer.parseInt(parsePoint.get("y"));
		Color col=new Color(Integer.parseInt(parsePoint.get("Color")));
		return new Point(x,y,col);
		
		
	}
	public Line buildLine(String command) {
		HashMap<String,String> parseLine=parseShape(command);
		int startPointX=Integer.parseInt(parseLine.get("StartPointX"));
		int startPointY=Integer.parseInt(parseLine.get("StartPointY"));
		int endPointX=Integer.parseInt(parseLine.get("EndPointX"));
		int endPointY=Integer.parseInt(parseLine.get("EndPointY"));
		Color col=new Color(Integer.parseInt(parseLine.get("Color")));
		return new Line(new Point(startPointX,startPointY),new Point(endPointX,endPointY),col);
		
	}
	
	public Rectangle buildRectangle(String command) {
		HashMap<String,String> parseRectangle=parseShape(command);
		int upperLeftPointX=Integer.parseInt(parseRectangle.get("UpperLeftPointX"));
		int upperLeftPointY=Integer.parseInt(parseRectangle.get("UpperLeftPointY"));
		int height=Integer.parseInt(parseRectangle.get("Height"));
		int width=Integer.parseInt(parseRectangle.get("Width"));
		Color outerCol=new Color(Integer.parseInt(parseRectangle.get("OuterColor")));
		Color innerCol=new Color(Integer.parseInt(parseRectangle.get("InnerColor")));
		try {
			return new Rectangle(new Point(upperLeftPointX,upperLeftPointY),height,width,innerCol,outerCol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Circle buildCircle(String command) {
		HashMap<String,String> parseCircle=parseShape(command);
		int centerX=Integer.parseInt(parseCircle.get("CenterX"));
		int centerY=Integer.parseInt(parseCircle.get("CenterY"));
		int radius=Integer.parseInt(parseCircle.get("Radius"));
		Color outerCol=new Color(Integer.parseInt(parseCircle.get("OuterColor")));
		Color innerCol=new Color(Integer.parseInt(parseCircle.get("InnerColor")));
		try {
			return new Circle(new Point(centerX,centerY),radius,innerCol,outerCol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Donut buildDonut(String command) {
		HashMap<String,String> parseDonut=parseShape(command);
		int centerX=Integer.parseInt(parseDonut.get("CenterX"));
		int centerY=Integer.parseInt(parseDonut.get("CenterY"));
		int radius=Integer.parseInt(parseDonut.get("Radius"));
		int innerRadius=Integer.parseInt(parseDonut.get("InnerRadius"));
		Color outerCol=new Color(Integer.parseInt(parseDonut.get("OuterColor")));
		Color innerCol=new Color(Integer.parseInt(parseDonut.get("InnerColor")));
		try {
			return new Donut(new Point(centerX,centerY),radius,innerRadius,innerCol,outerCol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public HexagonAdapter buildHexagon(String command) {
		HashMap<String,String> parseHex=parseShape(command);
		int X=Integer.parseInt(parseHex.get("x"));
		int Y=Integer.parseInt(parseHex.get("y"));
		int radius=Integer.parseInt(parseHex.get("radius"));
		Color outerCol=new Color(Integer.parseInt(parseHex.get("OuterColor")));	
		Color innerCol=new Color(Integer.parseInt(parseHex.get("InnerColor")));	
		try {
			return new HexagonAdapter(X,Y,radius,innerCol,outerCol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public HashMap<String,String> parseShape(String command){
		HashMap<String,String> helpMap=new HashMap<String,String>();
		String cast=command.split("\\(")[1];
		
		
		cast=cast.substring(0, cast.length()-1);
		
			
		for(String s: cast.split(",")) {
		
		    String [] ss=s.split("=");	
		
			helpMap.put(ss[0],ss[1]);
		
		}
		return helpMap;
	
		
	}
}
