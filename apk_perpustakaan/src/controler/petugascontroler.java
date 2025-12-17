/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;
import dao.petugasDAO;
import model.petugas;
import view.petugasview;
import javax.swing.AbstractAction.*;
import java.util.AbstractCollection.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.plaf.OptionPaneUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class petugascontroler {
    private petugasview view;
    private petugasDAO dao;
    private DefaultTableModel tabelModel;

    public petugascontroler(petugasview v) {
        this.view = v;
        dao = new petugasDAO();
        inittabelmodel();
        tampildata();
        settableclick();
        setbutonlistener();
    }
    public void inittabelmodel(){
    tabelModel = new DefaultTableModel(
            new Object[]{"ID Petugas","Username","Password","Nama"},0);
    view.gettblpetugas().setModel(tabelModel);
    }
    public void tampildata(){
        clearform();
        List<petugas> list = dao.getAllpetugas();
        DefaultTableModel model = (DefaultTableModel) view.gettblpetugas().getModel();
        model.setRowCount(0);
        for (petugas p:list) {
            Object[] row = {
                p.getId_petugas(),
                p.getUsername(),
                p.getPasword(),
                p.getNama()
            
        };
            model.addRow(row);
        }
        
    }
    public void simpandata(){
        petugas p = new petugas();
        p.setId_petugas(view.getTxtidpetugas().getText());
        p.setUsername(view.getTxtusername().getText());
        p.setPasword(view.getTxtpasword().getText());
        p.setNama(view.getTxtnama().getText());
        if (dao.insert(p)) {
            p.setId_petugas(view.getTxtidpetugas().getText());
            clearform();
            tampildata();
            
        }else{
            JOptionPane.showMessageDialog(view, "Gagal Menyimpan Data");
        }
    }
    public void settableclick(){
        view.gettblpetugas().getSelectionModel().addListSelectionListener(e ->{
            int row = view.gettblpetugas().getSelectedRow();
            if (row != -1) {
                view.getTxtidpetugas().setText(tabelModel.getValueAt(row, 0).toString());
                view.getTxtusername().setText(tabelModel.getValueAt(row, 1).toString());
                view.getTxtpasword().setText(tabelModel.getValueAt(row, 2).toString());
                view.getTxtnama().setText(tabelModel.getValueAt(row, 3).toString());
                
            }
        }
        
        );
    }
    
    public void ubahdata(){
        int baris = view.gettblpetugas().getSelectedRow();
        if (baris != -1) {
            petugas p = new petugas();
        p.setId_petugas(view.getTxtidpetugas().getText());
        p.setUsername(view.getTxtusername().getText());
        p.setPasword(view.getTxtpasword().getText());
        p.setNama(view.getTxtpasword().getText());
        if (dao.update(p)){
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
        int baris = view.gettblpetugas().getSelectedRow();
        if (baris != -1) {
            String id = view.gettblpetugas().getValueAt(baris, 0).toString();
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
        view.getBtnsimpan().addActionListener(e -> simpandata());
        view.getBtnhapus().addActionListener(e -> hapusdata());
        view.getBtnubah().addActionListener(e -> ubahdata());
        view.getBtnrefresh().addActionListener(e -> tampildata());
    }
    public void clearform(){
        view.getTxtidpetugas().setText("");
        view.getTxtusername().setText("");
        view.getTxtpasword().setText("");
        view.getTxtnama().setText("");
    }
    
}
