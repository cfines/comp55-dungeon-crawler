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

public class mapBase_R8 extends GraphicsPane{
	private MainApplication program;
	private GImage rock1, rock2, hole1, E14, E15, background,userRep;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	
	public mapBase_R8(MainApplication app) {
		this.program = app;
		Interactions irock1 = new Interactions(interactionType.obstacle_rock,150,425);
		Interactions irock2 = new Interactions(interactionType.obstacle_rock,575,325);
		Interactions ihole1 = new Interactions(interactionType.obstacle_hole,901,325);
		Interactions iE14 = new Interactions(interactionType.entry_door_WEST,27,300);
		Interactions iE15 = new Interactions(interactionType.entry_bossDoor,575,28);
		rock1 = irock1.getImage();
		rock2 = irock2.getImage();
		hole1 = ihole1.getImage();
		E14 = iE14.getImage();
		E15 = iE15.getImage();
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userRep.setSize(75, 75);
		hole1.setSize(200, 200);

		background.setSize(1125, 550);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		listOfInter.add(iE15);
		listOfInter.add(iE14);
		listOfInter.add(ihole1);
		listOfInter.add(irock2);
		listOfInter.add(irock1);
		
		elements.add(background);
		elements.add(rock1);
		elements.add(rock2);
		elements.add(hole1);
		elements.add(E14);
		elements.add(E15);
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
