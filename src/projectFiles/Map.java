//This the map class that takes in the values of map layout and room, 
//spits out the combination, 
//and leads to something beautiful
package projectFiles;

import java.util.ArrayList;
import java.util.HashMap;

public class Map{
	private MapLayout type;
	private Interactions entries;
	private ArrayList<Room> roomList;
	private Room curRoom;
	private HashMap <Room, spawnCoords> randSpawns;
	private ArrayList<randSpawns> roomSpawns;
	private HashMap <Interactions, Room> roomReact; 
	//instance variables
	
	Map()
	{
		int xCoord = 0;
		int yCoord = 0;
	}

	public MapLayout getType() {
		return type;
	}

	public void setType(MapLayout type) {
		this.type = type;
	}

	public ArrayList<Room> getRoomList() {
		return roomList;
	}

	public void setRoomList(ArrayList<Room> roomList) {
		this.roomList = roomList;
	}

	public Room getCurRoom() {
		return curRoom;
	}

	public void setCurRoom(Room curRoom) {
		this.curRoom = curRoom;
	}

	public HashMap <Room, spawnCoords> getRandSpawns() {
		return randSpawns;
	}

	public void setRandSpawns(HashMap <Room, spawnCoords> randSpawns) {
		this.randSpawns = randSpawns;
	}

	public ArrayList<randSpawns> getRoomSpawns() {
		return roomSpawns;
	}

	public void setRoomSpawns(ArrayList<randSpawns> roomSpawns) {
		this.roomSpawns = roomSpawns;
	}

	public HashMap <Interactions, Room> getRoomReact() {
		return roomReact;
	}

	public void setRoomReact(HashMap <Interactions, Room> roomReact) {
		this.roomReact = roomReact;
	}

	public Interactions getEntries() {
		return entries;
	}

	public void setEntries(Interactions entries) {
		this.entries = entries;
	}
	
}
