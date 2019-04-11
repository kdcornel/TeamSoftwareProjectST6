
//package main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Music;


public class HelloWorld extends ApplicationAdapter {
   
	public static void main(String[] args) {

		createApplication();
	}
	int[][] grid = Platform.tileGrid();
	public Player player;
	public Enemy enemy1;
	public static int worldWidth = 750;
	public static int worldHeight = 500;
	private Music menuMusic;
	private SpriteBatch batch1;
	private BitmapFont font;
	private Animation blackout;
	float elapsed_time = 0f;
	private OrthographicCamera camera;
	public boolean testStatus = false;
	Physics py = new Physics();
	int pY = -20;
	int pSpeed = 1;
	Texture bkgTexture;
	TextureRegion region;
	Texture fireball1;
	Texture fireball2;
	Texture platform;
	boolean init = false;
	int deathCount = 0;
	int killCount = 0;
	static LwjglApplication app;
	
	public static LwjglApplicationConfiguration createApplication() {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.width = worldWidth;
		cfg.height = worldHeight;
		app = new LwjglApplication(new HelloWorld(), cfg);
		return cfg;
	}

	@Override
	public void create() {
		batch1 = new SpriteBatch();
		font = new BitmapFont();
		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("Assets/Track2.wav"));
		menuMusic.setLooping(true);
		menuMusic.play();
		menuMusic.setVolume(0.05f);
		player = new Player();
		Texture death = new Texture(Gdx.files.internal("Assets/Blackout.png"));
		blackout = new Animation(new TextureRegion(death), 19, 35);
		enemy1 = new Enemy(1);
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void dispose() {
		batch1.dispose();
		font.dispose();
	}

	public void platformY() {
		if (pY == -20) {
			pSpeed = 1;
		} else if (pY == 100) {
			pSpeed = -1;
		}
		pY += pSpeed;
	}

	public void initialize() {
		Texture bkgTexture = new Texture(Gdx.files.internal("Assets/LowerResBkg.jpg"));
		region = new TextureRegion(bkgTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		fireball1 = new Texture(Gdx.files.internal("Assets/fireball_0.png"));
		fireball2 = new Texture(Gdx.files.internal("Assets/fireball_1.png"));
		platform = new Texture(Gdx.files.internal("Assets/platform.png"));
	}
	
	@Override
	public void render() {
		if (init == false) {
			initialize();
			init = true;
		}
		if (player.isDead()){
			deathCount++;
			if (deathCount > 100){
				deathCount = 0;
				player.save();
				enemy1.reset();
				enemy1.eSwitch();
				blackout.reset();
			}
		}
		player.cTp();
		player.getPlayerInput(grid, py, testStatus, elapsed_time, camera);
		player.life(enemy1.getEvPcollision(player.x(), player.y(), player.isDead()));
		enemy1.getEvAcollision(player.ax(), player.ay());
		if (!enemy1.pulse()){
			killCount++;
			if (killCount > 100){
				enemy1.reset();
				killCount = 0;
			}
		}
		float xyz = enemy1.getEnemyInput(player.x());
		// platformY();
		
		int[] platArr = {0,0, 3,3, 2,2, 1,1};
		
		
		
		
		batch1.begin();
		batch1.draw(region, 0, 0);
		
		for (int i = 0; i < platArr.length; i++){
			batch1.draw(platform,  platArr[i]* 75, platArr[i++] * 50, platform.getWidth() * .25f, platform.getHeight() * .5f);
		}
		

		if (!player.isDead()){
			batch1.draw(player.getTexture(), (int) player.x(), (int) player.y() + 10);
		if (enemy1.pulse()) {
			batch1.draw(enemy1.animate(xyz), (int) enemy1.getX(), (int) enemy1.getY());
		}

		if (player.isAttacking()) {
			if (player.attackSpeed() > 0) {
					batch1.draw(fireball1, (int) player.ax(), (int) player.ay());
				} else {
					batch1.draw(fireball2, (int) player.ax(), (int) player.ay());
				}
				float absDist = player.ax() - player.start();
				if (absDist < 0) {
					absDist = absDist * -1;
				}
				if (absDist > 500) {
					player.NotAttacking();
				}
			}
		} else {
			if (blackout.count() < 18){
			blackout.update(0.5f);
			}
			batch1.draw(blackout.getFrame(), 0, 0);
		}
		batch1.end();
		camera.update();
	}

	public void drawSprite(TextureRegion region, TextureRegion textureRegion, int playerX2, int playerY2) {

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