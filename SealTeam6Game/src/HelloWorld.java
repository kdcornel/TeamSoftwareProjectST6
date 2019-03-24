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
		
///////////////////////////////////////////////////////////////////////////////////////
//		KEVIN'S STUFF PLEASE DO NOT EDIT                                             //
///////////////////////////////////////////////////////////////////////////////////////
//	    LwjglApplicationConfiguration cfg = 
//                new LwjglApplicationConfiguration();
//        
//        cfg.title = Game.title;
//        cfg.width = Game.screenWidth * Game.scale;
//        cfg.height = Game.screenHeight * Game.scale;
//        
//        new LwjglApplication( new Game(), cfg);
	}
	
    private SpriteBatch batch1;
    private SpriteBatch batch2;
    private BitmapFont font;
    private Animation manRight;
    private Animation manLeft;
    private Animation manDown;
    private float elapsed_time = 0f;
    private static float FRAME_DURATION = 0.5f;
    //World world;
    //Body body;
    
    @Override
    public void create() {        
        batch1 = new SpriteBatch();
        batch2 = new SpriteBatch();
        font = new BitmapFont();
        
        font.setColor(Color.BLUE);
        
        Texture man1 = new Texture(Gdx.files.internal("Assets/Man Walking Right.png"));
        Texture man2 = new Texture(Gdx.files.internal("Assets/Man Walking Left.png"));
        Texture man3 = new Texture(Gdx.files.internal("Assets/Man Crouching.png"));
        Texture goomba = new Texture(Gdx.files.internal("Assets/Goomba.png"));
        manRight = new Animation(new TextureRegion(man1),4, 8);
        manLeft = new Animation(new TextureRegion(man2),4, 8);
        manDown = new Animation(new TextureRegion(man3),6, 8);
    }

    @Override
    public void dispose() {
        batch1.dispose();
        batch2.dispose();
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
    
//    public static Texture bkgTexture;
//    public static Sprite bkgSprite;
//    public static Texture texture;
//    public static Sprite sprite;
//    
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
            jumping = 5;
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
    
    
   
    @Override
    public void render() {  
    	
    	//keyPresses(currentAnim);
    	getPlayerInput();
    	
    	Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
        Texture texture = new Texture(Gdx.files.internal("Assets/Goomba.png"));
        Texture bkgTexture = new Texture(Gdx.files.internal("Assets/LowerResBkg.jpg"));
        TextureRegion region = new TextureRegion(bkgTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Sprite sprite = new Sprite(texture);

        //Sprite bkgSprite = new Sprite(bkgTexture);
        SpriteBatch sb = new SpriteBatch();
        
        batch1.begin();  
        batch1.draw(region, 0,0);
        batch1.draw(getTexture(currentAnim), (int)playerX, (int)playerY+12);
        batch1.end();
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