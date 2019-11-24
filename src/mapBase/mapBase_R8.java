package mapBase;

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
import enemyInteraction.Enemy;
import enemyInteraction.Interactions;
import enemyInteraction.interactionType;
import removeLater.User;

public class mapBase_R8 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage rock1, rock2, hole1, E14, E15, background,userRep, userWeapon;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private User user;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	private boolean unlocked = false;
	private Timer t = new Timer(30, this);

	private KeyPressedManager mover; 

	public mapBase_R8(MainApplication app) {
		this.program = app;
		user = program.getUser();
		Interactions irock1 = new Interactions(interactionType.obstacle_concrete_rubble,150,425);
		Interactions irock2 = new Interactions(interactionType.obstacle_concrete_rubble,575,325);
		Interactions ihole1 = new Interactions(interactionType.obstacle_hole,901,325);
		Interactions iE14 = new Interactions(interactionType.entry_door_WEST,27,300);
		Interactions iE15 = new Interactions(interactionType.entry_bossDoor,575,30);
		rock1 = irock1.getImage();
		rock2 = irock2.getImage();
		hole1 = ihole1.getImage();
		E14 = iE14.getImage();
		E15 = iE15.getImage();
		background = new GImage("Earth_Floor (Regular Floor).png", 15,30);

		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);

		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);

		listOfInter.add(iE15);
		listOfInter.add(iE14);
		listOfInter.add(ihole1);
		listOfInter.add(irock2);
		listOfInter.add(irock1);

		elements.add(background);
		elements.add(rock1);
		elements.add(rock2);
		elements.add(hole1);
		elements.add(E14);
		elements.add(E15);
		elements.add(userRep);

		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, elements,
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}
	
	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userX2 = userRep.getX() + 80;
		double userY2 = userRep.getY() + 80;
		if(userX >= E14.getX() && userY >= E14.getY() && userX <= E14.getX() + 75 && userY <= E14.getY() + 75) {
			user.setX(900);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToR7();
		}
		//Boss door
		else if(userX >= E15.getX() && userY >= E15.getY() && userX <= E15.getX() + 85 && userY <= E15.getY() + 85) {
			if(!unlocked) {
				if(program.getUser().getHasKey()) {
					unlockProtocol();
				}
			} else if (program.getBossDefeated()) {
				user.setX(575);
				user.setY(410);
				userRep.setLocation(user.getX(), user.getY());
				program.switchToR9Complete();
			} else {
				user.setX(575);
				user.setY(410);
				userRep.setLocation(user.getX(), user.getY());
				program.switchToR9();
			}
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

	@Override
	public void showContents() {
		t.start();
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		program.drawOverlay(8, program.getFloorNum());
		if(unlocked) {
			program.remove(E15);
			E15 = new GImage("entry_door_NORTH.png", 575, 28);
			program.add(E15);
		}
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
		if (obj == E14) {
			program.switchToR7();
			userRep.setLocation(1010,300);
		}
		else if(obj == E15) {
			user.setX(575);
			user.setY(410);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToR9();
		}
		else if(obj == rock1) {
			user.setX(575);
			user.setY(410);
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
	
	public void unlockProtocol() {
		user.setY(200);
		program.remove(E15);
		E15 = new GImage("entry_door_NORTH.png", 575, 28);
		program.add(E15);
		userRep.setLocation(user.getX(), user.getY());
		program.getUser().setHasKey(false);
		program.combatRefreshOverlay();
		unlocked = true;
	}
}
