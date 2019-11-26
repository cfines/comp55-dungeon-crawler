package FyiahEmburemFloor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import RoomPanes.GraphicsPane;
import RoomPanes.KeyPressedManager;
import RoomPanes.MainApplication;
import acm.graphics.GImage;
import acm.graphics.GRect;
import enemyInteraction.Enemy;
import enemyInteraction.Interactions;
import enemyInteraction.interactionType;
import miscMechanics.User;

public class fe_R12Complete  extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage E24, E25, background,userRep, userWeapon;
	private User user;
	private boolean move = true;
	private GRect voidSpace;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	private Timer t = new Timer(30, this);
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private KeyPressedManager mover;
	
	public fe_R12Complete(MainApplication app) 
	{
		this.program = app;
		user = program.getUser();
		Interactions iE24 = new Interactions(interactionType.entry_door_SOUTH,775,535);
		Interactions iE25 = new Interactions(interactionType.entry_stair,200,235);
		E24 = iE24.getImage();
		E25 = iE25.getImage();
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		background = new GImage("Finished Screen - FE.png", 15,30);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		listOfInter.add(iE24);
		listOfInter.add(iE25);

		elements.add(background);
		elements.add(E24);
		elements.add(E25);
		elements.add(userRep);
		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, elements,
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		nextRoom();
		userRep.setLocation(user.getX(), user.getY());
		mover.notReallyActionPerformed(e);
	}
	

	@Override
	public void showContents() {
		if(program.getBossRun()) {
			program.switchToMenu();
			return;
		}
		
		t.start();
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		program.drawOverlay(12, program.getFloorNum());
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
	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userX2 = userRep.getX() + 80;
		double userY2 = userRep.getY() + 80;
		if(userX >= E25.getX() && userY >= E25.getY() && userX <= E25.getX() + 75 && userY <= E25.getY() + 75) {
			program.setComingFromBoss(true);
			user.setX(575);
			user.setY(325);
			userRep.setLocation(user.getX(), user.getY());
			program.setBossDefeated(false);
			program.setFloorNum(program.getFloorNum() + 1);
			program.switchToGameWin();
		}
		if(userX <= E24.getX() && userY <= E24.getY() && userY2 >= E24.getY() - 30  && userX >= E24.getX() - 30) {
			user.setX(575);
			user.setY(110);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToFeR11();
		}
	}
	
}
