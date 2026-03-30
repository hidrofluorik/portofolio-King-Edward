<?php 
include 'koneksi.php';
$id = $_GET['id'];
$data = mysqli_query($koneksi, "SELECT * FROM produk WHERE id_produk='$id'");
$d = mysqli_fetch_array($data);
?>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Produk</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h3>Edit Data Produk</h3>
    <form action="update_produk.php" method="POST" enctype="multipart/form-data">
        <input type="hidden" name="id" value="<?php echo $d['id_produk']; ?>">
        
        <div class="mb-3">
            <label>Nama Produk</label>
            <input type="text" name="nama_produk" class="form-control" value="<?php echo $d['nama_produk']; ?>" required>
        </div>
        <div class="mb-3">
            <label>Harga</label>
            <input type="number" name="harga" class="form-control" value="<?php echo $d['harga']; ?>" required>
        </div>
        <div class="mb-3">
            <label>Stok</label>
            <input type="number" name="stok" class="form-control" value="<?php echo $d['stok']; ?>" required>
        </div>
        <div class="mb-3">
            <label>Gambar (Kosongkan jika tidak diganti)</label><br>
            <img src="gambar/<?php echo $d['gambar']; ?>" width="100" class="mb-2"><br>
            <input type="file" name="gambar" class="form-control">
        </div>
        <button type="submit" class="btn btn-primary">Update Data</button>
        <a href="produk.php" class="btn btn-secondary">Batal</a>
    </form>
</div>
</body>
</html>