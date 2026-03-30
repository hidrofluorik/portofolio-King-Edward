</div> <footer class="footer-admin">
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <h5>Admin Panel</h5>
                <p>Sistem Manajemen Produk berbasis PHP, MySQL, Bootstrap dan DataTables untuk UMKM.</p>
            </div>
            <div class="col-md-4 text-center">
                <h5>Sitemap</h5>
                <ul class="list-unstyled">
                    <li><a href="dashboard.php" class="text-white text-decoration-none">Dashboard</a></li>
                    <li><a href="produk.php" class="text-white text-decoration-none">Produk</a></li>
                </ul>
            </div>
            <div class="col-md-4">
                <h5>Informasi</h5>
                <p>Email : admin@email.com<br>Telp : 0812-XXXX-XXXX</p>
            </div>
        </div>
    </div>
</footer>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>

<script>
    $(document).ready(function () {
        $('#tabelProduk').DataTable();
    });
</script>
</body>
</html>