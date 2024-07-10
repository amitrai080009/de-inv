package com.upmscl.service.reporting;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.struts2.ServletActionContext;

import com.upmscl.bean.Blacklisting;
import com.upmscl.bean.Circular;
import com.upmscl.bean.Corrigendum;
import com.upmscl.bean.Dashboard;
import com.upmscl.bean.Tender;
import com.upmscl.bean.Vacancy;
import com.upmscl.dao.DaoAdmin;

public class TenderReport {

	// Tender List Variables
	private List<Tender> tenderList = null;
	Tender tender = new Tender();
	DaoAdmin da;
	ResultSet rs = null;

	// Corrigendum List variables
	private List<Corrigendum> amendList = null;
	Corrigendum amend = new Corrigendum();
	DaoAdmin da2;
	ResultSet rs2 = null;

	// Circuilar List variables
	private List<Circular> circularList = null;
	Circular circular = new Circular();
	DaoAdmin da3;
	ResultSet rs3 = null;

	// Blasklisting List variables
	private List<Blacklisting> blacklistingsList = null;
	Blacklisting blacklisting = new Blacklisting();
	DaoAdmin da4;
	ResultSet rs4 = null;

	// Vacancy List variables
	private List<Vacancy> vacancyList = null;
	Vacancy vacancy = new Vacancy();
	DaoAdmin da5;
	ResultSet rs5 = null;

	public String execute() {

		try {

			/*
			 * // Fetching tenders in List
			 */
			tenderList = new ArrayList<Tender>();
			da = new DaoAdmin();
			rs = da.report();
			if (rs != null) {
				while (rs.next()) {
					tender = new Tender();
					tender.setId(rs.getInt("id"));
					tender.setTender_no(rs.getString("tender_no"));
					tender.setTitle(rs.getString("description"));
					tender.setTender_end(rs.getString("tender_end"));
					tender.setFileFileName(rs.getString("filepath"));
					tender.setTender_cat(rs.getString("tender_cat"));
					tenderList.add(tender); // one tender added in the previous list
				}
			}
			/*
			 * // Fetching Corrigendum/Ammendments in List
			 */
			da2 = new DaoAdmin();
			amendList = new ArrayList<Corrigendum>();
			rs2 = da2.reportAmend();
			if (rs2 != null) {
				while (rs2.next()) {
					amend = new Corrigendum();
					amend.setId(rs2.getInt("id"));
					amend.setFileFileName(rs2.getString("filepath"));
					amend.setTitle(rs2.getString("title"));
					amendList.add(amend);
					// one tender added in the previous list
				}
			}
			/*
			 * // Fetching Circular in List
			 */
			da3 = new DaoAdmin();
			circularList = new ArrayList<Circular>();
			rs3 = da3.creport();
			if (rs3 != null) {
				while (rs3.next()) {
					circular = new Circular();
					circular.setId(rs3.getInt("id"));
					circular.setFileFileName(rs3.getString("filepath"));
					circular.setTitle(rs3.getString("content"));
					circularList.add(circular);
					// one tender added in the previous list
				}
			}

			/*
			 * // Fetching Blacklisting in List
			 */
			da4 = new DaoAdmin();
			blacklistingsList = new ArrayList<Blacklisting>();
			rs4 = da4.nsqreportall();
			if (rs4 != null) {
				while (rs4.next()) {
					blacklisting = new Blacklisting();
					blacklisting.setId(rs4.getInt("idnsq_drugs"));

					blacklistingsList.add(blacklisting);
					// one tender added in the previous list
				}
			}
			
			/*
			 * // Fetching Vacancy in List
			 */
			da5 = new DaoAdmin();
			vacancyList = new ArrayList<Vacancy>();
			rs5 = da5.vacancyReport();
			if (rs5 != null) {
				while (rs5.next()) {
					vacancy = new Vacancy();
					vacancy.setId(rs5.getInt("idvacancy_upmscl"));
					vacancy.setTitle(rs5.getString("title"));
					vacancy.setRefno(rs5.getString("refno"));
					vacancy.setFileFileName(rs5.getString("filepath"));
					vacancy.setUpdatedate(rs5.getString("update_date"));
					vacancy.setValidfrom(rs5.getString("validfrom"));
					vacancy.setValidto(rs5.getString("validto"));;
					
					vacancyList.add(vacancy);
					// one tender added in the previous list
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public List<Tender> getTenderList() {
		return tenderList;
	}

	public void setTenderList(List<Tender> tenderList) {
		this.tenderList = tenderList;
	}

	public List<Corrigendum> getAmendList() {
		return amendList;
	}

	public void setAmendList(List<Corrigendum> amendList) {
		this.amendList = amendList;
	}

	public List<Circular> getCircularList() {
		return circularList;
	}

	public void setCircularList(List<Circular> circularList) {
		this.circularList = circularList;
	}

	public List<Blacklisting> getBlacklistingsList() {
		return blacklistingsList;
	}

	public void setBlacklistingsList(List<Blacklisting> blacklistingsList) {
		this.blacklistingsList = blacklistingsList;
	}

	public List<Vacancy> getVacancyList() {
		return vacancyList;
	}

	public void setVacancyList(List<Vacancy> vacancyList) {
		this.vacancyList = vacancyList;
	}

}
