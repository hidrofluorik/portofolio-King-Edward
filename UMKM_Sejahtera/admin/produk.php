<?php 
// Panggil bagian kepala (HTML, CSS, Navigasi, Session Check)
include 'header.php'; 

// Panggil koneksi database
include 'koneksi.php'; 
?>

<div class="row">
    <div class="col-md-12">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Data Produk UMKM</h2>
            <a href="tambah_produk.php" class="btn btn-primary"> + Tambah Produk</a>
        </div>
        
        <div class="table-responsive">
            <table id="tabelProduk" class="table table-bordered table-striped">
                <thead class="table-dark">
                    <tr>
                        <th width="5%">No</th>
                        <th width="15%">Gambar</th>
                        <th>Nama Produk</th>
                        <th>Harga</th>
                        <th>Stok</th>
                        <th width="15%">Aksi</th>
                    </tr>
                </thead>
                <tbody>
                    <?php 
                    $no = 1;
                    $data = mysqli_query($koneksi, "SELECT * FROM produk");
                    while($d = mysqli_fetch_array($data)){
                    ?>
                    <tr>
                        <td><?php echo $no++; ?></td>
                        <td>
                            <img src="gambar/<?php echo $d['gambar']; ?>" width="70" class="img-thumbnail">
                        </td>
                        <td><?php echo $d['nama_produk']; ?></td>
                        <td>Rp <?php echo number_format($d['harga'], 0, ',', '.'); ?></td>
                        <td><?php echo $d['stok']; ?></td>
                        <td>
                            <a href="edit_produk.php?id=<?php echo $d['id_produk']; ?>" class="btn btn-warning btn-sm">Edit</a>
                            <a href="hapus_produk.php?id=<?php echo $d['id_produk']; ?>" class="btn btn-danger btn-sm" onclick="return confirm('Yakin mau hapus data ini?')">Hapus</a>
                        </td>
                    </tr>
                    <?php 
                    } 
                    ?>
                </tbody>
            </table>
        </div>
    </div>
</div>

<?php 
// Panggil bagian kaki (Script JS, Penutup HTML)
include 'footer.php'; 
?>