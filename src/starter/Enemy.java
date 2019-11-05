package starter;

public class Enemy {
	private Stats enemyStats;
	private User userStats;
	private int moveSpeedStat;
	private double powerStat;
	private ElementType enemyType;
	private int dx, dy, userX, userY;
	
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
	
	public void tick(){
		enemyStats.setCoordX(enemyStats.getCoordX() + 2);
		enemyStats.setCoordY(enemyStats.getCoordY() + 2);
	}
	
	public void move(User user) {
		dx = enemyStats.getCoordX();
		dy = enemyStats.getCoordY();
		userX = userStats.getCoordX();
		userY = userStats.getCoordY();
		
		if(dy > userY) {
			enemyStats.setCoordY(dy -= moveSpeedStat);
			if(dy < -moveSpeedStat) {
				dy = -moveSpeedStat;
			}
		}
	}
}
