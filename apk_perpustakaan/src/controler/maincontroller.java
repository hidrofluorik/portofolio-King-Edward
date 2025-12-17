/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import java.awt.CardLayout;
import view.mainview;
import config.koneksi;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author TOSHIBA
 */
public class maincontroller {
    private mainview view;
    private CardLayout cl;
    
    public maincontroller(mainview view){
        this.view = view;
        initcontroller();
    }
    
    private void initcontroller(){
        view.getBtn_anggota().addActionListener(e -> showpanel("anggota"));
        view.getBtn_buku().addActionListener(e -> showpanel("buku"));
        view.getBtn_petugas().addActionListener(e -> showpanel("petugas"));
        view.getBtn_transaksi().addActionListener(e -> showpanel("transaksi"));
        view.getBtn_cetaklaporan().addActionListener(e -> printReport());
    }
    
    private void showpanel(String name){
        CardLayout cl = (CardLayout) view.getMainpanel().getLayout();
        cl.show(view.getMainpanel(), name);
        view.getMainpanel().revalidate();
        view.getMainpanel().repaint();
    }
    
    private void printReport(){
        try {
            Connection con = koneksi.getConnection();
            String path = "src/report/laporan_transaksi.jasper";
            Map<String, Object> parameter = new HashMap<>();
            InputStream reportStream = getClass().getResourceAsStream("/report/laporan_transaksi.jasper");
            JasperPrint print = JasperFillManager.fillReport(reportStream, parameter, con);
            JasperViewer.viewReport(print,false);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Gagal Mencetak laporan "+e.getMessage());
        }
    }
}
