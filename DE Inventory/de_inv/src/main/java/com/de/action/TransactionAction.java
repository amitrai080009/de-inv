package com.de.action;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.de.bean.Item;
import com.de.bean.ItemTransaction;
import com.de.bean.Official;
import com.de.bean.Record;
import com.de.dao.DaoInv;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TransactionAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String idimei;
    //private Record record;
    
    private ItemTransaction record;
    private String message;

	/*
	 * public Record getRecord() { return record; }
	 */

    @Override
    public String execute() throws Exception {
        String url = "jdbc:mysql://localhost:3306/deinventory";
        String username = "deinventory";
        String password = "Amti@123$";
        
        HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		String sessionUser = (String) session.getAttribute("loggedUserName");
		/* to check if user exists in session then success else go to login page */
		if (sessionUser == null || sessionUser.equals("")) {
			return "login";
		} 

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM de_transaction_master WHERE IMEI=? and active_transaction=1";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                //statement.setInt(1, id);
            	statement.setString(1, idimei);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        record = new ItemTransaction();
                        record.setDate(resultSet.getString("date"));
                        record.setDevice(resultSet.getString("item_name"));
                        record.setIMEI(resultSet.getString("IMEI"));
                        record.setType(resultSet.getString("type"));
                        record.setFrom(resultSet.getString("from_location"));
                        record.setTo(resultSet.getString("to_location"));
                        record.setFilepath(resultSet.getString("filepath"));
                        return SUCCESS;
                    } else {
                        message = "INVALID IMEI! (No record found for ID:)- " + idimei;
                        return ERROR;
                    }
                }
            }
        } catch (SQLException e) {
            message = "Database error: " + e.getMessage();
            return ERROR;
        }
    }

	public String getIdimei() {
		return idimei;
	}

	public void setIdimei(String idimei) {
		this.idimei = idimei;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public ItemTransaction getRecord() {
		return record;
	}
	
}
