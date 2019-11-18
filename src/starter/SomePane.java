package starter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class SomePane extends GraphicsPane {
		private MainApplication program;
		private GImage rock1, rock2, hole1, E1, background, userRep;
		private ArrayList<GImage> elements = new ArrayList<GImage>();

	public SomePane(MainApplication app) {
		this.program = app;
		background = new GImage("Base_Floor (Tutorial Floor).png", 15,30);
		rock1 = new GImage("obstacle_rock.png",170,189);
		rock2 = new GImage("obstacle_rock.png",890,200);
		hole1 = new GImage("obstacle_hole.png",172,425);
		E1 = new GImage("entry_door_EAST.png",1040,300);
		userRep = new GImage("Rogue_(Sample User).gif", 500, 500);
		userRep.setSize(75, 75);
		rock1.setSize(75, 75);
		rock2.setSize(75, 75);
		hole1.setSize(75, 75);
		E1.setSize(75, 75);
		background.setSize(1125, 550);
		
		elements.add(background);
		elements.add(rock1);
		elements.add(rock2);
		elements.add(hole1);
		elements.add(E1);
		elements.add(userRep);
	}

	@Override
	public void showContents() {
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
	}

	@Override
	public void hideContents() {
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.remove(elements.get(i));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == E1) {
			program.switchToR2();
		}
		else if(obj == rock1) {
			program.switchToMenu();
		}
	}
}
