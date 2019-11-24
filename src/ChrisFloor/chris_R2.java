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
import enemyInteraction.enemyType;
import enemyInteraction.interactionType;
import removeLater.User;

public class chris_R2 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage E2, E3, E4, E5, background,userRep, userWeapon;
	private ArrayList<GImage> enemyImages = new ArrayList<GImage>();
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private User user;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	private Timer t = new Timer(30, this);
	private int timerCont = 0;
	private KeyPressedManager mover;
	
	public chris_R2(MainApplication app) {
		this.program = app;
		user = program.getUser(); 
		
		//Interactions
		Interactions iE2 = new Interactions(interactionType.chrisEntry_NORTH, 575,-3);
		Interactions iE3 = new Interactions(interactionType.chrisEntry_SOUTH, 575,535);
		Interactions iE4 = new Interactions(interactionType.chrisEntry_EAST,1050,300);
		Interactions iE5 = new Interactions(interactionType.chrisEntry_WEST,27,300);
		
		//Enemies
		
		//gImages
		background = new GImage("background_block.gif", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		E2 = iE2.getImage();
		E3 = iE3.getImage();
		E4 = iE4.getImage();
		E5 = iE5.getImage();
		
		listOfInter.add(iE2);
		listOfInter.add(iE3);
		listOfInter.add(iE4);
		listOfInter.add(iE5);
		
		//listOfEnemies.add)();
		
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		elements.add(background);
		elements.add(E2);
		elements.add(E3);
		elements.add(E4);
		elements.add(E5);
		elements.add(userRep);
		
		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, elements, 
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timerCont++;
		mover.notReallyActionPerformed(e);
		nextRoom();
		userRep.setLocation(user.getX(), user.getY());
		System.out.println("x: "+ user.getX() + " y: " + user.getY());		
	}

	@Override
	public void showContents() {
		t.start();
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		program.drawOverlay(2, program.getFloorNum());		
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
//			program.switchToChrisR1();
//		}

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
		double userX2 = userRep.getX() + 80;
		double userY2 = userRep.getY() + 80;
		if(userX >= E2.getX() && userY >= E2.getY() && userX <= E2.getX() + 85 && userY <= E2.getY() + 85) {
			user.setX(575);
			user.setY(410);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToChrisR1();
		}
		else if(userX <= E3.getX() && userY <= E3.getY() && userY2 >= E3.getY() - 30  && userX >= E3.getX() - 30) {
			user.setX(575);
			user.setY(110);
			userRep.setLocation(user.getX(),user.getY());
			program.switchToChrisR1();
		}
		else if(userX <= E4.getX() && userY <= E4.getY() && userX2 >= E4.getX() && userY2 >= E4.getY()) {
			user.setX(150);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToChrisR1();
		}
		else if(userX >= E5.getX() && userY >= E5.getY() && userX <= E5.getX() + 75 && userY <= E5.getY() + 75) {
			user.setX(900);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToChrisR1();
		}
	}
	
	public boolean everyXSeconds(double x) {
		return(timerCont %(x) == 0);
	}
}
