package com.de.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.de.bean.Item;
import com.de.bean.ItemTransaction;
import com.opensymphony.xwork2.ActionSupport;

public class FetchIssueAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String idimei;
	
	private Item record;
	private String message;
	
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
	            String query = "SELECT de_item_issue_school.date as issue_date, de_item_issue_school.imei as imei, cc.cc_name as cc_name, school.school_name as school_name, "
	            		+ "student.student_name as student_name, student.UDISE as udise, de_item_master.brand as brand_name, de_item_issue_school.filepath as filepath "
	            		+ "FROM deinventory.de_item_issue_school\r\n"
	            		+ "join cc on de_item_issue_school.cc_name = cc.cc_id\r\n"
	            		+ "join school on de_item_issue_school.school_name = school.school_id\r\n"
	            		+ "join student on de_item_issue_school.student_name = student.student_id\r\n"
	            		+ "join de_item_master on de_item_issue_school.imei = de_item_master.IMEI\r\n"
	            		+ "where de_item_issue_school.imei = ? and active_flag= 1";
	            try (PreparedStatement statement = conn.prepareStatement(query)) {
	                //statement.setInt(1, id);
	            	statement.setString(1, idimei);
	                try (ResultSet resultSet = statement.executeQuery()) {
	                    if (resultSet.next()) {
	                        record = new Item();
	                        record.setDate(resultSet.getString("issue_date"));
	                        record.setImei(resultSet.getString("imei"));
	                        record.setCc_name(resultSet.getString("cc_name"));
	                        record.setSchool_name(resultSet.getString("school_name"));
	                        record.setStudent_name(resultSet.getString("student_name"));
	                        record.setUdise(resultSet.getString("udise"));
	                        record.setBrand(resultSet.getString("brand_name"));
	                        record.setFileFileName(resultSet.getString("filepath"));
	                        
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

	public void setMessage(String message) {
		this.message = message;
	}

	public Item getRecord() {
		return record;
	}

	public void setRecord(Item record) {
		this.record = record;
	}
	

}
