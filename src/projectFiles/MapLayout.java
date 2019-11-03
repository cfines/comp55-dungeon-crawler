package projectFiles;

import java.util.ArrayList;
import java.util.HashMap;

// More like MapEntries but whatever
public class MapLayout {
	
	private Floor floor;
	
	private HashMap <String, String> mapHashStartEnd = new HashMap <String, String>();
	private HashMap <String, String> mapHashTeleporter = new HashMap <String, String>();
	private HashMap <String, String> mapHashUnpredictable = new HashMap <String, String>();
	private HashMap <String, String> mapHashMatrix = new HashMap <String, String>();
	private HashMap <String, String> mapHashEspeciallyThisOne = new HashMap <String, String>();
	
	private int entryAmountofLayout;
	private ArrayList<String> entryAmount = new ArrayList<String>();
	
	MapLayout(){}	
	
	public String whatMapWeOn() {
		return floor.whatMapWeOn(floor.getLevelCounter());
	}
	
	public int getEntryAmountofLayout() {
		return entryAmountofLayout;
	}
	
	public ArrayList<String> getEntryAmount() {
		return entryAmount;
	}
	
	public void setEntryAmountofLayout(int amount) {
		entryAmountofLayout = amount;
	}
	
	public HashMap<String, String> getMapHashStartEnd(){
		return mapHashStartEnd;
	}
	
	public HashMap<String, String> getMapHashTeleporter(){
		return mapHashTeleporter;
	}
	
	public HashMap<String, String> getMapHashUnpredictable(){
		return mapHashUnpredictable;
	}
	
	public HashMap<String, String> getmapHashMatrix(){
		return mapHashMatrix;
	}
	
	public HashMap<String, String> getmapHashEspeciallyThisOne(){
		return mapHashEspeciallyThisOne;
	}
	
	/* This could have also been done with the level counter
	 * but I chose to set the amount of entries based off of
	 * the string instead of the level number.
	 */
	public void setEntryAmountBasedonLayout(String currLayout) {
		if(currLayout == "map_base1") {
			setEntryAmountofLayout(4); //TBD
		}
		else if(currLayout == "map_fire") {
			setEntryAmountofLayout(0); //TBD
		}
		else if(currLayout == "map_water") {
			setEntryAmountofLayout(0); //TBD
		}
		else if(currLayout == "map_earth") {
			setEntryAmountofLayout(0); //TBD
		}
		else {
			setEntryAmountofLayout(0); //TBD
		} 
	}
	
	// based on the amount of entries of the specified map layout, this for loop will create that amount of entries
	public void setEntryAmount(int amountOfEntries) {
		for(int i = 1; i <= amountOfEntries; i++) {
			String temp = new String("E" + i);
			getEntryAmount().add(temp);
		}
	}
	
	// Sets hard coded entry points for the current map 
	public void setMapHash(String currLayout) {
		if(currLayout == "map_base1") {
			getMapHashStartEnd().put(getEntryAmount().get(0), getEntryAmount().get(1));
			getMapHashStartEnd().put(getEntryAmount().get(1), getEntryAmount().get(0));
			getMapHashStartEnd().put(getEntryAmount().get(2), getEntryAmount().get(3));
			getMapHashStartEnd().put(getEntryAmount().get(3), getEntryAmount().get(2));
		}
		/*else if(currLayout == "map_fire") {
			
		}
		else if(currLayout == "map_water") {
			
		}
		else if(currLayout == "map_earth") {
			
		}
		else {
			
		}*/
	}
	
	/* Returns the hash map based off the current layout string.
	 * Again, I could have used the level counter as an alternative way 
	 * but I just decided to go with the current layout string.*/
	public HashMap<String, String> getMapHash(String currLayout) {
		if(currLayout == "map_base1") {
			return mapHashStartEnd;
		}
		else if(currLayout == "map_fire") {
			return mapHashTeleporter;
		}
		else if(currLayout == "map_water") {
			return mapHashUnpredictable;
		}
		else if(currLayout == "map_earth") {
			return mapHashMatrix;
		}
		else {
			return mapHashEspeciallyThisOne;
		}
	}
	
	
	public void runTest() {
		String currMap = "map_base1"; // will be replaced with whatMapWeOn() when the program is actually running
		HashMap<String, String> mapHashCurrMap;
		int temp;
		setEntryAmountBasedonLayout(currMap);
		temp = getEntryAmountofLayout();
		setEntryAmount(temp);
		setMapHash(currMap);
		mapHashCurrMap = getMapHash(currMap);
		
		System.out.println("The current map (which would be recieved from Floor) is 'map_base1'.");
		System.out.println("The player enters E1 of 'map_base1' and should exit out of E2. Does it?: " + mapHashCurrMap.get(getEntryAmount().get(0)));
		System.out.println("The player enters E2 of 'map_base1' and should exit out of E1. Does it?: " + mapHashCurrMap.get(getEntryAmount().get(1)));	
	}	
	/*The order of calling functions in this class goes as follows:
	 * - whatMapWeOn
	 * - setEntryBasedonLayout
	 * - setEntryAmount
	 * - setMapHash
	 * - getMapHash*/
	public static void main(String[] args){
		
		MapLayout testt = new MapLayout();
		testt.runTest();
	}
	
}
