package com.hospital;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        PatientController controller = new PatientController();

        Scene scene = new Scene(controller.getRoot(), 1100, 600);
        stage.setTitle("Hospital Management System");
        stage.setScene(scene);
        stage.show();

        controller.loadPatients();
    }

    public static void main(String[] args) {
        launch(args);
    }
}