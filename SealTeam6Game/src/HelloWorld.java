//package main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Music;

public class HelloWorld extends ApplicationAdapter {

    
    public static int worldWidth = 750;
    public static int worldHeight = 500;    


	public static void main(String[] args) {

		createApplication();
	}

	private Music menuMusic;
	private SpriteBatch batch1;
	private BitmapFont font;
	private Animation manRight;
	private Animation manLeft;
	private Animation manDown;
	private Animation enemRight;
	private Animation manStand;
	private Animation manUpR;
	private Animation manUpL;
	private Animation eRight;
	private Animation eLeft;
	private Animation blackout;
	private float elapsed_time = 0f;
	private static float FRAME_DURATION = 0.5f;

	private OrthographicCamera camera;
	public boolean testStatus = false;

	// Creates application
	public static LwjglApplicationConfiguration createApplication() {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.width = worldWidth;
		cfg.height = worldHeight;

		LwjglApplication app = new LwjglApplication(new HelloWorld(), cfg);
		return cfg;
	}

	@Override
	public void create() {
		batch1 = new SpriteBatch();

		font = new BitmapFont();

		Texture man1 = new Texture(Gdx.files.internal("Assets/Man Walking Right.png"));
		Texture man2 = new Texture(Gdx.files.internal("Assets/Man Walking Left.png"));
		Texture man3 = new Texture(Gdx.files.internal("Assets/Man Crouching.png"));
		Texture enem1 = new Texture(Gdx.files.internal("Assets/Hellhound Right.png"));
		Texture enem2 = new Texture(Gdx.files.internal("Assets/Hellhound Left.png"));

		manRight = new Animation(new TextureRegion(man1), 4, 8);
		manLeft = new Animation(new TextureRegion(man2), 4, 8);
		manDown = new Animation(new TextureRegion(man3), 6, 8);
		enemRight = new Animation(new TextureRegion(enem1), 4, 40);

		Texture man4 = new Texture(Gdx.files.internal("Assets/Man Standing.png"));
		Texture man5 = new Texture(Gdx.files.internal("Assets/Man Jumping.png"));
		Texture man6 = new Texture(Gdx.files.internal("Assets/Man Jumping Left.png"));
		Texture e1 = new Texture(Gdx.files.internal("Assets/Hellhound Right.png"));
		Texture e2 = new Texture(Gdx.files.internal("Assets/Hellhound Left.png"));
		manRight = new Animation(new TextureRegion(man1), 4, 60);
		manLeft = new Animation(new TextureRegion(man2), 4, 60);
		manDown = new Animation(new TextureRegion(man3), 6, 60);
		manStand = new Animation(new TextureRegion(man4), 2, 60);
		manUpR = new Animation(new TextureRegion(man5), 2, 8);
		manUpL = new Animation(new TextureRegion(man6), 2, 8);
		eRight = new Animation(new TextureRegion(e1), 4, 50);
		eLeft = new Animation(new TextureRegion(e2), 4, 50);

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void dispose() {
		batch1.dispose();
		font.dispose();
	}

	// Speed for key press

	float playerSpeed = 300.0f;
	public float playerX;
	float playerY;
	public int currentAnim = 1;
	Physics py = new Physics();
	int justJumped = 0;
	boolean jumped = false;
	int jumping = 0;
	boolean down = false;
	int prevAnim = 1;
	int runAnim = 1;
	int previous;

	
	Player player = new Player();
	Enemy enemy = new Enemy(10, 1, worldWidth - 100, 0);

	public float enemyX = worldWidth - 100;
	float enemyY = 0;
	float enemySpeed = 150f;

//	public float getEnemyInput() {
//		// Getting distance from player X direction
//		float absDist = enemyX - playerX;
//		float actDist = enemyX - playerX;
//		if (absDist < 0) {
//			absDist = absDist * -1;
//		}
//
//		if (absDist < 3) {
//			return 0;
//		}
//
//		if (absDist < 175) {
//			if (actDist < 0) {
//				enemyX += Gdx.graphics.getDeltaTime() * enemySpeed;
//			} else {
//				enemyX -= Gdx.graphics.getDeltaTime() * enemySpeed;
//			}
//		}
//		return actDist;
//	}

//	float attackX = playerX;
//	float attackY = playerY + 5;
//	float start = playerX;
//	float attackSpeed = 600f;
//	public boolean attacking = false;
//
//	public void getAttack() {
//		if (attacking == false) {
//			attackX = playerX;
//			attackY = playerY + 17;
//			start = playerX;
//			attacking = true;
//			if (currentAnim == 1) {
//				attackSpeed = 600f;
//			} else if (currentAnim == 2) {
//				attackSpeed = -600f;
//			}
//		}
//		
//		if (testStatus == true)
//			return;
//		attackX += Gdx.graphics.getDeltaTime() * attackSpeed;
//	}

	int pY = -20;
	int pSpeed = 1;

	public void platformY() {
		if (pY == -20) {
			pSpeed = 1;
		} else if (pY == 100) {
			pSpeed = -1;
		}
		pY += pSpeed;
	}

	Texture bkgTexture;
	TextureRegion region;
	Texture fireball1;
	Texture fireball2;
	Texture platform;

	public void initialize() {
		Texture bkgTexture = new Texture(Gdx.files.internal("Assets/LowerResBkg.jpg"));
		region = new TextureRegion(bkgTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		dead = false;
		deathCount = 0;
		enemyDead = false;
		//blackout.reset();
		playerX = 0; 
		playerY = 10;
		enemyX = 650;
		enemyY = 0;
		
		// Sprite bkgSprite = new Sprite(bkgTexture);
		fireball1 = new Texture(Gdx.files.internal("Assets/fireball_0.png"));
		fireball2 = new Texture(Gdx.files.internal("Assets/fireball_1.png"));
		platform = new Texture(Gdx.files.internal("Assets/platform.png"));
	}

	boolean dead = false;
	
	int deathCount = 0;
	boolean init = false;

	@Override
	public void render() {
		if (init == false) {
			initialize();
			init = true;
		}
		
		if (dead){ 
			deathCount++;
			if (deathCount > 100){
				init = false;
				dead = false;
			}
		}
		currentAnim = previous;
		PlayerInput.getPlayerInput(player);

//		if (enemyDead == false) {
//			getEnemyInput();
//		}

		if (-10 <= (int) enemy.getX() - (int) player.getX() && 10 >= (int) enemy.getX() - (int) player.getX() ){
			dead = true;
		}
		
		float xyz = EnemyInput.getEnemyInput(enemy, player);
		// platformY();

		int[][] grid = Platform.tileGrid();
		
		batch1.begin();
		batch1.draw(region, 0, 0);
		Platform.placePlat(0, 0, grid);
        batch1.draw(platform, Platform.getGridX(grid) * 100, Platform.getGridY(grid) * 50, platform.getWidth() * .25f, platform.getHeight() * .5f);
        Platform.placePlat(0, 1, grid);
        batch1.draw(platform, Platform.getGridX(grid) * 100, Platform.getGridY(grid) * 50, platform.getWidth() * .25f, platform.getHeight() * .5f);
        Platform.placePlat(1, 0, grid);
        batch1.draw(platform, Platform.getGridX(grid) * 100, Platform.getGridY(grid) * 50, platform.getWidth() * .25f, platform.getHeight() * .5f);
        Platform.placePlat(2, 2, grid);
        batch1.draw(platform, Platform.getGridX(grid) * 100, Platform.getGridY(grid) * 50, platform.getWidth() * .25f, platform.getHeight() * .5f);
		
        if(!dead){
        batch1.draw(getTexture(player.getCurrentAnim()), (int) player.getX(), (int) player.getY() + 10);

		if (!getEvAcollision() || enemyDead == false) {
			batch1.draw(getEnemyTexture(xyz), (int) enemy.getX(), (int) enemy.getY());
		}

		

		if (Attack.attacking == true) {
			if (Attack.dir > 0) {
				batch1.draw(fireball1, (int) Attack.curx, (int) Attack.starty + 10);
			} else {
				batch1.draw(fireball2, (int) Attack.curx, (int) Attack.starty + 10);
			}
		}
		} else{
        	if (blackout.count() < 18){
        		blackout.update(.5f);
        	}
        	batch1.draw(blackout.getFrame(), 0, 0);
        }
		batch1.end();

		camera.update();

	}

	boolean enemyDead = false;

	private boolean getEvAcollision() {
		if (enemyX - 50 <= Attack.curx && enemyX + 50 >= Attack.curx) {
			if (enemyY - 50 <= Attack.starty + 17 && enemyY + 40 >= Attack.starty + 17) {
				enemyDead = true;
			}
		}
		return enemyDead;

	}

	public void drawSprite(TextureRegion region, TextureRegion textureRegion, int playerX2, int playerY2) {

	}

	public TextureRegion getTexture(int currentAnim) {
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

	public TextureRegion getEnemyTexture(float distance) {
		if (distance < 0) {
			eRight.update(0.5f);
			return eRight.getFrame();
		} else {
			eLeft.update(0.5f);
			return eLeft.getFrame();
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}