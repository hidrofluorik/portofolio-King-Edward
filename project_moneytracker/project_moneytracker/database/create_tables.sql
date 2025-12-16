-- Script untuk membuat tabel-tabel yang diperlukan untuk Money Tracker

-- Tabel user
CREATE TABLE IF NOT EXISTS user (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nama VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    nomorHp VARCHAR(15) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Tabel transactions
CREATE TABLE IF NOT EXISTS transactions (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_nama VARCHAR(50) NOT NULL,
    date DATE NOT NULL,
    type VARCHAR(10) NOT NULL CHECK (type IN ('Income', 'Outcome')),
    kategori VARCHAR(50) NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    keterangan TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_nama) REFERENCES user(nama)
);

-- Index untuk performa yang lebih baik
CREATE INDEX IF NOT EXISTS idx_transactions_user_date ON transactions(user_nama, date);
CREATE INDEX IF NOT EXISTS idx_transactions_type ON transactions(type);
CREATE INDEX IF NOT EXISTS idx_user_nama ON user(nama);

-- Insert data contoh (opsional)
INSERT OR IGNORE INTO user (nama, password, nomorHp) VALUES 
('admin', 'admin123', '081234567890'),
('user1', 'password123', '081234567891');