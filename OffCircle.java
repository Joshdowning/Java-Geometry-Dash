import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

public class OffCircle extends GameObject{
	
	public OffCircle(int x,int y,ObjId id){
		super(x,y,id);
	}
	
	public void tick(LinkedList < GameObject > obj) {

	}

	public void render(Graphics g) {

	}

	public Rectangle getBounds() {
	    return new Rectangle((int) x+45, (int) 0, 10, 1000);
	}
	
}
