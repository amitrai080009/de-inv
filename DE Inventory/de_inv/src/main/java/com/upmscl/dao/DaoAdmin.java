package com.upmscl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoAdmin {

	GetConnection Conn = new GetConnection();

	public ResultSet report() throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM tender order by id desc";
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

	// Method exactly similar to the method above but may be that can be used
	// somewhere else so i did not deleted that method
	public ResultSet treport() throws SQLException, Exception {
		ResultSet t_rs = null;
		try {
			String sql = "SELECT * FROM tender order by id desc";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			t_rs = ps.executeQuery();
			return t_rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	// method for fetch saved Tender Data
	public ResultSet treportall() throws SQLException, Exception {
		ResultSet t_rs = null;
		try {
			/* String sql = "SELECT * FROM tender order by id desc"; */
			String sql = "SELECT * FROM tender order by amend_date desc";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			t_rs = ps.executeQuery();
			return t_rs;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet creport() throws SQLException, Exception {
		ResultSet c_rs = null;
		try {
			String sql = "SELECT * FROM circular order by id desc";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			c_rs = ps.executeQuery();
			return c_rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet nsqreportall() throws SQLException, Exception {
		ResultSet nsq_rs = null;
		try {
			/* String sql = "SELECT * FROM tender order by id desc"; */
			// String sql = "SELECT * FROM nsq_drugs order by nsq_date desc where
			// idnsq_drugs != 18";
			String sql = "SELECT * FROM nsq_drugs where idnsq_drugs ";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			nsq_rs = ps.executeQuery();
			return nsq_rs;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	// method for fetch saved Tender Data
	public ResultSet treportcovid() throws SQLException, Exception {
		ResultSet covid_rs;
		try {
			/* String sql = "SELECT * FROM tender order by id desc"; */
			String sql = "SELECT * FROM tender where tender_no like '%covid%' order by amend_date desc";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			covid_rs = ps.executeQuery();
			return covid_rs;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet treportdrug() throws SQLException, Exception {
		ResultSet t_rs;
		try {
			/* String sql = "SELECT * FROM tender order by id desc"; */
			String sql = "SELECT * FROM tender where tender_cat = 'Drugs' order by amend_date desc";
			// String sql = "SELECT * FROM upmscl_it_db2.tender where tender_cat = 'Drugs'
			// order by amend_date desc";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			t_rs = ps.executeQuery();
			return t_rs;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	// method for fetch saved Tender Data
	public ResultSet treportequip() throws SQLException, Exception {
		ResultSet t_rs;
		try {
			/* String sql = "SELECT * FROM tender order by id desc"; */
			String sql = "SELECT * FROM tender where tender_cat = 'Equipment' order by amend_date desc";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			t_rs = ps.executeQuery();
			return t_rs;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet reportAmend() throws SQLException, Exception {
		ResultSet t_rs;
		try {
			/* String sql = "SELECT * FROM tender order by id desc"; */
			String sql = "SELECT * FROM tender_amend order by amend_date desc";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			t_rs = ps.executeQuery();
			return t_rs;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet vacancyReport() throws SQLException, Exception {
		ResultSet t_rs;
		try {
			/* String sql = "SELECT * FROM tender order by id desc"; */
			String sql = "SELECT * FROM vacancy_upmscl order by update_date desc";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			t_rs = ps.executeQuery();
			return t_rs;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (Conn.getConnection() != null) {
				Conn.getConnection().close();
			}
		}
	}

	public ResultSet tenderCount() throws SQLException, Exception {

		ResultSet t_count;
		try {
			/* String sql = "SELECT * FROM tender order by id desc"; */
			String sql = "SELECT count(id) as tc FROM tender;";
			PreparedStatement ps = Conn.getConnection().prepareStatement(sql);
			t_count = ps.executeQuery();
			return t_count;

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
