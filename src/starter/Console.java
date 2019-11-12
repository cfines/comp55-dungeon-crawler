package starter;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.Timer;

import acm.graphics.GImage;

public class Console {

	///////////////////////////// INSTANCE VARIABLES ///////////////////////////////////////

	//CONSOLE IMPORTANT VARIABLES
	private User user;
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

	private Boolean[] keyDown = new Boolean[9];
	private boolean canMove = true;
	
	//Room Traversal
	private String floorWeOn = new String();
	private String roomWeIn = new String();
	private String currRoom;
	private int keyInput;
	private Timer timer;
	public static final int DELAY_MS = 25;

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
<<<<<<< HEAD

=======
	
	public ArrayList<Enemy> getEnemies(){
		ArrayList<Enemy> tempArray = new ArrayList<Enemy>();
		for(Enemy key : getEnemyHash().keySet()) {
			tempArray.add(key);
		}
		return tempArray;
	}
	
>>>>>>> branch 'master' of https://github.com/comp55/group-project-stacked_overflow.git
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
<<<<<<< HEAD

=======
	
	public boolean getCanMove() {
		return canMove;
	}
	
>>>>>>> branch 'master' of https://github.com/comp55/group-project-stacked_overflow.git
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
		user = new User(5, 5, 1000, 1, 300, 300);
		enemies = new ArrayList<Enemy>(); 
		for(Enemy key : getEnemyHash().keySet()) {
			enemies.add(key);
		}
		int temp = getLevelCounter(); //return int;
		floor.setMapArrayList();
		floorWeOn = floor.whatMapWeOn(temp);
		setCurrFloor(floorWeOn);
		boolean Playing = true;
<<<<<<< HEAD

		baseInit(floorWeOn);

		//return string of map we on
		
=======
>>>>>>> branch 'master' of https://github.com/comp55/group-project-stacked_overflow.git
		if(getLocalCurrRoom() == null) {
			resetUserRoom();
		}
		
		baseInit(getLocalCurrRoom() , floorWeOn);
		
		 //return string of map we on
		//System.out.println(floorWeOn);

		/*if(enemies.size() > 0) {
			for(Enemy enemy : enemies) {
				enemy.move();
			}
		}*/

		if(floorWeOn == "map_base1") {
			if(getLocalCurrRoom() == null) {
				resetRoom();
			}
		}
<<<<<<< HEAD

=======

		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).tick();
			enemies.get(i).setStartX((int)enemies.get(i).getCoordX()+5);
			enemies.get(i).setStartY(0);
			//Timer timer = new Timer(DELAY_MS, null);
			//timer.start();
		}
>>>>>>> branch 'master' of https://github.com/comp55/group-project-stacked_overflow.git
	}

	//I'm not too sure why we need this but I won't remove it for the sake of someone testing
	public static void main(String[] args) {
		Console test = new Console();
		//test.enemies = test.getEnemies();
		//test.getNextRoom();
		//test.generateEnemies();
		/*for(int i = 0; i < test.enemies.size(); i++) {
			//Timer timer = new Timer(DELAY_MS, null);
			//timer.start();
			System.out.println("X: " + test.enemies.get(i).getCoordX() + " Y: " + test.enemies.get(i).getCoordY());
		}*/
		Enemy tempEnemy = new Enemy(5,5,5,5,5,5,ElementType.FIRE);
		HashMap<Enemy, Coordinates> enemyHash = test.getEnemyHash();
		test.enemies = test.getEnemies();
		
		for(HashMap.Entry test2 : enemyHash.entrySet()) {
			
			tempEnemy = (Enemy) test2.getKey();
			Coordinates tempCoord = enemyHash.get(test2.getKey());
			tempEnemy.tick();
			System.out.println("X: " + tempCoord.getX() + " Y: " + tempCoord.getY());
		}
	}

	/////////////////////////////// END OF PLAY GAME /////////////////////////////////////////////


	////////////////////////////// MOVEMENT AND INTERACTMENT ////////////////////////////////
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/comp55/group-project-stacked_overflow.git
	
	public void actionPerformed(KeyEvent ae) {
<<<<<<< HEAD

		if(gamePaused) { return; }
		/*if(canMove(ae)) {
			
		}
		else {
			
		}
		if(!canMove()) { return; }
		*/
=======
		
		//Checks for...
		if(gamePaused) { return; }		//Game being paused
		//checkEnemyAttack();				//Enemy attacks
		if(!getCanMove()) { return; }	//Interaction blocks
		
		//Now you can move!
>>>>>>> branch 'master' of https://github.com/comp55/group-project-stacked_overflow.git
		user.tick();
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/comp55/group-project-stacked_overflow.git
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).tick();
			//Timer timer = new Timer(DELAY_MS, null);
			//timer.start();
		}

	}

	public void keyPressedManager(KeyEvent e) {
		
		keyInput = e.getKeyCode();

		switch(keyInput) {
		case KeyEvent.VK_W:
			user.setDY(-user.getMoveSpeedStat());
			setCanMove(e);
			getNextRoom();
			keyDown[0] = true;
			break;
		case KeyEvent.VK_A:
			user.setDX(-user.getMoveSpeedStat());
			setCanMove(e);
			getNextRoom();
			keyDown[1] = true;
			break;
		case KeyEvent.VK_S:
			user.setDY(user.getMoveSpeedStat());
			setCanMove(e);
			getNextRoom();
			keyDown[2] = true;
			break;
		case KeyEvent.VK_D:
			user.setDX(user.getMoveSpeedStat());
			setCanMove(e);
			getNextRoom();
			keyDown[3] = true;
			break;
		case KeyEvent.VK_E:
			keyDown[4] = true;
			user.cycleWeapon();
			break;
			//TODO fix this method, moves the user when changing weapons and shouldn't
		case KeyEvent.VK_UP:
			// handle up 
			break;
		case KeyEvent.VK_LEFT:
			// handle left
			break;
		case KeyEvent.VK_RIGHT :
			// handle right
			generateHitbox(e);
			break;
			keyDown[6] = true;
			generateHitbox(e);
			break;
		case KeyEvent.VK_DOWN:
			keyDown[7] = true;
			generateHitbox(e);
			break;
		default:
			break;
		}

		actionPerformed(e);

	}

	public void keyReleasedManager(KeyEvent e) {

		keyInput = e.getKeyCode();
		
		if(keyInput == KeyEvent.VK_W) {
			keyDown[0] = false;
		}
		if(keyInput == KeyEvent.VK_A) {
			keyDown[1] = false;
		}
		if(keyInput == KeyEvent.VK_S) {
			keyDown[2] = false;
		}
		if(keyInput == KeyEvent.VK_D) {
			keyDown[3] = false;
		}
		if(keyInput == KeyEvent.VK_E) {
			keyDown[4] = false;
		}
		if(keyInput == KeyEvent.VK_UP) {
			keyDown[5] = false;		
		}
		if(keyInput == KeyEvent.VK_LEFT) {
			keyDown[6] = false;
		}
		if(keyInput == KeyEvent.VK_DOWN) {
			keyDown[7] = false;
		}
		if(keyInput == KeyEvent.VK_RIGHT) {
			keyDown[8] = false;
		}
		
		if(keyDown[0] && !keyDown[2]) {
			user.setDY(-user.getMoveSpeedStat());
		}
		if(!keyDown[0] && keyDown[2]) {
			user.setDY(user.getMoveSpeedStat());
		}
		if(!keyDown[0] && !keyDown[2]) {
			user.setDY(0);
		}
		
		
		if(keyDown[1] && !keyDown[3]) {
			user.setDX(-user.getMoveSpeedStat());
		}
		if(!keyDown[1] && keyDown[3]) {
			user.setDX(user.getMoveSpeedStat());
		}
		if(!keyDown[1] && !keyDown[3]) {
			user.setDX(0);
		}
		/*switch(keyInput) {
		case KeyEvent.VK_W:
			keyDown[0] = false;
			break;
		case KeyEvent.VK_A:
			keyDown[1] = false;
			break;
		case KeyEvent.VK_S:
			keyDown[2] = false;
			break;
		case KeyEvent.VK_D:
			keyDown[3] = false;
			break;
		default:
			break;
		}*/

	}

	public void moveEnemy(Enemy enemy) {
	
	/*public void moveEnemy(Enemy enemy) {
>>>>>>> branch 'master' of https://github.com/comp55/group-project-stacked_overflow.git
		for(Enemy enemy2 : enemies) {
			enemy2.move();
		}
<<<<<<< HEAD
		enemy.move(user);
	}

=======
		enemy.move();
	}*/
<<<<<<< HEAD
	}
	public boolean canMove() {
		//TODO have some boundary checks called in here
		return true;
=======
	
	public void canMove() {};
	
	public void setCanMove(KeyEvent e) {
		
		keyInput = e.getKeyCode();
		Coordinates foundInteractionCoordinates = new Coordinates();
		boolean foundInteractionBool = false;
		
		for(HashMap.Entry<Interactions, Coordinates> test : interactionHash.entrySet()) {
			
			Coordinates tempCoord = interactionHash.get(test.getKey());
			
			//UPWARDS MOVEMENT
			if(keyInput == KeyEvent.VK_W) {
				
				//X CHECK
					//In front of user's leftmost point		//Behind user's rightmost point
				if((tempCoord.getX() > user.getCoordX()) && (tempCoord.getX() < user.getCoordX() + 75)) {
					//Y checks
					//Interaction is above user					//Less than a move away
					if((tempCoord.getY() > user.getCoordY()) && (tempCoord.getY() < (user.getCoordY() - user.getMoveSpeedStat()))) {
						foundInteractionCoordinates = tempCoord;
					}
				}
				
				
			//LEFT MOVEMENT
			} else if(keyInput == KeyEvent.VK_A) {
				
				//Y CHECK
				//Below user's topmost point				//Above user's lowest point
				if((tempCoord.getY() < user.getCoordY()) && (tempCoord.getY() > user.getCoordY() + 75)) {
					//X checks
					//Interaction is "behind" user					//Less than a move away
					if((tempCoord.getX() < user.getCoordX()) && (tempCoord.getX() > (user.getCoordX() - user.getMoveSpeedStat()))) {
						foundInteractionCoordinates = tempCoord;
					}
				}	
				
			//DOWNWARDS MOVEMENT
			} else if(keyInput == KeyEvent.VK_S) {
				
				//X CHECK
					//In front of user's leftmost point		//Behind user's rightmost point
				if((tempCoord.getX() > user.getCoordX()) && (tempCoord.getX() < user.getCoordX() + 75)) {
					//Y checks
					//Interaction is below user							//Less than a move away
					if((tempCoord.getY() < (user.getCoordY() + 75)) && (tempCoord.getY() > ((user.getCoordY() + 75) + user.getMoveSpeedStat()))) {
						foundInteractionCoordinates = tempCoord;
					}
				}
			
			//RIGHT MOVEMENT
			} else if(keyInput == KeyEvent.VK_D) {
				
				//Y CHECK
				//Below user's topmost point				//Above user's lowest point
				if((tempCoord.getY() < user.getCoordY()) && (tempCoord.getY() > user.getCoordY() + 75)) {
					//X checks
					//Interaction is in front of user					//Less than a move away
					if((tempCoord.getX() > (user.getCoordX() + 75)) && (tempCoord.getX() < ((user.getCoordX() + 75) + user.getMoveSpeedStat()))) {
						foundInteractionCoordinates = tempCoord;
					}
				}				
				
			}

		}
		
		//This final check goes through our dedicated ArrayList of entries and sees if the
		//coordinates match between the encountered interaction. If the two match up, that means
		//the encountered interaction is an entry, and that the user should move. If not, it is
		//either a rock or hole, and the user cannot move.
		if(foundInteractionBool) {
			for(int i = 0; i < entries.size(); i++) {
				if(foundInteractionCoordinates == entries.get(i)) {
					canMove = true;
					return;
				}
			}
			canMove = false;
		} else {			
			//If no interaction is found, then cool- we can move regardless.
			canMove = true;
		}
	}
		
	//Stan's canMove(), conflicted with merge. Given new name for now, we'll discuss the 
	//purpose of the function and all that later.
	public boolean stanCanMove() {
		//TODO have some boundary checks called in here		
		Enemy tempEnemy = new Enemy();
		for(HashMap.Entry enemy : enemyHash.entrySet()) {
			
			tempEnemy = (Enemy) enemy.getKey();
			Coordinates tempCoord = enemyHash.get(enemy.getKey());
			tempCoord.setX(tempCoord.getX() + 5);
		}
		//enemyStats.setCoordX(enemyStats.getCoordX() + dx);
	//	enemyStats.setCoordY(enemyStats.getCoordY() + dy);
		
		return true;
>>>>>>> branch 'master' of https://github.com/comp55/group-project-stacked_overflow.git
	}

	/////////////////////////// END OF MOVEMENT AND INTERACTMENT ////////////////////////////


	/////////////////////////// ROOM/MAP/FLOOR TRAVERSAL AND SETUP ///////////////////////////////

	public void baseInit(String currFloor) {
		resetRoom();
		generateRoom(currFloor);
	}
	
	
	/////////////////////////// COMBAT METHODS //////////////////////////////////////////////////
	
	//This will be called whenever a user wants to attack
	public void generateHitbox(KeyEvent e) {
		//TODO add checks for enemy within X/Y pixels in from of User depending on KeyEvent
		
		//FOR RIGHT NOW, THIS IS JUST THE SAME CHECK USED IN SETCANMOVE() TO CHECK FOR
		//INTERACTIONS, WITH A FEW VARIABLES CHANGED. WE CAN CHANGE THIS IN THE FUTURE, I
		//JUST HAVE THIS FOR NOW FOR THE SAKE OF HAVING SOMETHING TO BUILD ON TOP OF
		for(HashMap.Entry<Enemy, Coordinates> test : enemyHash.entrySet()) {
			
			Coordinates tempCoord = enemyHash.get(test.getKey());
			
			//UPWARDS ATTACK
			if(keyInput == KeyEvent.VK_UP) {
				
				//X CHECK
					//In front of user's leftmost point		//Behind user's rightmost point
				if((tempCoord.getX() > user.getCoordX()) && (tempCoord.getX() < user.getCoordX() + 75)) {
					//Y checks
					//Enemy is above user					//Less than a hit away
					if((tempCoord.getY() > user.getCoordY()) && (tempCoord.getY() < (user.getCoordY() - 50))) {
						userDmgToEnemy((Enemy)test.getKey());
					}
				}
				
				
			//LEFT ATTACK
			} else if(keyInput == KeyEvent.VK_LEFT) {
				
				//Y CHECK
				//Below user's topmost point				//Above user's lowest point
				if((tempCoord.getY() < user.getCoordY()) && (tempCoord.getY() > user.getCoordY() + 75)) {
					//X checks
					//Enemy is "behind" user					//Less than a hit away
					if((tempCoord.getX() < user.getCoordX()) && (tempCoord.getX() > (user.getCoordX() - 50))) {
						userDmgToEnemy((Enemy)test.getKey());
					}
				}	
				
			//DOWNWARDS ATTACK
			} else if(keyInput == KeyEvent.VK_DOWN) {
				
				//X CHECK
					//In front of user's leftmost point		//Behind user's rightmost point
				if((tempCoord.getX() > user.getCoordX()) && (tempCoord.getX() < user.getCoordX() + 75)) {
					//Y checks
					//Enemy is below user							//Less than a hit away
					if((tempCoord.getY() < (user.getCoordY() + 75)) && (tempCoord.getY() > ((user.getCoordY() + 75) + 50))) {
						userDmgToEnemy((Enemy)test.getKey());
					}
				}
			
			//RIGHT ATTACK
			} else if(keyInput == KeyEvent.VK_RIGHT) {
				
				//Y CHECK
				//Below user's topmost point				//Above user's lowest point
				if((tempCoord.getY() < user.getCoordY()) && (tempCoord.getY() > user.getCoordY() + 75)) {
					//X checks
					//Enemy is in front of user					//Less than a hit away
					if((tempCoord.getX() > (user.getCoordX() + 75)) && (tempCoord.getX() < ((user.getCoordX() + 75) + 50))) {
						userDmgToEnemy((Enemy)test.getKey());
					}
				}				
				
			}

		}
		
	}

	public void generateRoom(String currFloor) {

		if(getLocalCurrRoom() == null) {
			resetUserRoom();
		}
	}
			
	//This will be called inside generateHitbox if an enemy is detected within the attack range
	public int userDmgToEnemy(Enemy enemyBeingAttacked) {
		Weapon tempSword = user.getCurWeapon();
		int attackBoost = 0;
		
		//Checks for elemental damage boosts
		if((tempSword.getWepType() == ElementType.FIRE) && (enemyBeingAttacked.getEnemyType() == ElementType.EARTH)) {
			attackBoost += 1;
		} else if((tempSword.getWepType() == ElementType.WATER) && (enemyBeingAttacked.getEnemyType() == ElementType.FIRE)) {
			attackBoost += 1;
		} else if((tempSword.getWepType() == ElementType.EARTH) && (enemyBeingAttacked.getEnemyType() == ElementType.WATER)) {
			attackBoost += 1;
		}

		System.out.println("Current level: " + getCurrFloor());
		System.out.println("Current room: " + getLocalCurrRoom());

		map.runRunBase(getLocalCurrRoom(), floor, map, interactionHash, enemyHash, entries, enteredEntriesHash, bossHash);
		//getNextRoom();

		
		//TODO Potentially add elemental damage debuffs? (Like if a user attacks a water enemy with fire
		
		return user.getUserStats().getBaseDamage() + attackBoost;
		
	}
	
	public void checkEnemyAttack() {
		
		for(HashMap.Entry<Enemy, Coordinates> test : enemyHash.entrySet()) {
			
			Coordinates tempCoord = enemyHash.get(test.getKey());
			
				//TODO these aren't accurate at all, fix this in future
				if((tempCoord.getY() < user.getCoordY()) && (tempCoord.getY() > user.getCoordY() + 75)) {
					if((tempCoord.getX() > (user.getCoordX() + 75)) && (tempCoord.getX() < ((user.getCoordX() + 75) + 50))) {
						enemyDmgToUser(test.getKey());
					}
				}
				
		}
		
	}
	
	//This will be called when a user is detected to be touching an enemy
	public void enemyDmgToUser(Enemy enemyAttacking) {
		user.getUserStats().setHP_cur(user.getUserStats().getHP_cur() - enemyAttacking.getEnemyStats().getBaseDamage());
	}
	
	/////////////////////////// END OF COMBAT METHODS ///////////////////////////////////////////
	
	
	
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
		double coordX = user.getCoordX();
		double coordY = user.getCoordY();
		//ArrayList<Coordinates> tempArrayList = new ArrayList<Coordinates>();
		//tempArrayList = getEntries();
		HashMap<String,ArrayList<Coordinates>> tempHash = getEntriesHash();
		Set<String> tempKeys = tempHash.keySet();
		Iterator<String> tempKeysIter = tempKeys.iterator();
		while(tempKeysIter.hasNext()) 
		{
			String tempKey = tempKeysIter.next();
			ArrayList<Coordinates> tempCoord = tempHash.get(tempKey);

			for(int i = 0; i <= tempCoord.size() - 1; i++) 
			{
				double temp1 = tempCoord.get(i).getX();
				double temp2 = tempCoord.get(i).getY();

				// TODO check pixel range of player instead of that single point
				if (coordX >= temp1 && coordY >= temp2 - 250 && coordX <= temp1 + 50
						&& coordY <= temp2 + 500 ) 
				{
					//TODO this method is only friendly to rooms with doors on the left or right, not up or down, find way to 
					//detect x-coordinates as explicitly as the y-coordinates
					System.out.println("Detected user in the gRect!");
					System.out.println("The name of the gRect the user is in is: " + tempKey);
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

				}break;
			}break;
					
						layout.setEntryAmountBasedonLayout(floor.whatMapWeOn(floor.getLevelCounter()));
						temp = layout.getEntryAmountofLayout();
						layout.setEntryAmount(temp);
						layout.setMapHash(floor.whatMapWeOn(floor.getLevelCounter()));
						mapHashCurrEntry = layout.getMapHash(floor.whatMapWeOn(floor.getLevelCounter()));
						
						String nextEntry = mapHashCurrEntry.get(tempKey);
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
<<<<<<< HEAD
		}
	}


	private void removeAll() {
		// TODO Auto-generated method stub
		
=======
>>>>>>> branch 'master' of https://github.com/comp55/group-project-stacked_overflow.git
	}
	
	//////////////////////// END OF ROOM/MAP/FLOOR TRAVERSAL AND SETUP ///////////////////////////////

}
