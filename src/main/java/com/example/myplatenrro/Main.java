package com.example.myplatenrro;

import com.example.myplatenrro.dataModel.ListaCuvinteSingleton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main application class for Romanian License Plate Generator
 * Initializes the JavaFX application and loads the word list
 */
public class Main extends Application {
    
    /**
     * Starts the JavaFX application by loading the main window and applying modern styling
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainwindow.fxml"));
        Scene scene = new Scene(root, 1100, 700);
        
        // Apply modern CSS styling
        scene.getStylesheets().add(getClass().getResource("modern-style.css").toExternalForm());
        
        stage.setTitle("🚗 Generator Numere Înmatriculare România 🇷🇴");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMinWidth(1000);
        stage.setMinHeight(650);
        stage.show();
    }

    /**
     * Main method to launch the JavaFX application
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Initializes the application by loading the Romanian word list
     * This method is called before start() to prepare necessary resources
     */
    @Override
    public void init() throws Exception {
        try {
            ListaCuvinteSingleton.getInstance().incarcaLista();
        } catch (IOException e) {
            System.out.println("Error loading word list: " + e.getMessage());
        }
    }
}