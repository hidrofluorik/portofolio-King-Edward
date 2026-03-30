<?php
include 'koneksi.php';

// Query buat ambil jumlah produk masuk per bulan di tahun 2026
$query = mysqli_query($koneksi, "
    SELECT MONTHNAME(tanggal) as bulan, SUM(jumlah) as total 
    FROM produk_masuk 
    JOIN produk_masuk_detail ON produk_masuk.id_masuk = produk_masuk_detail.id_masuk 
    WHERE YEAR(tanggal) = '2026'
    GROUP BY MONTH(tanggal)
");

$labels = [];
$data = [];

while($row = mysqli_fetch_array($query)){
    $labels[] = $row['bulan'];
    $data[] = $row['total'];
}

// Kirim data dalam format JSON biar bisa dibaca JavaScript
$result = [
    'labels' => $labels,
    'data' => $data
];

echo json_encode($result);
?>