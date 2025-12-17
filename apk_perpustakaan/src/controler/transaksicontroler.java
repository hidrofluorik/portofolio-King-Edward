/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import model.*;
import dao.*;
import view.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

public class transaksicontroler {
    private transaksiview view;
    private transaksiDAO dao;
    private anggotaDAO adao;
    private bukuDAO bdao;
    private DefaultTableModel tableModel;

    public transaksicontroler(transaksiview v) {
        this.view = v;
        dao = new transaksiDAO();
        adao = new anggotaDAO();
        bdao = new bukuDAO();
        inittabelmodel();
        tampildata();
        settableclick();
        setbutonlistener();
    }
    
    public void inittabelmodel(){
        tableModel = new DefaultTableModel(
                new Object[]{"ID Transaksi", "NIM", "ID Buku","Tgl_pinjam", "Tgl_kembali", "status"},0
        );
        view.getTbltransaksi().setModel(tableModel);
    }
    public void tampildata(){
        clearfrom();
        List<transaksi> list = dao.getAlltransaksi();
        DefaultTableModel model = (DefaultTableModel) view.getTbltransaksi().getModel();
        model.setRowCount(0);
        for (transaksi t:list) {
            Object[] row = {
                t.getId_transaksi(),
                t.getNim(),
                t.getId_buku(),
                t.getTgl_pinjam(),
                t.getTgl_kembali(),
                t.getStatus()
                
            };
            model.addRow(row);
        }
    }
    public void clearfrom(){
        view.getTxtnimpinjam().setText("");
        view.getTxtidbukupinjam().setText("");
        Date today = new Date();
        view.getTxttglpinjam().setDate(today);
        view.getTxtidtransaksi().setText("");
        view.getTxtnimkembali().setText("");
        view.getTxtidbukukembali().setText("");
        view.getTxtstatuskembali().setText("");
        view.getTxttglkmbali().setDate(today);
        
    }
    public void simpandata(){
        String nim = view.getTxtnimpinjam().getText();
        String idbuku = view.getTxtidbukupinjam().getText();
        Date tglpinjam = view.getTxttglpinjam().getDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(tglpinjam);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        Date tglkembali = cal.getTime();
        try {
            anggota a = adao.searchAnggota(nim);
            buku b = bdao.searchbuku(idbuku);
            if (a == null || b == null) {
                JOptionPane.showMessageDialog(view, "NIM/ID Buku Tidak Ada!");
                return;
                
            }
            transaksi t = new transaksi();
            t.setNim(nim);
            t.setId_buku(idbuku);
            t.setTgl_pinjam(tglpinjam);
            t.setTgl_kembali(tglkembali);
            t.setStatus("Dipinjam");
            if (dao.insert(t)) {
                bdao.tambahkurangstok(idbuku, 1, "Dipinjam");
                JOptionPane.showMessageDialog(view, " Data Berhasil Disimpan");
                
            }else{
                JOptionPane.showMessageDialog(view, "Input Gagal");
            }
            tampildata();
            clearfrom();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Input Gagal "+e.getMessage());
        }
    }
    
    public void ubahdata(){
      String nim = view.getTxtnimkembali().getText();
        String idbuku = view.getTxtidbukukembali().getText();
        Date tglkembali = view.getTxttglkmbali().getDate(); 
        transaksi t = new transaksi();
        t.setNim(nim);
        t.setId_buku(idbuku);
        t.setTgl_kembali(tglkembali);
        t.setStatus("Dikembalikan");
        
        try {
            if (dao.update(t)) {
                bdao.tambahkurangstok(idbuku, 1, "Dikembalikan");
                tampildata();
                clearfrom();
                JOptionPane.showMessageDialog(view, "Data berhasil ");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Input Gagal "+e.getMessage());
        }
    }
    
    public void settableclick(){
        view.getTbltransaksi().getSelectionModel().addListSelectionListener(e ->{
            int row = view.getTbltransaksi().getSelectedRow();
            if (row != -1) {
                view.getTxtidtransaksi().setText(tableModel.getValueAt(row, 0).toString());
                view.getTxtnimkembali().setText(tableModel.getValueAt(row, 1).toString());
                view.getTxtidbukukembali().setText(tableModel.getValueAt(row, 2).toString());
                view.getTxtstatuskembali().setText(tableModel.getValueAt(row, 5).toString());
                
            }
        }
        
        );
    }
    
     public void setbutonlistener(){
        view.getBtnsimpan().addActionListener(e -> simpandata());
        view.getBtnubahkembali().addActionListener(e -> ubahdata());
        view.getBtnrefresh().addActionListener(e -> tampildata());
    }
}
