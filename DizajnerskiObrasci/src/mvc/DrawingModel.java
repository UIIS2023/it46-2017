package mvc;

import java.beans.PropertyChangeListener; 
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import command.Command;
import drawing.Shape; 



public class DrawingModel {

	private List<Shape> shapes=new ArrayList<Shape>();
		
	public void add(Shape s) {
		shapes.add(s);
	}
	
	public void remove(Shape s) {
		shapes.remove(s);		
	}

	public List<Shape> getShapes() {
		return shapes;
	}
	
	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
	}
	
	public List<Shape> getSelectedShapes() {
		List<Shape> placeholder = new ArrayList<Shape>();
	    for (Shape s : shapes) {
			if (s.isSelected()) {
				placeholder.add(s);
			}
		}

	    return placeholder;
	}

	public Shape getSelectedShape() {
    for (Shape s : shapes) {
			
			if (s != null && s.isSelected()) {
				return s;
			}
		}

		return null;
	}

	public int getIndexOfShape(Shape s) {
		
		int listSize = shapes.size() - 1;
		for (int i = 0; i <= listSize; i++) {
			if (shapes.get(i).equals(s)) {
				
				return i;
			}
		}
		return -1;
	}
	
	public Shape get(int i) {
		return shapes.get(i);
	}

}
