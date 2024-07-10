package com.de.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.de.bean.Item;
import com.upmscl.dao.GetConnection;

public class DaoInv {

	GetConnection Conn = new GetConnection();

	public ResultSet stockReport() throws SQLException, Exception {
		// Method to get stock report
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM de_stock_master order by date desc";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet itemReport() throws SQLException, Exception {
		// Method to get Item List report
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM de_item_master order by date desc";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet stockList() throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT sum(quantity) FROM de_stock_master";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet itemList() throws SQLException, Exception {
		// Method to get Item List report
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM de_item_master";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet itemIssueList() throws SQLException, Exception {
		// Method to get Item List report
		ResultSet rs = null;
		try {
			// String sql = "SELECT * FROM de_item_issue_school";

			// join quesry to get details from issue school, cc, school, and student tables.
			String sql = "SELECT de_item_issue_school.date, \r\n" + "       de_item_issue_school.IMEI, \r\n"
					+ "       cc.cc_name, \r\n" + "       school.school_name, \r\n"
					+ "       student.student_name, \r\n" + "       student.UDISE, \r\n"
					+ "       de_item_issue_school.description, \r\n" + "       de_item_issue_school.filepath, \r\n"
					+ "		  de_item_issue_school.filepath_pcf, \r\n" + 
					  "		  de_item_issue_school.in_repair_flag \r\n" +
					"FROM de_item_issue_school\r\n"
					+ "JOIN cc ON de_item_issue_school.cc_name = cc.cc_id\r\n"
					+ "JOIN school ON de_item_issue_school.school_name = school.school_id\r\n"
					+ "JOIN student ON de_item_issue_school.student_name = student.student_id where active_flag=1"
					+ "";

			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet officialIssueReport() throws SQLException, Exception {
		// Method to get Item List report
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM de_item_issue_official";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet officialIssueList() throws SQLException, Exception {
		// Method to get Item List report
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM de_item_issue_official";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet itemInStockReport() throws SQLException, Exception {
		// Method to get Item List report
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM de_item_master where issue_flag = 0";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet transactionReport() throws SQLException, Exception {

		// Method to get Item List report
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM de_transaction_master";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet brandQuantitiesStock() throws SQLException, Exception {
		// Method to get Item List report
		ResultSet rs = null;
		try {
			String sql = "SELECT brand as brand_name, sum(quantity) as total_quantity FROM de_stock_master group by brand order by brand";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet brandQuantitiesItem() throws SQLException, Exception {
		// Method to get Item List report
				ResultSet rs = null;
				try {
					String sql = "select brand as brand_name, count(brand) as total_quantity from de_item_master group by brand order by brand";
					PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
					rs = ps.executeQuery();
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					if (Conn.getConnection() != null) {
						Conn.getConnection().close();
					}
				}
	}

	public ResultSet transactionReport(String idimei) throws SQLException, Exception {
		// Method to get Item List report
				ResultSet rs = null;
				try {
					String sql = "SELECT * FROM de_transaction_master where IMEI = ?";
					PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
					ps.setString(1, idimei);
					rs = ps.executeQuery();
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					if (Conn.getConnection() != null) {
						Conn.getConnection().close();
					}
				}
	}

	public ResultSet issueReport(String idimei) throws SQLException, Exception {
		// Method to get Item List report
		ResultSet rs = null;
		try {
			String sql = "SELECT de_item_issue_school.date as issue_date, de_item_issue_school.imei as imei, cc.cc_name as cc_name, \r\n"
					+ "school.school_name as school_name, student.student_name as student_name, student.UDISE as udise,  \r\n"
					+ "de_item_master.brand as brand_name, de_item_issue_school.filepath as filepath  \r\n"
					+ "FROM deinventory.de_item_issue_school\r\n"
					+ "join cc on de_item_issue_school.cc_name = cc.cc_id\r\n"
					+ "join school on de_item_issue_school.school_name = school.school_id\r\n"
					+ "join student on de_item_issue_school.student_name = student.student_id\r\n"
					+ "join de_item_master on de_item_issue_school.imei = de_item_master.IMEI\r\n"
					+ "where de_item_issue_school.imei = ?";
			
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			ps.setString(1, idimei);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet issueItemReport(Item item) throws SQLException, Exception {
		// Method to get Item List report
				ResultSet rs = null;
				try {
					String sql = "SELECT de_item_issue_school.date as issue_date, de_item_issue_school.imei as imei, cc.cc_name as cc_name, \r\n"
							+ "school.school_name as school_name, student.student_name as student_name, student.UDISE as udise,  \r\n"
							+ "de_item_master.brand as brand_name, de_item_issue_school.filepath as filepath  \r\n"
							+ "FROM deinventory.de_item_issue_school\r\n"
							+ "join cc on de_item_issue_school.cc_name = cc.cc_id\r\n"
							+ "join school on de_item_issue_school.school_name = school.school_id\r\n"
							+ "join student on de_item_issue_school.student_name = student.student_id\r\n"
							+ "join de_item_master on de_item_issue_school.imei = de_item_master.IMEI\r\n"
							+ "where de_item_issue_school.school_name = ?";
					
					PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
					ps.setString(1, item.getSchool_name());
					rs = ps.executeQuery();
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					if (Conn.getConnection() != null) {
						Conn.getConnection().close();
					}
				}
	}

	public ResultSet brandQuantitiesIssuetoSchool() throws SQLException, Exception {
		// Method to get Item List report
		ResultSet rs = null;
		try {
			/*
			 * String sql =
			 * "select de_item_master.brand as brand_name, count(de_item_issue_school.imei) as total_quantity \r\n"
			 * + "from de_item_issue_school\r\n" +
			 * "join de_item_master on de_item_issue_school.imei = de_item_master.IMEI\r\n"
			 * + "where de_item_issue_school.active_flag = 1\r\n" +
			 * "group by de_item_master.brand order by de_item_master.brand";
			 */
			
			String sql = " SELECT \r\n"
					+ "    m.brand AS brand_name, \r\n"
					+ "    COUNT(s.imei) + IFNULL(o.total_official_count, 0) AS total_quantity\r\n"
					+ "FROM \r\n"
					+ "    de_item_issue_school s\r\n"
					+ "    JOIN de_item_master m ON s.imei = m.IMEI\r\n"
					+ "    LEFT JOIN (\r\n"
					+ "        SELECT \r\n"
					+ "            m2.brand, COUNT(o.imei) AS total_official_count\r\n"
					+ "        FROM \r\n"
					+ "            de_item_issue_official o\r\n"
					+ "            JOIN de_item_master m2 ON o.imei = m2.IMEI\r\n"
					+ "        WHERE \r\n"
					+ "            o.active_flag = 1\r\n"
					+ "        GROUP BY \r\n"
					+ "            m2.brand\r\n"
					+ "    ) o ON m.brand = o.brand\r\n"
					+ "WHERE \r\n"
					+ "    s.active_flag = 1\r\n"
					+ "GROUP BY \r\n"
					+ "    m.brand, o.total_official_count  -- Include o.total_official_count in the GROUP BY clause\r\n"
					+ "ORDER BY \r\n"
					+ "    m.brand;\r\n"
					+ "\r\n"
					+ "";
			
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet brandQuantitiesAvailableStock() throws SQLException, Exception {
		// Method to get Item List report
				ResultSet rs = null;
				try {
					String sql = "select brand as brand_name, count(IMEI) as total_quantity from de_item_master \r\n"
							+ "where issue_flag = 0\r\n"
							+ "group by brand order by brand";
					
					PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
					rs = ps.executeQuery();
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					if (Conn.getConnection() != null) {
						Conn.getConnection().close();
					}
				}
	}

	public ResultSet brandQuantitiesIssuetoOfficial() throws SQLException, Exception {
		// Method to get Item List report
				ResultSet rs = null;
				try {
					String sql = "select de_item_master.brand as brand_name, count(de_item_issue_official.imei) as total_quantity \r\n"
							+ "from de_item_issue_official\r\n"
							+ "join de_item_master on de_item_issue_official.imei = de_item_master.IMEI\r\n"
							+ "where de_item_issue_official.active_flag = 1\r\n"
							+ "group by de_item_master.brand order by de_item_master.brand";
					
					PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
					rs = ps.executeQuery();
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					if (Conn.getConnection() != null) {
						Conn.getConnection().close();
					}
				}
			
	}

	public ResultSet brandQuantitiesInRepair() throws SQLException, Exception {
		// Method to get Item List report
		ResultSet rs = null;
		try {
			String sql = "select de_item_master.brand as brand_name, count(de_transaction_master.IMEI) as total_quantity \r\n"
					+ "from de_transaction_master\r\n"
					+ "join de_item_master on de_transaction_master.IMEI = de_item_master.IMEI\r\n"
					+ "where active_transaction = 1 and (type = 'IN_FOR_REPAIR' or type = 'OUT_SERVICE_CENTER' or type = 'IN_FROM_SERVICE_CENTER') \r\n"
					+ "group by de_item_master.brand order by de_item_master.brand";
			
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet inRepairList() throws SQLException, Exception {
		// Method to get Item List report
				ResultSet rs = null;
				try {
					String sql = "select de_item_master.brand as brand_name, count(de_transaction_master.IMEI) as total_quantity \r\n"
							+ "from de_transaction_master\r\n"
							+ "join de_item_master on de_transaction_master.IMEI = de_item_master.IMEI\r\n"
							+ "where active_transaction = 1 and (type = 'IN_FOR_REPAIR' or type = 'OUT_SERVICE_CENTER' or type = 'IN_FROM_SERVICE_CENTER') \r\n"
							+ "group by de_item_master.brand order by de_item_master.brand";
					
					PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
					rs = ps.executeQuery();
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					if (Conn.getConnection() != null) {
						Conn.getConnection().close();
					}
				}
	}

	public ResultSet availableInStock() throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT count(*) FROM de_item_master where issue_flag=0";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet availableWorkingItemList() throws SQLException, Exception {
		// Method to get Item List report
				ResultSet rs = null;
				try {
					//String sql = "SELECT count(*) FROM de_item_master where current_status= 'WORKING'";
					String sql = "SELECT * FROM de_item_master where current_status= 'WORKING' and issue_flag=0";
					PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
					rs = ps.executeQuery();
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					if (Conn.getConnection() != null) {
						Conn.getConnection().close();
					}
				}
	}

	public ResultSet availableNotWorkingItemList() throws SQLException, Exception {
		// Method to get Item List report
		ResultSet rs = null;
		try {
			//String sql = "SELECT count(*) FROM de_item_master where current_status= 'WORKING'";
			String sql = "SELECT * FROM de_item_master where current_status= 'NOT-WORKING' and issue_flag=0";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet brandQuantitiesWorkingItem() throws SQLException, Exception {
		// Method to get Item List report
		ResultSet rs = null;
		try {
			String sql = "select brand as brand_name, count(brand) as total_quantity from de_item_master where current_status='WORKING' and issue_flag=0 group by brand order by brand";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet brandQuantitiesNotWorkingItem() throws SQLException, Exception {
		// Method to get Item List report
				ResultSet rs = null;
				try {
					String sql = "select brand as brand_name, count(brand) as total_quantity from de_item_master where current_status='NOT-WORKING' and issue_flag=0 group by brand order by brand";
					
					PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
					rs = ps.executeQuery();
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					if (Conn.getConnection() != null) {
						Conn.getConnection().close();
					}
				}
	}

	public ResultSet itemBrandConditionList() throws SQLException, Exception {
		// Method to get Item List report
		ResultSet rs = null;
		String issue_flag = "0";
		try {
			/*
			 * String sql =
			 * "select brand, current_status, count(*) from de_item_master where issue_flag=? group by brand, current_status order by brand"
			 * ;
			 */
			String sql = "select brand, current_status, status_remarks, count(*) from de_item_master \r\n"
					+ "where issue_flag=? \r\n"
					+ "group by brand, current_status, status_remarks \r\n"
					+ "order by brand, current_status desc\r\n"
					+ "";

			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			ps.setString(1, issue_flag);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet itemBrandConditionRemarksList() throws SQLException, Exception {
		// Method to get Item List report
				ResultSet rs = null;
				String issue_flag = "0";
				String current_status = "NOT-WORKING";
				try {
					String sql = "select brand, status_remarks, count(*) from de_item_master where issue_flag=? and current_status = ? group by brand, status_remarks order by brand";
					
					PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
					ps.setString(1, issue_flag);
					ps.setString(2, current_status);
					rs = ps.executeQuery();
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					if (Conn.getConnection() != null) {
						Conn.getConnection().close();
					}
				}
	}

	public ResultSet itemIssueList_Active() throws SQLException, Exception {
		// Method to get Item List report
				ResultSet rs = null;
				try {
					// String sql = "SELECT * FROM de_item_issue_school";

					// join quesry to get details from issue school, cc, school, and student tables.
					String sql = "SELECT de_item_issue_school.date, \r\n" + "       de_item_issue_school.IMEI, \r\n"
							+ "       cc.cc_name, \r\n" + "       school.school_name, \r\n"
							+ "       student.student_name, \r\n" + "       student.UDISE, \r\n"
							+ "       de_item_issue_school.description, \r\n" + "       de_item_issue_school.filepath, \r\n"
							+ "		  de_item_issue_school.filepath_pcf \r\n" + "FROM de_item_issue_school\r\n"
							+ "JOIN cc ON de_item_issue_school.cc_name = cc.cc_id\r\n"
							+ "JOIN school ON de_item_issue_school.school_name = school.school_id\r\n"
							+ "JOIN student ON de_item_issue_school.student_name = student.student_id where active_flag=1 and in_repair_flag is null"
							+ "";

					PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
					rs = ps.executeQuery();
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					if (Conn.getConnection() != null) {
						Conn.getConnection().close();
					}
				}
	}

	public ResultSet brandQuantitiesIssuetoSchool_active() throws SQLException, Exception {
		// Method to get Item List report
				ResultSet rs = null;
				try {
					/*
					 * String sql =
					 * "select de_item_master.brand as brand_name, count(de_item_issue_school.imei) as total_quantity \r\n"
					 * + "from de_item_issue_school\r\n" +
					 * "join de_item_master on de_item_issue_school.imei = de_item_master.IMEI\r\n"
					 * + "where de_item_issue_school.active_flag = 1\r\n" +
					 * "group by de_item_master.brand order by de_item_master.brand";
					 */
					
					String sql = " SELECT \r\n"
							+ "    m.brand AS brand_name, \r\n"
							+ "    COUNT(s.imei) + IFNULL(o.total_official_count, 0) AS total_quantity\r\n"
							+ "FROM \r\n"
							+ "    de_item_issue_school s\r\n"
							+ "    JOIN de_item_master m ON s.imei = m.IMEI\r\n"
							+ "    LEFT JOIN (\r\n"
							+ "        SELECT \r\n"
							+ "            m2.brand, COUNT(o.imei) AS total_official_count\r\n"
							+ "        FROM \r\n"
							+ "            de_item_issue_official o\r\n"
							+ "            JOIN de_item_master m2 ON o.imei = m2.IMEI\r\n"
							+ "        WHERE \r\n"
							+ "            o.active_flag = 1\r\n"
							+ "        GROUP BY \r\n"
							+ "            m2.brand\r\n"
							+ "    ) o ON m.brand = o.brand\r\n"
							+ "WHERE \r\n"
							+ "    s.active_flag = 1 and in_repair_flag is null \r\n"
							+ "GROUP BY \r\n"
							+ "    m.brand, o.total_official_count  -- Include o.total_official_count in the GROUP BY clause\r\n"
							+ "ORDER BY \r\n"
							+ "    m.brand;\r\n"
							+ "\r\n"
							+ "";
					
					PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
					rs = ps.executeQuery();
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					if (Conn.getConnection() != null) {
						Conn.getConnection().close();
					}
				}
	}

	public ResultSet inRepairItemList() throws SQLException, Exception {
		// Method to get Item List report
		ResultSet rs = null;
		try {
			//String sql = "SELECT count(*) FROM de_item_master where current_status= 'WORKING'";
			String sql = "SELECT * FROM deinventory.de_transaction_master where active_transaction = 1 "
					+ "and (type = 'IN_FOR_REPAIR' or type = 'OUT_SERVICE_CENTER' or type = 'IN_FROM_SERVICE_CENTER')";
			
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet atOfficeItemList() throws SQLException, Exception {
		// Method to get Item List report
				ResultSet rs = null;
				try {
					//String sql = "SELECT count(*) FROM de_item_master where current_status= 'WORKING'";
					String sql = "SELECT * FROM deinventory.de_transaction_master where active_transaction = 1\r\n"
							+ "and (type = 'IN_FOR_REPAIR' or type = 'IN_FROM_SERVICE_CENTER') and to_location = 'AIF Office Amreli'";
					
					PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
					rs = ps.executeQuery();
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					if (Conn.getConnection() != null) {
						Conn.getConnection().close();
					}
				}	}

	public ResultSet brandQuantitiesInRepairStock() throws SQLException, Exception {
		// Method to get Item List report
		ResultSet rs = null;
		try {
			String sql = "select de_item_master.brand as brand_name, count(de_transaction_master.IMEI) as total_quantity\r\n"
					+ "from de_transaction_master\r\n"
					+ "join de_item_master on de_transaction_master.IMEI = de_item_master.IMEI\r\n"
					+ "where active_transaction = 1 and (type = 'IN_FOR_REPAIR' or type = 'OUT_SERVICE_CENTER' or type = 'IN_FROM_SERVICE_CENTER')\r\n"
					+ "group by de_item_master.brand order by de_item_master.brand";
			
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet brandQuantitiesatOfficeStock() throws SQLException, Exception {
		// Method to get Item List report
				ResultSet rs = null;
				try {
					String sql = "select de_item_master.brand as brand_name, count(de_transaction_master.IMEI) as total_quantity\r\n"
							+ "from de_transaction_master\r\n"
							+ "join de_item_master on de_transaction_master.IMEI = de_item_master.IMEI\r\n"
							+ "where active_transaction = 1 and (type = 'IN_FOR_REPAIR' or type = 'IN_FROM_SERVICE_CENTER') and to_location = 'AIF Office Amreli' \r\n"
							+ "group by de_item_master.brand order by de_item_master.brand";
					
					PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
					rs = ps.executeQuery();
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					if (Conn.getConnection() != null) {
						Conn.getConnection().close();
					}
				}
	}

	public ResultSet brandQuantitiesatServiceCenterStock() throws SQLException, Exception {
		// Method to get Item List report
		ResultSet rs = null;
		try {
			String sql = "select de_item_master.brand as brand_name, count(de_transaction_master.IMEI) as total_quantity\r\n"
					+ "from de_transaction_master\r\n"
					+ "join de_item_master on de_transaction_master.IMEI = de_item_master.IMEI\r\n"
					+ "where active_transaction = 1 and type = 'OUT_SERVICE_CENTER' \r\n"
					+ "group by de_item_master.brand order by de_item_master.brand";
			
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet inRepairItemBrandConditionList() throws SQLException, Exception {
		// Method to get Item List report
				ResultSet rs = null;
				String issue_flag = "0";
				try {
					/*
					 * String sql =
					 * "select brand, current_status, count(*) from de_item_master where issue_flag=? group by brand, current_status order by brand"
					 * ;
					 */
					String sql = "SELECT\r\n"
							+ "    im.brand,\r\n"
							+ "    tm.current_status,\r\n"
							+ "    tm.status_remarks,\r\n"
							+ "    COUNT(*) AS count_records\r\n"
							+ "FROM\r\n"
							+ "    de_item_master im\r\n"
							+ "JOIN\r\n"
							+ "    de_transaction_master tm ON im.IMEI = tm.IMEI\r\n"
							+ "WHERE\r\n"
							+ "    tm.active_transaction = 1\r\n"
							+ "    AND tm.type IN ('IN_FOR_REPAIR', 'OUT_SERVICE_CENTER', 'IN_FROM_SERVICE_CENTER')\r\n"
							+ "GROUP BY\r\n"
							+ "    im.brand,\r\n"
							+ "    tm.current_status,\r\n"
							+ "    tm.status_remarks\r\n"
							+ "ORDER BY\r\n"
							+ "    im.brand ASC,\r\n"
							+ "    tm.current_status DESC;\r\n"
							+ "";

					PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
					//ps.setString(1, issue_flag);
					rs = ps.executeQuery();
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					if (Conn.getConnection() != null) {
						Conn.getConnection().close();
					}
				}
	}

	public ResultSet itemInRepairReport() throws SQLException, Exception {
		// Method to get Item List report
				ResultSet rs = null;
				try {
					String sql = "SELECT\r\n"
							+ "    tm.date,\r\n"
							+ "    tm.IMEI,\r\n"
							+ "    im.brand,\r\n"
							+ "    cc.cc_name,\r\n"
							+ "    school.school_name,\r\n"
							+ "    student.student_name,\r\n"
							+ "    tm.type,\r\n"
							+ "    tm.current_status,\r\n"
							+ "    tm.status_remarks, \r\n"
							+ "    tm.remarks\r\n"
							+ "FROM\r\n"
							+ "    de_transaction_master tm\r\n"
							+ "JOIN\r\n"
							+ "    de_item_master im ON im.IMEI = tm.IMEI\r\n"
							+ "JOIN\r\n"
							+ "    de_item_issue_school iss ON im.IMEI = iss.IMEI\r\n"
							+ "JOIN\r\n"
							+ "    cc ON iss.cc_name = cc.cc_id -- Adjust join condition based on actual relationship\r\n"
							+ "JOIN\r\n"
							+ "    school ON iss.school_name = school.school_id -- Adjust join condition based on actual relationship\r\n"
							+ "JOIN\r\n"
							+ "    student ON iss.student_name = student.student_id -- Adjust join condition based on actual relationship\r\n"
							+ "WHERE\r\n"
							+ "    tm.active_transaction = 1 and iss.active_flag = 1 \r\n"
							+ "    AND tm.type IN ('IN_FOR_REPAIR', 'OUT_SERVICE_CENTER', 'IN_FROM_SERVICE_CENTER')";
					
					PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
					rs = ps.executeQuery();
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					if (Conn.getConnection() != null) {
						Conn.getConnection().close();
					}
				}
	}

	public ResultSet itemIssueList_ccwise() throws SQLException, Exception {
		// Method to get Item List report
				ResultSet rs = null;
				try {
					// String sql = "SELECT * FROM de_item_issue_school";

					// join quesry to get details from issue school, cc, school, and student tables.
					String sql = "SELECT\r\n"
							+ "    cc.cc_id,\r\n"
							+ "    cc.cc_name,\r\n"
							+ "    de_item_master.brand AS brand_name,\r\n"
							+ "    COUNT(DISTINCT case when de_item_issue_school.active_flag = 1 then de_item_issue_school.imei end) AS issued_item_count,\r\n"
							+ "    COUNT(DISTINCT case when de_item_issue_school.in_repair_flag = 1 then de_item_issue_school.imei end) AS items_in_transaction_count\r\n"
							+ "FROM\r\n"
							+ "    cc\r\n"
							+ "LEFT JOIN de_item_issue_school ON cc.cc_id = de_item_issue_school.cc_name\r\n"
							+ "LEFT JOIN de_item_master ON de_item_issue_school.imei = de_item_master.imei\r\n"
							+ "LEFT JOIN de_transaction_master ON de_item_master.imei = de_transaction_master.imei\r\n"
							+ "GROUP BY\r\n"
							+ "    cc.cc_id,\r\n"
							+ "    cc.cc_name,\r\n"
							+ "    de_item_master.brand\r\n"
							+ "ORDER BY\r\n"
							+ "    cc.cc_id;";

					PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
					rs = ps.executeQuery();
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					if (Conn.getConnection() != null) {
						Conn.getConnection().close();
					}
				}
	}

	public ResultSet itemIssueList(String cc_name, String school_name) throws SQLException, Exception {
		// Method to get Item List report
				ResultSet rs = null;
				try {
					// String sql = "SELECT * FROM de_item_issue_school";

					// join quesry to get details from issue school, cc, school, and student tables.
					
					  String sql = "SELECT de_item_issue_school.date, \r\n" +
					  "       de_item_issue_school.IMEI, \r\n" + "       cc.cc_name, \r\n" +
					  "       school.school_name, \r\n" + "       student.student_name, \r\n" +
					  "       student.UDISE, \r\n" +
					  "       de_item_issue_school.description, \r\n" +
					  "       de_item_issue_school.filepath, \r\n" +
					  "		  de_item_issue_school.filepath_pcf, \r\n"+
					  "		  de_item_issue_school.in_repair_flag \r\n" +
					  "FROM de_item_issue_school\r\n" +
					  "JOIN cc ON de_item_issue_school.cc_name = cc.cc_id\r\n" +
					  "JOIN school ON de_item_issue_school.school_name = school.school_id\r\n" +
					  "JOIN student ON de_item_issue_school.student_name = student.student_id where active_flag=1 \r\n" // and in_repair_flag is null,,, IT WII SHOW ITEM IN REPAIR ALSO
					  + "AND 1=1"; // Initial condition to start appending filters
					 
				//yaha se
			
					 // Append school name filter if provided
		            if (school_name != null && !school_name.isEmpty()) {
		                sql += " AND school.school_id = ?";
		            }
		            
		            // Append cc name filter if provided
		            if (cc_name != null && !cc_name.isEmpty()) {
		                sql += " AND cc.cc_id = ?";
		            }

					PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
					int parameterIndex = 1;
					
					 // Set parameters based on provided filters
		            if (school_name != null && !school_name.isEmpty()) {
		                ps.setString(parameterIndex++, school_name);
		            }
		            if (cc_name != null && !cc_name.isEmpty()) {
		                ps.setString(parameterIndex++, cc_name);
		            }
		            
		            //yaha tak
		            
					rs = ps.executeQuery();
					
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					if (Conn.getConnection() != null) {
						Conn.getConnection().close();
					}
				}
	}

	public ResultSet itemIssueList_Active(String cc_name, String school_name) throws SQLException, Exception {
		// Method to get Item List report
		ResultSet rs = null;
		try {
			// String sql = "SELECT * FROM de_item_issue_school";

			// join quesry to get details from issue school, cc, school, and student tables.
			/*
			 * String sql = "SELECT de_item_issue_school.date, \r\n" +
			 * "       de_item_issue_school.IMEI, \r\n" + "       cc.cc_name, \r\n" +
			 * "       school.school_name, \r\n" + "       student.student_name, \r\n" +
			 * "       student.UDISE, \r\n" +
			 * "       de_item_issue_school.description, \r\n" +
			 * "       de_item_issue_school.filepath, \r\n" +
			 * "		  de_item_issue_school.filepath_pcf \r\n" +
			 * "FROM de_item_issue_school\r\n" +
			 * "JOIN cc ON de_item_issue_school.cc_name = cc.cc_id\r\n" +
			 * "JOIN school ON de_item_issue_school.school_name = school.school_id\r\n" +
			 * "JOIN student ON de_item_issue_school.student_name = student.student_id where active_flag=1 and in_repair_flag is null \r\n"
			 * + "AND 1=1"; // Initial condition to start appending filters
			 */
			
			  String sql = "SELECT de_item_issue_school.date, \r\n" +
					  "       de_item_issue_school.IMEI, \r\n" + "       cc.cc_name, \r\n" +
					  "       school.school_name, \r\n" + "       student.student_name, \r\n" +
					  "       student.UDISE, \r\n" +
					  "       de_item_issue_school.description, \r\n" +
					  "       de_item_issue_school.filepath, \r\n" +
					  "		  de_item_issue_school.filepath_pcf \r\n" +
					  "FROM de_item_issue_school\r\n" +
					  "JOIN cc ON de_item_issue_school.cc_name = cc.cc_id\r\n" +
					  "JOIN school ON de_item_issue_school.school_name = school.school_id\r\n" +
					  "JOIN student ON de_item_issue_school.student_name = student.student_id where active_flag=1 and in_repair_flag is null \r\n"
					  + "AND 1=1"; // Initial condition to start appending filters
			
			
			//yaha se
			
			 // Append school name filter if provided
           if (school_name != null && !school_name.isEmpty()) {
               sql += " AND school.school_id = ?";
           }
           
           // Append cc name filter if provided
           if (cc_name != null && !cc_name.isEmpty()) {
               sql += " AND cc.cc_id = ?";
           }

			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			int parameterIndex = 1;
			
			 // Set parameters based on provided filters
           if (school_name != null && !school_name.isEmpty()) {
               ps.setString(parameterIndex++, school_name);
           }
           if (cc_name != null && !cc_name.isEmpty()) {
               ps.setString(parameterIndex++, cc_name);
           }
           
           //yaha tak
						
			
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet inRepairList(String cc_name, String school_name) throws SQLException, Exception {
		// Method to get Item List report
		ResultSet rs = null;
		try {
			/*
			 * String sql =
			 * "  select * from de_item_issue_school where in_repair_flag = 1 \r\n" +
			 * " AND 1=1";
			 */
			
			  String sql = "SELECT de_item_issue_school.date, de_item_issue_school.IMEI, cc.cc_name, school.school_name, \r\n"
			  		+ "  student.student_name, student.UDISE, de_item_issue_school.description, de_item_issue_school.filepath,\r\n"
			  		+ " de_item_issue_school.filepath_pcf FROM de_item_issue_school\r\n"
			  		+ "JOIN cc ON de_item_issue_school.cc_name = cc.cc_id\r\n"
			  		+ "JOIN school ON de_item_issue_school.school_name = school.school_id\r\n"
			  		+ "JOIN student ON de_item_issue_school.student_name = student.student_id where active_flag=1 and in_repair_flag is not null \r\n"
					  + "AND 1=1"; // Initial condition to start appending filters
			
			
			//yaha se
			
			 // Append school name filter if provided
          if (school_name != null && !school_name.isEmpty()) {
              sql += " AND school.school_id = ?";
          }
          
          // Append cc name filter if provided
          if (cc_name != null && !cc_name.isEmpty()) {
              sql += " AND cc.cc_id = ?";
          }
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			int parameterIndex = 1;
			
			 // Set parameters based on provided filters
          if (school_name != null && !school_name.isEmpty()) {
              ps.setString(parameterIndex++, school_name);
          }
          if (cc_name != null && !cc_name.isEmpty()) {
              ps.setString(parameterIndex++, cc_name);
          }
          
          //yaha tak
		
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet columnChart() throws SQLException, Exception {
		// Method to get Item List report
		ResultSet rs = null;
		try {
			// String sql = "SELECT * FROM de_item_issue_school";

			// join quesry to get details from issue school, cc, school, and student tables.
			String sql = "SELECT " +
		             "    school.school_name AS School_Name, " +
		             "    COUNT(DISTINCT de_item_issue_school.imei) AS Tablet_Count, " +
		             "    COUNT(DISTINCT student.student_id) AS Student_Count " +
		             "FROM " +
		             "    school " +
		             "LEFT JOIN " +
		             "    de_item_issue_school ON school.school_id = de_item_issue_school.school_name " +
		             "LEFT JOIN " +
		             "    student ON school.school_id = student.school_id " +
		             "WHERE " +
		             "    de_item_issue_school.active_flag = 1 " +
		             "GROUP BY " +
		             "    school.school_name";


			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet pieChart() throws SQLException, Exception {
		// Method to get Item List report
				ResultSet rs = null;
				try {
					// String sql = "SELECT * FROM de_item_issue_school";

					// join quesry to get details from issue school, cc, school, and student tables.
					String sql = "SELECT brand,quantity FROM de_stock_master";


					PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
					rs = ps.executeQuery();
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					if (Conn.getConnection() != null) {
						Conn.getConnection().close();
					}
				}
	}

	public ResultSet barChart() throws SQLException, Exception {
		// Method to get Item List report
		ResultSet rs = null;
		try {
			// String sql = "SELECT * FROM de_item_issue_school";

			// join quesry to get details from issue school, cc, school, and student tables.
			 String sql = "SELECT\r\n"
						+ "    cc.cc_id,\r\n"
						+ "    cc.cc_name,\r\n"
						+ "    COUNT(DISTINCT case when de_item_issue_school.active_flag = 1 then de_item_issue_school.imei end) AS issued_item_count,\r\n"
						+ "    COUNT(DISTINCT case when de_item_issue_school.in_repair_flag = 1 then de_item_issue_school.imei end) AS items_in_transaction_count\r\n"
						+ "FROM\r\n"
						+ "    cc\r\n"
						+ "LEFT JOIN de_item_issue_school ON cc.cc_id = de_item_issue_school.cc_name\r\n"
						+ "LEFT JOIN de_item_master ON de_item_issue_school.imei = de_item_master.imei\r\n"
						+ "LEFT JOIN de_transaction_master ON de_item_master.imei = de_transaction_master.imei\r\n"
						+ "GROUP BY\r\n"
						+ "    cc.cc_id,\r\n"
						+ "    cc.cc_name\r\n"
						+ "ORDER BY\r\n"
						+ "    cc.cc_id;";


			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet columnChart(String cc_name, String school_name) throws SQLException, Exception {
		// Method to get Item List report
				ResultSet rs = null;
				try {
					// String sql = "SELECT * FROM de_item_issue_school";

					// join quesry to get details from issue school, cc, school, and student tables.
					String sql = "SELECT school.school_name AS School_Name,\r\n"
							+ "COUNT(DISTINCT de_item_issue_school.imei) AS Tablet_Count,\r\n"
							+ "COUNT(DISTINCT student.student_id) AS Student_Count,\r\n"
							+ "cc.cc_id as cc_id\r\n"
							+ "FROM school\r\n"
							+ "JOIN de_item_issue_school ON school.school_id = de_item_issue_school.school_name\r\n"
							+ "JOIN student ON school.school_id = student.school_id \r\n"
							+ "JOIN cc ON school.cc_id = cc.cc_id \r\n"
							+ "WHERE de_item_issue_school.active_flag = 1 AND 1=1 \r\n";

					//yaha se
					
					 // Append school name filter if provided
		          if (school_name != null && !school_name.isEmpty()) {
		              sql += " AND school.school_id = ?";
		          }
		          
		          // Append cc name filter if provided
		          if (cc_name != null && !cc_name.isEmpty()) {
		              sql += " AND cc.cc_id = ?";
		          }
		          
		          sql += " GROUP BY school.school_name, cc.cc_id"; // Appending the Groupby Clause to execute the full query

					PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
					int parameterIndex = 1;
					
					 // Set parameters based on provided filters
		          if (school_name != null && !school_name.isEmpty()) {
		              ps.setString(parameterIndex++, school_name);
		          }
		          if (cc_name != null && !cc_name.isEmpty()) {
		              ps.setString(parameterIndex++, cc_name);
		          }
		          
		          //yaha tak
		          

					rs = ps.executeQuery();
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					if (Conn.getConnection() != null) {
						Conn.getConnection().close();
					}
				}
	}

	public ResultSet itemIssueList_ccwise(String cc_name, String school_name) throws SQLException, Exception {
		// Method to get Item List report
		ResultSet rs = null;
		try {
			// String sql = "SELECT * FROM de_item_issue_school";

			// join quesry to get details from issue school, cc, school, and student tables.
			String sql = "SELECT\r\n"
					+ "    cc.cc_id,\r\n"
					+ "    cc.cc_name, school.school_name, \r\n"
					+ "    de_item_master.brand AS brand_name,\r\n"
					+ "    COUNT(DISTINCT case when de_item_issue_school.active_flag = 1 then de_item_issue_school.imei end) AS issued_item_count,\r\n"
					+ "    COUNT(DISTINCT case when de_item_issue_school.in_repair_flag = 1 then de_item_issue_school.imei end) AS items_in_transaction_count\r\n"
					+ "FROM\r\n"
					+ "    cc\r\n"
					+ "LEFT JOIN de_item_issue_school ON cc.cc_id = de_item_issue_school.cc_name\r\n"
					+ "LEFT JOIN de_item_master ON de_item_issue_school.imei = de_item_master.imei\r\n"
					+ "LEFT JOIN de_transaction_master ON de_item_master.imei = de_transaction_master.imei\r\n"
					+ "LEFT JOIN school on de_item_issue_school.school_name = school.school_id "
					+ "WHERE 1=1 ";

			//yaha se
			
			 // Append school name filter if provided
         if (school_name != null && !school_name.isEmpty()) {
             sql += " AND school.school_id = ?";
         }
         
         // Append cc name filter if provided
         if (cc_name != null && !cc_name.isEmpty()) {
             sql += " AND cc.cc_id = ?";
         }
         
         sql += "GROUP BY cc.cc_id, cc.cc_name, de_item_master.brand, de_item_issue_school.school_name \r\n"
         		+ "	ORDER BY cc.cc_id"; // Appending the Groupby Clause to execute the full query

			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			int parameterIndex = 1;
			
			 // Set parameters based on provided filters
         if (school_name != null && !school_name.isEmpty()) {
             ps.setString(parameterIndex++, school_name);
         }
         if (cc_name != null && !cc_name.isEmpty()) {
             ps.setString(parameterIndex++, cc_name);
         }
         
         //yaha tak
			
			
			
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet barChart(String cc_name, String school_name) throws SQLException, Exception {
		// Method to get Item List report
				ResultSet rs = null;
				try {
					// String sql = "SELECT * FROM de_item_issue_school";

					// join quesry to get details from issue school, cc, school, and student tables.
					 String sql = "SELECT\r\n"
								+ "    cc.cc_id,\r\n"
								+ "    cc.cc_name,\r\n"
								+ "    COUNT(DISTINCT case when de_item_issue_school.active_flag = 1 then de_item_issue_school.imei end) AS issued_item_count,\r\n"
								+ "    COUNT(DISTINCT case when de_item_issue_school.in_repair_flag = 1 then de_item_issue_school.imei end) AS items_in_transaction_count\r\n"
								+ "FROM\r\n"
								+ "    cc\r\n"
								+ "LEFT JOIN de_item_issue_school ON cc.cc_id = de_item_issue_school.cc_name\r\n"
								+ "LEFT JOIN de_item_master ON de_item_issue_school.imei = de_item_master.imei\r\n"
								+ "LEFT JOIN de_transaction_master ON de_item_master.imei = de_transaction_master.imei\r\n"
								+ "left join school on de_item_issue_school.school_name = school.school_id "
								+ "WHERE 1=1 ";
								


					//yaha se
						
					 // Append school name filter if provided
		         if (school_name != null && !school_name.isEmpty()) {
		             sql += " AND school.school_id = ?";
		         }
		         
		         // Append cc name filter if provided
		         if (cc_name != null && !cc_name.isEmpty()) {
		             sql += " AND cc.cc_id = ?";
		         }
		         
		         sql += "GROUP BY cc.cc_id, cc.cc_name \r\n"
		         		+ "	ORDER BY cc.cc_id"; // Appending the Groupby Clause to execute the full query

					PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
					int parameterIndex = 1;
					
					 // Set parameters based on provided filters
		         if (school_name != null && !school_name.isEmpty()) {
		             ps.setString(parameterIndex++, school_name);
		         }
		         if (cc_name != null && !cc_name.isEmpty()) {
		             ps.setString(parameterIndex++, cc_name);
		         }
		         
		         //yaha tak
					
					
					rs = ps.executeQuery();
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					if (Conn.getConnection() != null) {
						Conn.getConnection().close();
					}
				}
	}

	public ResultSet brandQuantitiesIssuetoSchool_active(String cc_name, String school_name) throws SQLException, Exception {
		// Method to get Item List report
		ResultSet rs = null;
		try {
			/*
			 * String sql =
			 * "select de_item_master.brand as brand_name, count(de_item_issue_school.imei) as total_quantity \r\n"
			 * + "from de_item_issue_school\r\n" +
			 * "join de_item_master on de_item_issue_school.imei = de_item_master.IMEI\r\n"
			 * + "where de_item_issue_school.active_flag = 1\r\n" +
			 * "group by de_item_master.brand order by de_item_master.brand";
			 */
			
			String sql = " SELECT \r\n"
					+ "    m.brand AS brand_name, \r\n"
					+ "    COUNT(s.imei) + IFNULL(o.total_official_count, 0) AS total_quantity\r\n"
					+ "FROM \r\n"
					+ "    de_item_issue_school s\r\n"
					+ "    JOIN de_item_master m ON s.imei = m.IMEI\r\n"
					+ "    LEFT JOIN (\r\n"
					+ "        SELECT \r\n"
					+ "            m2.brand, COUNT(o.imei) AS total_official_count\r\n"
					+ "        FROM \r\n"
					+ "            de_item_issue_official o\r\n"
					+ "            JOIN de_item_master m2 ON o.imei = m2.IMEI\r\n"
					+ "        WHERE \r\n"
					+ "            o.active_flag = 1\r\n"
					+ "        GROUP BY \r\n"
					+ "            m2.brand\r\n"
					+ "    ) o ON m.brand = o.brand\r\n"
					+ "WHERE \r\n"
					+ "    s.active_flag = 1 and in_repair_flag is null \r\n"
					+ " AND 1=1 ";
			
			//yaha se
			
			 // Append school name filter if provided
        if (school_name != null && !school_name.isEmpty()) {
            sql += " AND s.school_name = ?";
        }
        
        // Append cc name filter if provided
        if (cc_name != null && !cc_name.isEmpty()) {
            sql += " AND s.cc_name = ?";
        }
        
        sql += "GROUP BY m.brand, o.total_official_count "
        		+ "ORDER BY m.brand"; // Appending the Groupby Clause to execute the full query

			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			int parameterIndex = 1;
			
			 // Set parameters based on provided filters
        if (school_name != null && !school_name.isEmpty()) {
            ps.setString(parameterIndex++, school_name);
        }
        if (cc_name != null && !cc_name.isEmpty()) {
            ps.setString(parameterIndex++, cc_name);
        }
        
        //yaha tak
			
			
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet itemList_available() throws SQLException, Exception {
		// Method to get Item List report
		ResultSet rs = null;
		try {
			//String sql = "SELECT count(*) FROM de_item_master where current_status= 'WORKING'";
			String sql = "SELECT * FROM de_item_master where issue_flag=0";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet brandQuantitiesInRepair_ccWise(String cc_name, String school_name) throws SQLException, Exception {
		// Method to get Item List report
				ResultSet rs = null;
				try {
					String sql = "SELECT \r\n"
							+ "    de_item_master.brand AS brand_name, \r\n"
							+ "    COUNT(de_item_issue_school.imei) AS total_quantity\r\n"
							+ "FROM \r\n"
							+ "    de_item_issue_school\r\n"
							+ "JOIN \r\n"
							+ "    de_item_master ON de_item_issue_school.imei = de_item_master.IMEI\r\n"
							+ "JOIN \r\n"
							+ "    school ON de_item_issue_school.school_name = school.school_id\r\n"
							+ "JOIN \r\n"
							+ "    cc ON de_item_issue_school.cc_name = cc.cc_id\r\n"
							+ "WHERE \r\n"
							+ "    de_item_issue_school.in_repair_flag = 1 "
							+ " AND 1=1 ";
					
					//yaha se
					
					 // Append school name filter if provided
		        if (school_name != null && !school_name.isEmpty()) {
		            sql += " AND school.school_id = ?";
		        }
		        
		        // Append cc name filter if provided
		        if (cc_name != null && !cc_name.isEmpty()) {
		            sql += " AND cc.cc_id = ?";
		        }
		        
		        sql += "GROUP BY de_item_master.brand \r\n"
		        		+ "ORDER BY de_item_master.brand"; // Appending the Groupby Clause to execute the full query

					PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
					int parameterIndex = 1;
					
					 // Set parameters based on provided filters
		        if (school_name != null && !school_name.isEmpty()) {
		            ps.setString(parameterIndex++, school_name);
		        }
		        if (cc_name != null && !cc_name.isEmpty()) {
		            ps.setString(parameterIndex++, cc_name);
		        }
		        
		        //yaha tak

					rs = ps.executeQuery();
					return rs;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					if (Conn.getConnection() != null) {
						Conn.getConnection().close();
					}
				}
	}
}

/*
 * public List<String> searchItems(String keyword) { List<String> items = new
 * ArrayList<>(); try (Connection con =
 * DriverManager.getConnection("jdbc:mysql://localhost:3306/deinventory",
 * "deinventory", "Amti@123$"); PreparedStatement ps =
 * con.prepareStatement("SELECT IMEI FROM de_item_master WHERE IMEI LIKE ?")) {
 * 
 * ps.setString(1, "%" + keyword + "%"); try (ResultSet rs = ps.executeQuery())
 * { while (rs.next()) { items.add(rs.getString("IMEI")); } } } catch
 * (SQLException e) { e.printStackTrace(); } return items; }
 */
