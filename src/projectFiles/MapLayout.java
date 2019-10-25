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
	
	
	
	public void main(){
		String entryID = "E1";
		
		
		// based on the mapLayout, this for loop 
		for(int i = 1; i >= entryAmountofTest; i++) {
			entryAmount.add("E" + 1);
		}
		
		// the key of these HashMaps will be the gRect ID the player  
		//  will enter and will return the gRect ID the player will exit out of
		mapHashTest.put("", ""); 
	}
}
