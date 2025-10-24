package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    private static DatabaseHelper instance;
    private static final String DB_URL = "jdbc:sqlite:Database/restaurant.db";

    private DatabaseHelper() {
        try {
            Class.forName("org.sqlite.JDBC"); // nạp driver
            System.out.println("✅ SQLite Driver da san sang!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Khong tim thay driver SQLite!");
            e.printStackTrace();
        }
    }

    public static synchronized DatabaseHelper getInstance() {
        if (instance == null) {
            instance = new DatabaseHelper();
        }
        return instance;
    }

    // ✅ Mỗi lần gọi → tạo connection mới
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // ❌ Không cần closeConnection nữa (vì mỗi DAO đã tự đóng)
    @Deprecated
    public void closeConnection() { }
}
