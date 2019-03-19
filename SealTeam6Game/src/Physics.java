
public class Physics {
	
	
	public int gravity(float goombaY){
    	int g = 4;
    	int vertSpeed = 0;
    	
    	vertSpeed += g;
    	goombaY -= vertSpeed * g;
    	
    	return (int) (goombaY);
    }
}
