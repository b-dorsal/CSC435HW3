<%@ page import="retailservice.model.Sale" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2/22/18
  Time: 2:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Receipt Info</title>
</head>
<body>
<%@ include file="links.jsp" %>

<h1>Receipt</h1>

    <%
        Object listobj = session.getAttribute("receipts");
        List<Sale> receiptList;
        if(listobj != null){
            int count = 0;
            receiptList = (ArrayList<Sale>)listobj;
            int receiptID = receiptList.get(0).getId();
            %> receipt ID = <%=receiptID%> <table><tr><td>sku</td><td>price</td><td>count</td></tr><%
            for(Sale p : receiptList){
                count += p.getCount();
    %>
    <tr><td><%=p.getSku() %></td><td> <%=p.getPrice() %></td> <td><%=p.getCount()%></td></tr>
    <% } %>
    <tr><td></td><td>count</td> <td><%=count %></td></tr>
    <% } %>
</table>


</body>
</html>
