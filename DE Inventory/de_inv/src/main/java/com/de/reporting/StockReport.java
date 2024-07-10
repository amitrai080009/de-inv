package com.de.reporting;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.de.bean.*;
import com.de.dao.DaoInv;

public class StockReport {

	private List<StockBean> stockList = null;
	StockBean stockBean = new StockBean();
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
			// here method to get stock list is implemented.

			stockList = new ArrayList<StockBean>();
			di = new DaoInv();
			rs = di.stockReport();
			if (rs != null) {
				while (rs.next()) {
					stockBean = new StockBean();
					stockBean.setDate(rs.getString("date"));
					stockBean.setItem_name(rs.getString("item_name"));
					stockBean.setBrand(rs.getString("brand"));
					stockBean.setQuantity(rs.getInt("quantity"));
					stockBean.setSender(rs.getString("sender"));
					stockBean.setRef_no(rs.getString("ref_no"));
					stockBean.setFileFileName(rs.getString("filepath"));
					stockBean.setRemarks(rs.getString("remarks"));

					stockList.add(stockBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public List<StockBean> getStockList() {
		return stockList;
	}

	public void setStockList(List<StockBean> stockList) {
		this.stockList = stockList;
	}

}
