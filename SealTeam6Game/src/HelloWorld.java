

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
		cfg.width = 1800;
		cfg.height = 900;
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
        
        //world = new World(new Vector2(0, -98f), true);
        //World doesnt currently work, ill look into it
        font.setColor(Color.BLUE);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
    
    //Speed for key press
    float goombaSpeed = 150.0f;
    float goombaX;
    float goombaY;
    
    @Override
    public void render() {  
    	
    	//Action Listeners for dpad key presses
    	if(Gdx.input.isKeyPressed(Keys.DPAD_UP))
    		goombaY += Gdx.graphics.getDeltaTime() * goombaSpeed;       
    	if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN))
    		goombaY -= Gdx.graphics.getDeltaTime() * goombaSpeed;
    	if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT))
    		goombaX -= Gdx.graphics.getDeltaTime() * goombaSpeed;
    	if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT))
    		goombaX += Gdx.graphics.getDeltaTime() * goombaSpeed;
    	
    	    	
    	Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Texture texture = new Texture(Gdx.files.internal("C:/Users/joshj/workspace/GdxTestOrSomethingYouKnowThisIsReallyDumbBlahBlahBlahGottaMakeTheTitleLongerOrElseItsNotLikeProfessionalOrSomethingImDrunkDontJudgeMe/src/Goomba.png"));
        Sprite sprite = new Sprite(texture);
        
      
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