package view.pekerjaan;

import java.awt.event.*;
import java.util.UUID;
import javax.swing.JOptionPane;
import dao.PekerjaanDao;
import model.Pekerjaan;

public class PekerjaanButtonSimpanActionListener implements ActionListener {
    private PekerjaanFrame pekerjaanFrame;
    private PekerjaanDao pekerjaanDao;

    public PekerjaanButtonSimpanActionListener(PekerjaanFrame pekerjaanFrame, PekerjaanDao pekerjaanDao) {
        this.pekerjaanFrame = pekerjaanFrame;
        this.pekerjaanDao = pekerjaanDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = this.pekerjaanFrame.getNama();

        if (nama.trim().isEmpty()) {
            this.pekerjaanFrame.showAlert("Form tidak boleh ada yang kosong!");
        } else {
            int confirmation = JOptionPane.showConfirmDialog(this.pekerjaanFrame,
                    "Masukan Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                Pekerjaan pekerjaan = new Pekerjaan();
                pekerjaan.setId(UUID.randomUUID().toString());
                pekerjaan.setNama(nama);

                this.pekerjaanFrame.addPekerjaan(pekerjaan);
                this.pekerjaanDao.insert(pekerjaan);

            } else {
                JOptionPane.showMessageDialog(this.pekerjaanFrame, "Anda tidak memasukan data");
            }
        }
    }
}
