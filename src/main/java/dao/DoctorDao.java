package dao;

import org.example.Db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDao {

    public static void addDoctor(String name, String surname, String specialization, int age)
            throws SQLException {

        String sql = "INSERT INTO doctor(name, surname, specialization,age) VALUES (?, ?, ?,?)";

        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setString(3, specialization);
            ps.setInt(4,age);

            ps.executeUpdate();
        }
    }

    public static List<String> getAllDoctors() throws SQLException {

        List<String> list = new ArrayList<>();
        String sql = "SELECT * FROM doctor";

        try (Connection con = Db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " " +
                                rs.getString("surname") + " | " +
                                rs.getString("specialization")
                );
            }
        }
        return list;
    }
}

