package starter;

public enum enemyType {
	EARTHBat, FIREBat, WATERBat, EARTHSkull, FIRESkull, WATERSkull, rip2;
	
	public String toString() {
		switch(this) {
			case EARTHBat: return "EARTHBat";
			case FIREBat: return "FIREBat";
			case WATERBat: return "WATERBat";
			case EARTHSkull: return "EARTHSkull";
			case FIRESkull: return "FIRESkull";
			case WATERSkull: return "WATERSkull";
			case rip2: return "rip2";
		}
		return "n/a";
	}
}
