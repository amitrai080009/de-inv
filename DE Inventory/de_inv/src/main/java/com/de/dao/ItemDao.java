package com.de.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.File;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;


import com.upmscl.dao.GetConnection;
import com.de.bean.Item;

public class ItemDao {
	
	//for file options -------------------------------------------
	
	ResultSet c_rs = null;
	ResultSet t_rs = null;
	String loc;

	// Define maximum file size allowed
	private static final long MAX_FILE_SIZE = 100 * 1024 * 1024; // 100 MB

	// Define allowed file types
	// private static final String[] ALLOWED_FILE_TYPES = { "image/jpeg",
	// "image/png", "image/gif" };
	private static final List<String> ALLOWED_FILE_TYPES = Arrays.asList("pdf");

	GetConnection Conn = new GetConnection();
	
	//for file options -------------------------------------------

	private static final String URL = "jdbc:mysql://localhost:3306/deinventory";
	private static final String USER = "deinventory";
	private static final String PASSWORD = "Amti@123$";

	public List<Item> getAllItems() {
		List<Item> itemList = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM de_item_issue_school")) {

			while (rs.next()) {
				Item item = new Item();
				item.setId(rs.getInt("id"));
				item.setDate(rs.getString("date"));
				item.setCc_name(rs.getString("cc_name"));
				item.setSchool_name(rs.getString("school_name"));
				item.setStudent_name(rs.getString("student_name"));
				item.setUdise(rs.getString("udise"));
				item.setImei(rs.getString("imei"));
				item.setFileFileName(rs.getString("filepath"));
				item.setDesc(rs.getString("desc"));

				itemList.add(item); // getting all items/tabs list in ItemList
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemList;
	}

	public Item getItemById(int id) {
		Item item = null;
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = con.prepareStatement("SELECT * FROM de_item_issue_school WHERE id = ?")) {

			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					item = new Item();
					item.setId(rs.getInt("id"));
					item.setDate(rs.getString("date"));
					item.setCc_name(rs.getString("cc_name"));
					item.setSchool_name(rs.getString("school_name"));
					item.setStudent_name(rs.getString("student_name"));
					item.setUdise(rs.getString("udise"));
					item.setImei(rs.getString("imei"));
					item.setFileFileName(rs.getString("filepath"));
					item.setDesc(rs.getString("desc"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}

	public void addItem(Item item) throws Exception {
		
		try {

			// Perform file validation

			if (item.getFile() == null || item.getFile().length() == 0) {
				item.setMsg("Please select a file.");
				System.out.println("no files selected");
				//return false;
			}

			if (item.getFile().length() > MAX_FILE_SIZE) {
				item.setMsg("File size exceeds the maximum allowed limit.");
				System.out.println("file exceeds maximum size limit");
				//return false;
			}
			if (item.getFileContentType() != null && item.getFileContentType().equals("application/pdf")) {

				String filePath = ServletActionContext.getServletContext().getRealPath("/").concat("NewItemIssueChallan/");
				// String filePath = "NewStockChallan/";
				System.out.println("Image Location:" + filePath);// see the server console for actual location
				File fileToCreate = new File(filePath, item.getFileFileName());

				// Code here checks that if a file is uploading as new or with duplicate name
				// if file with some previous duplicate filename then its name will be appended
				// with
				// timestamp like filename + date + timestamp + am/pm

				if (fileToCreate.exists()) {
					System.out.println("File Name Already Esists");
					// item.setMsg("This file name already axists");

					Calendar cal = Calendar.getInstance();
					cal.setTime(Date.from(Instant.now()));

					// Create a filename from a format string.
					// ... Apply date formatting codes.
					String fileNameWithOutExt = FilenameUtils.removeExtension(item.getFileFileName());
					// get file name only without extension

					String ext1 = FilenameUtils.getExtension(item.getFileFileName()); // get file extension like
																							// .pdf

					String newFileName = String
							.format(fileNameWithOutExt + "-%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp." + ext1, cal);
					fileToCreate = new File(filePath, newFileName);
					FileUtils.copyFile(item.getFile(), fileToCreate);// copying source file to new file
					loc = "NewStockChallan/" + newFileName;
				} else {

					FileUtils.copyFile(item.getFile(), fileToCreate);// copying source file to new file
					loc = "NewStockChallan/" + item.getFileFileName();
				}
			} else {
				item.setMsg("Invalid file type. Please upload a PDF file.");
				System.out.print("Invalid file type. Please upload a PDF file.");
				//return false;
			}

			///////////////////////////////////////////////////////////////////////////////////////////
			ResultSet rs = null;
			String imei = "";
			int active_flag = 1;

			String sql = "SELECT * FROM de_item_issue_school where imei = ? and udise = ?";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			ps.setString(1, item.getImei());
			ps.setString(2, item.getUdise());
			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					imei = rs.getString(item.getImei());
				}
				if (imei.equals("")) {

					// execute the insert process
					/////////////////////////////////////////////////////////////////////////////////////////////

					PreparedStatement ps2 = Conn.getConnection().prepareStatement(
							"INSERT INTO de_item_issue_school (date, cc_name, school_name, student_name, udise, imei, filepath, desc, publish_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?,NOW())");
					ps2.setString(1, item.getDate());
					ps2.setString(2, item.getCc_name());
					ps2.setString(3, item.getSchool_name());
					ps2.setString(4, item.getStudent_name());
					ps2.setString(5, item.getUdise());
					ps2.setString(6, item.getImei());
					ps2.setString(7, loc);
					ps2.setString(8, item.getDesc());

					int i = ps2.executeUpdate();

					if (i > 0) {
						item.setMsg("item Details Updated Successfully...");

					} else {
						item.setMsg(
								"This item Details already added!!! please upload the item related details in the corrigendum section..");
					}
				}
			} else {
				item.setMsg("please upload a pdf file.");
			}
		} catch (SQLException e) {
			item.setMsg(
					"This item Details already added!!! please upload the item related details in the ammendment section..");
			e.printStackTrace();
			System.out.print("Error aa gyi" + e);
			// return "error";
		}	
	}

	public void updateItem(Item item) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = con.prepareStatement("UPDATE de_item_issue_school SET cc_name = ?, school_name = ? WHERE id = ?")) {

			ps.setString(1, item.getCc_name());
			ps.setString(2, item.getSchool_name());
			ps.setInt(3, item.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteUser(int id) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = con.prepareStatement("DELETE FROM de_item_issue_school WHERE id = ?")) {

			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
