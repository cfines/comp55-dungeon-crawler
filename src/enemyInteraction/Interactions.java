package enemyInteraction;

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
		setImage(type);
	}

	// gets the interaction type from the interactionType enum
	public interactionType getinteractionType() {
		return inter;
	}

	public void setImage(interactionType inter) {
		if(inter == interactionType.item_gif_key || inter == interactionType.color1 || inter == interactionType.color2 || inter == interactionType.face ||
				inter == interactionType.candle1 || inter == interactionType.candle2 || inter == interactionType.candle3 || inter == interactionType.momoko ||
				inter == interactionType.monoe || inter == interactionType.poniko) {
			image = new GImage(inter + ".gif", coordX, coordY);
		}
		else {
			image = new GImage(inter + ".png", coordX, coordY);
		}
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