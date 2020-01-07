import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Rectangle;


public class JumpPad extends GameObject
{
    private Animation sparkle;
    Texture tex = Game.getTextureInstance();
    
    
    public JumpPad(float x, float y, ObjId id) {
        super(x, y, id);
        sparkle = new Animation(2,tex.platform[2],tex.platform[3],tex.platform[4]);
    }

    public void tick(LinkedList < GameObject > obj) {
            sparkle.runAnimation();
    }

    public void render(Graphics g) {
        sparkle.drawAnim(g,(int)x,(int)y);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 2);
    }

}
