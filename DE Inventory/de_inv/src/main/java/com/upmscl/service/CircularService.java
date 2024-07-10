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

import com.upmscl.bean.Circular;
import com.upmscl.dao.GetConnection;

public class CircularService {

	ResultSet c_rs = null;
	String loc;
	GetConnection Conn = new GetConnection();
	// Define maximum file size allowed
	private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10 MB

	// Define allowed file types
	// private static final String[] ALLOWED_FILE_TYPES = {"image/jpeg",
	// "image/png", "image/gif"};
	private static final List<String> ALLOWED_FILE_TYPES = Arrays.asList("pdf");

	public boolean uploadCircular(Circular circular) {
		try {

			// Perform file validation
			if (circular.getFile() == null || circular.getFile().length() == 0) {
				circular.setMsg("Please select a file.");
				return false;
			}
			if (circular.getFile().length() > MAX_FILE_SIZE) {
				circular.setMsg("File size exceeds the maximum allowed limit.");
				return false;
			}
			if (circular.getFileContentType() != null && circular.getFileContentType().equals("application/pdf")) {
			
			String filePath = ServletActionContext.getServletContext().getRealPath("/").concat("CircularFiles/");
			System.out.println("file Location:" + filePath);// see the server console for actual location
			File fileToCreate = new File(filePath, circular.getFileFileName());
			/*
			 * // Code here checks that if a file is uploading as new or with duplicate name
			 * // if file with some previous duplicate filename then its name will be
			 * appended // with // timestamp like filename + date + timestamp + am/pm
			 */
			if (fileToCreate.exists()) {
				System.out.println("File Already Esists");
				circular.setMsg("This File Name Already exists");
				Calendar cal = Calendar.getInstance();
				cal.setTime(Date.from(Instant.now()));
				/*
				 * // Create a filename from a format string. // ... Apply date formatting
				 * codes.
				 */
				String fileNameWithOutExt = FilenameUtils.removeExtension(circular.getFileFileName()); // get file name
																										// only without
				// extension
				String ext1 = FilenameUtils.getExtension(circular.getFileFileName()); // ge file extension like .pdf
				String newFileName = String.format(fileNameWithOutExt + "-%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp." + ext1,
						cal);
				fileToCreate = new File(filePath, newFileName);
				FileUtils.copyFile(circular.getFile(), fileToCreate);// copying source file to new file
				loc = "CircularFiles/" + newFileName;
			} else {

				FileUtils.copyFile(circular.getFile(), fileToCreate);// copying source file to new file
				loc = "CircularFiles/" + circular.getFileFileName();
			}
			} else {
				circular.setMsg("Invalid file type. Please upload a PDF file.");
				return false;
			}
			
			
			/* DB Connection and uploading */

			PreparedStatement preparedStatement = Conn.getConnection()
					.prepareStatement("insert into circular (content, filepath, publish_date) values (?, ?, NOW())");
			preparedStatement.setString(1, circular.getTitle());
			preparedStatement.setString(2, loc); // for inserting file path in to database

			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				circular.setMsg("Data successfully inserted.");
			} else {
				circular.setMsg("Something gone wrong.");
			}
		} catch (Exception e) {
			circular.setMsg("Something gone wrong.");
			e.printStackTrace();
			System.out.print("Error aa gyi" + e);
			// return "error";
		}
		return true;
	}
}
