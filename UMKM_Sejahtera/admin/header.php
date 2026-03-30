<?php
session_start();
// Cek apakah user sudah login, kalau belum lempar ke login.php
if($_SESSION['status'] != "login"){
    header("location:login.php");
}
?>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin UMKM Sejahtera</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap5.min.css">
    
    <style>
        /* Styling Biru sesuai UMKM 4 */
        .navbar-custom { background-color: #007bff; }
        .navbar-custom .nav-link { color: white !important; }
        .navbar-custom .nav-link:hover { color: #f8f9fa !important; opacity: 0.8; }
        .footer-admin { background-color: #007bff; color: white; padding: 40px 0; margin-top: 50px; }
        body { background-color: #f4f7f6; }
        .main-container { background-color: white; min-height: 80vh; padding: 30px; border-radius: 10px; box-shadow: 0 0 15px rgba(0,0,0,0.05); }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark navbar-custom mb-4 shadow-sm">
  <div class="container">
    <a class="navbar-brand fw-bold" href="dashboard.php">UMKM Admin</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto">
        <li class="nav-item"><a class="nav-link" href="dashboard.php">Dashboard</a></li>
        <li class="nav-item"><a class="nav-link" href="produk.php">Produk</a></li>
        <li class="nav-item"><a class="nav-link" href="staff.php">Staf</a></li>
        
        <li class="nav-item"><a class="nav-link" href="produk_masuk.php">Produk Masuk</a></li>
        
            <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
        Laporan
      </a>
      <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
        <li><a class="dropdown-item" href="laporan_pembelian.php">Laporan Pembelian</a></li>
        <li><a class="dropdown-item" href="laporan_stok.php">Laporan Stok</a></li>
        <li><hr class="dropdown-divider"></li>
        <li><a class="dropdown-item" href="dashboard.php">Ringkasan Grafik</a></li>
      </ul>
    </li>
          </ul>
      
      <ul class="navbar-nav ms-auto align-items-center">
        <li class="nav-item me-3">
            <span class="text-white small">Halo, <strong><?php echo $_SESSION['username']; ?></strong></span>
        </li>
        <li class="nav-item">
            <a class="btn btn-light btn-sm text-primary fw-bold px-3" href="logout.php">Logout</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<div class="container main-container mb-5">