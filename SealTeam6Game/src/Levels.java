import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Levels {
	int prevScene = 0;
	int currentScene = 0;
	boolean firstScene = true;
	boolean prevTwice = false;
	
	public float changeScene(SpriteBatch batchMain, float playerX, int pY, Texture platform)
	{
		// Only on start scene
		if (firstScene == true && playerX <= 1800)
		{
			currentScene = 1;
		}
		// Forward transition
		if (playerX >= 1840)
		{
			prevTwice = false;
			currentScene++;
			prevScene++;
			playerX = 0;
		}
		// Backwards transition
		if (playerX < 0 && prevTwice == false)
		{
			prevTwice = true;
			currentScene--;
			prevScene++;
			playerX = 1800;
		}
		// Double backwards transition
		if (playerX < 0 && prevTwice == true)
		{
			currentScene--;
			prevScene--;
			playerX = 1800;
		}
		// First level
		if (currentScene == 1)
		{
			firstScene = false;
			batchMain.draw(HelloWorld.region1, 0, 0);
			batchMain.draw(HelloWorld.introFont, 400, 550);
			batchMain.draw(HelloWorld.tutorialArrows, 0, 0);
			
		}
		// Second level
		if (currentScene == 2)
		{
			batchMain.draw(HelloWorld.region2, 0, 0);
			batchMain.draw(platform, 150, pY);
		}
		// Third level
		if (currentScene == 3)
		{
			batchMain.draw(HelloWorld.region1, 0, 0);
			batchMain.draw(platform, 300, pY);
		}
		return playerX;
	}
}
