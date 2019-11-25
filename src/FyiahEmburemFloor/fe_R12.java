package FyiahEmburemFloor;

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
import removeLater.User;

public class fe_R12 extends GraphicsPane implements ActionListener{
	private MainApplication program;
	private GImage Duma,enemy1, enemy2, enemy3, enemy4,background,userRep, userWeapon;
	private GRect voidSpace;
	private ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();
	private Enemy iDuma = new Enemy (69,69,2,4,900,100, ElementType.FIRE, enemyType.Duma);
	private User user;
	private int degree;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private KeyPressedManager mover;
	private boolean atkUp,atkDown,atkLeft,atkRight;
	private Timer t = new Timer(30, this);
	
	
	public fe_R12(MainApplication app) 
	{
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		
	}

}
