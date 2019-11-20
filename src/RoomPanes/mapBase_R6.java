package RoomPanes;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import starter.Enemy;
import starter.GraphicsPane;
import starter.Interactions;
import starter.MainApplication;
import starter.interactionType;

public class mapBase_R6 extends GraphicsPane{
	private MainApplication program;
	private GImage rock1, E11, key1, background,userRep;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();

	public mapBase_R6(MainApplication app) {
		this.program = app;
		Interactions irock1 = new Interactions(interactionType.obstacle_rock,900,150);
		Interactions iE11 = new Interactions(interactionType.entry_door_NORTH,575,28);
		Interactions ikey1 = new Interactions(interactionType.item_gif_key,575,300);
		rock1 = irock1.getImage();
		E11 = iE11.getImage();
		key1 = ikey1.getImage();
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		
		userRep = new GImage("Rogue_(Sample User).gif");
		userRep.setSize(75, 75);
		background.setSize(1125, 550);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		listOfInter.add(ikey1);
		listOfInter.add(iE11);
		listOfInter.add(irock1);
		
		elements.add(background);
		elements.add(rock1);
		elements.add(E11);
		elements.add(key1);
		elements.add(userRep);
	}

	@Override
	public void showContents() {
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
	}

	@Override
	public void hideContents() {
		program.remove(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.remove(elements.get(i));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == E11) {
			program.switchToR5();
			userRep.setLocation(575,48);
		}
	}
}
