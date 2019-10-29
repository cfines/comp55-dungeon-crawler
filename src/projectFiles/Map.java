//This the map class that takes in the values of map layout and room, 
//spits out the combination, 
//and leads to something beautiful
package projectFiles;

import java.util.ArrayList;
import java.util.HashMap;

public class Map{
	//instance variables
	private MapLayout type;
	private Interactions entries;
	private ArrayList<Room> roomList;
	private Room curRoom;
	private HashMap <Room, Coordinates> randSpawns;
	private ArrayList<Coordinates> roomSpawns;
	private HashMap <Interactions, Room> roomReact;
	private int horizontal, vertical;
	private Coordinates randomSpawnage;
	
	Map()
	{
		horizontal = 1;
		vertical = 1;
	}
	
	Map(int x, int y)
	{
		horizontal = x;
		vertical = y;
	}

	public void spawnValues() 
	{		
		//TODO add function for random values that would be called in the randSpawns interface
	}

	public Coordinates getCoordinates() {
		return randomSpawnage;
	}

	public void setCoordinates(Coordinates randomSpawnage)
	{
		randomSpawnage = new Coordinates(50,78);
	}

	//TODO add a function for spawn values that would be called in the spawnCoords interface
}