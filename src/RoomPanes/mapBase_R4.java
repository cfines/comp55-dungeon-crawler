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

public class mapBase_R4 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage enemy1, enemy2, enemy3, hole1, rock1, E6, E7, background,userRep;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private boolean atkUp,atkLeft,atkDown,atkRight;
	private Console game;
	private int degree;
	private User user;
	private Timer timer = new Timer(50, this);
	
	public mapBase_R4(MainApplication app) {
		this.program = app;
		user = program.getUser();
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
		userRep = new GImage("Rogue_(Sample User).gif", user.getX(), user.getY());
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
		//TODO insert timer based on attack speed
		
	}
	private void userUP() {
		user.setDY(-user.getMoveSpeedStat());
	}
	private void userDOWN() {
		user.setDY(user.getMoveSpeedStat());
	}
	private void userLEFT() {
		user.setDX(-user.getMoveSpeedStat());
	}
	private void userRIGHT() {
		user.setDX(user.getMoveSpeedStat());
	}
	private void attackUp() {
		userRep.setImage("Rogue_Attack(Up).png");
		userRep.setSize(75,75);
	}
	private void attackDown() {
		userRep.setImage("Rogue_Attack(Down).png");
		userRep.setSize(75,75);
		}
	private void attackLeft() {
		userRep.setImage("Rogue_Attack(Left).png");
		userRep.setSize(75,75);
	}
	private void attackRight() {
		userRep.setImage("Rogue_Attack(Right).png");
		userRep.setSize(75,75);
	}
	
	private void attackReset() {
		userRep.setImage("Rogue_(Sample User).gif");
		userRep.setSize(75,75);
	}

	
	@Override
	public void showContents() {
		timer.start();
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		program.drawOverlay(4, 1);
	}

	@Override
	public void hideContents() {
		timer.stop();
		program.setUser(user);
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
		case KeyEvent.VK_E:
			program.getUser().cycleWeapon();
			program.drawSword();
			break;
		case KeyEvent.VK_UP:
			atkUp = true;
			if(atkUp == true) 
			{
				attackUp();
			}
			break;
		case KeyEvent.VK_LEFT:
			atkLeft = true;
			if(atkLeft == true) 
			{
				attackLeft();
			}
			break;
		case KeyEvent.VK_DOWN:
			atkDown = true;
			if(atkDown == true) 
			{
				attackDown();
			}
			break;
		case KeyEvent.VK_RIGHT:
			atkRight = true;
			if(atkRight == true) 
			{
				attackRight();
			}
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) 
	{
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
		// for stopping attack 
		case KeyEvent.VK_UP:
			atkUp = false;
			if(atkUp == false) 
			{
				attackReset();
			}
			break;

		case KeyEvent.VK_LEFT:
			atkLeft = false;
			if(atkLeft == false) 
			{
				attackReset();
			}
			break;

		case KeyEvent.VK_DOWN: 
			atkDown = false;
			if(atkDown == false) 
			{
				attackReset();
			}
			break;

		case KeyEvent.VK_RIGHT: 
			atkRight = false;
			if(atkRight == false) 
			{
				attackReset();
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
		for(int i = 0; i < listOfEnemies.size(); i++)
			if(userX >= listOfEnemies.get(i).getCoordX() && 
				userY >= listOfEnemies.get(i).getCoordY() && 
				userX <= listOfEnemies.get(i).getCoordX() + 75 && 
				userY <= listOfEnemies.get(i).getCoordY() + 75) 
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
	@Override
	public void actionPerformed(ActionEvent e) {
		enemyMovement();
		checkCollision();
		nextRoom();
		user.tick();
		userRep.setLocation(user.getX(), user.getY());
	}
	
	public void checkCollision() {
		for(Interactions inter : listOfInter) {	
			if(intCollisionTest(inter.getImage())) {
				if(user.getX() - inter.getImage().getX() <= 0) {
					user.setX(user.getX() - user.getMoveSpeedStat());	
				}
				if(user.getX() - inter.getImage().getX() >= -75) {
					user.setX(user.getX() + user.getMoveSpeedStat());		
				}
				if(user.getY() - inter.getImage().getY() <= 0) {
					user.setY(user.getY() - user.getMoveSpeedStat());		
				}
				if(user.getY() - inter.getImage().getY() >= -75) {
					user.setY(user.getY() + user.getMoveSpeedStat());	
				}
			}
		}
	}

	public boolean intCollisionTest(GImage image) {
		return (user.getY() - image.getY() <= 60
				&& user.getY() - image.getY() >= -60
				&& user.getX() - image.getX() <= 60
				&& user.getX() - image.getX() >= -60);
	}
	
	public void enemyMovement() {
		for (Enemy enem : listOfEnemies) {
			double distX = enem.getImage().getX() - userRep.getX();
			double distY = enem.getImage().getY() - userRep.getY();
			double moveX = (distX * 2) / 100;
			double moveY = (distY * 2) / 100;
			enem.getImage().move(-moveX, -moveY);
			enem.getImage().movePolar(4, degree);
			degree+=50;
			degree%=360;
		}
	}
	
	private void nextRoom() {
		double userX = userRep.getX() + 75;
		double userY = userRep.getY() + 75;
		if(userX >= E6.getX() && userY >= E6.getY() && userX <= E6.getX() + 75 && userY <= E6.getY() + 75) {
			program.switchToR3();
		}
		if(userX >= E7.getX() && userY >= E7.getY() && userX <= E7.getX() + 75 && userY <= E7.getY() + 75) {
			program.switchToR5();
		}
	}
	
}
