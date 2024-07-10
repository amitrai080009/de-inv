package com.de.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.de.bean.City;

public class CityDAO {
    public List<City> getCitiesByState(int stateId) {
        List<City> cities = new ArrayList<>();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/deinventory", "deinventory", "Amti@123$");
             PreparedStatement ps = con.prepareStatement("SELECT * FROM student WHERE school_id = ?")) {

            ps.setInt(1, stateId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    City city = new City();
                    city.setId(rs.getInt("student_id"));
                    city.setName(rs.getString("student_name"));
                    city.setStateId(rs.getInt("school_id"));
                    city.setUdise(rs.getString("UDISE"));
                    
                    cities.add(city);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
