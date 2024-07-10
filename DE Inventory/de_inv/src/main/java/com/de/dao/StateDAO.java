package com.de.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.de.bean.State;

public class StateDAO {
    public List<State> getStatesByCountry(int countryId) throws ClassNotFoundException {
        List<State> states = new ArrayList<>();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/deinventory", "deinventory", "Amti@123$");
             PreparedStatement ps = con.prepareStatement("SELECT * FROM school WHERE cc_id = ?")) {

            ps.setInt(1, countryId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    State state = new State();
                    state.setId(rs.getInt("school_id"));
                    state.setName(rs.getString("school_name"));
                    state.setCountryId(rs.getInt("cc_id"));
                    states.add(state);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return states;
    }
}
