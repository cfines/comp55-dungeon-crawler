package starter;

import java.util.ArrayList;

public class User {
	//INSTANCE VARIABLES//
	
	//Most stats
	private Stats userStats;
	
	//User-specific stats
	private int moveSpeedStat;
	private double powerStat;
	private int atkSpeedStat;
	private int dx, dy;
	
	//Weapon Inventory variables
	private int weaponEquiped;
	private ArrayList<Weapon> weapons;
	
	//Other user-specific variables
	private int numConsumable;
	private boolean hasKey;
	private String currRoom;
	
	///////////////////////
	
	//Default Constructor
	User(){
		moveSpeedStat = 7;
		powerStat = 1;
		atkSpeedStat = 1;
		
		weaponEquiped = 0;
		weapons = new ArrayList<>();
		setupWeapons();
		
		numConsumable = 0;
		hasKey = false;
		currRoom = "R1";
	}
	
	//General Constructor
	User(int input_HP_cur, int input_HP_tot, int atkTime,
			int input_dmg, int input_x, int input_y){
		
		userStats = new Stats(input_HP_cur, input_HP_tot, atkTime, input_dmg, input_x, input_y);
		moveSpeedStat = 7;
		powerStat = 1;
		atkSpeedStat = 1;
		
		weaponEquiped = 0;
		weapons = new ArrayList<>();
		setupWeapons();
		
		numConsumable = 0;
		hasKey = false;
		currRoom = "R1";
		
	}
	
	public void tick() {
		userStats.setCoordX(userStats.getCoordX() + dx);
		userStats.setCoordY(userStats.getCoordY() + dy);
		if(userStats.getCoordX() <= 0) {
			userStats.setCoordX(0);
		}
		if(userStats.getCoordX() >= 1155 - 60) {
			userStats.setCoordX(1155 - 60);
		}
		if(userStats.getCoordY() <= 0) {
			userStats.setCoordY(0);
		}
		if(userStats.getCoordY() >= 650 - 80) {
			userStats.setCoordY(650 - 80);
		}
	}
	
	public void setDX(int input_dx) {
		dx = input_dx;
	}
	
	public void setDY(int input_dy) {
		dy = input_dy;
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
	
	public int getWeaponEquiped() {
		return weaponEquiped;
	}
	
	public String getWeaponEquipedString() {
		return weapons.get(weaponEquiped).toString();
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
	
	public int getCoordX() {
		return userStats.getCoordX();
	}
	
	public int getCoordY() {
		return userStats.getCoordY();
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
	public void cycleWeapon() {
		
		//Loop back to beginning of weapons array if applicable
		if(weaponEquiped == 2) {
			weaponEquiped = 0;
		} else {
			weaponEquiped++;
		}

		
	}
	
	public String getCurrRoom() {
		return currRoom;
	}
	
	public void setCurrRoom(String currRoom) {
		this.currRoom = currRoom;
	}

}
