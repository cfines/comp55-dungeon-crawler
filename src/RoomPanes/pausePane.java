package RoomPanes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import removeLater.GButton;

public class pausePane extends GraphicsPane implements ActionListener {
	private MainApplication program;
	private GButton pauseButton;
	private GButton resumeButton;
	private GLabel pauseLabel;
	
	///////DEVELOPER_MODE_LABELS_AND_BUTTONS/////////////////
	private GLabel baseFloor;
	private GLabel osvaldoomFloor;
	private GLabel shadowRealm;
	private GLabel lucinaDomain;
	private GLabel schtanlyParable;
	private GLabel bombFloor;
	private GButton BF_R1;
	private GButton BF_RKey;
	private GButton BF_R8;
	private GButton BF_Boss;
	private GButton OF_R1;
	private GButton OF_RKey;
	private GButton OF_Boss;
	private GButton SR_R1;
	private GButton SR_RKey;
	private GButton SR_RBoss;
	private GButton LD_R1;
	private GButton LD_RKey;
	private GButton LD_RBoss;
	private GButton SP_R1;
	private GButton SP_RKey;
	private GButton SP_Boss;
	private GButton BOMB_R1;
	private GButton BOMB_Bomb1;
	private GButton BOMB_Bomb2;
	private GButton BOMB_Bomb3;
	
	
	public pausePane(MainApplication app){
		this.program = app;
		pauseButton = new GButton("Quit", program.WINDOW_WIDTH/2 - 75, program.WINDOW_HEIGHT - 130, 150, 50);
		resumeButton = new GButton("Resume", program.WINDOW_WIDTH/2 - 75, program.WINDOW_HEIGHT - 190, 150, 50);
		pauseLabel = new GLabel("PAUSED", program.WINDOW_WIDTH / 2 - 135 ,85);
		pauseLabel.setColor(Color.red);
		pauseLabel.setFont("Arial-Bold-64");
		
	}

	@Override
	public void showContents() {
		program.add(pauseButton);
		program.add(pauseLabel);
		program.add(resumeButton);
		
		if(program.getDeveloperMode()) {
			
		}
		
	}

	@Override
	public void hideContents() {
		program.remove(pauseButton);
		program.remove(pauseLabel);
		program.remove(resumeButton);
		
		if(program.getDeveloperMode()) {
			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			program.noLongerPaused();
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == pauseButton) {
			program.switchToMenu();
		} else if (obj == resumeButton) {
			program.noLongerPaused();
		}
	}
	
	
	
}
