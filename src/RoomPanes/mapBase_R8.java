package RoomPanes;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import starter.GraphicsPane;
import starter.MainApplication;

public class mapBase_R8 extends GraphicsPane{
	private MainApplication program;
	private GImage rock1, rock2, hole1, E14, E15, background,userRep;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<GRect> space = new ArrayList<GRect>();
	private GRect voidSpace;
	
	public mapBase_R8(MainApplication app) {
		this.program = app;
		rock1 = new GImage("obstacle_rock.png",150,425);
		rock2 = new GImage("obstacle_rock.png",575,325);
		hole1 = new GImage("obstacle_hole.png",901,325);
		E14 = new GImage("entry_door_WEST.png",27,300);
		E15 = new GImage("entry_bossDoor.png",575,28);
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userRep.setSize(75, 75);
		rock1.setSize(75,75);
		rock2.setSize(75,75);
		hole1.setSize(75, 75);
		E14.setSize(75,75);
		E15.setSize(75, 75);
		background.setSize(1125, 550);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		space.add(voidSpace);
		elements.add(background);
		elements.add(rock1);
		elements.add(rock2);
		elements.add(hole1);
		elements.add(E14);
		elements.add(E15);
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
		if (obj == E14) {
			program.switchToR7();
			userRep.setLocation(1010,300);
		}
		else if(obj == E15) {
			program.switchToR9();
			userRep.setLocation(575,48);
		}
	}
}
