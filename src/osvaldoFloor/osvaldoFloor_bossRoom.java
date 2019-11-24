package osvaldoFloor;

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

public class osvaldoFloor_bossRoom extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage background,userRep, userWeapon, shoot; 
	private GImage boss = new GImage("electric.jpg", 100, 100); 
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Enemy> listOfProjectiles = new ArrayList<Enemy>();
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private int degree;
	private User user;
	private Enemy osvaldoom = new Enemy(20,20,2,2,100,100, ElementType.FIRE, enemyType.electric);
	private Enemy shot = new Enemy(100, 100, 2, 2, 0, 0, ElementType.FIRE, enemyType.projectile);
	private enemyType attk = enemyType.electric;
	private enemyType still = enemyType.electric;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	private Timer t = new Timer(30, this);
	private int timerCont = 0;
	private boolean move = false;
	private boolean attack = false;
	private boolean hit = false;

	private KeyPressedManager mover;

	public osvaldoFloor_bossRoom(MainApplication app) {
		this.program = app;
		user = program.getUser();

		boss = osvaldoom.getImage();
		boss.setSize(100, 100);
		
		shoot = new GImage("leg.gif");

		background = new GImage("Fire_Floor (Regular Floor).png", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);

		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);

		listOfEnemies.add(osvaldoom);

		elements.add(background);
		elements.add(boss);
		elements.add(userRep);

		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, elements,
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}

	@Override
	public void showContents() {
		
		if(osvaldoom.getEnemyStats().getHP_cur() <= 0) {
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
		
		if(osvaldoom.getEnemyStats().getHP_cur() <= 0) {
			program.setBossDefeated(true);
			program.switchToR9Complete();
		}
		
		timerCont++;
		enemyMovement();
		mover.notReallyActionPerformed(e);
		userRep.setLocation(user.getX(), user.getY());
	}

	public boolean everyXSeconds(double x) {
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
			attack = !attack;
			if(attack) {
				shot = new Enemy(100, 100, 2, 2, (int)osvaldoom.getCoordX(), (int)osvaldoom.getCoordY(), ElementType.FIRE, enemyType.projectile);
				listOfProjectiles.add(shot);
			} else {
				listOfProjectiles.remove(shot);
				program.remove(shot.getImage());
			}
		}
		
		for(Enemy enem : listOfEnemies) {
			if(enem.getCoordX() > 970) {
				move = false;
			} else if (enem.getCoordX() < 100) {
				move = true;
			}
			
			if(move) { enem.getImage().move(10, 0); }
			else { enem.getImage().move(-10, 0); }
			
			enem.setStartX(enem.getImage().getX());
			enem.setStartY(enem.getImage().getY());
			
		}
		
		if(listOfProjectiles.size() >= 1) {
			for(Enemy arr : listOfProjectiles) {
				
				arr.getEnemyStats().setCoordX(osvaldoom.getCoordX());
				arr.getEnemyStats().setCoordY(osvaldoom.getCoordY());
				
				program.add(arr.getImage());
				
				if(checkHitBack(arr, userWeapon)) { arr.getImage().move(0, -10); }
				else { arr.getImage().move(0, 10); }
			
			
			}
		}
		
	}
	
	public boolean checkHitBack(Enemy enem, GImage image) {
		return (enem.getImage().getY() - image.getY() <= 60
				&& enem.getImage().getY() - image.getY() >= -60
				&& enem.getImage().getX() - image.getX() <= 60
				&& enem.getImage().getX() - image.getX() >= -60);
	}
	
}

