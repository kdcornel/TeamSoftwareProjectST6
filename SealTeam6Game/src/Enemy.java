
import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.audio.Music;

public class Enemy {
   private float x = 650;
   private float y = 0;
   public static int pnts = 0;
   private int marker;
   private float speed = 150f;
   private boolean alive = true;
   private Texture l;
   private int helpx;
   private Texture r;
   private Animation left;
   private Animation right;
   private Music manHit;
   private int plats;
   Texture platform = new Texture(Gdx.files.internal("Assets/platform.png"));
   
   public Enemy(int k){
	   
	   manHit = Gdx.audio.newMusic(Gdx.files.internal("Assets/HitPlayer.wav"));
	   manHit.setLooping(false);
	   
	   if (k == 1){
		   r = new Texture(Gdx.files.internal("Assets/Hellhound Right.png"));
		   l = new Texture(Gdx.files.internal("Assets/Hellhound Left.png"));
		   left = new Animation(new TextureRegion(l), 4, 35);
		   right = new Animation(new TextureRegion(r), 4, 35); 
		   marker = 1;
	   } else {
		   r = new Texture(Gdx.files.internal("Assets/Spider Right.png"));
		   l = new Texture(Gdx.files.internal("Assets/Spider Left.png"));
		   left = new Animation(new TextureRegion(l), 8, 35);
		   right = new Animation(new TextureRegion(r), 8, 35);
		   x = 900;
		   marker = 0;
	   }
   }
   
   public void revive(){
	   alive = true;
   }
   
   public void reset(){
       alive = true;
       x = ThreadLocalRandom.current().nextInt(0,750);
       y = ThreadLocalRandom.current().nextInt(0,1000);
   }
   
   public void kill(){
	   alive = false;
   }
   
   public boolean pulse(){
	   return alive;
   }
   
   public float getX(){
	   return x;
   }
   
   public float getY(){
	   return y;
   }
   
   public void setX(float z){
	   x = z;
   }
   
   public void setY(float z){
	   y = z;
   }
   
   public int pnts() {
       return pnts;
   }
   
   public void coin(){
	   r = new Texture(Gdx.files.internal("Assets/Coin.png"));
	   right = new Animation(new TextureRegion(r), 4, 40);
   }
   
   
   public void enemyHound(){
	   r = new Texture(Gdx.files.internal("Assets/Hellhound Right.png"));
	   l = new Texture(Gdx.files.internal("Assets/Hellhound Left.png"));
	   left = new Animation(new TextureRegion(l), 4, 40);
	   right = new Animation(new TextureRegion(r), 4, 40);
	   y = ThreadLocalRandom.current().nextInt(0,1000);
   }
   
   public void enemySpider(){
	   r = new Texture(Gdx.files.internal("Assets/Spider Right.png"));
	   l = new Texture(Gdx.files.internal("Assets/Spider Left.png"));
	   left = new Animation(new TextureRegion(l), 8, 40);
	   right = new Animation(new TextureRegion(r), 8, 40);
	   y = ThreadLocalRandom.current().nextInt(0,1000);
   }
   
   public void eSwitch(){
   if (marker == 1){
	   enemySpider();
	   marker = 0;
	   return;
   } else if (marker == 0){
	   enemyHound();
	   marker = 1;
	   return;
   }
   }
   
   public TextureRegion animate(float distance){
		if (distance < 0) {
			right.update(0.5f);
			return right.getFrame();
		} else {
			left.update(0.5f);
			return left.getFrame();
		}	
   }
   
	public void getEvAcollision(float attackX, float attackY) {
		if (x - 25 <= attackX && x + 25 >= attackX) {
			if (y - 50 <= attackY + 17 && y + 40 >= attackY + 17) {
			    if (alive) {
			        pnts++;
			    }
				alive = false;
			}
		}
	}
	
	public boolean getEvPcollision(float px, float py, boolean death){
		if (alive && !death){
		if (-20 <= (int)x-(int)px && 20 >= (int)x-(int)px){
			if (-30 <= (int)y-(int)py && 30 >= (int)y-(int)py){
				death = true;
				manHit.play();
				}
			}
		}
		return death;
	}
	
	public float getEnemyInput(float playerX, Physics py, int[] platArr) {
		float absDist = x - playerX;
		float actDist = x - playerX;
		int platCount = platArr.length;
		 
		
		if (absDist < 0) {
			absDist = absDist * -1;
		}

		if (absDist < 3) {
			return 0;
		}

		if (absDist < 175) {
			if (actDist < 0) {
				x += Gdx.graphics.getDeltaTime() * speed;
			} else {
				x -= Gdx.graphics.getDeltaTime() * speed;
			}
		}
		
		
		for(int i = 0; i < platCount; i+=2){
			if ((int)x >= platArr[i] * platform.getWidth() * .25f && (int)x <= platArr[i]*platform.getWidth() * .25f+platform.getWidth() * .25f){
				if ((int)y >= platArr[i+1]*platform.getHeight() * .5f + platform.getHeight() * .5f){
					plats = (int)(platArr[i+1]*platform.getHeight() * .5f+platform.getHeight() * .5f);
					helpx = (int) (platArr[i] * platform.getWidth() * .25f);
//					
				}
			}
		}
		
//		
		
		if (helpx < (int)x - 40 || helpx > (int)x+75){
			plats = 0;
		}
		y = py.gravity(y, plats);
		
		
		return actDist;
	}


}
