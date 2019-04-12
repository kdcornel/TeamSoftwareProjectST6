
public class Physics {
	
	
	public int gravity(float goombaY, int killMe){
    	int g = 3;
    	int vertSpeed = -1;
    	if ((int)goombaY > killMe){
    	vertSpeed += g;
    	goombaY -= vertSpeed * g;
    	}
    	
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
	
	
	public float platCollisionY(int[][] grid, int x, int y, float playerX, float playerY){
        int[] startPos = Platform.tileDim(grid, x, y); // Start of rectangle
        int rectTop = x + 50;
        int rectSide = y + 100;
        float playerPos = playerY;
        int yTopRegion = y + 10;
        int yBottomRegion = y - 45; // Gives a region so that the physics can be applied in time
        int rectTopReg = rectTop + 140;
        int rectBotReg = rectTop - 20;

        //bottom
        if (playerX > x && playerX < rectSide && playerY <= yTopRegion && playerY >= yBottomRegion){
            playerPos = playerY - 10;
        }

        //top
//        if (playerX > x && playerX < rectSide && playerY >= rectTopReg && playerY <= rectBotReg){
//            playerPos = playerY + 60;
//        }

        return playerPos;
    }
	
	
	
	
}
