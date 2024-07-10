package com.de.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.de.bean.Country;

public class CountryDAO {
    public List<Country> getAllCountries() throws ClassNotFoundException {
        List<Country> countries = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/deinventory", "deinventory", "Amti@123$");
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM cc")) {

            while (rs.next()) {
                Country country = new Country();
                country.setId(rs.getInt("cc_id"));
                country.setName(rs.getString("cc_name"));
                countries.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }
}