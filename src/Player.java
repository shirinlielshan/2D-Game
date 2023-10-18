import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{
	private Handler handler;
	Random r = new Random();
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}


	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp((int) x, 0, Game.WIDTH - 48);
		y = Game.clamp((int) y, 0, Game.HEIGHT - 71);
		
        handler.addObject(new Trail((int) x, (int) y, ID.Trail, Color.white, 32, 32, 0.09f, handler));
        
		collision();
	}
	
	public void collision() {
		for(int i = 0;i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 1;
				}
				
			}
			
			if(tempObject.getId() == ID.HealthObject) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH += 10;
					handler.object.remove(tempObject);
				}
			}
		}
	
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int) x, (int) y, 32, 32);
	}

	
}
