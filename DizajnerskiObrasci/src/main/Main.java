package main;

import javax.swing.JFrame;

import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class Main {
	
	public static void main(String[] args) {
			
		DrawingModel model=new DrawingModel();
		DrawingFrame frame=new DrawingFrame();
		frame.getView().setModel(model);
	
		
		DrawingController controller=new DrawingController(model,frame);
		frame.setController(controller);
		
		controller.addObserver(frame);
	
		frame.setBounds(70, 70, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	//	model.addPropertyChangeListener(controller);
	}

}