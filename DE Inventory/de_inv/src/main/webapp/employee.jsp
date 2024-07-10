<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Fetch Employee Data</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
    <h2>Enter Employee ID:</h2>
    <input type="text" id=empId>
    <button onclick="fetchData()">Fetch Data</button>
    <br><br>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Salary</th>
            </tr>
        </thead>
        <tbody id="employeeData">
        </tbody>
    </table>

<script>
    function fetchData() {
        var empId = $("#empId").val();
        $.ajax({
            url: "fetchData.action",
            type: "POST",
            data: {id: empId},
            success: function(data) {
                console.log("Response:", data); // Log the response object
                if (data && data.success) {
                    var employee = data.employee;
                    if (employee) {
                        var html = "<tr><td>" + employee.id + "</td><td>" + employee.name + "</td><td>" + employee.salary + "</td></tr>";
                        $("#employeeData").html(html);
                    } else {
                        console.error("Employee data not found in response.");
                    }
                } else {
                    console.error("Error response:", data);
                    alert("No employee found with ID: " + empId);
                }
            },
            error: function(xhr, status, error) {
                console.error("AJAX error:", error);
                alert("An error occurred while fetching data.");
            }
        });
    }
</script>

    

</body>
</html>
