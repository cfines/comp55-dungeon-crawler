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

public class chris_R1 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage E1, candle1, candle2, candle3, candle4, candle5, candle6, candle7, face, background, userRep, userWeapon;
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
		
		Interactions iE1 = new Interactions(interactionType.chrisEntry_EAST, 1050,300);
		Interactions iface = new Interactions(interactionType.poniko, 1005,175);
		Interactions icandle1 = new Interactions(interactionType.candle1, 965,35);
		Interactions icandle2 = new Interactions(interactionType.candle2, 1055,110);
		Interactions icandle3 = new Interactions(interactionType.candle1, 250,440);
		Interactions icandle4 = new Interactions(interactionType.candle1, 520,130);
		Interactions icandle5 = new Interactions(interactionType.candle2, 860,500);
		Interactions icandle6 = new Interactions(interactionType.candle3, 165,85);
		Interactions icandle7 = new Interactions(interactionType.candle2, 115,105);
		
		
		
		E1 = iE1.getImage();
		face = iface.getImage();
		candle1 = icandle1.getImage();
		candle2 = icandle2.getImage();
		candle3 = icandle3.getImage();
		candle4 = icandle4.getImage();
		candle5 = icandle5.getImage();
		candle6 = icandle6.getImage();
		candle7 = icandle7.getImage();

		background = new GImage("background_block.gif", 15,30);
		
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		
		listOfInter.add(iface);
		listOfInter.add(icandle1);
		listOfInter.add(icandle2);
		listOfInter.add(icandle3);
		listOfInter.add(icandle4);
		listOfInter.add(icandle5);
		listOfInter.add(icandle6);
		listOfInter.add(icandle7);
		listOfInter.add(iE1);
		
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		elements.add(background);
		elements.add(E1);
		elements.add(candle1);
		elements.add(candle2);
		elements.add(candle3);
		elements.add(candle4);
		elements.add(candle5);
		elements.add(candle6);
		elements.add(candle7);
		elements.add(face);
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
//		if (obj == E1) {
//			program.switchToMenu();
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
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userX2 = userX + 80;
		double userY2 = userX + 80;
		if(userX <= E1.getX() && userY <= E1.getY() && userX2 >= E1.getX() && userY2 >= E1.getY()) {
			user.setX(150);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToChrisR2();
		}
	}
}
