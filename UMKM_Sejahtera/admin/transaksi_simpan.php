<?php
session_start();
include 'koneksi.php';

$tanggal = date('Y-m-d');
// Ambil ID dari session, kalau gak ada kasih default 1 (bukan 0)
$id_staff = isset($_SESSION['id_staff']) ? $_SESSION['id_staff'] : 1;

// 1. Simpan ke tabel produk_masuk (Header)
mysqli_query($koneksi, "INSERT INTO produk_masuk (tanggal, id_staff) VALUES ('$tanggal', '$id_staff')");
$id_masuk_baru = mysqli_insert_id($koneksi); // Ambil ID transaksi barusan

// 2. Pindahkan isi keranjang ke detail dan UPDATE STOK
foreach($_SESSION['keranjang'] as $id_produk => $jumlah){
    // Simpan rincian
    mysqli_query($koneksi, "INSERT INTO produk_masuk_detail (id_masuk, id_produk, jumlah) VALUES ('$id_masuk_baru', '$id_produk', '$jumlah')");
    
    // UPDATE STOK BARANG (Ini yang bikin stok nambah otomatis)
    mysqli_query($koneksi, "UPDATE produk SET stok = stok + $jumlah WHERE id_produk = '$id_produk'");
}

// 3. Kosongkan keranjang setelah selesai
unset($_SESSION['keranjang']);

echo "<script>alert('Transaksi Berhasil Disimpan!'); window.location='produk_masuk.php';</script>";
?>