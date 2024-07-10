package com.de.service;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;
import java.util.Arrays;
import java.util.List;

import com.upmscl.dao.GetConnection;
import com.de.bean.Item;
import com.de.bean.StockBean;

public class AddItemService {

	private static final long serialVersionUID = 1L;
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

	public boolean addItem(Item item) {

		try {
			// Here file upload method starting

			// Perform file validation

			if (item.getFile() == null || item.getFile().length() == 0) {
				item.setMsg("Please select a file.");
				System.out.println("no files selected");
				return false;
			}

			if (item.getFile().length() > MAX_FILE_SIZE) {
				item.setMsg("File size exceeds the maximum allowed limit.");
				System.out.println("file exceeds maximum size limit");
				return false;
			}
			if (item.getFileContentType() != null && item.getFileContentType().equals("application/pdf")) {

				String filePath = ServletActionContext.getServletContext().getRealPath("/").concat("NewIssueChallan/");
				// String filePath = "NewStockChallan/";
				System.out.println("Image Location:" + filePath);// see the server console for actual location
				File fileToCreate = new File(filePath, item.getFileFileName());

				// Code here checks that if a file is uploading as new or with duplicate name
				// if file with some previous duplicate filename then its name will be appended
				// with
				// timestamp like filename + date + timestamp + am/pm

				if (fileToCreate.exists()) {
					System.out.println("File Name Already Esists");
					// stockBean.setMsg("This file name already axists");

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
					loc = "NewIssueChallan/" + newFileName;
				} else {

					FileUtils.copyFile(item.getFile(), fileToCreate);// copying source file to new file
					loc = "NewIssueChallan/" + item.getFileFileName();
				}
			} else {
				item.setMsg("Invalid file type. Please upload a PDF file.");
				System.out.print("Invalid file type. Please upload a PDF file.");
				return false;
			}

			// Here File upload process completes
			///////////////////////////////////////////////////////////////////////////////////////////
			ResultSet rs = null;
			ResultSet rs2 = null;
			String ref_no = "";
			int active_flag = 1;
			String issue_flag = "1";

			String sql = "SELECT * FROM de_item_issue_school where imei = ? and active_flag=1";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			ps.setString(1, item.getImei());
			//ps.setString(2, item.getDate());
			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					ref_no = rs.getString(item.getImei());
				}
				if (ref_no.equals("")) {

					// before issuing the item check if it is in Item master or not
					/////////////////////////////////////////////////////////////////////////////////////////////

					String sql2 = "SELECT * FROM de_item_master where IMEI = ? and issue_flag='0' ";
					PreparedStatement ps2 = Conn.getConnection().prepareStatement(sql2);
					ps2.setString(1, item.getImei());
					rs2 = ps2.executeQuery();
					if (rs2 != null) {
						while (rs2.next()) {
							ref_no = rs2.getString("IMEI");
						}
						if (ref_no.equals("")) {
							item.setMsg("INVALID! IMEI Number");
						}else {
						

						// execute the insert process
						/////////////////////////////////////////////////////////////////////////////////////////////

						PreparedStatement preparedStatement = Conn.getConnection().prepareStatement(
								"INSERT INTO de_item_issue_school (date, cc_name, school_name, student_name, imei, description, filepath, active_flag, publish_date) values ( ?, ?, ?, ?, ?, ?, ?, ?, NOW())");
						preparedStatement.setString(1, item.getDate());
						preparedStatement.setString(2, item.getCc_name());
						preparedStatement.setString(3, item.getSchool_name());
						preparedStatement.setString(4, item.getStudent_name());
						preparedStatement.setString(5, item.getImei());
						preparedStatement.setString(6, item.getDesc());
						preparedStatement.setString(7, loc); // for inserting filepath in to database
						preparedStatement.setInt(8, active_flag);

						int i = preparedStatement.executeUpdate();

						if (i > 0) {
							item.setMsg("Item Successfully Issued to School Code :- " + item.getSchool_name()
									+ "and Student Code:- " + item.getStudent_name());

							// execute the update process when item successfully issued to student
							/////////////////////////////////////////////////////////////////////////////////////////////

							PreparedStatement preparedStatement1 = Conn.getConnection()
									.prepareStatement("UPDATE de_item_master SET issue_flag = ? WHERE IMEI = ?");
							preparedStatement1.setString(1, issue_flag);
							preparedStatement1.setString(2, item.getImei());

							int j = preparedStatement1.executeUpdate();

							if (j > 0) {
								
								item.setMsg("Item Successfully Issued to School Code :- " + item.getSchool_name()
										+ "and Student Code:- " + item.getStudent_name());

								
								///////////////////////////////////////////////////////UPDATE TRANSACTION TABLE BEFORE ADDING ISSUE TRANSACTION
								int active_transaction1 = 0;
								PreparedStatement preparedStatement3 = Conn.getConnection()
										.prepareStatement("UPDATE de_transaction_master SET active_transaction=? WHERE IMEI = ?");
								preparedStatement3.setInt(1, active_transaction1);
								preparedStatement3.setString(2, item.getImei());

								int K = preparedStatement3.executeUpdate();
								if(K > 0)
								{
									System.out.print("Previous Transaction Updated with active transaction as 0");
								} else {
									System.out.print("No Active Transaction available for IMEI" +item.getImei());
								}

								
								///////////////////////////////////////// Add Issue to School Transaction adding
								///////////////////////////////////////// to Transaction
								///////////////////////////////////////// Table/////////////////////////////////////////
								String from_location = "AIF Office Amreli";
								String type = "NEW_ISSUED_TO_SCHOOL";
								int active_transaction = 1;
								PreparedStatement preparedStatement2 = Conn.getConnection().prepareStatement(
										"INSERT INTO de_transaction_master (IMEI, item_name, from_location, to_location, type, name_from, name_to, date, filepath, active_transaction, publish_date) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())");
								preparedStatement2.setString(1, item.getImei());
								preparedStatement2.setString(2, item.getDesc());
								preparedStatement2.setString(3, from_location);
								preparedStatement2.setString(4, item.getSchool_name());
								preparedStatement2.setString(5, type);
								preparedStatement2.setString(6, from_location);
								preparedStatement2.setString(7, item.getStudent_name());
								preparedStatement2.setString(8, item.getDate());
								preparedStatement2.setString(9, loc); // for inserting filepath in to database
								preparedStatement2.setInt(10, active_transaction);

								int k = preparedStatement2.executeUpdate();

								if (k > 0) {
									item.setMsg("ITEM! Issued Successfully! and Transaction Details Updated Successfully...");

//		 					execute the update process when item successfully issued to student
									/////////////////////////////////////////////////////////////////////////////////////////////

								}
								}
							}

						 else {
							item.setMsg(
									"This stockBean Details already added!!! please upload the stockBean related details in the corrigendum section..");
						}

						}}
					else {
						item.setMsg("This IMEI no.-"+ item.getImei()+" is //ALREADY ISSUED//.");
					}
				} else {
					item.setMsg("This IMEI no.-"+ item.getImei()+" is //ALREADY ISSUED//.");
				}
			}

		} catch (Exception e) {

			item.setMsg("This IMEI no.-"+ item.getImei()+" is //ALREADY ISSUED//.");
			e.printStackTrace();
			System.out.print("Error aa gyi" + e);
			// return "error";

		}
		return true; // stockBean Uploaded Successfully ten returned true
	}

	// Validate file type
	private boolean isValidFileType(String contentType) {
		for (String allowedType : ALLOWED_FILE_TYPES) {
			if (allowedType.equals(contentType)) {
				return true;
			}
		}
		return false;
	}

}
