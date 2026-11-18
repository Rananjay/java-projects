# Maple Leaf Library Management System

A Java console application demonstrating object-oriented library-management concepts.

## Features

- Member registration with overloaded methods and validation
- Library staff hierarchy: librarian, senior librarian, and library assistant
- Late-fee calculations with method overloading
- Monthly compensation calculations
- Static person and staff counters
- Runtime polymorphism through `LibraryStaff` references
- `Reportable` and `Reservable` interface demonstrations
- Outstanding-fee tracking and clearing

## Run the application

From the repository root:

```bash
mvn --projects maple-leaf-library-management-system exec:java
```

Or from this module:

```bash
mvn exec:java
```

## Run the assignment tests

The included `AssignmentTest` uses a custom `main`-based test runner rather than JUnit.

```bash
mvn --projects maple-leaf-library-management-system test
```

## Original repository

This project was migrated from:

https://github.com/Rananjay/Maple_Leaf_Library_Management_System
