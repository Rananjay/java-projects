# Hospital Management System (JavaFX)

Migrated from `Hospital-MFX`.

## Overview

A JavaFX desktop application for managing patient records. It provides a patient registration interface with add, update, delete, search, and listing functionality, backed by MySQL through JDBC.

## Requirements

- Java 25 or compatible JDK
- JavaFX SDK
- MySQL Server
- MySQL Connector/J

## Database setup

1. Create the database and table:

```bash
mysql -u root -p < database.sql
```

2. Update the database connection settings in `src/com/hospital/PatientDAO.java`.

The source currently uses:

```java
jdbc:mysql://localhost:3306/hospital_db?allowPublicKeyRetrieval=true&useSSL=false
```

It also contains a placeholder password, `YOUR_MYSQL_PASSWORD`. Replace it with a local development credential or use an environment variable; do not commit a real password.

## Run

This project was configured for Eclipse with local JavaFX SDK paths in `.classpath`. Open it in Eclipse with a compatible JavaFX SDK installed, or run it using your local JavaFX module path.

## Files

- `src/com/hospital/App.java` — JavaFX application entry point
- `src/com/hospital/Patient.java` — patient model
- `src/com/hospital/PatientController.java` — JavaFX UI and patient workflow
- `src/com/hospital/PatientDAO.java` — MySQL data-access layer
- `src/application/application.css` — JavaFX stylesheet
- `database.sql` — MySQL database and table setup
- `lib/` — project dependencies
