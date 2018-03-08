<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2/22/18
  Time: 2:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Employee Info</title>
</head>

<body>

<%@ include file="links.jsp" %>


<h1>Employee</h1>
<p>
    Info for: ${requestScope['employee'].ID} <BR>
    name: ${requestScope['employee'].firstname} ${requestScope['employee'].lastname}<BR>
    role: ${requestScope['employee'].role}<BR>
    store: <a href="Store?id=${requestScope['employee'].store}">${requestScope['employee'].store}</a> <BR>
</p>


</body>
</html>
