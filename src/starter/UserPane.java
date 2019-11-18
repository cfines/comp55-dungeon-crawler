package starter;
import acm.graphics.GImage;

public class UserPane extends GraphicsPane
{
	private GImage userRep;
	private MainApplication program;
	private Console game;
	
	public UserPane(MainApplication app) 
	{
		userRep = new GImage("Rouge_(Sample User).gif",game.getUser().getCoordX(), game.getUser().getCoordY());
		
	}
	
	@Override
	public void showContents() {
		
	}

	@Override
	public void hideContents() {
			
	}
}
