package RoomPanes;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.Console;
import starter.GraphicsPane;
import starter.MainApplication;

public class mapBase_R2 extends GraphicsPane{
	private Console game;
	private MainApplication program;
	private GImage rock1, hole2, hole1, E2, E3, background, enemy1, enemy2, userRep;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	
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
		//userRep = new GImage("Rogue_(Sample User).gif", game.getUser().getCoordX(), game.getUser().getCoordY());
		//userRep.setSize(75, 75);
		enemy1.setSize(50,50);
		enemy2.setSize(50,50);
		rock1.setSize(50, 50);
		hole2.setSize(50, 50);
		hole1.setSize(50, 50);
		E2.setSize(50, 50);
		E3.setSize(50, 50);
		background.setSize(1125, 550);
		
		elements.add(background);
		elements.add(rock1);
		elements.add(hole1);
		elements.add(hole2);
		elements.add(E2);
		elements.add(E3);
		elements.add(enemy1);
		elements.add(enemy2);
		
	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.remove(elements.get(i));
		}
		
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
