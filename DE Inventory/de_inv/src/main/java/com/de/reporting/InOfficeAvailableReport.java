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
import com.de.bean.Official;
import com.de.bean.StockBean;
import com.de.dao.*;


public class InOfficeAvailableReport {
	
	
	// Stock List Variables
		private List<StockBean> stockList = null;
		StockBean stock = new StockBean();
		private int totalStock;
		DaoInv da;
		ResultSet rs = null;

		// Item List variables
		private List<Item> itemList = null;
		Item item = new Item();
		DaoInv da2;
		ResultSet rs2 = null;

		// Item List variables
		private List<Item> itemList2 = null;
		Item item2 = new Item();
		DaoInv da3;
		ResultSet rs3 = null;
		
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
	    
	    // Brand condition items
	    private List<ItemInStock> brandConditionItems;
	    DaoInv da131;
	    ResultSet rs131 = null;
	    private Map<String, Map<String, Integer>> brandConditionMap;
	    
	 // Brand condition items
	    private List<ItemInStock> brandConditionItems1;
	    DaoInv da132;
	    ResultSet rs132 = null;



		public String execute() {
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();

			String sessionUser = (String) session.getAttribute("loggedUserName");
			/* to check if user exists in session then success else go to login page */
			if (sessionUser == null || sessionUser.equals("")) {
				return "login";
			} 

			try {

				/*
				 * // Fetching Stock in List
				 */
				stockList = new ArrayList<StockBean>();
				da = new DaoInv();
				//rs = da.stockList();
				rs = da.availableInStock();
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
				//rs2 = da2.itemList();
				rs2 = da2.availableWorkingItemList();
				
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
				rs13 = da13.inRepairList();
				if (rs13 != null) {
					while (rs13.next()) {
						item3 = new Item();
						item3.setBrand(rs13.getString(1));
						item3.setId(rs13.getInt(2));

						itemList3.add(item3);
						// one tender added in the previous list
					}
				}
				

				/*
				 * // Fetching item issued List
				 */
				da3 = new DaoInv();
				itemList2 = new ArrayList<Item>();
				//rs3 = da3.itemIssueList();
				rs3 = da3.availableNotWorkingItemList();
				if (rs3 != null) {
					while (rs3.next()) {
						item2 = new Item();
						item2.setDate(rs3.getString(1));
						item2.setImei(rs3.getString(2));
						item2.setCc_name(rs3.getString(3));
						item2.setSchool_name(rs3.getString(4));
						item2.setStudent_name(rs3.getString(5));
						item2.setUdise(rs3.getString(6));
						item2.setDesc(rs3.getString(7));
						item2.setFileFileName(rs3.getString(8));
						String filepath_pcf = rs3.getString(9);

						// logic to check if file available or not
						if (filepath_pcf == null || filepath_pcf == "") {
							item2.setFileFileName_pcf("no files");
						}

						itemList2.add(item2);
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
				
				/*/////////////////////////////////////////////////////////////////////////////////
				 * // Fetching item Condition List / WORKING / NOT-WORKING / OTHER REMAARKS Like 
				 */
				da131 = new DaoInv();
				// Process result set
		        brandConditionItems = new ArrayList<>();
				rs131 = da131.itemBrandConditionList();
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
				
				
				/*/////////////////////////////////////////////////////////////////////////////////
				 * // Fetching item Condition List / WORKING / NOT-WORKING / OTHER REMAARKS Like 
				 */
				da132 = new DaoInv();
				// Process result set
		        brandConditionItems1 = new ArrayList<>();
				//rs132 = da132.itemBrandConditionList();
				rs132 = da132.itemBrandConditionRemarksList();
				 // Initialize map to store results
				if (rs132 != null) {
					while(rs132.next()) {
						  itemInStock = new ItemInStock();
						  itemInStock.setBrand(rs132.getString(1));
						  itemInStock.setStatus_remarks(rs132.getString(2));
						  itemInStock.setItem_count(rs132.getInt(3));
						 
							/*
							 * System.out.print(itemInStock.getBrand());
							 * System.out.print(itemInStock.getCurrent_status());
							 * System.out.print(itemInStock.getItem_count());
							 */
						
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
			           
						

						brandConditionItems1.add(itemInStock);
						// one tender added in the previous list
					}
					 System.out.print(brandConditionItems1);
				}
				
				/////////////////////////////////////////////////////////////////////////////////////////
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
						itemInStock.setReceived_from(rs6.getString("received_from"));
						itemInStock.setParticular_name(rs6.getString("particular_name"));
						itemInStock.setBrand(rs6.getString("brand"));
						itemInStock.setCurrent_status(rs6.getString("current_status"));
						itemInStock.setStatus_remarks(rs6.getString("status_remarks"));
						itemInStock.setRef_no(rs6.getString("ref_no"));
						itemInStock.setWarranty_to(rs6.getString("warranty_to"));
						itemInStock.setRemarks(rs6.getString("remarks"));

						itemInStockList.add(itemInStock);
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
				//rs8 = di8.brandQuantitiesItem();
				rs8 = di8.brandQuantitiesWorkingItem();
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
				//rs9 = di9.brandQuantitiesIssuetoSchool();
				rs9 = di9.brandQuantitiesNotWorkingItem();
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
				rs12 = di12.brandQuantitiesInRepair();
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

		public List<ItemInStock> getBrandConditionItems() {
			return brandConditionItems;
		}

		public void setBrandConditionItems(List<ItemInStock> brandConditionItems) {
			this.brandConditionItems = brandConditionItems;
		}

		public Map<String, Map<String, Integer>> getBrandConditionMap() {
			return brandConditionMap;
		}

		public void setBrandConditionMap(Map<String, Map<String, Integer>> brandConditionMap) {
			this.brandConditionMap = brandConditionMap;
		}

		public List<ItemInStock> getBrandConditionItems1() {
			return brandConditionItems1;
		}

		public void setBrandConditionItems1(List<ItemInStock> brandConditionItems1) {
			this.brandConditionItems1 = brandConditionItems1;
		}


}
