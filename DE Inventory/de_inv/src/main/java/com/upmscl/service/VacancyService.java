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

import com.upmscl.bean.Vacancy;
import com.upmscl.dao.GetConnection;

public class VacancyService {
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

	public boolean uploadVacancy(Vacancy vacancy) {

		try {

			// Perform file validation
			
			  if (vacancy.getFile1() == null || vacancy.getFile1().length() == 0) {
			  vacancy.setMsg("Please select a file."); return false; }
			 
			if (vacancy.getFile1().length() > MAX_FILE_SIZE) {
				vacancy.setMsg("File size exceeds the maximum allowed limit.");
				return false;
			}
			if (vacancy.getFileContentType() != null && vacancy.getFileContentType().equals("application/pdf")) {
				
				String filePath = ServletActionContext.getServletContext().getRealPath("/").concat("recruitment/");
				// String filePath = "TenderFiles/";
				System.out.println("Image Location:" + filePath);// see the server console for actual location
				File fileToCreate = new File(filePath, vacancy.getFileFileName());

				// Code here checks that if a file is uploading as new or with duplicate name
				// if file with some previous duplicate filename then its name will be appended
				// with
				// timestamp like filename + date + timestamp + am/pm

				if (fileToCreate.exists()) {
					System.out.println("File Already Esists");
					vacancy.setMsg("This file name already axists");

					Calendar cal = Calendar.getInstance();
					cal.setTime(Date.from(Instant.now()));

					// Create a filename from a format string.
					// ... Apply date formatting codes.
					String fileNameWithOutExt = FilenameUtils.removeExtension(vacancy.getFileFileName());
					// get file name only without extension

					String ext1 = FilenameUtils.getExtension(vacancy.getFileFileName()); // get file extension like .pdf

					String newFileName = String
							.format(fileNameWithOutExt + "-%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp." + ext1, cal);
					fileToCreate = new File(filePath, newFileName);
					FileUtils.copyFile(vacancy.getFile1(), fileToCreate);// copying source file to new file
					loc = "TenderFiles/" + newFileName;
				} else {

					FileUtils.copyFile(vacancy.getFile1(), fileToCreate);// copying source file to new file
					loc = "TenderFiles/" + vacancy.getFileFileName();
				}
			} else {
				vacancy.setMsg("Invalid file type. Please upload a PDF file.");
				return false;
			}

			///////////////////////////////////////////////////////////////////////////////////////////
			ResultSet rs = null;
			String tno = "";

			String sql = "SELECT * FROM vacancy_upmscl where refno = ?";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			ps.setString(1, vacancy.getRefno());
			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					tno = rs.getString(vacancy.getRefno());
				}
				if (tno.equals("")) {

					// execute the insert process
					/////////////////////////////////////////////////////////////////////////////////////////////

					PreparedStatement preparedStatement = Conn.getConnection().prepareStatement(
							"INSERT INTO vacancy_upmscl (title, refno, validfrom, validto, filepath, update_date) values ( ?, ?, ?, ?, ?, NOW())");
					preparedStatement.setString(1, vacancy.getTitle());
					preparedStatement.setString(2, vacancy.getRefno()); // for inserting file path in to database
					preparedStatement.setString(3, vacancy.getValidfrom());
					preparedStatement.setString(4, vacancy.getValidto());
					preparedStatement.setString(5, loc);
					
					int i = preparedStatement.executeUpdate();

					if (i > 0) {
						vacancy.setMsg("Vacancy Details Updated Successfully...");

					} else {
						vacancy.setMsg(
								"This Vacancy Details already added!!! please upload the vacancy related details in the corrigendum section..");
					}
				}
			} else {
				vacancy.setMsg("please upload a pdf file.");
			}
		} catch (

		Exception e) {
			vacancy.setMsg(
					"2This Vacancy Details already added!!! please upload the vacancy related details in the ammendment section..");
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

