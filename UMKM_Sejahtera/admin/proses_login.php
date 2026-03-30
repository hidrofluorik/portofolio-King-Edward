<?php
session_start();
include 'koneksi.php';

$username = $_POST['username'];
$password = $_POST['password'];
$captcha  = $_POST['captcha'];

if ($captcha == $_SESSION['captcha']) {
    $data = mysqli_query($koneksi, "SELECT * FROM staff WHERE username='$username' AND password='$password'");
    $cek = mysqli_num_rows($data);

    if ($cek > 0) {
        $_SESSION['username'] = $username;
        $_SESSION['status'] = "login";
        echo "<script>alert('Login Berhasil!'); window.location='dashboard.php';</script>";
        // Simpan ID Staff ke session biar pas transaksi id-nya gak 0
$_SESSION['id_staff'] = $data['id']; // atau $data['id_staff'] tergantung nama kolom di tabel staff lu
    } else {
        echo "<script>alert('Username atau Password salah!'); window.location='login.php';</script>";
    }
} else {
    echo "<script>alert('CAPTCHA salah!'); window.location='login.php';</script>";
}
?>