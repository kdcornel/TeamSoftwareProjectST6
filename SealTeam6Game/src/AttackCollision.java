public class AttackCollision {
    public static boolean PlayerVSEnemy(Player player, Enemy enemy) {
        Point p1 = new Point(); //Top left of player
        Point p2 = new Point(); //Bottom right of player
        Point e1 = new Point(); //Top left of enemy
        Point e2 = new Point(); //Bottom right of enemy
        
        p1.x = player.x;
        p1.y = player.y+40;
        
        p2.x = player.x+27;
        p2.y = player.y;
        
        e1.x = enemy.getX();
        e1.y = enemy.getY()+20;
        
        e2.x = enemy.getX();
        e2.y = enemy.getY();
        
        return doOverlap( p1, p2, e1, e2);
    }
    
    static class Point {
        float x, y;
    }
    
    static  boolean doOverlap(Point l1, Point r1, Point l2, Point r2) { 
        // If one rectangle is on left side of other  
        if (l1.x > r2.x || l2.x > r1.x) { 
            return false; 
        } 
  
        // If one rectangle is above other  
        if (l1.y < r2.y || l2.y < r1.y) { 
            return false; 
        } 
        
        System.out.println("Player Point1: px = " + l1.x + ", " + "py= " + l1.y);
        System.out.println("Player Point1: px = " + r1.x + ", " + "py= " + r1.y);
        
        System.out.println("Enemy Point1: ex = " + l2.x + ", " + "ey= " + l2.y);
        System.out.println("Enemy Point2: ex = " + r2.x + ", " + "ey= " + r2.y);
        return true; 
    } 
    
    public void AttackVSEnemy() {
        
    }
}