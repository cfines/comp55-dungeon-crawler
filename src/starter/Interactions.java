package starter;

import acm.graphics.GImage;

//remember, these things will be inside of room as we going to treat
//them like obstacles
public class Interactions {
	interactionType inter;
	int roomNum;
	int numInter;
	GImage image;

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

	public void setImage(interactionType inter, int x, int y) {
		image = new GImage(inter.toString() + ".png", x, y);
		image.setSize(75, 75);
	}

	public GImage getImage() {
		return image;
	}

	@Override
	public String toString() {
		return "room" + roomNum + "interaction" + numInter;
	}

	//test code for getImage function
	public static void main(String[] args) {
		Interactions bruh = new Interactions(interactionType.obstacle_rock, 1, 1);
		bruh.setImage(bruh.getinteractionType(), 20, 20);
		System.out.println(bruh.getImage().getSize());
		System.out.println(bruh.getImage());
	}
}
