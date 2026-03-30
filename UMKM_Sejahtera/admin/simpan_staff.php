<?php 
include 'koneksi.php';
$user = $_POST['username'];
$pass = $_POST['password'];
$nama = $_POST['nama'];
$role = $_POST['role'];

mysqli_query($koneksi, "INSERT INTO staff (username, password, nama, role) VALUES ('$user', '$pass', '$nama', '$role')");
header("location:staff.php");
?>