/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;
import view.loginview;
import dao.petugasDAO;
import javax.swing.JOptionPane;
import model.petugas;
import view.mainview;

/**
 *
 * @author TOSHIBA
 */
public class loginCONTROLER {
    private loginview view;
    private petugasDAO dao;
    
    public loginCONTROLER(loginview view){
        this.view = view;
        dao = new petugasDAO();
    }
    
    public void  login(){
        String username = view.getTxtUsername().getText();
        String pasword = view.getTxtPasword().getText();
        if (username.isEmpty() || pasword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "username dan pasword tidak boleh kosong!");
            return;
            
        }
        petugas p = dao.login(username, pasword);
        if (p != null) {
            JOptionPane.showMessageDialog(null, "login berhasil! selamat datang "+p.getNama());
            new mainview().setVisible(true);
            view.dispose();
            
        }else{
            JOptionPane.showMessageDialog(null, "username dan pasword salah");
        }
    }
}
