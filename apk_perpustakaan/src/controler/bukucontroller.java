/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;
import dao.bukuDAO;
import model.buku;
import view.BukuView;
import javax.swing.AbstractAction.*;
import java.util.AbstractCollection.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.plaf.OptionPaneUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class bukucontroller {
    private BukuView view;
    private bukuDAO dao;
    private DefaultTableModel tabelmodel;
    
    public bukucontroller(BukuView v){
        this.view = v;
        dao = new bukuDAO();
        inittablemodel();
        tampildata();
        settableclick();
        setbutonlistener();
    }
    
    private void inittablemodel(){
        tabelmodel = new DefaultTableModel(
                new Object[]{"ID Buku", "Judul", "Pengarang", "Penerbit", "Jumlah"},0);
        view.getTblbuku().setModel(tabelmodel);
    }
    
    public void tampildata(){
        clearform();
        List<buku> list = dao.getAllbuku();
        DefaultTableModel model = (DefaultTableModel) view.getTblbuku().getModel();
        model.setRowCount(0);
        for (buku b: list) {
            Object[] row = {
                b.getId_buku(),
                b.getJudul(),
                b.getPengarang(),
                b.getPenerbit(),
                b.getJumlah()
            };
            model.addRow(row);
        }
    }
    
    public void settableclick(){
        view.getTblbuku().getSelectionModel().addListSelectionListener(e ->{
            int row = view.getTblbuku().getSelectedRow();
            if (row != -1) {
                view.getTxt_idbuku().setText(tabelmodel.getValueAt(row, 0).toString());
                view.getTxt_judul().setText(tabelmodel.getValueAt(row, 1).toString());
                view.getTxt_pengarang().setText(tabelmodel.getValueAt(row, 2).toString());
                view.getTxt_penerbit().setText(tabelmodel.getValueAt(row, 3).toString());
                view.getTxt_jumlah().setText(tabelmodel.getValueAt(row, 4).toString());
                
            }
        }
        
        );
    }
    
    public void clearform(){
        view.getTxt_idbuku().setText("");
        view.getTxt_judul().setText("");
        view.getTxt_pengarang().setText("");
        view.getTxt_penerbit().setText("");
        view.getTxt_jumlah().setText("");
    }
    public void simpandata(){
        buku b = new buku();
        b.setId_buku(view.getTxt_idbuku().getText());
        b.setJudul(view.getTxt_judul().getText());
        b.setPengarang(view.getTxt_pengarang().getText());
        b.setPenerbit(view.getTxt_penerbit().getText());
        b.setJumlah(Integer.parseInt(view.getTxt_jumlah().getText()));
        if (dao.insert(b)){
            JOptionPane.showMessageDialog(view, "Data Tersimpan");
            tampildata();
            clearform();
        }else{
            JOptionPane.showMessageDialog(view, "Gagal Menyimpan Data");
        }
    }
    public void updatedata(){
        int baris = view.getTblbuku().getSelectedRow();
        if (baris != -1) {
            buku b = new buku();
        b.setId_buku(view.getTxt_idbuku().getText());
        b.setJudul(view.getTxt_judul().getText());
        b.setPengarang(view.getTxt_pengarang().getText());
        b.setPenerbit(view.getTxt_penerbit().getText());
        b.setJumlah(Integer.parseInt(view.getTxt_jumlah().getText()));
        if (dao.update(b)){
            JOptionPane.showMessageDialog(view, "Data Terupdate");
            tampildata();
            clearform();
        }else{
            JOptionPane.showMessageDialog(view, "Gagal Mengupdate Data");
        }
            
        }else{
            JOptionPane.showMessageDialog(view, "pilih baris data yang ingin di update");
        }
    }
    
    public void hapusdata(){
        int baris = view.getTblbuku().getSelectedRow();
        if (baris != -1) {
            String id = view.getTblbuku().getValueAt(baris, 0).toString();
            if (dao.delete(id)) {
                JOptionPane.showMessageDialog(view, "Baris Data Dihapus");
                tampildata();
                clearform();
            }else{
                JOptionPane.showMessageDialog(view, "Gagal Menghapus Data");
            }
    }else{
            JOptionPane.showMessageDialog(view, "pilih baris data yang ingin di hapus");
        }
    }
    public void setbutonlistener(){
        view.getBtn_simpan().addActionListener(e -> simpandata());
        view.getBtn_hapus().addActionListener(e -> hapusdata());
        view.getBtn_ubah().addActionListener(e -> updatedata());
        view.getBtn_refresh().addActionListener(e -> tampildata());
    }
}
