package starter;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class TitleScreenPane extends GraphicsPane {

	private GImage tittle = new GImage("Title Screen.png",0,0);
	private MainApplication program;
	
	public TitleScreenPane(MainApplication app) 
	{
		this.program = app;
		tittle.setSize(1150,650);
	}
	
	@Override
	public void showContents() {
		program.add(tittle);
		
	}

	@Override
	public void hideContents() {
		program.remove(tittle);
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == tittle) 
		{
			program.switchToMenu();
		}
	}

}
