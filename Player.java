import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.Shape;
import java.awt.image.BufferedImage;



public class Player extends GameObject {
    private float width = 45;
    private float height = 45;
    public final float MAX_VELOCITY = 12;
    private float gravity = 0.5f;
    private float acceleration = 3;
    Texture tex = Game.getTextureInstance();
    Handler handler;
    private Animation playerSpin;
    private boolean touchingGround = false;
    private boolean gotLastFrame = false;
    private BufferedImage lastFrame;
    

    public Player(float x, float y, Handler handler, ObjId id) {
        super(x, y, id);
        dropping = true;
        this.handler = handler;
        playerSpin = new Animation(2,tex.player[1],tex.player[2],tex.player[3],tex.player[4],tex.player[5],tex.player[6],tex.player[7],tex.player[8],tex.player[9],tex.player[10],tex.player[11],tex.player[12],tex.player[13],tex.player[14],tex.player[15],tex.player[16],tex.player[17],tex.player[18],tex.player[19]);
    }

    public void tick(LinkedList < GameObject > obj) {
        x += acceleration;
        acceleration += 0.002;
        if (acceleration > 4) {
            acceleration = 4;
        }
        y += velocityY;

        if (dropping || rising) {
            velocityY += gravity;
            

            if (velocityY > MAX_VELOCITY) {
                velocityY = MAX_VELOCITY;
            }

        }
        
        checkCollisions(obj);
        
        if(y>850){
            playerDied();
        }

        
    }

    public void render(Graphics g) {
        if (rising) {
            gotLastFrame= false;
            playerSpin.runAnimation();
            playerSpin.drawAnim(g,(int)x,(int)y);
        }
        else if(playerSpin.needsRolling(lastFrame)){
                playerSpin.runAnimation();
                playerSpin.drawAnim(g,(int)x,(int)y);
                lastFrame = playerSpin.getLastFrame();
        }
        else{
            g.drawImage(lastFrame,(int)x,(int)y,null);
        }
        
        
    }
    public Area getPlayerArea(){
        return new Area(new Rectangle((int)x,(int)y,(int)width,(int)height));
    }

    public Rectangle getBounds() {
        return new Rectangle((int)(x + (width / 4)), (int)(y + height / 2), (int) width / 2, (int) height / 2);
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)(x), (int) y + 5, (int) 5, (int) height - 10);
    }
    public Rectangle getBoundsRight() {
        return new Rectangle((int)(x + width - 5), (int) y + 5, (int) 5, (int) height - 7);
    }
    public Rectangle getBoundsTop() {
        return new Rectangle((int)(x + (width / 4)), (int)(y), (int) width / 2, (int) height / 2);
    }
    
    public float getX(){
        return x;
    }
    
    
    
    public void checkCollisions(LinkedList < GameObject > obj){
        for (GameObject entity: obj) {
            if (entity.getId() == ObjId.Block) {
                if (getBounds().intersects(entity.getBounds())) {
                    if(!gotLastFrame){
                        lastFrame = playerSpin.getLastFrame();
                    }
                    
                    gotLastFrame = true;
                    
                    dropping = false;
                    touchingGround = true;
                    rising = false;
                    y = (entity.getY()-height);
                    
                    velocityY = 0;
                    
                }
                

                if (getBoundsLeft().intersects(entity.getBounds())) {
                    x = entity.getX() + 35;
                    velocityX = 0;
                }
                if (getBoundsRight().intersects(entity.getBounds())) {
                    x = entity.getX() - width;
                    velocityX = 0;
                    playerDied();
                }
                if (getBoundsTop().intersects(entity.getBounds())) {
                    y = entity.getY() + 32;
                    velocityY = 0;
                }
                else{
                    
                    dropping = true;
                }
            }
            if (entity.getId() == ObjId.Spike){
                Area playerArea = getPlayerArea();
                playerArea.intersect(entity.getPolygonArea());
                if (!playerArea.isEmpty()){
                  playerDied();
                }
                
            }
                 
        }
    }
    public void playerDied(){
            x = 300;
            y = 700;
            acceleration = 3;
           
            Game.State = Game.STATE.MENU;
        }
    
}