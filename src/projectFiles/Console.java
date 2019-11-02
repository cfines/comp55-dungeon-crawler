package projectFiles;

import java.awt.event.*;

import acm.program.GraphicsProgram;

public class Console extends GraphicsProgram {
	
	public static final int WINDOW_WIDTH = 1155;
	public static final int WINDOW_HEIGHT = 650;
	
	private Map map;
	private Room room;
	private User user;
	private Enemy enemy;
	private int pressedKey;
	
	public void run() {
		
		playGame();
		
	}
	
	public void playGame() {
		user = new User(5, 5, 1000, 1, 300, 300);
		enemy = new Enemy(5, 5, 2000, 1, 500, 300, ElementType.FIRE);
	}
	
	public void keyPressedManager(KeyEvent e) {
		
		pressedKey = e.getKeyCode();
		
		if(pressedKey == KeyEvent.VK_W) {
			
			user.setVelY(user.getMoveSpeedStat());

		} else if (pressedKey == KeyEvent.VK_S){

			user.setVelY(-user.getMoveSpeedStat());

		} else if (pressedKey == KeyEvent.VK_D) {
			
			user.setVelX(user.getMoveSpeedStat());
		
		} else if (pressedKey == KeyEvent.VK_A) {
			
			user.setVelX(-user.getMoveSpeedStat());
			
		} 
		
		user.move();
		
	}
	
	public void keyReleasedManager(KeyEvent e) {
		
		pressedKey = e.getKeyCode();
		
		if(pressedKey == KeyEvent.VK_W) {
			
			user.setVelY(0);

		} else if (pressedKey == KeyEvent.VK_S){

			user.setVelY(0);
			
		} else if (pressedKey == KeyEvent.VK_D) {
			
			user.setVelX(0);
		
		} else if (pressedKey == KeyEvent.VK_A) {
			
			user.setVelX(0);
			
		} else if (pressedKey == KeyEvent.VK_E) {
			
			user.cycleWeapon();
			
		}
		
	}
	
	public boolean canMove() {
		return true;
	}
	
	public User getUser() {
		return user;
	}
	
}
