package view.pekerjaan;

import java.awt.event.*;
import javax.swing.JOptionPane;
import dao.PekerjaanDao;
import model.Pekerjaan;

public class PekerjaanButtonDeleteActionListener implements ActionListener {
    private PekerjaanFrame pekerjaanFrame;
    private PekerjaanDao pekerjaanDao;

    public PekerjaanButtonDeleteActionListener(PekerjaanFrame pekerjaanFrame, PekerjaanDao pekerjaanDao) {
        this.pekerjaanFrame = pekerjaanFrame;
        this.pekerjaanDao = pekerjaanDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int confirmation = JOptionPane.showConfirmDialog(this.pekerjaanFrame,
                "Hapus Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            Pekerjaan namaP = new Pekerjaan();
            namaP.setId(this.pekerjaanFrame.takePekerjaan());

            if (namaP.getId() != "") {
                this.pekerjaanFrame.deletePekerjaan();
                pekerjaanDao.delete(namaP);
            } else {
                this.pekerjaanFrame.showAlert("Tolong pilih baris tabel yang mau dihapus");
            }

        } else {
            JOptionPane.showMessageDialog(this.pekerjaanFrame, "Anda tidak menghapus data");
        }
    }
}
