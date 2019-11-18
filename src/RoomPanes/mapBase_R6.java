package RoomPanes;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GraphicsPane;
import starter.MainApplication;

public class mapBase_R6 extends GraphicsPane{
	private MainApplication program;
	private GImage rock1, E11, key1, background;
	private ArrayList<GImage> elements = new ArrayList<GImage>();

	public mapBase_R6(MainApplication app) {
		this.program = app;
		rock1 = new GImage("obstacle_rock.png",900,150);
		E11 = new GImage("entry_door_NORTH.png",575,60);
		key1 = new GImage("item_gif_key.gif", 575,300);
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		
		rock1.setSize(75,75);
		key1.setSize(75, 75);
		E11.setSize(75,75);
		background.setSize(1125, 550);
		
		elements.add(background);
		elements.add(rock1);
		elements.add(E11);
		elements.add(key1);
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
		if (obj == E11) {
			program.switchToR5();
		}
	}
}
