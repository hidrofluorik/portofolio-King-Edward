<?php 
include 'koneksi.php';

$id     = $_POST['id'];
$nama   = $_POST['nama_produk'];
$harga  = $_POST['harga'];
$stok   = $_POST['stok'];

$gambar = $_FILES['gambar']['name'];

if($gambar != "") {
    // Jika ganti gambar
    $tmp = $_FILES['gambar']['tmp_name'];
    move_uploaded_file($tmp, 'gambar/'.$gambar);
    mysqli_query($koneksi, "UPDATE produk SET nama_produk='$nama', harga='$harga', stok='$stok', gambar='$gambar' WHERE id_produk='$id'");
} else {
    // Jika TIDAK ganti gambar
    mysqli_query($koneksi, "UPDATE produk SET nama_produk='$nama', harga='$harga', stok='$stok' WHERE id_produk='$id'");
}

header("location:produk.php");
?>