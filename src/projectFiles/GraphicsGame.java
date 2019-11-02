package projectFiles;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import java.util.Stack;
import java.util.Timer;

import acm.graphics.*;
import acm.program.*;
import starter.GButton;

public class GraphicsGame extends GraphicsProgram implements ActionListener, KeyListener {

	public static final int WINDOW_WIDTH = 1155;
	public static final int WINDOW_HEIGHT = 650;
	public int pressedKey;
	
	public Console game;
	
	public GImage userRep;
	public GImage enemyRep;
	public GImage weapon;
	
	public boolean playing;
	
	public GImage menuScreen;
	public GButton menuPlay;
	public boolean inMenu;
	public GObject toClick;
	
	public GRect menuPause;
	public GButton menuPauseReturn;
	
	Timer timer;
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		requestFocus();
	}
	
	public void run() {
		
		addKeyListeners();
		addMouseListeners();
		
		inMenu = true;
		runMainMenu();
		playing = true;
		
		testDraw();
		game = new Console();
		game.run();
		
	}
	
	public void actionPerformed(KeyEvent ae) {
		game.getUser().tick();
		userRep.setLocation(game.getUser().getCoordX(), game.getUser().getCoordY());
		
		if(ae.getKeyCode() == KeyEvent.VK_E) {
			drawSword();
		}	
		
		if(ae.getKeyCode() == KeyEvent.VK_Q) {
			runPauseMenu();
		}
		
		//Check for User Location and Image Location sync
		System.out.println("USER LOCATION: X=" + game.getUser().getCoordX() + ", Y=" + game.getUser().getCoordY());
		System.out.println("IMAGE LOCATION: X=" + userRep.getX() + ", Y=" + userRep.getY());
		System.out.println("USER WEAPON: " + game.getUser().getWeaponEquipedString());
	}
	
	public void keyPressed(KeyEvent e) {
	
		if(inMenu) { return; }
		
		if(pressedKey == KeyEvent.VK_Q) {
			runPauseMenu();
		} else {			
			game.keyPressedManager(e);
			actionPerformed(e);		
		}
		
	}
	
	
	
	public void keyReleased(KeyEvent e) {
		
		if(inMenu) { return; }
		
		game.keyReleasedManager(e);
		actionPerformed(e);
		
	}
	
	public void mousePressed(MouseEvent e) {
		
		toClick = getElementAt(e.getX(), e.getY());
		
		//If "Play" button is selected in main menu
		if(toClick == menuPlay) {
			removeAll();
			inMenu = false;
		}
		
		//If "Return" button is selected in pause menu
		if(toClick == menuPauseReturn) {
			remove(menuPause);
			remove(menuPauseReturn);
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
	
	public void drawSword()	{
		
		remove(weapon);
		
		if(game.getUser().getWeaponEquiped() == 0) {
			weapon = new GImage("Fire Sword.gif", 0, WINDOW_HEIGHT - 100);
			weapon.setSize(100,100);
			add(weapon);
		} else if (game.getUser().getWeaponEquiped() == 1) {
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
		
		//inMenu is mainly used to let the game know that we aren't playing the game yet- the most important
		//functionality of this is that it doens't update character location. 
		while(inMenu) {
			
			//DO NOT REMOVE- GImages for testDraw() don't work without this message for whatever reason
			System.out.println("You are in the menu!");
			
		}
		
	}
	
	public void runPauseMenu() {
		
		//If the user is already in a menu, another pause menu is not created.
		//(This is mainly to prevent pausing within the main menu)
		if(inMenu) { return; }
		
		inMenu = true;
		
		menuPause = new GRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		menuPause.setColor(Color.WHITE);
		add(menuPause);
		
		menuPauseReturn = new GButton("Return", 50, WINDOW_HEIGHT - 75, 150, 50);
		add(menuPauseReturn);
		
		//inMenu is mainly used to let the game know that we aren't playing the game yet- the most important
		//functionality of this is that it doens't update character location. 
		while(inMenu) {
					
			//DO NOT REMOVE- GImages for testDraw() don't work without this message for whatever reason
			System.out.println("You are in the menu!");
					
		}
		
	}
	
}
