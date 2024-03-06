//package drawing;
//
//import java.awt.BorderLayout;
//import java.awt.EventQueue;
//
//import javax.swing.ButtonGroup;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//
//import javax.swing.JToolBar;
//import javax.swing.JToggleButton;
//import java.awt.Color;
//import java.awt.Dimension;
//
//import javax.swing.SwingConstants;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.util.ArrayList;
//import java.util.Stack;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//import java.awt.Panel;
//import javax.swing.GroupLayout;
//import javax.swing.GroupLayout.Alignment;
//import javax.swing.LayoutStyle.ComponentPlacement;
//import java.awt.FlowLayout;
//import java.awt.Frame;
//import java.awt.CardLayout;
//
//public class FrmDrawing extends JFrame {
//
//
//	private JPanel ContentPane;
//
//	JToggleButton tglbtnPoint;
//	JToggleButton tglbtnLine;
//	JToggleButton tglbtnRectangle;
//	JToggleButton tglbtnCircle;
//	JToggleButton tglbtnDonut;
//	JToggleButton tglbtnSelect;
//	JToggleButton tglbtnModify;
//	JToggleButton tglbtnDelete;
//	JToggleButton tglbtnUndo;
//	JToggleButton tglbtnRedo;
//
//	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrmDrawing frame = new FrmDrawing();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public FrmDrawing() {
//		PnlDrawing drawing= new PnlDrawing(this);
//		drawing.setForeground(Color.WHITE);
//		drawing.setBackground(Color.LIGHT_GRAY);
//		setExtendedState(MAXIMIZED_BOTH);
//		setTitle("IT46-2017 Dragan Krsmanovic");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 665, 429);
//		ContentPane = new JPanel();
//		ContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(ContentPane);
//		
//	
//		
//		
//		JToolBar toolBar = new JToolBar();
//		ButtonGroup group= new ButtonGroup();
//		
//		tglbtnPoint = new JToggleButton("Point");
//		tglbtnPoint.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				drawing.setStartPoint(null);
//			}
//		});
//		tglbtnPoint.setBackground(Color.YELLOW);
//		toolBar.add(tglbtnPoint);
//		group.add(tglbtnPoint);
//		
//		tglbtnLine = new JToggleButton("Line");
//		tglbtnLine.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				drawing.setStartPoint(null);
//			}
//		});
//		tglbtnLine.setBackground(Color.YELLOW);
//		toolBar.add(tglbtnLine);
//		group.add(tglbtnLine);
//		
//		tglbtnRectangle = new JToggleButton("Rectangle");
//		tglbtnRectangle.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				drawing.setStartPoint(null);
//			}
//		});
//		tglbtnRectangle.setBackground(Color.YELLOW);
//		toolBar.add(tglbtnRectangle);
//		group.add(tglbtnRectangle);
//		
//		tglbtnCircle = new JToggleButton("Circle");
//		tglbtnCircle.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				drawing.setStartPoint(null);
//			}
//		});
//		tglbtnCircle.setBackground(Color.YELLOW);
//		toolBar.add(tglbtnCircle);
//		group.add(tglbtnCircle);
//		
//		tglbtnDonut = new JToggleButton("Donut");
//		tglbtnDonut.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				drawing.setStartPoint(null);
//				///
//				
//			}
//		});
//		tglbtnDonut.setBackground(Color.YELLOW);
//		toolBar.add(tglbtnDonut);
//		group.add(tglbtnDonut);
//		
//		
//		tglbtnSelect = new JToggleButton("Select");
//		tglbtnSelect.setHorizontalAlignment(SwingConstants.LEFT);
//		toolBar.add(tglbtnSelect);
//		group.add(tglbtnSelect);
//		
//		///////Undo dugme-k
//		tglbtnUndo = new JToggleButton("Undo");
//		tglbtnUndo.setHorizontalAlignment(SwingConstants.LEFT);
//		toolBar.add(tglbtnUndo);
//		group.add(tglbtnUndo);
//		
//		////REDO DUGME-k
//		tglbtnRedo = new JToggleButton("Redo");
//		tglbtnRedo.setHorizontalAlignment(SwingConstants.LEFT);
//		toolBar.add(tglbtnRedo);
//		group.add(tglbtnRedo);
//		
//		
//		tglbtnModify = new JToggleButton("Modify");
//		tglbtnModify.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if(drawing.getSelected()!=null) {
//				Shape helpShape=drawing.getSelected();
//				ArrayList<Shape> list =drawing.getShapes();
//				int index = list.indexOf(helpShape);
//				
//					if(drawing.getSelected() instanceof Point) {
//						DlgPoint dlg=new DlgPoint();	
//						dlg.getTxtPointX().setText(Integer.toString(((Point)helpShape).getX()));
//						dlg.getTxtPointY().setText(Integer.toString(((Point)helpShape).getY()));
//						dlg.setColor(((Point)helpShape).getCol());
//						dlg.setVisible(true);
//						try {
//							if(dlg.isCommited()) {
//								((Point) helpShape).setX(Integer.parseInt(dlg.getTxtPointX().getText()));
//								((Point) helpShape).setY(Integer.parseInt(dlg.getTxtPointY().getText()));
//								((Point) helpShape).setCol(dlg.getColor());
//								list.set(index, helpShape);
//								drawing.setShapes(list);
//								drawing.setSelected(helpShape);
//								drawing.repaint();
//							}
//						}catch(NumberFormatException ex) {
//							JOptionPane.showMessageDialog(new JFrame(),"Invalid data type","ERROR!", JOptionPane.WARNING_MESSAGE);
//						}
//					}else if(drawing.getSelected() instanceof Line) {
//						DlgLine dlg= new DlgLine();
//						dlg.getTxtStartX().setText(Integer.toString(((Line)helpShape).getStartPoint().getX()));
//						dlg.getTxtStartY().setText(Integer.toString(((Line)helpShape).getStartPoint().getY()));
//						dlg.getTxtEndX().setText(Integer.toString(((Line)helpShape).getEndPoint().getX()));
//						dlg.getTxtEndY().setText(Integer.toString(((Line)helpShape).getEndPoint().getY()));
//						dlg.setColor(((Line)helpShape).getCol());
//						dlg.setVisible(true);
//						try {
//							if(dlg.isCommited()) {
//								((Line)helpShape).getStartPoint().setX(Integer.parseInt(dlg.getTxtStartX().getText()));
//								((Line)helpShape).getStartPoint().setY(Integer.parseInt(dlg.getTxtStartY().getText()));
//								((Line)helpShape).getEndPoint().setX(Integer.parseInt(dlg.getTxtEndX().getText()));
//								((Line)helpShape).getEndPoint().setY(Integer.parseInt(dlg.getTxtEndY().getText()));
//								((Line)helpShape).setCol(dlg.getColor());
//								list.set(index, helpShape);
//								drawing.setShapes(list);
//								drawing.setSelected(helpShape);
//								drawing.repaint();
//							}	
//						}catch(NumberFormatException ex) {
//							JOptionPane.showMessageDialog(new JFrame(),"Invalid data type","ERROR!", JOptionPane.WARNING_MESSAGE);
//						}
//					}else if(drawing.getSelected() instanceof Rectangle) {
//						DlgRectangle dlg = new DlgRectangle();
//						dlg.getTxtUpperLeftX().setText(Integer.toString(((Rectangle)helpShape).getUpperLeft().getX()));
//						dlg.getTxtUpperLeftY().setText(Integer.toString(((Rectangle)helpShape).getUpperLeft().getY()));
//						dlg.getTxtWidth().setText(Integer.toString(((Rectangle)helpShape).getWidth()));
//						dlg.getTxtHeight().setText(Integer.toString(((Rectangle)helpShape).getHeight()));
//						dlg.setLineColor(((Rectangle)helpShape).getLineCol());
//						dlg.setInnerColor(((Rectangle)helpShape).getInnerCol());
//						dlg.setVisible(true);
//						try {
//							if(dlg.isCommited()) {
//								((Rectangle)helpShape).getUpperLeft().setX(Integer.parseInt(dlg.getTxtUpperLeftX().getText()));
//								((Rectangle)helpShape).getUpperLeft().setY(Integer.parseInt(dlg.getTxtUpperLeftY().getText()));
//								((Rectangle)helpShape).setWidth(Integer.parseInt(dlg.getTxtWidth().getText()));
//								((Rectangle)helpShape).setHeight(Integer.parseInt(dlg.getTxtHeight().getText()));
//								((Rectangle)helpShape).setLineCol(dlg.getLineColor());
//								((Rectangle)helpShape).setInnerCol(dlg.getInnerColor());
//								list.set(index, helpShape);
//								drawing.setShapes(list);
//								drawing.setSelected(helpShape);
//								drawing.repaint();
//							}
//						}catch(NumberFormatException ex) {
//							JOptionPane.showMessageDialog(new JFrame(),"Invalid data type","ERROR!", JOptionPane.WARNING_MESSAGE);
//						}catch(Exception ex) {
//							JOptionPane.showMessageDialog(new JFrame(), "Values must be positive!", "Error!", JOptionPane.WARNING_MESSAGE);
//						}
//					} else if(drawing.getSelected() instanceof Donut) {
//						DlgDonut dlg= new DlgDonut();
//						dlg.getTxtCenterX().setText(Integer.toString(((Donut)helpShape).getCenter().getX()));
//						dlg.getTxtCenterY().setText(Integer.toString(((Donut)helpShape).getCenter().getY()));
//						dlg.getTxtRadius().setText(Integer.toString(((Donut)helpShape).getRadius()));
//						dlg.getTxtInnerRadius().setText(Integer.toString(((Donut)helpShape).getInnerRadius()));
//						dlg.setOuterLine(((Donut)helpShape).getLineCol());
//						dlg.setOuterFill(((Donut)helpShape).getInnerCol());
//						dlg.setInnerLine(((Donut)helpShape).getInnerLine());
//						dlg.setInnerFill(((Donut)helpShape).getInnerFill());
//						dlg.setVisible(true);
//						try {
//							if(dlg.isCommited()) {
//								((Donut)helpShape).getCenter().setX(Integer.parseInt(dlg.getTxtCenterX().getText()));
//								((Donut)helpShape).getCenter().setY(Integer.parseInt(dlg.getTxtCenterY().getText()));
//								((Donut)helpShape).setRadius(Integer.parseInt(dlg.getTxtRadius().getText()));
//								((Donut)helpShape).setInnerRadius(Integer.parseInt(dlg.getTxtInnerRadius().getText()));
//								((Donut)helpShape).setLineCol(dlg.getOuterLine());
//								((Donut)helpShape).setInnerCol(dlg.getOuterFill());
//								((Donut)helpShape).setInnerLine(dlg.getInnerLine());
//								((Donut)helpShape).setInnerFill(dlg.getInnerFill());
//								list.set(index, helpShape);
//								drawing.setShapes(list);
//								drawing.setSelected(helpShape);
//								drawing.repaint();
//							}
//							
//						}catch(NumberFormatException ex) {
//							JOptionPane.showMessageDialog(new JFrame(),"Invalid data type","ERROR!", JOptionPane.WARNING_MESSAGE);
//						}catch(Exception ex) {
//							JOptionPane.showMessageDialog(new JFrame(), "Values must be positive!", "Error!", JOptionPane.WARNING_MESSAGE);
//						}
//
//					}else if (drawing.getSelected() instanceof Circle) {
//						DlgCircle dlg= new DlgCircle();
//						dlg.getTxtCenterX().setText(Integer.toString(((Circle)helpShape).getCenter().getX()));
//						dlg.getTxtCenterY().setText(Integer.toString(((Circle)helpShape).getCenter().getY()));
//						dlg.getTxtRadius().setText(Integer.toString(((Circle)helpShape).getRadius()));
//						dlg.setLineColor(((Circle)helpShape).getLineCol());
//						dlg.setInnerColor(((Circle)helpShape).getInnerCol());
//						dlg.setVisible(true);
//						try {
//							if(dlg.isCommited()) {
//								((Circle)helpShape).getCenter().setX(Integer.parseInt(dlg.getTxtCenterX().getText()));
//								((Circle)helpShape).getCenter().setY(Integer.parseInt(dlg.getTxtCenterY().getText()));
//								((Circle)helpShape).setRadius(Integer.parseInt(dlg.getTxtRadius().getText()));
//								((Circle)helpShape).setLineCol(dlg.getLineColor());
//								((Circle)helpShape).setInnerCol(dlg.getInnerColor());
//								list.set(index, helpShape);
//								drawing.setShapes(list);
//								drawing.setSelected(helpShape);
//								drawing.repaint();
//							}
//						}catch(NumberFormatException ex) {
//							JOptionPane.showMessageDialog(new JFrame(),"Invalid data type","ERROR!", JOptionPane.WARNING_MESSAGE);
//						}catch(Exception ex) {
//							JOptionPane.showMessageDialog(new JFrame(), "Values must be positive!", "Error!", JOptionPane.WARNING_MESSAGE);
//						}
//					} 
//				}
//			}
//		});
//		tglbtnModify.setVisible(false);///////////////
//		toolBar.add(tglbtnModify);
//		group.add(tglbtnModify);
//		tglbtnDelete = new JToggleButton("Delete");
//		
//		tglbtnDelete.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if(drawing.getSelected()!=null)
//				{
//					Shape forDelete = drawing.getSelected();
//					ArrayList<Shape> list=drawing.getShapes();
//					int index = list.indexOf(forDelete);
//					if(JOptionPane.showConfirmDialog(ContentPane, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
//					{
//						list.remove(index);
//						drawing.setSelected(null);
//						drawing.setShapes(list);
//					}
//					drawing.repaint();
//					
//				}
//				else
//				{
//					JOptionPane.showMessageDialog(drawing, "You have to choose", "Error", JOptionPane.ERROR_MESSAGE);
//				}
//			}
//		});
//		tglbtnDelete.setVisible(false);////////////
//		toolBar.add(tglbtnDelete);
//		group.add(tglbtnDelete);
//		
//		
//		
//		
//		tglbtnUndo.addMouseListener(new MouseAdapter() {/////k
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				PnlDrawing p = new PnlDrawing();
//				p.undo();
//				
//				
//			}
//		});
//		tglbtnUndo.setVisible(false);////////////k
//		toolBar.add(tglbtnUndo);
//		group.add(tglbtnUndo);
//		
//		
//		
//		tglbtnRedo.addMouseListener(new MouseAdapter() {/////k
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				PnlDrawing p = new PnlDrawing();
//				p.redo();
//				
//			}
//		});
//		tglbtnRedo.setVisible(false);////////////k
//		toolBar.add(tglbtnRedo);
//		group.add(tglbtnRedo);
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		drawing.setSize(new Dimension(20, 40));
//		drawing.setPreferredSize(new Dimension(200, 400));
//		ContentPane.add(drawing);
//		GroupLayout gl_contentPane = new GroupLayout(ContentPane);
//		gl_contentPane.setHorizontalGroup(
//			gl_contentPane.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_contentPane.createSequentialGroup()
//					.addContainerGap()
//					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
//						.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//						.addComponent(drawing, GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE))
//					.addContainerGap())
//		);
//		gl_contentPane.setVerticalGroup(
//			gl_contentPane.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_contentPane.createSequentialGroup()
//					.addContainerGap()
//					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
//					.addPreferredGap(ComponentPlacement.UNRELATED)
//					.addComponent(drawing, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
//					.addContainerGap())
//		);
//		ContentPane.setLayout(gl_contentPane);
//		
//
//	}
//	
//	// observer actions
//	public void showBtns() {
//		this.tglbtnDelete.setVisible(true);
//		this.tglbtnModify.setVisible(true);
//		
//	}
//	
//	// observer actions
//	public void hideBtns() {
//		this.tglbtnDelete.setVisible(false);
//		this.tglbtnModify.setVisible(false);
//		
//	}
//	
//	
//	public void hideUndo() ///k
//	{
//		this.tglbtnUndo.setVisible(false);
//		
//	}
//	
//	public void showUndo()//kuca
//	{
//		this.tglbtnUndo.setVisible(true);
//		
//	}
//	
//	public void hideRedo() ///k
//	{
//		this.tglbtnRedo.setVisible(false);
//	}
//	
//	public void showRedo() 
//	{
//		this.tglbtnRedo.setVisible(true);
//	}
//	
//	
//	
//	public JToggleButton getTglbtnPoint() {
//		return tglbtnPoint;
//	}
//
//	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
//		this.tglbtnPoint = tglbtnPoint;
//	}
//
//	public JToggleButton getTglbtnLine() {
//		return tglbtnLine;
//	}
//
//	public void setTglbtnLine(JToggleButton tglbtnLine) {
//		this.tglbtnLine = tglbtnLine;
//	}
//
//	public JToggleButton getTglbtnRectangle() {
//		return tglbtnRectangle;
//	}
//
//	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
//		this.tglbtnRectangle = tglbtnRectangle;
//	}
//
//	public JToggleButton getTglbtnCircle() {
//		return tglbtnCircle;
//	}
//
//	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
//		this.tglbtnCircle = tglbtnCircle;
//	}
//
//	public JToggleButton getTglbtnDonut() {
//		return tglbtnDonut;
//	}
//
//	public void setTglbtnDonut(JToggleButton tglbtnDonut) {
//		this.tglbtnDonut = tglbtnDonut;
//	}
//
//	public JToggleButton getTglbtnSelect() {
//		return tglbtnSelect;
//	}
//
//	public void setTglbtnSelect(JToggleButton tglbtnSelect) {
//		this.tglbtnSelect = tglbtnSelect;
//	}
//
//	public JToggleButton getTglbtnModify() {
//		return tglbtnModify;
//	}
//
//	public void setTglbtnModify(JToggleButton tglbtnModify) {
//		this.tglbtnModify = tglbtnModify;
//	}
//
//	public JToggleButton getTglbtnDelete() {
//		return tglbtnDelete;
//	}
//
//	public void setTglbtnDelete(JToggleButton tglbtnDelete) {
//		this.tglbtnDelete = tglbtnDelete;
//	}
//
//	
//	public JToggleButton getTglbtnUndo() {/////k
//		return tglbtnUndo;
//	}
//
//	public void setTglbtnUndo(JToggleButton tglbtnUndo) { ////k
//		this.tglbtnUndo = tglbtnUndo;
//	}
//
//	public JToggleButton getTglbtnRedo() ///k
//	{
//		return tglbtnRedo;
//	}
//	
//	public void setTglbtnRedo() /////k
//	{
//		this.tglbtnRedo = tglbtnRedo;
//	}
//	
//	
//	
//	
//	
//
//	
//}
