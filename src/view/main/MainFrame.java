package view.main;

import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import dao.BiodataDao;
import dao.PekerjaanDao;
import view.biodata.BiodataFrame;
import view.pekerjaan.PekerjaanFrame;

public class MainFrame extends JFrame {
    private PekerjaanFrame pekerjaanFrame;
    private BiodataFrame biodataFrame;
    private JButton buttonPekerjaan;
    private JButton buttonBiodata;
    private PekerjaanDao pekerjaanDao;
    private BiodataDao biodataDao;

    public MainFrame() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int exit = JOptionPane.showConfirmDialog(null,
                        "Apakah anda yakin ingin keluar?", "Keluar",
                        JOptionPane.YES_NO_OPTION);

                if (exit == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    biodataFrame.dispose();
                    pekerjaanFrame.dispose();
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        this.setSize(700, 700);

        this.pekerjaanDao = new PekerjaanDao();
        this.biodataDao = new BiodataDao();

        this.pekerjaanFrame = new PekerjaanFrame(pekerjaanDao);
        this.biodataFrame = new BiodataFrame(biodataDao, pekerjaanDao);

        this.setLayout(new FlowLayout());
        MainButtonActionListener actionListener = new MainButtonActionListener(this);

        this.buttonPekerjaan = new JButton("Pekerjaan");
        this.buttonBiodata = new JButton("Biodata");

        this.buttonPekerjaan.addActionListener(actionListener);
        this.buttonBiodata.addActionListener(actionListener);

        this.add(buttonPekerjaan);
        this.add(buttonBiodata);
    }

    public JButton getButtonPekerjaan() {
        return buttonPekerjaan;
    }

    public JButton getButtonBiodata() {
        return buttonBiodata;
    }

    public void showPekerjaanFrame() {
        if (pekerjaanFrame == null) {
            pekerjaanFrame = new PekerjaanFrame(pekerjaanDao);
        }
        pekerjaanFrame.setVisible(true);
    }

    public void showBiodataFrame() {
        if (biodataFrame == null) {
            biodataFrame = new BiodataFrame(biodataDao, pekerjaanDao);
        }
        biodataFrame.populateComboJenis();
        biodataFrame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame f = new MainFrame();
                f.setVisible(true);
            }
        });
    }
}
