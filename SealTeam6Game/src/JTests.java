import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;
import org.lwjgl.openal.AL;

import java.awt.AWTException;
import java.awt.Robot;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class JTests extends GameTest {
	
	// Internal assets
	@Test
	public void assetsExist() {
		assertTrue("Man Walking Right does not exist.",Gdx.files.internal("Assets/Man Walking Right.png").exists());
		assertTrue("Man Walking Left does not exist.",Gdx.files.internal("Assets/Man Walking Left.png").exists());
		assertTrue("Man Crouching does not exist.",Gdx.files.internal("Assets/Man Crouching.png").exists());
	}
	// Application created
	@Test
	public void applicationLaunch() {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.width = 1000;
		cfg.height = 500;

		LwjglApplication app = new LwjglApplication(new HelloWorld(), cfg);
		assertEquals(cfg.width, 1000);
		assertEquals(cfg.height, 500);
		
		Gdx.app.exit();
		AL.destroy();
	}
	// Tests gravity physics
	@Test
	public void gravity() {
		Physics test = new Physics();
		float goombaY = 7; 
		goombaY = test.gravity(goombaY);
		assertTrue("Position is greater than 0.", goombaY <= 0);
	}
	// Tests character sprite animation
	@Test
	public void currentAnimations() throws AWTException {
		HelloWorld test = new HelloWorld();
		Robot r = new Robot();
		int currentAnim = 0;
		
		// Down 
		r.keyPress(40);
		currentAnim = test.keyPresses(currentAnim);
		assertTrue("Not crouch animation.", currentAnim == 3);
		r.keyRelease(40);
		currentAnim = 0;
				
		// Right
		r.keyPress(39);
		currentAnim = test.keyPresses(currentAnim);
		assertTrue("Not right animation.", currentAnim == 1);
		r.keyRelease(39);
		currentAnim = 0;
				
		// Left
		r.keyPress(37);
		currentAnim = test.keyPresses(currentAnim);
		assertTrue("Not left animation.", currentAnim == 2);
		r.keyRelease(37);
		currentAnim = 0;
	}
	// Tests creation of a Bitmapfont - Currently Causes Error
	@Test
	public void fontColorTest() {	
		HelloWorld test = new HelloWorld(); // Initialize test
		BitmapFont result = new BitmapFont();
		BitmapFont expected = new BitmapFont(); // New BitmapFont of what should happen
		test.setFontColor(result); // Run setFontColor method
		expected.setColor(Color.BLUE); // Set the expected color to blue
	    assertEquals(expected.getColor(), result.getColor()); // Compare expected color to actual color
    }
}