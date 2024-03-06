package mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import drawing.Point;
import drawing.Shape;



public class DrawingView extends JPanel{
	private DrawingModel model = new DrawingModel();///mora ovako za slucaj da niko ne pozove setModel(zbog DrawingFrame)
	
	public DrawingView() {
		setBackground(Color.WHITE);
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	@Override
	public void paint(Graphics g) {//nigdje je ne pozivamo vec je java virtuelna masina non stop izvrsava
		super.paint(g);//da bi crtanjem upravljao JPanel
		Iterator<Shape> it = model.getShapes().iterator();
		while(it.hasNext()) {
			it.next().draw(g);
		}
	}
}
