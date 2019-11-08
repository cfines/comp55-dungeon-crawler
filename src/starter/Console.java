package starter;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class Console {

	///////////////////////////// INSTANCE VARIABLES ///////////////////////////////////////

	//CONSOLE IMPORTANT VARIABLES
	private User user = new User();
	private Floor floor = new Floor();
	private Map map = new Map();
	private MapLayout layout = new MapLayout();
	private Room room = new Room();
	private boolean gamePaused = false;
	
	//Enemy and Interaction Handling
	private HashMap<Interactions, Coordinates> interactionHash = new HashMap<Interactions, Coordinates>();
	private HashMap<Enemy, Coordinates> enemyHash = new HashMap<Enemy, Coordinates>();
	private HashMap <Boss, Coordinates> bossHash = new HashMap <Boss, Coordinates>();
	private HashMap<String,ArrayList<Coordinates>> enteredEntriesHash = new HashMap<String,ArrayList<Coordinates>>();
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Coordinates> entries = new ArrayList<Coordinates>();
	
	//Room Traversal
	private String floorWeOn = new String();
	private String roomWeIn = new String();
	private String currRoom;
	private int keyInput;

	//Misc. Variables
	//private String roomFromEntry = new String();

	///////////////////////////// END OF INSTANCE VARIABLES ///////////////////////////////////////
	
	
	
	///////////////////////////// GETTERS AND SETTERS ///////////////////////////////////////

	//CONSOLE VARIABLES
	public User getUser() {
		return user;
	}
	
	public void setUser(int input_HP_cur, int input_HP_tot, int atkTime, int input_dmg, int input_x, int input_y) {
		user = new User(input_HP_cur, input_HP_tot, atkTime, input_dmg, input_x, input_y);
	}
	
	public Floor getFloor() {
		return floor;
	}
	
	public void setNextCurrRoom(String nextCurrRoom) {
		currRoom = nextCurrRoom;
		floor.setCurrRoom(nextCurrRoom);
	}

	public String getLocalCurrRoom() {
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
	
	public ArrayList<Enemy> getEnemiesArray(){
		return this.enemies;
	}
	
	public void setGamePaused(boolean gamePaused) {
		this.gamePaused = gamePaused;
	}
	
	public boolean getGamePaused() {
		return gamePaused;
	}
	
	public String getRoomWeIn() {
		return roomWeIn;
	}
	
	public void setRoomWeIn(String roomWeIn) {
		this.roomWeIn = roomWeIn;
	}
	
	//RESETS

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
	
	public ArrayList<Enemy> getEnemies() {
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		for(Enemy key : enemyHash.keySet()) {
			enemies.add(key);
		}
		return enemies;
	}

	
	/////////////////////////// END OF GETTERS AND SETTERS ////////////////////////////////////////
	private String currentFloor;
	public void setCurrFloor(String input) {
		currentFloor = input;
	}
	public String getCurrFloor() {
		return currentFloor;
	}
	
	//////////////////////////////////// PLAY GAME //////////////////////////////////////////////////
	
	public void playGame() {
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		user = new User(5, 5, 1000, 1, 300, 300);
		int temp = getLevelCounter(); //return int;
		floor.setMapArrayList();
		floorWeOn = floor.whatMapWeOn(temp);
		setCurrFloor(floorWeOn);
		boolean Playing = true;
		
		if(getLocalCurrRoom() == null) {
			resetUserRoom();
		}
		
		baseInit(getLocalCurrRoom() , floorWeOn);
		//baseInit(getLocalCurrRoom() , floorWeOn);
		//baseInit(getLocalCurrRoom() , floorWeOn);
		
		
		 //return string of map we on
		//System.out.println(floorWeOn);

		for(Enemy key : enemyHash.keySet()) {
			enemies.add(key);
		}
		if(enemies.size() > 0) {
			for(Enemy enemy : enemies) {
				moveEnemy(enemy);
			}
		}

		if(floorWeOn == "map_base1") {
			if(getLocalCurrRoom() == null) {
				resetRoom();
			}
		}
		
	}
	
	//I'm not too sure why we need this but I won't remove it for the sake of someone testing
	public static void main(String[] args) {
		Console test = new Console();
		test.getNextRoom();
	}
	
	/////////////////////////////// END OF PLAY GAME /////////////////////////////////////////////
	
	
	////////////////////////////// MOVEMENT AND INTERACTMENT ////////////////////////////////
	
	public void actionPerformed(KeyEvent ae) {
		
		if(gamePaused) { return; }
		user.tick();
		
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).tick();
		}
		
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
	
	public void moveEnemy(Enemy enemy) {
		for(Enemy enemy2 : enemies) {
			enemy2.move(user);
		}
		enemy.move(user);
	}
	
	public boolean canMove() {
		return true;
	}
	
	/////////////////////////// END OF MOVEMENT AND INTERACTMENT ////////////////////////////
	
	
	/////////////////////////// ROOM/MAP/FLOOR TRAVERSAL AND SETUP ///////////////////////////////
	
	public void baseInit(String nextCurrRoom, String currFloor) {
		resetRoom();
		generateRoom(nextCurrRoom, currFloor);
	}
	
	public void generateRoom(String nextCurrRoom, String currFloor) {
		
		
		System.out.println("Current level: " + currFloor);
		System.out.println("Current room: " + nextCurrRoom);

		map.runRunBase(nextCurrRoom, floor, map, interactionHash, enemyHash, entries, enteredEntriesHash, bossHash);
		//getNextRoom();

	}

	public void getNextRoom() {
		int coordX = user.getCoordX();
		int coordY = user.getCoordY();
		String tempString;
		//ArrayList<Coordinates> tempArrayList = new ArrayList<Coordinates>();
		//tempArrayList = getEntries();
		HashMap<String,ArrayList<Coordinates>> tempHash = getEntriesHash();

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
					HashMap<String, String> mapHashCurrEntry = new HashMap<String, String>();
					HashMap<String, String> mapHashNextRoom = new HashMap<String, String>();
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
						//baseInit(getNextCurrRoom(), getCurrFloor());
						playGame();
						break;
					}
				}
			}
		}

	private void removeAll() {
		// TODO Auto-generated method stub
		
	}
	
	//////////////////////// END OF ROOM/MAP/FLOOR TRAVERSAL AND SETUP ///////////////////////////////

}
