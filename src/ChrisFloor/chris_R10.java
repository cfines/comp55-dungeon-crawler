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

public class chris_R10 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage E1, E3, E4, momoko, monoe, blue1, blue2, egg, background,userRep, userWeapon;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private User user;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	private Timer t = new Timer(30, this);
	private boolean unlocked = false;
	private int timerCont = 0;
	private KeyPressedManager mover;
	

	public chris_R10(MainApplication app) {
		this.program = app;
		user = program.getUser();


		//Interactions
		Interactions iE1 = new Interactions(interactionType.chrisEntry_NORTH, 575,-3);
		Interactions iE3 = new Interactions(interactionType.entry_bossDoor_EAST,1050,300);
		Interactions iE4 = new Interactions(interactionType.chrisEntry_WEST,27,300);
		Interactions imomoko = new Interactions(interactionType.momoko, 495,35);
		Interactions imonoe = new Interactions(interactionType.monoe, 665,35);
		Interactions iblue1 = new Interactions(interactionType.blueboi, 665,440);
		Interactions iblue2 = new Interactions(interactionType.blueboi, 495,440);
		Interactions iegg = new Interactions(interactionType.Umbrella_Egg, 578,225);
		

		//Enemies

		//gImages
		background = new GImage("background_figures.gif", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		E1 = iE1.getImage();
		E3 = iE3.getImage();
		E4 = iE4.getImage();
		momoko = imomoko.getImage();
		monoe = imonoe.getImage();
		blue1 = iblue1.getImage();
		blue2 = iblue2.getImage();
		egg = iegg.getImage();

		//listOfInter.add();
		listOfInter.add(iE1);
		listOfInter.add(iE3);
		listOfInter.add(iE4);
		listOfInter.add(imomoko);
		listOfInter.add(imonoe);
		listOfInter.add(iblue1);
		listOfInter.add(iblue2);
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
		elements.add(momoko);
		elements.add(monoe);
		elements.add(blue1);
		elements.add(blue2);
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
		program.drawOverlay(10, program.getFloorNum());
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
		mover.notReallyActionPerformed(e);
		nextRoom();
		userRep.setLocation(user.getX(), user.getY());
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
			program.switchToChrisR4();
		}
		else if(userX <= E3.getX() && userY <= E3.getY() && userX2 >= E3.getX() && userY2 >= E3.getY()) {
			user.setX(150);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToChrisR9();
		}
		else if(userX >= E3.getX() && userY >= E3.getY() && userX <= E3.getX() + 85 && userY <= E3.getY() + 85) {
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
	
	public void unlockProtocol() {
		user.setY(200);
		program.remove(E3);
		E3 = new GImage("entry_door_NORTH.png", 575, 28);
		program.add(E3);
		userRep.setLocation(user.getX(), user.getY());
		program.getUser().setHasKey(false);
		program.combatRefreshOverlay();
		unlocked = true;
	}

	public boolean everyXSeconds(double x) {
		return(timerCont %(x) == 0);
	}
}
