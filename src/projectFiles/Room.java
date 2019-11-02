package projectFiles;

import java.util.ArrayList;
import java.util.HashMap;

public class Room {
	private static roomLayout roomLayout;
	static Map map;
	private static Interactions interactionClass;
	private static boolean isKeyRoom;
	private static int numEntries;
	private static ArrayList<Interactions> entries;
	private static ArrayList<Interactions> obstacles;
	private static HashMap<String,String> entryPoints;
	private static ArrayList<String> entryAmount;
	
	
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
	
	public static Map getMapClass() {
		return map;
	}
	
	public static ArrayList<Interactions> getEntries(){
		return entries;
	}
	
	public static ArrayList<Interactions> getObstacles(){
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
	public HashMap<String,String> getMapHash(String currLayout) {
		//is this legal??
		return getMapClass().getMapHash(currLayout);
	}
	
	public static ArrayList<String> getEntryAmount(){
		return getMapClass().getEntryAmount();
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
	
	public static HashMap<Enemy, Coordinates> getEnemySpawn(){
		return Map.getEnemySpawn();
	}
	
	public static HashMap<Boss, Coordinates> getBossSpawn(){
		return Map.getBossSpawn();
	}
	
	
	Room(roomLayout layout, HashMap<Enemy, Coordinates> Espawns,  HashMap <HashMap<Interactions, Coordinates>, Room> Ispawns){
		roomLayout = layout;
		
	}
	
	public static void main(String[] args) {
	
	}
	
	
}
