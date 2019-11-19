package RoomPanes;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import starter.GraphicsPane;
import starter.MainApplication;

public class mapBase_R7 extends GraphicsPane{
	private MainApplication program;
	private GImage rock1, E12, E13, enemy1, enemy2, enemy3, background,userRep;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<GRect> space = new ArrayList<GRect>();
	private GRect voidSpace;

	public mapBase_R7(MainApplication app) {
		this.program = app;
		rock1 = new GImage("obstacle_rock.png",300,200);
		E12 = new GImage("entry_door_WEST.png",60,300);
		E13 = new GImage("entry_door_EAST.png",1040,300);
		enemy1 = new GImage("EARTHSkull.png", 575,100);
		enemy2 = new GImage("EARTHBat.gif", 575,250);
		enemy3 = new GImage("EARTHSkull.png", 575,420);
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userRep.setSize(75, 75);
		enemy1.setSize(75,75);
		enemy2.setSize(75,75);
		enemy3.setSize(75,75);
		E12.setSize(75,75);
		E13.setSize(75,75);
		rock1.setSize(75,75);
		background.setSize(1125, 550);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		space.add(voidSpace);
		elements.add(background);
		elements.add(enemy1);
		elements.add(enemy2);
		elements.add(enemy3);
		elements.add(rock1);
		elements.add(E12);
		elements.add(E13);
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
		if (obj == E12) {
			program.switchToR5();
		}
		else if(obj == E13) {
			program.switchToR8();
		}
	}
}
