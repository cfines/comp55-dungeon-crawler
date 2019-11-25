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
import enemyInteraction.ElementType;
import enemyInteraction.Enemy;
import enemyInteraction.Interactions;
import enemyInteraction.enemyType;
import enemyInteraction.interactionType;
import removeLater.User;

public class mapBase_R9Complete extends GraphicsPane implements ActionListener {
	private MainApplication program;
	private GImage overlay,E16, ENext,background,userRep, userWeapon; 
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private GRect voidSpace;
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private User user;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	private Timer t = new Timer(30, this);

	private KeyPressedManager mover;

	public mapBase_R9Complete(MainApplication app) {
		this.program = app;
		user = program.getUser();
		Interactions iE16 = new Interactions(interactionType.entry_door_SOUTH,575,535);
		Interactions iENext = new Interactions(interactionType.entry_stair,575,300);

		E16 = iE16.getImage();
		ENext = iENext.getImage();

		background = new GImage("Fire_Floor (Regular Floor).png", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		overlay = new GImage("dark_overlay.png",15,30);

		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);

		listOfInter.add(iENext);
		listOfInter.add(iE16);

		elements.add(background);
		elements.add(E16);
		elements.add(ENext);
		elements.add(userRep);
		elements.add(overlay);

		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, elements,
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		mover.notReallyActionPerformed(e);
	}

	@Override
	public void showContents() {
		
		if(program.getBossRun()) {
			program.switchToOsvaldoBoss();
			return;
		}
		
		t.start();
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		program.drawOverlay(9, program.getFloorNum());
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
	public void keyPressed(KeyEvent e) {
		if((e.getKeyCode() == KeyEvent.VK_ESCAPE) || (e.getKeyCode() == KeyEvent.VK_Q)) {
			t.stop();
		}
		mover.notReallyKeyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		mover.notReallyKeyReleased(e);
		nextRoom();
		userRep.setLocation(user.getX(), user.getY());
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == E16) {
			program.switchToR8();
			userRep.setLocation(575,435);
		}
	}
	
	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userX2 = userRep.getX() + 80;
		double userY2 = userRep.getY() + 80;
		//TODO fix these boundary checks
		if(userX >= ENext.getX() && userY >= ENext.getY() && userX <= ENext.getX() + 75 && userY <= ENext.getY() + 75) {
			
			program.setComingFromBoss(true);
			user.setX(575);
			user.setY(325);
			userRep.setLocation(user.getX(), user.getY());
			program.setBossDefeated(false);
			program.setFloorNum(program.getFloorNum() + 1);
			program.switchToBombRoomR1();
		} else if (userX <= E16.getX() && userY <= E16.getY() && userY2 >= E16.getY() - 30  && userX >= E16.getX() - 30) {
			user.setX(575);
			user.setY(130);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToR8();
		}
	}

}
