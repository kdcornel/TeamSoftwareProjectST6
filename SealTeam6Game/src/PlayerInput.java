import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class PlayerInput {

	
	static float playerSpeed = 300.0f;
	static public float playerX;
	static float playerY;
	static public int currentAnim = 1;
	static Physics py = new Physics();
	static int justJumped = 0;
	static boolean jumped = false;
	static int jumping = 0;
	static boolean down = false;
	static int prevAnim = 1;
	static int runAnim = 1;
	static int previous;
	static int dir = 1;
	
    public static void getPlayerInput(Player player) {
		// Action Listeners for dpad key presses
		if (jumping > 0) {
			if (runAnim == 1) {
				currentAnim = 5;
				previous = 5;
			}
			if (runAnim == 2) {
				currentAnim = 6;
				previous = 6;
			}
			player.setY((float) (player.getY() + (Gdx.graphics.getDeltaTime() * (playerSpeed * 3)) * 1.5));
			jumping--;
		}
		if (justJumped > 0) {
			justJumped--;
			if (runAnim == 1) {
				currentAnim = 5;
				previous = 5;
			}
			if (runAnim == 2) {
				currentAnim = 6;
				previous = 6;
			}
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_UP) && jumped == false && justJumped == 0) {
			// goombaY += Gdx.graphics.getDeltaTime() * (goombaSpeed * 5);
			if (runAnim == 1) {
				currentAnim = 5;
				previous = 5;
			}
			if (runAnim == 2) {
				currentAnim = 6;
				previous = 6;
			}
			jumped = true;
			jumping = 10;
			justJumped = 15;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
			// goombaY -= Gdx.graphics.getDeltaTime() * goombaSpeed;
			if (currentAnim != 3) {
				prevAnim = currentAnim;
			}
			currentAnim = 3;
			down = true;
		}
		if (!Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
			if (down == true) {
				currentAnim = prevAnim;
				down = false;
			}
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {
			player.setX(player.getX() - Gdx.graphics.getDeltaTime() * playerSpeed);
			currentAnim = 2;
			previous = 2;
			runAnim = 2;
			dir = 0;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
			player.setX(player.getX() + Gdx.graphics.getDeltaTime() * playerSpeed);
			currentAnim = 1;
			previous = 6;
			runAnim = 1;
			dir = 1;
		}
		if (Gdx.input.isKeyPressed(Keys.SPACE) || Attack.attacking == true) {
			if (Attack.attacking == false) {
				Attack.start(player.getX(), player.getY(), dir);
			}
			Attack.attacking();
			System.out.println(Attack.startx + ", " + Attack.starty);
			System.out.println(Attack.curx + ", " + Attack.starty);
		}
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		if (!Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) && !Gdx.input.isKeyPressed(Keys.DPAD_LEFT)
				&& !Gdx.input.isKeyPressed(Keys.DPAD_UP) && !Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
			currentAnim = 7;
			previous = 7;
		}
		
//		if (testStatus == true)
//			return;

		if (player.getY() >= HelloWorld.worldHeight || player.getY() <= 0) {
			player.setY(0);
			if (jumped == true) {
				if (jumping > 0) {
					jumping--;
				} else {
					jumped = false;
				}
			}
		}

		if (player.getX() <= 0) {
			player.setX(0);
		}
		if (player.getX() >= HelloWorld.worldWidth - 70) {
			player.setX(player.getX() - Gdx.graphics.getDeltaTime() * playerSpeed);
		}

		player.setY(py.gravity(player.getY()));
    }
}

		//elapsed_time += Gdx.graphics.getDeltaTime();

		//camera.position.x = playerX
    
		
//		if (testStatus == true)
//			return;
