<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <title>Tambah Produk</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h4>Tambah Produk Baru</h4>
                </div>
                <div class="card-body">
                    <form action="simpan_produk.php" method="POST" enctype="multipart/form-data">
                        <div class="mb-3">
                            <label class="form-label">Nama Produk</label>
                            <input type="text" name="nama_produk" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Harga</label>
                            <input type="number" name="harga" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Stok</label>
                            <input type="number" name="stok" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Pilih Gambar Produk</label>
                            <input type="file" name="gambar" class="form-control" accept="image/*" required>
                        </div>
                        <div class="mb-3">
                             <label class="form-label">Kategori Produk</label>
                                <select name="kategori" class="form-control" required>
        <option value="">-- Pilih Kategori --</option>
        <option value="Makanan">Makanan</option>
        <option value="Minuman">Minuman</option>
        <option value="Fashion">Fashion</option>
        <option value="Lainnya">Lainnya</option>
                                 </select>
                        </div>
                        <div class="mt-4">
                            <button type="submit" class="btn btn-success">Simpan Produk</button>
                            <a href="produk.php" class="btn btn-secondary">Kembali</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>