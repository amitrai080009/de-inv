package com.de.action;

import java.util.List;

import com.de.bean.City;
import com.de.dao.CityDAO;
import com.opensymphony.xwork2.ActionSupport;

public class CityAction extends ActionSupport {
    private int stateId;
    private List<City> cities;

    public String execute() {
        CityDAO dao = new CityDAO();
        cities = dao.getCitiesByState(stateId);
        return SUCCESS;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public List<City> getCities() {
        return cities;
    }
}