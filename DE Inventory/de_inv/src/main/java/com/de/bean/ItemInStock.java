package com.de.bean;

public class ItemInStock {
	
	private int id;
	private String date;
	private String IMEI;
	private String item_name;
	private String received_from; //new variable
	private String particular_name; //new variable
	private String brand;  //new variable
	private String ref_no;
	private String warranty_to;
	private String remarks;
	
	private String current_status; //new variable
	private String status_remarks; //new variable
	private int item_count; //new variable
	
	
	//new new new 
	private String cc_name;
	private String school_name;
	private String student_name;
	
	private String type;
	

	
	
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
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getReceived_from() {
		return received_from;
	}
	public void setReceived_from(String received_from) {
		this.received_from = received_from;
	}
	public String getRef_no() {
		return ref_no;
	}
	public void setRef_no(String ref_no) {
		this.ref_no = ref_no;
	}
	public String getWarranty_to() {
		return warranty_to;
	}
	public void setWarranty_to(String warranty_to) {
		this.warranty_to = warranty_to;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getParticular_name() {
		return particular_name;
	}
	public void setParticular_name(String particular_name) {
		this.particular_name = particular_name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
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
	public int getItem_count() {
		return item_count;
	}
	public void setItem_count(int item_count) {
		this.item_count = item_count;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
