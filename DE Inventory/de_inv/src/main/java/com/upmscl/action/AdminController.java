package com.upmscl.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.upmscl.bean.Tender;
import com.upmscl.bean.Vacancy;
import com.upmscl.bean.Circular;
import com.upmscl.bean.Corrigendum;
import com.upmscl.service.CircularService;
import com.upmscl.service.CorrigendumService;
import com.upmscl.service.ManageTenderService;
import com.upmscl.service.VacancyService;
import com.upmscl.service.reporting.TenderReport;

public class AdminController extends ActionSupport implements ModelDriven<Tender> {

	/*varible declarations*/
	private static final long serialVersionUID = 1L;
	private Tender tender = new Tender();
	private Corrigendum corrigendum = new Corrigendum();
	private Circular circular = new Circular();
	private Vacancy vacancy = new Vacancy();
	ManageTenderService manageTenderService;
	CorrigendumService corrigendumService;
	CircularService circularService;
	VacancyService vacancyService;

	// Startup Tender run all time
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

	public String uploadTender() {
		manageTenderService = new ManageTenderService();
		if (manageTenderService.uploadTender(tender)) {
			/*
			 * calling tender report to get tender details
			 */			
			TenderReport tr = new TenderReport();
			tr.execute();
			tr.getTenderList();
			return "success";
		} else {
			return "error";
		}
	}
	
	public String uploadCorrigendum() {
		corrigendumService = new CorrigendumService();
		if(corrigendumService.uploadCorrigendum(corrigendum)) {
			return "success";
		}else {
			return "error";
		}
	}
	
	public String uploadCircular() {
		circularService = new CircularService();
		if(circularService.uploadCircular(circular)) {
			/*
			 * calling tender report to get tender details
			 */			
			TenderReport tr = new TenderReport();
			tr.execute();
			tr.getCircularList();// not being used not working
			return "success";
		}else {
			return "error";
		}
	}
	
	public String uploadVacancy() {
		vacancyService = new VacancyService();
		if (vacancyService.uploadVacancy(vacancy)) {
			/*
			 * calling vacancy report to get tender details
			 */			
			TenderReport tr = new TenderReport();
			tr.execute();
			tr.getVacancyList();
			return "success";
		} else {
			return "error";
		}
	}

	@Override
	public Tender getModel() {
		// TODO Auto-generated method stub
		return tender;
	}

	public Corrigendum getCorrigendum() {
		return corrigendum;
	}

	public void setCorrigendum(Corrigendum corrigendum) {
		this.corrigendum = corrigendum;
	}

	public Circular getCircular() {
		return circular;
	}

	public void setCircular(Circular circular) {
		this.circular = circular;
	}

	public Vacancy getVacancy() {
		return vacancy;
	}

	public void setVacancy(Vacancy vacancy) {
		this.vacancy = vacancy;
	}

	
}
