package RoomPanes;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GraphicsPane;
import starter.MainApplication;

public class mapBase_R9 extends GraphicsPane{
	private MainApplication program;
	private GImage E16, ENext, boss, background;
	private ArrayList<GImage> elements = new ArrayList<GImage>();

	public mapBase_R9(MainApplication app) {
		this.program = app;
		E16 = new GImage("entry_door_SOUTH.png",575,500);
		ENext = new GImage("entry_stair.png",575,300);
		boss = new GImage("Kirb_BOSS.gif", 575,200);
		background = new GImage("Base_Floor (Regular Floor).png", 15,30);
		
		E16.setSize(75,75);
		ENext.setSize(75, 75);
		background.setSize(1125, 550);
		boss.setSize(100, 100);
		
		elements.add(background);
		elements.add(E16);
		elements.add(boss);
		elements.add(ENext);
	}

	@Override
	public void showContents() {
		 // TODO Auto-generated method stub
		for (int i = 0; i <= elements.size() - 1; i++) {
			program.add(elements.get(i));
		}
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
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
	}
}
