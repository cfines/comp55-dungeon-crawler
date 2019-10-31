package projectFiles;

public enum itemType {
HPRECOVERY, ATKSPEED, MOVESPEED, POWERSTAT, HPINCREASE;
	
	public String toString() {
		switch(this) {
			case HPRECOVERY: return "active_hprecovery";
			case ATKSPEED: return "passive_atkspeed";
			case MOVESPEED: return "passive_movespeed";
			case POWERSTAT: return "passive_powerstat";
			case HPINCREASE: return "passive_hpincrease";
		}
		return "n/a";
	}
}
