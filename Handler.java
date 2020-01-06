import java.util.LinkedList;
import java.awt.Graphics;

public class Handler {

	public LinkedList < GameObject > obj = new LinkedList < GameObject > ();

	public void tick() {
		for (GameObject entity: obj) {
			entity.tick(obj);
		}
	}

	public void render(Graphics g) {
		for (GameObject entity: obj) {
			entity.render(g);
		}
	}
	public void addObj(GameObject obj) {
		this.obj.add(obj);
	}

	public void removeObj(GameObject obj) {
		this.obj.remove(obj);
	}


	public LinkedList < GameObject > getObj() {
		return obj;
	}

}