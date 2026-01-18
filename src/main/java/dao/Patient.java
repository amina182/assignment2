package dao;

import org.example.Db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Patient {

    public static void addPatient(String name, String surname, int age, String gender)
            throws SQLException {

        String sql = "INSERT INTO patient(name, surname, age, gender) VALUES (?, ?, ?, ?)";

        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setInt(3, age);
            ps.setString(4, gender);

            ps.executeUpdate();
        }
    }

    public static List<String> getAllPatients() throws SQLException {

        List<String> list = new ArrayList<>();
        String sql = "SELECT * FROM patient";

        try (Connection con = Db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " " +
                                rs.getString("surname") + " | age: " +
                                rs.getInt("age") + " | " +
                                rs.getString("gender")
                );
            }
        }
        return list;
    }

    public static void updatePatient(int id, int age) throws SQLException {

        String sql = "UPDATE patient SET age=? WHERE id=?";
        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, age);
            ps.setInt(2, id);

            ps.executeUpdate();
        }
    }

    public static void deletePatient(int id) throws SQLException {

        String sql = "DELETE FROM patient WHERE id=?";

        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
