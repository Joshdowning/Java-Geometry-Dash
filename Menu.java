import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Menu
{
    private BufferedImage menu;
    private BufferedImageLoader loader;
    public Menu(){
        loader = new BufferedImageLoader();
        menu = loader.loadImage("/res/Menu.png");
    }
    
    
    public void tick(){
        
    }
    
    
    public void render(Graphics g){
        g.drawImage(menu,0,0,null);
        System.out.println("rendering menu");
        
    }
    
   

 
}
