package com.de.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.de.bean.Item;
import com.de.service.AddTransactionService;
import com.de.service.ReturnItemService;
import com.opensymphony.xwork2.ModelDriven;

public class ReturnItemAction implements ModelDriven<Item> {

	private Item item = new Item();
	ReturnItemService returnItemService;
	
	
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
			
			public String returnItem() throws Exception {
				
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = request.getSession();

				String sessionUser = (String) session.getAttribute("loggedUserName");
				/* to check if user exists in session then success else go to login page */
				if (sessionUser == null || sessionUser.equals("")) {
					return "login";
				} 

				returnItemService = new ReturnItemService();
				if (returnItemService.returnItemService(item)) {
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
