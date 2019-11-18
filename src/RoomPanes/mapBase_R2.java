package RoomPanes;

import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GraphicsPane;
import starter.MainApplication;

public class mapBase_R2 extends GraphicsPane{
	private MainApplication program;
	private GImage rock1, hole2, hole1, E2, E3, background, enemy1, enemy2;
	
	public mapBase_R2(MainApplication app) {
		this.program = app;
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		rock1 = new GImage("obstacle_rock.png",100,9);
		hole2 = new GImage("obstacle_hole.png",500,91);
		hole1 = new GImage("obstacle_hole.png",575,400);
		E2 = new GImage("entry_door_WEST.png",60,300);
		E3 = new GImage("entry_door_EAST.png",1040,300);
		enemy1 = new GImage("FIRESkull.png", 350,76);
		enemy2 = new GImage("FIRESkull.png", 367,504);
		enemy1.setSize(50,50);
		enemy2.setSize(50,50);
		rock1.setSize(50, 50);
		hole2.setSize(50, 50);
		hole1.setSize(50, 50);
		E2.setSize(50, 50);
		E3.setSize(50, 50);
		background.setSize(1125, 550);
	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(background);
		program.add(rock1);
		program.add(hole1);
		program.add(hole2);
		program.add(E2);
		program.add(E3);
		program.add(enemy1);
		program.add(enemy2);
		
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		program.remove(background);
		program.remove(rock1);
		program.remove(hole1);
		program.remove(hole2);
		program.remove(E2);
		program.remove(E3);
		program.remove(enemy1);
		program.remove(enemy2);
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == E2) {
			program.switchToSome();
		}
		else if(obj == E3) {
			program.switchToR3();
		}
	}

}
