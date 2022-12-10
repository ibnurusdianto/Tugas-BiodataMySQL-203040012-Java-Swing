package dao;

import db.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import model.Biodata;
import model.Pekerjaan;

public class BiodataDao {
    public int insert(Biodata biodata) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                            "insert into biodata (id, nama, pekerjaan_id, no_telp, alamat, jenis_kelamin) value (?, ?, ?, ?, ?, ?)");
            statement.setString(1, biodata.getId());
            statement.setString(2, biodata.getNama());
            statement.setString(3, biodata.getPekerjaan().getId());
            statement.setString(4, biodata.getNoTelp());
            statement.setString(5, biodata.getAlamat());
            statement.setString(6, biodata.getJenisKelamin());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(Biodata biodata) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                            "update biodata set nama = ?, pekerjaan_id = ?, no_telp = ?, alamat = ?, Jenis_kelamin = ?, where id = ?");
            statement.setString(1, biodata.getNama());
            statement.setString(2, biodata.getPekerjaan().getId());
            statement.setString(3, biodata.getNoTelp());
            statement.setString(4, biodata.getAlamat());
            statement.setString(5, biodata.getJenisKelamin());
            statement.setString(6, biodata.getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(Biodata biodata) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("delete from biodata where id = ?");
            statement.setString(1, biodata.getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Biodata> findAll() {
        List<Biodata> list = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
                Statement statement = connection.createStatement();) {
            try (ResultSet resultSet = statement.executeQuery("select biodata.*, pekerjaan.* " 
            + "from biodata join pekerjaan on pekerjaan.id = biodata.pekerjaan_id");) {
                // Retrieving the data
                while (resultSet.next()) {
                    Biodata biodata = new Biodata();
                    biodata.setId(resultSet.getString("id"));
                    biodata.setNama(resultSet.getString("nama"));
                    biodata.setNoTelp(resultSet.getString("no_telp"));
                    biodata.setAlamat(resultSet.getString("alamat"));
                    biodata.setJenisKelamin(resultSet.getString("jenis_kelamin"));

                    Pekerjaan pekerjaan = new Pekerjaan();
                    pekerjaan.setId(resultSet.getString("biodata.pekerjaan_id"));
                    pekerjaan.setNama(resultSet.getString("pekerjaan.nama_pekerjaan"));

                    biodata.setPekerjaan(pekerjaan);

                    list.add(biodata);
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
