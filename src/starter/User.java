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
	private double dx = 0;
	private double dy = 0;
	private double x;
	private double y;
	
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
	public User(int input_HP_cur, int input_HP_tot, int atkTime,
			int input_dmg, double input_x, double input_y){
		
		userStats = new Stats(input_HP_cur, input_HP_tot, atkTime, input_dmg, input_x, input_y);
		x = input_x;
		y = input_y;
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
		x+= dx;
		y+= dy;
		/*
		 * userStats.setCoordX(userStats.getCoordX() + dx);
		 * userStats.setCoordY(userStats.getCoordY() + dy);
		 */
		if(getX() <= 125-60) {
			setX(125-60);
		}
		if(getX() >= 1065 - 60) {
			setX(1065 - 60);
		}
		if(getY() <= 95-60) {
			setY(95-60);
		}
		if(getY() >= 520-80) {
			setY(520-80);
		}
	}
	
	public void setDX(double dx) {
		this.dx = dx;
	}
	
	public void setDY(double dy) {
		this.dy = dy;
	}
	
	public double getDX() {
		return dx;
	}
	
	public double getDY() {
		return dy;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
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
	
	public Weapon getCurWeapon() {
		return weapons.get(weaponEquiped);
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
	
	public double getCoordX() {
		return userStats.getCoordX();
	}
	
	public double getCoordY() {
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
