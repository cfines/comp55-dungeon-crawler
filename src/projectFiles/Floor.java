package projectFiles;

import java.util.ArrayList;

public class Floor {

	private Map curMap;
	//private PowerUps key;
	//private PowerUps drop;
	
	//this decides drops (as per method name...)
	private boolean decideDrop() 
	{
		return false;
	}
	
	//this is for key placement
	//there can only be 1 key per floor
	private void placeKey() 
	{
		
	}
	
	private int levelCounter = 0;
	private ArrayList<String> map = new ArrayList<String>();
	private String currRoom = new String();
	// Resets the current room to "R1" when the user enters a new floor
	public void resetCurrRoom() {
		currRoom = "R1";
	}
	// Returns the current room the user is in
	public String getCurrRoom() {
		return currRoom;
	}
	// Sets the new room the player will be in based on the hashmap identifying which room has which entry
	public void setCurrRoom(String newCurrRoom) {
		currRoom = newCurrRoom;
	}
	// this would need to be one of the FIRST functions to be called in order to initialize the arraylist of the types of maps
	public void setMapArrayList() {
		map.add("map_base1"); 
		map.add("map_fire");
		map.add("map_water");
		map.add("map_earth");
		map.add("map_base2");
	}
	// this will be called when the user advances onto the next level i.e. enters a staircase
	public void levelAdder() {
		levelCounter++;
	}
	// returns current map
	public String currentMap(String curMap) {
		return curMap;
	}
	// returns the level counter
	//I'm adding a +1 to the getLevel counter since its offset by the ArrayList's first index being 0
	public int getLevelCounter() {
		return levelCounter;
	}
	// this returns which map layout the user will be on
	// mod 5 will loop back to the first level if the current level exceeds 5, making this a map rotation
	public String whatMapWeOn(int currLevel){
		int temp = currLevel % 5;
		return map.get(temp);
	}
	
	
	
	public static void main(String[] args) {
		/*Floor test = new Floor();
		
		test.setMapArrayList();
		
		System.out.println("We should currently be on level 1. Are we?: " + test.getLevelCounter());
		System.out.println("Because we are on level " + test.getLevelCounter() + ", we should be on map_base1. Are we?: " + test.whatMapWeOn() + "\n");
		
		test.levelAdder();
		
		System.out.println("We should now be on level 2. Are we?: " + test.getLevelCounter());
		System.out.println("Because we are on level " + test.getLevelCounter() + ", we should be on map_fire. Are we?: " + test.whatMapWeOn() + "\n");
			
		test.levelAdder();
		test.levelAdder();
		test.levelAdder();
		test.levelAdder();
		test.levelAdder();
		
		System.out.println("The player has advanced another 5 levels. It should now say we are on level 7. Does it?: " + test.getLevelCounter());
		System.out.println("Because we are on level " + test.getLevelCounter() + ", we should be on map_fire again, "
				+ "because we have done a full rotation of the map order. Are we?: " + test.whatMapWeOn() + "\n");*/
	}
}
