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
	
	private void setAtkSpd() {
		atkSpd = player.getAtkSpeedStat();
	}
	
	private void setMoveSpd() {
		moveSpd = player.getMoveSpeedStat();
	}
	
	private void setPower() {
		power = player.getPowerStat();
	}
	
	private void setHP() {
		hp = player.getUserStats().getHP_tot();
	}
	
//TODO change the stat values depending on which power-up is picked up
	private Stats changeStat() {
		switch(type) {
		case HPRECOVERY: 
		case ATKSPEED: 
			//Powerups increase stat by one (can change later)
			atkSpd += 1;
			player.setAtkSpeedStat(atkSpd);
		case MOVESPEED: 
			moveSpd += 1;
			player.setMoveSpeedStat(moveSpd);
		case POWERSTAT: 
			power += 1;
			player.setPowerStat(power);
		case HPINCREASE: 
			hp += 1;
			player.set
		}
	}
}
