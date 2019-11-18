package RoomPanes;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.TODO;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GraphicsPane;
import starter.MainApplication;

public class mapBase_R5 extends GraphicsPane{
	private MainApplication program;
	private GImage enemy1, hole1, E8, E9, E10, background;
	private ArrayList<GImage> elements = new ArrayList<GImage>();

	public mapBase_R5(MainApplication app) {
		this.program = app;
		enemy1 = new GImage("FIRESkull.png", 575,325);
		hole1 = new GImage("obstacle_hole.png",230,325);
		E8 = new GImage("entry_door_NORTH.png",575,60);
		E9 = new GImage("entry_door_SOUTH.png",575,490);
		E10 = new GImage("entry_door_EAST.png",1040,300);
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		
		enemy1.setSize(50,50);
		hole1.setSize(50, 50);
		E8.setSize(50,50);
		E9.setSize(50, 50);
		E10.setSize(50, 50);
		background.setSize(1125, 550);
		
		elements.add(background);
		elements.add(enemy1);
		elements.add(hole1);
		elements.add(E8);
		elements.add(E9);
		elements.add(E10);
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
		if (obj == E8) {
			program.switchToR4();
		}
//		else if(obj == E9) {
//			program.switchTo??();
//		}
//		else if(obj == E10) {
//			program.switchTo??
//		}
	}
}
