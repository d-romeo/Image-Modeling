package com.example.imagemodeling;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Controller{
    public BufferedImage img;
    @FXML
    public Label label;
    @FXML
    public Button button;
    @FXML
    public ImageView imageView;
    @FXML
    public ImageView imageView1;
    @FXML
    public Label label1;
    @FXML
    public Button button1;

    @FXML
    protected void convert() throws InterruptedException {
        int w = img.getWidth();
        int h = img.getHeight();
        Row[] rows = new Row[h];
        Thread[] threads = new Thread[h];

        for(int i=0; i<h; i++){
            rows[i] = new Row(img.getRGB(0, i, w, 1, null, 0, w));
            threads[i] = new Thread(rows[i]);
        }
        for(int i=0; i<h; i++){
            threads[i].start();
        }

        for(int i=0; i<h; i++){
            threads[i].join();
            img.setRGB(0, i, w, 1, rows[i].ARGBInt(), 0, w);
        }
        // IOTools.savedImage(img);
        Image img0 = IOTools.convertToFxImage(img);
        imageView1.setImage(img0);
        button1.setVisible(true);
        button1.setDisable(false);
    }
    @FXML
    private void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()){
            event.acceptTransferModes(TransferMode.ANY);
        }
    }
    @FXML
    private void handleDrop(DragEvent Event) throws FileNotFoundException{
        List<File> files = Event.getDragboard().getFiles();
        System.out.println("Got " + files.size() + " files");
        img = IOTools.read(files.get(0));
        imageView.setImage(new Image(new FileInputStream(files.get(0))));
        Event.consume();
        button.setDisable(false);
        button.setOpacity(1);

    }

    public void save(){
        BufferedImage bufferedImage = img;
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter(
                "Image Files",
                "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp", "*.tiff", "*.ico",
                "*.PNG", "*.JPG", "*.JPEG", "*.GIF", "*.BMP", "*.TIFF", "*.ICO"
        );
        fileChooser.getExtensionFilters().add(imageFilter);
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try {
                ImageIO.write(bufferedImage, ".png", file);
                System.out.println("Immagine salvata correttamente.");
            } catch (IOException e) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Errore di Salvataggio");
                errorAlert.setHeaderText("Non è stato possibile caricare il file");
                errorAlert.showAndWait();
            }
        }
        button1.setVisible(false);
        imageView1.setImage(null);
        imageView.setImage(null);
        button1.setDisable(true);
        button.setDisable(true);
        button.setOpacity(0.5);

    }

    public void openFile(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
//Set extension filter
        FileChooser.ExtensionFilter extFilterALL =
                new FileChooser.ExtensionFilter("All Files", "*.jpeg", "*.jpg", "*.png",
                        "*.tiff", "*.tif","*.JPG","*.JPEG", "*.png", "*.TIFF", "*.TIF");
        FileChooser.ExtensionFilter extFilterJPG =
                new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG", "*.jpg",
                        "*.JPEG", "*.jpeg");
        FileChooser.ExtensionFilter extFilterPDF =
                new FileChooser.ExtensionFilter("PNG files (*.pdf)", "*.PNG", "*.pdf");
        FileChooser.ExtensionFilter extFilterTIFF =
                new FileChooser.ExtensionFilter("TIFF files (*.tiff)", "*.TIF", "*.TIFF",
                        "*.tif", "*.tiff");

        fileChooser.getExtensionFilters().addAll(extFilterALL,extFilterJPG, extFilterPDF, extFilterTIFF);

        img = IOTools.read(file);
        try {
            imageView.setImage(new Image(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Errore di Caricamento");
            errorAlert.setHeaderText("Non è stato caricato nessun file");
            errorAlert.showAndWait();
        }
        mouseEvent.consume();
        button.setDisable(false);
        button.setOpacity(1);
    }
}
