package enemyInteraction;

public enum interactionType {
	entry_door_EAST, entry_door_NORTH, entry_door_SOUTH, entry_door_WEST, entry_stair, 
	entry_bossDoor, entry_bossDoor_SOUTH, entry_bossDoor_EAST, entry_bossDoor_WEST, obstacle_rock, obstacle_hole, item_gif_key, rip, rip2, obstacle_concrete_rocks,
	obstacle_concrete_rubble, nullified, color1, color2, face, chrisEntry_NORTH, chrisEntry_SOUTH,
	chrisEntry_EAST, chrisEntry_WEST, candle1, candle2, candle3, treeFell,momoko, monoe, poniko, blueboi, schlept,
	madotsuki,tree,statue, Umbrella_Egg, Long_Hair_Egg, Buyo_buyo_Egg;
	
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
			case color1: return "color1";
			case color2: return "color2";
			case face: return "face";
			case chrisEntry_NORTH: return "chrisEntry_NORTH";
			case chrisEntry_SOUTH: return "chrisEntry_SOUTH";
			case chrisEntry_EAST: return "chrisEntry_EAST";
			case chrisEntry_WEST: return "chrisEntry_WEST";
			case candle1: return "candle1";
			case candle2: return "candle2";
			case candle3: return "candle3";
			case momoko: return "momoko";
			case monoe: return "monoe";
			case poniko: return "poniko";
			case blueboi: return "blueboi";
			case schlept: return "schlept";
			case madotsuki: return "madotsuki";
			case tree: return "Tree";
			case treeFell: return "Tree_boolin";
			case statue: return "Statue";
			case Umbrella_Egg: return "Umbrella_Egg";
			case Long_Hair_Egg: return "Long_Hair_Egg";
			case Buyo_buyo_Egg: return "Buyo_buyo_Egg";
			case entry_bossDoor_EAST: return "entry_bossDoor_EAST";
			case entry_bossDoor_SOUTH: return "entry_bossDoor_SOUTH";
			case entry_bossDoor_WEST: return "entry_bossDoor_WEST";
		}
		return "n/a";
	}
}

