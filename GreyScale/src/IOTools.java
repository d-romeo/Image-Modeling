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

    public static void savedImage(BufferedImage img, String fileName){
        try{
            File outputFile = new File(fileName);
            ImageIO.write(img, "jpg", outputFile);
        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }
}
