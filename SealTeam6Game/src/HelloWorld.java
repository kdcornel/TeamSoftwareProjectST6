import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HelloWorld extends ApplicationAdapter {
    
    public static int worldWidth = 750;
    public static int worldHeight = 500;
    
	public static void main(String[] args) {

		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.width = worldWidth;
		cfg.height = worldHeight;

		LwjglApplication app = new LwjglApplication(new HelloWorld(), cfg);
	}
    private SpriteBatch batch1;
    private BitmapFont font;
    private Animation manRight;
    private Animation manLeft;
    private Animation manDown;
    private Animation enemRight;
    private float elapsed_time = 0f;
    private static float FRAME_DURATION = 0.5f;
    //World world;
    //Body body;
    
    @Override
    public void create() {       
        batch1 = new SpriteBatch();
        
        font = new BitmapFont();
        
        font.setColor(Color.BLUE);
        
        Texture man1 = new Texture(Gdx.files.internal("Assets/Man Walking Right.png"));
        Texture man2 = new Texture(Gdx.files.internal("Assets/Man Walking Left.png"));
        Texture man3 = new Texture(Gdx.files.internal("Assets/Man Crouching.png"));
        Texture enem1 = new Texture(Gdx.files.internal("Assets/Hellhound Right.png"));
        Texture enem2 = new Texture(Gdx.files.internal("Assets/Hellhound Left.png"));
        
        manRight = new Animation(new TextureRegion(man1),4, 8);
        manLeft = new Animation(new TextureRegion(man2),4, 8);
        manDown = new Animation(new TextureRegion(man3),6, 8);
        enemRight = new Animation(new TextureRegion(enem1), 4, 40);
    }

    @Override
    public void dispose() {
        batch1.dispose();
        font.dispose();
    }
    
    //Speed for key press

    float playerSpeed = 300.0f;

    float playerX;
    float playerY;
    int currentAnim = 1;
    Physics py = new Physics();
    
    int justJumped = 0;
    boolean jumped = false;
    int jumping = 0;
    boolean down = false;
    int prevAnim = 1;
    
    public void getPlayerInput() {
        
      //Action Listeners for dpad key presses
        if ( jumping > 0 ) {
            playerY += (Gdx.graphics.getDeltaTime() * (playerSpeed * 3))*1.5;
            jumping--;
        }
        if ( justJumped > 0 ) {
            justJumped--;
        }
        if(Gdx.input.isKeyPressed(Keys.DPAD_UP) && jumped == false && justJumped == 0){
            //goombaY += Gdx.graphics.getDeltaTime() * (goombaSpeed * 5);
            jumped = true;
            jumping = 10;
            justJumped = 15;
        }
        if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
            //goombaY -= Gdx.graphics.getDeltaTime() * goombaSpeed;
            if ( currentAnim != 3 ) {
                prevAnim = currentAnim;
            }
            currentAnim = 3;
            down = true;
        }
        if(!Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
            //goombaY -= Gdx.graphics.getDeltaTime() * goombaSpeed;
            if ( down == true ) {
                currentAnim = prevAnim;
                down = false;
            }
        }
        if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {
            playerX -= Gdx.graphics.getDeltaTime() * playerSpeed;
            currentAnim = 2;
        }
        if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
            playerX += Gdx.graphics.getDeltaTime() * playerSpeed;
            currentAnim = 1;
        }
        if(Gdx.input.isKeyPressed(Keys.SPACE) || attacking == true) {
            getAttack();
        }
        if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            dispose();
        }
        
        if(playerY >= worldHeight || playerY <= 0){
            playerY -= playerY;
            if ( jumped == true ) {
                if ( jumping > 0 ) {
                    jumping--;
                } else {
                    jumped = false;
                }
            }
        }
         
        if( playerX <= 0){
            playerX -= playerX;
            //goombaX = worldWidth-70;
        }
        if( playerX >= worldWidth-70 ) {
            playerX -= Gdx.graphics.getDeltaTime() * playerSpeed;
            //goombaX += goombaX;
        }
        
        playerY = py.gravity(playerY);
        
        elapsed_time += Gdx.graphics.getDeltaTime();
    }
    
    float enemyX = worldWidth-100;
    float enemyY = 0;
    float enemySpeed = 150f;
    
    public void getEnemyInput() {
        //Getting distance from player X direction
        float absDist = enemyX - playerX;
        float actDist = enemyX - playerX;
        if ( absDist < 0 ) {
            absDist = absDist*-1;
        }
        
        if ( absDist < 175) {
            if ( actDist < 0) {
                enemyX += Gdx.graphics.getDeltaTime() * enemySpeed;
            } else {
                enemyX -= Gdx.graphics.getDeltaTime() * enemySpeed;
            }
        }
        
    }
    
    float attackX = playerX;
    float attackY = playerY+5;
    float start = playerX;
    float attackSpeed = 600f;
    boolean attacking = false;
    
    public void getAttack() {
        if ( attacking == false ) {
            attackX = playerX;
            attackY = playerY+17;
            start = playerX;
            attacking = true;
            if ( currentAnim == 1) {
                attackSpeed = 600f;
            } else if ( currentAnim == 2 ) {
                attackSpeed = -600f;
            }
        }
        attackX += Gdx.graphics.getDeltaTime() * attackSpeed;
    }

    int pY = -20;
    int pSpeed = 1;
    public void platformY() {
        if ( pY == -20 ) {
            pSpeed = 1;
        } else if ( pY == 100 ) {
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

        //Sprite bkgSprite = new Sprite(bkgTexture);
        fireball1 = new Texture(Gdx.files.internal("Assets/fireball_0.png"));
        fireball2 = new Texture(Gdx.files.internal("Assets/fireball_1.png"));
        platform = new Texture(Gdx.files.internal("Assets/platform.png"));
    }
    
    boolean init = false;
    @Override
    public void render() {  
        if ( init == false ) {
            initialize();
            init = true;
        }
        
        getPlayerInput();
        if (enemyDead == false) {
            getEnemyInput();
        }
        //platformY();
        
        batch1.begin();
        batch1.draw(region, 0,0);
        batch1.draw(platform, 150, pY);
        batch1.draw(getTexture(currentAnim), (int)playerX, (int)playerY+10);
        if ( !getEvAcollision() || enemyDead == false ) {
            batch1.draw(getTexture(currentAnim), (int)enemyX, (int)enemyY);
        }
        
        if( attacking == true) {
            if ( attackSpeed > 0 ) {
                batch1.draw(fireball1, (int)attackX, (int)attackY);
            } else {
                batch1.draw(fireball2, (int)attackX, (int)attackY);
            }
            float absDist = attackX - start;
            if ( absDist < 0) {
                absDist = absDist*-1;
            }
            if (absDist > 500) {
                attacking = false;
            }
        }
        batch1.end();
        
    }
    
    boolean enemyDead = false;
    private boolean getEvAcollision() {
        if ( enemyX-50 <= attackX && enemyX+50 >= attackX  ) {
            if ( enemyY-50 <= attackY+17 && enemyY+40 >= attackY+17 ) {
                enemyDead = true;
            }
        }
        return enemyDead;
    }

    public void drawSprite(TextureRegion region, TextureRegion textureRegion, int playerX2, int playerY2) {
        
    }
    
    public TextureRegion getTexture( int currentAnim ) {
        if (currentAnim == 1) {
            manRight.update(0.5f);
            return manRight.getFrame();
        }
        if (currentAnim == 2) {
            manLeft.update(0.5f);
            return manLeft.getFrame();
        }
        manDown.update(0.5f);
        return manDown.getFrame();
    }
    
    public int keyPresses(int currentAnim) {
    	//Action Listeners for dpad key presses
        if ( jumping > 0 ) {
            playerY += (Gdx.graphics.getDeltaTime() * (playerSpeed * 3))*1.25;
            jumping--;
        }
        if(Gdx.input.isKeyPressed(Keys.DPAD_UP) && jumped == false){
            //goombaY += Gdx.graphics.getDeltaTime() * (goombaSpeed * 5);
            jumped = true;
            jumping = 10;
        }
    	if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
    		//goombaY -= Gdx.graphics.getDeltaTime() * goombaSpeed;
    	    currentAnim = 3;
    	}
    	if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {
    	    playerX -= Gdx.graphics.getDeltaTime() * playerSpeed;
    	    currentAnim = 2;
    	}
    	if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
    	    playerX += Gdx.graphics.getDeltaTime() * playerSpeed;
    		currentAnim = 1;
    	}
		return currentAnim;
    }

    // Sets color of font
    public BitmapFont setFontColor(BitmapFont result) {
    	result.setColor(Color.BLUE);
		return result;
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