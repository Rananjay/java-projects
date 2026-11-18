# Java Projects

Consolidated Java coursework and projects.

## Modules

| Module | Description | Original repository |
|---|---|---|
| [`student-management-assignment`](student-management-assignment/) | JavaFX student registration application | https://github.com/Rananjay/StudentManagementAssignment |

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

## Repository layout

```text
java-projects/
├── pom.xml
├── student-management-assignment/
│   ├── pom.xml
│   └── src/
└── README.md
```

## Migration status

- [x] `StudentManagementAssignment`
- [ ] `Hospital-MFX`
- [ ] `Maple_Leaf_Library_Management_System`
- [ ] `Hospital-Management`
- [ ] `java-bank-account-assignment`
- [ ] `Lab2_Java_Diceroller`
- [ ] `java-development-assignment`
