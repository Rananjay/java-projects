package com.hospital;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PatientController {

    private PatientDAO patientDAO = new PatientDAO();

    private ObservableList<Patient> patientData =
            FXCollections.observableArrayList();

    private TextField idField = new TextField();
    private TextField nameField = new TextField();
    private TextField ageField = new TextField();
    private ComboBox<String> genderBox = new ComboBox<String>();
    private TextField phoneField = new TextField();
    private TextField diagnosisField = new TextField();
    private DatePicker admissionDatePicker = new DatePicker();

    private TableView<Patient> patientTable = new TableView<Patient>();
    private TextField searchField = new TextField();
    private Label statusLabel = new Label("Ready.");

    private Patient selectedPatient;

    public BorderPane getRoot() {

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));

        idField.setEditable(false);

        genderBox.getItems().addAll(
                "Female", "Male", "Non-binary", "Prefer not to say");

        root.setTop(createHeader());
        root.setCenter(createMainContent());
        root.setBottom(createFooter());

        setupTable();
        setupTableSelection();

        return root;
    }

    private VBox createHeader() {

        Label title = new Label("Hospital Management System");
        Label subtitle = new Label("Patient Registration and Records");

        VBox header = new VBox(5, title, subtitle);
        header.setAlignment(Pos.CENTER);

        return header;
    }

    private HBox createMainContent() {

        VBox form = createForm();
        VBox table = createTable();

        HBox content = new HBox(30, form, table);

        return content;
    }

    private VBox createForm() {

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Patient ID:"), 0, 0);
        grid.add(idField, 1, 0);

        grid.add(new Label("Full Name:"), 0, 1);
        grid.add(nameField, 1, 1);

        grid.add(new Label("Age:"), 0, 2);
        grid.add(ageField, 1, 2);

        grid.add(new Label("Gender:"), 0, 3);
        grid.add(genderBox, 1, 3);

        grid.add(new Label("Phone:"), 0, 4);
        grid.add(phoneField, 1, 4);

        grid.add(new Label("Diagnosis / Reason:"), 0, 5);
        grid.add(diagnosisField, 1, 5);

        grid.add(new Label("Admission Date:"), 0, 6);
        grid.add(admissionDatePicker, 1, 6);

        Button addButton = new Button("Add Patient");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button clearButton = new Button("Clear");

        addButton.setOnAction(event -> addPatient());
        updateButton.setOnAction(event -> updatePatient());
        deleteButton.setOnAction(event -> deletePatient());
        clearButton.setOnAction(event -> clearForm());

        HBox buttons = new HBox(10, addButton, updateButton,
                deleteButton, clearButton);

        VBox form = new VBox(15, new Label("Patient Details"),
                grid, buttons);

        return form;
    }

    private VBox createTable() {

        searchField.setPromptText("Search by name or phone");

        Button searchButton = new Button("Search");
        Button showAllButton = new Button("Show All");

        searchButton.setOnAction(event -> searchPatients());
        showAllButton.setOnAction(event -> loadPatients());

        HBox searchRow = new HBox(10, searchField,
                searchButton, showAllButton);

        VBox table = new VBox(10, new Label("Patient Records"),
                searchRow, patientTable);

        return table;
    }

    private HBox createFooter() {

        HBox footer = new HBox(statusLabel);

        return footer;
    }

    private void setupTable() {

        TableColumn<Patient, Integer> idColumn =
                new TableColumn<Patient, Integer>("ID");

        idColumn.setCellValueFactory(data ->
                new SimpleIntegerProperty(
                        data.getValue().getPatientId()).asObject());

        TableColumn<Patient, String> nameColumn =
                new TableColumn<Patient, String>("Name");

        nameColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getName()));

        TableColumn<Patient, Integer> ageColumn =
                new TableColumn<Patient, Integer>("Age");

        ageColumn.setCellValueFactory(data ->
                new SimpleIntegerProperty(
                        data.getValue().getAge()).asObject());

        TableColumn<Patient, String> genderColumn =
                new TableColumn<Patient, String>("Gender");

        genderColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getGender()));

        TableColumn<Patient, String> phoneColumn =
                new TableColumn<Patient, String>("Phone");

        phoneColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getPhone()));

        TableColumn<Patient, String> diagnosisColumn =
                new TableColumn<Patient, String>("Diagnosis");

        diagnosisColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getDiagnosis()));

        TableColumn<Patient, LocalDate> dateColumn =
                new TableColumn<Patient, LocalDate>("Admission Date");

        dateColumn.setCellValueFactory(data ->
                new SimpleObjectProperty<LocalDate>(
                        data.getValue().getAdmissionDate()));

        patientTable.getColumns().addAll(idColumn, nameColumn,
                ageColumn, genderColumn, phoneColumn,
                diagnosisColumn, dateColumn);

        patientTable.setItems(patientData);
    }

    private void setupTableSelection() {

        patientTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldPatient, newPatient) ->
                        populateForm(newPatient));
    }

    private void populateForm(Patient patient) {

        selectedPatient = patient;

        if (patient == null) {
            clearForm();
            return;
        }

        idField.setText(String.valueOf(patient.getPatientId()));
        nameField.setText(patient.getName());
        ageField.setText(String.valueOf(patient.getAge()));
        genderBox.setValue(patient.getGender());
        phoneField.setText(patient.getPhone());
        diagnosisField.setText(patient.getDiagnosis());
        admissionDatePicker.setValue(patient.getAdmissionDate());
    }

    private void addPatient() {

        if (validateForm() == false) {
            return;
        }

        Patient patient = new Patient(
                0,
                nameField.getText().trim(),
                Integer.parseInt(ageField.getText().trim()),
                genderBox.getValue(),
                phoneField.getText().trim(),
                diagnosisField.getText().trim(),
                admissionDatePicker.getValue());

        try {
            patientDAO.addPatient(patient);
            setStatus("Patient added successfully.");
            loadPatients();
            clearForm();

        } catch (SQLException exception) {
            setStatus("Database error: " + exception.getMessage());
        }
    }

    private void updatePatient() {

        if (selectedPatient == null) {
            setStatus("Please select a patient first.");
            return;
        }

        if (validateForm() == false) {
            return;
        }

        Patient patient = new Patient(
                selectedPatient.getPatientId(),
                nameField.getText().trim(),
                Integer.parseInt(ageField.getText().trim()),
                genderBox.getValue(),
                phoneField.getText().trim(),
                diagnosisField.getText().trim(),
                admissionDatePicker.getValue());

        try {
            patientDAO.updatePatient(patient);
            setStatus("Patient updated successfully.");
            loadPatients();
            clearForm();

        } catch (SQLException exception) {
            setStatus("Database error: " + exception.getMessage());
        }
    }

    private void deletePatient() {

        if (selectedPatient == null) {
            setStatus("Please select a patient first.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Delete this patient?");
        alert.setContentText("Patient ID: "
                + selectedPatient.getPatientId());

        alert.showAndWait().ifPresent(response -> {

            if (response == ButtonType.OK) {

                try {
                    patientDAO.deletePatient(
                            selectedPatient.getPatientId());

                    setStatus("Patient deleted successfully.");
                    loadPatients();
                    clearForm();

                } catch (SQLException exception) {
                    setStatus("Database error: "
                            + exception.getMessage());
                }
            }
        });
    }

    private void searchPatients() {

        String keyword = searchField.getText().trim();

        if (keyword.isEmpty()) {
            setStatus("Enter a name or phone number.");
            return;
        }

        try {
            List<Patient> results =
                    patientDAO.searchPatients(keyword);

            patientData.clear();
            patientData.addAll(results);

            setStatus("Search complete.");

        } catch (SQLException exception) {
            setStatus("Database error: " + exception.getMessage());
        }
    }

    public void loadPatients() {

        try {
            List<Patient> patients =
                    patientDAO.getAllPatients();

            patientData.clear();
            patientData.addAll(patients);

            setStatus("Patient records loaded.");

        } catch (SQLException exception) {
            setStatus("Database error: " + exception.getMessage());
        }
    }

    private boolean validateForm() {

        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();
        String gender = genderBox.getValue();
        String phone = phoneField.getText().trim();
        String diagnosis = diagnosisField.getText().trim();
        LocalDate admissionDate = admissionDatePicker.getValue();

        if (name.isEmpty() || ageText.isEmpty() || gender == null
                || phone.isEmpty() || diagnosis.isEmpty()
                || admissionDate == null) {

            setStatus("Please complete all fields.");
            return false;
        }

        int age = 0;

        try {
            age = Integer.parseInt(ageText);

        } catch (NumberFormatException exception) {
            setStatus("Age must be a whole number.");
            return false;
        }

        if (age < 0 || age > 125) {
            setStatus("Age must be between 0 and 125.");
            return false;
        }

        if (admissionDate.isAfter(LocalDate.now())) {
            setStatus("Admission date cannot be in the future.");
            return false;
        }

        return true;
    }

    private void clearForm() {

        selectedPatient = null;

        idField.clear();
        nameField.clear();
        ageField.clear();
        genderBox.setValue(null);
        phoneField.clear();
        diagnosisField.clear();
        admissionDatePicker.setValue(null);

        patientTable.getSelectionModel().clearSelection();
        setStatus("Form cleared.");
    }

    private void setStatus(String message) {
        statusLabel.setText(message);
    }
}