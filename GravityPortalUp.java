import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GravityPortalUp extends GameObject {
    
    
    Texture tex = Game.getTextureInstance();
    
    
    public GravityPortalUp(float x, float y, ObjId id) {
        super(x, y, id);

    }

    public void tick(LinkedList < GameObject > obj) {

    }

    public void render(Graphics g) {
        g.drawImage(tex.platform[10],(int) x, (int) y,null);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 40, 100);
    }
}