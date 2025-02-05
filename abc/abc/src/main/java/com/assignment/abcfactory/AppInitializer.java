package com.assignment.abcfactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/mainlayout.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("The Uniform Store");
        Image image = new Image(getClass().getResourceAsStream("/image/UniformStore.jpg"));
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
