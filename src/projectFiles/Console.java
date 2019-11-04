package projectFiles;

import java.awt.event.*;
import java.util.HashMap;

import acm.program.GraphicsProgram;

public class Console {
	
	public static final int WINDOW_WIDTH = 1155;
	public static final int WINDOW_HEIGHT = 650;
	
	private Map map = new Map();
	private Room room = new Room();
	private User user = new User();
	private Enemy enemy;
	private int keyInput;
	private Floor floor = new Floor();
	
	private String floorWeOn = new String();
	private HashMap<Interactions, Coordinates> interactionHash = new HashMap<Interactions, Coordinates>();
	private HashMap<Enemy, Coordinates> enemyHash = new HashMap<Enemy, Coordinates>();
	
	public static void main(String[] args) {
		Console test = new Console();
		test.playGame();
		System.out.println("Running...");
	}
	
	public void playGame() {
		user = new User(5, 5, 1000, 1, 300, 300);
		int temp = floor.getLevelCounter(); //return int;
		floor = new Floor();
		floor.setMapArrayList();
		floorWeOn = floor.whatMapWeOn(temp); //return string of map we on
		
		if(floorWeOn == "map_base1") {
			System.out.println("I have the talking stick in the if statement");
			map.runRunBase("R1", floor, map, interactionHash, enemyHash);
			//HashMap<Interactions, Coordinates> tempInteractionHash = map.getInteractions();
			//HashMap<Enemy, Coordinates> tempEnemyHash = map.getEnemySpawn();
			
			//interactionHash = tempInteractionHash;
			//enemyHash = tempEnemyHash;
			
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
	
	public HashMap<Interactions, Coordinates> getInteractionHash(){
		return this.interactionHash;
	}
	
	public HashMap<Enemy, Coordinates> getEnemyHash(){
		return this.enemyHash;
	}
	
}
