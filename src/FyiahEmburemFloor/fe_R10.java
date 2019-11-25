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
import enemyInteraction.enemyType;
import enemyInteraction.interactionType;
import miscMechanics.User;

public class fe_R10 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage E21, statue1,background,userRep,key1, userWeapon;
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
	
	public fe_R10(MainApplication app) 
	{
		this.program = app;
		user = program.getUser();
		Interactions iE21= new Interactions(interactionType.entry_door_EAST,1050,300);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		background = new GImage("FE Ice Cave.png", 10,20);
		E21 = iE21.getImage();
		Interactions ikey1 = new Interactions(interactionType.item_gif_key,575,325);
		key1 = ikey1.getImage();
		Interactions istatue1 = new Interactions(interactionType.statue, 575,60);
		statue1 = istatue1.getImage();
		listOfInter.add(istatue1);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		listOfInter.add(iE21);
		listOfInter.add(ikey1);
		elements.add(background);
		elements.add(statue1);
		elements.add(E21);
		elements.add(key1);
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
	}

	@Override
	public void showContents() {
		t.start();
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		
		if(program.getUser().getHasKey()) {
			program.remove(key1);
		}
		program.drawOverlay(10, program.getFloorNum());
	}

	public boolean everyXSeconds(double x) {
		return(timerCont %(x) == 0);
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
	
	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userX2 = userRep.getX() + 80;
		double userY2 = userRep.getY() + 80;
		if(userX <= E21.getX() && userY <= E21.getY() && userX2 >= E21.getX() && userY2 >= E21.getY()) {
			user.setX(150);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToFeR7();
		}
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
	}
}
