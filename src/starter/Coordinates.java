package starter;

public class Coordinates {
	public double x;
	public double y;
	
	public Coordinates(double horizontal, double vertical)
	{
		x = horizontal;
		y = vertical;
	}
	
	public Coordinates() {}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getX() 
	{
		return x;
	}
	
	public double getY() 
	{
		return y;
	}
	//to be used as coordinates

	@Override
	public String toString() {
		return "Coordinates [x=" + x + ", y=" + y + "]";
	}
	
	
	
}
