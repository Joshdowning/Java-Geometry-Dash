public class Camera {

    private float x, y;
    private float acceleration;


    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
        acceleration = 2;
    }

    public void tick() {
        x -= acceleration;
        acceleration += 0.002;
        if (acceleration > 4) {
            acceleration = 4;
        }
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
    
    public void setAccel(float acceleration){
        this.acceleration = acceleration;
    }
}