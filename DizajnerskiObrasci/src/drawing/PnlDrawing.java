//package drawing;
//
//import java.awt.Graphics;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.Stack;
//
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//
//import commandsnouse.AddCommand;
//import commandsnouse.Command;
//import commandsnouse.DeleteCommand;
//import commandsnouse.ModifyCommand;
//
//public class PnlDrawing extends JPanel {
//
//	private FrmDrawing frame;
//	private ArrayList<Shape> shapes = new ArrayList<Shape>();
//	private Stack<Command> undoStack = new Stack();/////
//	private Stack<Command> redoStack = new Stack();/////
//	private Point startPoint;
//	private Shape selected;
//	
//	
//	public PnlDrawing() {
//		addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				thisMouseClicked(e);
//			}
//		});
//	}
//	
//	public PnlDrawing(FrmDrawing frame) {
//
//		this.frame=frame;
//		addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				thisMouseClicked(e);
//			}
//		});
//	}
//	
//	protected void thisMouseClicked(MouseEvent e) {
//		Shape newShape = null;
//		if(frame.getTglbtnSelect().isSelected()) {
//			selected= null;
//			Iterator<Shape>it = shapes.iterator();
//			while(it.hasNext()) {
//				Shape shape =it.next();
//				//shape.setSelected(false);
//				if(shape.containts(e.getX(), e.getY()))
//					selected=shape;
//				
//				
//			}
//			if(selected != null)
//				selected.setSelected(true);
//				this.frame.showBtns();////////////////
//			
//		} else if (frame.getTglbtnPoint().isSelected()) {
//			
//			Point p = new Point(e.getX(),e.getY());
//			DlgPoint dlg = new DlgPoint();
//			dlg.getTxtPointX().setText(Integer.toString(p.getX()));
//			dlg.getTxtPointY().setText(Integer.toString(p.getY()));
//			dlg.getTxtPointX().setEditable(false);
//			dlg.getTxtPointY().setEditable(false);
//			dlg.setVisible(true);
//			try {
//				if(dlg.isCommited()) {
//					newShape= new Point(p.getX(), p.getY());
//					((Point)newShape).setCol(dlg.getColor());
//				}
//			}catch(NumberFormatException ex){
//				JOptionPane.showMessageDialog(new JFrame(), "Invalid data type", "ERROR!",JOptionPane.WARNING_MESSAGE);
//			}catch(Exception ex) {
//				JOptionPane.showMessageDialog(new JFrame(), "Values must be positive!","Error!",JOptionPane.WARNING_MESSAGE);
//			}
//			
//			
//		} else if (frame.getTglbtnLine().isSelected()) {
//			
//			if(startPoint==null)
//				startPoint=new Point(e.getX(), e.getY());
//			else {
//				Point endPoint= new Point(e.getX(),e.getY());
//				
//			DlgLine dlg = new DlgLine();
//			dlg.getTxtStartX().setText(Integer.toString(startPoint.getX()));
//			dlg.getTxtStartY().setText(Integer.toString(startPoint.getY()));
//			dlg.getTxtEndX().setText(Integer.toString(endPoint.getX()));
//			dlg.getTxtEndY().setText(Integer.toString(endPoint.getY()));
//			dlg.getTxtStartX().setEditable(false);
//			dlg.getTxtStartY().setEditable(false);
//			dlg.getTxtEndX().setEditable(false);
//			dlg.getTxtEndY().setEditable(false);
//			dlg.setVisible(true);
//			try {
//				if(dlg.isCommited()) {
//					newShape= new Line(startPoint,endPoint);
//					((Line)newShape).setCol(dlg.getColor());
//				}
//			}catch(NumberFormatException ex){
//				JOptionPane.showMessageDialog(new JFrame(), "Invalid data type", "ERROR!",JOptionPane.WARNING_MESSAGE);
//			}catch(Exception ex) {
//				JOptionPane.showMessageDialog(new JFrame(), "Values must be positive!","Error!",JOptionPane.WARNING_MESSAGE);
//			}
//			
//				startPoint=null;
//				endPoint=null;
//			
//			}
//		} else if (frame.getTglbtnRectangle().isSelected()) {
//			DlgRectangle dlg = new DlgRectangle();
//			Point p= new Point(e.getX(), e.getY());
//			dlg.getTxtUpperLeftX().setText(Integer.toString(e.getX()));
//			dlg.getTxtUpperLeftY().setText(Integer.toString(e.getY()));
//			dlg.getTxtUpperLeftX().setEditable(false);
//			dlg.getTxtUpperLeftY().setEditable(false);
//			dlg.setVisible(true);
//			
//			try {
//				if(dlg.isCommited()) {
//				
//					int width = Integer.parseInt(dlg.getTxtWidth().getText());
//					int height=Integer.parseInt(dlg.getTxtHeight().getText());
//					newShape = new Rectangle(new Point(p.getX(), p.getY()), width,height);
//					((Rectangle)newShape).setLineCol(dlg.getLineColor());
//					((Rectangle)newShape).setInnerCol(dlg.getInnerColor());
//				}
//				}catch(NumberFormatException ex){
//					JOptionPane.showMessageDialog(new JFrame(), "Invalid data type", "ERROR!",JOptionPane.WARNING_MESSAGE);
//				}catch(Exception ex) {
//					JOptionPane.showMessageDialog(new JFrame(), "Values must be positive!","Error!",JOptionPane.WARNING_MESSAGE);
//				}
//				
//			
//			
//		}else if(frame.getTglbtnCircle().isSelected()) {
//			DlgCircle dlg= new DlgCircle();
//			Point p= new Point(e.getX(), e.getY());
//			dlg.getTxtCenterX().setText(Integer.toString(e.getX()));
//			dlg.getTxtCenterY().setText(Integer.toString(e.getY()));
//			dlg.getTxtCenterX().setEditable(false);
//			dlg.getTxtCenterY().setEditable(false);
//			dlg.setVisible(true);
//			if(dlg.isCommited()) {
//				try {
//					int radius= Integer.parseInt(dlg.getTxtRadius().getText());
//					newShape = new Circle(new Point(p.getX(),p.getY()), radius);
//					((Circle)newShape).setLineCol(dlg.getLineColor());
//					((Circle)newShape).setInnerCol(dlg.getInnerColor());
//					
//				} catch(NumberFormatException ex) {
//					JOptionPane.showMessageDialog(new JFrame(), "Invalid data type","ERROR!", JOptionPane.WARNING_MESSAGE);
//				} catch(Exception ex) {
//					JOptionPane.showMessageDialog(new JFrame(), "Values must be positive!", "Error!", JOptionPane.WARNING_MESSAGE);
//				}
//			}
//		} else if(frame.getTglbtnDonut().isSelected()) {
//			DlgDonut dlg = new DlgDonut();
//			Point p = new Point(e.getX(),e.getY());
//			dlg.getTxtCenterX().setText(Integer.toString(e.getX()));
//			dlg.getTxtCenterY().setText(Integer.toString(e.getY()));
//			dlg.getTxtCenterX().setEditable(false);
//			dlg.getTxtCenterY().setEditable(false);
//			dlg.setVisible(true);
//			if(dlg.isCommited()) {
//				try {
//					int radius= Integer.parseInt(dlg.getTxtRadius().getText());
//					int innerRadius= Integer.parseInt(dlg.getTxtInnerRadius().getText());
//					newShape = new Donut(new Point(p.getX(), p.getY()), radius, innerRadius);
//					((Donut)newShape).setLineCol(dlg.getOuterLine());
//					((Donut)newShape).setInnerCol(dlg.getOuterFill());
//					((Donut)newShape).setInnerLine(dlg.getInnerLine());
//					((Donut)newShape).setInnerFill(dlg.getInnerFill());
//					
//				}catch(NumberFormatException ex) {
//					JOptionPane.showMessageDialog(new JFrame(), "Invalid data type","ERROR!", JOptionPane.WARNING_MESSAGE);
//				} catch(Exception ex) {
//					JOptionPane.showMessageDialog(new JFrame(), "Radius must be greater then inner radius", "Error!", JOptionPane.WARNING_MESSAGE);
//				}
//			}
//		
//		}
//		
//		
//		
//		if(newShape!=null)
//			shapes.add(newShape);
//			AddCommand addCommand = new AddCommand(shapes, newShape);
//			undoStack.add(addCommand);
//		
//			this.frame.showRedo();/////kuca
//			this.frame.showUndo();/////kuca
//			
//		if (selected == null) {
//			this.frame.hideBtns();
//			Iterator<Shape>it = shapes.iterator();
//			while(it.hasNext()) {
//				Shape shape =it.next();
//				shape.setSelected(false);
//				
//			}
//		}
//		repaint();
//		
//	}
//		public void paint(Graphics g) {
//			super.paint(g);
//			Iterator<Shape> it = shapes.iterator();
//			while(it.hasNext())
//			it.next().draw(g);
//		}
//
//		public ArrayList<Shape> getShapes() {
//			return shapes;
//		}
//
//		public void setShapes(ArrayList<Shape> shapes) {
//			this.shapes = shapes;
//		}
//
//		public Shape getSelected() {
//			return selected;
//		}
//
//		public void setSelected(Shape selected) {
//			this.selected = selected;
//		}
//
//
//		public void setStartPoint(Point startPoint) {
//			this.startPoint = startPoint;
//		}
//		
//		public void undo() {
//			Command command = undoStack.pop();
//			command.undo();
//			redoStack.push(command);
//			if (command instanceof AddCommand) {
//				AddCommand addCommand = (AddCommand) command;
//				shapes = addCommand.getShapes();
//				
//			} else if (command instanceof DeleteCommand ){
//			     DeleteCommand deleteCommand = (DeleteCommand) command;
//			     shapes= deleteCommand.getShapes();
//			     
//			}else  
//			{
//				ModifyCommand modifyCommand= (ModifyCommand)command;
//				shapes = modifyCommand.getShapes();
//				
//			}
//			repaint();
//		}
//		
//		
//		public void redo() {//////kuca
//			Command command2 = redoStack.lastElement();
//			command2.redo();
//			if (command2 instanceof AddCommand) {
//				AddCommand addCommand2 = (AddCommand) command2;
//				shapes = addCommand2.getShapes();
//				
//			} else if (command2 instanceof DeleteCommand ){
//			     DeleteCommand deleteCommand2 = (DeleteCommand) command2;
//			     shapes= deleteCommand2.getShapes();
//			     
//			}else  
//			{
//				ModifyCommand modifyCommand2= (ModifyCommand)command2;
//				shapes = modifyCommand2.getShapes();
//				
//			}
//			repaint();
//		}
//		
//		
//
//}
