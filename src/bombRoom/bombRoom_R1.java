package bombRoom;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import RoomPanes.GraphicsPane;
import RoomPanes.KeyPressedManager;
import RoomPanes.MainApplication;
import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import enemyInteraction.ElementType;
import enemyInteraction.Enemy;
import enemyInteraction.Interactions;
import enemyInteraction.enemyType;
import enemyInteraction.interactionType;
import removeLater.User;


public class bombRoom_R1 extends GraphicsPane implements ActionListener {
	private MainApplication program;
	private GImage rock1, rock2, hole1, E1, E2, E3, E4, background, userRep, userWeapon;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private GRect voidSpace;
	private User user;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	Timer t = new Timer(30, this);
	private int decrementTimer = 0;

	private KeyPressedManager mover;

	public bombRoom_R1(MainApplication app) {
		this.program = app;
		user = program.getUser(); 
		//Interactions irock1 = new Interactions(interactionType.obstacle_concrete_rocks, 170,189);
		//Interactions irock2 = new Interactions(interactionType.obstacle_concrete_rocks, 700, 150);
		//Interactions ihole1 = new Interactions(interactionType.obstacle_hole, 172,425);
		Interactions oE1 = new Interactions(interactionType.entry_door_NORTH, 575,-3);
		Interactions oE2 = new Interactions(interactionType.entry_door_SOUTH, 575,535);
		Interactions oE3 = new Interactions(interactionType.entry_door_EAST,1050,300);
		Interactions oE4 = new Interactions(interactionType.entry_door_WEST,27,300);

		//listOfInter.add(irock1);
		//listOfInter.add(irock2);
		//listOfInter.add(ihole1);
		listOfInter.add(oE1);
		listOfInter.add(oE2);
		listOfInter.add(oE3);
		listOfInter.add(oE4);

		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		//rock1 = irock1.getImage();
		//rock2 = irock2.getImage();
		//hole1 = ihole1.getImage();
		E1 = oE1.getImage();
		E2 = oE2.getImage();
		E3 = oE3.getImage();
		E4 = oE4.getImage();

		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);

		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);

		elements.add(background);
		//elements.add(rock1);
		//elements.add(rock2);
		//elements.add(hole1);
		elements.add(E1);
		elements.add(E2);
		elements.add(E3);
		elements.add(E4);
		elements.add(userRep);

		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, elements,
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}

	@Override
	public void showContents() {
		t.start();
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		program.bombOverlay();
		program.drawOverlay(1, program.getFloorNum());
	}

	@Override
	public void hideContents() {
		t.stop();
		program.remove(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.remove(elements.get(i));
		}
		program.refreshOverlay();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == E1) {
			program.switchToR2();
			userRep.setLocation(70,300);
		}
		else if(obj == rock1) {
			user.setX(150);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToChrisR1();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mover.notReallyActionPerformed(e);
		nextRoom();
		userRep.setLocation(user.getX(), user.getY());
	}

	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userX2 = userX + 80;
		double userY2 = userX + 80;
		if(userX <= E1.getX() && userY <= E1.getY() && userX2 >= E1.getX() && userY2 >= E1.getY()) {
			user.setX(150);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToR2();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if((e.getKeyCode() == KeyEvent.VK_ESCAPE) || (e.getKeyCode() == KeyEvent.VK_Q)) {
			t.stop();
		}
		mover.notReallyKeyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		mover.notReallyKeyReleased(e);
	}
	
	public void decrementTimer() {
		decrementTimer++;
		if(decrementTimer == 4) {
			decrementTimer = 0;
			
			program.bombOverlay();
		}
	}
	
}
