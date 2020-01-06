import java.awt.image.BufferedImage;
public class Texture
{
   private BufferedImage blocks_sheet=null;
   private BufferedImage platforms_sheet=null;
   
   TextureSheet bsheet;
   TextureSheet psheet;
   public BufferedImage[]player = new BufferedImage[20];
   public BufferedImage[]platform  = new BufferedImage[2];
   
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
        for(int i=0;i<20;i++){
            if(i<7){
                player[i] = bsheet.grabImage(i+1,1,45,45);
            }
            else if(i>=7 &&i<14){
                player[i] = bsheet.grabImage(i-6,2,45,45);
            }
            else
                player[i] = bsheet.grabImage(i-13,3,45,45);
            }
        platform[0] = psheet.grabImage(1,1,32,32);  
        platform[1] = psheet.grabImage(2,1,32,32);  
    }

}