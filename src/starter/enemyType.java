package starter;

public enum enemyType {
	EARTHBat, FIREBat, WATERBat, EARTHSkull, FIRESkull, WATERSkull;
	
	public String toString() {
		switch(this) {
			case EARTHBat: return "EARTHBat";
			case FIREBat: return "FIREBat";
			case WATERBat: return "WATERBat";
			case EARTHSkull: return "FIRESkull";
			case FIRESkull: return "FIRESkull";
			case WATERSkull: return "WATERSkull";
		}
		return "n/a";
	}
}
