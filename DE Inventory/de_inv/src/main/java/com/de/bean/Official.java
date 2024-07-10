package com.de.bean;

import java.io.File;

public class Official {
	
	private int id;
	private String date;
	private String transaction_type;
	private String official_name;
	private String official_id_no;
	private String designation;
	private String type; //whether AIF employee/Govt offficial/Teacher/Student
	private String type_other;
	private String imei;
	private String msg;
	
	//file variable objects
	private File file;
	private String fileFileName; //filepath property in mysql
	private String fileContentType;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	public String getOfficial_name() {
		return official_name;
	}
	public void setOfficial_name(String official_name) {
		this.official_name = official_name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getType_other() {
		return type_other;
	}
	public void setType_other(String type_other) {
		this.type_other = type_other;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOfficial_id_no() {
		return official_id_no;
	}
	public void setOfficial_id_no(String official_id_no) {
		this.official_id_no = official_id_no;
	}
	
}
