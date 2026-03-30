<?php 
include 'koneksi.php';
$nama  = $_POST['nama_produk'];
$harga = $_POST['harga'];
$stok  = $_POST['stok'];

$gambar = $_FILES['gambar']['name'];
$tmp = $_FILES['gambar']['tmp_name'];
move_uploaded_file($tmp, 'gambar/'.$gambar);

$kategori = $_POST['kategori'];
mysqli_query($koneksi, "INSERT INTO produk VALUES('', '$nama', '$kategori', '$harga', '$stok', '$gambar')");
header("location:produk.php");
?>