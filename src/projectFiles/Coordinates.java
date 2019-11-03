package projectFiles;

//only to be called by Map

public class Coordinates {
	public int x;
	public int y;
	
	public Coordinates(int horizontal, int vertical)
	{
		x = horizontal;
		y = vertical;
	}
	
	public Coordinates() {}
	
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
