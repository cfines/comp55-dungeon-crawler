package projectFiles;

public enum ElementType {
FIRE, WATER, EARTH;
	
	public String toString() {
		switch(this) {
			case FIRE: return "FIRE";
			case WATER: return "WATER";
			case EARTH: return "EARTH";
		}
		return "n/a";
	}
}
