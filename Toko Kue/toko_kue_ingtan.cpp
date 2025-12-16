#include <iostream>
#include <iomanip>
#include <string>
using namespace std;

struct beli {
    string nama;
    string pengaduan;
};

struct barang {
    int id;
    string nama;
    int jumlah;
    float harga;
};

// Array Barang, Isi Data Awal
barang data_barang[100] = {
    {1, "Kue Sus", 10, 3000},
    {2, "Brownies", 10, 15000},
    {3, "Hot Chocolate", 10, 5000},
    {4, "Milk Shake", 10, 10000},
    {5, "Teh Manis", 10, 500},
    {6, "Paket Pasutri", 10, 25000},
    {7, "Paket Selingkuh", 10, 15000},
    {8, "Paket Jomblo", 10, 4000}
};
int hitung = 8; // Awal data barang berisi 8 barang

// Fungsi cari ID terbesar di data_barang
int tambah_id(int max_id = 0){
    for(int i = 0; i < hitung; i++) {
        if (data_barang[i].id > max_id) {
            max_id = data_barang[i].id;
        }
    }
    return max_id;
}

void bersihkan() {
    cout << "\033[2J\033[1;1H";
}

void menu_admin() {
    cout << "========== MENU ADMIN ==========\n";
    cout << "1. Buat Data\n";
    cout << "2. Tampilkan Data\n";
    cout << "3. Ubah Data\n";
    cout << "4. Hapus Data\n";
    cout << "5. Keluar\n";
    cout << "================================\n";
}

void buat_data() {
    if (hitung < 100) {
        int id_baru = tambah_id() + 1;
        data_barang[hitung].id = id_baru;

        cout << "Masukkan Nama Barang: ";
        cin >> data_barang[hitung].nama;
        cout << "Masukkan Jumlah Barang: ";
        cin >> data_barang[hitung].jumlah;
        cout << "Masukkan Harga Barang: ";
        cin >> data_barang[hitung].harga;

        hitung++;
        cout << "Barang berhasil ditambahkan dengan ID: " << id_baru << endl;
    } else {
        cout << "Data sudah penuh!\n";
    }
}

void tampil_data() {
    cout << "======================== DATA BARANG =======================\n";
    for (int i = 0; i < hitung; i++) {
        cout << data_barang[i].id << ". " << data_barang[i].nama
             << " (Stok: " << data_barang[i].jumlah << ") - Rp " << data_barang[i].harga << endl;
    }
    cout << "============================================================\n";
    cout << "\t\tMenu Paket\t\t\n";
    cout << "============================================================\n";
    cout << "Paket Pasutri\n -Kue sus\n -Brownies\n -Milkshake" << endl;
    cout << endl;
    cout << "Paket Selingkuh\n -Hot Chocholate\n -Milkshake" << endl;
    cout << endl;
    cout << "Paket Jomblo\n -Teh Manis 4" << endl;
    cout << "============================================================\n";
}

void ubah_data() {
    int id, index = -1;
    cout << "\n=== Edit Data ===\n";
    cout << "Masukkan ID data yang ingin diubah: ";
    cin >> id;

    for (int i = 0; i < hitung; i++) {
        if (data_barang[i].id == id)
            {
            index = i;
            break;
        }
    }

    if (index == -1) {
        cout << "ID tidak ditemukan!\n";
        return;
    }

    cout << "Data saat ini:\n";
    cout << "ID: " << data_barang[index].id << ", Nama: " << data_barang[index].nama
         << ", Stok: " << data_barang[index].jumlah << ", Harga: " << data_barang[index].harga << endl;

    cout << "Masukkan Nama baru: ";
    cin >> data_barang[index].nama;
    cout << "Masukkan Jumlah baru: ";
    cin >> data_barang[index].jumlah;
    cout << "Masukkan Harga baru: ";
    cin >> data_barang[index].harga;

    cout << "Data berhasil diubah.\n";
}

void hapus_data() {
    int id, index = -1;
    cout << "Masukkan ID data yang ingin dihapus: ";
    cin >> id;

    for (int i = 0; i < hitung; i++) {
        if (data_barang[i].id == id) {
            index = i;
            break;
        }
    }

    if (index == -1) {
        cout << "ID tidak ditemukan!\n";
        return;
    }

    for (int i = index; i < hitung - 1; i++) {
        data_barang[i] = data_barang[i + 1];
    }
    hitung--;

    cout << "Data berhasil dihapus.\n";
}

void pelanggan() {
    beli data_beli[2];
    float bayar = 0;
    int pilih;

    do {
        cout << "\n1. Tentang Toko\n2. Pengaduan\n3. Menu Makanan\n4. Beli Barang\n5. Keluar\nPilih Fitur: ";
        cin >> pilih;

               if (pilih < 1 ){
                cout << "" << endl;
                cout << "PILIHAN ANDA SALAH" << endl;
               }
               if (pilih > 5){
                cout << "" << endl;
                cout << " PILIHAN ANDA SALAH" << endl;
               }
               if (pilih == 1) {

            cout << "============================================================" << endl;
            cout << "\t\t\t TENTANG TOKO KUE BU INTAN"                             << endl;
            cout << "============================================================" << endl;
            cout << "Selamat datang di Toko Kue Bu Intan!"                         << endl;
            cout << "Kami telah berdiri sejak tahun 1945 dengan fokus memberikan"  << endl;
            cout << "kue dan camilan berkualitas tinggi untuk pelanggan kami."     << endl;
            cout << endl;
            cout << "Visi kami adalah menjadi toko kue pilihan utama dengan"       << endl;
            cout << "mengutamakan rasa, kebersihan, dan pelayanan terbaik."        << endl;
            cout << endl;
            cout << "Keunggulan kami:"                                             << endl;
            cout << "1. Bahan berkualitas tinggi, tanpa bahan pengawet."           << endl;
            cout << "2. Beragam pilihan menu, dari klasik hingga modern."          << endl;
            cout << "3. Harga terjangkau untuk semua kalangan."                    << endl;
            cout << "4. Promo menarik setiap bulan!"                               << endl;
            cout << "============================================================" << endl;
        } else if (pilih == 2) {
    // Memasukkan Nama Pengguna
    cout << "\n====================================\n";
    cout << "            FORM PENGADUAN          \n";
    cout << "====================================\n";
    cout << "Masukkan Nama     : ";
    getline(cin >> ws, data_beli[0].nama);

    // Memasukkan Pengaduan Pengguna
    cout << "Masukkan Pengaduan: ";
    getline(cin >> ws, data_beli[1].pengaduan);
    cout << "====================================\n\n";

    // Menampilkan Struk Pengaduan
    cout << "=========== STRUK PENGADUAN ==========\n";
    cout << "Nama     : " << data_beli[0].nama << endl;
    cout << "Pengaduan: " << data_beli[1].pengaduan << endl;
    cout << "======================================\n";
    cout << "Terima kasih atas masukan Anda.\n";
    cout << "======================================\n";
}
         else if (pilih == 3) {
            tampil_data();
        } else if (pilih == 4){
            int id_barang, index = -1, jumlah;

            // Meminta ID barang yang ingin dibeli
            cout << "\nMasukkan ID Barang yang ingin dibeli: ";
            cin >> id_barang;

            // Mencari barang berdasarkan ID
            for (int i = 0; i < hitung; i++) {
            if (data_barang[i].id == id_barang) {
            index = i;  // Barang ditemukan, simpan indeks
            break;
            }
            }

            // Jika barang tidak ditemukan
            if (index == -1) {
            cout << "\nBarang dengan ID tersebut tidak ditemukan!\n";
            } else {
            // Meminta jumlah barang yang ingin dibeli
            cout << "Jumlah yang ingin dibeli: ";
            cin >> jumlah;

            // Cek apakah stok mencukupi
            if (jumlah > data_barang[index].jumlah) {
            cout << "\nStok tidak mencukupi!\n";
            }
            else {
            // Proses pembelian
            data_barang[index].jumlah -= jumlah; // Kurangi stok
            bayar += data_barang[index].harga * jumlah; // Hitung total harga

            // Menampilkan detail pembelian
            cout << "\n=========== DETAIL PEMBELIAN ===========\n";
            cout << "Nama Barang : " << data_barang[index].nama << endl;
            cout << "ID Barang   : " << data_barang[index].id << endl;
            cout << "Jumlah Beli : " << jumlah << endl;
            cout << "Total Harga : Rp " << bayar << endl;
            cout << "========================================\n";
            cout << "Barang berhasil dibeli. Terima kasih!\n";
        }
    }
}
    }
     while (pilih != 5);
}

int main() {
    int pilihan_utama, pilihan_admin;

    do {
        cout << "\n1. Admin\n2. Pelanggan\n3. Keluar\nPilih fitur: ";
        cin >> pilihan_utama;

        if(pilihan_utama < 1){
        cout << "" << endl;
        cout << "PILIHAN ANDA SALAH" << endl;
        }

        if(pilihan_utama > 3){
        cout << "" << endl;
        cout << "PILIH ANDA SALAH" << endl;
        }

        if (pilihan_utama == 1) {
            do {
                menu_admin();
                cout << "Pilih menu admin: ";
                cin >> pilihan_admin;

                switch (pilihan_admin) {
                    case 1: buat_data(); break;
                    case 2: tampil_data(); break;
                    case 3: ubah_data(); break;
                    case 4: hapus_data(); break;
                    case 5: cout << "Keluar dari menu admin.\n"; break;
                    default: cout << "Pilihan tidak valid.\n";
                }
            } while (pilihan_admin != 5);
        } else if (pilihan_utama == 2) {
            pelanggan();
        }
    } while (pilihan_utama != 3);

    cout << "Terima kasih telah menggunakan program ini.\n";
    return 0;
}