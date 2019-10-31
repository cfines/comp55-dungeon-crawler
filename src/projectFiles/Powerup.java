package projectFiles;

public class Powerup {
	private itemType type;
	private User player;
	private int atkSpd, moveSpd, hp;
	private double power;
	
	public Powerup(itemType type) {
		this.type = type;
	}
	
	public itemType getItemType() {
		return type;
	}
	
	private int setAtkSpd() {
		atkSpd = player.getAtkSpeedStat();
	}
	
	private int setMoveSpd() {
		moveSpd = player.getMoveSpeedStat();
	}
	
	private int setPower() {
		power = player.getPowerStat();
	}
	
	private void setHP() {
		hp = player.getUserStats().getHP_tot();
	}
	
//TODO change the stat values depending on which power-up is picked up
	private Stats changeStat() {
		switch(type) {
		case HPRECOVERY: 
		case ATKSPEED: player.getUserStats().setAtkTimer(atkTimer);
		case MOVESPEED: 
		case POWERSTAT: 
		case HPINCREASE: 
		}
	}
}
