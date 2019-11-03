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
	private Floor floor;
	
	private String floorWeOn;
	private H_A_R_D_C_O_D_E_D_ roomSetup; 
	
	public void run() {
		playGame();
	}
	
	public void playGame() {
		user = new User(5, 5, 1000, 1, 300, 300);
		enemy = new Enemy(5, 5, 2000, 1, 500, 300, ElementType.FIRE);
		
		floor = new Floor();
		floor.setMapArrayList();
		floor.getLevelCounter(); //return int
		floorWeOn = floor.whatMapWeOn(); //return string of map we on
		
		if(floorWeOn == "map_base1") {
			//roomSetup.runBase("R1", floor);
				for(int i = 0; i <= map.getInteractions().size() - 1; i++) {
				
					String tempString = "roomOneInteraction" + i;
					Coordinates testCoordinates = roomSetup.getCoordinateFromString(tempString);
					
					System.out.println("Coordinate for " + tempString + ": " + testCoordinates.toString());
				
				}
			
		}
		
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
			user.cycleWeapon();
			break;
		default:
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
		default:
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
