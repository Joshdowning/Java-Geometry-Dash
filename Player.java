/**
 * Class modeling the main player cube for the game
 * @author Josh Downing
 * @version 01/08/2020
 */
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

public class Player extends GameObject {
	public final float MAX_VELOCITY = 12;
	private float width = 45;
	private float height = 45;
	private float gravity = 0.5f;
	private float acceleration = 4;
	private Texture tex = Game.getTextureInstance();
	private Animation playerSpin;
	private Animation playerExplosion;
	private boolean gotLastFrame = false;
	private BufferedImage lastFrame;
	private boolean gravityUp = false;
	private boolean spaceship = false;
	private boolean finishedDeathAnimation= false;
	private boolean playerIsDead= false;
	
	public Player(float x, float y, ObjId id) {
		super(x, y, id);
		dropping = true;
		//this.handler = handler;
		playerSpin = new Animation(2, tex.player[1], tex.player[2], tex.player[3], tex.player[4], tex.player[5],
				tex.player[6], tex.player[7], tex.player[8], tex.player[9], tex.player[10], tex.player[11],
				tex.player[12], tex.player[13], tex.player[14], tex.player[15], tex.player[16], tex.player[17],
				tex.player[18], tex.player[19]);
		
		playerExplosion = new Animation(2,tex.explosion[5],tex.explosion[7],tex.explosion[3],tex.explosion[1],
				tex.explosion[2],tex.explosion[0],tex.explosion[6],tex.explosion[4]);
	}
	
	public void tick(LinkedList<GameObject> obj) {
		if(!playerIsDead){
			x += acceleration;
			acceleration += 0.002;
			if (acceleration > 5) {
				acceleration = 5;
			}
			y += velocityY;
			if (dropping || rising) {
				velocityY += gravity;
				if (velocityY > MAX_VELOCITY) {
					velocityY = MAX_VELOCITY;
				}
			}
			checkCollisions(obj);
			if (y > 850) {
				playerDied();
			}
		}
	}

	public void render(Graphics g) {
		if(playerIsDead){
			if(!playerExplosion.finishedExplosion()){
				playerExplosion.runAnimation();
				playerExplosion.drawAnim(g,(int)x-77,(int)y-66);
			}
			else{
				playerExplosion.setFrame(5);
				finishedDeathAnimation = true;
				gameOver();
				//playerExplosion.setFrame(5);
			}
		}
		
		else if(spaceship){
			g.drawImage(tex.player[22], (int) x, (int) y, null);
			//spaceship.runAnimation(); animate this later
			//spaceship.drawAnim(g,(int)x,(int)y);
		}
		else if (rising) {
			gotLastFrame = false;
			playerSpin.runAnimation();
			playerSpin.drawAnim(g, (int) x, (int) y);
		} else if (playerSpin.needsRolling(lastFrame)) {
			playerSpin.runAnimation();
			playerSpin.drawAnim(g, (int) x, (int) y);
			lastFrame = playerSpin.getLastFrame();
		}else {
			g.drawImage(lastFrame, (int) x, (int) y, null);
		}

		/*Graphics2D g2d = (Graphics2D)g; //see the collision bounds
		g.setColor(Color.yellow);
		g2d.draw(getBoundsTop());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBounds());*/
	}

	public Area getPlayerArea() {
		return new Area(new Rectangle((int) x, (int) y, (int) width, (int) height));
	}

	public Rectangle getBounds() {
		return new Rectangle((int) (x + (width / 4)), (int) (y + height / 2), (int) width / 2, (int) height / 2);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) (x), (int) y + 8, (int) 4, (int) height - 20);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) (x + width - 5), (int) y + 8, (int) 4, (int) height - 20);
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int) (x + (width / 4)), (int) (y), (int) width / 2, (int) height / 2);
	}

	public float getX() {
		return x;
	}

	public void checkCollisions(LinkedList<GameObject> obj) {
		for (GameObject entity : obj) {
			if (entity.getId() == ObjId.BLOCK) {	//BLOCKS COLLISION
				if (getBounds().intersects(entity.getBounds()) && !spaceship) {					
					if (!gotLastFrame) {
						lastFrame = playerSpin.getLastFrame();
					}
					gotLastFrame = true;
					dropping = false;
					rising = false;
					y = (entity.getY() - height);
					velocityY = 0;
				}
				if (getBounds().intersects(entity.getBounds()) && spaceship) {
					playerDied();
				}
				if (getBoundsTop().intersects(entity.getBounds()) && gravityUp) {
					y = entity.getY() + 30;
					velocityY = 0;					
					dropping = false;
					rising = false;
				}
				if (getBoundsTop().intersects(entity.getBounds()) && !gravityUp) {
					playerDied();					
				}
				if (getBoundsRight().intersects(entity.getBounds())) {
					/*x = entity.getX() - width;
					velocityX = 0;*/
					playerDied();
				}
				 else {
					dropping = true;
					
				}
			}
			if (entity.getId() == ObjId.SPIKE || entity.getId() == ObjId.FLIPSPIKE) { //SPIKES COLLISION
				Area playerArea = getPlayerArea();
				playerArea.intersect(entity.getPolygonArea());
				if (!playerArea.isEmpty()) {
					playerDied();
				}
			}
			if (entity.getId() == ObjId.JUMPPAD) { //JUMPPAD COLLISION

				if (getBounds().intersects(entity.getBounds()) || getBoundsRight().intersects(entity.getBounds())) {
					rising = true;
					velocityY = -11;
				}
			}
			if (entity.getId() == ObjId.FALLPAD) //FALLPAD COLLISION
				if(getBoundsTop().intersects(entity.getBounds())||getBoundsRight().intersects(entity.getBounds()) ||getBoundsLeft().intersects(entity.getBounds())){
					rising = false;
					velocityY = 11;
				}	
			if (entity.getId() == ObjId.GRAVITYPORTALUP) //GRAV PORTAL COLLISION
				if(getBoundsRight().intersects(entity.getBounds())){
					rising = true;
					gravity = -0.5f;
					gravityUp = true;					
				}
			if (entity.getId() == ObjId.GRAVITYPORTALDOWN) //REVERT GRAVITY PORTAL COLLISION
				if(getBoundsRight().intersects(entity.getBounds()) ){
					rising = true;
					gravity = 0.5f;
					gravityUp = false;
				}
			
			if (entity.getId() == ObjId.SPACESHIPPORTAL) //SPACESHIP PORTAL COLLISION
				if(getBoundsRight().intersects(entity.getBounds()) ){
					gravity =0.4f;
					spaceship = true;	
				}
			if (entity.getId() == ObjId.REVERTPORTAL) //REVERT SPACESHIP PORTAL COLLISION
				if(getBoundsRight().intersects(entity.getBounds()) ){
					gravity =0.5f;
					spaceship = false;	
				}
			
			}		
		}
	
	public boolean isSpaceship(){
		return spaceship;		
	}
	
	public boolean isGravityUp(){
		return gravityUp;
	}
	
	
	
	public void playerDied() {
		velocityX = 0;
		velocityY = 0;
		playerIsDead= true;
	}
	
	public void gameOver(){
		playerIsDead = false;
		x = 300;
		y = 700;
		
		
		acceleration = 4;
		dropping = true;
		rising = false;
		gravity = 0.5f;
		gravityUp = false;
		spaceship = false;
		finishedDeathAnimation = false;
		Game.State = Game.STATE.MENU;
	}
}