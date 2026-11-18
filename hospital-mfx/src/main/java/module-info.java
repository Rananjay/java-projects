module HospitalMFX {
    requires javafx.controls;
    requires java.sql;

    opens com.hospital to javafx.graphics, javafx.fxml;
}
