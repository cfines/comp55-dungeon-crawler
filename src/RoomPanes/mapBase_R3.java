package RoomPanes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import starter.ElementType;
import starter.Enemy;
import starter.GraphicsPane;
import starter.Interactions;
import starter.MainApplication;
import starter.enemyType;
import starter.interactionType;

public class mapBase_R3 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage enemy1, enemy2, E4, E5, rock1, hole1, background,userRep;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private boolean atkUp,atkLeft,atkRight,atkDown;

	public mapBase_R3(MainApplication app) {
		this.program = app;
		Enemy ienemy1 = new Enemy(2,2,2,2,800,70,ElementType.EARTH, enemyType.EARTHBat);
		Enemy ienemy2 = new Enemy(2,2,2,2,575,70,ElementType.FIRE, enemyType.FIRESkull);
		Interactions iE4 = new Interactions(interactionType.entry_door_WEST,27,300);
		Interactions iE5 = new Interactions(interactionType.entry_door_EAST,1050,300);
		Interactions irock1 = new Interactions(interactionType.obstacle_rock,575,325);
		Interactions ihole1 = new Interactions(interactionType.obstacle_hole,230,163);
		enemy1 = ienemy1.getImage();
		enemy2 = ienemy2.getImage();
		E4 = iE4.getImage();
		E5 = iE5.getImage();
		rock1 = irock1.getImage();
		hole1 = ihole1.getImage();
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);

		listOfEnemies.add(ienemy2);
		listOfEnemies.add(ienemy1);

		listOfInter.add(ihole1);
		listOfInter.add(iE5);
		listOfInter.add(iE4);
		listOfInter.add(irock1);

		userRep = new GImage("Rogue_(Sample User).gif");
		userRep.setSize(75, 75);

		hole1.setSize(200, 200);
		background.setSize(1125, 550);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);

		elements.add(background);
		elements.add(enemy1);
		elements.add(enemy2);
		elements.add(E4);
		elements.add(E5);
		elements.add(rock1);
		elements.add(hole1);
		elements.add(userRep);
	}

	private void userUP() {
		userRep.move(0, -5);
	}
	private void userDOWN() {
		userRep.move(0, 5);
	}
	private void userLEFT() {
		userRep.move(-5, 0);
	}
	private void userRIGHT() {
		userRep.move(5, 0);
	}

	@Override
	public void showContents() {
		program.add(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
	}

	@Override
	public void hideContents() {
		program.remove(voidSpace);
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.remove(elements.get(i));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == E4) {
			program.switchToR2();
			userRep.setLocation(90,300);
		}
		else if(obj == E5) {
			program.switchToR4();
			userRep.setLocation(1010,300);
		}
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			userUP();
			break;
		case KeyEvent.VK_S:
			userDOWN();
			break;
		case KeyEvent.VK_A:
			userLEFT();
			break;
		case KeyEvent.VK_D:
			userRIGHT();
			break;
		case KeyEvent.VK_UP:
			atkUp = true;
			if(atkUp == true) 
			{
				userRep.setImage("Rogue_Attack(Up).png");
				userRep.setSize(75,75);
			}
			break;
		case KeyEvent.VK_LEFT:
			atkLeft = true;
			if(atkLeft == true) 
			{
				userRep.setImage("Rogue_Attack(Left).png");
				userRep.setSize(75,75);
			}
			break;
		case KeyEvent.VK_DOWN:
			atkDown = true;
			if(atkDown == true) 
			{
				userRep.setImage("Rogue_Attack(Down).png");
				userRep.setSize(75,75);
			}
			break;
		case KeyEvent.VK_RIGHT:
			atkRight = true;
			if(atkRight == true) 
			{
				userRep.setImage("Rogue_Attack(Right).png");
				userRep.setSize(75,75);
			}
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) 
	{
		switch (e.getKeyCode()) {
		// for stopping attack 
		case KeyEvent.VK_UP:
			atkUp = false;
			if(atkUp == false) 
			{
				userRep.setImage("Rogue_(Sample User).gif");
				userRep.setSize(75,75);
			}
			break;

		case KeyEvent.VK_LEFT:
			atkLeft = false;
			if(atkLeft == false) 
			{
				userRep.setImage("Rogue_(Sample User).gif");
				userRep.setSize(75,75);
			}
			break;

		case KeyEvent.VK_DOWN: 
			atkDown = false;
			if(atkDown == false) 
			{
				userRep.setImage("Rogue_(Sample User).gif");
				userRep.setSize(75,75);
			}
			break;

		case KeyEvent.VK_RIGHT: 
			atkRight = false;
			if(atkRight == false) 
			{
				userRep.setImage("Rogue_(Sample User).gif");
				userRep.setSize(75,75);
			}
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

