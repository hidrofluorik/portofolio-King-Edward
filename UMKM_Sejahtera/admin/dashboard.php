<?php 
// 1. Panggil Header (Isinya: Session check, HTML Head, dan Navbar)
include 'header.php'; 

// 2. Panggil Koneksi (Kalo mau nampilin statistik jumlah data nanti)
include 'koneksi.php'; 
?>

<div class="row">
    <div class="col-md-12">
        <div class="p-5 mb-4 bg-light rounded-3 border">
            <div class="container-fluid py-5">
                <h1 class="display-5 fw-bold">Selamat Datang, <?php echo $_SESSION['username']; ?>!</h1>
                <p class="col-md-8 fs-4">Ini adalah halaman Administrasi UMKM Sejahtera. Di sini kamu bisa mengelola data produk, memantau stok, dan mengatur akun staff.</p>
                <hr class="my-4">
                <p>Gunakan menu navigasi di atas untuk mulai bekerja.</p>
                <a href="produk.php" class="btn btn-primary btn-lg">Kelola Produk</a>
                <a href="staff.php" class="btn btn-outline-secondary btn-lg">Data Staff</a>
            </div>
        </div>
    </div>
</div>

<div class="row text-center">
    <div class="col-md-4 mb-4">
        <div class="card bg-primary text-white shadow">
            <div class="card-body">
                <h5>Total Produk</h5>
                <?php 
                $query_produk = mysqli_query($koneksi, "SELECT * FROM produk");
                echo "<h2>" . mysqli_num_rows($query_produk) . "</h2>";
                ?>
            </div>
        </div>
    </div>

    <div class="col-md-4 mb-4">
        <div class="card bg-success text-white shadow">
            <div class="card-body">
                <h5>Total Staff</h5>
                <?php 
                $query_staff = mysqli_query($koneksi, "SELECT * FROM staff");
                echo "<h2>" . mysqli_num_rows($query_staff) . "</h2>";
                ?>
            </div>
        </div>
    </div>
    <div class="row mt-4">
    <div class="col-md-12">
        <div class="card shadow-sm">
            <div class="card-header bg-white">
                <h5 class="mb-0">Grafik Pembelian Produk Bulanan (2026)</h5>
            </div>
            <div class="card-body">
                <canvas id="canvasGrafik" width="400" height="150"></canvas>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script>
    // Ambil data dari file grafik_data.php pake Fetch API
    fetch('grafik_data.php')
        .then(response => response.json())
        .then(data => {
            const ctx = document.getElementById('canvasGrafik').getContext('2d');
            new Chart(ctx, {
                type: 'bar', // Bisa ganti 'line' kalau mau grafik garis
                data: {
                    labels: data.labels,
                    datasets: [{
                        label: 'Jumlah Produk Masuk',
                        data: data.data,
                        backgroundColor: 'rgba(0, 123, 255, 0.6)',
                        borderColor: 'rgba(0, 123, 255, 1)',
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        y: { beginAtZero: true }
                    }
                }
            });
        });
</script>
    <div class="col-md-4 mb-4">
        <div class="card bg-warning text-dark shadow">
            <div class="card-body">
                <h5>Status Sistem</h5>
                <h2>AKTIF</h2>
            </div>
        </div>
    </div>
</div>

<?php 
// 3. Panggil Footer (Isinya: Copyright dan Script JS)
include 'footer.php'; 
?>