package projectFiles;

import java.util.ArrayList;

public class Room {
	roomLayout roomLayout;
	Interactions interactionClass;
	private boolean isKeyRoom;
	private int numEntries;
	ArrayList<Interactions> entries;
	ArrayList<Interactions> obstacles;
	
	// gets the room layout from the roomLayout enum
	public roomLayout getRoomLayout() {
		return roomLayout;
	}
	
	// the parameters for getting the entries and obstacles are the amount of said entries and obstacles inside the specific room the player is in
	public void createNumEntries(int amount) {
		entries = new ArrayList<Interactions>(amount);
	}
	 
	public void createNumObsticles(int amount) {
		obstacles = new ArrayList<Interactions>(amount);
	}
}
