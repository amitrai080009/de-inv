// This File is deprecated and not being used in any file/project


package com.de.jfreecharts;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.servlet.ServletContext;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PieChartAction extends ActionSupport {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultPieDataset dataset;

    public String execute() {
        dataset = createDataset();
        return SUCCESS;
    }

    public DefaultPieDataset getDataset() {
        return dataset;
    }

    public String generatePieChart() {
        dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createPieChart(
                "",  // chart title --Category Count Distribution
                dataset,                        // data
                true,                           // include legend
                true,
                false
        );

        // Customizing pie chart
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionOutlinesVisible(false);
        plot.setLabelBackgroundPaint(Color.white);
        plot.setLabelOutlinePaint(null);
        plot.setLabelShadowPaint(null);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));
        
     // Set background color
        plot.setBackgroundPaint(Color.white);
        
       //set color of the sections of pie chart
        plot.setSectionPaint("IKALL", Color.decode("#007ea7"));
        plot.setSectionPaint("IRA", Color.decode("#FF9EAA"));
        
        //plot.setSectionPaint("Category 3", Color.GREEN);
        // Add more categories/colors as needed
        
        //??????????????????
        
        // Remove border
        plot.setOutlineVisible(false);


        // Save chart as PNG
        try {
            // Get the servlet context real path (base path of the web application)
            ServletContext servletContext = ServletActionContext.getServletContext();
            String basePath = servletContext.getRealPath("/");

            // Ensure directory exists
            File chartDir = new File(basePath, "Jfreecharts");
            if (!chartDir.exists()) {
                boolean created = chartDir.mkdirs(); // Attempt to create all necessary directories
                if (!created) {
                    // Handle the case where directories could not be created
                    System.out.println("Failed to create directories: " + chartDir.getAbsolutePath());
                    return ERROR;
                }
            }

            // Save chart as PNG
            File chartFile = new File(chartDir, "pieChart.png");
            ChartUtilities.saveChartAsPNG(chartFile, chart, 350, 350);
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }

    private DefaultPieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Replace with your database connection details and query
        String url = "jdbc:mysql://localhost:3306/deinventory";
        String username = "deinventory";
        String password = "Amti@123$";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT brand,quantity FROM deinventory.de_stock_master;")) {
            while (rs.next()) {
                String categoryName = rs.getString("brand");
                int count = rs.getInt("quantity");

                // Adding values to dataset
                dataset.setValue(categoryName, count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataset;
    }
}
