
public class Attack {

	public static boolean attacking = false;
	static float startx;
	static float starty;
	static float curx;
	static private float speed = 15f;
	static int dir;// 0 = left, 1 = right
	
	public static boolean attacking(){
		if (attacking == false){
			return attacking;
		}
		if (dir == 0) {
			curx -= speed;
		} 
		else {
			curx += speed;
		}
		
		float absDist = curx - startx;
		
		if (absDist < 0){
			absDist = absDist * -1;
		}
		
		if (absDist > 500){
			attacking = false;
		}
		
		return attacking;
	}
	
	public static void start(float x, float y, int d){
		attacking = true;
		
		startx = x;
		starty = y;
		curx = startx;
		dir = d;
	}
}
