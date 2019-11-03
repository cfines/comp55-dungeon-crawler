package projectFiles;

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

public class H_A_R_D_C_O_D_E_D_ 
{
	private static HashMap <Interactions, Coordinates> interactionStorage;
	private static HashMap <Enemy, Coordinates> enemyStorage;
	private static HashMap <Boss, Coordinates> bossStorage;
	private static Map map = new Map();
	private static Room room = new Room();
	private static Floor floor = new Floor();
	private static MapLayout layout = new MapLayout();
	private static HashMap <String, String> mapHashCurrMap = new HashMap<String, String>();
	private static String currentMap = new String();

	
	public String whatMapWeOn() {
		return floor.whatMapWeOn();
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
	
	public void runBase(String userRoomPosition) 
	{
		//roomID parameter if else verification
		
		while(floor.whatMapWeOn() == "map_base1"){
		//Room 1
		if(userRoomPosition == "R1")
			{
			Coordinates rock1 = new Coordinates (70,89);
			Coordinates rock2 = new Coordinates (780,301);
			Coordinates hole1 = new Coordinates (72,301);
			Coordinates entry1 = new Coordinates (1150, 325);
			Interactions roomOneInteraction1 = new Interactions(interactionType.obstacle_rock);
			Interactions roomOneInteraction2 = new Interactions(interactionType.obstacle_rock);
			Interactions roomOneInteraction3 = new Interactions(interactionType.obstacle_hole);
			Interactions roomOneInteraction4 = new Interactions(interactionType.entry_door);
			map.setInteractions(roomOneInteraction1, rock1, interactionStorage);
			map.setInteractions(roomOneInteraction2, rock2, interactionStorage);
			map.setInteractions(roomOneInteraction3, hole1, interactionStorage);
			map.setInteractions(roomOneInteraction4, entry1, interactionStorage);
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
			Interactions roomTwoInteraction1 = new Interactions(interactionType.obstacle_rock);
			Interactions roomTwoInteraction2 = new Interactions(interactionType.obstacle_hole);
			Interactions roomTwoInteraction3 = new Interactions(interactionType.obstacle_hole);
			Enemy roomTwoEnemy1 = new Enemy(1, 9, 6, 5, 4, 2, ElementType.EARTH);
			Enemy roomTwoEnemy2 = new Enemy(5,0,0,5,1,3, ElementType.FIRE);
			Interactions roomTwoInteraction4 = new Interactions(interactionType.entry_door);
			Interactions roomTwoInteraction5 = new Interactions(interactionType.entry_door);
			map.setInteractions(roomTwoInteraction1, rock1, interactionStorage);
			map.setInteractions(roomTwoInteraction2, hole1, interactionStorage);
			map.setInteractions(roomTwoInteraction3, hole2, interactionStorage);
			map.setEnemySpawn(roomTwoEnemy1, enemy1, enemyStorage);
			map.setEnemySpawn(roomTwoEnemy2, enemy2, enemyStorage);
			map.setInteractions(roomTwoInteraction4, entry1, interactionStorage);
			map.setInteractions(roomTwoInteraction5, entry2, interactionStorage);
			}
			
			//Room 3
			else if(userRoomPosition == "R3")
			{
			Coordinates stairs1 = new Coordinates (575,325);
			Coordinates enemy1 = new Coordinates (800, 70);
			Coordinates entry1 = new Coordinates (0, 325);
			Interactions roomThreeInteraction1 = new Interactions(interactionType.entry_stair);
			Enemy roomThreeEnemy1 = new Enemy(6,9,6,9,6,9, ElementType.WATER);
			Interactions roomThreeInteraction2 = new Interactions(interactionType.entry_door);
			map.setInteractions(roomThreeInteraction1, stairs1, interactionStorage);
			map.setEnemySpawn(roomThreeEnemy1, enemy1, enemyStorage);
			map.setInteractions(roomThreeInteraction2, entry1, interactionStorage);
			}
		
		}	
	}
}	

