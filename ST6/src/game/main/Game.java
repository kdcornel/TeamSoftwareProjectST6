package game.main;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.handlers.GameStateManager;

public class Game implements ApplicationListener {
    
    public static final String title = "Game";
    public static final int screenWidth = 320;
    public static final int screenHeight = 240;
    public static final int scale = 2;
    
    public static final float STEP = 1 / 60f;
    private float accum;
    
    private SpriteBatch sb;
    private OrthographicCamera cam;
    private OrthographicCamera hudCam;
    
    private GameStateManager gsm;
    
    public void create() {
        sb = new SpriteBatch();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, screenWidth, screenHeight);
        hudCam = new OrthographicCamera();
        hudCam.setToOrtho(false, screenWidth, screenHeight);
        
        gsm = new GameStateManager(this);
    }
    
    public void render() {
        accum += Gdx.graphics.getDeltaTime();
        while(accum >= STEP) {
            accum -= STEP;
            gsm.update(STEP);
            gsm.render();
        }
    }
    
    public void dispose() {}
    
    public void resize( int w, int h) {}
    public void pause() {}
    public void resume() {}
    
    public SpriteBatch getSpriteBatch() {return sb;}
    public OrthographicCamera getCamera() {return cam;}
    public OrthographicCamera getHUDCamera() {return hudCam;}
}
