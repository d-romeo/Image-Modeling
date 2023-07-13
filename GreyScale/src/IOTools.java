import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class IOTools {

    private IOTools(){}

    public static BufferedImage read(String fileName){
        try{
            File f = new File(fileName);
            return ImageIO.read(f);
        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    public static void savedImage(BufferedImage img){
        try{
            File outputFile = new File("savedImage/greyScaleImage.png");
            ImageIO.write(img, "png", outputFile);
        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }
}
