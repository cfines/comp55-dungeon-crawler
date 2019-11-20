package RoomPanes;

import java.awt.Color;
import java.awt.event.KeyEvent;
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

public class mapBase_R4 extends GraphicsPane{
	private MainApplication program;
	private GImage enemy1, enemy2, enemy3, hole1, rock1, E6, E7, background,userRep;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private boolean atkUp,atkLeft,atkDown,atkRight;
	private Console game;
	
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
		isUserInPain();
	}
	private void userUP() {
		userRep.move(0, -5);
		isUserInPain();
	}
	private void userDOWN() {
		userRep.move(0, 5);
		isUserInPain();
	}
	private void userLEFT() {
		userRep.move(-5, 0);
		isUserInPain();
	}
	private void userRIGHT() {
		userRep.move(5, 0);
		isUserInPain();
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
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			userUP();
			break;
		case KeyEvent.VK_S:
			userDOWN();
			break;
		case KeyEvent.VK_A:
			userLEFT();
			break;
		case KeyEvent.VK_D:
			userRIGHT();
			break;
		case KeyEvent.VK_UP:
			atkUp = true;
			if(atkUp == true) 
			{
				userRep.setImage("Rogue_Attack(Up).png");
				userRep.setSize(75,75);
			}
			break;
		case KeyEvent.VK_LEFT:
			atkLeft = true;
			if(atkLeft == true) 
			{
				userRep.setImage("Rogue_Attack(Left).png");
				userRep.setSize(75,75);
			}
			break;
		case KeyEvent.VK_DOWN:
			atkDown = true;
			if(atkDown == true) 
			{
				userRep.setImage("Rogue_Attack(Down).png");
				userRep.setSize(75,75);
			}
			break;
		case KeyEvent.VK_RIGHT:
			atkRight = true;
			if(atkRight == true) 
			{
				userRep.setImage("Rogue_Attack(Right).png");
				userRep.setSize(75,75);
			}
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) 
	{
		switch (e.getKeyCode()) {
		// for stopping attack 
		case KeyEvent.VK_UP:
			atkUp = false;
			if(atkUp == false) 
			{
				userRep.setImage("Rogue_(Sample User).gif");
				userRep.setSize(75,75);
			}
			break;

		case KeyEvent.VK_LEFT:
			atkLeft = false;
			if(atkLeft == false) 
			{
				userRep.setImage("Rogue_(Sample User).gif");
				userRep.setSize(75,75);
			}
			break;

		case KeyEvent.VK_DOWN: 
			atkDown = false;
			if(atkDown == false) 
			{
				userRep.setImage("Rogue_(Sample User).gif");
				userRep.setSize(75,75);
			}
			break;

		case KeyEvent.VK_RIGHT: 
			atkRight = false;
			if(atkRight == false) 
			{
				userRep.setImage("Rogue_(Sample User).gif");
				userRep.setSize(75,75);
			}
			break;
		}
	}
	
	//currently testing for one enemy
	public void isUserInPain() 
	{
		int newHealth;
		double userX = userRep.getX() + 75;
		double userY = userRep.getY() + 75;
		if(userX >= enemy1.getX() && userY >= enemy1.getY() && userX <= enemy1.getX() + 75 && userY <= enemy1.getY() + 75) 
		{
			//newHealth = game.getUser().getUserStats().getHP_cur() - 1;
			//game.getUser().getUserStats().setHP_cur(newHealth);
			System.out.println("User takes 1 damage, ouch.");
			//TODO insert user getting hurt here
		}
		else 
		{
			System.out.println("User is not taking damage.");
		}
	}
}
