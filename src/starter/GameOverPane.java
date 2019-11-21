package starter;

import acm.graphics.GImage;

public class GameOverPane extends GraphicsPane{
	private GImage gameOver;
	private MainApplication program;
	
	public GameOverPane(MainApplication app) 
	{
		this.program = app;
		gameOver = new GImage ("Game Over Screen.png",0,0);
		gameOver.setSize(1150,650);
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
