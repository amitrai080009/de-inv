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
import com.de.bean.StockBean;

public class AddStockService {

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

	public boolean addStock(StockBean stockBean) {

		try {

			// Perform file validation

			if (stockBean.getFile() == null || stockBean.getFile().length() == 0) {
				stockBean.setMsg("Please select a file.");
				System.out.println("no files selected");
				return false;
			}

			if (stockBean.getFile().length() > MAX_FILE_SIZE) {
				stockBean.setMsg("File size exceeds the maximum allowed limit.");
				System.out.println("file exceeds maximum size limit");
				return false;
			}
			if (stockBean.getFileContentType() != null && stockBean.getFileContentType().equals("application/pdf")) {

				String filePath = ServletActionContext.getServletContext().getRealPath("/").concat("NewStockChallan/");
				// String filePath = "NewStockChallan/";
				System.out.println("Image Location:" + filePath);// see the server console for actual location
				File fileToCreate = new File(filePath, stockBean.getFileFileName());

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
					String fileNameWithOutExt = FilenameUtils.removeExtension(stockBean.getFileFileName());
					// get file name only without extension

					String ext1 = FilenameUtils.getExtension(stockBean.getFileFileName()); // get file extension like
																							// .pdf

					String newFileName = String
							.format(fileNameWithOutExt + "-%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp." + ext1, cal);
					fileToCreate = new File(filePath, newFileName);
					FileUtils.copyFile(stockBean.getFile(), fileToCreate);// copying source file to new file
					loc = "NewStockChallan/" + newFileName;
				} else {

					FileUtils.copyFile(stockBean.getFile(), fileToCreate);// copying source file to new file
					loc = "NewStockChallan/" + stockBean.getFileFileName();
				}
			} else {
				stockBean.setMsg("Invalid file type. Please upload a PDF file.");
				System.out.print("Invalid file type. Please upload a PDF file.");
				return false;
			}

			///////////////////////////////////////////////////////////////////////////////////////////
			ResultSet rs = null;
			String ref_no = "";
			int active_flag = 1;

			String sql = "SELECT * FROM de_stock_master where ref_no = ? and date = ?";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			ps.setString(1, stockBean.getRef_no());
			ps.setString(2, stockBean.getDate());
			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					ref_no = rs.getString(stockBean.getRef_no());
				}
				if (ref_no.equals("")) {

					// execute the insert process
					/////////////////////////////////////////////////////////////////////////////////////////////

					PreparedStatement preparedStatement = Conn.getConnection().prepareStatement(
							"INSERT INTO de_stock_master (date, transaction_type, item_name, brand, quantity, sender, receiver, invoice_no, amount, ref_no, filepath, remarks, active_flag, publish_date) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())");
					preparedStatement.setString(1, stockBean.getDate());
					preparedStatement.setString(2, stockBean.getTransaction_type());
					preparedStatement.setString(3, stockBean.getItem_name());
					preparedStatement.setString(4, stockBean.getBrand());
					preparedStatement.setInt(5, stockBean.getQuantity());
					preparedStatement.setString(6, stockBean.getSender());
					preparedStatement.setString(7, stockBean.getReceiver());
					preparedStatement.setString(8, stockBean.getChallan_no());
					preparedStatement.setInt(9, stockBean.getAmount());
					preparedStatement.setString(10, stockBean.getRef_no());
					preparedStatement.setString(11, loc); // for inserting filepath in to database
					preparedStatement.setString(12, stockBean.getRemarks());
					preparedStatement.setInt(13, active_flag);

					int i = preparedStatement.executeUpdate();

					if (i > 0) {
						stockBean.setMsg("stockBean Details Updated Successfully...");

					} else {
						stockBean.setMsg(
								"This stockBean Details already added!!! please upload the stockBean related details in the corrigendum section..");
					}
				}
			} else {
				stockBean.setMsg("please upload a pdf file.");
			}
		} catch (

		Exception e) {
			stockBean.setMsg(
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
