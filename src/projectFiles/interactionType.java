package projectFiles;

public enum interactionType {
	entry_door, entry_stair, obstacle_wallShort, obstacle_wallLong, obstacle_rock, obstacle_hole;
	
	public String toString() {
		switch(this) {
			case entry_door: return "entry_door";
			case entry_stair: return "entry_stair";
			case obstacle_wallShort: return "obstacle_wallShort";
			case obstacle_wallLong: return "obstacle_wallLong";
			case obstacle_rock: return "obstacle_rock";
			case obstacle_hole: return "obstacle_hole";
		}
		return "n/a";
	}
}
