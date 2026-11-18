package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));

        Label title = new Label("College Student Registration");

        HBox top = new HBox(10);
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(10));
        top.getChildren().add(title);

        root.setTop(top);

        Button dashboard = new Button("Dashboard");
        Button registration = new Button("Registration");
        Button courses = new Button("Courses");
        Button help = new Button("Help");

        VBox left = new VBox(10);
        left.setPadding(new Insets(10));
        left.getChildren().addAll(dashboard, registration, courses, help);

        root.setLeft(left);

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);

        TextField studentId = new TextField();
        TextField fullName = new TextField();
        TextField email = new TextField();
        TextField program = new TextField();

        pane.add(new Label("Student ID:"), 0, 0);
        pane.add(studentId, 1, 0);

        pane.add(new Label("Full Name:"), 0, 1);
        pane.add(fullName, 1, 1);

        pane.add(new Label("Email:"), 0, 2);
        pane.add(email, 1, 2);

        pane.add(new Label("Program:"), 0, 3);
        pane.add(program, 1, 3);

        Button register = new Button("Register");
        Button clear = new Button("Clear");

        HBox buttons = new HBox(10);
        buttons.setPadding(new Insets(10));
        buttons.getChildren().addAll(register, clear);

        Label message = new Label();

        VBox center = new VBox(10);
        center.setPadding(new Insets(10));
        center.getChildren().addAll(pane, buttons, message);

        root.setCenter(center);

        register.setOnAction(_ -> {
            String id = studentId.getText().trim();
            String name = fullName.getText().trim();
            String emailAddress = email.getText().trim();
            String programName = program.getText().trim();

            if (id.isEmpty() || name.isEmpty() || emailAddress.isEmpty() || programName.isEmpty()) {
                message.setText("Please fill in all fields.");
            } else if (!emailAddress.matches("[^\\s@]+@[^\\s@]+\\.[^\\s@]+")) {
                message.setText("Enter an email like student@example.com.");
            } else {
                message.setText("Registration successful!");
            }
        });

        clear.setOnAction(event -> {
            studentId.clear();
            fullName.clear();
            email.clear();
            program.clear();
            message.setText("");
        });

        dashboard.setOnAction(_ -> message.setText("Welcome to the Student Registration Dashboard."));

        registration.setOnAction(_ -> {
            message.setText("Enter your details and click Register.");
            studentId.requestFocus();
        });

        courses.setOnAction(_ -> message.setText(
                "Centennial Courses for Software Development:\n"
                        + "Java Programming\n"
                        + "Web Development\n"
                        + "AI System Design\n"
                        + "Introduction to AI\n"
                        + "Mathematics\n"
                        + "English Comm"
        ));

        help.setOnAction(_ -> message.setText(
                "1. Fill in all four fields.\n"
                        + "2. Enter an email like student@example.com.\n"
                        + "3. Click Register to check your details.\n"
                        + "4. Click Clear to reset the form."
        ));

        Scene scene = new Scene(root, 650, 400);

        primaryStage.setTitle("Student Registration");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
