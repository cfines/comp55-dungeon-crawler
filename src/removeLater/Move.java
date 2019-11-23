package removeLater;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import acm.graphics.GImage;

public class Move implements ActionListener {
	GImage enemyRep;
	private Timer enemTimer = new Timer(50, this);
	public int degree = 0;
	
	public Move(GImage enemyRep) {
		this.enemyRep = enemyRep;
		enemTimer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		enemyRep.movePolar(30, degree);
		degree+=10;
		degree = degree % 360;
	}
}
