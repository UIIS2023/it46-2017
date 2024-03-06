package stack;

public abstract class Shape {

	private boolean selected;

	public Shape() {
		
	}
	public Shape(boolean selected) {
		this.selected=selected;
	}
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
