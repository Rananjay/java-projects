package ca.college.registration;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RegistrationView {

    private final BorderPane root = new BorderPane();

    private final TextField studentIdField = new TextField();
    private final TextField fullNameField = new TextField();
    private final TextField emailField = new TextField();
    private final TextField programField = new TextField();

    private final Label statusLabel = new Label("Ready.");

    public BorderPane getRoot() {
        buildHeader();
        buildSidebar();
        buildRegistrationForm();

        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: #f4f6f8;");

        return root;
    }

    private void buildHeader() {
        Label title = new Label("College Student Registration");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

        HBox header = new HBox(title);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(15));
        header.setStyle("-fx-background-color: #2c3e50;");

        root.setTop(header);
    }

    private void buildSidebar() {
        Button dashboardButton = createMenuButton("Dashboard");
        Button registrationButton = createMenuButton("Registration");
        Button coursesButton = createMenuButton("Courses");
        Button helpButton = createMenuButton("Help");

        VBox sidebar = new VBox(12, dashboardButton, registrationButton,
                coursesButton, helpButton);
        sidebar.setPadding(new Insets(20));
        sidebar.setPrefWidth(190);
        sidebar.setStyle("-fx-background-color: #34495e;");

        root.setLeft(sidebar);
    }

    private Button createMenuButton(String text) {
        Button button = new Button(text);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; " +
                "-fx-font-size: 14px; -fx-alignment: baseline-left;");
        return button;
    }

    private void buildRegistrationForm() {
        GridPane formGrid = new GridPane();
        formGrid.setHgap(12);
        formGrid.setVgap(12);
        formGrid.setPadding(new Insets(20));

        formGrid.add(new Label("Student ID:"), 0, 0);
        formGrid.add(studentIdField, 1, 0);

        formGrid.add(new Label("Full Name:"), 0, 1);
        formGrid.add(fullNameField, 1, 1);

        formGrid.add(new Label("Email:"), 0, 2);
        formGrid.add(emailField, 1, 2);

        formGrid.add(new Label("Program:"), 0, 3);
        formGrid.add(programField, 1, 3);

        studentIdField.setPromptText("Enter student ID");
        fullNameField.setPromptText("Enter full name");
        emailField.setPromptText("Enter email address");
        programField.setPromptText("Enter program");

        Button registerButton = new Button("Register");
        Button clearButton = new Button("Clear");

        registerButton.setOnAction(event -> registerStudent());
        clearButton.setOnAction(event -> clearForm());

        HBox actionButtons = new HBox(12, registerButton, clearButton);
        actionButtons.setAlignment(Pos.CENTER_RIGHT);

        VBox center = new VBox(20,
                new Label("Student Registration Form"),
                formGrid,
                actionButtons,
                statusLabel);
        center.setPadding(new Insets(25));
        center.setStyle("-fx-background-color: white;");

        root.setCenter(center);
    }

    private void registerStudent() {
        String studentId = studentIdField.getText().trim();
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        String program = programField.getText().trim();

        if (studentId.isEmpty() || fullName.isEmpty() || email.isEmpty()
                || program.isEmpty()) {
            statusLabel.setText("Error: Please complete all required fields.");
            statusLabel.setStyle("-fx-text-fill: #c0392b; -fx-font-weight: bold;");
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            statusLabel.setText("Error: Please enter a valid email address.");
            statusLabel.setStyle("-fx-text-fill: #c0392b; -fx-font-weight: bold;");
            return;
        }

        statusLabel.setText("Success: Student " + fullName
                + " has been registered.");
        statusLabel.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
    }

    private void clearForm() {
        studentIdField.clear();
        fullNameField.clear();
        emailField.clear();
        programField.clear();

        statusLabel.setText("Ready.");
        statusLabel.setStyle("-fx-text-fill: black;");
    }
}
