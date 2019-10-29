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
	
	private HashMap <String, String> mapHashTest = new HashMap <String, String>();
	public int entryAmountofTest = 3;
	public ArrayList<String> entryAmount = new ArrayList<String>();
	public String test;
	
	MapLayout(){
		roomID = "";
		entryID = "";
		test = "";
		
	}
	
	// returns mapHashTest with hard coded values for the sake of testing 
	// will be changed later
	public HashMap<String, String> getMapHash() {
		return mapHashTest;
	}
	
	public void setMapHash() {
		getMapHash().put(entryAmount.get(0), entryAmount.get(1)); 
		getMapHash().put(entryAmount.get(1), entryAmount.get(0)); 
	}
	
	/* TODO: connect class to the superclass Floor in order to recieve what mapLayout the player will be on, and
	to return the hard coded entries for said mapLayout */
	
	
	
	// based on the mapLayout, this for loop 
	public void entryAmount() {
		 for(int i = 1; i >= entryAmountofTest; i++) {
			String temp = new String("E" + i);
			entryAmount.add(i, temp);
		 } 
		// entryAmount.add("E1");
		// entryAmount.add("E2");
		System.out.println(entryAmount.get(1));
	}
	
	public void runTest() {
		test = entryAmount.get(0);
		System.out.println("User enters E1, the HashMap should spit out where the user will pop out from which shoudl be E2. Does it?:    "
				+ getMapHash().get(test));
		test = entryAmount.get(1);
		System.out.println("User enters E2, the HashMap should spit out where the user will pop out from which shoudl be E1. Does it?:    "
				+ getMapHash().get(test));
	}
	
	public static void main(String[] args){
		
		MapLayout testt = new MapLayout();
		//testt.entryAmount();
		//testt.setMapHash();
		//testt.runTest();
	}
	
}
