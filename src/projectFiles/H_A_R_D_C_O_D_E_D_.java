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
	
	public void runBase() 
	{
		//roomID parameter if else verification
		
		//Room 1
		//if()
		{
		Coordinates rock1 = new Coordinates (70,89);
		Coordinates rock2 = new Coordinates (780,301);
		Coordinates hole1 = new Coordinates (72,301);
		Coordinates entry1 = new Coordinates (1150, 325);
		Interactions rockOne = new Interactions(interactionType.obstacle_rock);
		Interactions rockTwo = new Interactions(interactionType.obstacle_rock);
		Interactions holeOne = new Interactions(interactionType.obstacle_hole);
		Interactions entryOne = new Interactions(interactionType.entry_door);
		map.setInteractions(rockOne, rock1, interactionStorage);
		map.setInteractions(rockTwo, rock2, interactionStorage);
		map.setInteractions(holeOne, hole1, interactionStorage);
		map.setInteractions(entryOne, entry1, interactionStorage);
		}
		
		//Room 2
		//else if()
		{
		Coordinates rock3 = new Coordinates (80, 90);
		Coordinates hole2 = new Coordinates (500,91);
		Coordinates hole3 = new Coordinates (75, 500);
		Coordinates enemy1 = new Coordinates (350, 76);
		Coordinates enemy2 = new Coordinates (367, 504);
		Coordinates entry2 = new Coordinates (0, 325);
		Coordinates entry3 = new Coordinates (1150, 325);
		Interactions rockThree = new Interactions(interactionType.obstacle_rock);
		Interactions holeTwo = new Interactions(interactionType.obstacle_hole);
		Interactions holeThree = new Interactions(interactionType.obstacle_hole);
		Enemy enemyOne = new Enemy(1, 9, 6, 5, 4, 2, ElementType.EARTH);
		Enemy enemyTwo = new Enemy(5,0,0,5,1,3, ElementType.FIRE);
		Interactions entryTwo = new Interactions(interactionType.entry_door);
		Interactions entryThree = new Interactions(interactionType.entry_door);
		map.setInteractions(rockThree, rock3, interactionStorage);
		map.setInteractions(holeTwo, hole2, interactionStorage);
		map.setInteractions(holeThree, hole3, interactionStorage);
		map.setEnemySpawn(enemyOne, enemy1, enemyStorage);
		map.setEnemySpawn(enemyTwo, enemy2, enemyStorage);
		map.setInteractions(entryTwo, entry2, interactionStorage);
		map.setInteractions(entryThree, entry3, interactionStorage);
		}
		
		//Room 3
		//else if()
		{
		Coordinates stairs1 = new Coordinates (575,325);
		Coordinates enemy3 = new Coordinates (800, 70);
		Coordinates entry4 = new Coordinates (0, 325);
		Interactions stairsOne = new Interactions(interactionType.entry_stair);
		Enemy enemyThree = new Enemy(6,9,6,9,6,9, ElementType.WATER);
		Interactions entryFour = new Interactions(interactionType.entry_door);
		map.setInteractions(stairsOne, stairs1, interactionStorage);
		map.setEnemySpawn(enemyThree, enemy3, enemyStorage);
		map.setInteractions(entryFour, entry4, interactionStorage);
		}
	}
}	

