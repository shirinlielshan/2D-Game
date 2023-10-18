import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	private Handler handler;
	private boolean[] keyPress = new boolean[4];
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		
		keyPress[0] = false;
		keyPress[1] = false;
		keyPress[2] = false;
		keyPress[3] = false;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				//key events for player 1
				
				if(key == KeyEvent.VK_W) {tempObject.setVelY(-5); keyPress[0] = true;}
				if(key == KeyEvent.VK_S) {tempObject.setVelY(5); keyPress[1] = true;}
				if(key == KeyEvent.VK_D) {tempObject.setVelX(5); keyPress[2] = true;}
				if(key == KeyEvent.VK_A) {tempObject.setVelX(-5); keyPress[3] = true;}
					
			}
	
		}
		

	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				//key events for player 1
				
				if(key == KeyEvent.VK_W) keyPress[0] = false;
				if(key == KeyEvent.VK_S) keyPress[1] = false;
				if(key == KeyEvent.VK_D) keyPress[2] = false;
				if(key == KeyEvent.VK_A) keyPress[3] = false;
				
				
				if(!keyPress[0] && !keyPress[1]) tempObject.setVelY(0); 
				if(!keyPress[2] && !keyPress[3]) tempObject.setVelX(0); 
			}

		}
	}
}
