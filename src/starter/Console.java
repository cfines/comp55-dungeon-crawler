package starter;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

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
		enemy.move(user);
	}
	
	public void keyPressedManager(KeyEvent e) {
		
		keyInput = e.getKeyCode();
		
		switch(keyInput) {
		case KeyEvent.VK_W:
			user.setDY(-user.getMoveSpeedStat());
			getNextRoom();
			break;
		case KeyEvent.VK_S:
			user.setDY(user.getMoveSpeedStat());
			getNextRoom();
			break;
		case KeyEvent.VK_D:
			user.setDX(user.getMoveSpeedStat());
			getNextRoom();
			break;
		case KeyEvent.VK_A:
			user.setDX(-user.getMoveSpeedStat());
			getNextRoom();
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
	
	////////////////////////////////////
	
	private ArrayList<Coordinates> enteredEntries = new ArrayList<Coordinates>();
	private Hardcoded hardcoded = new Hardcoded();
	
	public void setEnteredEntries(Coordinates entry) {
		enteredEntries.add(entry);
		System.out.println(enteredEntries.size());
	}
	
	public ArrayList<Coordinates> getEnteredEntries() {
		return enteredEntries;
	}
	
	public void getNextRoom() {
		int coordX = user.getCoordX();
		int coordY = user.getCoordY();
		ArrayList<Coordinates> tempArrayList = getEnteredEntries();
		System.out.println(tempArrayList.size());
		
		for(int i = 0; i <= tempArrayList.size() - 1; i++) {
			
			System.out.println("Inside for loop of getNextRoom");
			
			if (coordX >= getEnteredEntries().get(i).getX() && coordY >= getEnteredEntries().get(i).getY() && coordX <= getEnteredEntries().get(i).getX() 
					&& coordY <= getEnteredEntries().get(i).getY()) {
				
				System.out.println("User has entered " + getEnteredEntries().get(i));
				
			}
		}	
	}
		

	
	////////////////////////////////////
	
	public HashMap<Interactions, Coordinates> getInteractionHash(){
		return this.interactionHash;
	}
	
	public HashMap<Enemy, Coordinates> getEnemyHash(){
		return this.enemyHash;
	}
	
	public static void main(String[] args) {
		Console test = new Console();
		test.getNextRoom();
	}
	
}
