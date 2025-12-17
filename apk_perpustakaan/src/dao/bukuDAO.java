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
import java.util.ArrayList;
import java.util.List;
import model.anggota;
import model.buku;
import model.petugas;

/**
 *
 * @author TOSHIBA
 */
public class bukuDAO {
    
    
    public List<buku> getAllbuku(){
        List<buku> list = new ArrayList<>();
        String sql = "select * from buku";
        try {
            Connection con = koneksi.getConnection();
            try(Statement st = con.createStatement();
            ResultSet rs = st. executeQuery(sql)){
                while (rs.next()) {
                    buku b = new buku(
                            rs.getString("id_buku"),
                            rs.getString("judul"),
                            rs.getString("pengarang"),
                            rs.getString("penerbit"),
                            rs.getInt("jumlah"));
                    list.add(b);

                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        
    }
     public boolean  insert(buku b){
        String sql = "insert into buku(id_buku, judul, pengarang, penerbit, jumlah) values(?,?,?,?,?)";
        try {
            Connection con = koneksi.getConnection();
            try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, b.getId_buku());
            ps.setString(2, b.getJudul());
            ps.setString(3, b.getPengarang());
            ps.setString(4, b.getPenerbit());
            ps.setInt(5, b.getJumlah());
            ps.executeUpdate();
            return true;}
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
     public boolean update(buku b){
       String sql = "UPDATE buku SET judul = ?, pengarang = ?, penerbit = ?, jumlah =? WHERE id_buku = ?";
        try {
            Connection con = koneksi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, b.getJudul());
            ps.setString(2, b.getPengarang());           
            ps.setString(3, b.getPenerbit());
            ps.setInt(4, b.getJumlah());
            ps.setString(5, b.getId_buku());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
     public boolean delete(String idbuku){
        String sql = "delete from buku where id_buku=?";
        try {
            Connection con = koneksi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idbuku);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
      public buku searchbuku(String idbuku){
        buku b = null;
        String sql = "select * from buku where id_buku =?";
        try {
            Connection con = koneksi.getConnection();
            try(PreparedStatement ps = con.prepareStatement(sql)){
                ps.setString(1, idbuku);
                try(ResultSet rs = ps. executeQuery();){
                  if (rs.next()){
                    b = new buku(
                            rs.getString("id_buku"),
                            rs.getString("judul"),
                            rs.getString("pengarang"),
                            rs.getString("penerbit"),
                            rs.getInt("jumlah"));

                }  
                }
            }
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
      
    public boolean tambahkurangstok(String idbuku, int jumlah, String keterangan){
        String sql = "";
        if (keterangan.equals("Dipinjam")){
            sql = "update buku set jumlah = jumlah - ? where id_buku = ?";
        }else{
            sql = "update buku set jumlah = jumlah + ? where id_buku = ?";
            
        }
        try {
            Connection con = koneksi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, jumlah);
            ps.setString(2, idbuku);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
             e.printStackTrace();
             return false;
        }
        
    }
}
