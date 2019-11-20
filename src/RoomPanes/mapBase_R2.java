package RoomPanes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import starter.Console;
import starter.ElementType;
import starter.Enemy;
import starter.GraphicsPane;
import starter.Interactions;
import starter.MainApplication;
import starter.User;
import starter.enemyType;
import starter.interactionType;

public class mapBase_R2 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage rock1, hole2, hole1, E2, E3, background, enemy1, enemy2,userRep;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private int degree;
	private User user;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	
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
		
		user = new User(5, 5, 1000, 1, 300, 300);
		userRep = new GImage("Rogue_(Sample User).gif");
		userRep.setSize(75, 75);

		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		background.setSize(1125, 550);
		
		elements.add(background);
		elements.add(rock1);
		elements.add(hole1);
		elements.add(hole2);
		elements.add(E2);
		elements.add(E3);
		elements.add(enemy1);
		elements.add(enemy2);
		elements.add(userRep);
		
		Timer t = new Timer(50, this);
		t.start();
	}
	
	private void nextRoom() {
		double userX = userRep.getX() + 75;
		double userY = userRep.getY() + 75;
		if(userX >= E2.getX() && userY >= E2.getY() && userX <= E2.getX()&& userY <= E2.getY()) {
			program.switchToSome();
		}
	}
	
	private void userUP() {
		user.setDY(-user.getMoveSpeedStat());
		//nextRoom();
	}
	private void userDOWN() {
		user.setDY(user.getMoveSpeedStat());
		//nextRoom();
	}
	private void userLEFT() {
		user.setDX(-user.getMoveSpeedStat());
		//nextRoom();
	}
	private void userRIGHT() {
		user.setDX(user.getMoveSpeedStat());
		//nextRoom();
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
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			user.setDY(0);
			break;
		case KeyEvent.VK_S:
			user.setDY(0);
			break;
		case KeyEvent.VK_A:
			user.setDX(0);
			break;
		case KeyEvent.VK_D:
			user.setDX(0);
			break;
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
		if (obj == E2) {
			program.switchToSome();
			userRep.setLocation(1010,300);
		}
		else if(obj == E3) {
			program.switchToR3();
			userRep.setLocation(90,300);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		enemy1.movePolar(4, degree);
		degree+=50;
		degree%=360;
		enemyMovement();
		user.tick();
		userRep.setLocation(user.getX(), user.getY());
	}
	
	public void enemyMovement() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double enemyX = enemy1.getX();
		double enemyY = enemy1.getY();
		double distX = enemyX - userX;
		double distY = enemyY - userY;
		double moveX = (distX * 2) / 100;
		double moveY = (distY * 2) / 100;
		for (Enemy enem : listOfEnemies)
			enem.getImage().move(-moveX, -moveY);
	}

}
