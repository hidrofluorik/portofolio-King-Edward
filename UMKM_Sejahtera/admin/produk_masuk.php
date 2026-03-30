<?php 
include 'header.php'; 
include 'koneksi.php'; 
?>


<h2>Transaksi Produk Masuk</h2>
<hr>

<div class="card mb-4">
    
    <div class="card-body">
        <form action="keranjang_tambah.php" method="POST">
            <div class="row">
                <div class="col-md-4">
                    <label>Pilih Produk</label>
                    <select name="id_produk" class="form-control" required>
                        <option value="">-- Pilih Produk --</option>
                        <?php 
                        $prod = mysqli_query($koneksi, "SELECT * FROM produk");
                        while($p = mysqli_fetch_array($prod)){
                            echo "<option value='".$p['id_produk']."'>".$p['nama_produk']."</option>";
                        }
                        ?>
                    </select>
                </div>
                <div class="col-md-3">
                    <label>Jumlah Masuk</label>
                    <input type="number" name="jumlah" class="form-control" min="1" required>
                </div>
                <div class="col-md-2">
                    <label>&nbsp;</label>
                    <button type="submit" class="btn btn-primary d-block">Tambah</button>
                </div>
            </div>
        </form>
    </div>
</div>

<h4>Daftar Barang dalam Transaksi Ini</h4>
<table class="table table-bordered table-striped">
    <thead class="table-dark">
        <tr>
            <th>No</th>
            <th>Nama Produk</th>
            <th>Jumlah</th>
            <th>Aksi</th>
        </tr>
    </thead>
    <tbody>
        <?php 
        $no = 1;
        if(isset($_SESSION['keranjang']) && !empty($_SESSION['keranjang'])){
            foreach($_SESSION['keranjang'] as $id => $val){
                $ambil = mysqli_query($koneksi, "SELECT * FROM produk WHERE id_produk='$id'");
                $dt = mysqli_fetch_array($ambil);
                ?>
                <tr>
                    <td><?php echo $no++; ?></td>
                    <td><?php echo $dt['nama_produk']; ?></td>
                    <td><?php echo $val; ?></td>
                    <td>
                        <a href="keranjang_hapus.php?id=<?php echo $id; ?>" class="btn btn-danger btn-sm">Batal</a>
                    </td>
                </tr>
                <?php 
            }
        } else {
            echo "<tr><td colspan='4' class='text-center'>Keranjang masih kosong</td></tr>";
        }
        ?>
    </tbody>
</table>

<?php if(isset($_SESSION['keranjang']) && !empty($_SESSION['keranjang'])): ?>
    <a href="transaksi_simpan.php" class="btn btn-success" onclick="return confirm('Simpan transaksi ini?')">Simpan Transaksi Ke Database</a>
<?php endif; ?>

<?php include 'footer.php'; ?>