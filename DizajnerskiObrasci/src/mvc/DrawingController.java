package mvc;

import java.awt.Color;                   
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import command.Command;
import command.CommandManager;
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
import command.Implem.Selection;
import command.Implem.ToBack;
import command.Implem.ToFront;
import command.Implem.Unselection;
import drawing.Circle;
import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import drawing.Donut;
import drawing.HexagonAdapter;
import drawing.Line;
import drawing.Point;
import drawing.Rectangle;
import drawing.Shape;
import fileManager.DialogsHelper;
import fileManager.ExportManager;

import fileManager.FileHelp;
import fileManager.FileOperationsHelper;
import fileManager.ImportManager;
import fileManager.LoadDrawingFromFile;
import fileManager.LoadLogFromFile;
import fileManager.Logger;
import fileManager.LoggerModel;
import fileManager.SaveLogToFile;
import fileManager.SerializeShapesToFile;
import logsParsing.CommandParser;
import observer.Transfer;

@SuppressWarnings("deprecation")
public class DrawingController extends Observable {
	private DrawingModel model;
	private DrawingFrame frame;
	private Point startPoint;
	private Color colInner=Color.WHITE;
	private Color colOuter=Color.BLACK;
	private List<String> logs = new ArrayList<String>();
	private List<Command> undoList = new ArrayList<Command>();
	private List<Command> redoList = new ArrayList<Command>();
	private List<String> loadedLogsFromFile = new ArrayList<String>();
	private CommandParser parser = new CommandParser();
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		
		this.model = model;
		this.frame = frame;
	}

	public void mouseClicked(MouseEvent e)  {
		if(frame.getTglbtnPoint()) 
		{		
			Point p=new Point(e.getX(),e.getY()); //klik	
			DlgPoint dlgP=new DlgPoint();
			dlgP.getBtnColor().setVisible(false);
			dlgP.getBtnColor().setBackground(colOuter);
			dlgP.setTxtXEditable(false); 
			dlgP.setTxtYEditable(false);
			dlgP.setTxtX(Integer.toString(p.getX()));
			dlgP.setTxtY(Integer.toString(p.getY()));
			dlgP.setVisible(true);
			
			if(dlgP.isOk())
			{
				p.setOuterColor(colOuter);
				
				Command cmdAdd = new Add(p,model);
				loadedLogsFromFile = new ArrayList<String>();
				execute(cmdAdd);
				
				frame.getBtnUndo().setEnabled(true);
				frame.getBtnRedo().setEnabled(false);
		  }
		}
		else if(frame.getTglbtnDonut()) {
			Point center = new Point(e.getX(),e.getY());
			DlgDonut dld = new DlgDonut();
			dld.setTxtCenterX(Integer.toString(center.getX()));
			dld.setTxtCenterY(Integer.toString(center.getY()));
			dld.getBtnExteriorColor().setBackground(colOuter);
			dld.getBtnInteriorColor().setBackground(colInner);
			dld.setVisible(true);
			
			try {
				String radius = dld.getTxtRadius();
				String innerRadius = dld.getTxtInnerRadius();
				
				Donut donut = new Donut(center, Integer.parseInt(radius), Integer.parseInt(innerRadius), dld.getBtnInteriorColor().getBackground(), dld.getBtnExteriorColor().getBackground());
				if(dld.isOk()) {
					Command cmdAdd = new Add(donut,model);
	                
					loadedLogsFromFile = new ArrayList<String>();
					execute(cmdAdd);
					
					frame.getBtnUndo().setEnabled(true);
					frame.getBtnRedo().setEnabled(false);
				}
			}catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(new JFrame(), "Popunite sva polja ili provjerite tip podataka koji ste unijeli!", "Greska", JOptionPane.WARNING_MESSAGE);
				return;
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(new JFrame(), "Poluprecnici moraju biti pozitivni i unutrasnji poluprecnik mora biti manji od spoljasnjeg!", "Greska", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
		}
		
		if(frame.getTglbtnLine()) {
			
			if(startPoint == null) {
				startPoint = new Point(e.getX(), e.getY());
			} else{			
				Line l = new Line(startPoint,new Point(e.getX(),e.getY()));
	    		DlgLine dlgL=new DlgLine();
			    dlgL.setTxtStartPointX(Integer.toString(l.getStartPoint().getX()));
			 	dlgL.setTxtStartPointY(Integer.toString(l.getStartPoint().getY()));
				dlgL.setTxtEndPointX(Integer.toString(l.getEndPoint().getX()));
				dlgL.setTxtEndPointY(Integer.toString(l.getEndPoint().getY()));
				dlgL.getBtnColor().setBackground(colOuter);
				dlgL.setVisible(true);
				if(dlgL.isOk()) {
					 l.setOuterColor(dlgL.getBtnColor().getBackground());
					 
				     Command cmdAdd = new Add(l,model);
				     
				     loadedLogsFromFile = new ArrayList<String>();
				     execute(cmdAdd);
		
		             startPoint = null;
		
				 	frame.getBtnUndo().setEnabled(true);
					frame.getBtnRedo().setEnabled(false);
	
				}
			}
//			
		}
		else if(frame.getTglbtnRectangle()) {
			Point upperLeftPoint = new Point(e.getX(),e.getY());
			DlgRectangle dlr = new DlgRectangle();
			dlr.setTxtUpperLeftPointX(Integer.toString(upperLeftPoint.getX()));
			dlr.setTxtUpperLeftPointY(Integer.toString(upperLeftPoint.getY()));
			dlr.getBtnExteriorColor().setBackground(colOuter);
			dlr.getBtnInteriorColor().setBackground(colInner);
			
			dlr.setVisible(true);
			
			if(dlr.isOk()) {
				try {
					String height = dlr.getTxtHeight();
					String width = dlr.getTxtWidth();
					
					Rectangle rec = new Rectangle(upperLeftPoint,Integer.parseInt(height),Integer.parseInt(width), dlr.getBtnInteriorColor().getBackground(), dlr.getBtnExteriorColor().getBackground());
					Command cmdAdd = new Add(rec,model);
					
					loadedLogsFromFile = new ArrayList<String>();
					execute(cmdAdd);
					
					frame.getBtnUndo().setEnabled(true);
					frame.getBtnRedo().setEnabled(false);
				}
				catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(new JFrame(), "Popunite sva polja ili provjerite tip podataka koji ste unijeli!", "Greska", JOptionPane.WARNING_MESSAGE);
					return;
				
				} catch (Exception e1) {
					
					JOptionPane.showMessageDialog(new JFrame(), "Visina i sirina moraju biti pozitivne!", "Greska", JOptionPane.WARNING_MESSAGE);
					return;
				}
	
			}
		}
		else if(frame.getTglbtnCircle()) 
		{
			Point center = new Point(e.getX(),e.getY());
			DlgCircle dlc = new DlgCircle();
			dlc.setTxtCenterX(Integer.toString(center.getX()));
			dlc.setTxtCenterY(Integer.toString(center.getY()));
			dlc.getBtnExteriorColor().setBackground(colOuter);
			dlc.getBtnInteriorColor().setBackground(colInner);
			
			dlc.setVisible(true);
			
			if(dlc.isOk()) {
				Circle circle;
				try {
					String radius = dlc.getTxtRadius();
					circle = new Circle(center,Integer.parseInt(radius), dlc.getBtnInteriorColor().getBackground(), dlc.getBtnExteriorColor().getBackground());
					Command cmdAdd = new Add(circle,model);

					loadedLogsFromFile = new ArrayList<String>();
					execute(cmdAdd);
					
					frame.getBtnUndo().setEnabled(true);
					frame.getBtnRedo().setEnabled(false);
				}catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(new JFrame(), "Popunite sva polja ili provjerite tip podataka koji ste unijeli!", "Greska", JOptionPane.WARNING_MESSAGE);
					return;
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Poluprecnik mora da bude pozitivan!", "Greska", JOptionPane.WARNING_MESSAGE);
					return;
				}
		
			}
		}
		
		else if (frame.getTglbtnHexagon()) {
			Point center=new Point(e.getX(),e.getY());
			DlgHexagon dlgH=new DlgHexagon();
			dlgH.getBtnInteriorColor().setVisible(true);
			dlgH.getBtnExteriorColor().setVisible(true);
			dlgH.setTxtCenterXEditable(false);
			dlgH.setTxtCenterYEditable(false);
			dlgH.setTxtCenterX(Integer.toString(center.getX()));
			dlgH.setTxtCenterY(Integer.toString(center.getY()));
			dlgH.getBtnInteriorColor().setBackground(colInner);
			dlgH.getBtnExteriorColor().setBackground(colOuter);
			
			dlgH.setVisible(true);
			try
			{
				if(dlgH.isOk())
				{
					int radius=Integer.parseInt(dlgH.getTxtRadius());	
					HexagonAdapter h = null;
					try {
						h = new HexagonAdapter(center.getX(),center.getY(),radius, dlgH.getBtnInteriorColor().getBackground(), dlgH.getBtnExteriorColor().getBackground());
						Command cmdAdd=new Add(h,model);
						
						loadedLogsFromFile = new ArrayList<String>();
	 				    execute(cmdAdd);
	 				    
						frame.getBtnUndo().setEnabled(true);
						frame.getBtnRedo().setEnabled(false);
					}catch(NumberFormatException ex)
					{
						JOptionPane.showMessageDialog(new JFrame(), "Popunite sva polja ili provjerite tip podataka koji ste unijeli!", "Greska", JOptionPane.WARNING_MESSAGE);
						return;
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(new JFrame(), "Poluprecnik mora da bude pozitivan!", "Greska", JOptionPane.WARNING_MESSAGE);
						return;
					}					
				}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Fill in all the fields or check data type you have entered!", "Error", JOptionPane.WARNING_MESSAGE);
				return;
			} catch (Exception e1) {
			
				JOptionPane.showMessageDialog(new JFrame(), "Radius must be positive!", "Error", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		else if(frame.getTglbtnSelection()) {

			boolean notFoundShape=true;
			Point p=new Point(e.getX(),e.getY());
			List<Shape> selectedShapes = null ;
			for (int i = model.getShapes().size() - 1; i >= 0; i--) {
				Shape shape = model.getShapes().get(i);
				if(shape.contains(p))
				{
					notFoundShape=false;
					
					if(!shape.isSelected())
					{
						
						Command cmdSelection = new Selection(shape);
						
						loadedLogsFromFile = new ArrayList<String>();
						execute(cmdSelection);
						
						frame.getBtnUndo().setEnabled(true);
						frame.getBtnRedo().setEnabled(false);
					}
					else 
					{
						List<Shape> placeholder = new ArrayList<Shape>();
						placeholder.add(shape);
						Command cmdUnselect = new Unselection(placeholder);
						
						loadedLogsFromFile = new ArrayList<String>();
						execute(cmdUnselect);
						
						frame.getBtnUndo().setEnabled(true);
						frame.getBtnRedo().setEnabled(false);
					}
					break;
				}			
			}
			if(notFoundShape) {
				if (model.getSelectedShapes().size() != 0) {
					Command cmdUnselect = new Unselection(model.getSelectedShapes());
					
					loadedLogsFromFile = new ArrayList<String>();
					execute(cmdUnselect);
					
					frame.getBtnUndo().setEnabled(true);
					frame.getBtnRedo().setEnabled(false);
				}
			}
		}
		
		frame.repaint();
	}
	
	public void execute(Command command) {
		
		command.execute();	
			 	
		undoList.add(command);
		redoList.clear();
		
		sendToObservers();
		
		frame.getDlm().addElement(command.commandToString());
		logs.add(command.commandToString());
	}
	

	public void bringToBack() {
		if (model.getSelectedShape() == null)
			return;
		
		Shape s=model.getSelectedShape();
		

		BringToBack cmdBToB=new BringToBack(model,s);
		
		loadedLogsFromFile = new ArrayList<String>();
		execute(cmdBToB);
		frame.repaint();
		
	}
	

	public void undo() {

		Command command = undoList.get(undoList.size() - 1);
		undoList.remove(undoList.size() - 1);
		
		command.unexecute();
		
		redoList.add(command);
		frame.getBtnRedo().setEnabled(true);
		
		if(undoList.size() == 0) {
			frame.getBtnUndo().setEnabled(false);
		}
		else {
			frame.getBtnUndo().setEnabled(true);
		}
		
		loadedLogsFromFile = new ArrayList<String>();
		
		sendToObservers();
		
		frame.getDlm().addElement("UNDO");
		logs.add("UNDO");
		frame.repaint();
		
	}

	public void redo() {
		Command command = redoList.get(redoList.size() - 1);
		redoList.remove(redoList.size() - 1);
		
		command.execute();
		
		undoList.add(command);
		frame.getBtnUndo().setEnabled(true);
		
		if(redoList.size() == 0) {
			frame.getBtnRedo().setEnabled(false);
		}
		else {
			frame.getBtnRedo().setEnabled(true);
		}
		
		loadedLogsFromFile = new ArrayList<String>();
		
		sendToObservers();
		
		frame.getDlm().addElement("REDO");
		logs.add("REDO");
		frame.repaint();
	}

	public void toBack() {
		if (model.getSelectedShape() == null)
			return;
		Shape s=model.getSelectedShape();
		
		ToBack cmdToB=new ToBack(model,s);
		
		loadedLogsFromFile = new ArrayList<String>();
		execute(cmdToB);
		frame.repaint();	
		}

	public void toFront() {
		if (model.getSelectedShape() == null)
			return;
		Shape s=model.getSelectedShape();
		ToFront cmdToF=new ToFront(model,s);
		
		loadedLogsFromFile = new ArrayList<String>();
		execute(cmdToF);
		frame.repaint();
		
	}

	public void bringToFront() {
		if (model.getSelectedShape() == null)
			return;
		Shape s=model.getSelectedShape();
	

		BringToFront cmdBToF=new BringToFront(model,s);
		
		loadedLogsFromFile = new ArrayList<String>();
		execute(cmdBToF);
		frame.repaint();
		
	}


	public void mouseClickedModify(MouseEvent e) {
		 if(e.getSource() == frame.getBtnModify() && model.getSelectedShape() != null) {
			 
			    Shape shape = model.getSelectedShape();
				String s = shape.getClass().getSimpleName();
				Shape newState;
				
				switch(s) {
				case "Point":
					try {

						Point p = (Point) shape;
						Point point = pointDialog(p.getX(), p.getY(), p.getOuterColor(), true);
						if (point != null) {
							point.setSelected(true);
							PointModification pointMod = new PointModification(p,point);
							
							loadedLogsFromFile = new ArrayList<String>();
							execute(pointMod);
							
						}
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				
				break;
				case "Circle":
					try {
						Circle c = (Circle) shape;
						Circle circle = circleDialog(c.getCenter().getX(),c.getCenter().getY(),c.getRadius(),c.getInnerColor(),c.getOuterColor(),true);
						
						if(circle !=null) {
							circle.setSelected(true);
							CircleModification circleMod = new CircleModification(c,circle);
							
							loadedLogsFromFile = new ArrayList<String>();
							execute(circleMod);
							
						}
						
						
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
					 break;
					 
				case "Rectangle":
					try {
						Rectangle r = (Rectangle) shape;
						Rectangle rectangle = rectangleDialog(r.getUpperLeftPoint().getX(),r.getUpperLeftPoint().getY(),r.getHeight(),r.getWidth(),r.getInnerColor(),r.getOuterColor(),true);
						
						if(rectangle !=null) {
							rectangle.setSelected(true);
							RectangleModification recMod = new RectangleModification(r,rectangle);
							
							loadedLogsFromFile = new ArrayList<String>();
							execute(recMod);
							
						}
						
						
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
					break;
					
				case "Line":
					try {
						Line l = (Line)shape;
						Line line = lineDialog(l.getStartPoint().getX(),l.getStartPoint().getY(),l.getEndPoint().getX(),l.getEndPoint().getY(),l.getOuterColor(),true);
						if(line !=null) {
							line.setSelected(true);
							LineModification lineMod = new LineModification(l,line);
							
							loadedLogsFromFile = new ArrayList<String>();
							execute(lineMod);
							
						}
					}catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
					
					break;
					/////
				case "Donut":
					try {
						Donut d = (Donut) shape;
						Donut donut = donutDialog(d.getCenter().getX(), d.getCenter().getY(), d.getInnerRadius(),
								d.getRadius(), d.getInnerColor(), d.getOuterColor(), true);
						if(donut != null) {
							donut.setSelected(false);
							DonutModification donutMod = new DonutModification(d,donut);
							
							loadedLogsFromFile = new ArrayList<String>();
							execute(donutMod);
							
						}
					}catch(Exception ex) {
						ex.printStackTrace();
					}
					break;
				case "HexagonAdapter":
					try {
						HexagonAdapter h = (HexagonAdapter) shape;
						HexagonAdapter hexagon = hexagonDialog(h.getX(),h.getY(),h.getRadius(),h.getInterColor(),h.getOuterColor());
						if(hexagon !=null) {
							hexagon.setSelected(false);
							HexagonModification hexMod = new HexagonModification(h,hexagon);
							
							loadedLogsFromFile = new ArrayList<String>();
							execute(hexMod);
						}
					}catch(Exception ex) {
						ex.printStackTrace();
					}
					break;
					///
				default:
						break;
				}
					frame.repaint();
				
		 }
		 else {
			 JOptionPane.showMessageDialog(null, "Choose 1 object");
		 }
	}

	public void mouseClickedDelete(MouseEvent e) {


		ArrayList<Shape> shapesForDelete=new ArrayList<Shape>();
		for(Shape s: model.getSelectedShapes()) {
			shapesForDelete.add(s);
			
		}
		
		int answer=JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to delete shapes?", "Delete", JOptionPane.YES_NO_OPTION);
		
		if( answer==JOptionPane.YES_OPTION)
		{
			Remove command=new Remove(shapesForDelete,model);
			
			loadedLogsFromFile = new ArrayList<String>();
			execute(command);
			
			frame.repaint();

			frame.getBtnUndo().setEnabled(true);
			frame.getBtnRedo().setEnabled(false);
		}

	}
		
	public void sendToObservers() {
		setChanged();
		
		Transfer transfer = new Transfer();
		transfer.setEnableModifyButton(model.getSelectedShapes().size() == 1);
		transfer.setEnableDeleteButton(model.getSelectedShapes().size() >= 1);
		
		Shape selectedShape = model.getSelectedShape();
		if (selectedShape != null && model.getSelectedShapes().size() == 1 && model.getShapes().size() > 1) {
			int indexOfShape = model.getIndexOfShape(selectedShape);
			if (indexOfShape == 0) {
				transfer.setEnableBringToBackButton(false);
				transfer.setEnableBringToFrontButton(true);
				transfer.setEnableToBackButton(false);
				transfer.setEnableToFrontButton(true);
			} else if (indexOfShape == model.getShapes().size() - 1) {
				transfer.setEnableBringToBackButton(true);
				transfer.setEnableBringToFrontButton(false);
				transfer.setEnableToBackButton(true);
				transfer.setEnableToFrontButton(false);
			} else {
				transfer.setEnableBringToBackButton(true);
				transfer.setEnableBringToFrontButton(true);
				transfer.setEnableToBackButton(true);
				transfer.setEnableToFrontButton(true);
			}
		} else {
			transfer.setEnableBringToBackButton(false);
			transfer.setEnableBringToFrontButton(false);
			transfer.setEnableToBackButton(false);
			transfer.setEnableToFrontButton(false);
		}
		transfer.setEnableExecuteLogButton(loadedLogsFromFile.size() > 0);
		
		notifyObservers(transfer);
	}
	

	public void exportToLog() {
		
		ArrayList<Object> helpList=new ArrayList<Object>();
		helpList.addAll(logs);
		ExportManager exportManager=new ExportManager(new SaveLogToFile());
		String path=FileHelp.showFileDialogSave("log");
		if(path!=null) {
			exportManager.exportData(helpList, path);
		}
		
	}

	public void importFromLog() {
		ImportManager manager = new ImportManager(new LoadLogFromFile());
		String path = FileOperationsHelper.showFileDialogOpen("log");
		parser = new CommandParser();
		
		if (path != null) {
			ArrayList<Object> bundle = manager.importData(path);

			// Put log file lines from bundle to its array list
			loadedLogsFromFile = (ArrayList<String>) bundle.get(0);
		}
		
		sendToObservers();
	}

	public void exportToDraw() {
		if (model.getShapes().size() == 0) {
			DialogsHelper.showErrorMessage("You cannot export empty drawing!");
			return;
		}

		ArrayList<Object> bundle = new ArrayList<Object>();
		bundle.add(model.getShapes());

		ExportManager manager = new ExportManager(new SerializeShapesToFile());
		String path = FileOperationsHelper.showFileDialogSave("drwg");
		if (path != null)
			manager.exportData(bundle, path);
	}

	public void importFromDraw() { 
		ImportManager manager = new ImportManager(new LoadDrawingFromFile());
		String path = FileOperationsHelper.showFileDialogOpen("drwg");
		model.setShapes(new ArrayList<Shape>());
		if (path != null) {
			ArrayList<Object> bundle = manager.importData(path);
			// If ArrayList setter was used observers wouldn't work at all
			for (Shape s : (ArrayList<Shape>) bundle.get(0)) {
//				s.setObserver(new ShapeObserver(frame, model));
				model.add(s);
			}
			
			sendToObservers();
			
			frame.getBtnUndo().setEnabled(true);
			frame.getBtnRedo().setEnabled(false);
			
			logs = new ArrayList<String>();
			frame.setDlm(new DefaultListModel<String>());
			
			frame.repaint();
		}
		
	}

	public void executeLog() {
		try {
			Command command = parser.parse(loadedLogsFromFile.get(0), model, frame);
			loadedLogsFromFile.remove(0);
			execute(command);
			
			
			frame.getBtnUndo().setEnabled(undoList.size() > 0);
			frame.getBtnRedo().setEnabled(redoList.size() > 0);
			
			frame.getView().repaint();
		} catch (Exception e) {
			e.printStackTrace();
			loadedLogsFromFile = new ArrayList<String>();
			JOptionPane.showMessageDialog(new JFrame(), "Greska!", "Greska", JOptionPane.WARNING_MESSAGE);
		}
		sendToObservers();
	}
	
	private Point pointDialog(int x,int y,Color col,boolean editable) {
		DlgPoint dlp = new DlgPoint();
		dlp.setTxtX(Integer.toString(x));
		dlp.setTxtY(Integer.toString(y));
		dlp.getBtnColor().setBackground(col);
		
		dlp.setTxtXEditable(editable);
		dlp.setTxtYEditable(editable);
		dlp.setVisible(true);
		if(dlp.isOk()) {
			x = Integer.parseInt(dlp.getTxtX());
			y = Integer.parseInt(dlp.getTxtY());
			col = dlp.getBtnColor().getBackground();
			Point point = new Point(x, y, col);
			point.setSelected(true);
			return point;
		}
		return null;
	}
	
	
	private Circle circleDialog(int x, int y, int radius, Color innerColor, Color outerColor, boolean editable) throws Exception {
		DlgCircle dlc = new DlgCircle();
		dlc.setTxtCenterX(Integer.toString(x));
		dlc.setTxtCenterY(Integer.toString(y));
		dlc.setTxtRadius(Integer.toString(radius));
		dlc.getBtnExteriorColor().setBackground(outerColor);
		dlc.getBtnInteriorColor().setBackground(innerColor);
		
		dlc.setTxtCenterXEditable(editable);
		dlc.setTxtCenterYEditable(editable);
		dlc.setVisible(true);
		if(dlc.isOk()) {
		      x = Integer.parseInt(dlc.getTxtCenterX());
		      y = Integer.parseInt(dlc.getTxtCenterY());
		      radius = Integer.parseInt(dlc.getTxtRadius());
		      outerColor = dlc.getBtnExteriorColor().getBackground();
		      innerColor = dlc.getBtnInteriorColor().getBackground();
		      Circle circle = new Circle(new Point(x,y),radius,innerColor,outerColor);
		      circle.setSelected(true);
		      return circle;
		}
		return null;
	}
	
	
	private Rectangle rectangleDialog(int x,int y,int height,int width,Color innerColor,Color outerColor,boolean editable) throws Exception {
		DlgRectangle dlr = new DlgRectangle();
		dlr.setTxtUpperLeftPointX(Integer.toString(x));
		dlr.setTxtUpperLeftPointY(Integer.toString(y));
		dlr.setTxtHeight(Integer.toString(height));
		dlr.setTxtWidth(Integer.toString(width));
		dlr.getBtnExteriorColor().setBackground(outerColor);
		dlr.getBtnInteriorColor().setBackground(innerColor);
		
		dlr.setTxtUpperLeftPointXEditable(editable);
		dlr.setTxtUpperLeftPointYEditable(editable);
		dlr.setVisible(true);
		if(dlr.isOk()) {
			x=Integer.parseInt(dlr.getTxtUpperLeftPointX());
			y=Integer.parseInt(dlr.getTxtUpperLeftPointY());
			height = Integer.parseInt(dlr.getTxtHeight());
			width = Integer.parseInt(dlr.getTxtWidth());
			innerColor = dlr.getBtnInteriorColor().getBackground();
			outerColor = dlr.getBtnExteriorColor().getBackground();
			Rectangle rec = new Rectangle(new Point(x,y),height,width,innerColor,outerColor); 
			rec.setSelected(true);
			return rec;
		}
		return null;
	}
	
	
	private Line lineDialog(int x1, int y1, int x2, int y2, Color outerColor, boolean editable) {
		DlgLine dll = new DlgLine();
		dll.setTxtStartPointX(Integer.toString(x1));
		dll.setTxtStartPointY(Integer.toString(y1));
		dll.setTxtEndPointX(Integer.toString(x2));
		dll.setTxtEndPointY(Integer.toString(y2));
		dll.setTxtStartPointXEditable(editable);
		dll.setTxtStartPointYEditable(editable);
		dll.setTxtEndPointXEditable(editable);
		dll.setTxtEndPointYEditable(editable);
		dll.getBtnColor().setBackground(outerColor);
		
		dll.setVisible(true);
		if(dll.isOk()) {
			x1 = Integer.parseInt(dll.getTxtStartPointX());
			y1 = Integer.parseInt(dll.getTxtStartPointY());
			x2 = Integer.parseInt(dll.getTxtEndPointX());
			y2 = Integer.parseInt(dll.getTxtEndPointY());
			outerColor = dll.getBtnColor().getBackground();
			Line line = new Line(new Point(x1,y1),new Point(x2,y2),outerColor);
			line.setSelected(true);
			return line;
		}
		return null;
	}
	
	
	private Donut donutDialog(int x, int y, int innerRadius, int radius, Color innerColor, Color outerColor,
			boolean editable) throws Exception {
		
		   DlgDonut dld = new DlgDonut();
		   dld.setTxtCenterX(Integer.toString(x));
		   dld.setTxtCenterY(Integer.toString(y));
		   dld.setTxtInnerRadius(Integer.toString(innerRadius));
		   dld.setTxtRadius(Integer.toString(radius));
		   dld.setTxtCenterXEditable(editable);
		   dld.setTxtCenterYEditable(editable);
		   dld.getBtnExteriorColor().setBackground(outerColor);
		   dld.getBtnInteriorColor().setBackground(innerColor);
		   
		   dld.setVisible(true);
		   if(dld.isOk()) {
			   x=Integer.parseInt(dld.getTxtCenterX());
			   y=Integer.parseInt(dld.getTxtCenterY());
			   innerRadius = Integer.parseInt(dld.getTxtInnerRadius());
			   radius = Integer.parseInt(dld.getTxtRadius());
			   innerColor =dld.getBtnInteriorColor().getBackground();
			   outerColor = dld.getBtnExteriorColor().getBackground();
			   Donut d = new Donut(new Point(x,y),radius,innerRadius,innerColor,outerColor); 
			   d.setSelected(true);
			   return d;
		   }
		   return null;
	}
	
	private HexagonAdapter hexagonDialog(int x,int y,int radius,Color innerColor,Color outerColor) throws Exception {
		DlgHexagon dlh = new DlgHexagon();
		dlh.setTxtCenterX(Integer.toString(x));
		dlh.setTxtCenterY(Integer.toString(y));
		dlh.setTxtRadius(Integer.toString(radius));
		dlh.setTxtCenterXEditable(true);
		dlh.setTxtCenterYEditable(true);
		dlh.getBtnExteriorColor().setBackground(outerColor);
		dlh.getBtnInteriorColor().setBackground(innerColor);
		
		dlh.setVisible(true);
		if(dlh.isOk()) {
			x = Integer.parseInt(dlh.getTxtCenterX());
			y=Integer.parseInt(dlh.getTxtCenterY());
			radius = Integer.parseInt(dlh.getTxtRadius());
			innerColor = dlh.getBtnInteriorColor().getBackground();
			outerColor = dlh.getBtnExteriorColor().getBackground();
			HexagonAdapter hex = new HexagonAdapter(x,y,radius,innerColor,outerColor);
			hex.setSelected(true);
			return hex;
		}
		return null;
	}
	
	
	public void addOuterColor() {
		
		colOuter=JColorChooser.showDialog(null, "Choose a color!", colOuter);
		if(colOuter!=null) {
		frame.getBtnOuterColor().setBackground(colOuter);
		}
	}	
	
	public void addInnerColor() {
		
		colInner=JColorChooser.showDialog(null, "Choose a color!", colInner);
		if(colInner!=null) {
			frame.getBtnInnerColor().setBackground(colInner);
		}
	}	
	

}

