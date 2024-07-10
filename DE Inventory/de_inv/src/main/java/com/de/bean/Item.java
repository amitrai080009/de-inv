package com.de.bean;

import java.io.File;
import java.util.List;

public class Item {

	private int id;
	private String date;
	private String transaction_type;
	private String cc_name;
	private String school_name;
	private String student_name;
	private String udise;
	private String imei;
	private String desc;
	private String msg;
	private String brand; // newly added when creating action for return item FetchIssueAction Class
	private String from;   //school name
	private String to;
	
	//newly added
		private String name_from;
		private String name_to;
		private String std;
	
	private File file;
	private String fileFileName; //filepath property in mysql
	private String fileContentType;
	
	private String fileFileName_pcf;
	
	//new variables
	private String current_status;
	private String status_remarks;
	private String remarks;
	
	//new intransaction/ inrepair items page
	private String type;
	
	
	//new fields 
	private int issued_item_count;
	private int items_in_transaction_count;

	
	//getter and setters
	
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
	
	
	public String getCc_name() {
		return cc_name;
	}
	public void setCc_name(String cc_name) {
		this.cc_name = cc_name;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getUdise() {
		return udise;
	}
	public void setUdise(String udise) {
		this.udise = udise;
	}
	public String getFileFileName_pcf() {
		return fileFileName_pcf;
	}
	public void setFileFileName_pcf(String fileFileName_pcf) {
		this.fileFileName_pcf = fileFileName_pcf;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
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
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getStd() {
		return std;
	}
	public void setStd(String std) {
		this.std = std;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getIssued_item_count() {
		return issued_item_count;
	}
	public void setIssued_item_count(int issued_item_count) {
		this.issued_item_count = issued_item_count;
	}
	public int getItems_in_transaction_count() {
		return items_in_transaction_count;
	}
	public void setItems_in_transaction_count(int items_in_transaction_count) {
		this.items_in_transaction_count = items_in_transaction_count;
	}
	
	
}
