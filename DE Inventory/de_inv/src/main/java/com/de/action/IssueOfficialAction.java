package com.de.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.de.bean.Official;
import com.de.service.AddItemService;
import com.de.service.IssueOfficialService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class IssueOfficialAction extends ActionSupport implements ModelDriven<Official> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Official official = new Official();
	IssueOfficialService issueOfficialService;

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

	public String issueOfficial() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		String sessionUser = (String) session.getAttribute("loggedUserName");
		/* to check if user exists in session then success else go to login page */
		if (sessionUser == null || sessionUser.equals("")) {
			return "login";
		} 

		issueOfficialService = new IssueOfficialService();
		if (issueOfficialService.issueOfficial(official)) {
			return "success";
		} else {
			return "error";
		}

	}

	@Override
	public Official getModel() {
		// TODO Auto-generated method stub
		return official;
	}

}
