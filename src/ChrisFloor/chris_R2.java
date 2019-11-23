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
import removeLater.User;

public class chris_R2 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage E2, E3, E4, background, userRep, userWeapon;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private GRect voidSpace;
	private User user;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	Timer t = new Timer(30, this);
	boolean move = false;
	private int degree, timerCont = 0;
	
	private KeyPressedManager mover;
	
	public chris_R2(MainApplication app) {
		this.program = app;
		user = program.getUser(); 
		
		//Interactions
		
		//Enemies
		
		//gImages
		background = new GImage("background_block.gif", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		
		//listOfInter.add();
		
		//listOfEnemies.add)();
		
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		//elements.add();
		
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
	
	public boolean everyXSeconds(double x) {
		return(timerCont %(x) == 0);
	}
	
	public void enemyMovement() {
		if(everyXSeconds(20)) {
			move = !move;
		}
//		for (Enemy enem : listOfEnemies) {
//			enem.getImage().movePolar(5, degree);
//			degree+=20;
//			degree%=360;
//			if(enem.getEnemyType() == xxxxxxxx) {
//				if(move) {
//					double distX = enem.getImage().getX() - userRep.getX();
//					double distY = enem.getImage().getY() - userRep.getY();
//					double moveX = (distX * 2) / 100;
//					double moveY = (distY * 2) / 100;
//					enem.getImage().move(-moveX, -moveY);
//				}else {enem.getImage().move(0, 0);}
//			}
//			else if(enem.getEnemyType() == xxxxxxxx) {
//				double distX = enem.getImage().getX() - userRep.getX();
//				double distY = enem.getImage().getY() - userRep.getY();
//				double moveX = (distX * 2) / 100;
//				double moveY = (distY * 2) / 100;
//				enem.getImage().move(-moveX, -moveY);
//			}
//			enem.setStartX(enem.getImage().getX());
//			enem.setStartY(enem.getImage().getY());
//		}
	}
}
