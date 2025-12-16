/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Acer
 */
public class koneksi {
    private static final String url = "jdbc:sqlite:database/moneytracker.db";
    private static Connection con;

    public static synchronized Connection getConnection() throws SQLException{
        if(con == null || con.isClosed()){
            con = DriverManager.getConnection(url);
            System.out.println("Koneksi Berhasil");
        }
        return con;
    }
    
    public static synchronized void closeConnection(){
        if(con != null){
            try {
                con.close();
                con = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
