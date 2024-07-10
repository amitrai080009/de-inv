package com.upmscl.service;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;

import com.upmscl.bean.Corrigendum;
import com.upmscl.dao.DaoAdmin;
import com.upmscl.dao.GetConnection;

public class CorrigendumService {

	/* Execute Method varible list */
	ResultSet rs = null;
	DaoAdmin admin1 = new DaoAdmin();

	/*
	 * Upload Corrigendum variables List
	 */
	ResultSet c_rs = null;
	String loc;

	// Define maximum file size allowed
	private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10 MB

	// Define allowed file types
	// private static final String[] ALLOWED_FILE_TYPES = {"image/jpeg",
	// "image/png", "image/gif"};
	private static final List<String> ALLOWED_FILE_TYPES = Arrays.asList("pdf");

	GetConnection Conn = new GetConnection();

	List<Corrigendum> beanList = null;
	Corrigendum bean = null;

	public String execute() throws Exception {

		try {
			beanList = new ArrayList<Corrigendum>();
			rs = admin1.report();
			if (rs != null) {
				while (rs.next()) {
					bean = new Corrigendum();
					bean.setId(rs.getInt("id"));
					bean.setTender_no(rs.getString("tender_no"));
					beanList.add(bean);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	public boolean uploadCorrigendum(Corrigendum corrigendum) {
		try {
			// Perform file validation
			if (corrigendum.getFile() == null || corrigendum.getFile().length() == 0) {
				corrigendum.setMsg("Please select a file.");
				return false;
			}
			if (corrigendum.getFile().length() > MAX_FILE_SIZE) {
				corrigendum.setMsg("File size exceeds the maximum allowed limit.");
				return false;
			}
			if (corrigendum.getFileContentType() != null
					&& corrigendum.getFileContentType().equals("application/pdf")) {

				/* File Uploading in the File folder */
				String filePath = ServletActionContext.getServletContext().getRealPath("/").concat("CorrigendumFiles/");
				System.out.println("Image Location:" + filePath);// see the server console for actual location
				System.out.println(corrigendum.getFileFileName());
				File fileToCreate = new File(filePath, corrigendum.getFileFileName());
				/*
				 * file To Create means full path // including filename // with extension
				 */
				/*
				 * Code here checks that if a file is uploading as new or with duplicate name if
				 * file with some previous duplicate filename then its name will be appended //
				 * with // timestamp like filename + date + timestamp + am/pm
				 */
				if (fileToCreate.exists()) {
					System.out.println("File Already Esists");
					corrigendum.setMsg("This File Name Already exists.");

					Calendar cal = Calendar.getInstance();
					cal.setTime(Date.from(Instant.now()));

					// Create a filename from a format string.
					// ... Apply date formatting codes.
					String fileNameWithOutExt = FilenameUtils
							.removeExtension(corrigendum.getFileFileName()); /*
																				 * // get file // name only // without
																				 */ // extension
					String ext1 = FilenameUtils.getExtension(corrigendum.getFileFileName()); // ge file extension like
																								// .pdf

					String newFileName = String
							.format(fileNameWithOutExt + "-%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp." + ext1, cal);
					fileToCreate = new File(filePath, newFileName);
					FileUtils.copyFile(corrigendum.getFile(), fileToCreate);// copying source file to new file
					loc = "CorrigendumFiles/" + newFileName;

				} else {

					FileUtils.copyFile(corrigendum.getFile(), fileToCreate);// copying source file to new file
					loc = "CorrigendumFiles/" + corrigendum.getFileFileName();
				}

			} else {
				corrigendum.setMsg("Invalid file type. Please upload a PDF file.");
				return false;
			}

			/* Database Connection and uploading data and files */
			PreparedStatement preparedStatement = Conn.getConnection().prepareStatement(
					"INSERT INTO tender_amend (id, filepath, title, amend_date) values (?, ?, ?, NOW())");
			preparedStatement.setInt(1, corrigendum.getId());
			preparedStatement.setString(2, loc);
			preparedStatement.setString(3, corrigendum.getTitle());

			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				PreparedStatement ps = Conn.getConnection()
						.prepareStatement("update tender set amend_date= NOW() where id=?");
				ps.setInt(1, corrigendum.getId());
				ps.executeUpdate();
				corrigendum.setMsg("Successfully Updated Corrigendum/Amendment.");
				return true;
			} else {
				corrigendum.setMsg("Error! While updating Data");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("Error aa gyi");
		}
		return false;
	}

	public List<Corrigendum> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<Corrigendum> beanList) {
		this.beanList = beanList;
	}
}