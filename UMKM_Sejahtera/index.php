<?php 
include 'admin/koneksi.php'; 

// Jika ada kategori yang dipilih di URL
if(isset($_GET['kat'])){
    $kategori = $_GET['kat'];
    $ambil = mysqli_query($koneksi, "SELECT * FROM produk WHERE kategori = '$kategori' ORDER BY id_produk DESC");
} else {
    // Kalau nggak ada, tampilkan semua
    $ambil = mysqli_query($koneksi, "SELECT * FROM produk ORDER BY id_produk DESC");
}
?><?php 
// 1. Koneksi ke database (mengambil file dari folder admin)
include 'admin/koneksi.php'; 

// 2. Ambil data produk dari database
$ambil = mysqli_query($koneksi, "SELECT * FROM produk ORDER BY id_produk DESC");
?>

<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pasar UMKM Sejahtera</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    
    <style>
        body { background-color: #f4f7f6; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }
        .navbar-brand { font-weight: 800; color: #007bff !important; letter-spacing: 1px; }
        .search-box { border-radius: 20px; background-color: #f1f3f5; border: none; }
        
        /* Style Kategori */
        .cat-icon { width: 60px; height: 60px; line-height: 60px; background: white; border-radius: 50%; margin: 0 auto 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.05); transition: 0.3s; display: flex; align-items: center; justify-content: center; }
        .cat-icon:hover { transform: scale(1.1); background: #007bff; color: white; }
        .cat-text { font-size: 12px; font-weight: 600; color: #495057; }
        
        /* Style Produk Card */
        .card-produk { border: none; border-radius: 15px; overflow: hidden; transition: 0.3s; background: white; }
        .card-produk:hover { transform: translateY(-8px); box-shadow: 0 12px 24px rgba(0,0,0,0.1); }
        .img-wrapper { height: 160px; overflow: hidden; }
        .img-wrapper img { width: 100%; height: 100%; object-fit: cover; }
        .harga { color: #007bff; font-size: 1.1rem; }
        .btn-beli { border-radius: 10px; font-weight: 600; padding: 8px; }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm sticky-top py-3">
  <div class="container">
    <a class="navbar-brand" href="index.php text-uppercase">UMKM SEJAHTERA</a>
    
    <a href="admin/login.php" class="btn btn-outline-primary btn-sm d-lg-none"><i class="bi bi-person-fill"></i></a>
    
    <div class="collapse navbar-collapse" id="navbarNav">
      <div class="mx-auto w-50">
          <form action="index.php" method="get" class="d-flex">
              <input class="form-control search-box px-4" type="search" name="cari" placeholder="Cari ayam, kentang, atau kopi..." aria-label="Search">
          </form>
      </div>
      <ul class="navbar-nav ms-auto">
        <li class="nav-item"><a class="btn btn-primary px-4 rounded-pill" href="admin/login.php">Login Staff</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="container mt-4 mb-4 text-center">
    <div class="row g-2 justify-content-center">
        
        <div class="col-3 col-md-2">
            <a href="index.php?kat=Makanan" class="text-decoration-none">
                <div class="cat-icon"><i class="bi bi-egg-fried fs-3"></i></div>
                <span class="cat-text">Makanan</span>
            </a>
        </div>

        <div class="col-3 col-md-2">
            <a href="index.php?kat=Minuman" class="text-decoration-none">
                <div class="cat-icon"><i class="bi bi-cup-hot fs-3"></i></div>
                <span class="cat-text">Minuman</span>
            </a>
        </div>

        <div class="col-3 col-md-2">
            <a href="index.php?kat=Fashion" class="text-decoration-none">
                <div class="cat-icon"><i class="bi bi-handbag fs-3"></i></div>
                <span class="cat-text">Fashion</span>
            </a>
        </div>

        <div class="col-3 col-md-2">
            <a href="index.php" class="text-decoration-none">
                <div class="cat-icon"><i class="bi bi-grid-fill fs-3"></i></div>
                <span class="cat-text">Semua</span>
            </a>
        </div>

    </div>
</div>
<div class="container mb-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h4 class="fw-bold mb-0">Rekomendasi Produk</h4>
        <a href="#" class="text-decoration-none small fw-bold">Lihat Semua</a>
    </div>

    <div class="row row-cols-2 row-cols-md-3 row-cols-lg-4 g-3">
        <?php 
        // Loop data dari database
        if(mysqli_num_rows($ambil) > 0) {
            while($p = mysqli_fetch_array($ambil)){ 
        ?>
        <div class="col">
            <div class="card h-100 card-produk shadow-sm">
                <div class="img-wrapper">
                    <img src="admin/gambar/<?php echo $p['gambar']; ?>" alt="<?php echo $p['nama_produk']; ?>">
                </div>
                <div class="card-body p-3">
                    <h6 class="card-title text-dark mb-1 text-truncate"><?php echo $p['nama_produk']; ?></h6>
                    <p class="harga fw-bold mb-3">Rp <?php echo number_format($p['harga'], 0, ',', '.'); ?></p>
                    <a href="#" class="btn btn-primary w-100 btn-beli">
                        <i class="bi bi-cart-plus me-2"></i>Beli
                    </a>
                </div>
            </div>
        </div>
        <?php 
            } 
        } else {
            echo "<div class='col-12 text-center py-5'><p class='text-muted'>Belum ada produk yang dijual.</p></div>";
        }
        ?>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>