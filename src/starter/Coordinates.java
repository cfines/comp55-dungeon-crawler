package starter;

public class Coordinates {
	public int x;
	public int y;
	
	public Coordinates(int horizontal, int vertical)
	{
		x = horizontal;
		y = vertical;
	}
	
	public Coordinates() {}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() 
	{
		return x;
	}
	
	public int getY() 
	{
		return y;
	}
	//to be used as coordinates

	@Override
	public String toString() {
		return "Coordinates [x=" + x + ", y=" + y + "]";
	}
	
	
	
}
