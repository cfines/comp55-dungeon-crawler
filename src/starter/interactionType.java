package starter;

public enum interactionType {
	entry_door, entry_stair, entry_bossDoor, obstacle_rock, obstacle_hole, item_gif_key;
	
	public String toString() {
		switch(this) {
			case entry_door: return "entry_door";
			case entry_stair: return "entry_stair";
			case obstacle_rock: return "obstacle_rock";
			case obstacle_hole: return "obstacle_hole";
			case item_gif_key: return "item_gif_key";
			case entry_bossDoor: return "entry_bossDoor";
		}
		return "n/a";
	}
}

