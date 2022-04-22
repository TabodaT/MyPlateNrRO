package com.example.myplatenrro;

import com.example.myplatenrro.dataModel.ListaCuvinteSingleton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainwindow.fxml"));
        Scene scene = new Scene(root, 900, 500);
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        stage.setTitle("MyPlateNr:RO");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() throws Exception {
        try {
            ListaCuvinteSingleton.getInstance().incarcaLista();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}