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
    <title>Find Product</title>
</head>
<body>

<%@ include file="links.jsp" %>

<h2>Find Store</h2>
<form action="manage/Store" method="get">
    SKU:<input type="text" name="id">
    <input type="submit" value="find">
</form>
</body>
</html>
