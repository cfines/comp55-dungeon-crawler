package enemyInteraction;

public enum enemyType {
	EARTHBat, FIREBat, WATERBat, EARTHSkull, FIRESkull, 
	WATERSkull, rip, EARTHSpider, WATERSpider, FIRESpider, EARTHSpooder,
	WATERSpooder, FIRESpooder, EARTHDrawing, WATERDrawing, FIREDrawing,
	xokStill, xokAttack, EARTHDeath, WATERDeath, FIREDeath, EARTHCheese, WATERCheese, FIRECheese,
	EARTHFish, WATERFish,DumaCrest, FIREFish, leg, electric, projectile, insidePacific, goon1, goon2, momoko, bomb,
	Duma, Samurai, DarkMage, Brigand,GuyFieri,MargeStill, MMMHOMIE, Nagic, face, Rinea, Berkut, Dread, Deadass,
	Pleft, Pright;
	
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
			case xokStill: return "xokStill";
			case xokAttack: return "xokAttack";
			case EARTHDeath: return "EARTHDeath";
			case WATERDeath: return "WATERDeath";
			case FIREDeath: return "FIREDeath";
			case EARTHCheese: return "EARTHCheese";
			case WATERCheese: return "WATERCheese";
			case FIRECheese: return "FIRECheese";
			case EARTHFish: return "EARTHFish";
			case WATERFish: return "WATERFish";
			case FIREFish: return "FIREFish";
			case rip: return "Nullified";
			case leg: return "leg";
			case projectile: return "projectile";
			case electric: return "electric";
			case insidePacific: return "insidePacific";
			case goon1: return "goon1";
			case goon2: return "goon2";
			case momoko: return "momoko";
			case bomb: return "bomb";
			case Duma: return "Duma";
			case Samurai: return "Samurai";
			case DarkMage: return "Dark Mage";
			case Brigand: return "Brigand";
			case MargeStill: return "Marge_BOSS (idle animation)";
			case MMMHOMIE: return "Marge_BOSS (attack animation)";
			case Nagic: return "Dark Magic";
			case face: return "face";
			case Rinea: return "Rinea";
			case Berkut: return "Berkut";
			case Dread: return "Dread";
			case Deadass: return "Deadboi";
			case GuyFieri : return "Fyah";
			case DumaCrest: return "Duma Crest";
			case Pleft: return "plant_WEST";
			case Pright: return "plant_EAST";
		}
		return "n/a";
	}
}
