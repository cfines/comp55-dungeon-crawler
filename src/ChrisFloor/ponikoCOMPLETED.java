package ChrisFloor;

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
import miscMechanics.User;

public class ponikoCOMPLETED extends GraphicsPane implements ActionListener {
	private MainApplication program;
	private GImage overlay,E1,E2,background,userRep, userWeapon; 
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private GRect voidSpace;
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private User user;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	private Timer t = new Timer(30, this);

	private KeyPressedManager mover;

	public ponikoCOMPLETED(MainApplication app) {
		this.program = app;
		user = program.getUser();
		Interactions iE1 = new Interactions(interactionType.chrisEntry_NORTH,748,248);
		Interactions iE2 = new Interactions(interactionType.entry_stair,235,365);
		
		E1 = iE1.getImage();
		E2 = iE2.getImage();

		background = new GImage("ponikoCOMPLETED.jpg", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		overlay = new GImage("dark_overlay.png",15,30);

		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);

		listOfInter.add(iE1);
		listOfInter.add(iE2);

		elements.add(background);
		elements.add(E1);
		elements.add(E2);
		elements.add(userRep);
		elements.add(overlay);

		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, elements,
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		mover.notReallyActionPerformed(e);
		//System.out.println("x: "+ user.getX() + " y: " + user.getY());	
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
		program.drawOverlay(11, program.getFloorNum());
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
		if(obj == E2) {
			program.switchToChrisR10();
			userRep.setLocation(900,300);
		}
	}
	
	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userX2 = userRep.getX() + 80;
		double userY2 = userRep.getY() + 80;
		//TODO fix these boundary checks
		if(userX >= E2.getX() && userY >= E2.getY() && userX <= E2.getX() + 75 && userY <= E2.getY() + 75) {
			program.setComingFromBoss(true);
			user.setX(575);
			user.setY(325);
			userRep.setLocation(user.getX(), user.getY());
			program.setBossDefeated(false);
			program.setFloorNum(program.getFloorNum() + 1);
			program.switchToFeR1();
		}
		else if(userX >= E1.getX() && userY >= E1.getY() && userX <= E1.getX() + 85 && userY <= E1.getY() + 85) {
			user.setX(900);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToChrisR10();
		}
	}
}
