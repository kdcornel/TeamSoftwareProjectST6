
public class Player {
    private int health;
    private int damage;
    private float x;
    private float y;
    private int immunityFrames = 15;
    
    public Player ( int health, int damage, float x, float y ) {
        this.health = health;
        this.damage = damage;
        this.x = x;
        this.y = y;
    }
    
    public Player ( int health, int damage, float x, float y, int i ) {
        this.health = health;
        this.damage = damage;
        this.x = x;
        this.y = y;
        this.immunityFrames = i;
    }
    
    public Player () {
        health = 5;
        damage = 1;
        x = 0;
        y = 0;
    }
    
    public void setHealth( int health, boolean attacked ) {
        this.health = health;
        if (attacked) {
            tookDamage = true;
        }
    }
    
    public int getHealth() {
        return health;
    }
    
    public void setDamage( int damage ) {
        this.damage = damage;
    }
    
    public int getDamage() {
        return damage;
    }
    
    public void setXY ( float x, float y ) {
        this.x = x;
        this.y = y;
    }
    
    public float getX () {
        return x;
    }
    
    public float getY () {
        return y;
    }
    
    public float[] getPosition () {
        float [] pos = {x, y};
        return pos;
    }
    
    private boolean tookDamage = false;
    private int immunity = 0;
    public boolean damageImmunity() {
        if ( tookDamage == true ) {
            immunity = immunityFrames;
            tookDamage = false;
        }
        if ( immunity > 0 ) {
            immunity--;
            return true;
        }
        return false;
    }
    
    
}
