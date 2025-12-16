package controller;

import view.mainview;
import view.transaksiview;
import dao.transaksiDao;
import model.transaction;
import java.util.List;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class mainController {
    private mainview view;
    private transaksiDao dao;
    private String currentUser;
    
    public mainController(mainview view, String username) {
        this.view = view;
        this.currentUser = username;
        this.dao = new transaksiDao();
        initializeView();
    }
    
    private void initializeView() {
        Calendar cal = Calendar.getInstance();
        view.getMonthChooser().setMonth(cal.get(Calendar.MONTH));
     
        updateSummary();
        
        view.getMonthChooser().addPropertyChangeListener(e -> {
            if ("month".equals(e.getPropertyName())) {
                updateSummary();
            }
        });
    }
    
    public void updateSummary() {
        int selectedMonth = view.getMonthChooser().getMonth();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        
        List<transaction> transactions = dao.getTransactionsByMonth(currentUser, selectedMonth + 1, currentYear);
        
        double totalIncome = 0;
        double totalOutcome = 0;
        
        for (transaction t : transactions) {
            if ("Income".equals(t.getType())) {
                totalIncome += t.getAmount();
            } else if ("Outcome".equals(t.getType())) {
                totalOutcome += t.getAmount();
            }
        }
        
        view.getIncomeField().setText(String.format("%.2f", totalIncome));
        view.getOutcomeField().setText(String.format("-%.2f", totalOutcome));
        
        updateRecentTransactions(transactions);
    }
    
    private void updateRecentTransactions(List<transaction> transactions) {
        view.updateTransactionList(transactions);
    }
    
    public void openTransactionView() {
        new transaksiview(currentUser).setVisible(true);
    }
    
    public void logout() {
        int confirm = JOptionPane.showConfirmDialog(
            view, 
            "Apakah Anda yakin ingin logout?", 
            "Konfirmasi Logout", 
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            view.dispose();
            new view.loginview().setVisible(true);
        }
    }
}