//This the map class that takes in the values of map layout and room, 
//spits out the combination, 
//and leads to something beautiful
package starter;

import java.util.ArrayList;
import java.util.HashMap;

public class Map{
	
	//instance variables
	private MapLayout type;
	private MapRooms rooms;
	private Floor floor;
	private Enemy badGuy;
	private Boss biggerBadGuy;
	//private Room curRoom = new Room();
	private Interactions inter;
	//private HashMap <Room, ArrayList<Coordinates>> randSpawns = new HashMap<Room, ArrayList<Coordinates>>();
	private HashMap <Interactions, Coordinates> reaction = new HashMap<Interactions, Coordinates>();
	private HashMap <Enemy, Coordinates> enemySpawn = new HashMap <Enemy, Coordinates>();
	private HashMap <Boss, Coordinates> bossSpawn = new HashMap <Boss, Coordinates>();
	private ArrayList<Coordinates> enteredEntries = new ArrayList<Coordinates>();
	private HashMap<String, ArrayList<Coordinates>> enteredEntriesHash = new HashMap<String, ArrayList<Coordinates>>();
	
	private Hardcoded bruhMoment = new Hardcoded();
	
	//basic constructor
	Map(){}
	
	///////////////////////////////////////////
	
	
	public void setEnteredEntries(String entryID, Coordinates entry, ArrayList<Coordinates> passBy1, HashMap <String, ArrayList<Coordinates>> passBy2) {
		passBy1.add(entry);
		ArrayList<Coordinates> temp = passBy1;
		temp.add(entry);
		passBy2.put(entryID, temp);		
	}
	
	public ArrayList<Coordinates> getEnteredEntries() {
		return enteredEntries;		
	}
	
	public HashMap<String, ArrayList<Coordinates>> getEnteredEntriesHash(){
		return enteredEntriesHash;
	}
	
	
	
	///////////////////////////////////////////
	
	public String whatMapWeOn() {
		return floor.whatMapWeOn(floor.getLevelCounter());
	}
	
	//sets the interaction to a room
	public void setInteractions(Interactions react, Coordinates r, HashMap <Interactions, Coordinates> interactationPassbyHash) 
	{
		interactationPassbyHash.put(react, r);
	}
	
	//gets the hash map of the room interactions
	public HashMap<Interactions, Coordinates> getInteractions()
	{
		return reaction;
	}
	
	public HashMap<Enemy, Coordinates> getEnemies()
	{
		return enemySpawn;
	}
	
	public HashMap<Boss, Coordinates> getBoss()
	{
		return bossSpawn;
	}
	
	//returns x-coordinates from enemy
	public int getEnemyRoomX() 
	{	
		return enemySpawn.get(badGuy).getX();
	}
	
	//returns y-coordinates from enemy
	public int getEnemyRoomY()
	{
		return enemySpawn.get(badGuy).getY();
	}
		
	//returns x-coordinates from boss
	public int getBossRoomX() 
	{	
		return bossSpawn.get(biggerBadGuy).getX();
	}
	
	//returns y-coordinates from boss
	public int getBossRoomY()
	{		
		return bossSpawn.get(biggerBadGuy).getY();
	}
	
	
	
	//Not sure if we really need the 2 functions down below as one recursively calls Map to be set into the same thing
	//While the other just returns what was already made
	
	// sets the random spawns into a hash map that uses the room as a key and roomSpawns as the value
	//public static void setRandSpawns(HashMap <Room, ArrayList<Coordinates>> randSpawns) {
	//	Map.randSpawns = randSpawns;
	//}
	
	// returns the random spawns into a hash map that uses the room as a key and roomSpawns as the value
	//public static HashMap <Room, ArrayList<Coordinates>> getRandSpawns() {
	//	return randSpawns;
	//}
	
	// gets coordinates from the enemy
	public HashMap<Enemy, Coordinates> getEnemySpawn() {
		return enemySpawn;
	}
	
	// sets enemy onto an array list of coordinates
	public void setEnemySpawn(Enemy enemy, Coordinates h,  HashMap <Enemy, Coordinates> eee) {
		eee.put(enemy, h);
	}
	
	// gets coordinates from the boss
	public HashMap<Boss, Coordinates> getBossSpawn() {
		return bossSpawn;
	}
	
	//sets boss onto an array list of coordinates
	public void setBossSpawn(Boss boss, Coordinates h, HashMap <Boss,Coordinates> bbb) 
	{
		bbb.put(boss, h);
	}
	
	public Coordinates getCoordinateFromString(String hashKey) {
		
		Coordinates correctCoordinate = new Coordinates();
		correctCoordinate = reaction.get(hashKey);
		
		return correctCoordinate;
		
	}
	
	public void runRunBase(String userRoomPosition, Floor curFloor,  Map bruhMap, HashMap <Interactions, Coordinates> h, HashMap <Enemy, Coordinates> eee, 
			ArrayList<Coordinates> enteredEntries, HashMap<String,ArrayList<Coordinates>> enteredEntriesHash, HashMap<Boss,Coordinates> bossHash) {
		
		bruhMoment.runBase(userRoomPosition, curFloor, bruhMap, h, eee, enteredEntries, enteredEntriesHash,bossHash);
		
	}
	
	/*
	public void test() 
	{
		Coordinates danger = new Coordinates(300,500);
		Coordinates inTheWay = new Coordinates (250,700);
		setEnemySpawn(badGuy, danger, enemySpawn);
		inter = new Interactions(interactionType.obstacle_rock);
		setInteractions(inter, inTheWay, reaction);
		System.out.println(getEnemySpawn());
		getEnemyRoomX();
	}
	*/
	
	//big boi testing right here
	public static void main (String args[]) 
	{
		Map mep = new Map();
		//mep.test();
		//test string to see if this actually wants to work for me
//		System.out.println("Sample test for Coordinate class compatibility with Map\n");
		
		//test successful
//		Coordinates test1 = new Coordinates(50,76);
//		Coordinates test2 = new Coordinates(60,88);
//		setRoomSpawns(test1, roomSpawns);
//		getRoomSpawns();
//		System.out.println("Now testing with another variable being added, will it save both?");
//		setRoomSpawns(test2, roomSpawns);
//		System.out.println("It should be added if this text is even appearing, let us see the info again."
//			+ " Did it change?\n");  
//		getRoomSpawns();
		
	}


}