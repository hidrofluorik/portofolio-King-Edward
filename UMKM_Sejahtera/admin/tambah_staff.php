<?php include 'header.php'; ?>
<h3>Tambah Staff Baru</h3>
<form action="simpan_staff.php" method="POST">
    <div class="mb-3">
        <label>Username</label>
        <input type="text" name="username" class="form-control" required>
    </div>
    <div class="mb-3">
        <label>Password</label>
        <input type="password" name="password" class="form-control" required>
    </div>
    <div class="mb-3">
        <label>Nama Lengkap</label>
        <input type="text" name="nama" class="form-control" required>
    </div>
    <div class="mb-3">
        <label>Role</label>
        <select name="role" class="form-control">
            <option value="admin">Admin</option>
            <option value="staff">Staff</option>
            <option value="pemilik">Pemilik / CEO</option>
        </select>
    </div>
    <button type="submit" class="btn btn-success">Simpan</button>
</form>
<?php include 'footer.php'; ?>