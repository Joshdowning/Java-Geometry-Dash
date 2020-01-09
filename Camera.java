public class Camera {

    private float x, y;
    
    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void tick(GameObject player) {
        /*x -= acceleration; //no need for now
        acceleration += 0.002;
        if (acceleration > 5) {
            acceleration = 5; 
        }*/
    	x = -player.getX()+Game.WIDTH/3;
    	y = (float) (-player.getY()+Game.HEIGHT/1.5);
    }
    
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    
    public void setX(float x){
        this.x = x;
    }
    
    public void setY(float y){
        this.y = y;
    }
}