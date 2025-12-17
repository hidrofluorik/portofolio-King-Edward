/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;
import dao.anggotaDAO;
import model.anggota;
import view.anggotaview;
import javax.swing.AbstractAction.*;
import java.util.AbstractCollection.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.plaf.OptionPaneUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;



public class anggotacontroler {
    private anggotaview view;
    private anggotaDAO dao;
    private DefaultTableModel tabelmodel;
    

    public anggotacontroler(anggotaview v) {
        this.view = v;
        dao = new anggotaDAO();
        inittabelmodel();
        tampildata();
        settableclick();
        setbutonlistener();
    }
    
    
    public void inittabelmodel(){
        tabelmodel = new DefaultTableModel(
                new Object[]{"NIM", "Nama", "Prodi", "angkatan"},0);
        view.getTblanggota().setModel(tabelmodel);
    }
    
    public void settableclick(){
        view.getTblanggota().getSelectionModel().addListSelectionListener(e ->{
            int row = view.getTblanggota().getSelectedRow();
            if (row != -1) {
                view.getTxtnim().setText(tabelmodel.getValueAt(row, 0).toString());
                view.getTxtnama().setText(tabelmodel.getValueAt(row, 1).toString());
                view.getTxtprodi().setText(tabelmodel.getValueAt(row, 2).toString());
                view.getTxtangkatan().setYear(Integer.parseInt(tabelmodel.getValueAt(row, 3).toString()));
                
            }
        }
        
        );
    }
    public void tampildata(){
        clearfrom();
        List<anggota> list = dao.getAllangota();
        DefaultTableModel model = (DefaultTableModel) view.getTblanggota().getModel();
        model.setRowCount(0);
        
        for (anggota a: list) {
            Object[] row = {
                a.getNim(),
                a.getNama(),
                a.getProdi(),
                a.getAngkatan()
            };
            model.addRow(row);
        }
    }
    public void clearfrom(){
        view.getTxtnim().setText("");
        view.getTxtnama().setText("");
        view.getTxtprodi().setText("");
        view.getTxtangkatan().setYear(2000);
    }
    public void simpandata(){
        anggota a = new anggota();
        a.setNim(view.getTxtnim().getText());
        a.setNama(view.getTxtnama().getText());
        a.setProdi(view.getTxtprodi().getText());
        a.setAngkatan(view.getTxtangkatan().getYear());
        if (dao.insert(a)){
            JOptionPane.showMessageDialog(view, "Data Tersimpan");
            tampildata();
            clearfrom();
        }else{
            JOptionPane.showMessageDialog(view, "Gagal Menyimpan Data");
        }
    }
    public void ubahdata(){
        int baris = view.getTblanggota().getSelectedRow();
        if (baris != -1) {
            anggota a = new anggota();
        a.setNim(view.getTxtnim().getText());
        a.setNama(view.getTxtnama().getText());
        a.setProdi(view.getTxtprodi().getText());
        a.setAngkatan(view.getTxtangkatan().getYear());
        if (dao.update(a)){
            JOptionPane.showMessageDialog(view, "Data Terupdate");
            tampildata();
            clearfrom();
        }else{
            JOptionPane.showMessageDialog(view, "Gagal Mengupdate Data");
        }
            
        }else{
            JOptionPane.showMessageDialog(view, "pilih baris data yang ingin di update");
        }
    }
    public void hapusdata(){
        int baris = view.getTblanggota().getSelectedRow();
        if (baris != -1) {
            String id = view.getTblanggota().getValueAt(baris, 0).toString();
            if (dao.delete(id)) {
                JOptionPane.showMessageDialog(view, "Baris Data Dihapus");
                tampildata();
                clearfrom();
            }else{
                JOptionPane.showMessageDialog(view, "Gagal Menghapus Data");
            }
    }else{
            JOptionPane.showMessageDialog(view, "pilih baris data yang ingin di hapus");
        }
    }
    public void setbutonlistener(){
        view.getBtnsimpan().addActionListener(e -> simpandata());
        view.getBtnhapus().addActionListener(e -> hapusdata());
        view.getBtnubah().addActionListener(e -> ubahdata());
        view.getBtnrefresh().addActionListener(e -> tampildata());
    }
}
