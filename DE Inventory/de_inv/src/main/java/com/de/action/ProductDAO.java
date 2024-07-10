package com.de.action;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public List<Product> searchProducts(String query) {
        List<Product> products = new ArrayList<>();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/deinventory", "deinventory", "Amti@123$");
             PreparedStatement ps = con.prepareStatement("SELECT * FROM de_item_master WHERE IMEI LIKE ? and issue_flag = 0")) {
            ps.setString(1, "%" + query + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("item_id"));
                    product.setName(rs.getString("IMEI"));
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
