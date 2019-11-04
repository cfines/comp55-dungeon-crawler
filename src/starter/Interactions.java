package starter;

//remember, these things will be inside of room as we going to treat
//them like obstacles
public class Interactions {
	interactionType inter;
	int roomNum;
	int numInter;
	
	public Interactions(interactionType i, int roomNum, int numInter)
	{
		inter = i;
		this.roomNum = roomNum;
		this.numInter = numInter;
	}
	
	// gets the interaction type from the interactionType enum
	public interactionType getinteractionType() {
		return inter;
	}

	@Override
	public String toString() {
		return "room" + roomNum + "interaction" + numInter;
	}
	
}
