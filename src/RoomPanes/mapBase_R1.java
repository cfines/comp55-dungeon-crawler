package RoomPanes;

import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GraphicsPane;
import starter.MainApplication;

public class mapBase_R1 extends GraphicsPane{
	private MainApplication program;
	
	private GImage rock1;
	
	public mapBase_R1(MainApplication app) {
		this.program = app;
		rock1 = new GImage("obstacle_rock.png", 50, 50);
	}
	
	
	
	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(rock1);
		
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		program.remove(rock1);
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == rock1) {
			program.switchToMenu();
		}
	}
	
}
