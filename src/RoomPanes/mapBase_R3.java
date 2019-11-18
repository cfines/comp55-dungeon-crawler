package RoomPanes;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GraphicsPane;
import starter.MainApplication;

public class mapBase_R3 extends GraphicsPane{
	private MainApplication program;
	private GImage enemy1, enemy2, E4, E5, rock1, hole1, background;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	
	public mapBase_R3(MainApplication app) {
		this.program = app;
		enemy1 = new GImage("EARTHSkull.png", 800,70);
		enemy2 = new GImage("WATERSkull.png",575,487);
		E4 = new GImage("entry_door_WEST.png",60,300);
		E5 = new GImage("entry_door_EAST.png",1040,300);
		rock1 = new GImage("obstacle_rock.png",575,325);
		hole1 = new GImage("obstacle_hole.png",230,163);
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		enemy1.setSize(50, 50);
		enemy2.setSize(50,50);
		E4.setSize(50, 50);
		E5.setSize(50, 50);
		rock1.setSize(50, 50);
		hole1.setSize(50, 50);
		background.setSize(1125, 550);
		
		elements.add(background);
		elements.add(enemy1);
		elements.add(enemy2);
		elements.add(E4);
		elements.add(E5);
		elements.add(rock1);
		elements.add(hole1);
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
		if (obj == E4) {
			program.switchToR2();
		}
		else if(obj == E5) {
			program.switchToMenu();
		}
	}

}
