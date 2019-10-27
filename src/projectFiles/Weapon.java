package projectFiles;

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
	
}
