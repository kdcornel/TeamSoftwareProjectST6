import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player {
	private Animation manRight;
	private Animation manLeft;
	private Animation manDown;
	private Animation manStand;
	private Animation manUpR;
	private Animation manUpL;
	private float playerSpeed = 300.0f;
	private float attackSpeed = 600.0f;
	private float playerX = 0;
	private float playerY = 10;
	private Texture man1;
	private Texture man2;
	private Texture man3;
	private Texture man4;
	private Texture man5;
	private Texture man6;
	private int worldHeight = 500;
	private int worldWidth = 750;
	private int currentAnim = 1;
	private int prevAnim = 1;
	private int runAnim = 1;
	private int previous;
	private float attackX = playerX;
	private float attackY = playerY + 5;
	private float start = playerX;
	private boolean dead = false;
	private int jumping = 0;
	private int justJumped = 0;
	private boolean jumped = false;	
	private boolean down = false;
	private boolean attacking = false;
	
	public void kill(){
	dead = true;
}

	public void life(boolean j){
	dead = j;
}

	public Boolean isDead(){
	return dead;
}

	public float attackSpeed(){
	return attackSpeed;
}

	public void save(){
	dead = false;
	playerX = 0;
	playerY = 10;
}

	public boolean isAttacking(){
	return attacking;
}

	public void NotAttacking(){
	attacking = false;
}

	public float x(){
	return playerX;
}

	public float y(){
	return playerY;
}

	public float start(){
	return start;
}

	public float ax(){
	return attackX;
}

	public float ay(){
	return attackY;
}
	
	public void cTp(){
		currentAnim = previous;
	}
	
	public Player(){
		man1 = new Texture(Gdx.files.internal("Assets/Man Walking Right.png"));
		man2 = new Texture(Gdx.files.internal("Assets/Man Walking Left.png"));
		man3 = new Texture(Gdx.files.internal("Assets/Man Crouching.png"));
		man4 = new Texture(Gdx.files.internal("Assets/Man Standing.png"));
		man5 = new Texture(Gdx.files.internal("Assets/Man Jumping.png"));
		man6 = new Texture(Gdx.files.internal("Assets/Man Jumping Left.png"));
		manRight = new Animation(new TextureRegion(man1), 4, 35);
		manLeft = new Animation(new TextureRegion(man2), 4, 35);
		manDown = new Animation(new TextureRegion(man3), 6, 35);
		manStand = new Animation(new TextureRegion(man4), 2, 35);
		manUpR = new Animation(new TextureRegion(man5), 2, 8);
		manUpL = new Animation(new TextureRegion(man6), 2, 8);
		attackX = playerX;
		attackY = playerY + 5;
	}
	
	public void getPlayerInput(Physics py, Boolean testStatus, float elapsed_time, OrthographicCamera camera) {
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
			playerY += (Gdx.graphics.getDeltaTime() * (playerSpeed * 3)) * 1.5;
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
			playerX -= Gdx.graphics.getDeltaTime() * playerSpeed;
			currentAnim = 2;
			previous = 2;
			runAnim = 2;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
			playerX += Gdx.graphics.getDeltaTime() * playerSpeed;
			currentAnim = 1;
			previous = 6;
			runAnim = 1;
		}
		if (Gdx.input.isKeyPressed(Keys.SPACE) || attacking == true) {
			getAttack(testStatus);
		}
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		if (!Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) && !Gdx.input.isKeyPressed(Keys.DPAD_LEFT)
				&& !Gdx.input.isKeyPressed(Keys.DPAD_UP) && !Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
			currentAnim = 7;
			previous = 7;
		}
		
		if (testStatus == true)
			return;

		if (playerY >= worldHeight || playerY <= 0) {
			playerY -= playerY;
			if (jumped == true) {
				if (jumping > 0) {
					jumping--;
				} else {
					jumped = false;
				}
			}
		}

		if (playerX <= 0) {
			playerX -= playerX;
		}
		if (playerX >= worldWidth - 70) {
			playerX -= Gdx.graphics.getDeltaTime() * playerSpeed;
		}

		playerY = py.gravity(playerY);

		elapsed_time += Gdx.graphics.getDeltaTime();

		camera.position.x = playerX;
	}
	
	public void getAttack(Boolean testStatus) {
		if (attacking == false) {
			attackX = playerX;
			attackY = playerY + 17;
			start = playerX;
			attacking = true;
			if (currentAnim == 1) {
				attackSpeed = 600f;
			} else if (currentAnim == 2) {
				attackSpeed = -600f;
			}
		}
		
		if (testStatus == true)
			return;
		attackX += Gdx.graphics.getDeltaTime() * attackSpeed;
	}
	
	public TextureRegion getTexture() {
		if (currentAnim == 1) {
			manRight.update(0.5f);
			return manRight.getFrame();
		}
		if (currentAnim == 2) {
			manLeft.update(0.5f);
			return manLeft.getFrame();
		}
		if (currentAnim == 3) {
			manDown.update(0.5f);
			return manDown.getFrame();
		}
		if (currentAnim == 5) {
			manStand.update(0.1f);
			return manUpR.getFrame();
		}
		if (currentAnim == 6) {
			manStand.update(0.1f);
			return manUpL.getFrame();
		}
		if (currentAnim == 7) {
			manStand.update(0.1f);
			return manStand.getFrame();
		}
		return manStand.getFrame();
	}
}
