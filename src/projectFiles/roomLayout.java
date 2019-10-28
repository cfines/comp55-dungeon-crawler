package projectFiles;

public enum roomLayout {
	room_Norm, room_Long, room_Large, room_Gun, room_RevGun, room_UpsideGun, room_RevUpsideGun;
	
	public String toString() {
		switch(this) {
			case room_Norm: return "room_Norm";
			case room_Long: return "room_Long";
			case room_Large: return "room_Large";
			case room_Gun: return "room_Gun";
			case room_RevGun: return "room_RevGun";
			case room_UpsideGun: return "room_UpsideGun";
			case room_RevUpsideGun: return "room_RevUpsideGun";
		}
		return "n/a";
	}
}

