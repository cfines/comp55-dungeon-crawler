package starter;

import acm.graphics.GImage;

//remember, these things will be inside of room as we going to treat
//them like obstacles
public class Interactions {
	interactionType inter;
	int coordX;
	int coordY;
	GImage image;

	public Interactions(interactionType type, int coordX, int coordY)
	{
		inter = type;
		this.coordX = coordX;
		this.coordY = coordY;
	}

	// gets the interaction type from the interactionType enum
	public interactionType getinteractionType() {
		return inter;
	}

	public void setImage(interactionType inter) {
		image = new GImage(inter + ".png", coordX, coordY);
		image.setSize(75, 75);
	}
	
	public GImage getImage() {
		return image;
	}

	@Override
	public String toString() {
		return "x:" + coordX + "y:" + coordY;
	}

	//test code for getImage function
	public static void main(String[] args) {
		Interactions bruh = new Interactions(interactionType.obstacle_rock, 1, 1);
		bruh.setImage(bruh.getinteractionType());
		System.out.println(bruh.getImage().getSize());
		System.out.println(bruh.getImage());
	}
}
