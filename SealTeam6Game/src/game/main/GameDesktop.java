package game.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class GameDesktop {
    public static void main (String[] args) {
        LwjglApplicationConfiguration cfg = 
                new LwjglApplicationConfiguration();
        
        cfg.title = Game.title;
        cfg.width = Game.screenWidth * Game.scale;
        cfg.height = Game.screenHeight * Game.scale;
        
        new LwjglApplication( new Game(), cfg);
    }
}
