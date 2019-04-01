package main;
import com.badlogic.gdx.graphics.Texture;

public class Enemy {
    private int health;
    private int damage;
    private Texture t;
    
    private int x;
    private int y;
    
    public Enemy( int h, int d, int x, int y, Texture t) {
        health = h;
        damage = d;
        this.x = x;
        this.y = y;
        this.t= t;
    }
    
    public void setHealth( int h ) {
        health = h;
    }
    
    public boolean dead() {
        if (health < 0) {
            return true;
        }
        return false;
    }
    
    public void setPosition( int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int[] getPosition() {
        int[] p = {x, y};
        
        return p;
    }
    
    public int getDamage() {
        return damage;
    }
    
    public void setDamage( int d) {
        damage = d;
    }
}
