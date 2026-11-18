# Hospital MFX

A JavaFX hospital management application for patient registration and records, backed by MySQL.

## Features

- Add, update, delete, and view patients
- Search patients by name or phone number
- Patient table with ID, name, age, gender, phone, diagnosis, and admission date
- Form validation for required fields, age range, and future admission dates

## Database setup

1. Install and start MySQL.
2. Create the database and table:

```bash
mysql -u root -p < database.sql
```

3. Set database credentials before running the application:

```bash
export DB_USER=your_mysql_user
export DB_PASSWORD=your_mysql_password
```

On Windows PowerShell:

```powershell
$env:DB_USER = "your_mysql_user"
$env:DB_PASSWORD = "your_mysql_password"
```

## Run

From the repository root:

```bash
mvn --projects hospital-mfx javafx:run
```

Or from this module:

```bash
mvn javafx:run
```

## Database connection

The application connects to:

```text
jdbc:mysql://localhost:3306/hospital_db
```

Credentials are read from the `DB_USER` and `DB_PASSWORD` environment variables.

## Original repository

This project was migrated from:

https://github.com/Rananjay/Hospital-MFX
