package starter;

public class Enemy {
	private Stats enemyStats;
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
	
	public void setDX(int dx) {
		this.dx = dx;
	}
	
	public void setDY(int dy) {
		this.dy = dy;
	}
	
	public int getCoordX() {
		return enemyStats.getCoordX();
	}
	
	public int getCoordY() {
		return enemyStats.getCoordY();
	}
	
	public void tick(){
		enemyStats.setCoordX(enemyStats.getCoordX() + 2);
		enemyStats.setCoordY(enemyStats.getCoordY() + 2);
	}
	
	public void move(User user) {
		dx = enemyStats.getCoordX();
		dy = enemyStats.getCoordY();
		userX = user.getCoordX();
		userY = user.getCoordY();
		
		if(dy > userY) {
			enemyStats.setCoordY(dy -= moveSpeedStat);
			if(dy < -moveSpeedStat) {
				dy = -moveSpeedStat;
				System.out.println("Enemy y: " + dy);
			}
		}
		else if(dx > userX) {
			enemyStats.setCoordY(dx -= moveSpeedStat);
			if(dx < -moveSpeedStat) {
				dx = -moveSpeedStat;
				System.out.println("Enemy x: " + dx);
			}
		}

	}
}
