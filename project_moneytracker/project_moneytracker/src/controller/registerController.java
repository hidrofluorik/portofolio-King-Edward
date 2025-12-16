package controller;

import view.registerview;
import dao.userDao;
import model.user;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class registerController {
    private registerview view;
    private userDao dao;
    
    public registerController(registerview view) {
        this.view = view;
        this.dao = new userDao();
    }
    
    public void register() {
        String username = view.getUsernameField().getText().trim();
        String password = view.getPasswordField().getText().trim();
        String nomorHp = view.getNomorHpField().getText().trim();
        
        if (username.isEmpty() || password.isEmpty() || nomorHp.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Semua field harus diisi!");
            return;
        }
        
        if (username.length() < 3) {
            JOptionPane.showMessageDialog(view, "Username minimal 3 karakter!");
            return;
        }
        
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(view, "Password minimal 6 karakter!");
            return;
        }
        
        if (!nomorHp.matches("\\d+")) {
            JOptionPane.showMessageDialog(view, "Nomor HP harus berupa angka!");
            return;
        }
        
        if (dao.isUsernameExists(username)) {
            JOptionPane.showMessageDialog(view, "Username sudah digunakan! Pilih username lain.");
            return;
        }
        
        user newUser = new user(username, password, nomorHp);
        
        if (dao.insert(newUser)) {
            JOptionPane.showMessageDialog(view, "Registrasi berhasil! Silakan login.");
            // Kembali ke login view
            new view.loginview().setVisible(true);
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "Registrasi gagal! Terjadi kesalahan sistem.");
        }
    }
    
    public void backToLogin() {
        new view.loginview().setVisible(true);
        view.dispose();
    }
}