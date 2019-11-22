package starter;

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
import starter.ElementType;
import starter.Enemy;
import starter.GraphicsPane;
import starter.Interactions;
import starter.MainApplication;
import starter.User;
import starter.enemyType;
import starter.interactionType;

public class KeyPressedManager {

	private MainApplication program;
	private User user;
	private GImage enemy1, enemy2, E4, E5, rock1, hole1, background,userRep;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private boolean atkUp,atkLeft,atkRight,atkDown;
	Timer t = new Timer(30, this);
	private int degree;
	private int timerCont = 0;
	private boolean move = true;
	
	KeyPressedManager(){
		
	}
	
	public void notReallyActionPerformed(ActionEvent e) {
		isUserInPain();
		nextRoom();
		user.tick();
		checkCollision();
		userRep.setLocation(user.getX(), user.getY());
	}
	
	public void notReallyKeyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			userUP();
			//isUserInPain(); 
			break;
		case KeyEvent.VK_S:
			userDOWN();
			//isUserInPain();
			break;
		case KeyEvent.VK_A:
			userLEFT();
			//isUserInPain();
			break;
		case KeyEvent.VK_D:
			userRIGHT();
			//isUserInPain();
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

	public void notReallyKeyReleased(KeyEvent e) {
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

	
	public void isUserInPain() 
	{
		int newHealth;
		double userX = userRep.getX() + 75;
		double userY = userRep.getY() + 75;
		for(int i = 0; i < listOfEnemies.size(); i++) {
			if(userX >= listOfEnemies.get(i).getCoordX() && 
				userY >= listOfEnemies.get(i).getCoordY() && 
				userX <= listOfEnemies.get(i).getCoordX() + 75 && 
				userY <= listOfEnemies.get(i).getCoordY() + 75) 
			{
				//if the user is fighting
				if(atkUp == true || atkDown == true || atkLeft == true || atkRight == true) 
				{
					//damage dealt to enemy
					newHealth = listOfEnemies.get(i).getEnemyStats().getHP_cur() - (int)program.getUser().getPowerStat();
					listOfEnemies.get(i).getEnemyStats().setHP_cur(newHealth);
					if( listOfEnemies.get(i).getEnemyStats().getHP_cur() <= 0) 
					{
						//should remove an enemy
						int tempX = (int)listOfEnemies.get(i).getCoordX();
						int tempY = (int)listOfEnemies.get(i).getCoordY();
						Interactions rip = new Interactions(interactionType.rip, tempX, tempY);
						Interactions rip2 = new Interactions(interactionType.rip2, tempX, tempY);
						program.add(rip2.getImage());
						program.add(rip.getImage());
						listOfEnemies.remove(i);
					}
				}
				//if user is not attacking
				else{
					newHealth = program.getUser().getUserStats().getHP_cur() - 1;
					program.getUser().getUserStats().setHP_cur(newHealth);
					System.out.println("User takes 1 damage, ouch.");
					program.refreshOverlay();
					program.drawOverlay(3, 1);
				}
				//TODO insert user getting hurt here
			}
			/*
			 * if (program.getUser().getUserStats().getHP_cur() == 0) {
			 * program.switchToGameOver();
			 * 
			 * }
			 */
		}
	}
	
	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		if(userX >= E4.getX() && userY >= E4.getY() && userX <= E4.getX() + 75 && userY <= E4.getY() + 75) {
			program.switchToR2();
			userRep.setLocation(1030,300);
		}
		
		userX = userRep.getX() + 80;
		userY = userRep.getY() + 80;
		
		if(userX >= E5.getX() && userY >= E5.getY() && userX + 80 <= E5.getX() + 75 && userY + 80 <= E5.getY() + 75) {
			program.switchToR4();
			userRep.setLocation(40,300);
		}
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
	
	public void checkCollision() {
		for(Interactions inter : listOfInter) {	
			if(intCollisionTest(inter.getImage())) {
				//TODO Set these comparisons to booleans
				if (user.getDY() < 0 || user.getDY() < 0 && user.getDX() < 0 || user.getDY() < 0 && user.getDX() > 0) {
					System.out.println("bottom"); 
					user.setY(user.getY() + user.getMoveSpeedStat()); 
				} 
				if (user.getDY() > 0 || user.getDY() > 0 && user.getDX() < 0 || user.getDY() > 0 && user.getDX() > 0) {
					System.out.println("top"); 
					user.setY(user.getY() - user.getMoveSpeedStat());
				}
				if (user.getDX() < 0 || user.getDX() < 0 && user.getDY() < 0 || user.getDX() < 0 && user.getDY() > 0) { 
					System.out.println("right"); 
					user.setX(user.getX() + user.getMoveSpeedStat()); 
				} 
				if(user.getDX() > 0 || user.getDX() > 0 && user.getDY() < 0 || user.getDX() > 0 && user.getDY() > 0) {
					System.out.println("left"); 
					user.setX(user.getX() - user.getMoveSpeedStat());
				} 
			}
		}
		
		for(Enemy enem : listOfEnemies) {
			if(enemyCollisionTest(enem, userRep)) {
				if (user.getDY() < 0 || user.getDY() < 0 && user.getDX() < 0 || user.getDY() < 0 && user.getDX() > 0) {
					System.out.println("bottom"); 
					user.setY(user.getY() + 50); 
				} 
				if (user.getDY() > 0 || user.getDY() > 0 && user.getDX() < 0 || user.getDY() > 0 && user.getDX() > 0) {
					System.out.println("top"); 
					user.setY(user.getY() - 50);
				}
				if (user.getDX() < 0 || user.getDX() < 0 && user.getDY() < 0 || user.getDX() < 0 && user.getDY() > 0) { 
					System.out.println("right"); 
					user.setX(user.getX() + 50); 
				} 
				if(user.getDX() > 0 || user.getDX() > 0 && user.getDY() < 0 || user.getDX() > 0 && user.getDY() > 0) {
					System.out.println("left"); 
					user.setX(user.getX() - 50);
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
	
	public boolean enemyCollisionTest(Enemy enem, GImage image) {
		return (enem.getImage().getY() - image.getY() <= 60
				&& enem.getImage().getY() - image.getY() >= -60
				&& enem.getImage().getX() - image.getX() <= 60
				&& enem.getImage().getX() - image.getX() >= -60);
	}
	
	private void attackUp() {

		if(program.getUser().getWeaponEquiped() == 0)
		{
			userRep.setImage("FIREUser Attack (Up).png");
			userRep.setSize(55,115);
		}
		if(program.getUser().getWeaponEquiped() == 1) 
		{
			userRep.setImage("WaterUser Attack (Up).png");
			userRep.setSize(55,115);
		}
		if(program.getUser().getWeaponEquiped() == 2) 
		{
			userRep.setImage("EARTHUser Attack (Up).png");
			userRep.setSize(55,115);
		}
	}
	private void attackDown() {
		if(program.getUser().getWeaponEquiped() == 0)
		{
			userRep.setImage("FIREUser Attack (Down).png");
			userRep.setSize(55,115);
		}
		if(program.getUser().getWeaponEquiped() == 1) 
		{
			userRep.setImage("WaterUser Attack (Down).png");
			userRep.setSize(55,115);
		}
		if(program.getUser().getWeaponEquiped() == 2) 
		{
			userRep.setImage("EARTHUser Attack (Down).png");
			userRep.setSize(55,115);
		}
	}
	private void attackLeft() {
		if(program.getUser().getWeaponEquiped() == 0)
		{
			userRep.setImage("FIREUser Attack (Left).png");
			userRep.setSize(125,75);
		}
		if(program.getUser().getWeaponEquiped() == 1) 
		{
			userRep.setImage("WaterUser Attack (Left).png");
			userRep.setSize(125,75);
		}
		if(program.getUser().getWeaponEquiped() == 2) 
		{
			userRep.setImage("EARTHUser Attack (Left).png");
			userRep.setSize(125,75);
		}
	}
	private void attackRight() {
		if(program.getUser().getWeaponEquiped() == 0)
		{
			userRep.setImage("FIREUser Attack (Right).png");
			userRep.setSize(125,75);
		}
		if(program.getUser().getWeaponEquiped() == 1) 
		{
			userRep.setImage("WaterUser Attack (Right).png");
			userRep.setSize(125,75);
		}
		if(program.getUser().getWeaponEquiped() == 2) 
		{
			userRep.setImage("EARTHUser Attack (Right).png");
			userRep.setSize(125,75);
		}
	}

	private void attackReset() {
		userRep.setImage("Rogue_(Sample User).gif");
		userRep.setSize(75,75);
	}
	
}
