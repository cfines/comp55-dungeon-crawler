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
	private static ArrayList<Coordinates> roomSpawns = new ArrayList<Coordinates>();
	private HashMap <Room, ArrayList<Coordinates>> randSpawns = new HashMap<Room, ArrayList<Coordinates>>();
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
		
		//test successful
		Coordinates test1 = new Coordinates(50,76);
		Coordinates test2 = new Coordinates(60,88);
		setRoomSpawns(test1, roomSpawns);
		System.out.println("This should be able to retrieve the room spawn coordinates "
				+ "in the form of an ugly address, can it actually? " + getRoomSpawns() );
		System.out.println("Now testing with another variable being added, will it save both?");
		setRoomSpawns(test2, roomSpawns);
		System.out.println("It should be added if this text is even appearing, let us see the address again."
				+ " Did it change?" + getRoomSpawns());
		System.out.println("Now for those coordinates:\n"
				+ "test1 x = " + test1.getX() + " test1 y = " + test1.getY() + "\n"
				+ "test2 x = " + test2.getX() + " test2 y = " + test2.getY());
	}
}