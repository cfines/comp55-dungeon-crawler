package starter;

import java.util.ArrayList;
import java.util.HashMap;

public class MapRooms {
	
	private HashMap <String, String> mapRoomHashStartEnd = new HashMap <String, String>();
	private HashMap <String, String> mapRoomHashStartEnd_RoomtoEntries = new HashMap <String, String>();
	private HashMap <String, String> mapRoomHashTeleporter = new HashMap <String, String>();
	private HashMap <String, String> mapRoomHashUnpredictable = new HashMap <String, String>();
	private HashMap <String, String> mapRoomHashMatrix = new HashMap <String, String>();
	private HashMap <String, String> mapRoomHashEspeciallyThisOne = new HashMap <String, String>();
	
	private ArrayList<String> roomAmount = new ArrayList<String>();
	private int roomAmountofLayout;
	private Floor floor;
	private MapLayout layout;
	
	
	public HashMap<String, String> getMapRoomHashStartEnd_RoomtoEntries(){
		return mapRoomHashStartEnd_RoomtoEntries;
	}
	
	public HashMap<String, String> getMapRoomHashStartEnd(){
		return mapRoomHashStartEnd;
	}
	
	public HashMap<String, String> getMapRoomHashTeleporter(){
		return mapRoomHashTeleporter;
	}
	
	public HashMap<String, String> getMapRoomHashUnpredictable(){
		return mapRoomHashUnpredictable;
	}
	
	public HashMap<String, String> getMapRoomHashMatrix(){
		return mapRoomHashMatrix;
	}
	
	public HashMap<String, String> getMapRoomHashEspeciallyThisOne(){
		return mapRoomHashEspeciallyThisOne;
	}
	
	public String whatMapWeOn() {
		return floor.whatMapWeOn(floor.getLevelCounter());
	}
	
	public int getRoomAmountofLayout() {
		return roomAmountofLayout;
	}
	public void setRoomAmountofLayout(int amount) {
		roomAmountofLayout = amount;
	}
	
	public ArrayList<String> getRoomAmount(){
		return roomAmount;
	}
	
	public void setRoomAmount(int amountOfRooms) {
		for(int i = 1; i <= amountOfRooms; i++) {
			String temp = new String("R" + i);
			getRoomAmount().add(temp);
		}
	}
	
	public void setRoomAmountBasedonLayout(String currLayout) {
		if(currLayout == "map_base1") {
			setRoomAmountofLayout(3); //TBD
		}
		else if(currLayout == "map_fire") {
			setRoomAmountofLayout(0); //TBD
		}
		else if(currLayout == "map_water") {
			setRoomAmountofLayout(0); //TBD
		}
		else if(currLayout == "map_earth") {
			setRoomAmountofLayout(0); //TBD
		}
		else {
			setRoomAmountofLayout(0); //TBD
		} 
	}
	
	public void setMapRoomHash(String currLayout) {
		if(currLayout == "map_base1") {
			getMapRoomHashStartEnd().put("R1", getRoomAmount().get(0));
			getMapRoomHashStartEnd().put("R2", getRoomAmount().get(1));
			getMapRoomHashStartEnd().put("R3", getRoomAmount().get(1));
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
	
	public HashMap<String, String> getMapRoomHash(String currLayout) {
		if(currLayout == "map_base1") {
			return mapRoomHashStartEnd;
		}
		else if(currLayout == "map_fire") {
			return mapRoomHashTeleporter;
		}
		else if(currLayout == "map_water") {
			return mapRoomHashUnpredictable;
		}
		else if(currLayout == "map_earth") {
			return mapRoomHashMatrix;
		}
		else {
			return mapRoomHashEspeciallyThisOne;
		}
	}
	
	public void runTest() {
		String currMap = "map_base1";
		int temp;
		HashMap<String, String> mapHashCurrMap;
		setRoomAmountBasedonLayout(currMap);
		temp = getRoomAmountofLayout();
		setRoomAmount(temp);
		setMapRoomHash(currMap);
		mapHashCurrMap = getMapRoomHash(currMap);
		
		System.out.println("Current map is 'map_base1'");
		System.out.println("User enters E1 and exits from E2. E2 should be in R2. Is it?: " + mapHashCurrMap.get(getRoomAmount().get(1)));
		System.out.println("User enters E2 and exits from E1. E1 should be in R1. Is it?: " + mapHashCurrMap.get(getRoomAmount().get(0)));
		System.out.println(getRoomAmount().get(2));
	}
	
	public static void main(String[] args) {
		MapRooms test = new MapRooms();
		test.runTest();
	}
}
