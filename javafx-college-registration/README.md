# JavaFX Lab: Layout Panes

A beginner JavaFX application that demonstrates `BorderPane`, `GridPane`, `HBox`, and `VBox` through a College Student Registration screen.

## Requirements

- Java 25 or compatible JDK
- Maven

## Run

```bash
mvn javafx:run
```

## Layout structure

- `BorderPane` — root layout
- `HBox` — top application title
- `VBox` — left navigation menu
- `VBox` — centre registration form
- `GridPane` — Student ID, Full Name, Email, and Program fields
- `HBox` — Register and Clear buttons

## Features

- Required-field validation
- Basic email validation
- Confirmation and error status messages
- Clear form functionality
- Java-code UI construction with no FXML
