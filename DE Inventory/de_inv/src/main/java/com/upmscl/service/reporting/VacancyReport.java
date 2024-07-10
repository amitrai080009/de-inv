package com.upmscl.service.reporting;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.upmscl.bean.Vacancy;
import com.upmscl.dao.DaoAdmin;

public class VacancyReport {

	private List<Vacancy> vacancyList = null;
	Vacancy vacancy = new Vacancy();
	DaoAdmin da;
	ResultSet rs = null;

	public String execute() {

		try {
			/*
			 * // Fetching Vacancy in List
			 */
			vacancyList = new ArrayList<Vacancy>();
			da = new DaoAdmin();
			rs = da.vacancyReport();
			if (rs != null) {
				while (rs.next()) {
					vacancy = new Vacancy();
					vacancy.setId(rs.getInt("idvacancy_upmscl"));
					vacancy.setTitle(rs.getString("title"));
					vacancy.setRefno(rs.getString("refno"));
					vacancy.setValidfrom(rs.getString("validfrom"));
					vacancy.setValidto(rs.getString("validto"));
					//vacancy.setFilepathfile(rs.getString("filepath"));
					vacancy.setUpdatedate(rs.getString("update_date"));

					vacancyList.add(vacancy); // one vacancy added in the previous list
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public List<Vacancy> getVacancyList() {
		return vacancyList;
	}

	public void setVacancyList(List<Vacancy> vacancyList) {
		this.vacancyList = vacancyList;
	}

	public Vacancy getVacancy() {
		return vacancy;
	}

	public void setVacancy(Vacancy vacancy) {
		this.vacancy = vacancy;
	}
}
