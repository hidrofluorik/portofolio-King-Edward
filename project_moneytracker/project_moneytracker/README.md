# Money Tracker Application

Aplikasi Money Tracker adalah aplikasi desktop berbasis Java Swing untuk mengelola keuangan pribadi dengan fitur pencatatan pemasukan dan pengeluaran.

## Fitur Utama

### 🔐 Sistem Autentikasi

- **Login** dengan username dan password
- **Register** akun baru dengan validasi
- **Logout** dengan konfirmasi

### 💰 Dashboard Utama

- **Ringkasan bulanan** pemasukan dan pengeluaran
- **Filter berdasarkan bulan** menggunakan JMonthChooser
- **Navigasi** ke halaman kelola transaksi
- **Tampilan jumlah transaksi** dalam periode yang dipilih

### 📊 Kelola Transaksi

- **CRUD lengkap** (Create, Read, Update, Delete) transaksi
- **Tabel terpisah** untuk Income dan Outcome
- **Form input** dengan validasi:
  - ID Transaksi (auto-generated)
  - Kategori (Belanja, Jajan, Gaji, Bonus, Transport, Makanan, Hiburan, Tagihan, Lainnya)
  - Type (Income/Outcome)
  - Amount (jumlah uang)
  - Date (menggunakan JDateChooser)
  - Keterangan
- **Selection handling** - klik pada tabel untuk edit
- **Clear form** otomatis setelah operasi berhasil

## Struktur Database

### Tabel `user`

```sql
CREATE TABLE user (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nama VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    nomorHp VARCHAR(15) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);
```

### Tabel `transactions`

```sql
CREATE TABLE transactions (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_nama TEXT NOT NULL,
    date TEXT NOT NULL,
    amount REAL NOT NULL,
    type TEXT NOT NULL,
    kategori TEXT,
    keterangan TEXT,
    FOREIGN KEY (user_nama) REFERENCES user(nama)
);
```

## Teknologi yang Digunakan

- **Java Swing** - GUI Framework
- **SQLite** - Database
- **JCalendar** - Date picker components (JMonthChooser, JDateChooser)
- **MVC Pattern** - Arsitektur aplikasi

## Struktur Project

```
src/
├── project_moneytracker/
│   └── Project_moneytracker.java    # Main class
├── view/
│   ├── loginview.java               # Login form
│   ├── registerview.java            # Register form
│   ├── mainview.java                # Dashboard utama
│   └── transaksiview.java           # Form kelola transaksi
├── controller/
│   ├── loginController.java         # Login logic
│   ├── registerController.java      # Register logic
│   ├── mainController.java          # Dashboard logic
│   └── transaksiController.java     # Transaction CRUD logic
├── dao/
│   ├── userDao.java                 # User database operations
│   └── transaksiDao.java            # Transaction database operations
├── model/
│   ├── user.java                    # User entity
│   └── transaction.java             # Transaction entity
└── config/
    ├── koneksi.java                 # Database connection
    └── DatabaseInitializer.java     # Database setup
```

## Cara Menjalankan

### Prerequisites

1. **Java JDK 8+**
2. **SQLite JDBC Driver** (sqlite-jdbc-x.x.x.jar)
3. **JCalendar Library** (jcalendar-x.x.x.jar)

### Langkah-langkah

1. **Clone atau download** project
2. **Tambahkan library** yang diperlukan ke classpath:
   - sqlite-jdbc-x.x.x.jar
   - jcalendar-x.x.x.jar
3. **Compile** semua file Java:
   ```bash
   javac -cp "lib/*:src" src/project_moneytracker/Project_moneytracker.java src/view/*.java src/controller/*.java src/dao/*.java src/model/*.java src/config/*.java
   ```
4. **Jalankan** aplikasi:
   ```bash
   java -cp "lib/*:src" project_moneytracker.Project_moneytracker
   ```

### Atau menggunakan IDE

1. **Import project** ke IDE (NetBeans, IntelliJ IDEA, Eclipse)
2. **Tambahkan library** ke project dependencies
3. **Run** `Project_moneytracker.java`

## Fitur Validasi

### Register

- Username minimal 3 karakter
- Password minimal 6 karakter
- Nomor HP harus berupa angka
- Cek duplikasi username

### Login

- Validasi username dan password tidak boleh kosong
- Autentikasi dengan database

### Transaksi

- Semua field wajib diisi
- Amount harus berupa angka
- Date tidak boleh kosong

## Database Auto-Setup

Aplikasi akan otomatis membuat database dan tabel yang diperlukan saat pertama kali dijalankan melalui `DatabaseInitializer.java`.

## Screenshots

### Login Screen

- Form login dengan username dan password
- Link ke halaman register

### Dashboard

- Summary pemasukan dan pengeluaran bulanan
- Month selector untuk filter
- Tombol navigasi ke kelola transaksi dan logout

### Kelola Transaksi

- Form input transaksi lengkap
- Tabel Income dan Outcome terpisah
- Tombol Save, Update, Delete

## Kontribusi

Aplikasi ini dibuat untuk keperluan pembelajaran dan dapat dikembangkan lebih lanjut dengan fitur-fitur tambahan seperti:

- Export data ke Excel/PDF
- Grafik dan chart
- Kategori custom
- Multi-user dengan role
- Backup dan restore data

## License

Open source - bebas digunakan dan dikembangkan.
