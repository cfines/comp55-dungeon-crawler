package projectFiles;

import java.awt.*;
import java.awt.event.*;

import acm.graphics.*;
import acm.program.*;

public class GraphicsGame extends GraphicsProgram {

	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public char pressedKey;
	
	public User testUser;
	public Enemy testEnemy;
	public Map testMap;
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	public void run() {
		
		addKeyListeners();
		
		testUser = new User(5, 5, 5, 1, 300, 300);
		testEnemy = new Enemy(5, 5, 5, 1, 500, 300, ElementType.FIRE);
		testMap = new Map();
		
		testDraw();
		
	}
	
	public void KeyPressed(KeyEvent e) {
		
		pressedKey = e.getKeyChar();
		
		switch(pressedKey) {
		case 'w':
			
			testUser.moveX(1);
			break;
		
		case 's':
			
			testUser.moveX(-1);
			break;
		
		case 'd':
			
			testUser.moveY(1);
			break;
		
		case 'a':
			
			testUser.moveX(1);
			break;
			
		default: break;
		
		}
		
	}

	public void testDraw() {
		
		GRect userRep = new GRect(300, 300, 25, 50);
		add(userRep);
		
		GImage enemyRep = new GImage("file:///C:/Users/Jordan/git/group-project-stacked_overflow/media/ghost_enemy.gif", 500, 300);
		add(enemyRep);
		
	}
	
}
