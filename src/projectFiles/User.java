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
		
		arrEquiped = 0;
		setupWeapons();
		
		numConsumable = 0;
		hasKey = false;
	}
	
	//General Constructor
	User(int input_HP_cur, int input_HP_tot, int atkTime,
			int input_dmg, int input_x, int input_y){
		
		userStats = new Stats(input_HP_cur, input_HP_tot, atkTime, input_dmg, input_x, input_y);
		moveSpeedStat = 7;
		powerStat = 1;
		atkSpeedStat = 1;
		
		arrEquiped = 0;
		weapons = new ArrayList<>();
		setupWeapons();
		
		numConsumable = 0;
		hasKey = false;
		
	}

	//GETTERS AND SETTERS//
	
	public Stats getUserStats() {
		return userStats;
	}

	public void setUserStats(Stats userStats) {
		this.userStats = userStats;
	}

	public int getMoveSpeedStat() {
		return moveSpeedStat;
	}

	public void setMoveSpeedStat(int moveSpeedStat) {
		this.moveSpeedStat = moveSpeedStat;
	}

	public double getPowerStat() {
		return powerStat;
	}

	public void setPowerStat(double powerStat) {
		this.powerStat = powerStat;
	}

	public int getAtkSpeedStat() {
		return atkSpeedStat;
	}

	public void setAtkSpeedStat(int atkSpeedStat) {
		this.atkSpeedStat = atkSpeedStat;
	}

	public ArrayList<Weapon> getWeapons() {
		return weapons;
	}

	public int getNumConsumable() {
		return numConsumable;
	}

	public void setNumConsumable(int numConsumable) {
		this.numConsumable = numConsumable;
	}

	public boolean getHasKey() {
		return hasKey;
	}

	public void setHasKey(boolean hasKey) {
		this.hasKey = hasKey;
	}
	
	/////////////////////////////////////////
	
	//Creation of weapons and storing them in weapons array
	public void setupWeapons() {
		
		Weapon fireWeapon = new Weapon(ElementType.FIRE);
		Weapon waterWeapon = new Weapon(ElementType.WATER);
		Weapon earthWeapon = new Weapon(ElementType.EARTH);
		
		weapons.add(fireWeapon);
		weapons.add(waterWeapon);
		weapons.add(earthWeapon);
		
	}
	
	//Cycle of weapons for quickslot
	public Weapon cycleWeapon() {
		
		//Loop back to beginning of weapons array if applicable
		if(arrEquiped == 2) {
			arrEquiped = 0;
		} else {
			arrEquiped++;
		}
		
		return weapons.get(arrEquiped);
		
	}
	
	public void moveX(int move) {
		userStats.setCoordX(userStats.getCoordX() + move); 
	}
	
	public void moveY(int move) {
		userStats.setCoordY(userStats.getCoordY() + move);
	}
	
}
