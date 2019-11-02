package projectFiles;

import java.util.ArrayList;
import java.util.HashMap;

public class Room {
	private static roomLayout roomLayout;
	private static Map map = new Map();
	private static Interactions interactionClass;
	private static boolean isKeyRoom;
	private static int numEntries;
	private static ArrayList<Interactions> entries;
	private static ArrayList<Interactions> obstacles;
	private static HashMap<String,String> entryPoints;
	private static ArrayList<String> entryAmount;
	
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
		return map.whatMapWeOn();
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
		return map.getMapHash(currLayout);
	}
	
	public static ArrayList<String> getEntryAmount(){
		return map.getEntryAmount();
	}
	
	public static HashMap<String, String> getMapRoomHash(String currLayout){
		return map.getMapRoomHash(currLayout);
	}
	
	public static ArrayList<String> getRoomAmount(){
		return map.getRoomAmount();
	}
	
	//TODO change the functionality of this so that it works in room because
	//I just realized that it makes no sense to have a function that gets interactions from a room
	//from map
	
	//public static HashMap <HashMap<Interactions, Coordinates>, Room> getInteractionsFromRoom(){
	//	return Map.getInteractionsFromRoom();
	//}
	
	public static HashMap<Enemy, Coordinates> getEnemySpawn(){
		return map.getEnemySpawn();
	}
	
	public static HashMap<Boss, Coordinates> getBossSpawn(){
		return map.getBossSpawn();
	}
	
	public static void testCase() {
		String currMapTest = "map_base1";
		Coordinates dang = new Coordinates(30,50);
		Coordinates inTheWay = new Coordinates (25,70);
		Enemy badGuy = new Enemy(1, 1, 1, 1, 1, 1, ElementType.FIRE);
		HashMap <Enemy, Coordinates> eS = new HashMap <Enemy, Coordinates>();
		ArrayList<HashMap<Enemy,Coordinates>> enemyRoomSpawns = new ArrayList<HashMap<Enemy,Coordinates>>();
		HashMap<Interactions,Coordinates> ree = new HashMap<Interactions,Coordinates>();
		Map map = new Map();
		Interactions inter = new Interactions(interactionType.obstacle_rock);
		map.setInteractions(inter, inTheWay, ree);
		System.out.println("The current room the user is in is: " + currMapTest);
		System.out.println("There is an enemy that is of type: " + badGuy.getEnemyType());
		
		//The functions commented out only work in the Map class and nowhere else, not even here
		map.setEnemySpawn(badGuy, dang, eS);
		map.setEnemyRoomSpawns(eS,enemyRoomSpawns);	
		System.out.println("This enemy is located on: \nX = " + eS.get(badGuy).getX());
		//System.out.println(map.getEnemyRoomX());
		System.out.println("\nY = " + eS.get(badGuy).getY());
		//System.out.println(map.getEnemyRoomY());
		System.out.println("There is an interaction that is of this type: " + inter.getinteractionType());
		System.out.println("This interaction is located on: X = " + ree.get(inter).getX() + " Y = " + ree.get(inter).getY());
		System.out.println("Now testing for 2!\n");
		Enemy thirteenPercent = new Enemy(50,99,44,22,11,69,ElementType.EARTH);
		Coordinates majorityOfCrime = new Coordinates(69,420);
		map.setEnemySpawn(thirteenPercent, majorityOfCrime, eS);
		map.setEnemyRoomSpawns(eS, enemyRoomSpawns);
		System.out.println("This new enemy is of type: " + thirteenPercent.getEnemyType());
		System.out.println("This new enemy is located on: \nX = " + eS.get(thirteenPercent).getX());
		System.out.println("\nX = " + eS.get(thirteenPercent).getY());
		Interactions interracial = new Interactions(interactionType.obstacle_wallShort);
		Coordinates bruhItsInTheWay = new Coordinates(77,777);
		map.setInteractions(interracial, bruhItsInTheWay, ree);
		System.out.println("There is now a new interaction. It is of type: " + interracial.getinteractionType());
		System.out.println("This interaction is located on: X = " + ree.get(interracial).getX() + " Y = " + ree.get(interracial).getY());
	}
	
	// roomLayout layout, HashMap<Enemy, Coordinates> Espawns,  HashMap <HashMap<Interactions, Coordinates>, Room> Ispawns
	
	Room(){}
	
	
	public static void main(String[] args) {
		Room test = new Room();
		test.testCase();
	}	
}
