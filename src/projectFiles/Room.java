package projectFiles;

import java.util.ArrayList;
import java.util.HashMap;

public class Room {
	roomLayout roomLayout;
	private Map map;
	private Interactions interactionClass;
	private boolean isKeyRoom;
	private int numEntries;
	private ArrayList<Interactions> entries;
	private ArrayList<Interactions> obstacles;
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
	
	/*TODO: create hashmap where the key will be the name of an entry point (E1, E2, E3 etc) that will 
	 * return the room these entry points will be in. Remember to name each gRect what will be an entry
	 * the proper entry name (E1, E2, E3, etc)*/
	
	public Map map() {
		return map;
	}
	
	public ArrayList<Interactions> getEntries(){
		return entries;
	}
	
	public ArrayList<Interactions> getObstacles(){
		return obstacles;
	}
	
	// the parameters for getting the entries and obstacles are the amount of said entries and obstacles inside the specific room the player is in
	public void createNumEntries(int amount) {
		entries = new ArrayList<Interactions>(amount);
	}
	 
	public void createNumObsticles(int amount) {
		obstacles = new ArrayList<Interactions>(amount);
	}
	
	/*This function points to MapLayout and returns the HashMap of entries
	 * of the current map layout the user is in. What dictates the specific
	 * HashMap that will be returned will be based off of Floor that MapLayout
	 * will receive. It will get the hard coded entry points within the class
	 * and assign it to the HashMap that will be returned.*/
	public static HashMap<String,String> getMapHash(String currLayout) {
		//is this legal??
		return Map.getMapHash(currLayout);
	}
	
	public static ArrayList<String> getEntryAmount(){
		return Map.getEntryAmount();
	}
	
	public static HashMap<String, String> getMapRoomHash(String currLayout){
		return Map.getMapRoomHash(currLayout);
	}
	
	public static ArrayList<String> getRoomAmount(){
		return Map.getRoomAmount();
	}
	
	public static HashMap <HashMap<Interactions, Coordinates>, Room> getInteractionsFromRoom(){
		return Map.getInteractionsFromRoom();
	}
	
	public static void getEnemyRoomSpawns() {
		
	}
	
	
	
	public void createRoom() {}
	
	public static void main(String[] args) {
	
	}
	
	
}
