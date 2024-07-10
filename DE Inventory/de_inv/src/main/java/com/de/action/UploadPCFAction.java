package com.de.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.de.bean.Item;
import com.de.service.PcfUploadService;
import com.opensymphony.xwork2.ModelDriven;

public class UploadPCFAction implements ModelDriven<Item> {

	private Item item = new Item();
	PcfUploadService pus;


	public String uploadPcf() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		String sessionUser = (String) session.getAttribute("loggedUserName");
		/* to check if user exists in session then success else go to login page */
		if (sessionUser == null || sessionUser.equals("")) {
			return "login";
		} 

		// Here starts actual method implementation
		pus = new PcfUploadService();
		if (pus.pcfUploadService(item)) {
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
