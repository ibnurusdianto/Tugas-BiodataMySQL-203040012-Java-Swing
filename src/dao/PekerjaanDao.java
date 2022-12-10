package dao;

import db.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import model.Pekerjaan;

public class PekerjaanDao {
    public int insert(Pekerjaan pekerjaan) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("insert into pekerjaan (id, nama_pekerjaan) value (?, ?)");
            statement.setString(1, pekerjaan.getId());
            statement.setString(2, pekerjaan.getNama());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(Pekerjaan pekerjaan) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("update pekerjaan set nama_pekerjaan = ? where id = ?");
            statement.setString(1, pekerjaan.getNama());
            statement.setString(2, pekerjaan.getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(Pekerjaan pekerjaan) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "delete from pekerjaan where id = ?");
            statement.setString(1, pekerjaan.getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Pekerjaan> findAll() {
        List<Pekerjaan> list = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
                Statement statement = connection.createStatement();) {
            try (ResultSet resultSet = statement.executeQuery("select * from pekerjaan");) {
                // Retrieving the data
                while (resultSet.next()) {
                    Pekerjaan pekerjaan = new Pekerjaan();
                    pekerjaan.setId(resultSet.getString("id"));
                    pekerjaan.setNama(resultSet.getString("nama_pekerjaan"));
                    list.add(pekerjaan);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
