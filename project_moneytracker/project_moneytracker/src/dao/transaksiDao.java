/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Acer
 */

package dao;

import config.koneksi;
import model.transaction;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class transaksiDao {

    public boolean insert(transaction t) {
        String sql = "INSERT INTO transactions (user_nama, date, amount, type, kategori, keterangan) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection con = koneksi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getNama());
            ps.setString(2, new java.text.SimpleDateFormat("yyyy-MM-dd").format(t.getDate()));
            ps.setDouble(3, t.getAmount());
            ps.setString(4, t.getType());
            ps.setString(5, t.getKategori());
            ps.setString(6, t.getKeterangan());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean update(transaction t) {
        String sql = "UPDATE transactions SET kategori=?, type=?, amount=?, date=?, keterangan=? WHERE id=? AND user_nama=?";
        try {
            Connection con = koneksi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getKategori());
            ps.setString(2, t.getType());
            ps.setDouble(3, t.getAmount());
            ps.setString(4, new java.text.SimpleDateFormat("yyyy-MM-dd").format(t.getDate()));
            ps.setString(5, t.getKeterangan());
            ps.setInt(6, t.getId());
            ps.setString(7, t.getNama());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(int id) {
        String sql = "DELETE FROM transactions WHERE id=?";
        try {
            Connection con = koneksi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<transaction> getAllTransactions(String username) {
        List<transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE user_nama=? ORDER BY date DESC";
        try {
            Connection con = koneksi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                java.util.Date date = null;
                try {
                    date = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("date"));
                } catch (Exception ex) {
                    date = new java.util.Date();
                }
                
                transaction t = new transaction(
                    rs.getInt("id"),
                    rs.getString("user_nama"),
                    rs.getString("kategori"),
                    rs.getString("type"),
                    date,
                    rs.getDouble("amount"),
                    rs.getString("keterangan")
                );
                transactions.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }
    
    public List<transaction> getTransactionsByMonth(String username, int month, int year) {
        List<transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE user_nama=? AND CAST(strftime('%m', date) AS INTEGER)=? AND CAST(strftime('%Y', date) AS INTEGER)=? ORDER BY date DESC";
        try {
            Connection con = koneksi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setInt(2, month);
            ps.setInt(3, year);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                java.util.Date date = null;
                try {
                    date = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("date"));
                } catch (Exception ex) {
                    date = new java.util.Date(); 
                }
                
                transaction t = new transaction(
                    rs.getInt("id"),
                    rs.getString("user_nama"),
                    rs.getString("kategori"),
                    rs.getString("type"),
                    date,
                    rs.getDouble("amount"),
                    rs.getString("keterangan")
                );
                transactions.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }
    
    public transaction getTransactionById(int id) {
        transaction t = null;
        String sql = "SELECT * FROM transactions WHERE id=?";
        try {
            Connection con = koneksi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                java.util.Date date = null;
                try {
                    date = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("date"));
                } catch (Exception ex) {
                    date = new java.util.Date(); 
                }
                
                t = new transaction(
                    rs.getInt("id"),
                    rs.getString("user_nama"),
                    rs.getString("kategori"),
                    rs.getString("type"),
                    date,
                    rs.getDouble("amount"),
                    rs.getString("keterangan")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
