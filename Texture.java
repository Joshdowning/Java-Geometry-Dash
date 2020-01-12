
import java.awt.image.BufferedImage;
public class Texture
{
   public BufferedImage[]player = new BufferedImage[23];
   public BufferedImage[]platform  = new BufferedImage[13];
   public BufferedImage[]explosion  = new BufferedImage[8];
   public BufferedImage[]circle  = new BufferedImage[12];
   private BufferedImage blocks_sheet=null;
   private BufferedImage platforms_sheet=null;
   private BufferedImage explosions_sheet = null;
   private BufferedImage circle_sheet = null; 
   private TextureSheet bsheet; //player block sheet
   private TextureSheet csheet; //circles sheet
   private TextureSheet psheet; //platform sheet
   private TextureSheet esheet; //explosion sheet
   
   
    public Texture()
    {
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            blocks_sheet = loader.loadImage("/res/block_sheet.png");
            platforms_sheet = loader.loadImage("/res/platform_sheet.png");
            explosions_sheet = loader.loadImage("/res/explosion_sheet.png");
            circle_sheet = loader.loadImage("/res/circle_sheet.png");
            
        }catch(Exception e){
            e.printStackTrace();
        }
        bsheet = new TextureSheet(blocks_sheet); //blocks for player
        psheet = new TextureSheet(platforms_sheet); //platform textures
        esheet = new TextureSheet(explosions_sheet);//explosion death animation
        csheet = new TextureSheet(circle_sheet);
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
        //Clean this section up later
        
        platform[10] = psheet.grabImage(1,2,32,96);
        platform[11]= psheet.grabImage(1, 2, 32, 32); 
        platform[12]=psheet.grabImage(2,2,32,96);
        
        explosion[0] = esheet.grabImage(1, 1, 355/2, 370/2);
        explosion[1] = esheet.grabImage(2, 1, 370/2, 350/2);
        explosion[2] = esheet.grabImage(1, 2, 375/2, 367/2);
        explosion[3] = esheet.grabImage(2, 2, 365/2, 345/2);
        explosion[4] = esheet.grabImage(3, 2, 550/3, 345/2);
        explosion[5] = esheet.grabImage(1, 3, 370/2, 360/2);
        explosion[6] = esheet.grabImage(2, 3, 345/2, 340/2);
        explosion[7] = esheet.grabImage(3, 3, 340/2, 333/2);
        
        circle[0] = csheet.grabImage(1, 1, 105, 105);
        circle[1] = csheet.grabImage(2, 1, 105, 105);
        circle[2] = csheet.grabImage(3, 1, 105, 105);
        circle[3] = csheet.grabImage(4, 1, 105, 105);
        circle[4] = csheet.grabImage(1, 2, 105, 105);
        circle[5] = csheet.grabImage(2, 2, 105, 105);
        circle[6] = csheet.grabImage(3, 2, 106, 106);
        circle[7] = csheet.grabImage(4, 2, 108, 108);
        circle[8] = csheet.grabImage(1, 3, 105, 105);
        circle[9] = csheet.grabImage(2, 3, 105, 105);
        circle[10] = csheet.grabImage(3, 3, 108, 110);
        circle[11] = csheet.grabImage(4, 3, 108, 109);   
        
    }
}