import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 640, HEIGHT = WIDTH /12 * 9;
	private Thread thread;
	private boolean running = false;
	private BufferStrategy bs;
	private Graphics g;
	private Handler handler;
	private Random r;
	private HUD hud;
	private Spawn spawner;
	
	public Game() {
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		new Window(WIDTH, HEIGHT, "Game", this);
		hud = new HUD();
		
		r = new Random();
		spawner = new Spawn(handler, hud);
		
		
		//Objects
		handler.addObject(new Player(WIDTH /2-32, HEIGHT/2-32, ID.Player, handler));
		handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
		
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public synchronized void stop() {
		try {
			running = false;
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){
		this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
                while(delta >=1){
                    tick();
                    delta--;
                }
                if(running)
                    render();
                frames++;
                            
                if(System.currentTimeMillis() - timer > 1000){
                    timer += 1000;
                    System.out.println("FPS: "+ frames);
                    frames = 0;
                }
        }
        stop();
    }
	
	
	public void tick() {
		
		handler.tick();
		hud.tick();
		spawner.tick();
	}
	
	public void render() {
		bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
		return;
		}
		
		g = bs.getDrawGraphics();
		//Draw
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		//End
		
		handler.render(g);
		hud.render(g);
		
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max) {
		if(var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}
	
	public static void main(String[] args) {
		new Game();

	}

	

}
