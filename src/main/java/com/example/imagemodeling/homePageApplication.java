package com.example.imagemodeling;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;

public class homePageApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(homePageApplication.class.getResource("base.fxml"));
            Pane rootPane = fxmlLoader.load();
            Scene scene = new Scene(rootPane, 1024, 512);
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
