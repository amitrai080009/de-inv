package com.de.action;

import java.util.List;

import com.de.bean.State;
import com.de.dao.StateDAO;
import com.opensymphony.xwork2.ActionSupport;

public class StateAction extends ActionSupport {
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private int countryId;
	    private List<State> states;

	    public String execute() throws ClassNotFoundException {
	        StateDAO dao = new StateDAO();
	        states = dao.getStatesByCountry(countryId);
	        return SUCCESS;
	    }

	    public void setCountryId(int countryId) {
	        this.countryId = countryId;
	    }

	    public List<State> getStates() {
	        return states;
	    }
	}
