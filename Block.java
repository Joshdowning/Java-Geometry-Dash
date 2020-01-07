import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class Block extends GameObject {
    
    
    Texture tex = Game.getTextureInstance();
    
    
    public Block(float x, float y, ObjId id) {
        super(x, y, id);

    }

    public void tick(LinkedList < GameObject > obj) {

    }

    public void render(Graphics g) {
        g.drawImage(tex.platform[0],(int) x, (int) y,null);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}