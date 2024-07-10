package com.upmscl.service;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;

import com.upmscl.dao.GetConnection;

public class UpdateTenderService {

	private String update_tender_no;
	private String update_title;
	private String update_tender_start;
	private String update_tender_end;
	private String update_tender_cat;

	private String tender_no;
	private String title;
	private String tender_start;
	private String tender_end;
	private String tender_cat;
	private File file;
	private int id;
	private String msg;
	private String fileContentType;
	private String fileFileName;

	String loc;
	// Define maximum file size allowed
	private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10 MB

	// Define allowed file types
	// private static final String[] ALLOWED_FILE_TYPES = {"image/jpeg",
	// "image/png", "image/gif"};
	private static final List<String> ALLOWED_FILE_TYPES = Arrays.asList("pdf");

	GetConnection Conn = new GetConnection();

	public String execute() {

		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM tender WHERE id=?";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					setUpdate_tender_no(rs.getString("tender_no"));
					setUpdate_title(rs.getString("description"));
					setUpdate_tender_start(rs.getString("tender_start"));
					setUpdate_tender_end(rs.getString("tender_end"));
					setUpdate_tender_cat(rs.getString("tender_cat"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return "T_UPDATE";
	}

	public String updatetender() throws SQLException, Exception {

		try {
			// Perform file validation
			if (getFile() == null || getFile().length() == 0) {
				setMsg("Please select a file.");
				// return false;
			}
			if (getFile().length() > MAX_FILE_SIZE) {
				setMsg("File size exceeds the maximum allowed limit.");
				// return false;
			}
			if (getFileContentType() != null && getFileContentType().equals("application/pdf")) {

				String filePath = ServletActionContext.getServletContext().getRealPath("/")
						.concat("UpdateTenderFiles/");
				System.out.println("Image Location:" + filePath);// see the server console for actual location
				File fileToCreate = new File(filePath, fileFileName); // file To Create means full path including
																		// filename
																		// with extension

				// Code here checks that if a file is uploading as new or with duplicate name
				// if file with some previous duplicate filename then its name will be appended
				// with
				// timestamp like filename + date + timestamp + am/pm

				if (fileToCreate.exists()) {
					System.out.println("File Already Esists");
					setMsg("This File Name Already exists.");

					Calendar cal = Calendar.getInstance();
					cal.setTime(Date.from(Instant.now()));

					// Create a filename from a format string.
					// ... Apply date formatting codes.
					String fileNameWithOutExt = FilenameUtils.removeExtension(fileFileName); // get file name only
																								// without
																								// extension
					String ext1 = FilenameUtils.getExtension(fileFileName); // ge file extension like .pdf

					String newFileName = String
							.format(fileNameWithOutExt + "-%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp." + ext1, cal);
					fileToCreate = new File(filePath, newFileName);
					FileUtils.copyFile(file, fileToCreate);// copying source file to new file
					loc = "UpdateTenderFiles/" + newFileName;
				} else {

					FileUtils.copyFile(file, fileToCreate);// copying source file to new file
					loc = "UpdateTenderFiles/" + fileFileName;
				}

			} else {
				setMsg("Invalid file type. Please upload a PDF file.");
				//return false;
			}
			///////////////////////////////////////////////////////////////////////////////////////////

			PreparedStatement preparedStatement = Conn.getConnection().prepareStatement(
					"update tender set description=?, filepath=?, tender_no=?, tender_start=?, tender_end=?, tender_cat=?, updated=NOW() where id="
							+ id);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, loc); // for inserting file path in to database
			preparedStatement.setString(3, tender_no);
			preparedStatement.setString(4, tender_start);
			preparedStatement.setString(5, tender_end);
			preparedStatement.setString(6, tender_cat);

			int i = preparedStatement.executeUpdate();

			if (i > 0) {
				setMsg("Tender successfully Updated.");

			} else {
				setMsg("Something gone wrong.");
			}

		} catch (Exception e) {
			setMsg("Something gone wrong.");
			e.printStackTrace();
			System.out.print("Error aa gyi" + e);
			// return "error";
		}

		return "tupdated";
	}

	public String getTender_no() {
		return tender_no;
	}

	public void setTender_no(String tender_no) {
		this.tender_no = tender_no;
	}

	public String getTender_start() {
		return tender_start;
	}

	public void setTender_start(String tender_start) {
		this.tender_start = tender_start;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTender_cat() {
		return tender_cat;
	}

	public void setTender_cat(String tender_cat) {
		this.tender_cat = tender_cat;
	}

	public String getTender_end() {
		return tender_end;
	}

	public void setTender_end(String tender_end) {
		this.tender_end = tender_end;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUpdate_tender_no() {
		return update_tender_no;
	}

	public void setUpdate_tender_no(String update_tender_no) {
		this.update_tender_no = update_tender_no;
	}

	public String getUpdate_title() {
		return update_title;
	}

	public void setUpdate_title(String update_title) {
		this.update_title = update_title;
	}

	public String getUpdate_tender_end() {
		return update_tender_end;
	}

	public void setUpdate_tender_end(String update_tender_end) {
		this.update_tender_end = update_tender_end;
	}

	public String getUpdate_tender_start() {
		return update_tender_start;
	}

	public void setUpdate_tender_start(String update_tender_start) {
		this.update_tender_start = update_tender_start;
	}

	public String getUpdate_tender_cat() {
		return update_tender_cat;
	}

	public void setUpdate_tender_cat(String update_tender_cat) {
		this.update_tender_cat = update_tender_cat;
	}
}
