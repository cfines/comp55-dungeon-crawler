//This the map class that takes in the values of map layout and room, 
//spits out the combination, 
//and leads to something beautiful
package projectFiles;

import java.util.ArrayList;

public class Map {
	private MapLayout type;
	private ArrayList<Room> roomList;
	private Room curRoom;
	
	//instance variables
	
	public Map()
	{
		
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
	
	
}
