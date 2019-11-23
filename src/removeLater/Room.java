package removeLater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import enemyInteraction.Enemy;
import enemyInteraction.Interactions;

public class Room {
	private roomLayout roomLayout;
	private Map map = new Map();
	private Interactions interactionClass;
	private boolean isKeyRoom;
	private int numEntries;
	private ArrayList<Interactions> entries;
	private ArrayList<Interactions> obstacles;
	private ArrayList<Enemy> enemies;
	private HashMap<String,String> entryPoints;
	private ArrayList<String> entryAmount;
	
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
	
	public String whatMapWeOn() {
		return map.whatMapWeOn();
	}   
	    
	public ArrayList<Interactions> getEntries(){
		return entries;
	}
	
	public ArrayList<Interactions> getObstacles(){
		return obstacles;
	}
	
	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}
	
	// the parameters for getting the entries and obstacles are the amount of said entries and obstacles inside the specific room the player is in
	public void createNumEntries(int amount) {
		entries = new ArrayList<Interactions>(amount);
	}
	 
	public void createNumObsticles(int amount) {
		obstacles = new ArrayList<Interactions>(amount);
	}
	
	public void createEnemies(int amount) {
		enemies = new ArrayList<Enemy>(amount);
	}
	
	
	//TODO change the functionality of this so that it works in room because
	//I just realized that it makes no sense to have a function that gets interactions from a room
	//from map         
	                 
	//public static HashMap <HashMap<Interactions, Coordinates>, Room> getInteractionsFromRoom(){
	//	return Map.getInteractionsFromRoom();
	//}
	
	public HashMap<Enemy, Coordinates> getEnemySpawn(){
		return map.getEnemySpawn();
	}     
	      
	public HashMap<Boss, Coordinates> getBossSpawn(){
		return map.getBossSpawn();
	}
	
	
	public void testCase() {
		String currMapTest = "map_base1";
		MapLayout layout = new MapLayout();
		HashMap<String, String> mapHashCurrMap;
		ArrayList<String> numOfEntries;
		
		layout.setEntryAmountBasedonLayout(currMapTest);
		int temp = layout.getEntryAmountofLayout();
		layout.setEntryAmount(temp);
		layout.setMapHash(currMapTest);
		mapHashCurrMap = layout.getMapHash(currMapTest);
		
		String nextEntry, nextRoom;
		nextEntry = mapHashCurrMap.get(layout.getEntryAmount().get(2));
		System.out.println("E1 to E2?: " + mapHashCurrMap.get(layout.getEntryAmount().get(0)));
		System.out.println("E2 to E1?: " + mapHashCurrMap.get(layout.getEntryAmount().get(1)));
		System.out.println("E3 to E4?: " + mapHashCurrMap.get(layout.getEntryAmount().get(2)));
		System.out.println("E4 to E3?: " + mapHashCurrMap.get(layout.getEntryAmount().get(3)));
		
		setEntryToRoom(currMapTest);
		numOfEntries = setEtoRAmount(layout.getEntryAmount(), currMapTest);
		
		System.out.println("E1 to R1?: " + getMapBaseEtoR().get("E1"));
		System.out.println("E2 to R2?: " + getMapBaseEtoR().get("E2"));
		System.out.println("E3 to R2?: " + getMapBaseEtoR().get("E3"));
		System.out.println("E4 to R3?: " + getMapBaseEtoR().get("E4"));
	}

	
	private HashMap <String, String> mapBaseEtoR = new HashMap<String, String>();
	
	public HashMap<String,String> getMapBaseEtoR() {
		return mapBaseEtoR;
	}
	
	public ArrayList<String> setEtoRAmount(ArrayList<String> entryAmnt, String currRoom){
		ArrayList<String> entriesInRoom = new ArrayList<String>();
		for (int i = 0; i <= entryAmnt.size() - 1; i++) {
			String temp1 = entryAmnt.get(i);
			if(getMapBaseEtoR().get(temp1) == currRoom) {
				entriesInRoom.add(temp1);
			}
		}
		return entriesInRoom;
	}
	
	/*This function is another section that will be filled with hard coded values for a hashmap.
	 * This hashmap will contain the entry IDs (key) and will return the room ID it will be in (value).*/
	public void setEntryToRoom(String currLayout) {
		if(currLayout == "map_base1") {
			getMapBaseEtoR().put("E1", "R1"); 
			getMapBaseEtoR().put("E2", "R2"); 
			getMapBaseEtoR().put("E3", "R2");
			getMapBaseEtoR().put("E4", "R3");
			/*
			getMapBaseEtoR().put("E5", "R3");
			getMapBaseEtoR().put("E6", "R4");
			getMapBaseEtoR().put("E7", "R4");
			getMapBaseEtoR().put("E8", "R5");
			getMapBaseEtoR().put("E9", "R5");
			getMapBaseEtoR().put("E10", "R5");
			getMapBaseEtoR().put("E11", "R6");
			getMapBaseEtoR().put("E12", "R7");
			getMapBaseEtoR().put("E13", "R7");
			getMapBaseEtoR().put("E14", "R8");
			getMapBaseEtoR().put("E15", "R8");
			getMapBaseEtoR().put("E16", "R9");//TBD
			*/
		}
		else if(currLayout == "map_fire") {
			//TBD
		}
		else if(currLayout == "map_water") {
			//TBD
		}
		else if(currLayout == "map_earth") {
			//TBD
		}
		else {
			//TBD
		} 
	}
	
	
	// roomLayout layout, HashMap<Enemy, Coordinates> Espawns,  HashMap <HashMap<Interactions, Coordinates>, Room> Ispawns
	
	Room(){}
	
	
	public static void main(String[] args) {
		Room test = new Room();
		test.testCase();
	}	
}
