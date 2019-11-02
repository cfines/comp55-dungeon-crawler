package projectFiles;

import java.util.ArrayList;
import java.util.HashMap;

public class Room {
	private static roomLayout roomLayout;
	private static Map map;
	private static Interactions interactionClass;
	private static boolean isKeyRoom;
	private static int numEntries;
	private static ArrayList<Interactions> entries;
	private static ArrayList<Interactions> obstacles;
	private static HashMap<String,String> entryPoints;
	private static ArrayList<String> entryAmount;
	private static ArrayList<HashMap<Enemy,Coordinates>> enemyRoomSpawns = new ArrayList<HashMap<Enemy,Coordinates>>();
	private static HashMap<Enemy, Coordinates> enemySpawn = new HashMap<Enemy, Coordinates>();
	private static HashMap<Interactions, Coordinates> reaction = new HashMap<Interactions, Coordinates>();
	
	// gets the room layout from the roomLayout enum
	public roomLayout getRoomLayout() {
		return roomLayout;
	}
	
	/* TODO: connect to the Map class in order to recieve which room of which mapLayout the user is currently on*/
	
	/*TODO: Create room constructor. Parameters will be 
	 * - number of entries
	 * - spawn coordinates and interactions
	 * - room layout
	 * - powerups*/
	
	/*TODO: create hashmap where the key will be the name of an entry point (E1, E2, E3 etc) that will 
	 * return the room these entry points will be in. Remember to name each gRect what will be an entry
	 * the proper entry name (E1, E2, E3, etc)*/
	
	public static String whatMapWeOn() {
		return Map.whatMapWeOn();
	}
	
	public static ArrayList<Interactions> getEntries(){
		return entries;
	}
	
	public static ArrayList<Interactions> getObstacles(){
		return obstacles;
	}
	
	// the parameters for getting the entries and obstacles are the amount of said entries and obstacles inside the specific room the player is in
	public void createNumEntries(int amount) {
		entries = new ArrayList<Interactions>(amount);
	}
	 
	public void createNumObsticles(int amount) {
		obstacles = new ArrayList<Interactions>(amount);
	}
	
	/*This function points to MapLayout and returns the HashMap of entries
	 * of the current map layout the user is in. What dictates the specific
	 * HashMap that will be returned will be based off of Floor that MapLayout
	 * will receive. It will get the hard coded entry points within the class
	 * and assign it to the HashMap that will be returned.*/
	public static HashMap<String,String> getMapHash(String currLayout) {
		//is this legal??
		return Map.getMapHash(currLayout);
	}
	
	public static ArrayList<String> getEntryAmount(){
		return Map.getEntryAmount();
	}
	
	public static HashMap<String, String> getMapRoomHash(String currLayout){
		return Map.getMapRoomHash(currLayout);
	}
	
	public static ArrayList<String> getRoomAmount(){
		return Map.getRoomAmount();
	}
	
	public static HashMap <HashMap<Interactions, Coordinates>, Room> getInteractionsFromRoom(){
		return Map.getInteractionsFromRoom();
	}
	
	public static HashMap<Enemy, Coordinates> getEnemySpawn(){
		return Map.getEnemySpawn();
	}
	
	public static HashMap<Boss, Coordinates> getBossSpawn(){
		return Map.getBossSpawn();
	}
	
	public static void testCase() {
		String currMapTest = "map_base1";
		Coordinates danger = new Coordinates(30,50);
		Coordinates inTheWay = new Coordinates (25,70);
		Enemy badGuy = new Enemy(1, 1, 1, 1, 1, 1, ElementType.FIRE);
		Room room = new Room();
		
		Map.setEnemySpawn(badGuy, danger);
		Map.setEnemyRoomSpawns(enemySpawn,enemyRoomSpawns);	
		Interactions inter = new Interactions(interactionType.obstacle_rock);
		Map.setInteractions(inter, inTheWay);
		Map.setInteractionsToRoom(reaction, room);
		
		System.out.println("The current room the user is in is: " + currMapTest);
		System.out.println("There is an enemy that is of type: " + badGuy.getEnemyType());
		System.out.println("This enemy is located on: X = \n");
		System.out.println(enemyRoomSpawns);
		
		
		//System.out.println(enemyRoomSpawns);
		//System.out.println("There is an interaction that is of this type: " + reaction.get(inter.getinteractionType()));
		//System.out.println("This interaction is located on: X = " + reaction.get(inTheWay.getX()) + " Y = " + reaction.get(inTheWay.getY()));
	}
	
	// roomLayout layout, HashMap<Enemy, Coordinates> Espawns,  HashMap <HashMap<Interactions, Coordinates>, Room> Ispawns
	
	Room(){}
	
	
	public static void main(String[] args) {
		Room test = new Room();
		test.testCase();
	}	
}
