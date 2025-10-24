-- XÓA BẢNG CŨ NẾU CÓ
DROP TABLE IF EXISTS drinks;
DROP TABLE IF EXISTS order_history;

-- TẠO BẢNG ĐỒ UỐNG (drinks)
CREATE TABLE IF NOT EXISTS drinks (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    price REAL NOT NULL
);

-- TẠO BẢNG LỊCH SỬ ĐẶT HÀNG (order_history)
CREATE TABLE IF NOT EXISTS order_history (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    items TEXT NOT NULL,
    total_price REAL NOT NULL,
    payment_method TEXT NOT NULL,
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- THÊM MỘT SỐ ĐỒ UỐNG MẪU
INSERT INTO drinks (name, price) VALUES ('Tra sua truyen thong', 25000);
INSERT INTO drinks (name, price) VALUES ('Ca phe sua', 20000);
INSERT INTO drinks (name, price) VALUES ('Tra dao', 30000);
INSERT INTO drinks (name, price) VALUES ('Latte', 35000);
INSERT INTO drinks (name, price) VALUES ('Americano', 28000);
