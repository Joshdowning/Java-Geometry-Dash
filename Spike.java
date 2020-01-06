import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;


public class Spike extends GameObject{
    Texture tex = Game.getTextureInstance();
    
    public Spike(int[] xx, int[] yy, int x, int y, ObjId id) {
        super(xx, yy,x,y, id);
    }

    public void tick(LinkedList < GameObject > obj) {

    }

    public void render(Graphics g) {
        Polygon shape = new Polygon(xx,yy,3);        
        g.drawImage(tex.platform[1],(int)x,(int)y,null);
    }

    public Area getPolygonArea() {
        return new Area(new Polygon(xx,yy,3));
    }
   
}