package projectFiles;

//remember, these things will be inside of room as we going to treat
//them like obstacles
public class Interactions {
	interactionType inter;
	
	public Interactions(interactionType i)
	{
		inter = i;
	}
	
	// gets the interaction type from the interactionType enum
	public interactionType getinteractionType() {
		return inter;
	}
}
