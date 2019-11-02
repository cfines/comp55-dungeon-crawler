//This the map class that takes in the values of map layout and room, 
//spits out the combination, 
//and leads to something beautiful
package projectFiles;

import java.util.ArrayList;
import java.util.HashMap;

public class Map{
	
	//instance variables
	private static MapLayout type;
	private static MapRooms rooms;
	private static Enemy badGuy;
	private static Boss biggerBadGuy;
	private static Room curRoom;
	private static Coordinates location;
	private static Interactions inter;
	private static ArrayList<HashMap<Enemy,Coordinates>> enemyRoomSpawns = new ArrayList<HashMap<Enemy,Coordinates>>();
	private static ArrayList<HashMap<Boss,Coordinates>> bossRoomSpawns = new ArrayList<HashMap<Boss,Coordinates>>();
	private static HashMap <Room, ArrayList<Coordinates>> randSpawns = new HashMap<Room, ArrayList<Coordinates>>();
	private static HashMap <Interactions, Coordinates> reaction = new HashMap<Interactions, Coordinates>();
	private static HashMap <HashMap<Interactions, Coordinates>, Room> interToRoom = new HashMap <HashMap<Interactions, Coordinates>, Room>(); 
	private static HashMap <Enemy, Coordinates> enemySpawn = new HashMap <Enemy, Coordinates>();
	private static HashMap <Boss, Coordinates> bossSpawn = new HashMap <Boss, Coordinates>();
	
	//basic constructor
	Map()
	{
		
	}
	
	//sets the interaction to a room
	public static void setInteractions(Interactions react, Coordinates r) 
	{
		reaction.put(react, r);
	}
	
	//gets the hash map of the room interactions
	public static HashMap<Interactions, Coordinates> getInteractions()
	{
		return reaction;
	}
	
	//sets the interactions with coordinates to a room
	public static void setInteractionsToRoom(HashMap<Interactions, Coordinates> i, Room r) 
	{
		interToRoom.put(i,r);
	}
	
	//gets the interactions with coordinates from a room
	public static HashMap <HashMap<Interactions, Coordinates>, Room> getInteractionsFromRoom()
	{
		return interToRoom;
	}
	
	//adds enemy values to the array list
	public static void setEnemyRoomSpawns(HashMap<Enemy, Coordinates> random, ArrayList<HashMap<Enemy, Coordinates>> roomSpawns)
	{
		roomSpawns.add(random);
	}
	
	//returns coordinates from enemy
	public static void getEnemyRoomSpawns() 
	{	
		for(int i = 0; i < enemyRoomSpawns.size();i++) 
		{
			enemyRoomSpawns.get(i).get(enemySpawn).getX();
		}
		for(int j = 0; j < enemyRoomSpawns.size(); j++) 
		{
			enemyRoomSpawns.get(j).get(enemySpawn).getY();
		}
	}
	//adds boss values to the array list
	public static void setBossRoomSpawns(HashMap<Boss, Coordinates> random, ArrayList<HashMap<Boss, Coordinates>> bossRoom)
	{
		bossRoom.add(random);
	}
		
	//returns coordinates from boss
	public static void getBossRoomSpawns() 
	{	
			for(int i = 0; i < bossRoomSpawns.size();i++) 
			{
				bossRoomSpawns.get(i).get(bossSpawn).getX();
			}
			for(int j = 0; j < enemyRoomSpawns.size(); j++) 
			{
				bossRoomSpawns.get(j).get(bossSpawn).getY();
			}
		}
	
	public static HashMap<String, String> getMapHash(String currLayout) 
	{
		return type.getMapHash(currLayout);
	}
	
	public static ArrayList<String> getEntryAmount()
	{
		return type.getEntryAmount();
	}
	
	public static HashMap<String, String> getMapRoomHash(String currLayout)
	{
		return rooms.getMapRoomHash(currLayout);
	}
	
	public static ArrayList<String> getRoomAmount()
	{
		return rooms.getRoomAmount();
	}

	// sets the random spawns into a hash map that uses the room as a key and roomSpawns as the value
	public static void setRandSpawns(HashMap <Room, ArrayList<Coordinates>> randSpawns) {
		Map.randSpawns = randSpawns;
	}
	
	// returns the random spawns into a hash map that uses the room as a key and roomSpawns as the value
	public static HashMap <Room, ArrayList<Coordinates>> getRandSpawns() {
		return randSpawns;
	}
	
	// gets coordinates from the enemy
	public static HashMap<Enemy, Coordinates> getEnemySpawn() {
		return enemySpawn;
	}
	
	// sets enemy onto an array list of coordinates
	public static void setEnemySpawn(Enemy enemy, Coordinates h) {
		enemySpawn.put(badGuy, location);
	}
	
	// gets coordinates from the boss
	public static HashMap<Boss, Coordinates> getBossSpawn() {
		return bossSpawn;
	}
	
	//sets boss onto an array list of coordinates
	public static void setBossSpawn(Boss boss, Coordinates h) {
		bossSpawn.put(biggerBadGuy, location);
	}
	//TODO fix the other functions to properly do what they need to do
	
	public static void test(String args[]) 
	{
		Coordinates danger = new Coordinates(300,500);
		Coordinates inTheWay = new Coordinates (250,700);
		setEnemySpawn(badGuy, danger);
		setEnemyRoomSpawns(enemySpawn,enemyRoomSpawns);	
		inter = new Interactions(interactionType.obstacle_rock);
		setInteractions(inter, inTheWay);
		setInteractionsToRoom(reaction, curRoom);
		
	}
	
	//big boi testing right here
	public static void main (String args[]) 
	{
		
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