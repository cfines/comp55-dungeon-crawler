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
	private int keyInput;
	
	public void run() {
		
		playGame();
		
	}
	
	public void playGame() {
		user = new User(5, 5, 1000, 1, 300, 300);
		enemy = new Enemy(5, 5, 2000, 1, 500, 300, ElementType.FIRE);
	}
	
	public void actionPerformed(KeyEvent ae) {
		user.tick();
	}
	
	public void keyPressedManager(KeyEvent e) {
		
		keyInput = e.getKeyCode();
		
		switch(keyInput) {
		case KeyEvent.VK_W:
			user.setDY(-user.getMoveSpeedStat());
			break;
		case KeyEvent.VK_S:
			user.setDY(user.getMoveSpeedStat());
			break;
		case KeyEvent.VK_D:
			user.setDX(user.getMoveSpeedStat());
			break;
		case KeyEvent.VK_A:
			user.setDX(-user.getMoveSpeedStat());
			break;
		case KeyEvent.VK_E:
			break;
		}
		
		actionPerformed(e);
		
	}
	
	public void keyReleasedManager(KeyEvent e) {
		
		keyInput = e.getKeyCode();
		
		switch(keyInput) {
		case KeyEvent.VK_W:
			user.setDY(0);
			break;
		case KeyEvent.VK_S:
			user.setDY(0);
			break;
		case KeyEvent.VK_D:
			user.setDX(0);
			break;
		case KeyEvent.VK_A:
			user.setDX(0);
			break;
		case KeyEvent.VK_E:
			break;
		}
		
	}
	
	public boolean canMove() {
		return true;
	}
	
	public User getUser() {
		return user;
	}
	
}
