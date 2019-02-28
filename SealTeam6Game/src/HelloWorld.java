
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HelloWorld extends ApplicationAdapter {

    public static void main(String[] args) {
        new LwjglApplication(new HelloWorld()); 
    }

    private SpriteBatch batch;
    private BitmapFont font;

    @Override
    public void create() {        
        batch = new SpriteBatch();    
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render() {        
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
            Random r = new Random();
            Random rand = new Random();
            int xPos = r.nextInt(400)+100;
            int yPos = r.nextInt(400)+100;
            font.draw(batch, "Seal Team Six", xPos, yPos);
            
            int c = rand.nextInt(5);
            if ( c == 0 ) {
                font.setColor(Color.RED);
            } else if ( c== 1 ) {
                font.setColor(Color.GREEN);
            } else if ( c== 2 ) {
                font.setColor(Color.BLUE);
            } else if ( c== 3 ) {
                font.setColor(Color.MAGENTA);
            } else {
                font.setColor(Color.MAROON);
            }
            
            xPos = r.nextInt(400)+100;
            yPos = r.nextInt(400)+100;
            font.draw(batch, "(Extra Text)", xPos, yPos);
            
            
            c = rand.nextInt(5);
            if ( c == 0 ) {
                font.setColor(Color.RED);
            } else if ( c== 1 ) {
                font.setColor(Color.GREEN);
            } else if ( c== 2 ) {
                font.setColor(Color.BLUE);
            } else if ( c== 3 ) {
                font.setColor(Color.MAGENTA);
            } else {
                font.setColor(Color.MAROON);
            }
            
            //xPos++; yPos++;
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
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