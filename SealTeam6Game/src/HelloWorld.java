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
    private Animation manStand;
    private Animation manUpR;
    private Animation manUpL;
    private Animation eRight;
    private Animation eLeft;
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
        Texture man4 = new Texture(Gdx.files.internal("Assets/Man Standing.png"));
        Texture man5 = new Texture(Gdx.files.internal("Assets/Man Jumping.png"));
        Texture man6 = new Texture(Gdx.files.internal("Assets/Man Jumping Left.png"));
        Texture e1 = new Texture(Gdx.files.internal("Assets/Hellhound Right.png"));
        Texture e2 = new Texture(Gdx.files.internal("Assets/Hellhound Left.png"));
        manRight = new Animation(new TextureRegion(man1),4, 60);
        manLeft = new Animation(new TextureRegion(man2),4, 60);
        manDown = new Animation(new TextureRegion(man3),6, 60);
        manStand = new Animation(new TextureRegion(man4),2, 60);
        manUpR = new Animation(new TextureRegion(man5),2, 8);
        manUpL = new Animation(new TextureRegion(man6),2, 8);
        eRight = new Animation(new TextureRegion(e1),4, 50);
        eLeft = new Animation(new TextureRegion(e2),4, 50);
    }

    @Override
    public void dispose() {
        batch1.dispose();
        font.dispose();
    }
    
    public void playerBox(int x, int y){
    	int playerX = x;
    	int playerY = y;
    	int playerBase = x + 20;
    	int playerHeight = y + 40;
    	int playerCorner = playerBase + playerHeight;
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
    int runAnim = 1;
    int previous;
    public void getPlayerInput() {
    	float playerYadd = 0;
    	
    	
      //Action Listeners for dpad key presses
        if ( jumping > 0 ) {
        	if(runAnim == 1) {
    			currentAnim = 5;
    			previous = 5;
    		}
    		if(runAnim == 2) {
    			currentAnim = 6;
    			previous = 6;
    		}
            playerY += (Gdx.graphics.getDeltaTime() * (playerSpeed * 3))*1.5;
            jumping--;
        }
        if ( justJumped > 0 ) {
            justJumped--;
            if(runAnim == 1) {
    			currentAnim = 5;
    			previous = 5;
    		}
    		if(runAnim == 2) {
    			currentAnim = 6;
    			previous = 6;
    		}
        }
        if(Gdx.input.isKeyPressed(Keys.DPAD_UP) && jumped == false && justJumped == 0){
            //goombaY += Gdx.graphics.getDeltaTime() * (goombaSpeed * 5);
        	if(runAnim == 1) {
    			currentAnim = 5;
    			previous = 5;
    		}
    		if(runAnim == 2) {
    			currentAnim = 6;
    			previous = 6;
    		}
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
            previous = 2;
            runAnim = 2;
        }
        if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
            playerX += Gdx.graphics.getDeltaTime() * playerSpeed;
            currentAnim = 1;
            previous = 6;
            runAnim = 1;
        }
        if(Gdx.input.isKeyPressed(Keys.SPACE) || attacking == true) {
            getAttack();
        }
        if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            dispose();
        }
        if(!Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) && !Gdx.input.isKeyPressed(Keys.DPAD_LEFT) && !Gdx.input.isKeyPressed(Keys.DPAD_UP) && !Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
        	currentAnim = 7;
        	previous = 7;
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
        
        int[][] grid = Platform.tileGrid();
        Platform.placePlat(1, 0, grid);

        
        /**
         * Broken platform implementation, will work on it until it works
         */
        
        //playerSpeed = py.platCollisionY(grid, Platform.getGridX(grid) * 100, Platform.getGridY(grid) * 50, playerX, playerY, playerSpeed);
        //playerSpeed = py.platCollisionX(grid, Platform.getGridX(grid) * 100, Platform.getGridY(grid) * 50, playerX, playerY, playerSpeed);
       
        //playerSpeed = py.platCollisionY(grid, Platform.getGridX(grid) * 100, Platform.getGridY(grid) * 50, playerX, playerY, playerY);
        //playerX = py.platCollisionX(grid, Platform.getGridX(grid) * 100, Platform.getGridY(grid) * 50, playerX, playerY, playerX);
        //playerY += playerYadd;
        playerY = py.gravity(playerY);
        
        elapsed_time += Gdx.graphics.getDeltaTime();
    }
    
    float enemyX = worldWidth-100;
    float enemyY = 0;
    float enemySpeed = 150f;
    
    public float getEnemyInput() {
        //Getting distance from player X direction
        float absDist = enemyX - playerX;
        float actDist = enemyX - playerX;
        if ( absDist < 0 ) {
            absDist = absDist*-1;
        }
        
        if ( absDist < 3){
        	return 0;
        }
        
        
        if ( absDist < 175) {
            if ( actDist < 0) {
                enemyX += Gdx.graphics.getDeltaTime() * enemySpeed;
            } else {
                enemyX -= Gdx.graphics.getDeltaTime() * enemySpeed;
            }
        }
        return actDist;
        
    }
    
    float attackX = playerX;
    float attackY = playerY;
    float start = playerX;
    float attackSpeed = 400f;
    boolean attacking = false;
    
    public void getAttack() {
        if ( attacking == false ) {
            attackX = playerX;
            attackY = playerY;
            start = playerX;
            attacking = true;
            if ( currentAnim == 1) {
                attackSpeed = 400f;
            } else if ( currentAnim == 2 ) {
                attackSpeed = -400f;
            }
        }
        if ( attackSpeed == 400f ) {
            attackX += Gdx.graphics.getDeltaTime() * attackSpeed;
        } else {
            attackX += Gdx.graphics.getDeltaTime() * attackSpeed;
        }
        
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
    
    
    //    Gdx.gl.glClearColor(1, 1, 1, 1);
    //    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
   
    Texture bkgTexture;
    TextureRegion region;
    Texture goomba;
    Texture platform;
    
    public void initialize() {
        Texture bkgTexture = new Texture(Gdx.files.internal("Assets/LowerResBkg.jpg"));
        region = new TextureRegion(bkgTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //Sprite bkgSprite = new Sprite(bkgTexture);
        goomba = new Texture(Gdx.files.internal("Assets/Goomba.png"));
        platform = new Texture(Gdx.files.internal("Assets/platform.png"));
    }
    
    boolean init = false;
    @Override
    public void render() {  
        if ( init == false ) {
            initialize();
            init = true;
        }
        currentAnim = previous;
        getPlayerInput();
        float xyz = getEnemyInput();
        //platformY();
        
        
        //Placing platform
        int[][] grid = Platform.tileGrid();
        
        
        int platCount = Platform.checkGrid(grid); 
        
        batch1.begin();
        batch1.draw(region, 0,0);
        //batch1.draw(platform, 150, pY);
        Platform.placePlat(1, 0, grid);
        batch1.draw(platform, Platform.getGridX(grid) * 100, Platform.getGridY(grid) * 50, platform.getWidth() * .25f, platform.getHeight() * .5f);
//        Platform.placePlat(0, 0, grid);
//        batch1.draw(platform, Platform.getGridX(grid) * 100, Platform.getGridY(grid) * 50);
        batch1.draw(getTexture(currentAnim), (int)playerX, (int)playerY+10);
        batch1.draw(getEnemyTexture(xyz), (int)enemyX, (int)enemyY);
        
        if( attacking == true) {
        	batch1.draw(goomba, (int)attackX, (int)attackY, goomba.getWidth() * .15f, goomba.getHeight() * .15f);
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
    
    public TextureRegion getEnemyTexture( float distance ) {
    	if (distance < 0) {
            eRight.update(0.5f);
            return eRight.getFrame();
        }
        else {
            eLeft.update(0.5f);
            return eLeft.getFrame();
        }
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