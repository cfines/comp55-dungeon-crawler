package RoomPanes;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.TODO;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import starter.GraphicsPane;
import starter.MainApplication;

public class mapBase_R5 extends GraphicsPane{
	private MainApplication program;
	private GImage enemy1, hole1, E8, E9, E10, background,userRep;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	
	public mapBase_R5(MainApplication app) {
		this.program = app;
		enemy1 = new GImage("FIRESkull.png", 575,325);
		hole1 = new GImage("obstacle_hole.png",230,325);
		E8 = new GImage("entry_door_NORTH.png",575,28);
		E9 = new GImage("entry_door_SOUTH.png",575,505);
		E10 = new GImage("entry_door_EAST.png",1050,300);
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userRep.setSize(75, 75);
		enemy1.setSize(75,75);
		hole1.setSize(75,75);
		E8.setSize(75,75);
		E9.setSize(75,75);
		E10.setSize(75,75);
		background.setSize(1125, 550);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		elements.add(background);
		elements.add(enemy1);
		elements.add(hole1);
		elements.add(E8);
		elements.add(E9);
		elements.add(E10);
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
		if (obj == E8) {
			program.switchToR4();
			userRep.setLocation(575,48);
		}
		else if(obj == E9) {
			program.switchToR6();
			userRep.setLocation(575,435);
		}
		else if(obj == E10) {
			program.switchToR7();
			userRep.setLocation(90,300);
		}
	}
}
