package com.de.service;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;

import com.de.bean.ItemTransaction;
import com.upmscl.dao.GetConnection;

public class AddTransactionService {

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

	public boolean addTransactionService(ItemTransaction item, String sessionUser) { // here is the method to add the Transaction Service
		
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
			int issue_flag = 1;
			
			PreparedStatement preparedStatement1 = Conn.getConnection()
					.prepareStatement("UPDATE de_transaction_master SET active_transaction = ?, publish_by=? WHERE IMEI = ? and active_transaction = 1");
			preparedStatement1.setInt(1, active_transaction);
			preparedStatement1.setString(2, sessionUser);
			preparedStatement1.setString(3, item.getIMEI());

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
							"INSERT INTO de_transaction_master (IMEI, item_name, from_location, to_location, type, name_from, "
							+ "name_to, date, filepath, active_transaction, publish_date, current_status, status_remarks, remarks, publish_by) "
							+ "values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, ?, ?, ?)");
					preparedStatement.setString(1, item.getIMEI());
					preparedStatement.setString(2, item.getDevice());
					preparedStatement.setString(3, item.getFrom());
					preparedStatement.setString(4, item.getTo());
					preparedStatement.setString(5, item.getType());
					preparedStatement.setString(6, item.getName_from());
					preparedStatement.setString(7, item.getName_to());
					preparedStatement.setString(8, item.getDate());
					preparedStatement.setString(9, loc); // for inserting filepath in to database
					preparedStatement.setInt(10, issue_flag);
					
					//newly added
					preparedStatement.setString(11, item.getCurrent_status());
					preparedStatement.setString(12, item.getStatus_remarks());
					preparedStatement.setString(13, item.getRemarks());
					preparedStatement.setString(14, sessionUser);

					int i = preparedStatement.executeUpdate();

					if (i > 0) {
						item.setMsg("New Transaction Details Updated Successfully...");

// 					execute the update process when item successfully issued to student
/////////////////////////////////////////////////////////////////////////////////////////////
						
///////////////////////////////////Process to update the flag as 0-inactive for past transaction////////////////////////////////////////////////////////
						ResultSet rs_1 = null;
						String ref_no_1 = "";
						int active_transaction_1 = 0;
						int issue_flag_1 = 1;
						int in_repair_flag = 1;
						String type="IN_FOR_REPAIR";
						
						
	if(item.getType() == type)
	{
	
	PreparedStatement preparedStatement123 = Conn.getConnection()
			.prepareStatement("UPDATE de_item_issue_school SET in_repair_flag = ? WHERE IMEI = ? and active_flag = 1");
	preparedStatement123.setInt(1, active_transaction);
	//preparedStatement123.setString(2, sessionUser);
	//preparedStatement123.setString(3, item.getIMEI());

	int k = preparedStatement123.executeUpdate();

	if (k > 0) {
		item.setMsg("Issue to school in_repair_flag Details Updated Successfully...");
	}
	}
	
///////////////////////////////////Process to update the flag as 0-inactive for past transaction////////////////////////////////////////////////////////


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
