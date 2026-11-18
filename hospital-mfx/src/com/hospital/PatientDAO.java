package com.hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

	private static final String URL =
	        "jdbc:mysql://localhost:3306/hospital_db?allowPublicKeyRetrieval=true&useSSL=false";

    private static final String USER = "root";
    private static final String PASSWORD = "YOUR_MYSQL_PASSWORD";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public boolean addPatient(Patient patient) throws SQLException {

        String sql = "INSERT INTO patients "
                + "(name, age, gender, phone, diagnosis, admission_date) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getGender());
            statement.setString(4, patient.getPhone());
            statement.setString(5, patient.getDiagnosis());
            statement.setDate(6, Date.valueOf(patient.getAdmissionDate()));

            return statement.executeUpdate() > 0;

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    public List<Patient> getAllPatients() throws SQLException {

        String sql = "SELECT patient_id, name, age, gender, phone, "
                + "diagnosis, admission_date "
                + "FROM patients ORDER BY patient_id";

        List<Patient> patients = new ArrayList<Patient>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                patients.add(mapRow(resultSet));
            }

        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return patients;
    }

    public List<Patient> searchPatients(String keyword) throws SQLException {

        String sql = "SELECT patient_id, name, age, gender, phone, "
                + "diagnosis, admission_date "
                + "FROM patients "
                + "WHERE name LIKE ? OR phone LIKE ? "
                + "ORDER BY patient_id";

        List<Patient> patients = new ArrayList<Patient>();
        String value = "%" + keyword + "%";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, value);
            statement.setString(2, value);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                patients.add(mapRow(resultSet));
            }

        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return patients;
    }

    public boolean updatePatient(Patient patient) throws SQLException {

        String sql = "UPDATE patients SET name = ?, age = ?, gender = ?, "
                + "phone = ?, diagnosis = ?, admission_date = ? "
                + "WHERE patient_id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getGender());
            statement.setString(4, patient.getPhone());
            statement.setString(5, patient.getDiagnosis());
            statement.setDate(6, Date.valueOf(patient.getAdmissionDate()));
            statement.setInt(7, patient.getPatientId());

            return statement.executeUpdate() > 0;

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    public boolean deletePatient(int patientId) throws SQLException {

        String sql = "DELETE FROM patients WHERE patient_id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1, patientId);

            return statement.executeUpdate() > 0;

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    private Patient mapRow(ResultSet resultSet) throws SQLException {

        int patientId = resultSet.getInt("patient_id");
        String name = resultSet.getString("name");
        int age = resultSet.getInt("age");
        String gender = resultSet.getString("gender");
        String phone = resultSet.getString("phone");
        String diagnosis = resultSet.getString("diagnosis");
        LocalDate admissionDate =
                resultSet.getDate("admission_date").toLocalDate();

        return new Patient(patientId, name, age, gender, phone,
                diagnosis, admissionDate);
    }
}