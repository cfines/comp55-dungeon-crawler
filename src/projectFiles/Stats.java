package projectFiles;

import java.util.Timer; 

public class Stats {
	private int HP_cur, HP_tot;
	private Timer atkTimer;
	private int baseDamage;
	private int coordX, coordY;
	
	Stats () {
		HP_cur = HP_tot = 1;
		atkTimer = new Timer(); //add arguments to Timer later
		baseDamage = 1;
		coordX = coordY = 1;
	}
	
	public int getHP_cur() {
		return HP_cur;
	}
	public void setHP_cur(int hP_cur) {
		HP_cur = hP_cur;
	}
	public int getHP_tot() {
		return HP_tot;
	}
	public void setHP_tot(int hP_tot) {
		HP_tot = hP_tot;
	}
	public Timer getAtkTimer() {
		return atkTimer;
	}
	public void setAtkTimer(Timer atkTimer) {
		this.atkTimer = atkTimer;
	}
	public int getBaseDamage() {
		return baseDamage;
	}
	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}
	public int getCoordX() {
		return coordX;
	}
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}
	public int getCoordY() {
		return coordY;
	}
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	
}
