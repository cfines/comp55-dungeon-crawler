package starter;

public enum interactionType {
	entry_door_EAST, entry_door_NORTH, entry_door_SOUTH, entry_door_WEST, entry_stair, 
	entry_bossDoor, obstacle_rock, obstacle_hole, item_gif_key, rip, rip2, obstacle_concrete_rocks,
	obstacle_concrete_rubble, nullified;
	
	public String toString() {
		switch(this) {
			case entry_door_EAST: return "entry_door_EAST";
			case entry_door_NORTH: return "entry_door_NORTH";
			case entry_door_SOUTH: return "entry_door_SOUTH";
			case entry_door_WEST: return "entry_door_WEST";
			case entry_stair: return "entry_stair";
			case obstacle_rock: return "obstacle_rock";
			case obstacle_hole: return "obstacle_hole";
			case item_gif_key: return "item_gif_key";
			case entry_bossDoor: return "entry_bossDoor";
			case rip: return "rip";
			case rip2: return "rip2";
			case obstacle_concrete_rocks: return "obstacle_concrete_rocks";
			case obstacle_concrete_rubble: return "obstacle_concrete_rubble";
			case nullified: return "Nullified";
		}
		return "n/a";
	}
}

