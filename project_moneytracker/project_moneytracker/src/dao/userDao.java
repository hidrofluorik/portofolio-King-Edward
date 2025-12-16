/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import config.koneksi;
import model.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Acer
 */
public class userDao {
    public boolean insert(user a){
        String sql = "insert into user( nama, password,nomorHp) values(?,?,?)";
        try {
            Connection con = koneksi.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, a.getNama());
            ps.setString(2, a.getPassword());
            ps.setString(3, a.getNomorHp());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public user login(String username, String password){
        user p = null;
        String sql ="select * from user where nama = ? and password = ?";
        try {
            Connection con = koneksi.getConnection();
            try(PreparedStatement ps = con.prepareStatement(sql)){
                ps.setString(1, username);
                ps.setString(2, password);
                try(ResultSet rs = ps.executeQuery()){
                    if (rs.next()){
                        p = new user(
                            rs.getString("nama"),
                            rs.getString("password"),
                            rs.getString("nomorHp"));
                    }
                }
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        return p;
    }
    
    public boolean isUsernameExists(String username) {
        String sql = "select count(*) from user where nama = ?";
        try {
            Connection con = koneksi.getConnection();
            try(PreparedStatement ps = con.prepareStatement(sql)){
                ps.setString(1, username);
                try(ResultSet rs = ps.executeQuery()){
                    if (rs.next()){
                        return rs.getInt(1) > 0;
                    }
                }
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        return false;
    }
}
