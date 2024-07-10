// This File is deprecated and not being used in any file/project


package com.de.jfreecharts;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import java.awt.Color;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CcWiseLaptopDistributionAction extends ActionSupport {
	
	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private DefaultCategoryDataset dataset;

	    public String execute() {
	        dataset = createDataset();
	        return SUCCESS;
	    }

	    public DefaultCategoryDataset getDataset() {
	        return dataset;
	    }

	    @SuppressWarnings("deprecation")
		public String generateBarChart() {
	        dataset = createDataset();

	        JFreeChart chart = ChartFactory.createBarChart(
	                "",  // chart title
	                "Cluster-Coordinator",                           // domain axis label
	                "Number of Tablets",                // range axis label
	                dataset,                            // data
	                org.jfree.chart.plot.PlotOrientation.HORIZONTAL,           // orientation
	                true,                               // include legend
	                true,                               // tooltips
	                false                               // urls
	        );
	        
	  //Changing the color of the bars
	     // Get plot (where data is visualized)
	        CategoryPlot plot = (CategoryPlot) chart.getPlot();

	        // Customizing the bar renderer to set different colors for each bar
	        BarRenderer renderer = new BarRenderer();
	        renderer.setDrawBarOutline(false); // Remove bar outlines for cleaner look
	        renderer.setMaximumBarWidth(1.0); // Adjust the width of the bars if needed
	       

	        // Set custom colors for each bar
	       //// Color studentColor = Color.decode("#099427"); // Blue color for student count
	       // Color laptopColor = Color.decode("#2ca1f5");  // Green color for laptop count
	        
			/*
			 * renderer.setSeriesPaint(0, Color.red); renderer.setSeriesPaint(1,
			 * Color.blue);
			 */
	        renderer.setSeriesPaint(0, Color.decode("#007ea7"));
	        renderer.setSeriesPaint(1, Color.decode("#FF9EAA"));
	        //renderer.setSeriesPaint(2, Color.red);
	        // Disable gradient effect (light focus)
	        renderer.setBarPainter(new org.jfree.chart.renderer.category.StandardBarPainter());
	        // Adjusting space between bars
	        renderer.setItemMargin(0); // Remove space between bars
	        // Add more colors as needed for additional series
	        
	        // Enable item labels
	        renderer.setBaseItemLabelGenerator(new org.jfree.chart.labels.StandardCategoryItemLabelGenerator());
	        renderer.setBaseItemLabelsVisible(true);
	        
	     // Position item labels on the right side of the bars
	        renderer.setPositiveItemLabelPosition(new ItemLabelPosition(
	                ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER_LEFT));
	        
	     // Set background color
	        plot.setBackgroundPaint(Color.white);
	        
	        // Remove border
	        plot.setOutlineVisible(false);

	        // Assign custom renderer to the plot
	        plot.setRenderer(renderer);
	      //Changing the color of the bars
	        
	        // Adjusting CategoryAxis to wrap long labels
	        CategoryAxis domainAxis = plot.getDomainAxis();
	        domainAxis.setMaximumCategoryLabelWidthRatio(1f); // Adjust the ratio as needed

	        
	        try {
	        	String filePath = ServletActionContext.getServletContext().getRealPath("/").concat("Jfreecharts/");
	        	File chartFile = new File(filePath, "barChart.png");
	        	
	            //File chartFile = new File("columnChart.png");
	            ChartUtilities.saveChartAsPNG(chartFile, chart, 550, 350);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return SUCCESS;
	    }

	    private DefaultCategoryDataset createDataset() {
	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        
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

	        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/deinventory", "deinventory", "Amti@123$");
	             Statement stmt = conn.createStatement();	        		
	             ResultSet rs = stmt.executeQuery(sql)) {
	        	
	            while (rs.next()) {
	                String ccName = rs.getString("cc_name");
	                int issueditemCount = rs.getInt("issued_item_count");
	                int itemInTransactionCount = rs.getInt("items_in_transaction_count");
	                
	                //dataset.addValue(laptopCount, "Tablets Distribution School-wise", schoolName);
	                // Adding values to dataset
	                if(issueditemCount > 0) //added manually amit rai
						{
	                dataset.addValue(issueditemCount, "Student Count", ccName);
	                dataset.addValue(itemInTransactionCount, "Tablet Count", ccName);
						}
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return dataset;
	    }

	    // Database connection parameters, getters/setters
	}


