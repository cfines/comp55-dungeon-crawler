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
import miscMechanics.User;

public class chris_R6 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage E3, E4,egg, color1,color2,color3,color4, background,userRep, userWeapon;
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


	public chris_R6(MainApplication app) {
		this.program = app;
		user = program.getUser();


		//Interactions
		Interactions iE3 = new Interactions(interactionType.chrisEntry_EAST,1050,300);
		Interactions iE4 = new Interactions(interactionType.chrisEntry_WEST,27,300);
		Interactions iegg = new Interactions(interactionType.Buyo_buyo_Egg, 578,225);
		Interactions icolor1 = new Interactions(interactionType.color1, 577, 173);
		Interactions icolor2 = new Interactions(interactionType.color1, 577, 277);
		Interactions icolor3 = new Interactions(interactionType.color1, 503, 225);
		Interactions icolor4 = new Interactions(interactionType.color1, 653, 225);


		//Enemies

		//gImages
		background = new GImage("background_sky.gif", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		E3 = iE3.getImage();
		E4 = iE4.getImage();
		egg = iegg.getImage();
		color1 = icolor1.getImage();
		color2 = icolor2.getImage();
		color3 = icolor3.getImage();
		color4 = icolor4.getImage();


		//listOfInter.add();
		listOfInter.add(iE3);
		listOfInter.add(iE4);
		listOfInter.add(iegg);
		listOfInter.add(icolor1);
		listOfInter.add(icolor2);
		listOfInter.add(icolor3);
		listOfInter.add(icolor4);

		//listOfEnemies.add)();

		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);

		//elements.add();
		elements.add(background);
		elements.add(E3);
		elements.add(E4);
		elements.add(egg);
		elements.add(color1);
		elements.add(color2);
		elements.add(color3);
		elements.add(color4);
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

		program.drawOverlay(6, program.getFloorNum());
	}


	@Override
	public void hideContents() {
		t.stop();
		program.remove(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.remove(elements.get(i));
		}
		for (int i = 0; i <= enemyImages.size() - 1; i++) {
			program.remove(enemyImages.get(i));
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
		System.out.println("x: "+ user.getX() + " y: " + user.getY());	
	}

	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userX2 = userRep.getX() + 80;
		double userY2 = userRep.getY() + 80;
		if(userX <= E3.getX() && userY <= E3.getY() && userX2 >= E3.getX() && userY2 >= E3.getY()) {
			user.setX(150);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToChrisR5();
		}
		else if(userX >= E4.getX() && userY >= E4.getY() && userX <= E4.getX() + 75 && userY <= E4.getY() + 75) {
			user.setX(900);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToChrisR7();
		}
	}

	public boolean everyXSeconds(double x) {
		return(timerCont %(x) == 0);
	}
}