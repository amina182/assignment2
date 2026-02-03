package org.example.dao;

import org.example.model.Doctor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DoctorDao {

    private final DataSource dataSource;

    public DoctorDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Doctor> findAll() throws SQLException {
        String sql = "SELECT id, name, surname, specialization, age FROM doctor ORDER BY id";
        List<Doctor> list = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    public Optional<Doctor> findById(int id) throws SQLException {
        String sql = "SELECT id, name, surname, specialization, age FROM doctor WHERE id=?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(map(rs));
                return Optional.empty();
            }
        }
    }

    public int create(Doctor d) throws SQLException {
        String sql = """
                INSERT INTO doctor(name, surname, specialization, age)
                VALUES (?, ?, ?, ?)
                RETURNING id
                """;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, d.getName());
            ps.setString(2, d.getSurname());
            ps.setString(3, d.getSpecialization());
            ps.setInt(4, d.getAge());

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    public boolean update(int id, Doctor d) throws SQLException {
        String sql = """
                UPDATE doctor
                SET name=?, surname=?, specialization=?, age=?
                WHERE id=?
                """;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, d.getName());
            ps.setString(2, d.getSurname());
            ps.setString(3, d.getSpecialization());
            ps.setInt(4, d.getAge());
            ps.setInt(5, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM doctor WHERE id=?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private Doctor map(ResultSet rs) throws SQLException {
        return new Doctor(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("specialization"),
                rs.getInt("age")
        );
    }
}
