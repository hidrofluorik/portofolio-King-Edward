/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import config.koneksiKey;
import model.user;
import dao.UserDao;
import model.favorite;
import dao.FavDao;

public class main {
    public static void main(String[] args) {
    favorite f = new favorite("2", "123", "ayes");
    FavDao dao = new FavDao();
    dao.insert(f);
    
    }
}



