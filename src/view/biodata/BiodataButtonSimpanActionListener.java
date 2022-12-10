package view.biodata;

import java.awt.event.*;
import dao.BiodataDao;
import java.util.UUID;
import javax.swing.JOptionPane;
import model.*;

public class BiodataButtonSimpanActionListener implements ActionListener {
    private BiodataFrame biodataFrame;
    private BiodataDao biodataDao;

    public BiodataButtonSimpanActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao) {
        this.biodataFrame = biodataFrame;
        this.biodataDao = biodataDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = this.biodataFrame.getNama();
        String noTelp = this.biodataFrame.getNoTelp();
        String alamat = this.biodataFrame.getAlamat();
        String jenisKelamin = this.biodataFrame.getJenisKelamin();
        Pekerjaan pekerjaan = this.biodataFrame.getPekerjaan();

        if (nama.trim().isEmpty() || noTelp.trim().isEmpty() || alamat.trim().isEmpty()) {
            this.biodataFrame.showAlert("Form tidak boleh ada yang kosong!");
        } else if (!noTelp.matches("[0-9]+")) {
            this.biodataFrame.showAlert("No Telepon harus berisi angka saja!");
        } else {
            int confirmation = JOptionPane.showConfirmDialog(this.biodataFrame,
                    "Masukan Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                Biodata biodata = new Biodata();
                biodata.setId(UUID.randomUUID().toString());
                biodata.setNama(nama);
                biodata.setNoTelp(noTelp);
                biodata.setAlamat(alamat);
                biodata.setJenisKelamin(jenisKelamin);
                biodata.setPekerjaan(pekerjaan);

                this.biodataFrame.addBiodata(biodata);
                this.biodataDao.insert(biodata);
            } else {
                JOptionPane.showMessageDialog(this.biodataFrame, "Anda tidak memasukan data");
            }
        }
    }

}
