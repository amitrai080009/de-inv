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
import com.upmscl.dao.GetConnection;

public class PcfUploadService {

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

	public boolean pcfUploadService(Item item) {
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
			int active_flag = 1; //Means that this is active issue details 
			
			PreparedStatement preparedStatement1 = Conn.getConnection()
					.prepareStatement("UPDATE de_item_issue_school SET filepath_pcf = ? WHERE IMEI = ? and active_flag = ?");
			
			preparedStatement1.setString(1, loc);
			preparedStatement1.setString(2, item.getImei());
			preparedStatement1.setInt(3, active_flag);

			int j = preparedStatement1.executeUpdate();

			if (j > 0) {
				item.setMsg("PCF Document Uploaded Successfully...");
			}
			 else {
						item.setMsg(
								"This PCF Details already added!!! please upload the Next PCFDocument..");
					}

		} catch (Exception e) {
			item.setMsg(
					"Exception: This PCF Details already added!!! please upload the Next PCFDocument..");
			e.printStackTrace();
			System.out.print("Error aa gyi" + e);
// return "error";
		}
		return true; // stockBean Uploaded Successfully ten returned true
	}


}
