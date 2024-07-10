package com.upmscl.bean;

import java.io.File;

public class Tender {

	private int Id;
	private String tender_no;
	private String title;  //description property in mysql
	private File file ;
	private String tender_start;
	private String tender_end;
	private String tender_cat;
	private String msg;
	private String fileContentType;  
    private String fileFileName;   //filepath property in mysql
    
	public String getTender_no() {
		return tender_no;
	}
	public void setTender_no(String tender_no) {
		this.tender_no = tender_no;
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
	public String getTender_start() {
		return tender_start;
	}
	public void setTender_start(String tender_start) {
		this.tender_start = tender_start;
	}
	public String getTender_end() {
		return tender_end;
	}
	public void setTender_end(String tender_end) {
		this.tender_end = tender_end;
	}
	public String getTender_cat() {
		return tender_cat;
	}
	public void setTender_cat(String tender_cat) {
		this.tender_cat = tender_cat;
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
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
}
