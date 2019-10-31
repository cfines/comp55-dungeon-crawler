//This the map class that takes in the values of map layout and room, 
//spits out the combination, 
//and leads to something beautiful
package projectFiles;

import java.util.ArrayList;
import java.util.HashMap;

public class Map{
	
	//instance variables
	private static MapLayout type;
	private static Interactions entries;
	private static ArrayList<Room> roomList = new ArrayList<Room>();
	private	static Room curRoom;
	private static ArrayList<Coordinates> roomSpawns = new ArrayList<Coordinates>();
	private static HashMap <Room, ArrayList<Coordinates>> randSpawns = new HashMap<Room, ArrayList<Coordinates>>();
	private static HashMap <Interactions, Room> roomReact = new HashMap<Interactions,Room>();
	
	//basic constructor
	Map()
	{}
	
	//sets the interaction to a room
	public static void setRoomInteractions(Interactions react, Room r) 
	{
		roomReact.put(react, r);
	}
	
	//gets the hash map of the room interactions
	public static HashMap<Interactions, Room> getRoomInteractions()
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
			System.out.println("X Coordinate #" + i + " " + roomSpawns.get(i).getX());
		}
		for(int j = 0; j < roomSpawns.size(); j++) 
		{
			System.out.println("Y Coordinate #"+ j + " " + roomSpawns.get(j).getY());
		}
	}
	
	public static HashMap<String, String> getMapHash() 
	{
		return type.getMapHash();
	}
	
//	public static void setMapHash() 
	//{
	//	type.setMapHash();
	//}
	
	public static ArrayList<String> getEntryAmount()
	{
		return type.getEntryAmount();
	}
	
	//public static void setEntryAmount() 
	//{
	//	type.entryAmount();
	//}
	
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