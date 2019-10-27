package projectFiles;

import java.util.ArrayList;

public class User {
	//INSTANCE VARIABLES//
	
	//Most stats
	private Stats userStats;
	
	//User-specific stats
	private int moveSpeedStat;
	private double powerStat;
	private int atkSpeedStat;
	
	//Weapon Inventory variables
	private int arrSize;
	private int arrEquiped;
	private ArrayList<Weapon> weapons;
	
	//Other user-specific variables
	private int numConsumable;
	private boolean hasKey;
	
	///////////////////////
	
	//Default Constructor
	User(){
		moveSpeedStat = 1;
		powerStat = 1;
		atkSpeedStat = 1;
		
		arrSize = 3;
		arrEquiped = 0;
		weapons = new ArrayList<>(arrSize);
		
		numConsumable = 0;
		hasKey = false;
	}
	
	//General Constructor
	User(int input_HP_cur, int input_HP_tot, int atkTime,
			int input_dmg, int input_x, int input_y){
		
		userStats = new Stats(input_HP_cur, input_HP_tot, atkTime, input_dmg, input_x, input_y);
		moveSpeedStat = 1;
		powerStat = 1;
		atkSpeedStat = 1;
		
		arrSize = 3;
		arrEquiped = 0;
		weapons = new ArrayList<>(arrSize);
		
		numConsumable = 0;
		hasKey = false;
		
	}
	
}
