package mvc;

import java.awt.BorderLayout;    
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import observer.Transfer;



public class DrawingFrame extends JFrame implements Observer {

	private DrawingController controller;
	private DrawingView view=new DrawingView();
	private DefaultListModel<String> dlm=new DefaultListModel<>();
   
	private JPanel contentPane;
	private JToggleButton tglbtnPoint;
	private JToggleButton tglbtnLine;
	private JToggleButton tglbtnRectangle;
	private JToggleButton tglbtnCircle;
	private JToggleButton tglbtnDonut;
	private JToggleButton tglbtnSelection;
	private JButton btnModify;
	private JButton btnDelete;
    private JToggleButton tglbtnHexagon;
    private JButton btnUndo;
    private JButton btnRedo;
    private JToolBar toolBar_1;
    private JButton btnTofront;
	private JButton btnToback;
    private JButton btnBringToBack;
    private JButton btnBringToFront;
    private JScrollPane scrollPane;
    private JButton btnExportToLog;
    private JButton btnImportFromLog;
    private JButton btnExportToDrawFile;
    private JButton btnImportFromDraw;
    private  JButton btnExecuteLog;
    private JButton btnInnerColor;
    private JButton btnOuterColor;
    private JList logList;
    
    public DrawingFrame() {
    	setTitle("Dizajnerski");
    	contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.RED);
		
		ButtonGroup buttonGroup=new ButtonGroup();
		
		JToolBar toolBar = new JToolBar();
	

		contentPane.add(view, BorderLayout.CENTER);
		

		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		toolBar_1 = new JToolBar(JToolBar.VERTICAL);
		getContentPane().add(toolBar_1, BorderLayout.WEST);
		
		
		btnUndo = new JButton("Undo");
		btnUndo.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnUndo.setEnabled(false);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnUndo.isEnabled())
					controller.undo();
			}
		});
	
		
		toolBar_1.add(btnUndo);
		buttonGroup.add(btnUndo);
		
		
		
		
		btnRedo = new JButton("Redo");
		btnRedo.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnRedo.setEnabled(false);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnRedo.isEnabled())
					controller.redo();
			}
		});
	      toolBar_1.add(btnRedo);
	    ///dodato sad   buttonGroup.add(btnRedo);
		
		btnToback = new JButton("To Back");
		btnToback.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnToback.setEnabled(false);
		btnToback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnToback.isEnabled())
					controller.toBack();
			}
		});
		
		toolBar_1.add(btnToback);
		
		btnTofront = new JButton("To Front");
		btnTofront.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnTofront.setEnabled(false);
		btnTofront.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnTofront.isEnabled())
					controller.toFront();
			}
		});
		
		toolBar_1.add(btnTofront);
		
		btnBringToFront = new JButton("Bring To Front");
		btnBringToFront.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnBringToFront.setEnabled(false);
		btnBringToFront.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnBringToFront.isEnabled())
					controller.bringToFront();
			}
		});
	
		toolBar_1.add(btnBringToFront);
		
		btnBringToBack = new JButton("Bring To Back");
		btnBringToBack.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnBringToBack.setEnabled(false);
		btnBringToBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnBringToBack.isEnabled())
				controller.bringToBack();
			}
		});
	
		toolBar_1.add(btnBringToBack);
		
		btnInnerColor = new JButton("Inner Color");
		btnInnerColor.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnInnerColor.setBackground(Color.WHITE);
		btnInnerColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.addInnerColor();
			}
		});
		
		toolBar_1.add(btnInnerColor);
		
		btnOuterColor = new JButton("Outer Color");
		btnOuterColor.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnOuterColor.setBackground(Color.BLACK);
		btnOuterColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.addOuterColor();
			}
		});
		toolBar_1.add(btnOuterColor);
//
//		
		
		
		
		tglbtnPoint=new JToggleButton("Point");
		tglbtnPoint.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		toolBar.add(tglbtnPoint);
		buttonGroup.add(tglbtnPoint);
		
		tglbtnLine = new JToggleButton("Line");
		tglbtnLine.setFont(new Font("Tahoma", Font.BOLD, 10));

		toolBar.add(tglbtnLine);
		buttonGroup.add(tglbtnLine);
	

		tglbtnRectangle = new JToggleButton("Rectangle");
		tglbtnRectangle.setFont(new Font("Tahoma", Font.BOLD, 10));
		

	    toolBar.add(tglbtnRectangle);
		buttonGroup.add(tglbtnRectangle);
	

		tglbtnCircle = new JToggleButton("Circle");
		tglbtnCircle.setFont(new Font("Tahoma", Font.BOLD, 10));
		toolBar.add(tglbtnCircle);
		buttonGroup.add(tglbtnCircle);
		
		tglbtnDonut = new JToggleButton("Donut");
		tglbtnDonut.setFont(new Font("Tahoma", Font.BOLD, 10));
		toolBar.add(tglbtnDonut);
		buttonGroup.add(tglbtnDonut);
		
		tglbtnHexagon = new JToggleButton("Hexagon");
		tglbtnHexagon.setFont(new Font("Tahoma", Font.BOLD, 10));
		toolBar.add(tglbtnHexagon);
		buttonGroup.add(tglbtnHexagon);
		
		tglbtnSelection = new JToggleButton("Selection");
		tglbtnSelection.setFont(new Font("Tahoma", Font.BOLD, 10));
		
	
		toolBar.add(tglbtnSelection);
		buttonGroup.add(tglbtnSelection);
		
		btnModify = new JButton("Modify");
		btnModify.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnModify.setEnabled(false);
		btnModify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnModify.isEnabled()) 
					controller.mouseClickedModify(e);
				
			};
		});
	
	    
		toolBar.add(btnModify);
		buttonGroup.add(btnModify);
		
		btnDelete = new JButton("Delete");	
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnDelete.setEnabled(false);
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnDelete.isEnabled())
					controller.mouseClickedDelete(e);	
			};
		});
		toolBar.add(btnDelete);
		buttonGroup.add(btnDelete);
		
		
		btnExportToLog = new JButton("Export To Log");
		btnExportToLog.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnExportToLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.exportToLog();
			}
		});
		
	toolBar_1.add(btnExportToLog);
		
		btnImportFromLog = new JButton("Import From Log");
		btnImportFromLog.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnImportFromLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.importFromLog();
			}
		});
		toolBar_1.add(btnImportFromLog);
		
		btnExportToDrawFile = new JButton("Export To Draw File");
		btnExportToDrawFile.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnExportToDrawFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.exportToDraw();
			}
		});
		toolBar_1.add(btnExportToDrawFile);
		
		btnImportFromDraw = new JButton("Import From Draw ");
		btnImportFromDraw.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnImportFromDraw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.importFromDraw();
			}
		});
		toolBar_1.add(btnImportFromDraw);
	
		
		btnExecuteLog = new JButton("Execute log");
		btnExecuteLog.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnExecuteLog.setEnabled(false);
		btnExecuteLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnExecuteLog.isEnabled()) {
					try {
						controller.executeLog();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		toolBar_1.add(btnExecuteLog);
		
		
		
		
		
//		additionalActionsView.getBtnParseLog().addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent e) {
//				mfController.parseNextLogLine();
//			}
//		});
//		
		btnInnerColor = new JButton("Inner Color");
		btnInnerColor.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnInnerColor.setBackground(Color.WHITE);
		btnInnerColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.addInnerColor();
			}
		});

		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.EAST);//bijela povrsina
		logList = new JList();

		logList.setModel(dlm);
		scrollPane.setViewportView(logList);
		
		
		scrollPane.setBounds(586, 452, 784, 461);
		
		
    }

    @Override
    public void update(Observable o, Object arg) {
    	Transfer transfer = (Transfer) arg;
    	btnModify.setEnabled(transfer.isEnableModifyButton());
    	btnDelete.setEnabled(transfer.isEnableDeleteButton());
    	btnBringToBack.setEnabled(transfer.isEnableBringToBackButton());
    	btnBringToFront.setEnabled(transfer.isEnableBringToFrontButton());
    	btnToback.setEnabled(transfer.isEnableToBackButton());
    	btnTofront.setEnabled(transfer.isEnableToFrontButton());
    	btnExecuteLog.setEnabled(transfer.isEnableExecuteLogButton());
    }
    
    public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller2) {
		controller=controller2;
		
	}

	public boolean getTglbtnPoint() {
		return tglbtnPoint.isSelected();
	}

	public boolean getTglbtnLine() {
		return tglbtnLine.isSelected();
	}

	public boolean getTglbtnRectangle() {
		return tglbtnRectangle.isSelected();
	}

	public boolean getTglbtnCircle() {
		return tglbtnCircle.isSelected();
	}

	public boolean getTglbtnDonut() {
		return tglbtnDonut.isSelected();
	}

	public boolean getTglbtnSelection() {
		return tglbtnSelection.isSelected();
	}

	public boolean getbtnModify() {
		return btnModify.isSelected();
	}

	public boolean getbtnDelete() {
		return btnDelete.isSelected();
	}
	
	

	public boolean getTglbtnHexagon() {
		return tglbtnHexagon.isSelected();
	}
	
	public boolean getbtnUndo() {
		return btnUndo.isSelected();
	}
	public boolean getbtnTofront() {
			return btnTofront.isSelected();
	}

	public boolean getbtnToback() {
			return btnToback.isSelected();
	}

	public boolean getbtnBringToBack() {
			return btnBringToBack.isSelected();
	}

	public boolean getbtnBringToFront() {
			return btnBringToFront.isSelected();
	}
	
	public boolean getbtnRedo() {
		return btnRedo.isSelected();
	}
	
	
	public JButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(JButton btnModify) {
		this.btnModify = btnModify;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
	}

	public JButton getBtnTofront() {
		return btnTofront;
	}

	public void setBtnTofront(JButton btnTofront) {
		this.btnTofront = btnTofront;
	}

	public JButton getBtnToback() {
		return btnToback;
	}

	public void setBtnToback(JButton btnToback) {
		this.btnToback = btnToback;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public void setBtnBringToBack(JButton btnBringToBack) {
		this.btnBringToBack = btnBringToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public void setBtnBringToFront(JButton btnBringToFront) {
		this.btnBringToFront = btnBringToFront;
	}

	public JButton getBtnExportToLog() {
		return btnExportToLog;
	}

	public void setBtnExportToLog(JButton btnExportToLog) {
		this.btnExportToLog = btnExportToLog;
	}

	public JButton getBtnImportFromLog() {
		return btnImportFromLog;
	}

	public void setBtnImportFromLog(JButton btnImportFromLog) {
		this.btnImportFromLog = btnImportFromLog;
	}

	public JButton getBtnExportToDrawFile() {
		return btnExportToDrawFile;
	}

	public void setBtnExportToDrawFile(JButton btnExportToDrawFile) {
		this.btnExportToDrawFile = btnExportToDrawFile;
	}

	public JButton getBtnImportFromDraw() {
		return btnImportFromDraw;
	}

	public void setBtnImportFromDraw(JButton btnImportFromDraw) {
		this.btnImportFromDraw = btnImportFromDraw;
	}

	public JButton getBtnExecuteLog() {
		return btnExecuteLog;
	}

	public void setBtnExecuteLog(JButton btnExecuteLog) {
		this.btnExecuteLog = btnExecuteLog;
	}

	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}

	public JButton getBtnOuterColor() {
		return btnOuterColor;
	}

	public void setBtnOuterColor(JButton btnOuterColor) {
		this.btnOuterColor = btnOuterColor;
	}

	public void setTglbtnSelection(JToggleButton tglbtnSelection) {
		this.tglbtnSelection = tglbtnSelection;
	}

	public DefaultListModel<String> getDlm() {
		return dlm;
	}

	public void setDlm(DefaultListModel<String> dlm) {
		this.dlm = dlm;
		logList.setModel(dlm);
	}


}
