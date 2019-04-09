import com.badlogic.gdx.graphics.Texture;

public class Enemy {
    private int health;
    private int damage;
    
    private float x;
    private float y;
    
    public Enemy( int h, int d, float x, float y) {
        health = h;
        damage = d;
        this.x = x;
        this.y = y;
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
    
    public float[] getPosition() {
        float[] p = {x, y};
        
        return p;
    }
    
    public float getX(){
    	return x;
    }
    
    public float getY(){
    	return y;
    }
    
    public void setX(float newX){
    	x = newX;
    }
    
    public void setY(float newY){
    	y = newY;
    }
    
    public int getDamage() {
        return damage;
    }
    
    public void setDamage( int d) {
        damage = d;
    }
}
