package starter;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;

public class SomePane extends GraphicsPane {
		private MainApplication program;
		private GImage rock1, rock2, hole1, E1, background, userRep, enemy1;
		private ArrayList<GImage> elements = new ArrayList<GImage>();
		private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
		private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
		private ArrayList<GRect> space = new ArrayList<GRect>();
		private ArrayList<GImage> you = new ArrayList<GImage>();
		private GRect voidSpace;
		private Console game;
		
	public SomePane(MainApplication app) {
		this.program = app;
		Interactions irock1 = new Interactions(interactionType.obstacle_rock, 170,189);
		Interactions irock2 = new Interactions(interactionType.obstacle_rock, 890, 200);
		Interactions ihole1 = new Interactions(interactionType.obstacle_hole, 172,425);
		Interactions iE1 = new Interactions(interactionType.entry_door_EAST, 1040,300);
		Enemy ienemy1 = new Enemy(2,2,2,2,350,76, ElementType.FIRE);
		listOfInter.add(irock1);
		listOfInter.add(irock2);
		listOfInter.add(ihole1);
		listOfInter.add(iE1);
		listOfEnemies.add(ienemy1);
	
		background = new GImage("Base_Floor (Tutorial Floor).png", 15,30);
		rock1 = irock1.getImage();
		rock2 = irock2.getImage();
		hole1 = ihole1.getImage();
		E1 = iE1.getImage();
		userRep = new GImage("Rogue_(Sample User).gif");
		enemy1 = ienemy1.getImage();
		userRep.setSize(75, 75);
		background.setSize(1125, 550);
		
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		game = new Console();
		
		space.add(voidSpace);
		elements.add(background);
		elements.add(rock1);
		elements.add(rock2);
		elements.add(hole1);
		elements.add(E1);
		elements.add(enemy1);
		you.add(userRep);
	}

	@Override
	public void showContents() {
		program.add(space.get(0));
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		for (Interactions inter : listOfInter) {
			program.add(inter.getImage());
		}
		program.add(enemy1);
		userRep.setLocation(game.getUser().getCoordX(), game.getUser().getCoordY());
		program.add(you.get(0));
		
	}

	@Override
	public void hideContents() {
		program.remove(space.get(0));
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.remove(elements.get(i));
		}
		program.remove(you.get(0));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == E1) {
			program.switchToR2();
			userRep.setLocation(90, 300);
		}
		else if(obj == rock1) {
			program.switchToMenu();
		}
	}
	
	
}
