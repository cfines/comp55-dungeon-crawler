package RoomPanes;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GraphicsPane;
import starter.MainApplication;

public class mapBase_R7 extends GraphicsPane{
	private MainApplication program;
	private GImage rock1, E12, E13, enemy1, enemy2, enemy3, background;
	private ArrayList<GImage> elements = new ArrayList<GImage>();

	public mapBase_R7(MainApplication app) {
		this.program = app;
		rock1 = new GImage("obstacle_rock.png",300,200);
		E12 = new GImage("entry_door_NORTH.png",60,300);
		E13 = new GImage("entry_door_EAST.png",1040,300);
		enemy1 = new GImage("EARTHSkull.png", 575,100);
		enemy2 = new GImage("EARTHSkull.png", 575,250);
		enemy3 = new GImage("EARTHSkull.png", 575,420);
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		
		enemy1.setSize(50,50);
		enemy2.setSize(50,50);
		enemy3.setSize(50, 50);
		E12.setSize(50,50);
		E13.setSize(50, 50);
		rock1.setSize(50, 50);
		background.setSize(1125, 550);
		
		elements.add(background);
		elements.add(enemy1);
		elements.add(enemy2);
		elements.add(enemy3);
		elements.add(rock1);
		elements.add(E12);
		elements.add(E13);
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
		if (obj == E12) {
			program.switchToR5();
		}
//		else if(obj == E13) {
//			program.switchToR8();
//		}
	}
}
