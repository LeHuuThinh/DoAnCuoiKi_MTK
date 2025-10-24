package dao;

import model.Product;
import singleton.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrinkDAO {

    // Lấy toàn bộ đồ uống từ DB
    public List<Product> getAllDrinks() {
        List<Product> drinks = new ArrayList<>();
        String query = "SELECT * FROM drinks";

        try (Connection connection = DatabaseHelper.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");

                drinks.add(new Product(id, name, price));
            }

        } catch (SQLException e) {
            System.out.println("❌ Loi khi lay danh sach do uong!");
            e.printStackTrace();
        }

        return drinks;
    }
}
