package com.de.reporting;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.de.bean.Item;
import com.de.bean.ItemTransaction;
import com.de.dao.DaoInv;

public class IssueTransactionReport {

	private String idimei;

	private List<Item> itemList = null;
	Item item = new Item();
	DaoInv di1;
	ResultSet rs1 = null;

	private List<ItemTransaction> listT = null;
	ItemTransaction itemT = new ItemTransaction();
	DaoInv di2;
	ResultSet rs2 = null;

	public String execute() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		String sessionUser = (String) session.getAttribute("loggedUserName");
		/* to check if user exists in session then success else go to login page */
		if (sessionUser == null || sessionUser.equals("")) {
			return "login";
		} 
		
		try {

			// here method to get item list is implemented.

			listT = new ArrayList<ItemTransaction>();
			di2 = new DaoInv();
			rs2 = di2.transactionReport(idimei);
			if (rs2 != null) {
				while (rs2.next()) {
					itemT = new ItemTransaction();
					
					itemT.setDate(rs2.getString("date"));
					itemT.setIMEI(rs2.getString("imei"));
					itemT.setDevice(rs2.getString("item_name"));
					itemT.setType(rs2.getString("type")); // status on report page // to show the last transaction
															// status
					itemT.setFrom(rs2.getString("from_location"));
					itemT.setTo(rs2.getString("to_location"));
					itemT.setFileFileName(rs2.getString("filepath"));
					itemT.setPublish_date(rs2.getString("publish_date"));

					listT.add(itemT);
				}
			}
			
			itemList = new ArrayList<Item>();
			di1 = new DaoInv();
			rs1 = di1.issueReport(idimei);
			if(rs1 != null) {
				while(rs1.next()) {
					item = new Item();
					
					item.setDate(rs1.getString("issue_date"));
					item.setImei(rs1.getString("imei"));
					item.setCc_name(rs1.getString("cc_name"));
					item.setSchool_name(rs1.getString("school_name"));
					item.setStudent_name(rs1.getString("student_name"));
					item.setUdise(rs1.getString("udise"));
					item.setBrand(rs1.getString("brand_name"));
					item.setFileFileName(rs1.getString("filepath"));
				
					itemList.add(item);
				}
			}
			
			
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String getIdimei() {
		return idimei;
	}

	public void setIdimei(String idimei) {
		this.idimei = idimei;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public List<ItemTransaction> getListT() {
		return listT;
	}

	public void setListT(List<ItemTransaction> listT) {
		this.listT = listT;
	}
}
