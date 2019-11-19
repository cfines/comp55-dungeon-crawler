package RoomPanes;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import starter.GraphicsPane;
import starter.MainApplication;

public class mapBase_R6 extends GraphicsPane{
	private MainApplication program;
	private GImage rock1, E11, key1, background,userRep;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<GRect> space = new ArrayList<GRect>();
	private GRect voidSpace;

	public mapBase_R6(MainApplication app) {
		this.program = app;
		rock1 = new GImage("obstacle_rock.png",900,150);
		E11 = new GImage("entry_door_NORTH.png",575,28);
		key1 = new GImage("item_gif_key.gif", 575,300);
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userRep.setSize(75, 75);
		rock1.setSize(75,75);
		key1.setSize(75, 75);
		E11.setSize(75,75);
		background.setSize(1125, 550);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		space.add(voidSpace);
		elements.add(background);
		elements.add(rock1);
		elements.add(E11);
		elements.add(key1);
	}

	@Override
	public void showContents() {
		program.add(space.get(0));
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
	}

	@Override
	public void hideContents() {
		program.remove(space.get(0));
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.remove(elements.get(i));
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == E11) {
			program.switchToR5();
		}
	}
}
