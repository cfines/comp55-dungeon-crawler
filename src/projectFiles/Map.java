//This the map class that takes in the values of map layout and room, 
//spits out the combination, 
//and leads to something beautiful
package projectFiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map{
	
	//instance variables
	private MapLayout type;
	private Interactions entries;
	private ArrayList<Room> roomList = new ArrayList<Room>();
	private Room curRoom;
	private HashMap <Room, Coordinates> randSpawns = new HashMap<Room, Coordinates>();
	private static ArrayList<Coordinates> roomSpawns = new ArrayList<Coordinates>();
	private HashMap <Interactions, Room> roomReact = new HashMap<Interactions,Room>();
	
	//basic constructor
	Map()
	{}

	//adds values to the array list
	public static void setRoomSpawns(Coordinates random, ArrayList<Coordinates> roomSpawns)
	{
		roomSpawns.add(random);
	}
	
	//returns coordinates from array list
	public static ArrayList<Coordinates> getRoomSpawns() 
	{	
		return roomSpawns;
	}
	
	//big boi testing right here
	public static void main (String args[]) 
	{
		//test string to see if this actually wants to work for me
		System.out.println("Sample test for Coordinate class compatibility with Map\n");
		
		//testing to see if it will withdraw the single stored coordinate
		Coordinates test = new Coordinates(50,76);
		setRoomSpawns(test, roomSpawns);
		System.out.println("This should be able to retrieve the room spawn coordinates "
				+ "in the form of an ugly address, can it actually? " + getRoomSpawns() );
		
	}
}