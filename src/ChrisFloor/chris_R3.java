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
import enemyInteraction.ElementType;
import enemyInteraction.Enemy;
import enemyInteraction.Interactions;
import enemyInteraction.enemyType;
import enemyInteraction.interactionType;
import removeLater.User;

public class chris_R3 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage E1, E2, E3, E4, candle1, candle2, candle3, candle4, candle5, candle6, candle7, candle8, candle9, background,userRep, userWeapon;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<GImage> enemyImages = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private int degree;
	private User user;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	private Timer t = new Timer(30, this);
	private int timerCont = 0;
	private boolean move = true;
	private KeyPressedManager mover;


	public chris_R3(MainApplication app) {
		this.program = app;
		user = program.getUser();


		//Interactions
		Interactions iE1 = new Interactions(interactionType.chrisEntry_NORTH, 575,-3);
		Interactions iE2 = new Interactions(interactionType.chrisEntry_SOUTH, 575,535);
		Interactions iE3 = new Interactions(interactionType.chrisEntry_EAST,1050,300);
		Interactions iE4 = new Interactions(interactionType.chrisEntry_WEST,27,300);
		Interactions icandle1 = new Interactions(interactionType.candle1, 315,35);
		Interactions icandle2 = new Interactions(interactionType.candle2, 375,105);
		Interactions icandle3 = new Interactions(interactionType.candle2, 435,175);
		Interactions icandle4 = new Interactions(interactionType.candle1, 495,235);
		Interactions icandle5 = new Interactions(interactionType.candle2, 555,285);
		Interactions icandle6 = new Interactions(interactionType.candle1, 635,335);
		Interactions icandle7 = new Interactions(interactionType.candle1, 700,395);
		Interactions icandle8 = new Interactions(interactionType.candle2, 775,435);
		Interactions icandle9 = new Interactions(interactionType.candle1, 840,465);

		//Enemies

		//gImages
		background = new GImage("background_block.gif", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		E1 = iE1.getImage();
		E2 = iE2.getImage();
		E3 = iE3.getImage();
		E4 = iE4.getImage();
		candle1 = icandle1.getImage();
		candle2 = icandle2.getImage();
		candle3 = icandle3.getImage();
		candle4 = icandle4.getImage();
		candle5 = icandle5.getImage();
		candle6 = icandle6.getImage();
		candle7 = icandle7.getImage();
		candle8 = icandle8.getImage();
		candle9 = icandle9.getImage();

		//listOfInter.add();
		listOfInter.add(iE1);
		listOfInter.add(iE2);
		listOfInter.add(iE3);
		listOfInter.add(iE4);
		listOfInter.add(icandle1);
		listOfInter.add(icandle2);
		listOfInter.add(icandle3);
		listOfInter.add(icandle4);
		listOfInter.add(icandle5);
		listOfInter.add(icandle6);
		listOfInter.add(icandle7);
		listOfInter.add(icandle8);
		listOfInter.add(icandle9);

		//listOfEnemies.add)();

		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);

		//elements.add();
		elements.add(background);
		elements.add(E1);
		elements.add(E2);
		elements.add(E3);
		elements.add(E4);
		elements.add(candle1);
		elements.add(candle2);
		elements.add(candle3);
		elements.add(candle4);
		elements.add(candle5);
		elements.add(candle6);
		elements.add(candle7);
		elements.add(candle8);
		elements.add(candle9);
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
		if(listOfEnemies.size() >= 1) {
			for(int i = 0; i < listOfEnemies.size(); i++) {
				if(listOfEnemies.get(i).getEnemyType() == enemyType.rip) {
					enemyImages.remove(i);
					listOfEnemies.remove(i);
				} else {
					program.add(enemyImages.get(i));
				}
			}
		}
		program.drawOverlay(3, program.getFloorNum());
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
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			t.stop();
		}
		mover.notReallyKeyReleased(e);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timerCont++;
		mover.notReallyActionPerformed(e);
		nextRoom();
		userRep.setLocation(user.getX(), user.getY());
		//System.out.println("x: "+ user.getX() + " y: " + user.getY());	
	}

	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userX2 = userRep.getX() + 80;
		double userY2 = userRep.getY() + 80;
		if(userX >= E1.getX() && userY >= E1.getY() && userX <= E1.getX() + 85 && userY <= E1.getY() + 85) {
			user.setX(575);
			user.setY(410);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToChrisR6();
		}
		else if(userX <= E2.getX() && userY <= E2.getY() && userY2 >= E2.getY() - 30  && userX >= E2.getX() - 30) {
			user.setX(575);
			user.setY(110);
			userRep.setLocation(user.getX(),user.getY());
			program.switchToChrisR9();
		}
		else if(userX <= E3.getX() && userY <= E3.getY() && userX2 >= E3.getX() && userY2 >= E3.getY()) {
			user.setX(150);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToChrisR4();
		}
		else if(userX >= E4.getX() && userY >= E4.getY() && userX <= E4.getX() + 75 && userY <= E4.getY() + 75) {
			user.setX(900);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToChrisR2();
		}
	}
	public boolean everyXSeconds(double x) {
		return(timerCont %(x) == 0);
	}
}
