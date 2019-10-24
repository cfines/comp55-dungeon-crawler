package projectFiles;

public enum ElementType {
FIRE, WATER, EARTH;
	
	public String toString() {
		switch(this) {
			case FIRE: return "fire";
			case WATER: return "water";
			case EARTH: return "earth";
		}
		return "n/a";
	}
}
