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
	private static Console console = new Console();
	
	
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
	
	public void runBase(String userRoomPosition, Floor curFloor, Map bruhMap, HashMap <Interactions, Coordinates> h, 
													HashMap <Enemy, Coordinates> eee, ArrayList<Coordinates> enteredEntriesArrayList, 
													HashMap<String,ArrayList<Coordinates>> enteredEntriesHash, HashMap <Boss, Coordinates> bbb) 
	{
		//roomID parameter if else verification
		
		if(curFloor.whatMapWeOn(floor.getLevelCounter()) == "map_base1"){
			
			System.out.println(userRoomPosition + "<-----hard coded class prints this room");
		
			//Room 1
			if(userRoomPosition == "R1")
			{
//				Coordinates rock1 = new Coordinates (170,189);
//				Coordinates rock2 = new Coordinates (890,200);
//				Coordinates hole1 = new Coordinates (172,425);
//				Coordinates E1 = new Coordinates (1040, 300);
//				Coordinates enemy1 = new Coordinates(400,209);
//				bruhMap.setEnteredEntries("E1", E1, enteredEntriesArrayList, enteredEntriesHash);
//				Interactions roomOneInteraction1 = new Interactions(interactionType.obstacle_rock, 1, 1);
//				roomOneInteraction1.setImage(interactionType.obstacle_rock, 170, 189);
//				Interactions roomOneInteraction2 = new Interactions(interactionType.obstacle_rock, 1, 2);
//				roomOneInteraction2.setImage(interactionType.obstacle_rock, 890, 200);
//				Interactions roomOneInteraction3 = new Interactions(interactionType.obstacle_hole, 1, 3);
//				roomOneInteraction3.setImage(interactionType.obstacle_hole, 172, 425);
//				Interactions roomOneInteraction4 = new Interactions(interactionType.entry_door_EAST, 1, 4);
//				roomOneInteraction4.setImage(interactionType.entry_door_EAST, 1040, 300);
//				Enemy roomOneEnemy1 = new Enemy(2,2,2,2,2,2, ElementType.FIRE);
//				bruhMap.setInteractions(roomOneInteraction1, rock1, h);
//				bruhMap.setInteractions(roomOneInteraction2, rock2, h);
//				bruhMap.setInteractions(roomOneInteraction3, hole1, h);
//				bruhMap.setInteractions(roomOneInteraction4, E1, h);
//				bruhMap.setEnemySpawn(roomOneEnemy1, enemy1, eee);
			}
			//Room 2
			else if(userRoomPosition == "R2")
			{
				Coordinates rock1 = new Coordinates (100, 90);
				Coordinates hole1 = new Coordinates (500,91);
				Coordinates hole2 = new Coordinates (575, 400);
				Coordinates enemy1 = new Coordinates (350, 76);
				Coordinates enemy2 = new Coordinates (367, 504);
				Coordinates E2 = new Coordinates (60, 300);
				bruhMap.setEnteredEntries("E2", E2, enteredEntriesArrayList, enteredEntriesHash);
				Coordinates E3 = new Coordinates (1040, 300);
				bruhMap.setEnteredEntries("E3", E3, enteredEntriesArrayList, enteredEntriesHash);
				Interactions roomTwoInteraction1 = new Interactions(interactionType.obstacle_rock, 2, 1);
				Interactions roomTwoInteraction2 = new Interactions(interactionType.obstacle_hole, 2, 2);
				Interactions roomTwoInteraction3 = new Interactions(interactionType.obstacle_hole, 2, 3);
				Enemy roomTwoEnemy1 = new Enemy(2, 2, 2, 2, 2, 2, ElementType.EARTH);
				Enemy roomTwoEnemy2 = new Enemy(2,2,2,2,2,2, ElementType.FIRE);
				Interactions roomTwoInteraction4 = new Interactions(interactionType.entry_door_WEST, 2, 4);
				Interactions roomTwoInteraction5 = new Interactions(interactionType.entry_door_EAST, 2, 5);
				bruhMap.setInteractions(roomTwoInteraction1, rock1, h);
				bruhMap.setInteractions(roomTwoInteraction2, hole1, h);
				bruhMap.setInteractions(roomTwoInteraction3, hole2, h);
				bruhMap.setInteractions(roomTwoInteraction4, E2, h);
				bruhMap.setInteractions(roomTwoInteraction5, E3, h);
				bruhMap.setEnemySpawn(roomTwoEnemy1, enemy1, eee);
				bruhMap.setEnemySpawn(roomTwoEnemy2, enemy2, eee);
			}
			
			
			//Room 3
			else if(userRoomPosition == "R3")
			{
			Coordinates enemy1 = new Coordinates (800, 70);
			Coordinates enemy2 = new Coordinates(575,487);
			Coordinates E4 = new Coordinates (60, 300);
			bruhMap.setEnteredEntries("E4", E4, enteredEntriesArrayList, enteredEntriesHash);
			Coordinates E5 = new Coordinates (1040, 300);
			bruhMap.setEnteredEntries("E5", E5, enteredEntriesArrayList, enteredEntriesHash);
			Coordinates rock1 = new Coordinates (575,325);
			Coordinates hole1 = new Coordinates (230,163);
			Enemy roomThreeEnemy1 = new Enemy(2,2,2,2,2,2, ElementType.WATER);
			Enemy roomThreeEnemy2 = new Enemy(2,2,2,2,2,2, ElementType.WATER);
			Interactions roomThreeInteraction1 = new Interactions(interactionType.entry_door_WEST, 3,1);
			Interactions roomThreeInteraction2 = new Interactions(interactionType.entry_door_EAST,3,2);
			Interactions roomThreeInteraction3 = new Interactions(interactionType.obstacle_rock, 3, 3);
			Interactions roomThreeInteraction4 = new Interactions(interactionType.obstacle_hole, 3,4);
			bruhMap.setInteractions(roomThreeInteraction1, E4, h);
			bruhMap.setInteractions(roomThreeInteraction2, E5, h);
			bruhMap.setInteractions(roomThreeInteraction3, rock1, h);
			bruhMap.setInteractions(roomThreeInteraction4, hole1, h);
			bruhMap.setEnemySpawn(roomThreeEnemy1, enemy1, eee);
			bruhMap.setEnemySpawn(roomThreeEnemy2, enemy2, eee);
			}
		
			//Room 4
			else if(userRoomPosition == "R4") 
			{
			Coordinates enemy1 = new Coordinates(575,216);
			Coordinates enemy2 = new Coordinates(575,434);
			Coordinates enemy3 = new Coordinates(863, 434);
			Coordinates hole1 = new Coordinates(900,100);
			Coordinates rock1 = new Coordinates(230,490);
			Coordinates E6 = new Coordinates(60,300);
			Coordinates E7 = new Coordinates(575, 490);
			bruhMap.setEnteredEntries("E6", E6, enteredEntriesArrayList, enteredEntriesHash);
			bruhMap.setEnteredEntries("E7", E7, enteredEntriesArrayList, enteredEntriesHash);
			Enemy roomFourEnemy1 = new Enemy(2,2,2,2,2,2, ElementType.FIRE);
			Enemy roomFourEnemy2 = new Enemy(2,2,2,2,2,2, ElementType.WATER);
			Enemy roomFourEnemy3 = new Enemy(2,2,2,2,2,2, ElementType.EARTH);
			Interactions roomFourInteraction1 = new Interactions(interactionType.obstacle_hole,4,1);
			Interactions roomFourInteraction2 = new Interactions(interactionType.obstacle_rock,4,2);
			Interactions roomFourInteraction3 = new Interactions(interactionType.entry_door_WEST, 4,3);
			Interactions roomFourInteraction4 = new Interactions(interactionType.entry_door_SOUTH,4,4);
			bruhMap.setEnemySpawn(roomFourEnemy1, enemy1, eee);
			bruhMap.setEnemySpawn(roomFourEnemy2, enemy2, eee);
			bruhMap.setEnemySpawn(roomFourEnemy3, enemy3, eee);
			bruhMap.setInteractions(roomFourInteraction1, hole1, h);
			bruhMap.setInteractions(roomFourInteraction2, rock1, h);
			bruhMap.setInteractions(roomFourInteraction3, E6, h);
			bruhMap.setInteractions(roomFourInteraction4, E7, h);
			}
		
			//Room 5
			else if(userRoomPosition == "R5") 
			{
			Coordinates enemy1 = new Coordinates(575,325);
			Coordinates hole1 = new Coordinates(230,325);
			Coordinates E8 = new Coordinates(575,60);
			Coordinates E9 = new Coordinates(575,490);
			Coordinates E10 = new Coordinates(1040,300);
			bruhMap.setEnteredEntries("E8", E8, enteredEntriesArrayList, enteredEntriesHash);
			bruhMap.setEnteredEntries("E9", E9, enteredEntriesArrayList, enteredEntriesHash);
			bruhMap.setEnteredEntries("E10", E10, enteredEntriesArrayList, enteredEntriesHash);
			Enemy roomFiveEnemy1 = new Enemy(2,2,2,2,2,2,ElementType.EARTH);
			Interactions roomFiveInteraction1 = new Interactions(interactionType.obstacle_hole,5,1);
			Interactions roomFiveInteraction2 = new Interactions(interactionType.entry_door_NORTH,5,2);
			Interactions roomFiveInteraction3 = new Interactions(interactionType.entry_door_SOUTH,5,3);
			Interactions roomFiveInteraction4 = new Interactions(interactionType.entry_door_EAST,5,4);
			bruhMap.setEnemySpawn(roomFiveEnemy1, enemy1, eee);
			bruhMap.setInteractions(roomFiveInteraction1, hole1, h);
			bruhMap.setInteractions(roomFiveInteraction2, E8, h);
			bruhMap.setInteractions(roomFiveInteraction3, E9, h);
			bruhMap.setInteractions(roomFiveInteraction4, E10, h);
			}
			
			//Room 6
			else if(userRoomPosition == "R6") 
			{
			Coordinates rock1 = new Coordinates(900,150);
			Coordinates E11 = new Coordinates(575,60);
			bruhMap.setEnteredEntries("E11", E11, enteredEntriesArrayList, enteredEntriesHash);
			Coordinates key1 = new Coordinates(575,300);
			Interactions roomSixInteraction1 = new Interactions(interactionType.obstacle_rock,6,1);
			Interactions roomSixInteraction2 = new Interactions(interactionType.entry_door_NORTH,6,2);
			Interactions roomSixInteraction3 = new Interactions(interactionType.item_gif_key,6,3);
			bruhMap.setInteractions(roomSixInteraction1, rock1, h);
			bruhMap.setInteractions(roomSixInteraction2, E11, h);
			bruhMap.setInteractions(roomSixInteraction3, key1, h);
			}
		
			//Room 7
			else if(userRoomPosition == "R7") 
			{
			Coordinates rock1 = new Coordinates(300,200);
			Coordinates E12 = new Coordinates(60,300);
			Coordinates E13 = new Coordinates(1040,300);
			bruhMap.setEnteredEntries("E12", E12, enteredEntriesArrayList, enteredEntriesHash);
			bruhMap.setEnteredEntries("E13", E13, enteredEntriesArrayList, enteredEntriesHash);
			Coordinates enemy1 = new Coordinates(575,100);
			Coordinates enemy2 = new Coordinates(575,250);
			Coordinates enemy3 = new Coordinates(575,420);
			Interactions roomSevenInteraction1 = new Interactions(interactionType.obstacle_rock,7,1);
			Interactions roomSevenInteraction2 = new Interactions(interactionType.entry_door_WEST,7,2);
			Interactions roomSevenInteraction3 = new Interactions(interactionType.entry_door_EAST,7,3);
			Enemy roomSevenEnemy1 = new Enemy (2,2,2,2,2,2, ElementType.FIRE);
			Enemy roomSevenEnemy2 = new Enemy (2,2,2,2,2,2, ElementType.EARTH);
			Enemy roomSevenEnemy3 = new Enemy (2,2,2,2,2,2, ElementType.FIRE);
			bruhMap.setInteractions(roomSevenInteraction1, rock1, h);
			bruhMap.setInteractions(roomSevenInteraction2, E12, h);
			bruhMap.setInteractions(roomSevenInteraction3, E13, h);
			bruhMap.setEnemySpawn(roomSevenEnemy1, enemy1, eee);
			bruhMap.setEnemySpawn(roomSevenEnemy2, enemy2, eee);
			bruhMap.setEnemySpawn(roomSevenEnemy3, enemy3, eee);
			}
		
			//Room 8
			else if(userRoomPosition == "R8") 
			{
			Coordinates rock1 = new Coordinates(150,425);
			Coordinates rock2 = new Coordinates(575,325);
			Coordinates hole1 = new Coordinates(901,325);
			Coordinates E14 = new Coordinates(60,300);
			Coordinates E15 = new Coordinates(575,60);	//TODO this door needs to be locked as it leads to the boss room!!!
			bruhMap.setEnteredEntries("E14", E14, enteredEntriesArrayList, enteredEntriesHash);
			bruhMap.setEnteredEntries("E15", E15, enteredEntriesArrayList, enteredEntriesHash);
			Interactions roomEightInteraction1 = new Interactions(interactionType.obstacle_rock,8,1);
			Interactions roomEightInteraction2 = new Interactions(interactionType.obstacle_rock,8,2);
			Interactions roomEightInteraction3 = new Interactions(interactionType.obstacle_hole,8,3);
			Interactions roomEightInteraction4 = new Interactions(interactionType.entry_door_WEST,8,4);
			Interactions roomEightInteraction5 = new Interactions(interactionType.entry_bossDoor,8,5);
			bruhMap.setInteractions(roomEightInteraction1, rock1, h);
			bruhMap.setInteractions(roomEightInteraction2, rock2, h);
			bruhMap.setInteractions(roomEightInteraction3, hole1, h);
			bruhMap.setInteractions(roomEightInteraction4, E14, h);
			bruhMap.setInteractions(roomEightInteraction5, E15, h); //TODO same as previously mentioned
			}
		
			//Room 9 (BOSS ROOM)
			else if(userRoomPosition == "R9") 
			{
			//Coordinates boss1 = new Coordinates(575,325);
			Coordinates E16 = new Coordinates(575,490);	//TODO find way to prevent user to leave until the boss is dead
			bruhMap.setEnteredEntries("E16", E16, enteredEntriesArrayList, enteredEntriesHash);
			Coordinates stairs1 = new Coordinates(575, 100);	//TODO make sure that these don't appear until the boss is dead
			//Boss roomNineBoss1 = new Boss(10,10,2,2,2,2, ElementType.EARTH);	//TODO decide which boss we want first
			Interactions roomNineInteraction1 = new Interactions(interactionType.entry_door_SOUTH,9,1);
			Interactions roomNineInteraction2 = new Interactions(interactionType.entry_stair,9,2);
			//bruhMap.setBossSpawn(roomNineBoss1, boss1, bbb);
			bruhMap.setInteractions(roomNineInteraction1, E16, h);
			bruhMap.setInteractions(roomNineInteraction2, stairs1, h);
			}
		}	
	}
	
	public Coordinates getEnemies(Enemy enemies) {
		return enemyStorage.get(enemies);
	}
	
	public Coordinates getBoss(Boss boss) {
		return bossStorage.get(boss);
	}
	
	public Coordinates getCoordinateFromHash(Interactions input_interactions) {
		return interactionStorage.get(input_interactions);
	}

}	

