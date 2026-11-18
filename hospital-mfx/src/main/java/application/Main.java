package application;

import com.hospital.PatientController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        PatientController controller = new PatientController();
        stage.setScene(new Scene(controller.getRoot(), 1100, 600));
        stage.setTitle("Hospital Management System");
        stage.show();
        controller.loadPatients();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
