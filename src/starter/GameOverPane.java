package starter;

import acm.graphics.GImage;

public class GameOverPane extends GraphicsPane{
	private GImage gameOver;
	private MainApplication program;
	private GButton returnMenu;
	
	public GameOverPane(MainApplication app) 
	{
		this.program = app;
		gameOver = new GImage ("Game Over Screen.png",0,0);
		gameOver.setSize(1150,650);
		returnMenu = new GButton("Return to main menu", 400, 100, program.WINDOW_HEIGHT/2, 3*program.WINDOW_WIDTH/4);
	}
	
	@Override
	public void showContents() {
		program.add(gameOver);
	}

	@Override
	public void hideContents() {
		program.remove(gameOver);
	}

}
