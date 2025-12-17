/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.awt.CardLayout;
import view.mainView;
/**
 *
 * @author LENOVO
 */
public class mainController {
    private mainView view;
    private CardLayout cl;
    
    public mainController(mainView view){
        this.view = view;
        initcontroller();
    }
    
    private void initcontroller(){
        view.getBtn_user().addActionListener(e -> showpanel("user"));
        view.getBtn_fav().addActionListener(e -> showpanel("favorite"));
        view.getBtn_tambah().addActionListener(e -> showpanel("song"));
        view.getBtn_cetak().addActionListener(e -> showpanel("wrapped"));
        
    }
    
    private void showpanel(String name){
        CardLayout cl = (CardLayout) view.getMainpanel().getLayout();
        cl.show(view.getMainpanel(), name);
        view.getMainpanel().revalidate();
        view.getMainpanel().repaint();
    }
}
