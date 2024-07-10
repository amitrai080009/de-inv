package com.de.chartjs;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.servlet.ServletContext;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LaptopDistributionAction extends ActionSupport {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Map<String, Object>> dataset;

    public String execute() {
        dataset = retrieveData();
        return SUCCESS;
    }

    public List<Map<String, Object>> getDataset() {
        return dataset;
    }

    private List<Map<String, Object>> retrieveData() {
        List<Map<String, Object>> data = new ArrayList<>();

        // Replace with your database connection details and query
        String url = "jdbc:mysql://localhost:3306/deinventory";
        String username = "deinventory";
        String password = "Amti@123$";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT \\r\\n\"\r\n"
             		+ "             		+ \"    school.school_name AS School_Name,\\r\\n\"\r\n"
             		+ "             		+ \"    COUNT(DISTINCT de_item_issue_school.imei) AS Tablet_Count,\\r\\n\"\r\n"
             		+ "             		+ \"    COUNT(DISTINCT student.student_id) AS Student_Count\\r\\n\"\r\n"
             		+ "             		+ \"FROM \\r\\n\"\r\n"
             		+ "             		+ \"    school\\r\\n\"\r\n"
             		+ "             		+ \"LEFT JOIN \\r\\n\"\r\n"
             		+ "             		+ \"    de_item_issue_school ON school.school_id = de_item_issue_school.school_name\\r\\n\"\r\n"
             		+ "             		+ \"LEFT JOIN \\r\\n\"\r\n"
             		+ "             		+ \"    student ON school.school_id = student.school_id\\r\\n\"\r\n"
             		+ "             		+ \"WHERE \\r\\n\"\r\n"
             		+ "             		+ \"    de_item_issue_school.active_flag = 1\\r\\n\"\r\n"
             		+ "             		+ \"GROUP BY \\r\\n\"\r\n"
             		+ "             		+ \"    school.school_name;")) {
        	
            while (rs.next()) {
                String schoolName = rs.getString("School_Name");
                int studentCount = rs.getInt("Student_Count");
                int laptopCount = rs.getInt("Tablet_Count");

                Map<String, Object> entry = new HashMap<>();
                entry.put("schoolName", schoolName);
                entry.put("studentCount", studentCount);
                entry.put("laptopCount", laptopCount);
                data.add(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
}
