package projectFiles;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import acm.graphics.*;
import acm.program.*;
import java.util.Stack;

public class GraphicsGame extends GraphicsProgram implements KeyListener {

	public static final int WINDOW_WIDTH = 1155;
	public static final int WINDOW_HEIGHT = 650;
	public char pressedKey;
	//ArrayList<KeyEvent> diag = new ArrayList<KeyEvent>();
	Stack<KeyEvent> input = new Stack<>();
	
	public User testUser;
	public GImage userRep;
	public Enemy testEnemy;
	public GImage enemyRep;
	public Map testMap;
	public GImage weapon;
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		requestFocus();
	}
	
	public void run() {
		
		addKeyListeners();
		
		testUser = new User(5, 5, 5, 1, 300, 300);
		testEnemy = new Enemy(5, 5, 5, 1, 500, 300, ElementType.FIRE);
		testMap = new Map();
		
		testDraw();
		
	}
	
	public void keyPressed(KeyEvent e) {
		
		input.push(e);
		int pressedKey = input.lastIndexOf(o, index).getKeyCode();
		
		if(pressedKey == KeyEvent.VK_W) {
			System.out.println("W is being pressed!");
			if(pressedKey == KeyEvent.VK_D && pressedKey == KeyEvent.VK_W) {
				testUser.moveY(testUser.getMoveSpeedStat());
				testUser.moveX(testUser.getMoveSpeedStat());
				userRep.setLocation(userRep.getX() +  testUser.getMoveSpeedStat(), userRep.getY() - testUser.getMoveSpeedStat());
			}
			testUser.moveY(testUser.getMoveSpeedStat());
			userRep.setLocation(userRep.getX(), userRep.getY() - testUser.getMoveSpeedStat());

		} else if (pressedKey == KeyEvent.VK_S){

			testUser.moveY(-testUser.getMoveSpeedStat());
			userRep.setLocation(userRep.getX(), userRep.getY() + testUser.getMoveSpeedStat());

		} else if (pressedKey == KeyEvent.VK_D) {
			System.out.println("D is being pressed!");

			testUser.moveX(testUser.getMoveSpeedStat());
			userRep.setLocation(userRep.getX() + testUser.getMoveSpeedStat(), userRep.getY());
		
		} else if (pressedKey == KeyEvent.VK_A) {
			
			testUser.moveX(-testUser.getMoveSpeedStat());
			userRep.setLocation(userRep.getX() - testUser.getMoveSpeedStat(), userRep.getY());
			
		} 
		
		else if(pressedKey == KeyEvent.VK_D && pressedKey == KeyEvent.VK_W) {
			testUser.moveY(testUser.getMoveSpeedStat());
			testUser.moveX(testUser.getMoveSpeedStat());
			userRep.setLocation(userRep.getX() +  testUser.getMoveSpeedStat(), userRep.getY() - testUser.getMoveSpeedStat());
		}
		
		else if (pressedKey == KeyEvent.VK_E) {
			
			testUser.cycleWeapon();
			drawSword(testUser);
			
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		//clears all inputs 
		input.clear();
		System.out.println("Key released!");
	}

	public void testDraw() {
		
		userRep = new GImage("Rogue_(Sample User).gif", 300, 300);
		userRep.setSize(75, 75);
		add(userRep);
		
		enemyRep = new GImage("ghost_enemy.gif", 500, 300);
		enemyRep.setSize(75, 75);
		add(enemyRep);
		
		weapon = new GImage("Fire Sword.gif", 0, WINDOW_HEIGHT - 100);
		weapon.setSize(100,100);
		add(weapon);
		
	}
	
	
	
	public void drawSword(User input_user)	{
		
		remove(weapon);
		
		if(input_user.getWeaponEquiped() == 0) {
			weapon = new GImage("Fire Sword.gif", 0, WINDOW_HEIGHT - 100);
			weapon.setSize(100,100);
			add(weapon);
		} else if (input_user.getWeaponEquiped() == 1) {
			weapon = new GImage("Water Sword.gif", 0, WINDOW_HEIGHT - 100);
			weapon.setSize(100,100);
			add(weapon);
		} else {
			weapon = new GImage("Earth Sword.gif", 0, WINDOW_HEIGHT - 100);
			weapon.setSize(100,100);
			add(weapon);
		}
		
	}
	
}
