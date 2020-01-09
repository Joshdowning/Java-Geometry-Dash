import java.awt.Graphics;

public class SpaceShipPortal extends GravityPortalUp{
	
	private Texture tex = Game.getTextureInstance();
	
	public SpaceShipPortal(int x, int y, ObjId id){
		super(x,y,id);
	}
	
	public void render(Graphics g) {
        g.drawImage(tex.platform[12],(int) x, (int) y,null);
    }
}
