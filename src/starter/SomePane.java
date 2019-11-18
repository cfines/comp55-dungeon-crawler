package starter;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class SomePane extends GraphicsPane {
		private MainApplication program;
	
		private GImage rock1, rock2, hole1, E1, background;

	public SomePane(MainApplication app) {
		this.program = app;
		background = new GImage("Base_Floor (Tutorial Floor).png", 15,30);
		rock1 = new GImage("obstacle_rock.png",170,189);
		rock2 = new GImage("obstacle_rock.png",890,200);
		hole1 = new GImage("obstacle_hole.png",172,425);
		E1 = new GImage("entry_door_EAST.png",1040,300);
		rock1.setSize(50, 50);
		rock2.setSize(50, 50);
		hole1.setSize(50, 50);
		E1.setSize(50, 50);
		background.setSize(1125, 550);
	}

	@Override
	public void showContents() {
		program.add(background);
		program.add(rock1);
		program.add(rock2);
		program.add(hole1);
		program.add(E1);
	}

	@Override
	public void hideContents() {
		program.remove(background);
		program.remove(rock1);
		program.remove(rock2);
		program.remove(hole1);
		program.remove(E1);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == E1) {
			program.switchToMenu();
		}
	}
}
