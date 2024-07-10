package com.upmscl.service;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;

import com.upmscl.dao.GetConnection;

public class UpdateCircularService {


	ResultSet c_rs = null;
	private String title;
	private File file ;
	
	private String msg;
	private String fileContentType;  
    private String fileFileName;
    String loc;
    private int id;
    private String update_title;
	
	
	GetConnection Conn = new GetConnection();
	
	public String execute() {
		
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM circular WHERE id=?";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs != null) {
				while (rs.next()) {
					
					setUpdate_title(rs.getString("content"));
					
					}
				}
			} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
		
		return "C_UPDATE";
	}
		public String updatecircular() throws SQLException, Exception {
		
		try {
			String filePath = ServletActionContext.getServletContext().getRealPath("/").concat("UpdateCircularFiles/");
			System.out.println("file Location:" + filePath);//see the server console for actual location  
		    File fileToCreate = new File(filePath,fileFileName);

		    // Code here checks that if a file is uploading as new or with duplicate name
		    // if file with some previous duplicate filename then its name will be appended with 
		    // timestamp like filename + date + timestamp + am/pm 
		    
		    if(fileToCreate.exists()) {
		    	System.out.println("File Already Esists");
		    	setMsg("This File Name Already exists.");
		    	
		    	Calendar cal = Calendar.getInstance();
		        cal.setTime(Date.from(Instant.now()));

		        // Create a filename from a format string.
		        // ... Apply date formatting codes.
		        String fileNameWithOutExt = FilenameUtils.removeExtension(fileFileName); // get file name only without extension
		        String ext1 = FilenameUtils.getExtension(fileFileName); // ge file extension like .pdf
		        
		        String newFileName = String.format(fileNameWithOutExt + "-%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp." + ext1, cal);
		        fileToCreate = new File(filePath,newFileName);
		        FileUtils.copyFile(file, fileToCreate);//copying source file to new file
		        loc = "UpdateCircularFiles/"+newFileName;
		    } else {
		    
				    FileUtils.copyFile(file, fileToCreate);//copying source file to new file
				    loc = "UpdateCircularFiles/"+fileFileName;
				    }
		    
		    ///////////////////////////////////////////////////////////////////////////////////////////
		    
		    	
			PreparedStatement preparedStatement = Conn.getConnection().prepareStatement("update circular set content=?, filepath=?, publish_date=NOW() where id="+id);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, loc); // for inserting file path in to database
			
			int i = preparedStatement.executeUpdate();
			
			if (i > 0) {
				setMsg("Data successfully inserted.");
				
			} else {
				setMsg("Something gone wrong.");
				
			}
		} catch (Exception e) {
			setMsg("Something gone wrong.");
			e.printStackTrace();
			System.out.print("Error aa gyi"+e);
			//return "error";
		}
		
		return "cupdated";
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getUpdate_title() {
		return update_title;
	}

	public void setUpdate_title(String update_title) {
		this.update_title = update_title;
	}

	
}
