package RoomPanes;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import starter.Console;
import starter.ElementType;
import starter.Enemy;
import starter.GraphicsPane;
import starter.Interactions;
import starter.MainApplication;
import starter.enemyType;
import starter.interactionType;

public class mapBase_R2 extends GraphicsPane{
	private Console game;
	private MainApplication program;
	private GImage rock1, hole2, hole1, E2, E3, background, enemy1, enemy2,userRep;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<GRect> space = new ArrayList<GRect>();
	private GRect voidSpace;
	private ArrayList<GImage> you = new ArrayList<GImage>();
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	
	public mapBase_R2(MainApplication app) {
		this.program = app;
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		Interactions irock1 = new Interactions(interactionType.obstacle_rock, 100,9);
		Interactions ihole1 = new Interactions(interactionType.obstacle_hole, 500,91);
		Interactions ihole2 = new Interactions(interactionType.obstacle_hole, 575,400);
		Interactions iE2 = new Interactions(interactionType.entry_door_WEST,27,300);
		Interactions iE3 = new Interactions(interactionType.entry_door_EAST,1050,300);
		Enemy ienemy1 = new Enemy(2,2,2,2,350,76, ElementType.EARTH, enemyType.EARTHSkull);
		Enemy ienemy2 = new Enemy(2,2,2,2,367,504, ElementType.WATER, enemyType.WATERSkull);
		rock1 = irock1.getImage();
		hole2 = ihole2.getImage();
		hole1 = ihole1.getImage();
		E2 = iE2.getImage();
		E3 = iE3.getImage();
		
		listOfInter.add(irock1);
		listOfInter.add(ihole1);
		listOfInter.add(ihole2);
		listOfInter.add(iE2);
		listOfInter.add(iE3);
		
		enemy1 = ienemy1.getImage();
		enemy2 = ienemy2.getImage();
		
		listOfEnemies.add(ienemy1);
		listOfEnemies.add(ienemy2);
		
		userRep = new GImage("Rogue_(Sample User).gif");
		userRep.setSize(75, 75);

		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		background.setSize(1125, 550);
		
		space.add(voidSpace);
		elements.add(background);
		elements.add(rock1);
		elements.add(hole1);
		elements.add(hole2);
		elements.add(E2);
		elements.add(E3);
		elements.add(enemy1);
		elements.add(enemy2);
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
		if (obj == E2) {
			program.switchToSome();
			userRep.setLocation(1010,300);
		}
		else if(obj == E3) {
			program.switchToR3();
			userRep.setLocation(90,300);
		}
	}

}
