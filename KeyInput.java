import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    Handler handler;
    

    public KeyInput(Handler handler) {
        this.handler = handler;   
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (GameObject entity: handler.getObj()) {
            if (entity.getId() == ObjId.Player) {
                if (key == KeyEvent.VK_W && !entity.isRising() && !entity.isGravityUp()) {
                    entity.setRising(true);
                    entity.setVelocityY( - 10);    
                }
                if (key == KeyEvent.VK_W && !entity.isRising() && entity.isGravityUp()) {
                    entity.setRising(true);
                    entity.setVelocityY(10);    
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
            if (entity.getId() == ObjId.Player && !entity.isRising()) {
                if (key == KeyEvent.VK_W) entity.setVelocityY(0);
            }
        }
    }
    
    

}