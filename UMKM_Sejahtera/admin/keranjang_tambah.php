<?php
session_start();
$id_produk = $_POST['id_produk'];
$jumlah = $_POST['jumlah'];

// Kalau belum ada keranjang, buat array kosong
if(!isset($_SESSION['keranjang'])){
    $_SESSION['keranjang'] = array();
}

// Tambahkan produk ke keranjang. Kalau sudah ada, jumlahnya ditambahin.
if(isset($_SESSION['keranjang'][$id_produk])){
    $_SESSION['keranjang'][$id_produk] += $jumlah;
} else {
    $_SESSION['keranjang'][$id_produk] = $jumlah;
}

// Balik lagi ke halaman transaksi
header("location:produk_masuk.php");
?>