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
	private MapLayout layout = new MapLayout();
	
	private String floorWeOn = new String();
	private HashMap<Interactions, Coordinates> interactionHash = new HashMap<Interactions, Coordinates>();
	private HashMap<Enemy, Coordinates> enemyHash = new HashMap<Enemy, Coordinates>();
	private ArrayList<Coordinates> entries = new ArrayList<Coordinates>();
	private HashMap<String,ArrayList<Coordinates>> enteredEntriesHash = new HashMap<String,ArrayList<Coordinates>>();
	private HashMap <Boss, Coordinates> bossHash = new HashMap <Boss, Coordinates>();
	private String currRoom;
	//private String roomFromEntry = new String();
	
	
	public void setNextCurrRoom(String nextCurrRoom) {
		currRoom = nextCurrRoom;
		floor.setCurrRoom(nextCurrRoom);
	}
	
	public String getLocalCurrRoom() {
		//return floor.getCurrRoom();
		return currRoom;
	}
	
	public String getNextCurrRoom() {
		 return currRoom = floor.getCurrRoom();
		//return floor.getCurrRoom();
	}
	
	public void levelAdder() {
		floor.levelAdder();
	}
	
	public int getLevelCounter() {
		return floor.getLevelCounter();
	}
	
	public void resetRoom() {
		//currRoom = "R1";
		//floor.resetCurrRoom();
		interactionHash = new HashMap<Interactions, Coordinates>();
		enemyHash = new HashMap<Enemy, Coordinates>();
	}
	
	public void resetUserRoom() {
		currRoom = "R1";
	}
	
	public void resetMap() {
		map = new Map();
	}
	
	public void playGame() {
		user = new User(5, 5, 1000, 1, 300, 300);
		int temp = getLevelCounter(); //return int;
		floor.setMapArrayList();
		floorWeOn = floor.whatMapWeOn(temp);
		boolean Playing = true;
		
		baseInit(floorWeOn);
		
		 //return string of map we on
		//System.out.println(floorWeOn);
		
		
	}
	
	public void baseInit(String currFloor) {
		resetRoom();
		generateRoom(currFloor);
	}
	
	public void generateRoom(String currFloor) {
		
		if(getLocalCurrRoom() == null) {
			resetUserRoom();
		}
		
		if(currFloor == "map_base1") {
			System.out.println("Current level: " + currFloor);
			System.out.println("Current room: " + getLocalCurrRoom());
			//System.out.println("Current room: " + getLocalCurrRoom());
			
			// perhaps its because the assigned pass b references arent being cleared when entering a new room? 
			// which is why it just resets the user position back to where he was without any room changes?

			map.runRunBase(getLocalCurrRoom(), floor, map, interactionHash, enemyHash, entries, enteredEntriesHash);
			//getNextRoom();
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
					
					// TODO check pixel range of player instead of that single point
					
					
					if (coordX >= temp1 && coordY >= temp2 - 250 && coordX <= temp1 + 50
						&& coordY <= temp2 + 500 ) {
						
					System.out.println("Detected user in the gRect!");
					System.out.println("The name of the gRect the user is in is: " + tempString);
					
					
					//TODO for the love of god, change the way how we call all our functions when this thing actually works
					HashMap<String, String> mapHashCurrEntry;
					HashMap<String, String> mapHashNextRoom;
					ArrayList<String> numOfEntries;
					int temp; 
					
						layout.setEntryAmountBasedonLayout(floor.whatMapWeOn(floor.getLevelCounter()));
						temp = layout.getEntryAmountofLayout();
						layout.setEntryAmount(temp);
						layout.setMapHash(floor.whatMapWeOn(floor.getLevelCounter()));
						mapHashCurrEntry = layout.getMapHash(floor.whatMapWeOn(floor.getLevelCounter()));
						
						String nextEntry = mapHashCurrEntry.get(tempString);
						System.out.println("The next entry will be: " + nextEntry);
						
						room.setEntryToRoom(floor.whatMapWeOn(floor.getLevelCounter()));
						numOfEntries = room.setEtoRAmount(layout.getEntryAmount(), floor.whatMapWeOn(floor.getLevelCounter()));
						
						room.setEntryToRoom(floor.whatMapWeOn(floor.getLevelCounter()));
						mapHashNextRoom = room.getMapBaseEtoR();
	
						String nextRoom = mapHashNextRoom.get(nextEntry);
						setNextCurrRoom(nextRoom);
						System.out.println("Next room will be: " + getNextCurrRoom());
						
					}break;
				}break;
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
