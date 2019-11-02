package projectFiles;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import java.util.Stack;
import java.util.Timer;

import acm.graphics.*;
import acm.program.*;
import starter.GButton;

public class GraphicsGame extends GraphicsProgram implements KeyListener {

	public static final int WINDOW_WIDTH = 1155;
	public static final int WINDOW_HEIGHT = 650;
	public char pressedKey;
	
	public User testUser;
	public GImage userRep;
	public Enemy testEnemy;
	public GImage enemyRep;
	public Map testMap;
	public GImage weapon;
	Stack<Integer> pressedKeys = new Stack<Integer>();
	
	public boolean playing;
	
	public GImage menuScreen;
	public GButton menuPlay;
	public boolean inMenu;
	public GObject toClick;
	
	Timer timer;
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		requestFocus();
	}
	
	public void run() {
		
		addKeyListeners();
		addMouseListeners();
		
		testUser = new User(5, 5, 5, 1, 300, 300);
		testEnemy = new Enemy(5, 5, 5, 1, 500, 300, ElementType.FIRE);
		testMap = new Map();
		
		inMenu = true;
		runMainMenu();
		playing = true;
		
		testDraw();
		
		while(playing) {
			testUser.move();
		}
		
	}
	
	public void keyPressed(KeyEvent e) {
	
		if(inMenu) { return; }
		
		int pressedKey = e.getKeyCode();
		pressedKeys.push(pressedKey);
			
			if(pressedKey == KeyEvent.VK_W) {
				
				testUser.setVelY(testUser.getMoveSpeedStat());
				userRep.setLocation(userRep.getX(), userRep.getY() - testUser.getMoveSpeedStat());

			} else if (pressedKey == KeyEvent.VK_S){

				testUser.setVelY(-testUser.getMoveSpeedStat());
				userRep.setLocation(userRep.getX(), userRep.getY() + testUser.getMoveSpeedStat());

			} else if (pressedKey == KeyEvent.VK_D) {
				
				testUser.setVelX(testUser.getMoveSpeedStat());
				userRep.setLocation(userRep.getX() + testUser.getMoveSpeedStat(), userRep.getY());
			
			} else if (pressedKey == KeyEvent.VK_A) {
				
				testUser.setVelX(-testUser.getMoveSpeedStat());
				userRep.setLocation(userRep.getX() - testUser.getMoveSpeedStat(), userRep.getY());
				
			} 
			
			testUser.move();
		
		//else if (pressedKey == KeyEv
		//else if (pressedKey == KeyEvent.VK_E) {
			
			//testUser.cycleWeapon();
			//drawSword(testUser);
			
		//}
		
	}
	
	
	
	public void keyReleased(KeyEvent e) {
		
		//pressedKeys.pop();
		
		if(pressedKey == KeyEvent.VK_W) {
			
			testUser.setVelY(0);

		} else if (pressedKey == KeyEvent.VK_S){

			testUser.setVelY(0);
			
		} else if (pressedKey == KeyEvent.VK_D) {
			
			testUser.setVelX(0);
		
		} else if (pressedKey == KeyEvent.VK_A) {
			
			testUser.setVelX(0);
			
		} 
	}
	
	public void mousePressed(MouseEvent e) {
		
		toClick = getElementAt(e.getX(), e.getY());
		
		if(toClick == menuPlay) {
		
			removeAll();
			inMenu = false;
			
		}
		
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
	
	public void runMainMenu() {
		
		menuScreen = new GImage("Main Menu (Lights on without koolaid).png", 0, 0);
		menuScreen.setSize(1155, 650);
		add(menuScreen);
		
		menuPlay = new GButton("Play", 50, WINDOW_HEIGHT - 75, 150, 50);
		add(menuPlay);
		
		while(inMenu) {
			
			System.out.println("You are in the menu!");
			
			//timer = new Timer();
			
		}
		
	}
	
	public void runPauseMenu() {
		
	}
	
}
