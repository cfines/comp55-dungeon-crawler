package bombRoom;

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

public class osvaldoFloor_bossRoomComplete extends GraphicsPane implements ActionListener {
	private MainApplication program;
	private GImage E16, ENext,background,userRep, userWeapon, hole1, hole2, hole3, hole4, hole5, hole6, hole7, hole8, hole9, hole10, hole11, hole12, hole13, hole15; 
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private GRect voidSpace;
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private User user;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	private Timer t = new Timer(30, this);

	private KeyPressedManager mover;

	public osvaldoFloor_bossRoomComplete(MainApplication app) {
		this.program = app;
		user = program.getUser();
		Interactions iE16 = new Interactions(interactionType.entry_door_SOUTH,575,535);
		Interactions iENext = new Interactions(interactionType.entry_stair,575,300);
		
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
		listOfInter.add(ihole15);

		E16 = iE16.getImage();
		ENext = iENext.getImage();

		background = new GImage("Water_Floor (Regular Floor).png", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userWeapon = new GImage("Fire Sword(RIGHT).png", 0, 0);

		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);

		listOfInter.add(iENext);
		listOfInter.add(iE16);

		elements.add(background);
		elements.add(E16);
		elements.add(ENext);
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
		elements.add(hole15);

		mover = new KeyPressedManager(program, user, userRep, listOfEnemies, listOfInter, elements,
				atkUp, atkLeft, atkRight, atkDown, userWeapon);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		mover.notReallyActionPerformed(e);
	}

	@Override
	public void showContents() {
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
	public void keyPressed(KeyEvent e) {
		if((e.getKeyCode() == KeyEvent.VK_ESCAPE) || (e.getKeyCode() == KeyEvent.VK_Q)) {
			t.stop();
		}
		mover.notReallyKeyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		mover.notReallyKeyReleased(e);
		nextRoom();
		userRep.setLocation(user.getX(), user.getY());
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == E16) {
			program.switchToR8();
			userRep.setLocation(575,435);
		}
	}
	
	private void nextRoom() {
		double userX = userRep.getX();
		double userY = userRep.getY();
		double userX2 = userRep.getX() + 80;
		double userY2 = userRep.getY() + 80;
		//TODO fix these boundary checks
		if(userX >= ENext.getX() && userY >= ENext.getY() && userX <= ENext.getX() + 75 && userY <= ENext.getY() + 75) {
			program.setComingFromBoss(true);
			user.setX(575);
			user.setY(325);
			userRep.setLocation(user.getX(), user.getY());
			program.setBossDefeated(false);
			program.setFloorNum(program.getFloorNum() + 1);
			program.switchToChrisR1();
		} else if (userX <= E16.getX() && userY <= E16.getY() && userY2 >= E16.getY() - 30  && userX >= E16.getX() - 30) {
			user.setX(575);
			user.setY(130);
			userRep.setLocation(user.getX(), user.getY());
			program.switchToBombRoomR1();
		}
	}

}