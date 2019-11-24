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
import removeLater.User;

public class fe_R1 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage E1,tree1, tree2, tree3, tree4, tree5, tree6, tree7, background,userRep, userWeapon;
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

	public fe_R1(MainApplication app) {
		this.program = app;
		user = program.getUser();
		
		Interactions iE1 = new Interactions(interactionType.entry_door_EAST,1050,300);
		Interactions itree1 = new Interactions(interactionType.tree,150,60);
		Interactions itree2 = new Interactions(interactionType.tree,675,60);
		Interactions itree3 = new Interactions(interactionType.tree,750,60);
		Interactions itree4 = new Interactions(interactionType.tree,150,285);
		Interactions itree5 = new Interactions(interactionType.tree,225,285);
		Interactions itree6 = new Interactions(interactionType.tree,675,285);
		Interactions itree7 = new Interactions(interactionType.treeFell,750,360);
		
		tree1 = itree1.getImage();
		tree2 = itree2.getImage();
		tree3 = itree3.getImage();
		tree4 = itree4.getImage();
		tree5 = itree5.getImage();
		tree6 = itree6.getImage();
		tree7 = itree7.getImage();
		
		E1 = iE1.getImage();
		background = new GImage("FE Forest Clearing.png", 10,20);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);

		listOfInter.add(iE1);
		listOfInter.add(itree1);
		listOfInter.add(itree2);
		listOfInter.add(itree3);
		listOfInter.add(itree4);
		listOfInter.add(itree5);
		listOfInter.add(itree6);
		listOfInter.add(itree7);
		
		elements.add(background);
		elements.add(E1);
		elements.add(userRep);
		elements.add(tree1);
		elements.add(tree2);
		elements.add(tree3);
		elements.add(tree4);
		elements.add(tree5);
		elements.add(tree6);
		elements.add(tree7);
		
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
	public boolean everyXSeconds(double x) {
		return(timerCont %(x) == 0);
	}

	
	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userX2 = userRep.getX() + 80;
		double userY2 = userRep.getY() + 80;
		if(userX <= E1.getX() && userY <= E1.getY() && userX2 >= E1.getX() && userY2 >= E1.getY()) {
			user.setX(150);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToFeR2();
		}
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
