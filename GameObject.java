import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
public abstract class GameObject {
    protected float x,y;
    protected int[]xx,yy;
    protected float velocityX = 0;
    protected float velocityY = 0;
    protected ObjId id;
    protected boolean dropping = true;
    protected boolean rising = false;
    
    
    /**
     * Constructor for rectangle game objects
     * @param x
     * @param y
     * @param id
     */
    public GameObject(float x, float y, ObjId id) {
    	this.x = x;
    	this.y = y;
    	this.id = id;
    }
	
    /**
     * Constructor for polygon game objects
     * @param xx
     * @param yy
     * @param x
     * @param y
     * @param id
     */
    public GameObject(int[]xx,int[]yy,int x,int y,ObjId id){
    	this.xx = xx;
    	this.yy = yy;
    	this.x=x;
    	this.y=y;  
    	this.id = id;
    }

    public abstract void tick(LinkedList < GameObject > obj);

    public abstract void render(Graphics g);

    public Rectangle getBounds(){
    	return new Rectangle();
    }
    
    public boolean getJumped(){
        return true;
    }
    public boolean isGravityUp(){
        return true;
    }
    
    public void setJumped(boolean jumped){
        
    }
	   
    public Area getPolygonArea(){
    	return new Area();
    }

    public float getX() {
    	return x;
    }
    public float getY() {
    	return y;
    }
    public void setX(float x) {
    	this.x = x;
    }
    public void setY(float y) {
    	this.y = y;
    }

    public float getVelocityX() {
    	return velocityX;
    }
    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityX(float velocityX) {
    	this.velocityX = velocityX;
    }
    public void setVelocityY(float velocityY) {
    	this.velocityY = velocityY;
    }
    public boolean isDropping() {
    	return dropping;
    }
    public boolean isRising() {
    	return rising;
    }
    public void setSpaceshipDown(boolean spaceshipDown){
    	
    } 
    public void setDropping(boolean dropping) {
	this.dropping = dropping;
    }
    public void setRising(boolean rising) {
	this.rising = rising;
    }
    public boolean isSpaceship(){
    	return false;
    }
    
    public boolean isOnDropCircle(){
    	return false;
    }
    
    public ObjId getId() {
        return id;
    }
    
    public Area getCircleArea(){
    	return new Area();
    }
}