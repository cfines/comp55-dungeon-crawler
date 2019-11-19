package RoomPanes;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import starter.GraphicsPane;
import starter.MainApplication;

public class mapBase_R9 extends GraphicsPane{
	private MainApplication program;
	private GImage E16, ENext, boss, background,userRep;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private ArrayList<GRect> space = new ArrayList<GRect>();
	private GRect voidSpace;
	private ArrayList<GImage> you = new ArrayList<GImage>();

	public mapBase_R9(MainApplication app) {
		this.program = app;
		E16 = new GImage("entry_door_SOUTH.png",575,505);
		ENext = new GImage("entry_stair.png",575,300);
		boss = new GImage("Kirb_BOSS.gif", 575,200);
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userRep.setSize(75, 75);
		E16.setSize(75,75);
		ENext.setSize(75, 75);
		background.setSize(1125, 550);
		boss.setSize(100, 100);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		space.add(voidSpace);
		elements.add(background);
		elements.add(E16);
		elements.add(boss);
		elements.add(ENext);
		you.add(userRep);
	}

	@Override
	public void showContents() {
		program.add(space.get(0));
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
		program.add(you.get(0));
	}

	@Override
	public void hideContents() {
		program.remove(space.get(0));
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.remove(elements.get(i));
		}
		program.remove(you.get(0));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == ENext) {
			program.switchToMenu();
		}
		else if(obj == E16) {
			program.switchToR8();
			userRep.setLocation(575,435);
		}
	}
}
