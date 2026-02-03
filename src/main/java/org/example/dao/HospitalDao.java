package org.example.dao;

import org.example.model.Hospital;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class HospitalDao {

    private final DataSource dataSource;

    public HospitalDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Hospital> findAll() throws SQLException {
        String sql = "SELECT id, name, address FROM hospital ORDER BY id";
        List<Hospital> list = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    public Optional<Hospital> findById(int id) throws SQLException {
        String sql = "SELECT id, name, address FROM hospital WHERE id=?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(map(rs));
                return Optional.empty();
            }
        }
    }

    public int create(Hospital h) throws SQLException {
        String sql = "INSERT INTO hospital(name, address) VALUES (?, ?) RETURNING id";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, h.getName());
            ps.setString(2, h.getAddress());

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    public boolean update(int id, Hospital h) throws SQLException {
        String sql = "UPDATE hospital SET name=?, address=? WHERE id=?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, h.getName());
            ps.setString(2, h.getAddress());
            ps.setInt(3, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM hospital WHERE id=?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private Hospital map(ResultSet rs) throws SQLException {
        return new Hospital(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("address")
        );
    }
}
