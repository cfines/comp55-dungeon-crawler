package starter;
import acm.graphics.GImage;

//Mainly just a test to see if making the user into a pane would work

//Not final
public class UserPane
{	
	private GImage userRep;
	private MainApplication program;
	private Console game;
	
	public UserPane() 
	{
		game = new Console();
		game.playGame();
		userRep = new GImage("Rogue_(Sample User).gif", game.getUser().getCoordX(), game.getUser().getCoordY());
		userRep.setSize(75,75);
	}
	
	public void showUser() 
	{
		program.add(userRep);
	}

	public void hideUser() 
	{
		program.remove(userRep);
	}
}
