package RoomPanes;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import starter.ElementType;
import starter.Enemy;
import starter.GraphicsPane;
import starter.Interactions;
import starter.MainApplication;
import starter.enemyType;
import starter.interactionType;

public class mapBase_R5 extends GraphicsPane{
	private MainApplication program;
	private GImage enemy1, hole1, E8, E9, E10, background,userRep;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	
	public mapBase_R5(MainApplication app) {
		this.program = app;
		Enemy ienemy1 = new Enemy(2,2,2,2,575,325, ElementType.FIRE, enemyType.FIRESkull);
		Interactions ihole1 = new Interactions(interactionType.obstacle_hole, 230,325);
		Interactions iE8 = new Interactions(interactionType.entry_door_NORTH, 575,28);
		Interactions iE9 = new Interactions(interactionType.entry_door_SOUTH, 575,505);
		Interactions iE10 = new Interactions(interactionType.entry_door_EAST,1050,300);
		enemy1 = ienemy1.getImage();
		hole1 = ihole1.getImage();
		E8 = iE8.getImage();
		E9 = iE9.getImage();
		E10 = iE10.getImage();
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userRep.setSize(75, 75);
		
		background.setSize(1125, 550);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		listOfEnemies.add(ienemy1);
		listOfInter.add(iE10);
		listOfInter.add(iE9);
		listOfInter.add(iE8);
		listOfInter.add(ihole1);
		
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
