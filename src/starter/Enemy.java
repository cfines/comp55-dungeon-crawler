package starter;

public class Enemy {
	private Stats enemyStats;
	private User userStats;
	private ElementType enemyType;
	private int dx, dy, userX, userY;
	
	Enemy (int input_HP_cur, int input_HP_tot, int atkTime,
			int input_dmg, int input_x, int input_y, ElementType bossType)
	{
		enemyStats = new Stats(input_HP_cur, input_HP_tot, atkTime, input_dmg, input_x, input_y);
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
	
	public void move() {
		dx = enemyStats.getCoordX();
		dy = enemyStats.getCoordY();
		userX = userStats.getCoordX();
		userY = userStats.getCoordY();
		
		if(dx <= userX + 500 && dy <= userY + 500) {
			
		}
		else {
			
		}
	}
}
