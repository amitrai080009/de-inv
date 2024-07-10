package com.de.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.de.bean.Item;
import com.de.bean.StockBean;
import com.de.reporting.ItemReport;
import com.de.reporting.StockReport;
import com.de.service.AddItemService;
import com.de.service.AddStockService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AddItemAction extends ActionSupport implements ModelDriven<Item> {

	private static final long serialVersionUID = 1L;

	private Item item = new Item();
	AddItemService addItemService;

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

	public String addItem() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		String sessionUser = (String) session.getAttribute("loggedUserName");
		/* to check if user exists in session then success else go to login page */
		if (sessionUser == null || sessionUser.equals("")) {
			return "login";
		} 

		addItemService = new AddItemService();
		if (addItemService.addItem(item)) {
			return "success";
		} else {
			return "error";
		}

	}

	@Override
	public Item getModel() {
		// TODO Auto-generated method stub
		return item;
	}

}
