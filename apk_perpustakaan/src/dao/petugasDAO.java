/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.petugas;
import config.koneksi;
import java.sql.*;
/**
 *
 * @author TOSHIBA
 */
public class petugasDAO {
   
    public List<petugas> getAllpetugas(){
        List<petugas> list = new ArrayList<>();
        String sql = "select * from petugas";
        try {
            Connection con = koneksi.getConnection();
            try(Statement st = con.createStatement();
            ResultSet rs = st. executeQuery(sql)){
                while (rs.next()) {
                    petugas p = new petugas(
                            rs.getString("id_petugas"),
                            rs.getString("username"),
                            rs.getString("pasword"),
                            rs.getString("nama"));
                    list.add(p);
                
            }
            }
                      
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        
    }
    public boolean  insert(petugas p){
        String sql = "insert into petugas(id_petugas, username, pasword, nama) values(?,?,?,?)";
        try {
            Connection con = koneksi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getId_petugas());
            ps.setString(2, p.getUsername());
            ps.setString(3, p.getPasword());
            ps.setString(4, p.getNama());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean update(petugas p){
       String sql = "UPDATE petugas SET username = ?, pasword = ?, nama = ? WHERE id_petugas = ?";
        try {
            Connection con = koneksi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getUsername());
            ps.setString(2, p.getPasword());           
            ps.setString(3, p.getNama());
            ps.setString(4, p.getId_petugas());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean delete(String idpetugas){
        String sql = "delete from petugas where id_petugas=?";
        try {
            Connection con = koneksi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idpetugas);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public petugas login(String username, String pasword){
        petugas p = null;
        String sql ="select * from petugas where username = ? and pasword = ?";
        try {
            Connection con = koneksi.getConnection();
            try(PreparedStatement ps = con.prepareStatement(sql)){
                ps.setString(1, username);
                ps.setString(2, pasword);
                try(ResultSet rs = ps.executeQuery()){
                     if (rs.next()){
                        p = new petugas(
                                rs.getString("id_petugas"), 
                                rs.getString("username"),
                                rs.getString("pasword"),
                                rs.getString("nama"));
                    }
                } 
            }
            
            
        } catch (Exception e) {
             e.printStackTrace();
        }
        return p;
    }
}
