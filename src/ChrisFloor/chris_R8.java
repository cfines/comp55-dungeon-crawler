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

public class chris_R8 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage E1, E4, E3, egg, candle1, candle2, candle3, candle4, blueboi1, blueboi2, blueboi3, background,userRep, userWeapon;
	private ArrayList<GImage> enemyImages = new ArrayList<GImage>();
	private ArrayList<GImage> elements = new ArrayList<GImage>();
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
	

	public chris_R8(MainApplication app) {
		this.program = app;
		user = program.getUser();


		//Interactions
		Interactions iE1 = new Interactions(interactionType.chrisEntry_NORTH, 575,-3);
		Interactions iE3 = new Interactions(interactionType.chrisEntry_SOUTH,575,535);
		Interactions iE4 = new Interactions(interactionType.chrisEntry_WEST,27,300);
		Interactions icandle1 = new Interactions(interactionType.candle2,65,440);
		Interactions icandle2 = new Interactions(interactionType.candle1,65,35);
		Interactions icandle3 = new Interactions(interactionType.candle2,1005,35);
		Interactions icandle4 = new Interactions(interactionType.candle1,1005,440);
		Interactions iblue1 = new Interactions(interactionType.blueboi, 575,440);
		Interactions iblue2 = new Interactions(interactionType.blueboi, 535,480);
		Interactions iblue3 = new Interactions(interactionType.blueboi, 615,480);
		Interactions iegg = new Interactions(interactionType.Long_Hair_Egg, 578,225);
		
		//Enemies

		//gImages
		background = new GImage("background_figures.gif", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		E1 = iE1.getImage();
		E3 = iE3.getImage();
		E4 = iE4.getImage();
		blueboi1 = iblue1.getImage();
		blueboi2 = iblue2.getImage();
		blueboi3 = iblue3.getImage();
		candle1 = icandle1.getImage();
		candle2 = icandle2.getImage();
		candle3 = icandle3.getImage();
		candle4 = icandle4.getImage();
		egg = iegg.getImage();

		//listOfInter.add();
		listOfInter.add(iE1);
		listOfInter.add(iE3);
		listOfInter.add(iE4);
		listOfInter.add(iblue1);
		listOfInter.add(iblue2);
		listOfInter.add(iblue3);
		listOfInter.add(icandle1);
		listOfInter.add(icandle2);
		listOfInter.add(icandle3);
		listOfInter.add(icandle4);
		listOfInter.add(iegg);

		//listOfEnemies.add)();

		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);

		//elements.add();
		elements.add(background);
		elements.add(E1);
		elements.add(E3);
		elements.add(E4);
		elements.add(blueboi1);
		elements.add(blueboi2);
		elements.add(blueboi3);
		elements.add(candle1);
		elements.add(candle2);
		elements.add(candle3);
		elements.add(candle4);
		elements.add(egg);
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
			for(int i = 0; i < enemyImages.size(); i++) {
				if(listOfEnemies.get(i).getEnemyType() == enemyType.rip) {
					enemyImages.remove(i);
					listOfEnemies.remove(i);
				} else {
					program.add(enemyImages.get(i));
				}
			}
		}
		program.drawOverlay(8, program.getFloorNum());
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
		enemyMovement();
		mover.notReallyActionPerformed(e);
		nextRoom();
		userRep.setLocation(user.getX(), user.getY());
		System.out.println("x: "+ user.getX() + " y: " + user.getY());	
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
			program.switchToChrisR2();
		}
		else if(userX <= E3.getX() && userY <= E3.getY() && userY2 >= E3.getY() - 30  && userX >= E3.getX() - 30) {
			user.setX(575);
			user.setY(110);
			userRep.setLocation(user.getX(),user.getY());
			//program.switchToChris next section();
		}
	}

	public boolean everyXSeconds(double x) {
		return(timerCont %(x) == 0);
	}

	public void enemyMovement() {
//		if(everyXSeconds(20)) {
//			move = !move;
//		}
//		for (Enemy enem : listOfEnemies) {
//
//			enem.getImage().movePolar(5, degree);
//			degree+=5;
//			degree%=360;
//			if(move) {
//				if(enem.getEnemyType() == enemyType.FIREDeath) {
//					double distX = enem.getImage().getX() - userRep.getX();
//					double distY = enem.getImage().getY() - userRep.getY();
//					double moveX = (distX * 1) / 100;
//					double moveY = (distY * 1) / 100;
//					enem.getImage().move(-moveX, -moveY);
//				}
//			}else {enem.getImage().move(0, 0);}
//			enem.setStartX(enem.getImage().getX());
//			enem.setStartY(enem.getImage().getY());
//		}
	}
}
