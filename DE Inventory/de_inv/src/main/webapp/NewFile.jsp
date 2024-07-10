<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <title>Bulk Insert Form</title>
</head>
<body>
    <h2>Bulk Insert Users</h2>
    <s:form action="bulkInsert" method="post">
        <s:iterator value="users" status="status">
            <s:textfield label="Name" name="users[%{#status.index}].name"/>
            <s:textfield label="Email" name="users[%{#status.index}].email"/>
        </s:iterator>
        <s:submit value="Insert Users"/>
    </s:form>
</body>
</html>
