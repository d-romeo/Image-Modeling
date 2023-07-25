package com.example.imagemodeling;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class homePageApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(homePageApplication.class.getResource("base.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1024, 512);
            stage.setTitle("IMAGE CONVERTER");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch(Exception e){
            e.getStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
