<?php 
include 'koneksi.php';
$id = $_GET['id'];
mysqli_query($koneksi, "DELETE FROM staff WHERE id='$id'");
header("location:staff.php");
?>