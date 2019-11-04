package starter;

import java.util.ArrayList;
import java.util.HashMap;

//DO NOT EVEN LOOK AT THIS
//JESUS THIS IS ATROCIOUS
//ALL THESE HARDCODED VALUES WILL GIVE YOU AN ANYURISM

////////////////////////////////////////////////////////////////////////////////////////////////////////////

//References\\

/*MapRooms
 * - setRoomAmountBasedonLayout(currentMap)
 * - temp2 = getRoomAmountofLayout()
 * - setRoomAmount(temp2)
 * - setMapRoomHash(currentMap)
 * - hash map variable = getMapRoomHash(currentMap)*/

/*MapLayout
 * - setEntryAmountBasedonLayout(currentMap)
 * - temp = getEntryAmountofLayout()
 * - setEntryAmount(temp)
 * - setMapHash(currentMap)
 * - hash map variable = getMapHash(currentMap)*/

/*Room
 * - setEntryToRoom
 * - setEtoRAmount*/

/*Floor
 * - whatMapWeOn()
 */

public class Hardcoded 
{
	private static HashMap <Interactions, Coordinates> interactionStorage = new HashMap <Interactions, Coordinates>();
	private static HashMap <Enemy, Coordinates> enemyStorage = new HashMap <Enemy, Coordinates>();
	private static HashMap <Boss, Coordinates> bossStorage = new HashMap <Boss, Coordinates>();
	private static Map map = new Map();
	private static Room room = new Room();
	private static Floor floor = new Floor();
	private static MapLayout layout = new MapLayout();
	private static HashMap <String, String> mapHashCurrMap = new HashMap<String, String>();
	private static String currentMap = new String();
	private static ArrayList<Interactions> mbr1 = new ArrayList<Interactions>();
	
	public String whatMapWeOn() {
		return floor.whatMapWeOn(floor.getLevelCounter());
	}
	
	/*public void nextRoom() {
		
		HashMap <String, String> entriesInRoom = new HashMap<String,String>();
		//currentMap = whatMapWeOn();
		currentMap = "map_base1";
		int temp;
		
		layout.setEntryAmountBasedonLayout(currentMap);
		temp = layout.getEntryAmountofLayout();
		layout.setEntryAmount(temp);
		layout.setMapHash(currentMap);
		mapHashCurrMap = layout.getMapHash(currentMap);
		
		room.setEntryToRoom(currentMap);
		entriesInRoom = room.getMapBaseEtoR();
	}*/
	
	public void runBase(String userRoomPosition, Floor curFloor, Map bruhMap, HashMap <Interactions, Coordinates> h, HashMap <Enemy, Coordinates> eee) 
	{
		//roomID parameter if else verification
		
		if(curFloor.whatMapWeOn(floor.getLevelCounter()) == "map_base1"){
		//Room 1
		if(userRoomPosition == "R1")
			{
			Coordinates rock1 = new Coordinates (70,89);
			Coordinates rock2 = new Coordinates (780,301);
			Coordinates hole1 = new Coordinates (72,301);
			Coordinates entry1 = new Coordinates (1150, 325);
			Interactions roomOneInteraction1 = new Interactions(interactionType.obstacle_rock, 1, 1);
			Interactions roomOneInteraction2 = new Interactions(interactionType.obstacle_rock, 1, 2);
			Interactions roomOneInteraction3 = new Interactions(interactionType.obstacle_hole, 1, 3);
			Interactions roomOneInteraction4 = new Interactions(interactionType.entry_door, 1, 4);
			bruhMap.setInteractions(roomOneInteraction1, rock1, h);
			bruhMap.setInteractions(roomOneInteraction2, rock2, h);
			bruhMap.setInteractions(roomOneInteraction3, hole1, h);
			bruhMap.setInteractions(roomOneInteraction4, entry1, h);
			}
			
			//Room 2
			else if(userRoomPosition == "R2")
			{
			Coordinates rock1 = new Coordinates (80, 90);
			Coordinates hole1 = new Coordinates (500,91);
			Coordinates hole2 = new Coordinates (75, 500);
			Coordinates enemy1 = new Coordinates (350, 76);
			Coordinates enemy2 = new Coordinates (367, 504);
			Coordinates entry1 = new Coordinates (0, 325);
			Coordinates entry2 = new Coordinates (1150, 325);
			Interactions roomTwoInteraction1 = new Interactions(interactionType.obstacle_rock, 2, 1);
			Interactions roomTwoInteraction2 = new Interactions(interactionType.obstacle_hole, 2, 2);
			Interactions roomTwoInteraction3 = new Interactions(interactionType.obstacle_hole, 2, 3);
			Enemy roomTwoEnemy1 = new Enemy(1, 9, 6, 5, 4, 2, ElementType.EARTH);
			Enemy roomTwoEnemy2 = new Enemy(5,0,0,5,1,3, ElementType.FIRE);
			Interactions roomTwoInteraction4 = new Interactions(interactionType.entry_door, 2, 4);
			Interactions roomTwoInteraction5 = new Interactions(interactionType.entry_door, 2, 5);
			bruhMap.setInteractions(roomTwoInteraction1, rock1, h);
			bruhMap.setInteractions(roomTwoInteraction2, hole1, h);
			bruhMap.setInteractions(roomTwoInteraction3, hole2, h);
			bruhMap.setInteractions(roomTwoInteraction4, entry1, h);
			bruhMap.setInteractions(roomTwoInteraction5, entry2, h);
			bruhMap.setEnemySpawn(roomTwoEnemy1, enemy1, eee);
			bruhMap.setEnemySpawn(roomTwoEnemy2, enemy2, eee);
			}
			
			//Room 3
			else if(userRoomPosition == "R3")
			{
			Coordinates stairs1 = new Coordinates (575,325);
			Coordinates enemy1 = new Coordinates (800, 70);
			Coordinates entry1 = new Coordinates (0, 325);
			Interactions roomThreeInteraction1 = new Interactions(interactionType.entry_stair, 3, 1);
			Enemy roomThreeEnemy1 = new Enemy(6,9,6,9,6,9, ElementType.WATER);
			Interactions roomThreeInteraction2 = new Interactions(interactionType.entry_door, 3, 2);
			bruhMap.setInteractions(roomThreeInteraction1, stairs1, h);
			bruhMap.setInteractions(roomThreeInteraction2, entry1, h);
			bruhMap.setEnemySpawn(roomThreeEnemy1, enemy1, eee);
			}
		
		}	
	}

	public Coordinates getCoordinateFromHash(Interactions input_interactions) {
		return interactionStorage.get(input_interactions);
	}

}	

