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
	private ArrayList<Coordinates> entries = new ArrayList<Coordinates>();
	private HashMap<String,ArrayList<Coordinates>> enteredEntriesHash = new HashMap<String,ArrayList<Coordinates>>();
	
	public void playGame() {
		user = new User(5, 5, 1000, 1, 300, 300);
		int temp = floor.getLevelCounter(); //return int;
		floor = new Floor();
		floor.setMapArrayList();
		floorWeOn = floor.whatMapWeOn(temp); //return string of map we on
		
		
		if(floorWeOn == "map_base1") {
			map.runRunBase("R1", floor, map, interactionHash, enemyHash, entries, enteredEntriesHash);
			//HashMap<Interactions, Coordinates> tempInteractionHash = map.getInteractions();
			//HashMap<Enemy, Coordinates> tempEnemyHash = map.getEnemySpawn();
			
			//interactionHash = tempInteractionHash;
			//enemyHash = tempEnemyHash;
			
		} 
		
	}
	
	public void actionPerformed(KeyEvent ae) {
		user.tick();
		//enemy.move(user);
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
	
	Hardcoded hardcoded = new Hardcoded();
	
		public void getNextRoom() {
			int coordX = user.getCoordX();
			int coordY = user.getCoordY();
			String tempString;
			//ArrayList<Coordinates> tempArrayList = new ArrayList<Coordinates>();
			//tempArrayList = getEntries();
			
			HashMap<String,ArrayList<Coordinates>> tempHash = new HashMap<String,ArrayList<Coordinates>>();
			tempHash = getEntriesHash();
		
			for(HashMap.Entry test : tempHash.entrySet()) {
					
				tempString = (String)test.getKey();
				ArrayList<Coordinates> tempCoord = tempHash.get(test.getKey());
				
				for(int i = 0; i <= tempCoord.size() - 1; i++) {
					int temp1 = tempCoord.get(i).getX();
					int temp2 = tempCoord.get(i).getY();
					
					if (coordX >= temp1 && coordY >= temp2 - 250 && coordX <= temp1 + 50
						&& coordY <= temp2 + 500 ) {
						
					System.out.println("Detected user in the gRect!");
					System.out.println("The name of the gRect the user is in is: " + tempString);
					}
				}
			}
		}
	
		

	
	////////////////////////////////////
	
	public ArrayList<Coordinates> getEntries(){
		return this.entries;
	}
	
	public HashMap<String,ArrayList<Coordinates>> getEntriesHash(){
		return this.enteredEntriesHash;
	}
	
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
