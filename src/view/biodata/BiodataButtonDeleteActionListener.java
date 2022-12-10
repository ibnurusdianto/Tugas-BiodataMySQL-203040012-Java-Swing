package view.biodata;

import java.awt.event.*;
import dao.BiodataDao;
import javax.swing.JOptionPane;
import model.*;

public class BiodataButtonDeleteActionListener implements ActionListener {
    private BiodataFrame biodataFrame;
    private BiodataDao biodataDao;

    public BiodataButtonDeleteActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao) {
        this.biodataFrame = biodataFrame;
        this.biodataDao = biodataDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int confirmation = JOptionPane.showConfirmDialog(this.biodataFrame,
                "Hapus Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            Biodata namaB = new Biodata();
            namaB.setId(this.biodataFrame.takeBiodata());

            if (namaB.getId() != "") {
                this.biodataFrame.deleteBiodata();
                biodataDao.delete(namaB);
            } else {
                this.biodataFrame.showAlert("Tolong pilih baris tabel yang mau dihapus");
            }

        } else {
            JOptionPane.showMessageDialog(this.biodataFrame, "Anda tidak menghapus data");
        }
    }
}
