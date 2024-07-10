package com.de.bean;

import java.io.File;

public class ItemTransaction {
	
// currently not used
	
	private int id;
	private String date;
	private String IMEI;
	private String device; //item_name in database
	private String type; // Return for Repair // sent for Repair // Return After Repair // Re-issue after repair;
	private String cc_name;
	private String from;
	private String to;
	private String filepath;
	
	//newly added
	private String name_from;
	private String name_to;
	
	//new variable
	private String msg;
	private String publish_date;
	
	private File file;
	private String fileFileName; //filepath property in mysql
	private String fileContentType;
	
	
	//new form fields
	private String current_status;
	private String status_remarks;
	private String remarks;
	
	
	
	
	
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
	public String getIMEI() {
		return IMEI;
	}
	public void setIMEI(String iMEI) {
		IMEI = iMEI;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCc_name() {
		return cc_name;
	}
	public void setCc_name(String cc_name) {
		this.cc_name = cc_name;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
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
	public String getName_from() {
		return name_from;
	}
	public void setName_from(String name_from) {
		this.name_from = name_from;
	}
	public String getName_to() {
		return name_to;
	}
	public void setName_to(String name_to) {
		this.name_to = name_to;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}
	public String getCurrent_status() {
		return current_status;
	}
	public void setCurrent_status(String current_status) {
		this.current_status = current_status;
	}
	public String getStatus_remarks() {
		return status_remarks;
	}
	public void setStatus_remarks(String status_remarks) {
		this.status_remarks = status_remarks;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
