import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.Shape;
import java.awt.Color;

public class DropCircle extends GameObject{
	private Texture tex = Game.getTextureInstance();
	private Animation circleAnimation;
	private Shape circle;
	
	public DropCircle(int x,int y,ObjId id){
		super(x,y,id);
		
		circleAnimation = new Animation(2,tex.circle[0],tex.circle[1],tex.circle[2],tex.circle[3],tex.circle[4],tex.circle[5],tex.circle[6],tex.circle[7],tex.circle[8],
				tex.circle[9],tex.circle[10],tex.circle[11]);
	}

	@Override
	public void tick(LinkedList<GameObject> obj) {
		circleAnimation.runAnimation();
	}

	@Override
	public void render(Graphics g) {
		circleAnimation.drawAnim(g,(int)x-35,(int)y-30);
		
	}
	
	public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,40,40);
    }
}
