package osvaldoFloor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

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
	private GImage background,userRep, userWeapon, hole1, hole2, hole3, hole4, hole5, hole6, hole7, hole8, hole9, hole10, hole11, hole12, hole13, hole14, hole15; 
	private GImage boss = new GImage("electric.jpg", 100, 100); 
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Enemy> listOfProjectiles = new ArrayList<Enemy>();
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private int degree;
	private User user;
	private Enemy osvaldoom = new Enemy(1,1,2,2,100,100, ElementType.FIRE, enemyType.electric);
	private Enemy shot = new Enemy(100, 100, 5, 1, 0, 0, ElementType.FIRE, enemyType.projectile);
	private enemyType attk = enemyType.electric;
	private enemyType still = enemyType.electric;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	private Timer t = new Timer(30, this);
	private int timerCont = 0;
	private boolean move = false;
	private boolean attack = false;
	private boolean hit = false;
	private Random randomNum = new Random();
	private Random determineNegative = new Random();
	private int dN = determineNegative.nextInt(1);
	private int random = randomNum.nextInt(5);

	private KeyPressedManager mover;

	public osvaldoFloor_bossRoom(MainApplication app) {
		this.program = app;
		user = program.getUser();

		Interactions ihole1 = new Interactions(interactionType.obstacle_hole, 130,230);
		Interactions ihole2 = new Interactions(interactionType.obstacle_hole, 207,230);
		Interactions ihole3 = new Interactions(interactionType.obstacle_hole, 284,230);
		Interactions ihole4 = new Interactions(interactionType.obstacle_hole, 361,230);
		Interactions ihole5 = new Interactions(interactionType.obstacle_hole, 438,230);
		Interactions ihole6 = new Interactions(interactionType.obstacle_hole, 515,230);
		Interactions ihole7 = new Interactions(interactionType.obstacle_hole, 592,230);
		Interactions ihole8 = new Interactions(interactionType.obstacle_hole, 669,230);
		Interactions ihole9 = new Interactions(interactionType.obstacle_hole, 746,230); 
		Interactions ihole10 = new Interactions(interactionType.obstacle_hole, 823,230);
		Interactions ihole11 = new Interactions(interactionType.obstacle_hole, 900,230);
		Interactions ihole12 = new Interactions(interactionType.obstacle_hole, 977,230);
		Interactions ihole13 = new Interactions(interactionType.obstacle_hole, 284,400);
		Interactions ihole14 = new Interactions(interactionType.obstacle_hole, 592,305);
		Interactions ihole15 = new Interactions(interactionType.obstacle_hole, 746,400);
		
		hole1 = ihole1.getImage();
		hole2 = ihole2.getImage();
		hole3 = ihole3.getImage();
		hole4 = ihole4.getImage();
		hole5 = ihole5.getImage();
		hole6 = ihole6.getImage();
		hole7 = ihole7.getImage();
		hole8 = ihole8.getImage();
		hole9 = ihole9.getImage();
		hole10 = ihole10.getImage();
		hole11 = ihole11.getImage();
		hole12 = ihole12.getImage();
		hole13 = ihole13.getImage();
		hole14 = ihole14.getImage();
		hole15 = ihole15.getImage();
		
		listOfInter.add(ihole1);
		listOfInter.add(ihole2);
		listOfInter.add(ihole3);
		listOfInter.add(ihole4);
		listOfInter.add(ihole5);
		listOfInter.add(ihole6);
		listOfInter.add(ihole7);
		listOfInter.add(ihole8);
		listOfInter.add(ihole9);
		listOfInter.add(ihole10);
		listOfInter.add(ihole11);
		listOfInter.add(ihole12);
		listOfInter.add(ihole13);
		listOfInter.add(ihole14);
		listOfInter.add(ihole15);
		
		boss = osvaldoom.getImage();
		boss.setSize(100, 100);
		background = new GImage("Water_Floor (Regular Floor).png", 15,30);
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
		elements.add(hole1);
		elements.add(hole2);
		elements.add(hole3);
		elements.add(hole4);
		elements.add(hole5);
		elements.add(hole6);
		elements.add(hole7);
		elements.add(hole8);
		elements.add(hole9);
		elements.add(hole10);
		elements.add(hole11);
		elements.add(hole12);
		elements.add(hole13);
		elements.add(hole14);
		elements.add(hole15);
		
		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, elements,
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}

	@Override
	public void showContents() {
		
		if(osvaldoom.getEnemyStats().getHP_cur() <= 0) {
			program.setBossDefeated(true);
			program.switchToOsvaldoBossComplete();
		}
		
		t.start();
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		program.drawOverlay(2, program.getFloorNum());
		program.bossOverlay(osvaldoom);
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
			program.switchToOsvaldoBossComplete();
		}
		
		userCombat();
		enemyCombat();
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
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			atkUp = true;
		}
		mover.notReallyKeyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			atkUp = false;
		}
		mover.notReallyKeyReleased(e);
	}

	public void enemyMovement() {
		if(everyXSeconds(80)) {
			attack = !attack;
			if(attack) {
				generateRandom();
				shot = new Enemy(100, 100, 2, 2, (int)osvaldoom.getCoordX(), (int)osvaldoom.getCoordY() + 75, ElementType.FIRE, enemyType.projectile);
				listOfProjectiles.add(shot);
			} else {
				listOfProjectiles.remove(shot);
				program.remove(shot.getImage());
				hit = false;
			}
		}
		
		for(Enemy enem : listOfEnemies) {
			if(enem.getCoordX() > 970) {
				move = false;
			} else if (enem.getCoordX() < 100) {
				move = true;
			}
			
			if(move) { enem.getImage().move(8, 0); }
			else { enem.getImage().move(-8, 0); }
			
			enem.setStartX(enem.getImage().getX());
			enem.setStartY(enem.getImage().getY());
			enem.getEnemyStats().setCoordX(enem.getImage().getX());
			enem.getEnemyStats().setCoordY(enem.getImage().getY());
			
		}
		
		if(listOfProjectiles.size() >= 1) {
			for(Enemy arr : listOfProjectiles) {
				
				arr.getEnemyStats().setCoordX(osvaldoom.getCoordX());
				arr.getEnemyStats().setCoordY(osvaldoom.getCoordY());
				
				
				if(checkHitBack(arr, userWeapon) && atkUp) { 
					hit = true; 
				}
				
				program.add(arr.getImage());
				
				if(hit) { 
					
					arr.getImage().move(random, -10);
					
				} else { 
					
					arr.getImage().move(0, 10); 
					
				}
			
			
			}
		}
		
	}
	
	public void generateRandom() {
		randomNum = new Random();
		random = randomNum.nextInt(10);
		
		Random determineNegative = new Random();
		dN = determineNegative.nextInt(5);
		
		if(dN < 2) {
			random *= -1;
		}
	}
	
	public boolean checkHitBack(Enemy enem, GImage image) {
		return (enem.getImage().getY() - image.getY() <= 60
				&& enem.getImage().getY() - image.getY() >= -60
				&& enem.getImage().getX() - image.getX() <= 60
				&& enem.getImage().getX() - image.getX() >= -60);
	}
	
	public void enemyCombat() {
		for(int i = 0; i < listOfProjectiles.size(); i++) {
			if(checkHitBack(listOfProjectiles.get(i), userRep)) { 
				int newHealth = program.getUser().getUserStats().getHP_cur() - 1;
				program.getUser().getUserStats().setHP_cur(newHealth);
				program.combatRefreshOverlay();
			}
		}
	}
	
	public void userCombat() {
		for(int i = 0; i < listOfProjectiles.size(); i++) {
			if(checkHitBack(listOfProjectiles.get(i), osvaldoom.getImage())) { 
				System.out.println("You killed Osvaldoom!");
				osvaldoom.getEnemyStats().setHP_cur(0);
				listOfProjectiles.remove(listOfProjectiles.get(i));
				program.bossOverlay(osvaldoom);
				program.remove(shot.getImage());
			}
		}
	}
	
}

