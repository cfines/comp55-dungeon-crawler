package starter;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import javax.swing.Timer;

import acm.graphics.*;
import acm.program.*;

public class GraphicsGame extends GraphicsProgram implements ActionListener, KeyListener {

	////////////////////////// INSTANCE VARIABLES AND RUN /////////////////////////////
	
	//Window Variables
	public static final int WINDOW_WIDTH = 1155;
	public static final int WINDOW_HEIGHT = 650;
	
	//GG Variables
	public Console game;
	public boolean inMenu;
	public int pressedKey;
	public boolean firstSwordCall = true;
	public boolean running = true;
	public Timer timer;
	
	//GRAPHICS Door/Entries
	public static final int DOOR_WIDTH = 50;
	public static final int DOOR_HEIGHT = 500;
	public GRect entry;
	public GImage stairs;
	
	//GRAPHICS Room Creation and Entity Representation
	public GImage userRep;
	public GImage enemyRep;
	public GImage interactionRep;
	public GImage floor;
	
	//GRAPHICS Overlay Stuff
	public GImage weapon;
	public GLabel health;
	
	//GRAPHICS Menu Stuff
	public GImage menuScreen;
	public GButton menuPlay, highScore, credits, exit;
	public GRect menuPause;
	public GButton menuPauseReturn;
	
	//AUDIO Sound Stuff
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = { "main_menu_background.mp3" };
	private int count;
	
	//Misc. Important Stuff
	public GObject toClick;
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		requestFocus();
	}
	
	public void run() {
		playRandomSound();
		addKeyListeners();
		addMouseListeners();
		
		inMenu = true;
		runMainMenu();
		
		game = new Console();
		game.playGame();

		drawRoom();
		
		while(running) {
			
			if(game.getLocalCurrRoom() != game.getUser().getCurrRoom()) {
				resetRoom();
				drawRoom();
				game.getUser().setCurrRoom(game.getLocalCurrRoom());
			}
			
		}

	}
	
	////////////////////////// END OF INSTANCE VARIABLES AND RUN /////////////////////////////
	
	
	
	////////////////////////// USER INTERACTION WITH GRAPHICSGAME /////////////////////////////
	
	public void actionPerformed(KeyEvent ae) {
		
		if(inMenu || game.getGamePaused()) { return; }
		
		//These two lines are responsible for moving User and its respective image
		game.getUser().tick();
		userRep.setLocation(game.getUser().getCoordX(), game.getUser().getCoordY());
		
		ArrayList<Enemy> tempEnem = game.getEnemies();
		
		for(Enemy enemy : tempEnem) {
			game.moveEnemy(enemy);
		}
		
		for(Enemy enemy : tempEnem) {
			enemyRep.setLocation(enemy.getCoordX(), enemy.getCoordY());
		}
		
		if(ae.getKeyCode() == KeyEvent.VK_E) {
			drawSword();
		}	
		
		if(ae.getKeyCode() == KeyEvent.VK_ESCAPE) {
			runPauseMenu();
		}
		
		//Check for User Location and Image Location sync
		System.out.println("USER LOCATION: X=" + game.getUser().getCoordX() + ", Y=" + game.getUser().getCoordY());
		System.out.println("CURRENT ROOM: " + game.getLocalCurrRoom());
		//System.out.println("IMAGE LOCATION: X=" + userRep.getX() + ", Y=" + userRep.getY());
		//System.out.println("USER WEAPON: " + game.getUser().getWeaponEquipedString());
		
	}
	
	public void keyPressed(KeyEvent e) {
	
		if(game.getGamePaused() && (pressedKey == KeyEvent.VK_ESCAPE)) {

			remove(menuPause);
			remove(menuPauseReturn);
			game.setGamePaused(false);
			
		} else if(inMenu) { return; }
		
		if(pressedKey == KeyEvent.VK_ESCAPE) {
			runPauseMenu();
		} else {			
			game.keyPressedManager(e);
			actionPerformed(e);		
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		
		if(inMenu || game.getGamePaused()) { return; }
		
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
			game.setGamePaused(false);
		}
		
	}
	
	//////////////////////////// END USER INTERACTMENT WITH GRAPHICS GAME ////////////////////////////
	
	
	
	//////////////////////////// MENU CALLS //////////////////////////////////////////////////////////
	
	
	//TODO- change these to GraphicsPanes, have method calls that activate them
	//-Scharkey
	
	public void runMainMenu() {
		
		menuScreen = new GImage("Main Menu (Lights on without koolaid).png", 0, 0);
		menuScreen.setSize(1155, 650);
		add(menuScreen);
		
		menuPlay = new GButton("Play", 50, WINDOW_HEIGHT - 75, 150, 50);
		add(menuPlay);
		
		highScore = new GButton("High Scores", 300, WINDOW_HEIGHT - 75, 150, 50);
		add(highScore);
		
		credits = new GButton("Credits", 550, WINDOW_HEIGHT - 75, 150, 50);
		add(credits);
		
		exit = new GButton("Exit", 800, WINDOW_HEIGHT - 75, 150, 50);
		add(exit);
		
		
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
		if(inMenu || game.getGamePaused()) { return; }
		
		game.setGamePaused(true);
		
		menuPause = new GRect(WINDOW_WIDTH, WINDOW_HEIGHT, WINDOW_WIDTH, WINDOW_HEIGHT);
		menuPause.setColor(Color.WHITE);
		add(menuPause);
		
		menuPauseReturn = new GButton("Return", 50, WINDOW_HEIGHT - 75, 150, 50);
		add(menuPauseReturn);
		
		//inMenu is mainly used to let the game know that we aren't playing the game yet- the most important
		//functionality of this is that it doens't update character location. 
		
		
	}
	
	//////////////////////////// END OF MENU CALLS ///////////////////////////////////////////////////
	
	
	
	//////////////////////////// DRAWING CALLS ///////////////////////////////////////////////////////

	public void drawRoom() {
		
		floor = new GImage("Base Map (floor).png", 0, 0);
		floor.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		add(floor);
		
		userRep = new GImage("Rogue_(Sample User).gif", game.getUser().getCoordX(), game.getUser().getCoordY());
		userRep.setSize(75, 75);
		add(userRep);

		drawInteraction();
		drawEnemy();
		
		drawOverlay();
		
	}
	
	public void drawInteraction() {
		
		Interactions tempInteraction;
		HashMap<Interactions, Coordinates> tempHash = game.getInteractionHash();
		
		for(HashMap.Entry test : tempHash.entrySet()) {
			
			tempInteraction = (Interactions)test.getKey();
			Coordinates tempCoord = tempHash.get(test.getKey());
			
			// TODO: check if the entry's hard coded coordinates actually correspond to where theyre placed.
			
			if(tempInteraction.getinteractionType() == interactionType.entry_door) {
				entry = new GRect(tempCoord.getX(), tempCoord.getY() - (DOOR_HEIGHT / 2), DOOR_WIDTH, DOOR_HEIGHT);
				entry.setFillColor(Color.BLACK);
				add(entry);
			} else {
				interactionRep = new GImage(tempInteraction.getinteractionType() + ".png", tempCoord.getX(), tempCoord.getY());
				interactionRep.setSize(75, 75);
				add(interactionRep);
			}
			
		}
	}
	
	public void drawEnemy() {
		
		Enemy tempEnemy;
		HashMap<Enemy, Coordinates> tempHash = game.getEnemyHash();
		
		for(HashMap.Entry test : tempHash.entrySet()) {
			
			tempEnemy = (Enemy) test.getKey();
			Coordinates tempCoord = tempHash.get(test.getKey());
			
			enemyRep = new GImage(tempEnemy.getEnemyType() + "Skull.png", tempCoord.getX(), tempCoord.getY());
			enemyRep.setSize(75, 75);
			add(enemyRep);
			
		}
	
	}
	
	public void drawOverlay() {
		
		drawSword();
		drawHealth();
		
	}
	
	public void drawSword()	{
		
		if(!firstSwordCall) { remove(weapon); }
		firstSwordCall = false;
		
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
	
	public void drawHealth() {
		
		health = new GLabel("HP: " + game.getUser().getUserStats().getHP_cur() + " / " + game.getUser().getUserStats().getHP_tot(), 10, 50);
		health.setFont("Arial-Bold-22");
		health.setColor(Color.red);
		add(health);
		
		
	}
	
	public void resetRoom() {
		removeAll();
		firstSwordCall = true;
	}
	
	///////////////////////////// END OF DRAWING CALLS ////////////////////////////////////////////
	
	
	
	///////////////////////////// AUDIO CALLS /////////////////////////////////////////////////////
	
	private void playRandomSound() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound(MUSIC_FOLDER, SOUND_FILES[count % SOUND_FILES.length]);
	}
	
	///////////////////////////// END OF AUDIO CALLS //////////////////////////////////////////////
}
