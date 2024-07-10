package com.de.action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.de.bean.Country;
import com.de.bean.Item;
import com.de.bean.ItemMaster;
import com.de.dao.CountryDAO;
import com.de.dao.DaoInv;
import com.de.reporting.ItemReport;
import com.opensymphony.xwork2.ActionSupport;

public class CountryAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Country> countries;
	
	// variables for getting item issue list and display on the de issue item school jsp page
	private List<Item> itemList = null;
	Item item = new Item();
	DaoInv di;
	ResultSet rs = null;

	public String execute() throws ClassNotFoundException {
		
		//session checking..........................
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

			// here method to get item issue list from de_item_issue_school is implemented.

			itemList = new ArrayList<Item>();
			di = new DaoInv();
			rs = di.itemIssueList();
			if (rs != null) {
				while (rs.next()) {
					item = new Item();
					item.setDate(rs.getString(1));
					item.setImei(rs.getString(2));
					item.setCc_name(rs.getString(3));
					item.setSchool_name(rs.getString(4));
					item.setStudent_name(rs.getString(5));
					item.setUdise(rs.getString(6));
					item.setDesc(rs.getString(7));
					item.setFileFileName(rs.getString(8));

					itemList.add(item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
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