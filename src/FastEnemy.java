import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class FastEnemy extends GameObject{

    private Handler handler;
    private Random r = new Random();
   
    public FastEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        
        this.handler = handler;
        velX = 3;
        velY = 12;
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int) x,(int) y, 16,16);
    }
    
    public void tick() {
        x += velX;
        y += velY;
        
        if(y <= 0 || y >= Game.HEIGHT-40) {if(velY<0) velY = -(r.nextInt(7)+1)* -1; else velY = (r.nextInt(7)+1)* -1;}
        if(x <= 0 || x >= Game.WIDTH-24) {if(velX<0) velX = -(r.nextInt(7)+1)* -1; else velX = (r.nextInt(7)+1)* -1;}
        
        handler.addObject(new Trail((int) x,(int) y, ID.Trail, Color.CYAN, 15, 15, 0.07f, handler));
        
    }

    public void render(Graphics g) {
        
        g.setColor(Color.CYAN);
        g.fillRect((int) x,(int) y, 15, 15);
        
    }
    
    
}