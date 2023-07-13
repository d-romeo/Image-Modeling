import java.awt.image.BufferedImage;
import java.io.File;

public class GreyScale {
    public static void main(String[] args) throws InterruptedException{
        if(args.length == 0){
            System.err.println("uso: java GreyScale nome_del_file.estensione");
            System.exit(1);
        }
        BufferedImage img = IOTools.read(args[0]);

        BufferedImage greyScaleImage = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
        greyScaleImage.getGraphics().drawImage(img,0,0,null);
        int w = greyScaleImage.getWidth();
        int h = greyScaleImage.getHeight();
        Row[] rows = new Row[h];
        Thread[] threads = new Thread[h];

        for(int i=0; i<h; i++){
            rows[i] = new Row(img.getRGB(0, i, w, h, null, 0, w));
            threads[i] = new Thread(rows[i]);
        }

        for(int i=0; i<h; i++){
            threads[i].start();
        }

        for(int i=0; i<h; i++){
            threads[i].join();
            greyScaleImage.setRGB(0, i, w, h, rows[i].ARGBInt(), 0, w);
        }

        IOTools.savedImage(greyScaleImage);

    }
}
