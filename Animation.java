import java.awt.Graphics;
import java.awt.image.BufferedImage;
public class Animation
{
    private int speed;
    private int frames;
    private int index = 0;
    private int count = 0;
    private BufferedImage[] imgs;
    private BufferedImage currentImage;

    public Animation(int speed, BufferedImage... args)
    {
       this.speed= speed;
       imgs = new BufferedImage[args.length];
       for(int i =0;i<args.length;i++){
           imgs[i] = args[i];
        }
        frames = args.length;
        
    }
    
    public void runAnimation(){
        index++;
        if(index>speed){
            index = 0;
            changeFrame();
        }
    }
    private void changeFrame(){
     for(int i =0;i<frames;i++){
         if(count == i){
             currentImage = imgs[i];
         }
     }
     count++;
     
     if(count>frames){
         count = 0;
        }
    }
    
    public BufferedImage getLastFrame(){
       return currentImage;
    }
        
    public boolean needsRolling(BufferedImage img){ //for when the player cube does not land on an edge
        for(int i =0;i<frames;i++){
            if(img == imgs[11] ||img == imgs[0]){
                return false;
            }
        }        
        return true;
    }
    
    public boolean finishedExplosion(){
    	if(currentImage == imgs[7]){
    		return true;
    	}
    	return false;
    }

    public void drawAnim(Graphics g,int x, int y ){
        g.drawImage(currentImage,x,y,null);
    }
    
    public void setFrame(int i){
        currentImage = imgs[i];
    } 

}
