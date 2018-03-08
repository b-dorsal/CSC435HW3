<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2/22/18
  Time: 2:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>

<%@ include file="links.jsp" %>

    <h1>Profile</h1>
    User: ${requestScope['user'].getUsername()} <BR>
    Email: ${requestScope['user'].getEmail()} <BR>
    Employee ID: <a href="Employee?empid=${requestScope['user'].getEmployeeID()}">${requestScope['user'].getEmployeeID()}</a> <BR>
</body>
</html>
