/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author TOSHIBA
 */
public class buku {
    private String id_buku;
    private String judul;
    private String pengarang;
    private String penerbit;
    private int jumlah;

    public buku(String id_buku, String judul, String pengarang, String penerbit, int jumlah) {
        this.id_buku = id_buku;
        this.judul = judul;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
        this.jumlah = jumlah;
    }

    public buku() {
    }

    public String getId_buku() {
        return id_buku;
    }

    public String getJudul() {
        return judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setId_buku(String id_buku) {
        this.id_buku = id_buku;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    

}
