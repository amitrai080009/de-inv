<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>School-wise Student and Laptop Distribution</title>
    <!-- Include Chart.js -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <div style="width: 80%; margin: auto;">
        <canvas id="myChart" width="600" height="400"></canvas>
    </div>

    <script>
        // Retrieve data from Struts2 action
        var data = {
            labels: [<s:iterator value="dataset" status="rowStatus"><s:property value="schoolName" /><s:if test="#rowStatus.hasNext()">,</s:if></s:iterator>],
            datasets: [{
                label: 'Student Count',
                data: [<s:iterator value="dataset"><s:property value="studentCount" /><s:if test="#status.hasNext()">,</s:if></s:iterator>],
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }, {
                label: 'Laptop Count',
                data: [<s:iterator value="dataset"><s:property value="laptopCount" /><s:if test="#status.hasNext()">,</s:if></s:iterator>],
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1
            }]
        };

        // Chart.js options
        var options = {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        };

        // Create chart using Chart.js
        var ctx = document.getElementById('myChart').getContext('2d');
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: data,
            options: options
        });
    </script>
</body>
</html>
