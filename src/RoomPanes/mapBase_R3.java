package RoomPanes;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import starter.GraphicsPane;
import starter.MainApplication;

public class mapBase_R3 extends GraphicsPane{
	private MainApplication program;
	private GImage enemy1, enemy2, E4, E5, rock1, hole1, background,userRep;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<GRect> space = new ArrayList<GRect>();
	private GRect voidSpace;
	
	public mapBase_R3(MainApplication app) {
		this.program = app;
		enemy1 = new GImage("EARTHSkull.png", 800,70);
		enemy2 = new GImage("WATERBat.gif",575,487);
		E4 = new GImage("entry_door_WEST.png",27,300);
		E5 = new GImage("entry_door_EAST.png",1050,300);
		rock1 = new GImage("obstacle_rock.png",575,325);
		hole1 = new GImage("obstacle_hole.png",230,163);
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userRep.setSize(75, 75);
		enemy1.setSize(75, 75);
		enemy2.setSize(75,75);
		E4.setSize(75, 75);
		E5.setSize(75, 75);
		rock1.setSize(75, 75);
		hole1.setSize(75, 75);
		background.setSize(1125, 550);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		space.add(voidSpace);
		elements.add(background);
		elements.add(enemy1);
		elements.add(enemy2);
		elements.add(E4);
		elements.add(E5);
		elements.add(rock1);
		elements.add(hole1);
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
		if (obj == E4) {
			program.switchToR2();
		}
		else if(obj == E5) {
			program.switchToR4();
		}
	}

}
