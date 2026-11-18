package ca.college.registration;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CollegeRegistrationApp extends Application {

    @Override
    public void start(Stage stage) {
        RegistrationView view = new RegistrationView();

        Scene scene = new Scene(view.getRoot(), 900, 600);
        stage.setTitle("College Student Registration");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
