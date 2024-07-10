package com.de.reporting;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.de.bean.Country;
import com.de.bean.Item;
import com.de.bean.ItemInStock;
import com.de.bean.Official;
import com.de.bean.StockBean;
import com.de.dao.*;
import com.opensymphony.xwork2.ModelDriven;

public class DeReportFilterData {
	
	private List<Country> countries; //newly added to get the dropdown loist for cc and school
	
	
	// Thsese variables are for getting the dropdown box selection on the top of the home page
	  private String cc_name; 
	  private String school_name;
	  private String Filter_Flag = "true";
	  
	  private String remarks;
	  private int remarks1;
	  
	  
	//column chart variables
	    private List<String> schoolNames;
	    private List<Integer> studentsCount;
	    private List<Integer> issuedLaptops;
	    
	    //pie chart variables
	    private List<String> categories; //BRAND
	    private List<Integer> counts; //Brand Count
	    
	    
	    //cc wise item issued vs item in transaction variables
	    
	    private List<String> ccNames;
	   

		private List<Integer> issuedItemCounts;
	    private List<Integer> itemInTransactionCounts;   

	// Stock List Variables
	private List<StockBean> stockList = null;
	StockBean stock = new StockBean();
	private int totalStock;
	DaoInv da;
	ResultSet rs = null;

	// Item List variables
	private List<Item> itemList = null;
	private List<Item> itemList_available = null;
	Item item = new Item();
	DaoInv da2;
	ResultSet rs2 = null;

	// Item List variables
	private List<Item> itemList2 = null;
	Item item2 = new Item();
	DaoInv da3;
	ResultSet rs3 = null;
	
	// Item List variables
		private List<Item> itemList2_ccwise = null;
		Item item2_ccwise = new Item();

			
	
	// Item List variables
		private List<Item> itemList2_active = null;
		Item item2_active = new Item();
		DaoInv da3_active;
		ResultSet rs3_active = null;
	
	// Item List variables In repair itema
		private List<Item> itemList3 = null;
		Item item3 = new Item();
		DaoInv da13;
		ResultSet rs13 = null;

	// Official Issue list
	private List<Official> officialList = null;
	Official official = new Official();
	DaoInv da4;
	ResultSet rs4 = null;

	// Official Issue list
	private List<Official> officialList2 = null;
	Official official2 = new Official();
	DaoInv di5;
	ResultSet rs5 = null;

	// Item in Stock List
	private List<ItemInStock> itemInStockList = null;
	ItemInStock itemInStock = new ItemInStock();
	DaoInv di6;
	ResultSet rs6 = null;
	
	//Map Variable for getting Brand wise Quantities
    private List<Map<String, Object>> brandQuantitiesList;
    DaoInv di7;
    ResultSet rs7 = null;
    
    // variables for getting brand from item master
    private List<Map<String, Object>> brandQuantitiesList2;
    DaoInv di8;
    ResultSet rs8 = null;
    
    // variables for getting brand from issue to school master
    private List<Map<String, Object>> brandQuantitiesList3;
    DaoInv di9;
    ResultSet rs9 = null;
    
 // variables for getting brand from issue to school master
    private List<Map<String, Object>> brandQuantitiesList3_active;
    DaoInv di9_active;
    ResultSet rs9_active = null;
    
    // variables for getting brand from Item Master
    private List<Map<String, Object>> brandQuantitiesList4;
    DaoInv di10;
    ResultSet rs10 = null;
    
    // variables for getting brand from issue to official master
    private List<Map<String, Object>> brandQuantitiesList5;
    DaoInv di11;
    ResultSet rs11 = null;
    
    // variables for getting brand from transaction master In repair items
    private List<Map<String, Object>> brandQuantitiesList6;
    DaoInv di12;
    ResultSet rs12 = null;


	public String execute() throws ClassNotFoundException {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		String sessionUser = (String) session.getAttribute("loggedUserName");
		/* to check if user exists in session then success else go to login page */
		if (sessionUser == null || sessionUser.equals("")) {
			return "login";
		} 
		
		//here we have called the getAllContries method which is getting the list of cc name and accordingly school and student name based on cc name.
		CountryDAO dao = new CountryDAO();
		countries = dao.getAllCountries();
		// here this method ends and 
		//now here we have implemented code to get the list of items and issued to details below and display on the de item school page for reference.


		try {
			
			/*
			 * // Column CHart using chart.JS
			 */
			da2 = new DaoInv();
			schoolNames = new ArrayList<>();
		    studentsCount = new ArrayList<>();
		    issuedLaptops = new ArrayList<>();
		
		    rs2 = da2.columnChart(cc_name, school_name);
			if (rs2 != null) {
				while (rs2.next()) {				
					//Chart.JS Chart variables    
		                schoolNames.add(rs2.getString("School_Name"));
		                studentsCount.add(rs2.getInt("Student_Count"));
		                issuedLaptops.add(rs2.getInt("Tablet_Count"));
					//itemList.add(item);
					// one tender added in the previous list
				}
			}

			
			/*
			 * // Pie CHart using chart.JS
			 */
			da2 = new DaoInv();
			categories = new ArrayList<>();
		    counts = new ArrayList<>();
		
		    rs2 = da2.pieChart();
			if (rs2 != null) {
				while (rs2.next()) {				
					//Chart.JS Chart variables    
					categories.add(rs2.getString(1));
	                counts.add(rs2.getInt(2));
					//itemList.add(item);
					// one tender added in the previous list
				}
			}

			/*
			 * // Horizontal bar chart using chart.JS
			 */
			da2 = new DaoInv();
			ccNames = new ArrayList<>();
			issuedItemCounts = new ArrayList<>();
			itemInTransactionCounts = new ArrayList<>();  
		
		    rs2 = da2.barChart();
			if (rs2 != null) {
				while (rs2.next()) {				
					//Chart.JS Chart variables    
					if(rs2.getInt("issued_item_count") > 0)
					{
					ccNames.add(rs2.getString("cc_name"));
					issuedItemCounts.add(rs2.getInt("issued_item_count"));
					itemInTransactionCounts.add(rs2.getInt("items_in_transaction_count"));
					//itemList.add(item);
					// one tender added in the previous list
					}
				}
			}
			
			/*
			 * // Horizontal bar chart using chart.JS
			 */
			da2 = new DaoInv();
			ccNames = new ArrayList<>();
			issuedItemCounts = new ArrayList<>();
			itemInTransactionCounts = new ArrayList<>();  
		
		    rs2 = da2.barChart(cc_name, school_name);
			if (rs2 != null) {
				while (rs2.next()) {				
					//Chart.JS Chart variables    
					if(rs2.getInt("issued_item_count") > 0)
					{
					ccNames.add(rs2.getString("cc_name"));
					issuedItemCounts.add(rs2.getInt("issued_item_count"));
					itemInTransactionCounts.add(rs2.getInt("items_in_transaction_count"));
					//itemList.add(item);
					// one tender added in the previous list
					}
				}
			}
			

			/*
			 * // Fetching Stock in List
			 */
			stockList = new ArrayList<StockBean>();
			da = new DaoInv();
			rs = da.stockList();
			if (rs != null) {
				while (rs.next()) {
					stock = new StockBean();
					stock.setTotal_stock(rs.getInt(1));

					// totalStock = getTotalStock();
					stockList.add(stock); // one tender added in the previous list
				}
			}
			/*
			 * // Fetching Corrigendum/Ammendments in List
			 */
			da2 = new DaoInv();
			itemList = new ArrayList<Item>();
			rs2 = da2.itemList();
			if (rs2 != null) {
				while (rs2.next()) {
					item = new Item();
					item.setId(rs2.getInt("item_id"));
					item.setImei(rs2.getString("IMEI"));

					itemList.add(item);
					// one tender added in the previous list
				}
			}
			
			
			/*
			 * // Fetching Corrigendum/Ammendments in List
			 */
			da13 = new DaoInv();
			itemList3 = new ArrayList<Item>();
			rs13 = da13.inRepairList(cc_name, school_name);
			if (rs13 != null) {
				while (rs13.next()) {
					item3 = new Item();
					//item3.setDate(rs13.getString(""));
					item3.setImei(rs13.getString(2));

					itemList3.add(item3);
					// one tender added in the previous list
					
					System.out.print("itemlist3 size =="+itemList3.size()+"");
				}
			}
			

			/*
			 * // Fetching item issued List
			 */
			da3 = new DaoInv();
			itemList2 = new ArrayList<Item>();
			rs3 = da3.itemIssueList(cc_name, school_name);
			if (rs3 != null) {
				while (rs3.next()) {
					item = new Item();
					item.setDate(rs3.getString(1));
					item.setImei(rs3.getString(2));
					item.setCc_name(rs3.getString(3));
					item.setSchool_name(rs3.getString(4));
					item.setStudent_name(rs3.getString(5));
					item.setUdise(rs3.getString(6));
					item.setDesc(rs3.getString(7));
					item.setFileFileName(rs3.getString(8));
					String filepath_pcf = rs3.getString(9);
					//remarks1 = rs3.getInt(10);
					
					if(rs3.getString(10) != null){
						item.setRemarks("IN-REPAIR");
					}
					//item2.setFileFileName_pcf(rs3.getString(9));

					// logic to check if file available or not
					
					/*
					 * if (filepath_pcf == null || filepath_pcf == "") {
					 * item.setFileFileName_pcf("no files"); }else {
					 * item.setFileFileName_pcf(filepath_pcf); }
					 */
					 
					  
					itemList2.add(item);
					// one tender added in the previous list
					System.out.println("Renmarkwa be saale"+remarks1);
				}
			}
			
			
			/*
			 * // Fetching item issued List
			 */
			da3 = new DaoInv();
			itemList2_ccwise = new ArrayList<Item>();
			//rs3 = da3.itemIssueList_ccwise();
			rs3 = da3.itemIssueList_ccwise();
			if (rs3 != null) {
				while (rs3.next()) {
					item2_ccwise = new Item();
					item2_ccwise.setId(rs3.getInt(1));
					item2_ccwise.setCc_name(rs3.getString(2));
					item2_ccwise.setBrand(rs3.getString(3));
					item2_ccwise.setIssued_item_count(rs3.getInt(4));
					item2_ccwise.setItems_in_transaction_count(rs3.getInt(5));
					/*
					 * item2.setUdise(rs3.getString(6)); item2.setDesc(rs3.getString(7));
					 * item2.setFileFileName(rs3.getString(8)); String filepath_pcf =
					 * rs3.getString(9);
					 */
					//item2.setFileFileName_pcf(rs3.getString(9));

					// logic to check if file available or not
					/*
					 * if (filepath_pcf == null || filepath_pcf == "") {
					 * item2.setFileFileName_pcf("no files"); }else {
					 * item2.setFileFileName_pcf(filepath_pcf); }
					 */
					 

					itemList2_ccwise.add(item2_ccwise);
					// one tender added in the previous list
				}
			}
			
			
			

			/*
			 * // Fetching item issued List
			 */
			da3 = new DaoInv();
			itemList2_ccwise = new ArrayList<Item>();
			//rs3 = da3.itemIssueList_ccwise();
			rs3 = da3.itemIssueList_ccwise(cc_name, school_name);
			if (rs3 != null) {
				while (rs3.next()) {
					item2_ccwise = new Item();
					item2_ccwise.setId(rs3.getInt(1));
					item2_ccwise.setCc_name(rs3.getString(2));
					item2_ccwise.setSchool_name(rs3.getString(3));
					item2_ccwise.setBrand(rs3.getString(4));
					item2_ccwise.setIssued_item_count(rs3.getInt(5));
					item2_ccwise.setItems_in_transaction_count(rs3.getInt(6));
					/*
					 * item2.setUdise(rs3.getString(6)); item2.setDesc(rs3.getString(7));
					 * item2.setFileFileName(rs3.getString(8)); String filepath_pcf =
					 * rs3.getString(9);
					 */
					//item2.setFileFileName_pcf(rs3.getString(9));

					// logic to check if file available or not
					/*
					 * if (filepath_pcf == null || filepath_pcf == "") {
					 * item2.setFileFileName_pcf("no files"); }else {
					 * item2.setFileFileName_pcf(filepath_pcf); }
					 */
					 

					itemList2_ccwise.add(item2_ccwise);
					// one tender added in the previous list
				}
			}
			
			/*
			 * // Fetching item issued List
			 */
			da3_active = new DaoInv();
			itemList2_active = new ArrayList<Item>();
			rs3_active = da3_active.itemIssueList_Active(cc_name, school_name);
			if (rs3_active != null) {
				while (rs3_active.next()) {
					item2_active = new Item();
					item2_active.setDate(rs3_active.getString(1));
					item2_active.setImei(rs3_active.getString(2));
					item2_active.setCc_name(rs3_active.getString(3));
					item2_active.setSchool_name(rs3_active.getString(4));
					item2_active.setStudent_name(rs3_active.getString(5));
					item2_active.setUdise(rs3_active.getString(6));
					item2_active.setDesc(rs3_active.getString(7));
					item2_active.setFileFileName(rs3_active.getString(8));
					String filepath_pcf = rs3_active.getString(9);
					//item2.setFileFileName_pcf(rs3.getString(9));

					// logic to check if file available or not
					
					/*
					 * if (filepath_pcf == null || filepath_pcf == "") {
					 * item2_active.setFileFileName_pcf("no files"); }else {
					 * item2_active.setFileFileName_pcf(filepath_pcf); }
					 */

					  itemList2_active.add(item2_active);
					// one tender added in the previous list
				}
			}
			
			
			
			/*
			 * // Fetching item issued List
			 */
			da4 = new DaoInv();
			officialList = new ArrayList<Official>();
			rs4 = da4.officialIssueList();
			if (rs4 != null) {
				while (rs4.next()) {
					official = new Official();
					official.setDate(rs4.getString(1));
					official.setImei(rs4.getString(2));

					officialList.add(official);
					// one tender added in the previous list
				}
			}

			// here method to get item list is implemented.

			officialList2 = new ArrayList<Official>();
			di5 = new DaoInv();
			rs5 = di5.officialIssueReport();
			if (rs5 != null) {
				while (rs5.next()) {
					official2 = new Official();
					official2.setDate(rs5.getString("date"));
					official2.setImei(rs5.getString("imei"));
					official2.setOfficial_name(rs5.getString("official_name"));
					official2.setDesignation(rs5.getString("designation"));
					official2.setType(rs5.getString("type"));
					official2.setType_other(rs5.getString("type_other"));
					official2.setOfficial_id_no(rs5.getString("official_id_no"));
					official2.setFileFileName(rs5.getString("filepath"));

					officialList2.add(official2);
				}
			}

			// here method to get item in stock list is implemented.

			itemInStockList = new ArrayList<ItemInStock>();
			di6 = new DaoInv();
			rs6 = di6.itemInStockReport();
			if (rs6 != null) {
				while (rs6.next()) {
					itemInStock = new ItemInStock();
					itemInStock.setDate(rs6.getString("date"));
					itemInStock.setIMEI(rs6.getString("IMEI"));
					itemInStock.setItem_name(rs6.getString("item_name"));
					itemInStock.setReceived_from(rs6.getString("particular_name"));
					itemInStock.setRef_no(rs6.getString("ref_no"));
					itemInStock.setWarranty_to(rs6.getString("warranty_to"));
					itemInStock.setRemarks(rs6.getString("remarks"));

					itemInStockList.add(itemInStock);
				}
			}
			

			/*
			 * // Fetching Corrigendum/Ammendments in List
			 */
			da2 = new DaoInv();
			itemList_available = new ArrayList<Item>();
			rs2 = da2.itemList_available();
			if (rs2 != null) {
				while (rs2.next()) {
					item = new Item();
					item.setId(rs2.getInt("item_id"));
					item.setImei(rs2.getString("IMEI"));

					itemList_available.add(item);
					// one tender added in the previous list
				}
			}
			//////////////////////////////////////////////////////fetching brand wise total quantities//////////////////////////////////////////////////////////////////////////////
			
			/*
			 * // Fetching Brand wise quantities in Map function Java Collection Framework
			 */
	        brandQuantitiesList = new ArrayList<>();

	        
			di7 = new DaoInv();
			rs7 = di7.brandQuantitiesStock();
			if (rs7 != null) {
				System.out.println("rs7 not blank");
				while (rs7.next()) {
					 String brandName = rs7.getString(1);
		             int totalQuantity = rs7.getInt(2);
		             
		             Map<String, Object> brandQuantityMap = new HashMap<>(); 
		             brandQuantityMap.put("brandName", brandName);
		             brandQuantityMap.put("totalQuantity", totalQuantity);
		             
		             System.out.println(brandName);
		             System.out.println(totalQuantity);
		             
		             brandQuantitiesList.add(brandQuantityMap);		
		             
				}
			}
			
			/*
			 * // Fetching Brand wise quantities in Map function Java Collection Framework from item_master mysql table
			 */
	        brandQuantitiesList2 = new ArrayList<>();

	        
			di8 = new DaoInv();
			rs8 = di8.brandQuantitiesItem();
			if (rs8 != null) {
				while (rs8.next()) {
					 String brandName = rs8.getString(1);
		             int totalQuantity = rs8.getInt(2);
		             
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

	        
			di9 = new DaoInv();
			rs9 = di9.brandQuantitiesIssuetoSchool();
			if (rs9 != null) {
				while (rs9.next()) {
					 String brandName = rs9.getString(1);
		             int totalQuantity = rs9.getInt(2);
		             
		             Map<String, Object> brandQuantityMap = new HashMap<>(); 
		             
		             brandQuantityMap.put("brandName", brandName);
		             brandQuantityMap.put("totalQuantity", totalQuantity);
		             
		             System.out.println(brandName);
		             System.out.println(totalQuantity);
		             
		             brandQuantitiesList3.add(brandQuantityMap);		
		             
				}
			}
			
			
			/*
			 * // Fetching Brand wise quantities in Map function Java Collection Framework from item_master mysql table
			 */
	        brandQuantitiesList3_active = new ArrayList<>();

	        
			di9_active = new DaoInv();
			rs9_active = di9_active.brandQuantitiesIssuetoSchool_active(cc_name, school_name);
			if (rs9_active != null) {
				while (rs9_active.next()) {
					 String brandName = rs9_active.getString(1);
		             int totalQuantity = rs9_active.getInt(2);
		             
		             Map<String, Object> brandQuantityMap = new HashMap<>(); 
		             
		             brandQuantityMap.put("brandName", brandName);
		             brandQuantityMap.put("totalQuantity", totalQuantity);
		             
		             System.out.println(brandName);
		             System.out.println(totalQuantity);
		             
		             brandQuantitiesList3_active.add(brandQuantityMap);		
		             
				}
			}
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			/*
			 * // Fetching Brand wise quantities in Map function Java Collection Framework from item_master mysql table
			 */
	        brandQuantitiesList4 = new ArrayList<>();

	        
			di10 = new DaoInv();
			rs10 = di10.brandQuantitiesAvailableStock();
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
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			
			/*
			 * // Fetching Brand wise quantities in Map function Java Collection Framework from item_master mysql table
			 */
	        brandQuantitiesList5 = new ArrayList<>();

	        
			di11 = new DaoInv();
			rs11 = di11.brandQuantitiesIssuetoOfficial();
			if (rs11 != null) {
				while (rs11.next()) {
					 String brandName = rs11.getString(1);
		             int totalQuantity = rs11.getInt(2);
		             
		             Map<String, Object> brandQuantityMap = new HashMap<>(); 
		             
		             brandQuantityMap.put("brandName", brandName);
		             brandQuantityMap.put("totalQuantity", totalQuantity);
		             
		             System.out.println(brandName);
		             System.out.println(totalQuantity);
		             
		             brandQuantitiesList5.add(brandQuantityMap);		
		             
				}
			}
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


			/*
			 * // Fetching Brand wise quantities in Map function Java Collection Framework from item_master mysql table
			 */
	        brandQuantitiesList6 = new ArrayList<>();

	        
			di12 = new DaoInv();
			rs12 = di12.brandQuantitiesInRepair_ccWise(cc_name, school_name);
			if (rs12 != null) {
				while (rs12.next()) {
					 String brandName = rs12.getString(1);
		             int totalQuantity = rs12.getInt(2);
		             
		             Map<String, Object> brandQuantityMap = new HashMap<>(); 
		             
		             brandQuantityMap.put("brandName", brandName);
		             brandQuantityMap.put("totalQuantity", totalQuantity);
		             
		             System.out.println(brandName);
		             System.out.println(totalQuantity);
		             
		             brandQuantitiesList6.add(brandQuantityMap);		
		             
				}
			}
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public void setBrandQuantitiesList(List<Map<String, Object>> brandQuantitiesList) {
		this.brandQuantitiesList = brandQuantitiesList;
	}

	public List<StockBean> getStockList() {
		return stockList;
	}

	public void setStockList(List<StockBean> stockList) {
		this.stockList = stockList;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public int getTotalStock() {
		return totalStock;
	}

	public void setTotalStock(int totalStock) {
		this.totalStock = totalStock;
	}

	public List<Item> getItemList2() {
		return itemList2;
	}

	public void setItemList2(List<Item> itemList2) {
		this.itemList2 = itemList2;
	}

	public List<Official> getOfficialList() {
		return officialList;
	}

	public void setOfficialList(List<Official> officialList) {
		this.officialList = officialList;
	}

	public List<Official> getOfficialList2() {
		return officialList2;
	}

	public void setOfficialList2(List<Official> officialList2) {
		this.officialList2 = officialList2;
	}

	public List<ItemInStock> getItemInStockList() {
		return itemInStockList;
	}

	public void setItemInStockList(List<ItemInStock> itemInStockList) {
		this.itemInStockList = itemInStockList;
	}

	public List<Map<String, Object>> getBrandQuantitiesList() {
		return brandQuantitiesList;
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

	public List<Map<String, Object>> getBrandQuantitiesList4() {
		return brandQuantitiesList4;
	}

	public void setBrandQuantitiesList4(List<Map<String, Object>> brandQuantitiesList4) {
		this.brandQuantitiesList4 = brandQuantitiesList4;
	}

	public List<Map<String, Object>> getBrandQuantitiesList5() {
		return brandQuantitiesList5;
	}

	public void setBrandQuantitiesList5(List<Map<String, Object>> brandQuantitiesList5) {
		this.brandQuantitiesList5 = brandQuantitiesList5;
	}

	public List<Map<String, Object>> getBrandQuantitiesList6() {
		return brandQuantitiesList6;
	}

	public void setBrandQuantitiesList6(List<Map<String, Object>> brandQuantitiesList6) {
		this.brandQuantitiesList6 = brandQuantitiesList6;
	}

	public List<Item> getItemList3() {
		return itemList3;
	}

	public void setItemList3(List<Item> itemList3) {
		this.itemList3 = itemList3;
	}


	public List<Item> getItemList2_active() {
		return itemList2_active;
	}

	public void setItemList2_active(List<Item> itemList2_active) {
		this.itemList2_active = itemList2_active;
	}

	public List<Map<String, Object>> getBrandQuantitiesList3_active() {
		return brandQuantitiesList3_active;
	}

	public void setBrandQuantitiesList3_active(List<Map<String, Object>> brandQuantitiesList3_active) {
		this.brandQuantitiesList3_active = brandQuantitiesList3_active;
	}

	public List<Item> getItemList2_ccwise() {
		return itemList2_ccwise;
	}

	public void setItemList2_ccwise(List<Item> itemList2_ccwise) {
		this.itemList2_ccwise = itemList2_ccwise;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public String getCc_name() {
		return cc_name;
	}

	public void setCc_name(String cc_name) {
		this.cc_name = cc_name;
	}

	public String getSchool_name() {
		return school_name;
	}

	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}

	public String getFilter_Flag() {
		return Filter_Flag;
	}

	public void setFilter_Flag(String filter_Flag) {
		Filter_Flag = filter_Flag;
	}

	public List<String> getSchoolNames() {
		return schoolNames;
	}

	public void setSchoolNames(List<String> schoolNames) {
		this.schoolNames = schoolNames;
	}

	public List<Integer> getStudentsCount() {
		return studentsCount;
	}

	public void setStudentsCount(List<Integer> studentsCount) {
		this.studentsCount = studentsCount;
	}

	public List<Integer> getIssuedLaptops() {
		return issuedLaptops;
	}

	public void setIssuedLaptops(List<Integer> issuedLaptops) {
		this.issuedLaptops = issuedLaptops;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<Integer> getCounts() {
		return counts;
	}

	public void setCounts(List<Integer> counts) {
		this.counts = counts;
	}

	public List<String> getCcNames() {
		return ccNames;
	}

	public void setCcNames(List<String> ccNames) {
		this.ccNames = ccNames;
	}

	public List<Integer> getIssuedItemCounts() {
		return issuedItemCounts;
	}

	public void setIssuedItemCounts(List<Integer> issuedItemCounts) {
		this.issuedItemCounts = issuedItemCounts;
	}

	public List<Integer> getItemInTransactionCounts() {
		return itemInTransactionCounts;
	}

	public void setItemInTransactionCounts(List<Integer> itemInTransactionCounts) {
		this.itemInTransactionCounts = itemInTransactionCounts;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getRemarks1() {
		return remarks1;
	}

	public void setRemarks1(int remarks1) {
		this.remarks1 = remarks1;
	}

	 public List<Item> getItemList_available() {
			return itemList_available;
		}

		public void setItemList_available(List<Item> itemList_available) {
			this.itemList_available = itemList_available;
		}
	 


}
