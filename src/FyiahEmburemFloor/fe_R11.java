package FyiahEmburemFloor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import RoomPanes.GraphicsPane;
import RoomPanes.KeyPressedManager;
import RoomPanes.MainApplication;
import acm.graphics.GImage;
import acm.graphics.GRect;
import enemyInteraction.ElementType;
import enemyInteraction.Enemy;
import enemyInteraction.Interactions;
import enemyInteraction.enemyType;
import enemyInteraction.interactionType;
import removeLater.User;

public class fe_R11 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage enemy1, E22, E23, background,userRep, userWeapon;
	private int degree;
	private Enemy Rinea = new Enemy(40,40,2,5,650,100, ElementType.FIRE, enemyType.Rinea);
	private int timerCont = 0;
	private User user;
	private Timer t = new Timer(30, this);
	private boolean move = true;
	private GImage burned = new GImage("Rinea.gif", 650,100);
	private boolean atkUp,atkLeft,atkDown,atkRight;
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private ArrayList<GImage> enemyImages = new ArrayList<GImage>();
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<Enemy> listOfProjectiles = new ArrayList<Enemy>();
	private boolean attack = false, hit = false;
	private Enemy magic = new Enemy(100,100,99999,1,0,0,ElementType.FIRE, enemyType.Nagic);
	
	private KeyPressedManager mover;

	
	public fe_R11(MainApplication app) 
	{
		this.program = app;
		user = program.getUser();
		Enemy ienemy1 = new Enemy(50,50,2,3,575,100, ElementType.FIRE, enemyType.Berkut);
		Interactions iE22 = new Interactions(interactionType.entry_door_SOUTH,575,535);
		Interactions iE23 = new Interactions(interactionType.entry_door_NORTH,575,30);
		E22 = iE22.getImage();
		E23 = iE23.getImage();
		background = new GImage("FE Room.png",15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		enemy1 = ienemy1.getImage();
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		burned = Rinea.getImage();
		
		listOfEnemies.add(Rinea);
		listOfEnemies.add(ienemy1);
		listOfInter.add(iE22);
		listOfInter.add(iE23);
		elements.add(background);
		elements.add(E22);
		elements.add(E23);
		enemyImages.add(enemy1);
		enemyImages.add(burned);
		elements.add(userRep);
		
		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, elements,
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		timerCont++;
		enemyMovement();
		enemyCombat();
		if(mover.getDeleteEnemy()) { deleteEnemy(); }
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
		program.drawOverlay(11, program.getFloorNum());		
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		
	}
	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userY2 = userRep.getY() + 80;
		if(userX <= E22.getX() && userY <= E22.getY() && userY2 >= E22.getY() - 30  && userX >= E22.getX() - 30) {
			user.setX(575);
			user.setY(110);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToFeR4();
		}
		if(userX >= E23.getX() && userY >= E23.getY() && userX <= E23.getX() + 95 && userY <= E23.getY() + 90) {
			user.setX(575);
			user.setY(410);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToFeR12();
		}
	}
}
