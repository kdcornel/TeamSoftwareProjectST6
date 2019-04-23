import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Levels {
	int prevScene = 0;
	public static int currentScene = 0;
	boolean firstScene = true;
	boolean prevTwice = false;
	public boolean testStatus = false;
	
	private int[] platArr1 = {1,1,2,1,6,1,7,1,10,1,11,1,13,1,14,1,3,3,5,3,8,3,12,3,15,3,2,5,4,5,5,5,9,5,16,5,3,7,4,7,6,7,11,7,17,7,7,9,8,9,9,9};
	private int[] platArr2 = {3,1,7,1,11,1,15,1,4,2,8,2,12,2,13,3, 12,4, 11,5, 10,6, 9,7, 8,8, 7,9, 6,10, 5,10, 4,10, 3,10};
	private int[] platArr3 = {4,2,8,2,12,2,4,4,8,4,12,4,4,6,8,6,12,6,4,8,8,8,12,8,4,10,8,10,12,10};
	
	
	private int[] coinArr1 = {50,10, 110,100, 150,100};
	private int[] coinArr2 = {200,50, 400,100, 600,30, 800,100, 1000,50, 350,530, 400,530, 450,530, 500,530, 550,530, 600,530 };
	private int[] coinArr3 = {300, 100, 400, 200, 1200, 600};
	
	public int[] changePlats(){
		if (currentScene == 0){
		return platArr1;
		}
		if (currentScene == 1){
			return platArr1;
			}
		if (currentScene == 2){
			return platArr2;
			}
		if (currentScene == 3){
			return platArr3;
			}
		return platArr1;
	}
	
	
	public int[] changeCoins(){
		if (currentScene == 0){
		return coinArr1;
		}
		if (currentScene == 1){
			return coinArr1;
			}
		if (currentScene == 2){
			return coinArr2;
			}
		if (currentScene == 3){
			return coinArr3;
			} else
		return coinArr1;
	}
	
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
			if (currentScene < 3){
			prevTwice = false;
			currentScene++;
			prevScene++;
			playerX = 0;
			} else {playerX = 1840;}
		}
		// Backwards transition
		if (playerX < 0 && prevTwice == false)
		{
			prevTwice = true;
			if (currentScene > 1){
			currentScene--;
			prevScene++;
			playerX = 1800;
			} else {playerX = 0;}
		}
		// Double backwards transition
		if (playerX < 0 && prevTwice == true)
		{
			if (currentScene > 1){
				currentScene--;
				prevScene--;
				playerX = 1800;
				} else {playerX = 0;}
		}
		if (testStatus == true)
		{
			return playerX;
		}
		// First level
		if (currentScene == 1)
		{
			firstScene = false;
			batchMain.draw(HelloWorld.region1, 0, 0);
			batchMain.draw(HelloWorld.introFont, 400, 550);
			//batchMain.draw(HelloWorld.tutorialArrows, 0, 0);
			
		}
		// Second level
		if (currentScene == 2)
		{
			batchMain.draw(HelloWorld.region2, 0, 0);
			//batchMain.draw(platform, 150, pY);
		}
		// Third level
		if (currentScene == 3)
		{
			batchMain.draw(HelloWorld.region3, 0, 0);
			//batchMain.draw(platform, 300, pY);
		}
		return playerX;
	}
}
