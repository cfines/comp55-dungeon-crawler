package projectFiles;

import java.util.ArrayList;
import java.util.HashMap;

public class MapLayout {
	private String roomID;
	private String entryID;		
	private HashMap <String, String> mapHashStartEnd;
	private HashMap <String, String> mapHashTeleporter;
	private HashMap <String, String> mapHashUnpredictable;
	private HashMap <String, String> mapHashMatrix;
	private HashMap <String, String> mapHashEspeciallyThisOne;
	
	private HashMap <String, String> mapHashTest;
	private int entryAmountofTest = 8;
	public ArrayList<String> entryAmount;
	
	MapLayout(){
		roomID = "";
		entryID = "";
	}
	
	public HashMap<String, String> getMapHash() {
		return mapHashTest;
	}
	
	// based on the mapLayout, this for loop 
	public void entryAmount() {
		for(int i = 0; i >= entryAmountofTest; i++) {
			entryAmount.add("E" + 1);
		}
	}
	
	public void main(){
		
		entryAmount();
			
		// the key of these HashMaps will be the gRect ID the player  
		//  will enter and will return the gRect ID the player will exit out of
		getMapHash().put(entryAmount.get(0), entryAmount.get(1)); 
		getMapHash().put(entryAmount.get(1), entryAmount.get(0)); 
		getMapHash().put(entryAmount.get(2), entryAmount.get(3)); 
		getMapHash().put(entryAmount.get(3), entryAmount.get(2)); 
		getMapHash().put(entryAmount.get(3), entryAmount.get(4)); 
		/*mapHashTest.put(entryAmount.get(0), entryAmount.get(1)); 
		mapHashTest.put(entryAmount.get(0), entryAmount.get(1)); 
		mapHashTest.put(entryAmount.get(0), entryAmount.get(1)); 
		mapHashTest.put(entryAmount.get(0), entryAmount.get(1)); 
		mapHashTest.put(entryAmount.get(0), entryAmount.get(1)); 
		mapHashTest.put(entryAmount.get(0), entryAmount.get(1)); 
		mapHashTest.put(entryAmount.get(0), entryAmount.get(1)); 
		mapHashTest.put(entryAmount.get(0), entryAmount.get(1)); 
		mapHashTest.put(entryAmount.get(0), entryAmount.get(1)); 
		mapHashTest.put(entryAmount.get(0), entryAmount.get(1)); 
		mapHashTest.put(entryAmount.get(0), entryAmount.get(1)); */
		
	
		String test = entryAmount.get(0);
		getMapHash().get(test);
		System.out.println("User enters E1, the HashMap should spit out where the user will pop out from which shoudl be E2. Does it?:    "
				+ test);
	}
	
}
