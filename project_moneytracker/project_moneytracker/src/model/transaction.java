/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.math.BigDecimal;
import java.util.Date;
/**
 *
 * @author Lenovo
 */
public class transaction {
    private int id;        //id
    private String nama;     
    private String kategori;    //kategori (belanja/jajan/gaji/dll)
    private String type;          //Income/Outcome
    private Date date;
    private Double amount;  //jumlah uang
    private String keterangan;

    public transaction( int id,String nama, String kategori, String type, Date date, Double amount,String keterangan) {
        this.nama = nama;
        this.id = id;
        this.kategori = kategori;
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.keterangan = keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    
    
    
}


