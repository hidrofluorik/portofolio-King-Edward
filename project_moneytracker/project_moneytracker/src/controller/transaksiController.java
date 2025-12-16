package controller;

import view.transaksiview;
import dao.transaksiDao;
import model.transaction;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Date;

/**
 *
 * @author Acer
 */
public class transaksiController {
    private transaksiview view;
    private transaksiDao dao;
    private String currentUser;
    private DefaultTableModel incomeTableModel;
    private DefaultTableModel outcomeTableModel;
    
    public transaksiController(transaksiview view, String username) {
        this.view = view;
        this.currentUser = username;
        this.dao = new transaksiDao();
        initializeView();
        loadTransactions();
    }
    
    private void initializeView() {
        String[] columns = {"ID", "Kategori", "Amount", "Date", "Keterangan"};
        incomeTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        outcomeTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        view.getIncomeTable().setModel(incomeTableModel);
        view.getOutcomeTable().setModel(outcomeTableModel);
        
        // Add table selection listeners
        view.getIncomeTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadSelectedTransaction(view.getIncomeTable(), "Income");
            }
        });
        
        view.getOutcomeTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadSelectedTransaction(view.getOutcomeTable(), "Outcome");
            }
        });
    }
    
    public void saveTransaction() {
        try {
            if (view.getKategoriField().getSelectedItem() == null || 
                view.getAmountField().getText().trim().isEmpty() ||
                view.getDateField().getDate() == null) {
                JOptionPane.showMessageDialog(view, "Semua field harus diisi!");
                return;
            }
            
            transaction t = new transaction(
                0,
                currentUser,
                view.getKategoriField().getSelectedItem().toString(),
                view.getTypeField().getSelectedItem().toString(),
                view.getDateField().getDate(),
                Double.parseDouble(view.getAmountField().getText()),
                view.getKeteranganField().getText()
            );
            
            if (dao.insert(t)) {
                JOptionPane.showMessageDialog(view, "Transaksi berhasil disimpan!");
                clearForm();
                loadTransactions();
            } else {
                JOptionPane.showMessageDialog(view, "Gagal menyimpan transaksi!");
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Amount harus berupa angka!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
        }
    }
    
    public void updateTransaction() {
        try {
            String idText = view.getIdField().getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Pilih transaksi yang akan diupdate!");
                return;
            }
            
            int id = Integer.parseInt(idText);
            
            transaction t = new transaction(
                id,
                currentUser,
                view.getKategoriField().getSelectedItem().toString(),
                view.getTypeField().getSelectedItem().toString(),
                view.getDateField().getDate(),
                Double.parseDouble(view.getAmountField().getText()),
                view.getKeteranganField().getText()
            );
            
            if (dao.update(t)) {
                JOptionPane.showMessageDialog(view, "Transaksi berhasil diupdate!");
                clearForm();
                loadTransactions();
            } else {
                JOptionPane.showMessageDialog(view, "Gagal mengupdate transaksi!");
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "ID dan Amount harus berupa angka!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
        }
    }
    
    public void deleteTransaction() {
        try {
            String idText = view.getIdField().getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Pilih transaksi yang akan dihapus!");
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(
                view, 
                "Apakah Anda yakin ingin menghapus transaksi ini?", 
                "Konfirmasi Hapus", 
                JOptionPane.YES_NO_OPTION
            );
            
            if (confirm == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(idText);
                
                if (dao.delete(id)) {
                    JOptionPane.showMessageDialog(view, "Transaksi berhasil dihapus!");
                    clearForm();
                    loadTransactions();
                } else {
                    JOptionPane.showMessageDialog(view, "Gagal menghapus transaksi!");
                }
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "ID harus berupa angka!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
        }
    }
    
    private void loadTransactions() {
        incomeTableModel.setRowCount(0);
        outcomeTableModel.setRowCount(0);
        
        List<transaction> transactions = dao.getAllTransactions(currentUser);
        
        for (transaction t : transactions) {
            Object[] row = {
                t.getId(),
                t.getKategori(),
                t.getAmount(),
                t.getDate(),
                t.getKeterangan()
            };
            
            if ("Income".equals(t.getType())) {
                incomeTableModel.addRow(row);
            } else if ("Outcome".equals(t.getType())) {
                outcomeTableModel.addRow(row);
            }
        }
    }
    
    private void loadSelectedTransaction(javax.swing.JTable table, String expectedType) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (Integer) table.getValueAt(selectedRow, 0);
            transaction t = dao.getTransactionById(id);
            
            if (t != null && expectedType.equals(t.getType())) {
                view.getIdField().setText(String.valueOf(t.getId()));
                view.getKategoriField().setSelectedItem(t.getKategori());
                view.getTypeField().setSelectedItem(t.getType());
                view.getAmountField().setText(String.valueOf(t.getAmount()));
                view.getDateField().setDate(t.getDate());
                view.getKeteranganField().setText(t.getKeterangan());
            }
        }
    }
    
    private void clearForm() {
        view.getIdField().setText("");
        view.getKategoriField().setSelectedIndex(0);
        view.getTypeField().setSelectedIndex(0);
        view.getAmountField().setText("");
        view.getDateField().setDate(new Date());
        view.getKeteranganField().setText("");
    }
}