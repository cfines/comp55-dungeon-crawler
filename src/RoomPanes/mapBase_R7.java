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
import starter.enemyType;
import starter.interactionType;

public class mapBase_R7 extends GraphicsPane{
	private MainApplication program;
	private GImage rock1, E12, E13, enemy1, enemy2, enemy3, background,userRep;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();

	public mapBase_R7(MainApplication app) {
		this.program = app;
		Interactions irock1 = new Interactions(interactionType.obstacle_rock,300,200);
		Interactions iE12 = new Interactions(interactionType.entry_door_WEST,27,300);
		Interactions iE13 = new Interactions(interactionType.entry_door_EAST,1050,300);
		Enemy ienemy1 = new Enemy(2,2,2,2,575,100,ElementType.EARTH, enemyType.EARTHSkull);
		Enemy ienemy2 = new Enemy(2,2,2,2,575,250, ElementType.EARTH, enemyType.EARTHBat);
		Enemy ienemy3 = new Enemy(2,2,2,2,575,420, ElementType.EARTH, enemyType.EARTHSkull);
		rock1 = irock1.getImage();
		E12 = iE12.getImage();
		E13 = iE13.getImage();
		enemy1 = ienemy1.getImage();
		enemy2 = ienemy2.getImage();
		enemy3 = ienemy3.getImage();
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
		
		listOfInter.add(iE13);
		listOfInter.add(iE12);
		listOfInter.add(irock1);
		
		elements.add(background);
		elements.add(enemy1);
		elements.add(enemy2);
		elements.add(enemy3);
		elements.add(rock1);
		elements.add(E12);
		elements.add(E13);
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
		if (obj == E12) {
			program.switchToR5();
			userRep.setLocation(1010,300);
		}
		else if(obj == E13) {
			program.switchToR8();
			userRep.setLocation(90,300);
		}
	}
}
