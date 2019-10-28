package projectFiles;

import java.awt.*;
import java.awt.event.*;

import acm.graphics.*;
import acm.program.*;

public class GraphicsGame extends GraphicsProgram implements KeyListener {

	public static final int WINDOW_WIDTH = 1155;
	public static final int WINDOW_HEIGHT = 650;
	public char pressedKey;
	
	public User testUser;
	public GImage userRep;
	public Enemy testEnemy;
	public GImage enemyRep;
	public Map testMap;
	
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
		
		int pressedKey = e.getKeyCode();
		
		if(pressedKey == KeyEvent.VK_W) {
			
			testUser.moveY(1);
			userRep.setLocation(userRep.getX(), userRep.getY() - testUser.getMoveSpeedStat());

		} else if (pressedKey == KeyEvent.VK_S){

			testUser.moveY(-1);
			userRep.setLocation(userRep.getX(), userRep.getY() + testUser.getMoveSpeedStat());

		} else if (pressedKey == KeyEvent.VK_D) {
			
			testUser.moveX(1);
			userRep.setLocation(userRep.getX() + testUser.getMoveSpeedStat(), userRep.getY());
		
		} else if (pressedKey == KeyEvent.VK_A) {
			
			testUser.moveX(-1);
			userRep.setLocation(userRep.getX() - testUser.getMoveSpeedStat(), userRep.getY());
			
		}
		
	}

	public void testDraw() {
		
		userRep = new GImage("Rogue (Sample User).gif", 300, 300);
		userRep.setSize(75, 75);
		add(userRep);
		
		enemyRep = new GImage("ghost_enemy.gif", 500, 300);
		enemyRep.setSize(75, 75);
		add(enemyRep);
		
	}
	
}
