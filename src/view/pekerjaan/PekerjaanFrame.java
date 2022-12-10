package view.pekerjaan;

import javax.swing.*;
import dao.PekerjaanDao;
import model.Pekerjaan;
import java.util.*;

public class PekerjaanFrame extends JFrame {
    private List<Pekerjaan> pekerjaanList;
    private JTextField textFieldNama;
    PekerjaanTableModel tableModel;
    private PekerjaanDao pekerjaanDao;
    javax.swing.JTable table = new JTable();

    public PekerjaanFrame(PekerjaanDao pekerjaanDao) {
        this.pekerjaanDao = pekerjaanDao;
        this.pekerjaanList = pekerjaanDao.findAll();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 10);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        JButton button = new JButton("Simpan");
        button.setBounds(15, 100, 100, 40);

        JButton buttonDelete = new JButton("Hapus");
        buttonDelete.setBounds(130, 100, 100, 40);

        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 150, 350, 200);

        tableModel = new PekerjaanTableModel(pekerjaanList);
        table.setModel(tableModel);

        PekerjaanButtonSimpanActionListener actionListener = new PekerjaanButtonSimpanActionListener(this,
                pekerjaanDao);

        PekerjaanButtonDeleteActionListener deleteActionListener = new PekerjaanButtonDeleteActionListener(this,
                pekerjaanDao);

        button.addActionListener(actionListener);
        buttonDelete.addActionListener(deleteActionListener);
        this.add(button);
        this.add(buttonDelete);
        this.add(textFieldNama);
        this.add(labelInput);
        this.add(scrollableTable);

        this.setSize(700, 700);
        this.setLayout(null);
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public void addPekerjaan(Pekerjaan pekerjaan) {
        tableModel.add(pekerjaan);
        textFieldNama.setText("");
    }

    public String takePekerjaan() {
        int[] selection = table.getSelectedRows();
        for (int i = 0; i < selection.length; i++) {
            selection[i] = table.convertRowIndexToModel(selection[i]);
        }

        if (selection.length > 0) {
            String namaPekerjaan = (String) tableModel.getValueAt(selection[0], 1);
            return namaPekerjaan;
        } else {
            return "";
        }
    }

    public void deletePekerjaan() {
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
