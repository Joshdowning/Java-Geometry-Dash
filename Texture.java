
import java.awt.image.BufferedImage;
public class Texture
{
   public BufferedImage[]player = new BufferedImage[23];
   public BufferedImage[]platform  = new BufferedImage[13];
   private BufferedImage blocks_sheet=null;
   private BufferedImage platforms_sheet=null;
   private TextureSheet bsheet;
   private TextureSheet psheet;
   
   
    public Texture()
    {
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            blocks_sheet = loader.loadImage("/res/block_sheet.png");
            platforms_sheet = loader.loadImage("/res/platform_sheet.png");
            
        }catch(Exception e){
            e.printStackTrace();
        }
        bsheet = new TextureSheet(blocks_sheet); //blocks for player
        psheet = new TextureSheet(platforms_sheet); //platform textures
        getTexture();    
    }
    
    private void getTexture(){
        for(int i=0;i<23;i++){
        	
            if(i<7){
            	platform[i] = psheet.grabImage(i+1,1,32,32);
                player[i] = bsheet.grabImage(i+1,1,45,45);
            }
            else if(i>=7 &&i<10){
            	platform[i] = psheet.grabImage(i+1,1,32,32);
                player[i] = bsheet.grabImage(i-6,2,45,45);
            }
            else if(i>=10 && i<14)
            	player[i] = bsheet.grabImage(i-6,2,45,45);
            else if(i>=14 && i<21)
                player[i] = bsheet.grabImage(i-13,3,45,45);
            else
            	player[i] = bsheet.grabImage(i-20,4,45,45);
        }        
        platform[10] = psheet.grabImage(1,2,32,96);
        platform[11]= psheet.grabImage(1, 2, 32, 32); 
        platform[12]=psheet.grabImage(2,2,32,96);
    }
}