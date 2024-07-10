package com.de.reporting;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.de.bean.Country;
import com.de.bean.Item;
import com.de.bean.ItemMaster;
import com.de.bean.StockBean;
import com.de.dao.CountryDAO;
import com.de.dao.DaoInv;

public class ItemReport {
	
	private List<ItemMaster> itemList = null;
	ItemMaster itemMaster = new ItemMaster();
	DaoInv di;
	ResultSet rs = null;
	
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

			itemList = new ArrayList<ItemMaster>();
			di = new DaoInv();
			rs = di.itemReport();
			if (rs != null) {
				while (rs.next()) {
					itemMaster = new ItemMaster();
					itemMaster.setDate(rs.getString("date"));
					itemMaster.setItem_name(rs.getString("item_name"));
					itemMaster.setBrand(rs.getString("brand"));
					itemMaster.setIMEI(rs.getString("IMEI"));
					itemMaster.setReceived_from(rs.getString("particular_name"));
					itemMaster.setRef_no(rs.getString("ref_no"));
					itemMaster.setFilepath(rs.getString("filepath"));
					itemMaster.setRemarks(rs.getString("remarks"));

					itemList.add(itemMaster);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public List<ItemMaster> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemMaster> itemList) {
		this.itemList = itemList;
	}
}
