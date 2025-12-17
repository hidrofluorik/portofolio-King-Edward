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

/**
 *
 * @author TOSHIBA
 */
public class anggotaDAO {
    
    public List<anggota> getAllangota(){
        List<anggota> list = new ArrayList<>();
        String sql = "select * from anggota";
        try {
            Connection con = koneksi.getConnection();
            try(Statement st = con.createStatement();
                ResultSet rs = st. executeQuery(sql)){
                while (rs.next()) {
                    anggota a = new anggota(
                            rs.getString("nim"),
                            rs.getString("nama"),
                            rs.getString("prodi"),
                            rs.getInt("angkatan"));
                    list.add(a);    
                }
               }
                    
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        
    }
    public boolean  insert(anggota a){
        String sql = "insert into anggota(nim, nama, prodi, angkatan) values(?,?,?,?)";
        try {
            Connection con = koneksi.getConnection();
            try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, a.getNim());
            ps.setString(2, a.getNama());
            ps.setString(3, a.getProdi());
            ps.setInt(4, a.getAngkatan());
            ps.executeUpdate();
            return true;}
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean update(anggota a){
       String sql = "UPDATE anggota SET nama = ?, prodi = ?, angkatan = ? WHERE nim = ?";
        try {
            Connection con = koneksi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, a.getNama());
            ps.setString(2, a.getProdi());           
            ps.setInt(3, a.getAngkatan());
            ps.setString(4, a.getNim());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean delete(String nim){
        String sql = "delete from anggota where nim=?";
        try {
            Connection con = koneksi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nim);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public anggota searchAnggota(String nim){
        anggota a = null;
        String sql = "select * from anggota where nim =?";
        try {
            Connection con = koneksi.getConnection();
            try(PreparedStatement ps = con.prepareStatement(sql)){
                ps.setString(1, nim);
                try(ResultSet rs = ps. executeQuery()){
                 if (rs.next()){
                    a = new anggota(
                            rs.getString("nim"),
                            rs.getString("nama"),
                            rs.getString("prodi"),
                            rs.getInt("angkatan"));    
            }   
            }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }
    
}
