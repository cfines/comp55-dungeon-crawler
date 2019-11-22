package starter;

public enum enemyType {
	EARTHBat, FIREBat, WATERBat, EARTHSkull, FIRESkull, 
	WATERSkull, rip, EARTHSpider, WATERSpider, FIRESpider, EARTHSpooder,
	WATERSpooder, FIRESpooder, EARTHDrawing, WATERDrawing, FIREDrawing;
	
	public String toString() {
		switch(this) {
			case EARTHBat: return "EARTHBat";
			case FIREBat: return "FIREBat";
			case WATERBat: return "WATERBat";
			case EARTHSkull: return "EARTHSkull";
			case FIRESkull: return "FIRESkull";
			case WATERSkull: return "WATERSkull";
			case EARTHSpider: return "EARTHSpider";
			case WATERSpider: return "WATERSpider";
			case FIRESpider: return "FIRESpider";
			case EARTHSpooder: return "EARTHSpooder";
			case WATERSpooder: return "WATERSpooder";
			case FIRESpooder: return "FIRESpooder";
			case EARTHDrawing: return "EARTHDrawing";
			case WATERDrawing: return "WATERDrawing";
			case FIREDrawing: return "FIREDrawing";
			
			case rip: return "rip";
		}
		return "n/a";
	}
}
