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

import com.de.bean.Official;
import com.upmscl.dao.GetConnection;

public class IssueOfficialService {
	
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
	

	public boolean issueOfficial(Official official) {

		try {

			// Perform file validation

			if (official.getFile() == null || official.getFile().length() == 0) {
				official.setMsg("Please select a file.");
				System.out.println("no files selected");
				return false;
			}

			if (official.getFile().length() > MAX_FILE_SIZE) {
				official.setMsg("File size exceeds the maximum allowed limit.");
				System.out.println("file exceeds maximum size limit");
				return false;
			}
			if (official.getFileContentType() != null && official.getFileContentType().equals("application/pdf")) {

				String filePath = ServletActionContext.getServletContext().getRealPath("/").concat("NewIssueChallan/");
				// String filePath = "NewStockChallan/";
				System.out.println("Image Location:" + filePath);// see the server console for actual location
				File fileToCreate = new File(filePath, official.getFileFileName());

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
					String fileNameWithOutExt = FilenameUtils.removeExtension(official.getFileFileName());
					// get file name only without extension

					String ext1 = FilenameUtils.getExtension(official.getFileFileName()); // get file extension like
																						// .pdf

					String newFileName = String
							.format(fileNameWithOutExt + "-%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp." + ext1, cal);
					fileToCreate = new File(filePath, newFileName);
					FileUtils.copyFile(official.getFile(), fileToCreate);// copying source file to new file
					loc = "NewIssueChallan/" + newFileName;
				} else {

					FileUtils.copyFile(official.getFile(), fileToCreate);// copying source file to new file
					loc = "NewIssueChallan/" + official.getFileFileName();
				}
			} else {
				official.setMsg("Invalid file type. Please upload a PDF file.");
				System.out.print("Invalid file type. Please upload a PDF file.");
				return false;
			}

			///////////////////////////////////////////////////////////////////////////////////////////
			ResultSet rs = null;
			String ref_no = "";
			int active_flag = 1;
			String issue_flag = "1";

			String sql = "SELECT * FROM de_item_issue_official where imei = ? and date = ?";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			ps.setString(1, official.getImei());
			ps.setString(2, official.getDate());
			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					ref_no = rs.getString(official.getImei());
				}
				if (ref_no.equals("")) {

					// execute the insert process
					/////////////////////////////////////////////////////////////////////////////////////////////

					PreparedStatement preparedStatement = Conn.getConnection().prepareStatement(
							"INSERT INTO de_item_issue_official (date, official_name, designation, imei, type, type_other, official_id_no, filepath, active_flag, publish_date) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())");
					preparedStatement.setString(1, official.getDate());
					preparedStatement.setString(2, official.getOfficial_name());
					preparedStatement.setString(3, official.getDesignation());
					preparedStatement.setString(4, official.getImei());
					preparedStatement.setString(5, official.getType());
					preparedStatement.setString(6, official.getType_other());
					preparedStatement.setString(7, official.getOfficial_id_no());
					
					
					preparedStatement.setString(8, loc); // for inserting filepath in to database
					preparedStatement.setInt(9, active_flag);

					int i = preparedStatement.executeUpdate();

					if (i > 0) {
						official.setMsg("Item Details Updated Successfully...");

						// execute the update process when item successfully issued to student
						/////////////////////////////////////////////////////////////////////////////////////////////

						PreparedStatement preparedStatement1 = Conn.getConnection()
								.prepareStatement("UPDATE de_item_master SET issue_flag = ? WHERE IMEI = ?");
						preparedStatement1.setString(1, issue_flag);
						preparedStatement1.setString(2, official.getImei());

						int j = preparedStatement1.executeUpdate();

						if (j > 0) {
							official.setMsg("Item issue Details Updated Successfully...");
						}

					} else {
						official.setMsg(
								"This stockBean Details already added!!! please upload the stockBean related details in the corrigendum section..");
					}

				} else {
					official.setMsg("please upload a pdf file.");
				}
			}

		} catch (Exception e) {
			official.setMsg(
					"2This stockBean Details already added!!! please upload the stockBean related details in the ammendment section..");
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