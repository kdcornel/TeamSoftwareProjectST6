//package tests;

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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import main.*;

public class JTests {
	// Tests Internal assets
	@Test
	public void assetsExist() {
		assertTrue("background does not exist.", Gdx.files.internal("Assets/background.png").exists());
		assertTrue("Coin does not exist.", Gdx.files.internal("Assets/Coin.png").exists());
		assertTrue("Door does not exist.", Gdx.files.internal("Assets/Door.png").exists());
		assertTrue("fireball_0 does not exist.", Gdx.files.internal("Assets/fireball_0.png").exists());
		assertTrue("fireball_1 does not exist.", Gdx.files.internal("Assets/fireball_1.png").exists());
		assertTrue("Goomba does not exist.", Gdx.files.internal("Assets/Goomba.png").exists());
		assertTrue("Goomba does not exist.", Gdx.files.internal("Assets/hadouken.jpg").exists());
		assertTrue("Hellhound Left does not exist.", Gdx.files.internal("Assets/Hellhound Left.png").exists());
		assertTrue("Hellhound Right does not exist.", Gdx.files.internal("Assets/Hellhound Right.png").exists());
		assertTrue("LowerResBkg does not exist.", Gdx.files.internal("Assets/LowerResBkg.jpg").exists());
		assertTrue("Man Crouching does not exist.", Gdx.files.internal("Assets/Man Crouching.png").exists());
		assertTrue("Man Jumping does not exist.", Gdx.files.internal("Assets/Man Jumping.png").exists());
		assertTrue("Man Standing does not exist.", Gdx.files.internal("Assets/Man Standing.png").exists());
		assertTrue("Man Walking Right does not exist.", Gdx.files.internal("Assets/Man Walking Right.png").exists());
		assertTrue("Man Walking Left does not exist.", Gdx.files.internal("Assets/Man Walking Left.png").exists());
		assertTrue("platform does not exist.", Gdx.files.internal("Assets/platform.png").exists());
		assertTrue("Spider Left does not exist.", Gdx.files.internal("Assets/Spider Left.png").exists());
		assertTrue("Spider Right does not exist.", Gdx.files.internal("Assets/Spider Right.png").exists());
	}
	// Tests application creation
	@Test
	public void applicationCreate() {
		LwjglApplicationConfiguration result = HelloWorld.createApplication();
		assertEquals(750, result.width);
		assertEquals(500, result.height);

		AL.destroy();
	}

	// Tests gravity physics
	@Test
	public void gravity() {
		Physics test = new Physics();
		float player = 7;
		player = test.gravity(player);
		assertTrue("Position is greater than 0.", player <= 0);
	}
	// Tests player controlled actions
	@Test
	public void playerInput() throws AWTException {
		HelloWorld test = new HelloWorld();
		test.testStatus = true;
		Robot r = new Robot();
		int result;

		// Down
		r.keyPress(40);
		test.getPlayerInput();
		result = test.currentAnim;
		assertEquals(3, result);
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
		assertEquals(1, result);
		r.keyRelease(39);
	}
	// Tests the enemy
	@Test
	public void enemyInput() {
		HelloWorld test = new HelloWorld();
		float result;
		final double DELTA = 1e-15;
		test.enemyX = test.playerX + 2;
		result = test.getEnemyInput();
		// Enemy stays
		assertEquals(0, result, DELTA);
		test.enemyX = test.playerX + 3;
		result = test.getEnemyInput();
		// Enemy moves
		assertEquals(3, result, DELTA);
	}

	// Tests player attacks
	@Test
	public void attacks() {
		HelloWorld test = new HelloWorld();
		boolean result = false;
		test.testStatus = true;
		test.getAttack();
		result = test.attacking;
		assertEquals(true, result);
	}
}