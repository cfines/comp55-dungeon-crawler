package starter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import acm.graphics.GImage;

public class Enemy {
	private Coordinates startUp;
	private Stats enemyStats;
	private int moveSpeedStat;
	private double powerStat;
	private ElementType elementType;
	private enemyType EnemyType;
	private double dx, dy;
	private GImage image, boss;
	public int degree = 0;
	private GImage xokStill = new GImage ("xokStill.png", 375, 375);
	private GImage xokAttack = new GImage("xokAttack.png",375,375);
	
	Enemy(){};
	
	public Enemy (int input_HP_cur, int input_HP_tot, int atkTime,
			int input_dmg, int input_x, int input_y, ElementType element, enemyType enemy)
	{
		startUp = new Coordinates(input_x, input_y);
		dx = input_x;
		dy = input_y;
		enemyStats = new Stats(input_HP_cur, input_HP_tot, atkTime, input_dmg, input_x, input_y);
		moveSpeedStat = 5;
		powerStat = 5;
		elementType = element;
		EnemyType = enemy;
		setImage(enemy);
	}
	
	
	public ElementType getElementType() 
	{
		return elementType;
	}
	
	public enemyType getEnemyType() 
	{
		return EnemyType;
	}
	
	
	public void setImage(enemyType type) {
		if(type == enemyType.EARTHBat || type == enemyType.FIREBat || type == enemyType.WATERBat || type == enemyType.EARTHDrawing || type == enemyType.FIREDrawing || type == enemyType.WATERDrawing || type == enemyType.EARTHSpider || type == enemyType.FIRESpider || type == enemyType.WATERSpider || type == enemyType.EARTHSpooder || type == enemyType.FIRESpooder || type == enemyType.WATERSpooder || type == enemyType.EARTHDeath || type == enemyType.WATERDeath || type == enemyType.FIREDeath || type == enemyType.EARTHCheese || type == enemyType.WATERCheese 
				|| type == enemyType.FIRECheese || type == enemyType.EARTHFish || type == enemyType.WATERFish || type == enemyType.FIREFish || type == enemyType.leg || type == enemyType.goon1 || type == enemyType.goon2 || type == enemyType.insidePacific || type == enemyType.momoko) {
			image = new GImage(type + ".gif", dx, dy);
		}
		else{image = new GImage(type + ".png", dx, dy);}
	}
	
	public GImage getImage() {
		return image;
	}
	
	public Stats getEnemyStats() {
		return enemyStats;
	}
	
	public void setStartX(double x) {
		dx = x;
	}
	
	public void setStartY(double y) {
		dy = y;
	}
	
	public double getCoordX() {
		return dx;
	}
	
	public double getCoordY() {
		return dy;
	}
	
	public void tick() {
		enemyStats.setCoordX(getCoordX() + 5);
		enemyStats.setCoordY(getCoordY() + 5);
		setStartX(getCoordX() + 5);
		setStartY(getCoordY() + 5);
	}
}

