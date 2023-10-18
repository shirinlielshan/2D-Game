import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HealthObject extends GameObject{

	private Handler handler;
	
	public HealthObject(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velX = 2;
		velY = 2;
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 10, 10);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 50) velY *= -1; 
		if(x <= 0 || x >= Game.WIDTH - 30) velX *= -1; 
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillOval((int)x, (int)y, 12, 12);
	}

}
