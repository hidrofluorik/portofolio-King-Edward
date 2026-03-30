<?php
session_start();
$id = $_GET['id'];

// Hapus item spesifik dari keranjang
unset($_SESSION['keranjang'][$id]);

header("location:produk_masuk.php");
?>