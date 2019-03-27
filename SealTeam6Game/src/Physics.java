
public class Physics {
	
	
	public int gravity(float goombaY){
    	int g = 3;
    	int vertSpeed = 0;
    	
    	vertSpeed += g;
    	goombaY -= vertSpeed * g;
    	
    	return (int) (goombaY);
    }
}
