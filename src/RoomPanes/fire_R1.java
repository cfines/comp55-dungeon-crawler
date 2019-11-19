package RoomPanes;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import starter.GraphicsPane;
import starter.MainApplication;

public class fire_R1 extends GraphicsPane{
	private MainApplication program;
	private GImage E1, background, userRep;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<GImage> you = new ArrayList<GImage>();
	private ArrayList<GRect> space = new ArrayList<GRect>();
	private GRect voidSpace;

	public fire_R1(MainApplication app) {
		this.program = app;
		E1 = new GImage("entry_door_SOUTH.png",575,500);
		background = new GImage("Fire_Floor (Regular Floor).png", 15,30);
		E1.setSize(75,75);
		background.setSize(1125, 550);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		userRep = new GImage("Rogue_(Sample User).gif");
		userRep.setSize(75, 75);
		space.add(voidSpace);
		elements.add(background);
		elements.add(E1);
		you.add(userRep);
	}

	@Override
	public void showContents() {
		program.add(space.get(0));
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		program.add(you.get(0));
	}

	@Override
	public void hideContents() {
		program.remove(space.get(0));
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.remove(elements.get(i));
		}
		program.remove(you.get(0));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
//		if (obj == ENext) {
//			program.switchToMenu();
//		}
//		else if(obj == E16) {
//			program.switchToR8();
//		}
	}
}
