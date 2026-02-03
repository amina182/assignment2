package org.example.dao;

import org.example.model.Patient;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PatientDao {

    private final DataSource dataSource;

    public PatientDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Patient> findAll() throws SQLException {
        String sql = "SELECT id, name, surname, age, gender, email FROM patient ORDER BY id";
        List<Patient> list = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    public Optional<Patient> findById(int id) throws SQLException {
        String sql = "SELECT id, name, surname, age, gender, email FROM patient WHERE id=?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(map(rs));
                }
                return Optional.empty();
            }
        }
    }

    public int create(Patient patient) throws SQLException {
        String sql = """
                INSERT INTO patient(name, surname, age, gender, email)
                VALUES (?, ?, ?, ?, ?)
                RETURNING id
                """;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, patient.getName());
            ps.setString(2, patient.getSurname());
            ps.setInt(3, patient.getAge());
            ps.setString(4, patient.getGender());
            ps.setString(5, patient.getEmail());

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    public boolean update(int id, Patient patient) throws SQLException {
        String sql = """
                UPDATE patient
                SET name=?, surname=?, age=?, gender=?, email=?
                WHERE id=?
                """;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, patient.getName());
            ps.setString(2, patient.getSurname());
            ps.setInt(3, patient.getAge());
            ps.setString(4, patient.getGender());
            ps.setString(5, patient.getEmail());
            ps.setInt(6, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM patient WHERE id=?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private Patient map(ResultSet rs) throws SQLException {
        return new Patient(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getInt("age"),
                rs.getString("gender"),
                rs.getString("email")
        );
    }
}
