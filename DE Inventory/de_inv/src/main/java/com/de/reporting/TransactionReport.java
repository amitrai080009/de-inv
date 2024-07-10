package com.de.reporting;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.de.bean.ItemTransaction;
import com.de.bean.Official;
import com.de.dao.DaoInv;

public class TransactionReport {
	
	private List<ItemTransaction> listT = null;
	ItemTransaction itemT = new ItemTransaction();
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

			listT = new ArrayList<ItemTransaction>();
					di = new DaoInv();
					rs = di.transactionReport();
					if (rs != null) {
						while (rs.next()) {
							itemT = new ItemTransaction();
							itemT.setDate(rs.getString("date"));
							itemT.setIMEI(rs.getString("imei"));
							itemT.setDevice(rs.getString("item_name"));
							itemT.setType(rs.getString("type"));  //status on report page // to show the last transaction status
							itemT.setFrom(rs.getString("from_location"));
							itemT.setTo(rs.getString("to_location"));
							itemT.setFileFileName(rs.getString("filepath"));

							listT.add(itemT);
						}
					}
				}catch(

	Exception e)
	{
					e.printStackTrace();
				}return"success";
	}

	public List<ItemTransaction> getListT() {
		return listT;
	}

	public void setListT(List<ItemTransaction> listT) {
		this.listT = listT;
	}

	
}
