package starter;

public class Enemy {
	private Stats enemyStats;
	private int moveSpeedStat;
	private double powerStat;
	private ElementType enemyType;
	private int dx, dy;
	
	Enemy(){};
	
	Enemy (int input_HP_cur, int input_HP_tot, int atkTime,
			int input_dmg, int input_x, int input_y, ElementType bossType)
	{
		enemyStats = new Stats(input_HP_cur, input_HP_tot, atkTime, input_dmg, input_x, input_y);
		moveSpeedStat = 5;
		powerStat = 5;
		this.enemyType = bossType;
	}
	
	public ElementType getEnemyType() 
	{
		return enemyType;
	}
	
	public void setDX(int dx) {
		this.dx = dx;
	}
	
	public void setDY(int dy) {
		this.dy = dy;
	}
	
	public double getCoordX() {
		return enemyStats.getCoordX();
	}
	
	public double getCoordY() {
		return enemyStats.getCoordY();
	}
	
	public void tick(){
		
		enemyStats.setCoordX(enemyStats.getCoordX() + dx);
		dx+=5;
		enemyStats.setCoordY(enemyStats.getCoordY() + dy);
		dy+=5;
	}
	
}
