package removeLater;

import enemyInteraction.ElementType;

public class Weapon {
	private ElementType wepType;
	
	Weapon(){
		wepType = null;
	}
	
	Weapon(ElementType type){
		wepType = type;
	}
	
	public ElementType getWepType() {
		return wepType;
	}

	@Override
	public String toString() {
		return wepType.toString();
	}
	
}
