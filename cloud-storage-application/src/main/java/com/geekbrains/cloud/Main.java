package com.geekbrains.cloud;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage.setScene(new Scene(parent));
        stage.setTitle("Cloud storage");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}