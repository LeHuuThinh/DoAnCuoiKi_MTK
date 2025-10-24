package dao;

import singleton.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryDAO {

    public void saveOrder(String items, double totalPrice, String paymentMethod) {
        String sql = "INSERT INTO order_history (items, total_price, payment_method, order_date) VALUES (?, ?, ?, datetime('now'))";
        try (Connection conn = DatabaseHelper.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, items);
            ps.setDouble(2, totalPrice);
            ps.setString(3, paymentMethod);
            ps.executeUpdate();

            System.out.println("✅ Da luu lich su don hang vao DB.");
        } catch (SQLException e) {
            System.out.println("❌ Loi khi luu lich su Order: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<String> getOrderHistory() {
        List<String> history = new ArrayList<>();
        String sql = "SELECT id, items, total_price, payment_method, order_date FROM order_history ORDER BY order_date DESC";

        try (Connection conn = DatabaseHelper.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String items = rs.getString("items");
                double total = rs.getDouble("total_price");
                String pm = rs.getString("payment_method");
                String dt = rs.getString("order_date");

                String line = String.format("ID:%d | %s | Tong: %.0f | Tra bang: %s | %s", id, items, total, pm, dt);
                history.add(line);
            }
        } catch (SQLException e) {
            System.out.println("❌ Loi khi doc lich su Order: " + e.getMessage());
            e.printStackTrace();
        }
        return history;
    }
}
