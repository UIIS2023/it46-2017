package stack;

public class Point extends Shape{
	private int x,y;

	public Point() {
		
	}
	public Point(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public Point(int x, int y, boolean selected) {
		this(x,y);
		setSelected(selected);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	public String toString() {
		return "(" + x + "," + y + ")";
	}

}
