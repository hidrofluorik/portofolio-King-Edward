<?php include 'header.php'; include 'koneksi.php'; ?>
<div class="d-flex justify-content-between align-items-center mb-4">
    <h2>Data Staff / Pengguna</h2>
    <a href="tambah_staff.php" class="btn btn-primary">+ Tambah Staff</a>
</div>

<table id="tabelProduk" class="table table-bordered table-striped">
    <thead class="table-dark">
        <tr>
            <th>No</th>
            <th>Username</th>
            <th>Nama Lengkap</th>
            <th>Role / Jabatan</th>
            <th>Aksi</th>
        </tr>
    </thead>
    <tbody>
        <?php 
        $no = 1;
        $data = mysqli_query($koneksi, "SELECT * FROM staff");
        while($d = mysqli_fetch_array($data)){
        ?>
        <tr>
            <td><?php echo $no++; ?></td>
            <td><?php echo $d['username']; ?></td>
            <td><?php echo $d['nama']; ?></td>
            <td><span class="badge bg-info text-dark"><?php echo $d['role']; ?></span></td>
            <td>
                <a href="edit_staff.php?id=<?php echo $d['id']; ?>" class="btn btn-warning btn-sm">Edit</a>
                <a href="hapus_staff.php?id=<?php echo $d['id']; ?>" class="btn btn-danger btn-sm" onclick="return confirm('Hapus staff ini?')">Hapus</a>
            </td>
        </tr>
        <?php } ?>
    </tbody>
</table>
<?php include 'footer.php'; ?>