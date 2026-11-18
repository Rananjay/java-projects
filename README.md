# Java Projects

Consolidated Java coursework and projects.

## Modules

| Module | Description | Original repository |
|---|---|---|
| [`student-management-assignment`](student-management-assignment/) | JavaFX student registration application | https://github.com/Rananjay/StudentManagementAssignment |
| [`hospital-mfx`](hospital-mfx/) | JavaFX hospital management application with MySQL | https://github.com/Rananjay/Hospital-MFX |
| [`maple-leaf-library-management-system`](maple-leaf-library-management-system/) | Java console library management demonstration | https://github.com/Rananjay/Maple_Leaf_Library_Management_System |

## Build

Requirements:

- Java 21 or newer
- Maven 3.8 or newer

Build all modules:

```bash
mvn clean package
```

Run the Student Management Assignment module:

```bash
mvn --projects student-management-assignment javafx:run
```

Run the Hospital MFX module:

```bash
mvn --projects hospital-mfx javafx:run
```

Run the Maple Leaf Library Management System:

```bash
mvn --projects maple-leaf-library-management-system exec:java
```

## Repository layout

```text
java-projects/
├── pom.xml
├── student-management-assignment/
├── hospital-mfx/
├── maple-leaf-library-management-system/
└── README.md
```

## Migration status

- [x] `StudentManagementAssignment`
- [x] `Hospital-MFX`
- [x] `Maple_Leaf_Library_Management_System`
- [ ] `Hospital-Management`
- [ ] `java-bank-account-assignment`
- [ ] `Lab2_Java_Diceroller`
- [ ] `java-development-assignment`
