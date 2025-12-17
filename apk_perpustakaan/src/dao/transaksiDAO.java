/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.transaksi;

/**
 *
 * @author TOSHIBA
 */
public class transaksiDAO {
    
    
    public List<transaksi> getAlltransaksi(){
        List<transaksi> list = new ArrayList<>();
        String sql = "select * from transaksi";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Connection con = koneksi.getConnection();
            try(Statement st = con.createStatement();
            ResultSet rs = st. executeQuery(sql)){
                while (rs.next()) {
                    Date tgl_pinjam = sdf.parse(rs.getString("tgl_pinjam"));
                    Date tgl_kembali = sdf.parse(rs.getString("tgl_kembali"));
                    transaksi t = new transaksi(
                            rs.getInt("id_transaksi"),
                            rs.getString("id_buku"),
                            rs.getString("nim"),
                            tgl_pinjam,
                            tgl_kembali,
                            rs.getString("status"));
                    list.add(t);

                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        
    }
    
    public boolean  insert(transaksi t){
        String sql = "insert into transaksi( id_buku, nim, tgl_pinjam, tgl_kembali, status) values(?,?,?,?,?)";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String tgl_pinjam = sdf.format(t.getTgl_pinjam());
        String tgl_kembali = sdf.format(t.getTgl_kembali());
        try {
            Connection con = koneksi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getId_buku());
            ps.setString(2, t.getNim());
            ps.setString(3, tgl_pinjam);
            ps.setString(4,tgl_kembali);
            ps.setString(5, t.getStatus());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean update(transaksi t){
       String sql = "UPDATE transaksi SET tgl_kembali = ?, status =? WHERE nim=? and id_buku=? ";
       SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
       String tgl_kembali = sdf.format(t.getTgl_kembali()); 
       try {
            Connection con = koneksi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tgl_kembali);
            ps.setString(2, t.getStatus());
            ps.setString(3, t.getNim());
            ps.setString(4, t.getId_buku());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(String idtransaksi){
        String sql = "delete from transaksi where id_transaksi=?";
        try {
            Connection con = koneksi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idtransaksi);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public transaksi getbyNim(String nim){
        transaksi t = null;
        String sql = "select * from transaksi where nim = ? and status = 'dipinjam'";
        try {
            Connection con = koneksi.getConnection();
            try(PreparedStatement ps = con.prepareStatement(sql)){
                ps.setString(1, nim);
                ResultSet rs = ps.executeQuery();
                if  (rs.next()){
                   t = new transaksi(
                           rs.getInt("id_transaksi"),
                           rs.getString("id_buku"),
                           rs.getString("nim"),
                           rs.getDate("tgl_pinjam"),
                           rs.getDate("tgl_kembali"),
                           rs.getString("status"));
                }
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
