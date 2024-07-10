package com.de.reporting;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.de.bean.ItemMaster;
import com.de.bean.Official;
import com.de.dao.DaoInv;

public class IssueOfficialReport {

	private List<Official> officialList = null;
	Official official = new Official();
	DaoInv di;
	ResultSet rs = null;

	public String execute() {
		try {
			
			//Session Checking
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();

			String sessionUser = (String) session.getAttribute("loggedUserName");
			/* to check if user exists in session then success else go to login page */
			if (sessionUser == null || sessionUser.equals("")) {
				return "login";
			} 
			
		// here method to get item list is implemented.

		officialList = new ArrayList<Official>();
					di = new DaoInv();
					rs = di.officialIssueReport();
					if (rs != null) {
						while (rs.next()) {
							official = new Official();
							official.setDate(rs.getString("date"));
							official.setImei(rs.getString("imei"));
							official.setOfficial_name(rs.getString("official_name"));
							official.setDesignation(rs.getString("designation"));
							official.setType(rs.getString("type"));
							official.setType_other(rs.getString("type_other"));
							official.setOfficial_id_no(rs.getString("official_id_no"));
							official.setFileFileName(rs.getString("filepath"));

							officialList.add(official);
						}
					}
				}catch(

	Exception e)
	{
					e.printStackTrace();
				}return"success";
	}

	public List<Official> getOfficialList() {
		return officialList;
	}

	public void setOfficialList(List<Official> officialList) {
		this.officialList = officialList;
	}

}
