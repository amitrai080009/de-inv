package testing;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.*;

public class EmployeeAction extends ActionSupport {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5470242534812846887L;
	private int id;
    private String name;
    private double salary;

    // Getters and Setters

    public String fetchData() {
        try {
            // Connect to your MySQL database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/deinventory", "deinventory", "Amti@123$");

            // Execute query to fetch data based on provided ID
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM employees WHERE id = ?");
            stmt.setInt(1, id);	
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Set fetched data to properties
                setName(rs.getString("name"));
                setSalary(rs.getDouble("salary"));
            } else {
                // If no data found for the given ID
                addActionError("No employee found with ID: " + id);
                return ERROR;
            }

            // Close resources
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            //addActionError("An error occurred while fetching employee data.");
            return ERROR;
        }
        return SUCCESS;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
