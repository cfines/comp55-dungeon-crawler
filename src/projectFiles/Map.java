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
	private ArrayList<Room> roomList;
	private Room curRoom;
	private HashMap <Room, Coordinates> randSpawns;
	private static ArrayList<Coordinates> roomSpawns;
	private HashMap <Interactions, Room> roomReact;
	
	//basic constructor
	Map()
	{
		
	}

	//returns coordinates from array list

	public static void setRoomSpawns(Coordinates random, ArrayList<Coordinates> roomSpawns)
	{
		roomSpawns = new ArrayList<Coordinates>();
		roomSpawns.add(random);
	}
	
	public static ArrayList<Coordinates> getRoomSpawns() 
	{	
		return roomSpawns;
	}
	
	public static void main (String args[]) 
	{
		//test string to see if this actually wants to work for me
		System.out.println("Sample test for Coordinate class compatibility with Map\n");
		
		//testing to see if it will withdraw the single stored coordinate
		Coordinates test = new Coordinates(50,76);
		setRoomSpawns(test, roomSpawns);
		System.out.println( getRoomSpawns() );
	}
}