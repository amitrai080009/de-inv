package com.de.service;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;

import com.de.bean.Item;
import com.de.bean.ItemTransaction;
import com.upmscl.dao.GetConnection;

public class ReturnItemService {
	
	ResultSet c_rs = null;
	ResultSet t_rs = null;
	String loc;

	// Define maximum file size allowed //
	private static final long MAX_FILE_SIZE = 100 * 1024 * 1024; // 100 MB

	// Define allowed file types
	// private static final String[] ALLOWED_FILE_TYPES = { "image/jpeg",
	// "image/png", "image/gif" };
	private static final List<String> ALLOWED_FILE_TYPES = Arrays.asList("pdf");

	GetConnection Conn = new GetConnection();
	
	public boolean returnItemService(Item item) {
		
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

				String filePath = ServletActionContext.getServletContext().getRealPath("/").concat("TransactionFiles/");
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
					loc = "TransactionFiles/" + newFileName;
				} else {

					FileUtils.copyFile(item.getFile(), fileToCreate);// copying source file to new file
					loc = "TransactionFiles/" + item.getFileFileName();
				}
			} else {
				item.setMsg("Invalid file type. Please upload a PDF file.");
				System.out.print("Invalid file type. Please upload a PDF file.");
				return false;
			}
			// Here File upload process completes

///////////////////////////////////Process to update the flag as 0-inactive for past transaction////////////////////////////////////////////////////////
			ResultSet rs = null;
			String ref_no = "";
			int active_transaction = 0;
			int active_trans = 1;
			String issue_flag = "0";
			int active_flag = 0;
			
			PreparedStatement preparedStatement1 = Conn.getConnection()
					.prepareStatement("UPDATE de_transaction_master SET active_transaction = ? WHERE IMEI = ? and active_transaction = 1");
			preparedStatement1.setInt(1, active_transaction);
			preparedStatement1.setString(2, item.getImei());

			int j = preparedStatement1.executeUpdate();

			if (j > 0) {
				item.setMsg("Old Transaction Details Updated Successfully...");
			}
			
///////////////////////////////////Process to update the flag as 0-inactive for past transaction////////////////////////////////////////////////////////

			
/*
			String sql = "SELECT * FROM de_item_issue_school where imei = ? and date = ?";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			ps.setString(1, item.getImei());
			ps.setString(2, item.getDate());
			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					ref_no = rs.getString(item.getImei());
				}
				if (ref_no.equals("")) {
*/
// 					execute the insert process of new transaction
/////////////////////////////////////////////////////////////////////////////////////////////

					PreparedStatement preparedStatement = Conn.getConnection().prepareStatement(
							"INSERT INTO de_transaction_master (IMEI, item_name, from_location, to_location, particular_name, "
							+ "UDISE_office_id, type, name_from, name_to, date, filepath, active_transaction, publish_date, current_status, status_remarks, remarks) "
							+ "values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, ?, ?)");
					preparedStatement.setString(1, item.getImei());
					preparedStatement.setString(2, item.getBrand());
					preparedStatement.setString(3, item.getFrom());
					preparedStatement.setString(4, item.getTo());
					preparedStatement.setString(5, item.getStudent_name());
					preparedStatement.setString(6, item.getUdise());
					preparedStatement.setString(7, item.getTransaction_type());
					preparedStatement.setString(8, item.getName_from());
					preparedStatement.setString(9, item.getName_to());
					preparedStatement.setString(10, item.getDate());
					preparedStatement.setString(11, loc); // for inserting filepath in to database
					preparedStatement.setInt(12, active_trans);
					
					//newly added
					preparedStatement.setString(13, item.getCurrent_status());
					preparedStatement.setString(14, item.getStatus_remarks());
					preparedStatement.setString(15, item.getRemarks());

					int i = preparedStatement.executeUpdate();

					if (i > 0) {
						item.setMsg("New Transaction Details Updated Successfully...");

// 					Updating the Item master table setting issue_flag as 0 so that we can issue the item again.
/////////////////////////////////////////////////////////////////////////////////////////////
						
						
						PreparedStatement preparedStatement2 = Conn.getConnection()
								.prepareStatement("UPDATE de_item_master SET issue_flag = ?, current_status=?, status_remarks=?, remarks=? WHERE IMEI = ?");
						preparedStatement2.setString(1, issue_flag);
												
						//newly added
						preparedStatement2.setString(2, item.getCurrent_status());
						preparedStatement2.setString(3, item.getStatus_remarks());
						preparedStatement2.setString(4, item.getRemarks());
						
						//previously added
						preparedStatement2.setString(5, item.getImei());

						int j1 = preparedStatement2.executeUpdate();

						if (j1 > 0) {
						
						//Updating the Item master table setting issue_flag as 0 so that we can issue the item again.
/////////////////////////////////////////////////////////////////////////////////////////////
						
							PreparedStatement preparedStatement3 = Conn.getConnection()
									.prepareStatement("UPDATE de_item_issue_school SET active_flag = ?, date_return=? WHERE imei = ?");
							preparedStatement3.setInt(1, active_flag);
							preparedStatement3.setString(2, item.getDate());
							preparedStatement3.setString(3, item.getImei());

							int j2 = preparedStatement3.executeUpdate();

							if (j2 > 0) {
						
								item.setMsg("ITEM! Return to Stock Successfully! and Transaction Details Updated Successfully...");
							}
						}
						

					} else {
						item.setMsg(
								"This stockBean Details already added!!! please upload the stockBean related details in the corrigendum section..");
					}

		} catch (Exception e) {
			item.setMsg(
					"2This stockBean Details already added!!! please upload the stockBean related details in the ammendment section..");
			e.printStackTrace();
			System.out.print("Error aa gyi" + e);
// return "error";
		}
		return true; // stockBean Uploaded Successfully ten returned true
	}

}
