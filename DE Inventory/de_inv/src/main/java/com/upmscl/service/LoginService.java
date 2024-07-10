package com.upmscl.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.upmscl.bean.User;
import com.upmscl.dao.GetConnection;

public class LoginService extends ActionSupport {

	private static final long serialVersionUID = 1L;
	/*
	 * Connection Elements
	 */
	GetConnection Conn = new GetConnection();
	PreparedStatement stmt = null;
	ResultSet rs = null;

	/*
	 * Connection Elements
	 */
	public boolean verifyLogin(User user) throws Exception {
		try {
			// Prepare the SQL query
			String sql = "SELECT id FROM login_credentials WHERE id = ? AND password = ?";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			rs = ps.executeQuery();
			if (rs.next()) {
				user.setMsg("pass match and loggedin successfully");
				return true;
			} else {
				addActionError("Invalid username or password");
				user.setMsg("pass not match");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			addActionError("An error occurred during login");
			return false;

		} finally {
			// Close resources
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				// if (Conn != null) Conn.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void addLoginTrail(String clientIP, String macAdress, String dateTimeNow, String username)
			throws SQLException, Exception {

		PreparedStatement preparedStatement = Conn.getConnection()
				.prepareStatement("update login_credentials set last_login=?, user_ip=?, user_mac=? where id=?");
		preparedStatement.setString(1, dateTimeNow);
		preparedStatement.setString(2, clientIP);
		preparedStatement.setString(3, macAdress);
		preparedStatement.setString(4, username);

		int i = preparedStatement.executeUpdate();

		if (i > 0) {
			// setMsg("Tender successfully Updated.");
			System.out.print("Last Login Updated successfully");

		} else {
			System.out.print("Last Login Update failed");
		}

	}

	public void addLoginDetails(String username, String clientIP, String systemName, String dateTimeNow)
			throws Exception {

		PreparedStatement preparedStatement = Conn.getConnection().prepareStatement(
				"INSERT INTO login_details set login_username=?, login_ipaddress=?, login_host=?, login_date_time=?");
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, clientIP);
		preparedStatement.setString(3, systemName);
		preparedStatement.setString(4, dateTimeNow);

		int i = preparedStatement.executeUpdate();

		if (i > 0) {
			// setMsg("Tender successfully Updated.");
			System.out.print("Last Login Updated successfully");

		} else {
			System.out.print("Last Login Update failed");
		}

	}

	public void validate(User user) {

	}

	public boolean validateUser(User user, String captcha) {
		if (user.getUsername() != null && user.getUsername().contains("<")) {
			user.setMsg("Input cannot contain HTML tags");
			return false;
		} else if (user.getPassword() == null) {
			user.setMsg("Password is blank !");
		} else if (captcha == null) {
			user.setMsg("Captcha field is Blank!");
		} 
		return true;

	}
}
