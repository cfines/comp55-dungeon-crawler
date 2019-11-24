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

public class poniko extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage E5, bossFace,background,userRep, userWeapon;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	Enemy ibossFace = new Enemy (50,50,2,2,805,375,ElementType.WATER, enemyType.face);
	private User user;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	private Timer t = new Timer(30, this);
	private int timerCont = 0;
	private boolean move = false, attack = false, swapBack = false;
	private int degree;
	private KeyPressedManager mover;
	
	public poniko(MainApplication app) {
		this.program = app;
		user = program.getUser(); 
		
		//Interactions
		Interactions iE5 = new Interactions(interactionType.chrisEntry_WEST,27,300);
		//Enemies
		
		
		//gImages
		background = new GImage("ponikos_room.png", 15,-30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);
		E5 = iE5.getImage();
		listOfInter.add(iE5);
		bossFace = ibossFace.getImage();
		
		
		//listOfEnemies.add)();
		listOfEnemies.add(ibossFace);
		
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		elements.add(background);
		elements.add(E5);
		elements.add(bossFace);
		elements.add(userRep);
		
		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, elements, 
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(ibossFace.getEnemyStats().getHP_cur() <= 0) {
			if(program.getBossRun()) {
				user.setX(575);
				user.setY(425);
				program.switchToOsvaldoBoss();
				return;
			}
			program.setBossDefeated(true);
			program.switchToChrisR9();
		}
		
		timerCont++;
		enemyMovement();
		mover.notReallyActionPerformed(e);
		nextRoom();
		userRep.setLocation(user.getX(), user.getY());
		//System.out.println("x: "+ user.getX() + " y: " + user.getY());		
	}

	@Override
	public void showContents() {
		t.start();
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		program.drawOverlay(11, program.getFloorNum());		
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
		if((e.getKeyCode() == KeyEvent.VK_ESCAPE) || (e.getKeyCode() == KeyEvent.VK_Q)) {
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

		if(userX >= E5.getX() && userY >= E5.getY() && userX <= E5.getX() + 75 && userY <= E5.getY() + 75) {
			user.setX(900);
			user.setY(300);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToChrisR10();
		}
	}
	
	public boolean everyXSeconds(double x) {
		return(timerCont %(x) == 0);
	}
	
	public void backgroundSwap(boolean move) {
		if(!move) {background.setImage("ponikos_room_attk.png");}
		else {background.setImage("ponikos_room.png");}
		hideContents();
		showContents();
	}

	public void enemyMovement() {
		if(everyXSeconds(50)) {
			move = !move;
			attack =!attack;
		}
		
		for(Enemy enem : listOfEnemies) {
			
			enem.getImage().movePolar(1, degree);
			degree+=80;
			degree%=360;
			if(move) {
				System.out.println("attack at: " + timerCont * 30 + "ms");
				backgroundSwap(move);
				move =!move;
				swapBack =! swapBack;
				double userx = userRep.getX();
				double usery = userRep.getY();
				double bossx = enem.getImage().getX();
				double bossy = enem.getImage().getY();
				
				enem.getImage().setLocation(userx, usery);
				enem.setStartY(enem.getImage().getY());
				enem.setStartX(enem.getImage().getX());
				userRep.setLocation(bossx, bossy);
				user.setX(userRep.getX());
				user.setY(userRep.getY());
				if (swapBack) {backgroundSwap(move);}
			}else {
				double distX = enem.getImage().getX() - userRep.getX();
				double distY = enem.getImage().getY() - userRep.getY();
				double moveX = (distX * 1) / 100;
				double moveY = (distY * 1) / 100;
				enem.getImage().move(-moveX, -moveY);
				enem.setStartY(enem.getImage().getY());
				enem.setStartX(enem.getImage().getX());
				}
			if(attack) {
				double distX = enem.getImage().getX() - userRep.getX();
				double distY = enem.getImage().getY() - userRep.getY();
				double moveX = (distX * 8) / 100;
				double moveY = (distY * 8) / 100;
				enem.getImage().move(-moveX, -moveY);
			}
		}
	}
}
