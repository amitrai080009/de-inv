// This File is deprecated and not being used in any file/project

package com.de.jfreecharts;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.Color;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LaptopDistributionAction extends ActionSupport {
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

    public String generateColumnChart() {
        dataset = createDataset();
        
        JFreeChart chart = ChartFactory.createBarChart(
                "",  // chart title
                "School",                           // domain axis label
                "Number of Tablets",                // range axis label
                dataset,                            // data
                org.jfree.chart.plot.PlotOrientation.VERTICAL,           // orientation
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
        renderer.setMaximumBarWidth(0.1); // Adjust the width of the bars if needed

        // Set custom colors for each bar
       //// Color studentColor = Color.decode("#099427"); // Blue color for student count
       // Color laptopColor = Color.decode("#2ca1f5");  // Green color for laptop count
        
        renderer.setSeriesPaint(0, Color.decode("#007ea7"));
        renderer.setSeriesPaint(1, Color.decode("#FF9EAA"));
        //renderer.setSeriesPaint(2, Color.red);
        
       // renderer.setShadowVisible(false); //new added for removing shadow from the bars
     // Disable gradient effect (light focus)
        renderer.setBarPainter(new org.jfree.chart.renderer.category.StandardBarPainter());

        
        // Adjusting space between bars
        renderer.setItemMargin(0); // Remove space between bars
        // Add more colors as needed for additional series
        
        // Enable item labels
        renderer.setBaseItemLabelGenerator(new org.jfree.chart.labels.StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        
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
        	File chartFile = new File(filePath, "columnChart.png");
        	
            //File chartFile = new File("columnChart.png");
            ChartUtilities.saveChartAsPNG(chartFile, chart, 800, 350);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/deinventory", "deinventory", "Amti@123$");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT \r\n"
             		+ "    school.school_name AS School_Name,\r\n"
             		+ "    COUNT(DISTINCT de_item_issue_school.imei) AS Tablet_Count,\r\n"
             		+ "    COUNT(DISTINCT student.student_id) AS Student_Count\r\n"
             		+ "FROM \r\n"
             		+ "    school\r\n"
             		+ "LEFT JOIN \r\n"
             		+ "    de_item_issue_school ON school.school_id = de_item_issue_school.school_name\r\n"
             		+ "LEFT JOIN \r\n"
             		+ "    student ON school.school_id = student.school_id\r\n"
             		+ "WHERE \r\n"
             		+ "    de_item_issue_school.active_flag = 1\r\n"
             		+ "GROUP BY \r\n"
             		+ "    school.school_name;")) {
        	
            while (rs.next()) {
                String schoolName = rs.getString("School_Name");
                int laptopCount = rs.getInt("Tablet_Count");
                int studentCount = rs.getInt("Student_Count");
                
                //dataset.addValue(laptopCount, "Tablets Distribution School-wise", schoolName);
                // Adding values to dataset
                dataset.addValue(studentCount, "Student Count", schoolName);
                dataset.addValue(laptopCount, "Tablet Count", schoolName);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataset;
    }

    // Database connection parameters, getters/setters
}
