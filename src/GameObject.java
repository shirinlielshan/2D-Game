import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected float x, y;
	protected ID id;
	float velX;
	protected float velY;
	
	
	public GameObject(float x, float y, ID id) {
		
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract Rectangle getBounds();
	
	public abstract void tick();
	
	public abstract void render(Graphics g);

	//Getters Setters
	public int getX() {
		return (int) x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return (int) y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public int getVelX() {
		return (int) velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return (int) velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	
	
	
}
