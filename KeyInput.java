import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
   
	private Handler handler;
    public KeyInput(Handler handler) {
        this.handler = handler;   
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (GameObject entity: handler.getObj()) {
            if (entity.getId() == ObjId.PLAYER) {
            	
            	
            	if (key == KeyEvent.VK_SPACE && !entity.isGravityUp() && entity.isOnDropCircle()) {    
                    entity.setVelocityY(10); 
                }
            	if (key == KeyEvent.VK_SPACE && entity.isGravityUp() && entity.isOnDropCircle()) {
                    entity.setVelocityY(-10);    
                }
            	
                if (key == KeyEvent.VK_SPACE && !entity.isRising() && !entity.isGravityUp() && !entity.isSpaceship()) {
                    entity.setRising(true);
                    entity.setVelocityY( - 10);    
                }
                if (key == KeyEvent.VK_SPACE && !entity.isRising() && entity.isGravityUp()&& !entity.isSpaceship()) {
                    entity.setRising(true);
                    entity.setVelocityY(10);    
                }
                if (key == KeyEvent.VK_SPACE && !entity.isRising() && !entity.isGravityUp() && entity.isSpaceship()) {
                    entity.setRising(true);
                    entity.setVelocityY(-11);    
                }
                
                
            }
        }
        if (key == KeyEvent.VK_ESCAPE) {
                System.exit(1);
        }
        if(key == KeyEvent.VK_ENTER){
                Game.State = Game.STATE.GAME;
        }
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (GameObject entity: handler.getObj()) {
            if (entity.getId() == ObjId.PLAYER&& !entity.isRising() &&!entity.isSpaceship()) {
                if (key == KeyEvent.VK_SPACE){
                	entity.setVelocityY(0);
                	entity.setRising(false);
                	entity.setDropping(true);
                }
            }
            if (entity.getId() == ObjId.PLAYER &&entity.isSpaceship()) {
                if (key == KeyEvent.VK_SPACE){                	
                	entity.setVelocityY(2);
                	entity.setRising(false);
                	entity.setDropping(true);
                }
            }
        }
        
    }
    
}