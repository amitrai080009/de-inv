package com.de.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.de.bean.Item;
import com.de.bean.ItemTransaction;
import com.de.service.AddItemService;
import com.de.service.AddTransactionService;
import com.opensymphony.xwork2.ModelDriven;

public class AddTransactionAction implements ModelDriven<ItemTransaction> {
	
	private ItemTransaction item = new ItemTransaction();
	AddTransactionService addTransactionService;
	
	
	// Startup session run all time ok
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

		public String addTransaction() throws Exception {
			
			/*Session Management*/
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();

			String sessionUser = (String) session.getAttribute("loggedUserName");
			/* to check if user exists in session then success else go to login page */
			if (sessionUser == null || sessionUser.equals("")) {
				return "login";
			} 
			/*Session Management*/

			addTransactionService = new AddTransactionService();
			if (addTransactionService.addTransactionService(item, sessionUser)) {
				return "success";
			} else {
				return "error";
			}

		}

		@Override
		public ItemTransaction getModel() {
			// TODO Auto-generated method stub
			return item;
		}


	

}
