<?php 
include 'header.php'; 
include 'koneksi.php'; 
?>

<h2>Laporan Stok Produk Saat Ini</h2>
<p class="text-muted">Data per tanggal: <?php echo date('d-m-Y'); ?></p>

<table class="table table-bordered">
    <thead class="table-primary">
        <tr>
            <th>No</th>
            <th>Nama Produk</th>
            <th>Harga Jual</th>
            <th>Sisa Stok</th>
            <th>Status</th>
        </tr>
    </thead>
    <tbody>
        <?php 
        $no = 1;
        $sql = mysqli_query($koneksi, "SELECT * FROM produk ORDER BY stok ASC");
        while($p = mysqli_fetch_array($sql)){
            // Kasih warna merah kalau stok mau habis
            $status = ($p['stok'] <= 5) ? "<span class='badge bg-danger'>Hampir Habis</span>" : "<span class='badge bg-success'>Aman</span>";
        ?>
        <tr>
            <td><?php echo $no++; ?></td>
            <td><?php echo $p['nama_produk']; ?></td>
            <td>Rp <?php echo number_format($p['harga'], 0, ',', '.'); ?></td>
            <td><strong><?php echo $p['stok']; ?></strong></td>
            <td><?php echo $status; ?></td>
        </tr>
        <?php } ?>
    </tbody>
</table>

<?php include 'footer.php'; ?>