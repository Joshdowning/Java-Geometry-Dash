
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Area;


public class FlipSpike extends Spike {
	 Texture tex = Game.getTextureInstance();
	public FlipSpike(int[] xx, int[] yy, int x, int y, ObjId id) {
        super(xx, yy,x,y, id);
    }

    @Override
    public void render(Graphics g) {  
    	
        g.drawImage(tex.platform[11],(int)x,(int)y,null);
    }
    @Override
    public Area getPolygonArea() {
        return new Area(new Polygon(xx,yy,3));
    }
}
