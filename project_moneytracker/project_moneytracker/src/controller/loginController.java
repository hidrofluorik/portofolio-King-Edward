/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import view.loginview;
import dao.userDao;
import javax.swing.JOptionPane;
import model.user;
import view.mainview;
/**
 *
 * @author Acer
 */
public class loginController {
    private loginview view;
    private userDao dao;
    
    public loginController(loginview view){
        this.view = view;
        dao = new userDao();
    }
    
    public void  login(){
        String username = view.getUsernameField().getText();
        String password = view.getPasswordField().getText();
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "username dan pasword tidak boleh kosong!");
            return;
        }
        user p = dao.login(username, password);
        if (p != null) {
            JOptionPane.showMessageDialog(null, "login berhasil! selamat datang " + p.getNama());
            new mainview(p.getNama()).setVisible(true);
            view.dispose();
            
        }else{
            JOptionPane.showMessageDialog(null, "username dan pasword salah");
        }
    }
}
