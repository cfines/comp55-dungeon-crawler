//This the map class that takes in the values of map layout and room, 
//spits out the combination, 
//and leads to something beautiful
package projectFiles;

import java.util.ArrayList;
import java.util.HashMap;

public class Map{
	
	//instance variables
	private static MapLayout type;
	private static Enemy badGuy;
	private static Boss biggerBadGuy;
	private static Room curRoom;
	private static ArrayList<Room> roomList = new ArrayList<Room>();
	private static ArrayList<Coordinates> roomSpawns = new ArrayList<Coordinates>();
	private static HashMap <Room, ArrayList<Coordinates>> randSpawns = new HashMap<Room, ArrayList<Coordinates>>();
	private static HashMap <Interactions, ArrayList<Room>> roomReact = new HashMap<Interactions,ArrayList<Room>>();
	private static HashMap <Enemy, ArrayList<Coordinates>> enemySpawn = new HashMap <Enemy, ArrayList<Coordinates>>();
	private static HashMap <Boss, ArrayList<Coordinates>> bossSpawn = new HashMap <Boss, ArrayList<Coordinates>>();
	//TODO think of some alternative to put enemies onto a room and avoid making a 
	//HashMap <Room, HashMap<Enemy, ArrayList<Coordinates> thing
	
	//basic constructor
	Map()
	{}
	
	//sets the interaction to a room
	public static void setRoomInteractions(Interactions react, ArrayList<Room> r) 
	{
		roomReact.put(react, r);
	}
	
	//gets the hash map of the room interactions
	public static HashMap<Interactions, ArrayList<Room>> getRoomInteractions()
	{
		return roomReact;
	}
	
	//adds values to the array list
	public static void setRoomSpawns(Coordinates random, ArrayList<Coordinates> roomSpawns)
	{
		roomSpawns.add(random);
	}
	
	//returns coordinates from array list
	public static void getRoomSpawns() 
	{	
		for(int i = 0; i < roomSpawns.size();i++) 
		{
			roomSpawns.get(i).getX();
		}
		for(int j = 0; j < roomSpawns.size(); j++) 
		{
			roomSpawns.get(j).getY();
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
	
	//sets the rooms into the array list of type room
	public static void setRoomList(ArrayList<Room> roomList) {
		roomList.add(curRoom);
	}
	
	//retrieves the rooms from the array list of type room
	public static ArrayList<Room> getRoomList() {
		return roomList;
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
	public static HashMap <Enemy, ArrayList<Coordinates>> getEnemySpawn() {
		return enemySpawn;
	}
	
	// sets enemy onto an array list of coordinates
	public static void setEnemySpawn(HashMap <Enemy, ArrayList<Coordinates>> enemySpawn) {
		enemySpawn.put(badGuy, roomSpawns);
	}
	
	// gets coordinates from the boss
	public static HashMap <Boss, ArrayList<Coordinates>> getBossSpawn() {
		return bossSpawn;
	}
	
	//sets boss onto an array list of coordinates
	public static void setBossSpawn(HashMap <Boss, ArrayList<Coordinates>> bossSpawn) {
		bossSpawn.put(biggerBadGuy, roomSpawns);
	}
	//TODO fix the other functions to properly do what they need to do
	
	//big boi testing right here
	public static void main (String args[]) 
	{
		
		//test string to see if this actually wants to work for me
		System.out.println("Sample test for Coordinate class compatibility with Map\n");
		
		//test successful
		Coordinates test1 = new Coordinates(50,76);
		Coordinates test2 = new Coordinates(60,88);
		setRoomSpawns(test1, roomSpawns);
		getRoomSpawns();
		System.out.println("Now testing with another variable being added, will it save both?");
		setRoomSpawns(test2, roomSpawns);
		System.out.println("It should be added if this text is even appearing, let us see the info again."
			+ " Did it change?\n");  
		getRoomSpawns();
		
	}

}