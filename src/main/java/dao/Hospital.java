package dao;

import org.example.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Hospital {

    public static void addHospital(String name, String address)
            throws SQLException {

        String sql = "INSERT INTO hospital(name, address) VALUES (?, ?)";

        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, address);

            ps.executeUpdate();
        }
    }
}

