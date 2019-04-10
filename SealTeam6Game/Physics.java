
public class Physics {
	
	
	public int gravity(float goombaY){
    	int g = 3;
    	int vertSpeed = 0;
    	
    	vertSpeed += g;
    	goombaY -= vertSpeed * g;
    	
    	return (int) (goombaY);
    }
	
	public float platCollisionX(int[][] grid, int x, int y, float playerX, float playerY, float playerVel){
		int[] startPos = Platform.tileDim(grid, x, y); // Start of rectangle
		int rectTop = x + 50;
		int rectSide = y + 100;
		
		//Left side of plat
//		if (playerX > x && playerY > y && playerY <= rectTop && playerX < rectSide){
//			playerVel = 0;
//		}
//		
//		//right side
//		if (playerX > x && playerY > y && playerY <= rectTop && playerX < rectSide){
//			playerVel = 0;
//		}
		
		return playerVel;
	}
	
	
	public void playerBox(int x, int y){
    	int playerX = x;
    	int playerY = y;
    	int playerBase = x + 20;
    	int playerHeight = y + 40;
    	int playerCorner = playerBase + playerHeight;
    }
	
	
	public float platCollisionY(int[][] grid, int x, int y, float playerX, float playerY, float playerPos){
		int[] startPos = Platform.tileDim(grid, x, y); // Start of rectangle
		int rectTop = x + 50;
		int rectSide = y + 100;
		
		
		//bottom
		if (playerX > x && playerX < rectSide && playerY <= y){
			playerPos = -10;
		}
		
		//top
		if (playerX > x && playerX < rectSide && playerY == rectTop){
			playerPos = 4;
		}
		
		return playerPos;
	}
	
	
	
	
}
