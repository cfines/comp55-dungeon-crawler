package RoomPanes;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GraphicsPane;
import starter.MainApplication;

public class mapBase_R4 extends GraphicsPane{
	private MainApplication program;
	private GImage enemy1, enemy2, enemy3, hole1, rock1, E6, E7, background;
	private ArrayList<GImage> elements = new ArrayList<GImage>();

	public mapBase_R4(MainApplication app) {
		this.program = app;
		enemy1 = new GImage("FIRESkull.png", 575,216);
		enemy2 = new GImage("WATERSkull.png",575,434);
		enemy3 = new GImage("EARTHSkull.png",863,434);
		hole1 = new GImage("obstacle_hole.png",900,100);
		rock1 = new GImage("obstacle_rock.png",230,490);
		E6 = new GImage("entry_door_WEST.png",60,300);
		E7 = new GImage("entry_door_EAST.png",575,490);
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		
		enemy1.setSize(50,50);
		enemy2.setSize(50, 50);
		enemy3.setSize(50, 50);
		hole1.setSize(50, 50);
		rock1.setSize(50, 50);
		E6.setSize(50,50);
		E7.setSize(50, 50);
		background.setSize(50, 50);
		
		elements.add(background);
		elements.add(enemy1);
		elements.add(enemy2);
		elements.add(enemy3);
		elements.add(hole1);
		elements.add(rock1);
		elements.add(E6);
		elements.add(E7);
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
		if (obj == E6) {
			program.switchToR3();
		}
		else if(obj == E7) {
			//program.switchToR5();
		}
	}
}
