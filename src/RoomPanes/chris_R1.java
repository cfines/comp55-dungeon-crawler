package RoomPanes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import starter.Enemy;
import starter.GraphicsPane;
import starter.Interactions;
import starter.KeyPressedManager;
import starter.MainApplication;
import starter.User;
import starter.interactionType;

public class chris_R1 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage E1, background, userRep, userWeapon;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private GRect voidSpace;
	private User user;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	Timer t = new Timer(30, this);
	
	private KeyPressedManager mover;

	public chris_R1(MainApplication app) {
		this.program = app;
		user = program.getUser(); 
		
		Interactions iE1 = new Interactions(interactionType.entry_door_EAST, 1050,300);
		
		E1 = iE1.getImage();

		background = new GImage("background_block.gif", 15,30);
		
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		elements.add(background);
		elements.add(E1);
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
//		if (obj == ENext) {
//			program.switchToMenu();
//		}
//		else if(obj == E16) {
//			program.switchToR8();
//		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mover.notReallyActionPerformed(e);
		nextRoom();
		userRep.setLocation(user.getX(), user.getY());
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			t.stop();
		}
		mover.notReallyKeyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		mover.notReallyKeyReleased(e);
	}
	
	private void nextRoom() {
//		double userX = userRep.getX();
//		double userY = userRep.getY();
//		double userX2 = userX + 80;
//		double userY2 = userX + 80;
//		if(userX <= E1.getX() && userY <= E1.getY() && userX2 >= E1.getX() && userY2 >= E1.getY()) {
//			user.setX(150);
//			user.setY(300);
//			userRep.setLocation(user.getX(), user.getY());
//			program.xxxxxx();
//		}
	}
}
