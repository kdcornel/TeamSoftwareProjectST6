
public class Platform {

	
	
	//Needs platform group for easier drawing????
	
	
	
	public static void removePlat(int x, int y, int[][] grid){
	        grid[x][y] = 0;
	    }
	
	public static int[] tileDim(int[][] grid, int x, int y){
		int xPos = x * 100;
		int yPos = y * 50;
		int[] startPos = {xPos, yPos};
		
		return startPos;
	}
	
	public static int[][] tileGrid(){
		
		int Width = HelloWorld.worldWidth;
		int Height = HelloWorld.worldHeight;
		int tileW = 75;
		int tileH = 50;

		int[][] grid = new int[Width / tileW][Height / tileH];
		return grid;
	}
	
	public static void placePlat(int x, int y, int[][] grid){
		grid[x][y] = 1;
	}
	
	//Checks each grid position for a value
	// 1 = platform
	// 2 = door
	// 3 = enemy??
	public static int checkGrid(int[][] grid){
		
		int tileCount = 0;
		
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				if(grid[i][j] == 1){
					//System.out.println("platform at: " + "[" + i + "," + j + "]");
					//return grid[i][j];
					tileCount++;
				}
			}
		}
		return tileCount;
	}
	
	public static int getGridX(int[][] grid){
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				if(grid[i][j] == 1){
					return i;
				}
			}
		}
		return 0;
	}
	
	public static int getGridY(int[][] grid){
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				if(grid[i][j] == 1){
					return j;
				}
			}
		}
		return 0;
	}
	
	public static void main(String[] args){
		int[][] grid = tileGrid();
		placePlat(2, 2, grid);
		checkGrid(grid);
		for (int[] x : grid)
		{
		   for (int y : x)
		   {
		        System.out.print(y + " ");
		   }
		   System.out.println();
		}
	}
}
