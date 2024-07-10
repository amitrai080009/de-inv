package com.upmscl.service;

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

import com.opensymphony.xwork2.ActionSupport;
import com.upmscl.bean.Tender;
import com.upmscl.dao.GetConnection;

public class ManageTenderService extends ActionSupport {

	private static final long serialVersionUID = 1L;
	ResultSet c_rs = null;
	ResultSet t_rs = null;
	String loc;
	// private String uploadContentType;

	// Define maximum file size allowed
	private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10 MB

	// Define allowed file types
	// private static final String[] ALLOWED_FILE_TYPES = {"image/jpeg",
	// "image/png", "image/gif"};
	private static final List<String> ALLOWED_FILE_TYPES = Arrays.asList("pdf");

	GetConnection Conn = new GetConnection();

	public boolean uploadTender(Tender tender) {

		try {

			// Perform file validation
			if (tender.getFile() == null || tender.getFile().length() == 0) {
				tender.setMsg("Please select a file.");
				return false;
			}
			if (tender.getFile().length() > MAX_FILE_SIZE) {
				tender.setMsg("File size exceeds the maximum allowed limit.");
				return false;
			}
			if (tender.getFileContentType() != null && tender.getFileContentType().equals("application/pdf")) {
				
				String filePath = ServletActionContext.getServletContext().getRealPath("/").concat("TenderFiles/");
				// String filePath = "TenderFiles/";
				System.out.println("Image Location:" + filePath);// see the server console for actual location
				File fileToCreate = new File(filePath, tender.getFileFileName());

				// Code here checks that if a file is uploading as new or with duplicate name
				// if file with some previous duplicate filename then its name will be appended
				// with
				// timestamp like filename + date + timestamp + am/pm

				if (fileToCreate.exists()) {
					System.out.println("File Already Esists");
					tender.setMsg("This file name already axists");

					Calendar cal = Calendar.getInstance();
					cal.setTime(Date.from(Instant.now()));

					// Create a filename from a format string.
					// ... Apply date formatting codes.
					String fileNameWithOutExt = FilenameUtils.removeExtension(tender.getFileFileName());
					// get file name only without extension

					String ext1 = FilenameUtils.getExtension(tender.getFileFileName()); // get file extension like .pdf

					String newFileName = String
							.format(fileNameWithOutExt + "-%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp." + ext1, cal);
					fileToCreate = new File(filePath, newFileName);
					FileUtils.copyFile(tender.getFile(), fileToCreate);// copying source file to new file
					loc = "TenderFiles/" + newFileName;
				} else {

					FileUtils.copyFile(tender.getFile(), fileToCreate);// copying source file to new file
					loc = "TenderFiles/" + tender.getFileFileName();
				}
			} else {
				tender.setMsg("Invalid file type. Please upload a PDF file.");
				return false;
			}

			///////////////////////////////////////////////////////////////////////////////////////////
			ResultSet rs = null;
			String tno = "";

			String sql = "SELECT * FROM tender where tender_no = ?";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			ps.setString(1, tender.getTender_no());
			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					tno = rs.getString(tender.getTender_no());
				}
				if (tno.equals("")) {

					// execute the insert process
					/////////////////////////////////////////////////////////////////////////////////////////////

					PreparedStatement preparedStatement = Conn.getConnection().prepareStatement(
							"INSERT INTO tender (description, filepath, tender_no, tender_start, tender_end, tender_cat, publish_date, amend_date) values ( ?, ?, ?, ?, ?, ?, NOW(), NOW())");
					preparedStatement.setString(1, tender.getTitle());
					preparedStatement.setString(2, loc); // for inserting file path in to database
					preparedStatement.setString(3, tender.getTender_no());
					preparedStatement.setString(4, tender.getTender_start());
					preparedStatement.setString(5, tender.getTender_end());
					preparedStatement.setString(6, tender.getTender_cat());
					int i = preparedStatement.executeUpdate();

					if (i > 0) {
						tender.setMsg("Tender Details Updated Successfully...");

					} else {
						tender.setMsg(
								"1his Tender Details already added!!! please upload the tender related details in the corrigendum section..");
					}
				}
			} else {
				tender.setMsg("please upload a pdf file.");
			}
		} catch (

		Exception e) {
			tender.setMsg(
					"2This Tender Details already added!!! please upload the tender related details in the ammendment section..");
			e.printStackTrace();
			System.out.print("Error aa gyi" + e);
			// return "error";
		}
		return true; // Tender Uploaded Successfully ten returned true
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
