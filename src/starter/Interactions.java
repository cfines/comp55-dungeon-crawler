package starter;

import acm.graphics.GImage;

//remember, these things will be inside of room as we going to treat
//them like obstacles
public class Interactions {
	interactionType inter = null;
	int roomNum = 0;
	int numInter = 0;
	GImage image = null;
	
	public Interactions(interactionType type, int roomNum, int numInter)
	{
		inter = type;
		this.roomNum = roomNum;
		this.numInter = numInter;
	}
	
	// gets the interaction type from the interactionType enum
	public interactionType getinteractionType() {
		return inter;
	}
	
	public GImage getImage(interactionType inter, int x, int y) {
			image = new GImage(inter.toString() + ".png", x, y);
			image.setSize(75, 75);
			return image;	
	}
	
	@Override
	public String toString() {
		return "room" + roomNum + "interaction" + numInter;
	}
	
	//test code for getImage function
	public static void main(String[] args) {
		Interactions bruh = new Interactions(interactionType.obstacle_rock, 1, 1);
		GImage notBruh = bruh.getImage(bruh.getinteractionType(), 20, 20);
		System.out.println(notBruh.getSize());
		System.out.println(notBruh.getImage());
	}
}
