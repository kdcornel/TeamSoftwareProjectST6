package tests;
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

import main.*;

public class JTests {
	
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
		LwjglApplicationConfiguration result = HelloWorld.createApplication();
		assertEquals(750, result.width);
		assertEquals(500, result.height);
		
		//LwjglApplication app = new LwjglApplication(new HelloWorld(), result);
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
		int result;
		
		// Down 
		r.keyPress(40);
		test.getPlayerInput();
		result = test.currentAnim;
		assertTrue("Not crouch animation.", result == 3);
		r.keyRelease(40);
		
		// Left
		r.keyPress(37);
		test.getPlayerInput();
		result = test.currentAnim;
		assertEquals(2, result);
		r.keyRelease(37);
				
		// Right
		r.keyPress(39);
		test.getPlayerInput();
		result = test.currentAnim;
		assertTrue("Not right animation.", result == 1);
		r.keyRelease(39);
	}
	@Test
	public void attacks() {
		HelloWorld test = new HelloWorld();
		boolean result = false;
		boolean inputTest = true;
		test.getAttack(inputTest);
		result = test.attacking;
		assertEquals(true, result);
	}
}