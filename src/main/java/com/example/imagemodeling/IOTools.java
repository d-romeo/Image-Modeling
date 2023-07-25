package com.example.imagemodeling;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class IOTools {

    private IOTools(){}

    public static BufferedImage read(File fileName){
        try{
            return ImageIO.read(fileName);
        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    public static void savedImage(BufferedImage img) {
        try {
            File outputFile = new File("test.jpg");
            ImageIO.write(img, "jpg", outputFile);
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(outputFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
        public static Image convertToFxImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }

        return new ImageView(wr).getImage();
    }
}
