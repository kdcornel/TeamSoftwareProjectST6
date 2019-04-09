import com.badlogic.gdx.Gdx;

public class EnemyInput {
	public static float enemyX = HelloWorld.worldWidth - 100;
	static float enemyY = 0;
	static float enemySpeed = 150f;

	public static float getEnemyInput(Enemy enemy, Player player) {
		// Getting distance from player X direction
		float absDist = enemy.getX() - player.getX();
		float actDist = enemy.getX() - player.getX();
		if (absDist < 0) {
			absDist = absDist * -1;
		}

		if (absDist < 3) {
			return 0;
		}

		if (absDist < 175) {
			if (actDist < 0) {
				enemy.setX(enemy.getX() + Gdx.graphics.getDeltaTime() * enemySpeed);
			} else {
				enemy.setX(enemy.getX() - Gdx.graphics.getDeltaTime() * enemySpeed);
			}
		}
		return actDist;
	}
}
