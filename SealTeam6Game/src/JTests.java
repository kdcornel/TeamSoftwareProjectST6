import static org.junit.Assert.*;
import org.junit.*;
import org.lwjgl.openal.AL;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import main.*;

public class JTests {
	// Tests Internal assets
	@Test
	public void assetsExist() {
		assertTrue("arrows does not exist.", Gdx.files.internal("Assets/arrows.png").exists());
		assertTrue("background does not exist.", Gdx.files.internal("Assets/background.png").exists());
		assertTrue("Blackout does not exist.", Gdx.files.internal("Assets/Blackout.png").exists());
		assertTrue("Cave1 does not exist.", Gdx.files.internal("Assets/Cave1.wav").exists());
		assertTrue("Coin does not exist.", Gdx.files.internal("Assets/Coin.png").exists());
		assertTrue("Door does not exist.", Gdx.files.internal("Assets/Door.png").exists());
		assertTrue("fireball_0 does not exist.", Gdx.files.internal("Assets/fireball_0.png").exists());
		assertTrue("fireball_1 does not exist.", Gdx.files.internal("Assets/fireball_1.png").exists());
		assertTrue("Goomba does not exist.", Gdx.files.internal("Assets/Goomba.png").exists());
		assertTrue("hadouken does not exist.", Gdx.files.internal("Assets/hadouken.jpg").exists());
		assertTrue("Hellhound Left does not exist.", Gdx.files.internal("Assets/Hellhound Left.png").exists());
		assertTrue("Hellhound Right does not exist.", Gdx.files.internal("Assets/Hellhound Right.png").exists());
		assertTrue("HitPlayer does not exist.", Gdx.files.internal("Assets/HitPlayer.wav").exists());
		assertTrue("IntroFont does not exist.", Gdx.files.internal("Assets/IntroFont.png").exists());
		assertTrue("LowerResBkg does not exist.", Gdx.files.internal("Assets/LowerResBkg.jpg").exists());
		assertTrue("Man Crouching does not exist.", Gdx.files.internal("Assets/Man Crouching.png").exists());
		assertTrue("Man Jumping does not exist.", Gdx.files.internal("Assets/Man Jumping.png").exists());
		assertTrue("Man Standing does not exist.", Gdx.files.internal("Assets/Man Standing.png").exists());
		assertTrue("Man Walking Right does not exist.", Gdx.files.internal("Assets/Man Walking Right.png").exists());
		assertTrue("Man Walking Left does not exist.", Gdx.files.internal("Assets/Man Walking Left.png").exists());
		assertTrue("platform does not exist.", Gdx.files.internal("Assets/platform.png").exists());
		assertTrue("Spider Left does not exist.", Gdx.files.internal("Assets/Spider Left.png").exists());
		assertTrue("Spider Right does not exist.", Gdx.files.internal("Assets/Spider Right.png").exists());
		assertTrue("Track1 does not exist.", Gdx.files.internal("Assets/Track1.wav").exists());
		assertTrue("Track2 does not exist.", Gdx.files.internal("Assets/Track2.wav").exists());
	}
	// Tests application creation
	@Test
	public void applicationCreate() {
		LwjglApplicationConfiguration result = HelloWorld.createApplication();
		assertEquals(1920, result.width);
		assertEquals(1000, result.height);

		AL.destroy();
	}

	// Tests gravity physics
	@Test
	public void gravity() {
		Physics test = new Physics();
		float player = 5;
		player = test.gravity(player, 0);
		assertTrue("Position is greater than 0.", player <= 0);
	}
	
	// Tests level change
	@Test
	public void levelTransitions() {
		Levels test = new Levels();
		float xLocation = 0;
		int level = Levels.currentScene;
		int py = 0;
		
		// Level 1
		test.testStatus = true;
		test.changeScene(null, xLocation, py, null);
		level = Levels.currentScene;
		assertEquals(1, level);
		
		// Level 2 - (forward)
		xLocation = 1850;
		test.changeScene(null, xLocation, py, null);
		level = Levels.currentScene;
		assertEquals(2, level);
		
		// Level 1 - (backward)
		xLocation = -10;
		test.changeScene(null, xLocation, py, null);
		level = Levels.currentScene;
		assertEquals(1, level);
	}

	// Tests player attacks
	@Test
	public void attacks() {
		boolean result = false;
		Attack.attacking = true;
		result = Attack.attacking();
		assertEquals(true, result);
		
		Attack.attacking = false;
		result = Attack.attacking();
		assertEquals(false, result);
	}
	
	// Tests platform placement
	@Test
	public void platforms() {
		int[][] result = new int [5][3];
		Platform.placePlat(1, 2, result);
		// Int value of 1 signifies platform
		assertEquals(1, result[1][2]);
		// Int value of 0 signifies empty
		assertEquals(0, result[2][1]);
	}
}