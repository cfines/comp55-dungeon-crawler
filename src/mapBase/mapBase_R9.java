package mapBase;

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

public class mapBase_R9 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage background,userRep, userWeapon; 
	private GImage xok = new GImage("xokStill.png", 375, 375); 
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private int degree;
	private User user;
	private Enemy ixokStill = new Enemy(20,20,2,2,375,375, ElementType.EARTH, enemyType.xokStill);
	private enemyType attk = enemyType.xokAttack;
	private enemyType still = enemyType.xokStill;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	private Timer t = new Timer(30, this);
	private int timerCont = 0;
	private boolean move = false;

	private KeyPressedManager mover;

	public mapBase_R9(MainApplication app) {
		this.program = app;
		user = program.getUser();

		xok = ixokStill.getImage();

		background = new GImage("Fire_Floor (Regular Floor).png", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);

		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);

		listOfEnemies.add(ixokStill);

		elements.add(background);
		elements.add(xok);
		elements.add(userRep);

		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, elements,
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}

	@Override
	public void showContents() {
		
		if(ixokStill.getEnemyStats().getHP_cur() <= 0) {
			program.setBossDefeated(true);
			program.switchToR9Complete();
		}
		
		t.start();
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		program.drawOverlay(9, program.getFloorNum());
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
	public void actionPerformed(ActionEvent e) {
		
		if(ixokStill.getEnemyStats().getHP_cur() <= 0) {
			if(program.getBossRun()) {
				user.setX(575);
				user.setY(425);
				program.switchToOsvaldoBoss();
				return;
			}
			program.setBossDefeated(true);
			program.switchToR9Complete();
		}
		
		timerCont++;
		enemyMovement();
		mover.notReallyActionPerformed(e);
		userRep.setLocation(user.getX(), user.getY());
	}

	public boolean everyXSeconds(double x) {
		program.bossOverlay(ixokStill);
		return(timerCont %(x) == 0);
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

	public void enemyMovement() {
		if(everyXSeconds(40)) {
			move = !move;
			program.remove(ixokStill.getImage());
			if(move) {ixokStill.setImage(attk);}
			else {ixokStill.setImage(still);}
			program.add(ixokStill.getImage());
		}
		
		for(Enemy enem : listOfEnemies) {
			
			enem.getImage().movePolar(2, degree);
			degree+=80;
			degree%=360;
			if(move) {
				double distX = enem.getImage().getX() - userRep.getX();
				double distY = enem.getImage().getY() - userRep.getY();
				double moveX = (distX * 5) / 100;
				double moveY = (distY * 5) / 100;
				enem.getImage().move(-moveX, -moveY);
			}else {xok.move(0, 0);}
			enem.setStartX(enem.getImage().getX());
			enem.setStartY(enem.getImage().getY());
		}
	}
}

