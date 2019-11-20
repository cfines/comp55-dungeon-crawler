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

public class mapBase_R4 extends GraphicsPane{
	private MainApplication program;
	private GImage enemy1, enemy2, enemy3, hole1, rock1, E6, E7, background,userRep;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();

	public mapBase_R4(MainApplication app) {
		this.program = app;
		Enemy ienemy1 = new Enemy(2,2,2,2,575,216, ElementType.FIRE, enemyType.FIRESkull);
		Enemy ienemy2 = new Enemy(2,2,2,2,575,434, ElementType.WATER, enemyType.WATERBat);
		Enemy ienemy3 = new Enemy(2,2,2,2,500,420, ElementType.EARTH, enemyType.EARTHSkull);
		Interactions ihole1 = new Interactions(interactionType.obstacle_hole,900,100); 
		Interactions irock1 = new Interactions(interactionType.obstacle_rock,230,490);
		Interactions iE6 = new Interactions(interactionType.entry_door_WEST,27,300);
		Interactions iE7 = new Interactions(interactionType.entry_door_SOUTH,575,505);
		enemy1 = ienemy1.getImage();
		enemy2 = ienemy2.getImage();
		enemy3 = ienemy3.getImage();
		hole1 = ihole1.getImage();
		rock1 = irock1.getImage();
		E6 = iE6.getImage();
		E7 = iE7.getImage();
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userRep.setSize(75, 75);
		
		background.setSize(1125, 550);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		listOfEnemies.add(ienemy3);
		listOfEnemies.add(ienemy2);
		listOfEnemies.add(ienemy1);
		
		listOfInter.add(iE7);
		listOfInter.add(iE6);
		listOfInter.add(ihole1);
		listOfInter.add(irock1);
		
		elements.add(background);
		elements.add(enemy1);
		elements.add(enemy2);
		elements.add(enemy3);
		elements.add(hole1);
		elements.add(rock1);
		elements.add(E6);
		elements.add(E7);
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
		if (obj == E6) {
			program.switchToR3();
			userRep.setLocation(1010,300);
		}
		else if(obj == E7) {
			program.switchToR5();
			userRep.setLocation(575,435);
		}
	}
}
