package com.de.reporting;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.de.bean.Item;
import com.de.bean.ItemInStock;
import com.de.dao.DaoInv;

public class InTransactionReport {
	
	// Item List variables
	private List<Item> itemList = null;
	Item item = new Item();
	DaoInv da2;
	ResultSet rs2 = null;

	// Item List variables
	private List<Item> itemList_atOffice = null;
	//Item item = new Item();
	//DaoInv da2;
	//ResultSet rs2 = null;
	
	// variables for getting brand from Item Master
    private List<Map<String, Object>> brandQuantitiesList4;
    DaoInv di10;
    ResultSet rs10 = null;
    
    private List<Map<String, Object>> brandQuantitiesList2;
    
    private List<Map<String, Object>> brandQuantitiesList3;
    
    // Brand condition items
    private List<ItemInStock> brandConditionItems;
    DaoInv da131;
    ResultSet rs131 = null;
    private Map<String, Map<String, Integer>> brandConditionMap;
	ItemInStock itemInStock = new ItemInStock();
	
	// Item in Stock List
			private List<ItemInStock> itemInStockList = null;
			DaoInv di6;
			ResultSet rs6 = null;


    
	

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public String execute() {
		
		//////////////////////////////////////////////////////////////////////
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		String sessionUser = (String) session.getAttribute("loggedUserName");
		/* to check if user exists in session then success else go to login page */
		if (sessionUser == null || sessionUser.equals("")) {
			return "login";
		} 
		
		///Session code completed here
		//////////////////////////////////////////////////////////////////////
		
		try {
			
			/*
			 * // Fetching Corrigendum/Ammendments in List
			 */
			da2 = new DaoInv();
			itemList = new ArrayList<Item>();
			//rs2 = da2.itemList();
			rs2 = da2.inRepairItemList();
			
			if (rs2 != null) {
				while (rs2.next()) {
					item = new Item();
					item.setType(rs2.getString("type"));
					item.setImei(rs2.getString("IMEI"));

					itemList.add(item);
					// one tender added in the previous list
				}
			}
			
			/*
			 * // Fetching Corrigendum/Ammendments in List
			 */
			da2 = new DaoInv();
			itemList_atOffice = new ArrayList<Item>();
			//rs2 = da2.itemList();
			rs2 = da2.atOfficeItemList();
			
			if (rs2 != null) {
				while (rs2.next()) {
					item = new Item();
					item.setType(rs2.getString("type"));
					item.setImei(rs2.getString("IMEI"));

					itemList_atOffice.add(item);
					// one tender added in the previous list
				}
			}
			
			
			/*
			 * // Fetching Brand wise quantities in Map function Java Collection Framework from item_master mysql table
			 */
	        brandQuantitiesList4 = new ArrayList<>();

	        
			di10 = new DaoInv();
			rs10 = di10.brandQuantitiesInRepairStock();
			if (rs10 != null) {
				while (rs10.next()) {
					 String brandName = rs10.getString(1);
		             int totalQuantity = rs10.getInt(2);
		             
		             Map<String, Object> brandQuantityMap = new HashMap<>(); 
		             
		             brandQuantityMap.put("brandName", brandName);
		             brandQuantityMap.put("totalQuantity", totalQuantity);
		             
		             System.out.println(brandName);
		             System.out.println(totalQuantity);
		             
		             brandQuantitiesList4.add(brandQuantityMap);		
		             
				}
			}
			
			/*
			 * // Fetching Brand wise quantities in Map function Java Collection Framework from item_master mysql table
			 */
	        brandQuantitiesList2 = new ArrayList<>();

	        
			di10 = new DaoInv();	
			rs10 = di10.brandQuantitiesatOfficeStock();
			if (rs10 != null) {
				while (rs10.next()) {
					 String brandName = rs10.getString(1);
		             int totalQuantity = rs10.getInt(2);
		             
		             Map<String, Object> brandQuantityMap = new HashMap<>(); 
		             
		             brandQuantityMap.put("brandName", brandName);
		             brandQuantityMap.put("totalQuantity", totalQuantity);
		             
		             System.out.println(brandName);
		             System.out.println(totalQuantity);
		             
		             brandQuantitiesList2.add(brandQuantityMap);		
		             
				}
			}
			
			/*
			 * // Fetching Brand wise quantities in Map function Java Collection Framework from item_master mysql table
			 */
	        brandQuantitiesList3 = new ArrayList<>();

	        
			di10 = new DaoInv();	
			rs10 = di10.brandQuantitiesatServiceCenterStock();
			if (rs10 != null) {
				while (rs10.next()) {
					 String brandName = rs10.getString(1);
		             int totalQuantity = rs10.getInt(2);
		             
		             Map<String, Object> brandQuantityMap = new HashMap<>(); 
		             
		             brandQuantityMap.put("brandName", brandName);
		             brandQuantityMap.put("totalQuantity", totalQuantity);
		             
		             System.out.println(brandName);
		             System.out.println(totalQuantity);
		             
		             brandQuantitiesList3.add(brandQuantityMap);		
		             
				}
			}
			
			/*/////////////////////////////////////////////////////////////////////////////////
			 * // Fetching item Condition List / WORKING / NOT-WORKING / OTHER REMAARKS Like 
			 */
			da131 = new DaoInv();
			// Process result set
	        brandConditionItems = new ArrayList<>();
			//rs131 = da131.itemBrandConditionList();
	        rs131 = da131.inRepairItemBrandConditionList();
			 // Initialize map to store results
			if (rs131 != null) {
				while (rs131.next()) {
					  itemInStock = new ItemInStock();
					  itemInStock.setBrand(rs131.getString(1));
					  itemInStock.setCurrent_status(rs131.getString(2));
					  itemInStock.setStatus_remarks(rs131.getString(3));
					  itemInStock.setItem_count(rs131.getInt(4));
					 
					System.out.print(itemInStock.getBrand());
					System.out.print(itemInStock.getCurrent_status());
					System.out.print(itemInStock.getItem_count());
					
					/*
					 * String brandName = rs131.getString(1); String condition = rs131.getString(2);
					 * int itemCount = rs131.getInt(3);
					 */
		            
					
					/*
					 * // If brandName does not exist in the map, add a new inner map
					 * 
					 * if (!brandConditionMap.containsKey(brandName)) {
					 * brandConditionMap.put(brandName, new HashMap<>()); }
					 * 
					 * 
					 * // Put condition and itemCount into the inner map
					 * brandConditionMap.get(brandName).put(condition, itemCount);
					 * 
					 */
		           
					

					brandConditionItems.add(itemInStock);
					// one tender added in the previous list
				}
				 System.out.print(brandConditionItems);
			}
			
			/////////////////////////////////////////////////////////////////////////////////////////
			// here method to get item list is implemented.
			
			// here method to get item in stock list is implemented.

			itemInStockList = new ArrayList<ItemInStock>();
			di6 = new DaoInv();
			//rs6 = di6.itemInStockReport();
			rs6 = di6.itemInRepairReport();
			if (rs6 != null) {
				while (rs6.next()) {
					itemInStock = new ItemInStock();
					itemInStock.setDate(rs6.getString(1));
					itemInStock.setIMEI(rs6.getString(2));
					itemInStock.setBrand(rs6.getString(3));
					itemInStock.setCc_name(rs6.getString(4));
					itemInStock.setSchool_name(rs6.getString(5));
					itemInStock.setStudent_name(rs6.getString(6));
					itemInStock.setType(rs6.getString(7));
					itemInStock.setCurrent_status(rs6.getString(8));
					itemInStock.setStatus_remarks(rs6.getString(9));
					//itemInStock.setRef_no(rs6.getString("ref_no"));
					//itemInStock.setWarranty_to(rs6.getString("warranty_to"));
					itemInStock.setRemarks(rs6.getString(10));

					itemInStockList.add(itemInStock);
				}
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return "success";
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public List<Item> getItemList_atOffice() {
		return itemList_atOffice;
	}

	public void setItemList_atOffice(List<Item> itemList_atOffice) {
		this.itemList_atOffice = itemList_atOffice;
	}
	public List<Map<String, Object>> getBrandQuantitiesList4() {
		return brandQuantitiesList4;
	}
	public void setBrandQuantitiesList4(List<Map<String, Object>> brandQuantitiesList4) {
		this.brandQuantitiesList4 = brandQuantitiesList4;
	}
	public List<Map<String, Object>> getBrandQuantitiesList2() {
		return brandQuantitiesList2;
	}

	public void setBrandQuantitiesList2(List<Map<String, Object>> brandQuantitiesList2) {
		this.brandQuantitiesList2 = brandQuantitiesList2;
	}

	public List<Map<String, Object>> getBrandQuantitiesList3() {
		return brandQuantitiesList3;
	}


	public void setBrandQuantitiesList3(List<Map<String, Object>> brandQuantitiesList3) {
		this.brandQuantitiesList3 = brandQuantitiesList3;
	}


	public Map<String, Map<String, Integer>> getBrandConditionMap() {
		return brandConditionMap;
	}


	public void setBrandConditionMap(Map<String, Map<String, Integer>> brandConditionMap) {
		this.brandConditionMap = brandConditionMap;
	}

	public List<ItemInStock> getBrandConditionItems() {
		return brandConditionItems;
	}


	public void setBrandConditionItems(List<ItemInStock> brandConditionItems) {
		this.brandConditionItems = brandConditionItems;
	}

	public List<ItemInStock> getItemInStockList() {
		return itemInStockList;
	}


	public void setItemInStockList(List<ItemInStock> itemInStockList) {
		this.itemInStockList = itemInStockList;
	}

}
