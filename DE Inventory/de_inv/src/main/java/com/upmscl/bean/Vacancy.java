package com.upmscl.bean;

import java.io.File;

public class Vacancy {
	
	private int id;
	private String title;
	private String refno;
	private String validfrom;
	private String validto;
	private String updatedate;
	private File file1;
	private String msg;
	private String fileContentType;
	private String fileFileName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRefno() {
		return refno;
	}
	public void setRefno(String refno) {
		this.refno = refno;
	}
	public String getValidfrom() {
		return validfrom;
	}
	public void setValidfrom(String validfrom) {
		this.validfrom = validfrom;
	}
	public String getValidto() {
		return validto;
	}
	public void setValidto(String validto) {
		this.validto = validto;
	}

	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
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
	public File getFile1() {
		return file1;
	}
	public void setFile1(File file1) {
		this.file1 = file1;
	}
	
	

}
