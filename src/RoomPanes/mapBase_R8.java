package RoomPanes;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GraphicsPane;
import starter.MainApplication;

public class mapBase_R8 extends GraphicsPane{
	private MainApplication program;
	private GImage rock1, rock2, hole1, E14, E15, background;
	private ArrayList<GImage> elements = new ArrayList<GImage>();

	public mapBase_R8(MainApplication app) {
		this.program = app;
		rock1 = new GImage("obstacle_rock.png",150,425);
		rock2 = new GImage("obstacle_rock.png",575,325);
		hole1 = new GImage("obstacle_hole.png",901,325);
		E14 = new GImage("entry_door_WEST.png",60,300);
		E15 = new GImage("entry_bossDoor.png",575,60);
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		
		rock1.setSize(75,75);
		rock2.setSize(75,75);
		hole1.setSize(75, 75);
		E14.setSize(75,75);
		E15.setSize(75, 75);
		background.setSize(1125, 550);
		
		elements.add(background);
		elements.add(rock1);
		elements.add(rock2);
		elements.add(hole1);
		elements.add(E14);
		elements.add(E15);
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
		if (obj == E14) {
			program.switchToR7();
		}
		else if(obj == E15) {
			program.switchToR9();
		}
	}
}
