<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search</title>
</head>
<body>
    <h1>Search</h1>
   <%--  <s:form action="fetchRecord">
        <s:textfield label="Enter ID" name="id"/>
        <s:submit value="Search"/>
    </s:form> --%>
    
    <form action="fetchRecord.action" method="post">
    <input type="text" name="id">
    <input type="submit" value="find record">
    
    </form>

    <s:if test="record != null">
        <p>Name: <s:property value="record.name"/></p>
        <p>Age: <s:property value="record.age"/></p>
    </s:if>
</body>
</html>
