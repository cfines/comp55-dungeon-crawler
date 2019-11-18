package RoomPanes;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import starter.GraphicsPane;
import starter.MainApplication;

public class mapBase_R4 extends GraphicsPane{
	private MainApplication program;
	private GImage enemy1, enemy2, enemy3, hole1, rock1, E6, E7, background;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<GRect> space = new ArrayList<GRect>();
	private GRect voidSpace;

	public mapBase_R4(MainApplication app) {
		this.program = app;
		enemy1 = new GImage("FIRESkull.png", 575,216);
		enemy2 = new GImage("WATERBat.gif",575,434);
		enemy3 = new GImage("EARTHSkull.png",863,434);
		hole1 = new GImage("obstacle_hole.png",900,100);
		rock1 = new GImage("obstacle_rock.png",230,490);
		E6 = new GImage("entry_door_WEST.png",60,300);
		E7 = new GImage("entry_door_SOUTH.png",575,490);
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		
		enemy1.setSize(75,75);
		enemy2.setSize(75,75);
		enemy3.setSize(75,75);
		hole1.setSize(75, 75);
		rock1.setSize(75, 75);
		E6.setSize(75,75);
		E7.setSize(75, 75);
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
		elements.add(hole1);
		elements.add(rock1);
		elements.add(E6);
		elements.add(E7);
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
		if (obj == E6) {
			program.switchToR3();
		}
		else if(obj == E7) {
			program.switchToR5();
		}
	}
}
