package RoomPanes;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import starter.GraphicsPane;
import starter.Interactions;
import starter.MainApplication;
import starter.interactionType;

public class mapBase_R9 extends GraphicsPane{
	private MainApplication program;
	private GImage E16, ENext, boss, background,userRep;
	private ArrayList<GImage> elements = new ArrayList<GImage>();
	private GRect voidSpace;
	private ArrayList<Interactions> listOfInter = new ArrayList<Interactions>();

	public mapBase_R9(MainApplication app) {
		this.program = app;
		Interactions iE16 = new Interactions(interactionType.entry_door_SOUTH,575,505);
		Interactions iENext = new Interactions(interactionType.entry_stair,575,300);
		E16 = iE16.getImage();
		ENext = iENext.getImage();
		
		// TODO: handle boss like how entries and enemies are handled
		boss = new GImage("Kirb_BOSS.gif", 575,200);
		boss.setSize(100, 100);
		
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		userRep = new GImage("Rogue_(Sample User).gif");
		userRep.setSize(75, 75);
		E16.setSize(75,75);
		ENext.setSize(75, 75);
		background.setSize(1125, 550);
		voidSpace = new GRect(0,0);
		voidSpace.setSize(1150,650);
		voidSpace.setColor(Color.BLACK);
		voidSpace.setFilled(true);
		
		listOfInter.add(iENext);
		listOfInter.add(iE16);
		
		elements.add(background);
		elements.add(E16);
		elements.add(boss);
		elements.add(ENext);
		elements.add(userRep);
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
		if (obj == ENext) {
			program.switchToMenu();
		}
		else if(obj == E16) {
			program.switchToR8();
			userRep.setLocation(575,435);
		}
	}
}
