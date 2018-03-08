<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2/22/18
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find Employee</title>
</head>
<body>

<%@ include file="links.jsp" %>

<h2>Find Employee</h2>
<form action="manage/Employee" method="get">
    Employee ID:<input type="text" name="empid">
    <input type="submit" value="find">
</form>
</body>
</html>
