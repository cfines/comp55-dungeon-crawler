package removeLater;

public enum itemType {
HPRECOVERY, ATKSPEED, MOVESPEED, POWERSTAT, HPINCREASE, KEY;
	
	public String toString() {
		switch(this) {
			case HPRECOVERY: return "active_hprecovery";
			case ATKSPEED: return "atkspeed";
			case MOVESPEED: return "movespeed";
			case POWERSTAT: return "powerstat";
			case HPINCREASE: return "hpincrease";
			case KEY: return "key";
		}
		return "n/a";
	}
}
