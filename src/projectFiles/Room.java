package projectFiles;

import java.util.ArrayList;
import java.util.HashMap;

public class Room {
	roomLayout roomLayout;
	private Interactions interactionClass;
	private boolean isKeyRoom;
	private int numEntries;
	ArrayList<Interactions> entries;
	ArrayList<Interactions> obstacles;
	static HashMap<String,String> entryPoints;
	static ArrayList<String> entryAmount;
	
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
	
	
	// the parameters for getting the entries and obstacles are the amount of said entries and obstacles inside the specific room the player is in
	public void createNumEntries(int amount) {
		entries = new ArrayList<Interactions>(amount);
	}
	 
	public void createNumObsticles(int amount) {
		obstacles = new ArrayList<Interactions>(amount);
	}
	
	public static HashMap<String,String> getMapHash(String currLayout) {
		//is this legal??
		return Map.getMapHash(currLayout);
	}
	
	public static ArrayList<String> getEntryAmount(){
		return Map.getEntryAmount();
	}
	
	public void createRoom() {}
	
	public static void main(String[] args) {
	
	}
	
	
}
