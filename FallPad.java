import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Rectangle;


public class FallPad extends GameObject
{
    private Animation flippedSparkle;
    Texture tex = Game.getTextureInstance();
    
    
    public FallPad(float x, float y, ObjId id) {
        super(x, y, id);
        flippedSparkle = new Animation(2,tex.platform[6],tex.platform[7],tex.platform[8],tex.platform[9]);

    }

    public void tick(LinkedList < GameObject > obj) {
            flippedSparkle.runAnimation();
    }

    public void render(Graphics g) {
        flippedSparkle.drawAnim(g,(int)x,(int)y);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 35, 10);
    }

}