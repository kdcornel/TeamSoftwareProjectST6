

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HelloWorld extends ApplicationAdapter {
    
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.width = 2000;
		cfg.height = 1800;
		LwjglApplication app = new LwjglApplication(new HelloWorld(), cfg); 
	}
	
    private SpriteBatch batch;
    private BitmapFont font;
    //World world;
    //Body body;
    
    @Override
    public void create() {        
        batch = new SpriteBatch();    
        font = new BitmapFont();
        
        font.setColor(Color.BLUE);
        
        Texture bkgTexture = new Texture(Gdx.files.internal("Assets/background.png"));
        
        batch.begin();
        	batch.draw(bkgTexture, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
    
    //Speed for key press

    float goombaSpeed = 400.0f;

    float goombaX;
    float goombaY;
    Physics py = new Physics();
    
    
    int jumpCtr = 0;
    
//    public static Texture bkgTexture;
//    public static Sprite bkgSprite;
//    public static Texture texture;
//    public static Sprite sprite;
//    
    
    
    @Override
    public void render() {  
    	
    	
    	//Action Listeners for dpad key presses
    	if(Gdx.input.isKeyPressed(Keys.DPAD_UP) & jumpCtr < 12){
    		goombaY += Gdx.graphics.getDeltaTime() * (goombaSpeed * 5);
    		jumpCtr++;
    	}
    	if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN))
    		goombaY -= Gdx.graphics.getDeltaTime() * goombaSpeed;
    	if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT))
    		goombaX -= Gdx.graphics.getDeltaTime() * goombaSpeed;
    	if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT))
    		goombaX += Gdx.graphics.getDeltaTime() * goombaSpeed;
    	
    	
    	if(goombaY >= 900 || goombaY <= 0){
    		goombaY -= goombaY;
    		jumpCtr = 0;
    	}
    	 
    	if(goombaX >= 1800 || goombaX <= 0){
    		goombaX -= goombaX;
    	}
    	
    	goombaY = py.gravity(goombaY);
    	
    	
    	Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
        Texture texture = new Texture(Gdx.files.internal("Assets/Goomba.png"));
        //Texture bkgTexture = new Texture(Gdx.files.internal("Assets/background.png"));
        Sprite sprite = new Sprite(texture);
        //Sprite bkgSprite = new Sprite(bkgTexture);
      
        batch.begin();  
        batch.draw(texture, (int)goombaX, (int)goombaY);
        //sprite.draw(batch);
        batch.end();
        
        
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