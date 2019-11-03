package projectFiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

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
		HashMap<Interactions,Coordinates> ree = new HashMap<Interactions,Coordinates>();
		Map map = new Map();
		Interactions inter = new Interactions(interactionType.obstacle_rock);
		map.setInteractions(inter, inTheWay, ree);
		System.out.println("The current room the user is in is: " + currMapTest);
		System.out.println("There is an enemy that is of type: " + badGuy.getEnemyType());
		//The functions commented out only work in the Map class and nowhere else, not even here
		map.setEnemySpawn(badGuy, dang, eS);	
		System.out.println("This enemy is located on: \nX = " + eS.get(badGuy).getX());
		//System.out.println(map.getEnemyRoomX());
		System.out.println("Y = " + eS.get(badGuy).getY());
		//System.out.println(map.getEnemyRoomY());
		System.out.println("There is an interaction that is of this type: " + inter.getinteractionType());
		System.out.println("This interaction is located on: X = " + ree.get(inter).getX() + " Y = " + ree.get(inter).getY());
		System.out.println("\nNow testing for 2!\n");
		Enemy thirteenPercent = new Enemy(50,99,44,22,11,69,ElementType.EARTH);
		Coordinates majorityOfCrime = new Coordinates(69,420);
		map.setEnemySpawn(thirteenPercent, majorityOfCrime, eS);
		System.out.println("This new enemy is of type: " + thirteenPercent.getEnemyType());
		System.out.println("This new enemy is located on: \nX = " + eS.get(thirteenPercent).getX());
		System.out.println("X = " + eS.get(thirteenPercent).getY());
		Interactions interracial = new Interactions(interactionType.obstacle_wallShort);
		Coordinates bruhItsInTheWay = new Coordinates(77,777);
		map.setInteractions(interracial, bruhItsInTheWay, ree);
		System.out.println("There is now a new interaction. It is of type: " + interracial.getinteractionType());
		System.out.println("This interaction is located on: X = " + ree.get(interracial).getX() + " Y = " + ree.get(interracial).getY());
		
		HashMap<String, String> mapHashCurrMap;
		int temp;
		MapLayout layout = new MapLayout();
		layout.setEntryAmountBasedonLayout(currMapTest);
		temp = layout.getEntryAmountofLayout();
		layout.setEntryAmount(temp);
		layout.setMapHash(currMapTest);
		mapHashCurrMap = layout.getMapHash(currMapTest);
		
		System.out.println("\nThe current map (which would be recieved from Floor) is 'map_base1'.");
		System.out.println("The amount of entries is: " + temp);
		System.out.println("The size of the ArrayList for entries is: " + layout.getEntryAmount().size());
		System.out.println("The player enters E1 of 'map_base1' and should exit out of E2. Does it?: " + mapHashCurrMap.get(layout.getEntryAmount().get(0)));
		System.out.println("The player enters E2 of 'map_base1' and should exit out of E1. Does it?: " + mapHashCurrMap.get(layout.getEntryAmount().get(1)));
		System.out.println("The player enters E5 of 'map_base1' and should exit out of E6. Does it?: " + mapHashCurrMap.get(layout.getEntryAmount().get(4)));
		System.out.println("The player enters E6 of 'map_base1' and should exit out of E5. Does it?: " + mapHashCurrMap.get(layout.getEntryAmount().get(5)));
		
		int temp2;
		MapRooms rooms = new MapRooms();
		HashMap<String, String> mapHashCurrMap2;
		rooms.setRoomAmountBasedonLayout(currMapTest);
		temp2 = rooms.getRoomAmountofLayout();
		rooms.setRoomAmount(temp2);
		rooms.setMapRoomHash(currMapTest);
		mapHashCurrMap2 = rooms.getMapRoomHash(currMapTest);
		
		System.out.println("\nCurrent map is 'map_base1'");
		System.out.println("The amount of entries is: " + temp2);
		System.out.println("The size of the ArrayList for entries is: " + rooms.getRoomAmount().size());
		System.out.println("User enters E1 and exits from E2. E2 should be in R2. Is it?: " + mapHashCurrMap2.get(rooms.getRoomAmount().get(1)));
		System.out.println("User enters E2 and exits from E1. E1 should be in R1. Is it?: " + mapHashCurrMap2.get(rooms.getRoomAmount().get(0)));
		
		
		ArrayList <String> entryArrayList = layout.getEntryAmount();
		ArrayList <String> roomArrayList = rooms.getRoomAmount();
		ArrayList<String> entriesInRoom = new ArrayList<String>();
		System.out.println("\nThe current room is R2. Which entries are in R2?");
		System.out.println("Entry amount size: " + layout.getEntryAmount());
		
		
		setEntryToRoom(currMapTest);
		for (int i = 0; i <= layout.getEntryAmount().size() - 1; i++) {
			String temp1 = layout.getEntryAmount().get(i);
			if(getMapBaseEtoR().get(temp1) == "R2") {
				entriesInRoom.add(temp1);
			}
		}
		
		for (int i = 0; i <= entriesInRoom.size() - 1; i++) {
			System.out.println("Entries: " + entriesInRoom.get(i));
		}
	}
	
	private static HashMap <String, String> mapBaseEtoR = new HashMap<String, String>();
	
	public static HashMap<String,String> getMapBaseEtoR() {
		return mapBaseEtoR;
	}
	
	public static void setEntryToRoom(String currLayout) {
		if(currLayout == "map_base1") {
			getMapBaseEtoR().put("E2", "R2"); 
			getMapBaseEtoR().put("E3", "R2"); //TBD
		}
		else if(currLayout == "map_fire") {
			//TBD
		}
		else if(currLayout == "map_water") {
			//TBD
		}
		else if(currLayout == "map_earth") {
			//TBD
		}
		else {
			//TBD
		} 
	}
	
	
	// roomLayout layout, HashMap<Enemy, Coordinates> Espawns,  HashMap <HashMap<Interactions, Coordinates>, Room> Ispawns
	
	Room(){}
	
	
	public static void main(String[] args) {
		Room test = new Room();
		test.testCase();
	}	
}
