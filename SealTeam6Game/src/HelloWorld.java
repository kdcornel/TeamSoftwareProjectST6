
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Music;

public class HelloWorld extends ApplicationAdapter {

	public static void main(String[] args) {
		createApplication();
	}

	private int[] platArr = {0,0, 3,3, 2,2, 1,1, 5,6, 2,3, 4,2, 5,2};
	private int[] coinArr = {50,50, 100,100, 150,30};
	private int[][] grid = Physics.tileGrid();
	private String result;
	private String scoreboard = "Score: ";
	public Player player;
	
	public Enemy enemy1;
	public static int worldWidth = 1920;
	public static int worldHeight = 1000;
	private Music menuMusic;
	private SpriteBatch batchMain;
	private BitmapFont font;
	private Animation blackout;
	float elapsed_time = 0f;
	Physics py = new Physics();
	Levels lv = new Levels();
	int pY = -20;
	int pSpeed = 1;
	Texture bkgTexture;
	TextureRegion region;
	Texture fireball1;
	Texture fireball2;
	Texture platform;

	Texture coin;
	TextureRegion coinRegion;
	//TODO remove if not needed for coin animation

	Texture bkgTexture1;
	public static TextureRegion region1;
	public static TextureRegion region2;
	public static TextureRegion introFont;
	public static TextureRegion tutorialArrows;
	boolean init = false;
	int deathCount = 0;
	int killCount = 0;
	static LwjglApplication app;
	//private Batch batch1;

	//Boolean for JUnit tests
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
		batchMain = new SpriteBatch();
		font = new BitmapFont();
		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("Assets/Track2.wav"));
		menuMusic.setLooping(true);
		menuMusic.play();
		menuMusic.setVolume(0.05f);
		player = new Player();
		Texture death = new Texture(Gdx.files.internal("Assets/Blackout.png"));
		blackout = new Animation(new TextureRegion(death), 19, 35);
		enemy1 = new Enemy(1);
		player.setPlats(platArr, platArr.length);
	}

	@Override
	public void dispose() {
		batchMain.dispose();
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
		Texture bkgTexture1 = new Texture(Gdx.files.internal("Assets/LowerResBkg2.jpg"));
		Texture bkgTexture2 = new Texture(Gdx.files.internal("Assets/snoop.jpg"));
		Texture font1 = new Texture(Gdx.files.internal("Assets/IntroFont.png"));
		Texture arrows = new Texture(Gdx.files.internal("Assets/arrows.png"));
		region1 = new TextureRegion(bkgTexture1, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		region2 = new TextureRegion(bkgTexture2, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		introFont = new TextureRegion(font1, 0, 0, 1459, 91);
		tutorialArrows = new TextureRegion(arrows, 0, 0, 696, 564);
		
		// Sprite bkgSprite = new Sprite(bkgTexture);
		fireball1 = new Texture(Gdx.files.internal("Assets/fireball_0.png"));
		fireball2 = new Texture(Gdx.files.internal("Assets/fireball_1.png"));
		platform = new Texture(Gdx.files.internal("Assets/platform.png"));
		coin = new Texture(Gdx.files.internal("Assets/Coin.png"));
		coinRegion = new TextureRegion(coin, 0, 0);
		//TODO get coin animation working
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
		player.getPlayerInput(grid, py, testStatus, elapsed_time);
		
		player.getPlayerInput(grid, py, testStatus, elapsed_time);
		player.life(enemy1.getEvPcollision(player.x(), player.y(), player.isDead()));
		
		enemy1.getEvAcollision(Attack.curx, Attack.starty);
		if (!enemy1.pulse()){
			killCount++;
			if (killCount > 100){
				enemy1.reset();
				killCount = 0;
			}
		}
		float xyz = enemy1.getEnemyInput(player.x());
		
		batchMain.begin();
		
		// Changes level on player location
		Player.playerX = lv.changeScene(batchMain, Player.playerX, pY, platform);

		for (int i = 0; i < platArr.length; i+=2){
			batchMain.draw(platform,  platArr[i]* platform.getWidth() * .25f, platArr[i+1] * platform.getHeight() * .5f, platform.getWidth() * .25f, platform.getHeight() * .5f);
		}
		

		for (int i = 0; i < coinArr.length; i+=2){
			batchMain.draw(coin, coinArr[i], coinArr[i+1]);
		}
		//Trying to draw coin animation here

		if (!player.isDead()) {
			batchMain.draw(player.getTexture(), (int) player.x(), (int) player.y());
		}

		if (enemy1.pulse()) {
			batchMain.draw(enemy1.animate(xyz), (int) enemy1.getX(), (int) enemy1.getY());
		}
	    if (Attack.attacking()) {
	        if (Attack.dir == 0) {
	            batchMain.draw(fireball2, (int) Attack.curx, (int) Attack.starty);
	        } else {
	            batchMain.draw(fireball1, (int) Attack.curx, (int) Attack.starty);
	        }
	    } else {
	        if (blackout.count() < 18){
	            blackout.update(0.5f);
	        }
	        batchMain.draw(blackout.getFrame(), 0, 0);
	        result = scoreboard.concat(Integer.toString(enemy1.pnts()));
	        font.draw(batchMain, result, 350, 250);
	    }
		batchMain.end();
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