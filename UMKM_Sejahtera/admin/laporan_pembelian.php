<?php 
include 'header.php'; 
include 'koneksi.php'; 
?>

<div class="d-flex justify-content-between align-items-center mb-4">
    <h2>Laporan Riwayat Pembelian</h2>
    <button onclick="window.print()" class="btn btn-secondary">Cetak Laporan (PDF)</button>
</div>

<div class="table-responsive">
    <table id="tabelProduk" class="table table-bordered table-striped">
        <thead class="table-dark">
            <tr>
                <th>No</th>
                <th>Tanggal</th>
                <th>Petugas (Staff)</th>
                <th>Rincian Barang (Produk - Jumlah)</th>
            </tr>
        </thead>
        <tbody>
    <?php 
    $no = 1;
    // 1. Ambil data utama transaksi
    $transaksi = mysqli_query($koneksi, "SELECT * FROM produk_masuk ORDER BY id_masuk DESC");
    
    // Cek apakah query utama jalan
    if($transaksi){
        while($t = mysqli_fetch_array($transaksi)){
            $id_m = $t['id_masuk'];
    ?>
    <tr>
        <td><?php echo $no++; ?></td>
        <td><?php echo date('d-m-Y', strtotime($t['tanggal'])); ?></td>
        <td>
            <?php 
            $id_s = $t['id_staff'];
            // Cari nama staff, sesuaikan 'id' dengan nama kolom di tabel staff lu
            $s_query = mysqli_query($koneksi, "SELECT nama FROM staff WHERE id='$id_s' LIMIT 1");
            $s_data = mysqli_fetch_array($s_query);
            echo ($s_data) ? $s_data['nama'] : "Admin (ID: $id_s)";
            ?>
        </td>
        <td>
            <ul>
                <?php 
                // 2. Ambil detail produk. PASTIIN nama tabelnya 'produk_masuk_detail'
                // PASTIIN juga nama kolom di tabel produk adalah 'id_produk' dan 'nama_produk'
                $q_detail = "SELECT * FROM produk_masuk_detail JOIN produk ON produk_masuk_detail.id_produk = produk.id_produk WHERE id_masuk = '$id_m'";
                $detail = mysqli_query($koneksi, $q_detail);

                if($detail && mysqli_num_rows($detail) > 0){
                    while($d = mysqli_fetch_array($detail)){
                        echo "<li>".$d['nama_produk']." (".$d['jumlah'].")</li>";
                    }
                } else {
                    echo "<li class='text-muted small'>Data detail tidak ditemukan</li>";
                }
                ?>
            </ul>
        </td>
    </tr>
    <?php 
        } 
    } else {
        echo "<tr><td colspan='4'>Gagal mengambil data: ".mysqli_error($koneksi)."</td></tr>";
    }
    ?>
</tbody>
    </table>
</div>

<?php include 'footer.php'; ?>