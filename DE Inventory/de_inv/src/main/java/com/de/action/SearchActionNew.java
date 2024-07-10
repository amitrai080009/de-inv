package com.de.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;

public class SearchActionNew extends ActionSupport {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String query;
    private List<Product> products;

    public String execute() {
        ProductDAO dao = new ProductDAO();
        products = dao.searchProducts(query);
        return SUCCESS;
    }

    // Getters and Setters
    public void setQuery(String query) {
        this.query = query;
    }

    public List<Product> getProducts() {
        return products;
    }
}
