package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.sqlite.SQLiteConfig;

public class koneksi {

    private static final String url = "jdbc:sqlite:C:/Users/Lenovo/Downloads/apk_perpustakaan (2)/apk_perpustakaan/dist/database/perpustakaan.db";

    private static Connection con = null;

    public static synchronized Connection getConnection() throws SQLException{
       if (con == null || con.isClosed()){
           SQLiteConfig config = new SQLiteConfig();
           config.setBusyTimeout(5000);
           con = DriverManager.getConnection(url, config.toProperties());
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
