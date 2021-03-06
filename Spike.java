import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Area;



public class Spike extends GameObject{
    Texture tex = Game.getTextureInstance();
    
    public Spike(int[] xx, int[] yy, int x, int y, ObjId id) {
        super(xx, yy,x,y, id);
    }

    public void tick(LinkedList < GameObject > obj) {

    }

    public void render(Graphics g) {
        g.drawImage(tex.platform[1],(int)x,(int)y,null);
    }

    public Area getPolygonArea() {
        return new Area(new Polygon(xx,yy,3));
    }
   
}