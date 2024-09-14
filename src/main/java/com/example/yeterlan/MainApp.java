package com.example.yeterlan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // FXML dosyasını yükle
        Parent root = FXMLLoader.load(getClass().getResource("home-page.fxml"));

        // Sahneyi oluştur
        Scene scene = new Scene(root);
        primaryStage.setTitle("KAP Haber İzleme Modülü");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        System.out.println();
        launch(args);
    }
}


