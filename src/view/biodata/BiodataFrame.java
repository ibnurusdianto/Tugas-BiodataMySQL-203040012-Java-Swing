package view.biodata;

import javax.swing.*;
import dao.BiodataDao;
import dao.PekerjaanDao;
import model.*;
import java.util.*;

public class BiodataFrame extends JFrame {
    private List<Pekerjaan> pekerjaanList;
    private List<Biodata> biodataList;
    private JTextField textFieldNama;
    private JTextField textFieldNoTelp;
    private JTextArea textAreaAlamat;
    private JRadioButton radioButtonL;
    private JRadioButton radioButtonP;
    private BiodataTableModel tableModel;
    private JComboBox<String> comboJenis;
    private PekerjaanDao pekerjaanDao;
    private BiodataDao biodataDao;
    javax.swing.JTable table = new JTable();

    public BiodataFrame(BiodataDao biodataDao, PekerjaanDao pekerjaanDao) {
        this.biodataDao = biodataDao;
        this.pekerjaanDao = pekerjaanDao;
        this.pekerjaanList = pekerjaanDao.findAll();
        this.biodataList = biodataDao.findAll();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel labelInputNama = new JLabel("Nama:");
        labelInputNama.setBounds(15, 40, 350, 30);
        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 70, 350, 30);

        JLabel labelInputNoTelp = new JLabel("No Telp:");
        labelInputNoTelp.setBounds(15, 100, 350, 30);
        textFieldNoTelp = new JTextField();
        textFieldNoTelp.setBounds(15, 130, 350, 30);

        JLabel labelInputAlamat = new JLabel("Alamat:");
        labelInputAlamat.setBounds(15, 160, 350, 30);
        textAreaAlamat = new JTextArea();
        textAreaAlamat.setBounds(15, 190, 350, 60);

        JLabel labelJenisKelamin = new JLabel("Jenis Kelamin:");
        labelJenisKelamin.setBounds(15, 250, 350, 30);
        radioButtonL = new JRadioButton("Laki-Laki", true);
        radioButtonL.setBounds(15, 280, 350, 30);
        radioButtonP = new JRadioButton("Perempuan");
        radioButtonP.setBounds(15, 310, 350, 30);

        JLabel labelJenis = new JLabel("Pekerjaan:");
        labelJenis.setBounds(15, 340, 350, 30);
        comboJenis = new JComboBox<>();
        comboJenis.setBounds(15, 370, 150, 30);

        JButton button = new JButton("Simpan");
        button.setBounds(15, 410, 100, 40);

        JButton buttonDelete = new JButton("Hapus");
        buttonDelete.setBounds(130, 410, 100, 40);

        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 460, 500, 200);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButtonL);
        bg.add(radioButtonP);

        tableModel = new BiodataTableModel(biodataList);
        table.setModel(tableModel);

        BiodataButtonSimpanActionListener actionListener = new BiodataButtonSimpanActionListener(this, biodataDao);

        BiodataButtonDeleteActionListener deleteActionListener = new BiodataButtonDeleteActionListener(this,
                biodataDao);

        button.addActionListener(actionListener);
        buttonDelete.addActionListener(deleteActionListener);

        this.add(button);
        this.add(buttonDelete);
        this.add(textFieldNama);
        this.add(textFieldNoTelp);
        this.add(textAreaAlamat);
        this.add(radioButtonL);
        this.add(radioButtonP);
        this.add(labelInputNama);
        this.add(labelInputNoTelp);
        this.add(labelInputAlamat);
        this.add(labelJenisKelamin);
        this.add(labelJenis);
        this.add(comboJenis);
        this.add(scrollableTable);

        this.setSize(700, 700);
        this.setLayout(null);
    }

    public void populateComboJenis() {
        this.pekerjaanList = this.pekerjaanDao.findAll();
        comboJenis.removeAllItems();
        for (Pekerjaan pekerjaan : this.pekerjaanList) {
            comboJenis.addItem(pekerjaan.getNama());
        }
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public String getNoTelp() {
        return textFieldNoTelp.getText();
    }

    public String getAlamat() {
        return textAreaAlamat.getText();
    }

    public String getJenisKelamin() {
        String jenisKelamin = "";

        if (radioButtonL.isSelected()) {
            jenisKelamin = radioButtonL.getText();
        }
        if (radioButtonP.isSelected()) {
            jenisKelamin = radioButtonP.getText();
        }
        return jenisKelamin;
    }

    public Pekerjaan getPekerjaan() {
        return pekerjaanList.get(comboJenis.getSelectedIndex());
    }

    public void addBiodata(Biodata biodata) {
        tableModel.add(biodata);
        textFieldNama.setText("");
        textFieldNoTelp.setText("");
        textAreaAlamat.setText("");
    }

    public String takeBiodata() {
        int[] selection = table.getSelectedRows();
        for (int i = 0; i < selection.length; i++) {
            selection[i] = table.convertRowIndexToModel(selection[i]);
        }

        if (selection.length > 0) {
            String namaBiodata = (String) tableModel.getValueAt(selection[0], 5);
            return namaBiodata;
        } else {
            return "";
        }
    }

    public void deleteBiodata() {
        int[] selection = table.getSelectedRows();
        for (int i = 0; i < selection.length; i++) {
            selection[i] = table.convertRowIndexToModel(selection[i]);
        }
        tableModel.remove(selection[0]);
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }
}
