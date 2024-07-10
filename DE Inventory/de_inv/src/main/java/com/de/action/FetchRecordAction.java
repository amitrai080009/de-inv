package com.de.action;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.de.bean.*;
import com.de.bean.Record;

public class FetchRecordAction extends ActionSupport {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private Record record;
    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Record getRecord() {
        return record;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String execute() throws Exception {
        String url = "jdbc:mysql://localhost:3306/deinventory";
        String username = "deinventory";
        String password = "Amti@123$";
        
        //Session Checking
        HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		String sessionUser = (String) session.getAttribute("loggedUserName");
		/* to check if user exists in session then success else go to login page */
		if (sessionUser == null || sessionUser.equals("")) {
			return "login";
		} 
		///////////////////////////

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM employees WHERE id=?";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        record = new Record();
                        record.setName(resultSet.getString("name"));
                        record.setAge(resultSet.getInt("age"));
                        return SUCCESS;
                    } else {
                        message = "No record found for ID: " + id;
                        return ERROR;
                    }
                }
            }
        } catch (SQLException e) {
            message = "Database error: " + e.getMessage();
            return ERROR;
        }
    }
}
