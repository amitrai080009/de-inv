package com.de.reporting;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.de.bean.Country;
import com.de.bean.Item;
import com.de.bean.ItemTransaction;
import com.de.dao.CountryDAO;
import com.de.dao.DaoInv;
import com.opensymphony.xwork2.ModelDriven;

public class IssueItemReport implements ModelDriven<Item> {
	
	private List<Item> itemList = null;
	private List<Country> countries;

	
	Item item = new Item();
	DaoInv di1;
	ResultSet rs1 = null;
	
	public String execute() throws ClassNotFoundException {
		
		//session checking
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		String sessionUser = (String) session.getAttribute("loggedUserName");
		/* to check if user exists in session then success else go to login page */
		if (sessionUser == null || sessionUser.equals("")) {
			return "login";
		} 
		
		//here we have called the getAllContries method which is getting the list of cc name and accordingly school and student name based on cc name.
				CountryDAO dao = new CountryDAO();
				countries = dao.getAllCountries();
				// here this method ends and 
				//now here we have implemented code to get the list of items and issued to details below and display on the de item school page for reference.

		
		try {

			// here method to get item list is implemented.
			
			itemList = new ArrayList<Item>();
			di1 = new DaoInv();
			rs1 = di1.issueItemReport(item);
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
			return "error";
		}
		return "success";
	}

	@Override
	public Item getModel() {
		// TODO Auto-generated method stub
		return item;
	}
	
	public List<Country> getCountries() {
		return countries;
	}
	
	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}


}
