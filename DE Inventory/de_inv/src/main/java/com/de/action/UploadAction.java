package com.de.action;

import com.opensymphony.xwork2.ActionSupport;
import com.upmscl.dao.GetConnection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.struts2.ServletActionContext;

public class UploadAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File file;
	private String fileContentType;
	private String fileFileName;
	private String msg;

	GetConnection Conn = new GetConnection();

	// Getters and setters for file, fileContentType, and fileFileName

	public String execute() throws Exception {

		try {

			// Database Connection Strings
			String url = "jdbc:mysql://localhost:3306/deinventory";
			String username = "deinventory";
			String password = "Amti@123$";
			Connection conn = DriverManager.getConnection(url, username, password);

			// Read the CSV file and insert data into MySQL table
			BufferedReader reader = new BufferedReader(new FileReader(file));
			reader.readLine(); // Skip the first line (header) of the CSV file
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");

				// Assuming your_table has columns col1, col2, col3
				String query = "INSERT INTO de_item_master(date, transaction_in_out, item_name, IMEI, brand, item_condition, received_from, particular_name, ref_no, current_location, current_status, warranty_status, warranty_from, warranty_to, issue_flag) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(query);

				pstmt.setString(1, data[0]);
				pstmt.setString(2, data[1]);
				pstmt.setString(3, data[2]);
				pstmt.setString(4, data[3]);
				pstmt.setString(5, data[4]);
				pstmt.setString(6, data[5]);
				pstmt.setString(7, data[6]);
				pstmt.setString(8, data[7]);
				pstmt.setString(9, data[8]);
				pstmt.setString(10, data[9]);
				pstmt.setString(11, data[10]);
				pstmt.setString(12, data[11]);
				pstmt.setString(13, data[12]);
				pstmt.setString(14, data[13]);
				pstmt.setString(15, data[14]);

				pstmt.executeUpdate();
				pstmt.close();
			}
			
			reader.close();
			
		} catch (SQLException e) {
			System.out.print(e);
			setMsg("NAAAA..............!");
		}
		setMsg("Items Data Uploaded to Item Master successfully..............!");
		return SUCCESS;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
