package com.de.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.de.bean.StockBean;
import com.de.reporting.StockReport;
import com.de.service.AddStockService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AddStockAction extends ActionSupport implements ModelDriven<StockBean> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private StockBean stockBean = new StockBean();
	AddStockService addStockService;
	
	
	// Startup session run all time
		public String execute() {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();

			String sessionUser = (String) session.getAttribute("loggedUserName");
			/* to check if user exists in session then success else go to login page */
			if (sessionUser != null && !sessionUser.equals("")) {
				return "success";
			} else {
				return "login";
			}
		}
		
		public String addStock() {
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();

			String sessionUser = (String) session.getAttribute("loggedUserName");
			/* to check if user exists in session then success else go to login page */
			if (sessionUser == null || sessionUser.equals("")) {
				return "login";
			} 
			
			addStockService = new AddStockService();
			if (addStockService.addStock(stockBean)) {
				/*
				 * calling stock report to get tender details
				 */		
			StockReport stockReport = new StockReport();
			stockReport.execute();
			stockReport.getStockList();
				return "success";
			} else {
				return "error";
			}
			
		}
	
	@Override
	public StockBean getModel() {
		// TODO Auto-generated method stub
		return stockBean;
	}

}
