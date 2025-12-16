/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Acer
 */
public class user {
    private String nama;
    private String password;
    private String nomorHp;

    public user(String nama, String Password,String nomorHp) {
        this.nama = nama;
        this.password = Password;
        this.nomorHp = nomorHp;
    }
    

    public user() {
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String Password) {
        this.password = Password;
    }
    
}
