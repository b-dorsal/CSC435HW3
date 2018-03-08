<%@ page import="java.util.List" %>
<%@ page import="retailservice.model.Product" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 3/8/18
  Time: 3:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Checkout</title>
</head>
<body>

<%@ include file="links.jsp" %>
<BR>

    <H2>Checkout</H2>
    <form action="Checkout" method="get">
        SKU:<input type="text" name="sku">
        <input type="submit" value="add">
    </form>
sale id: <%=session.getAttribute("saleid")%>
<table>

        <%
Object listobj = session.getAttribute("checkoutList");
List<Product> checkoutList;
if(listobj != null){
    checkoutList = (ArrayList<Product>)listobj;
    float total = 0.0f;
    for(Product p : checkoutList){
%>
        <tr><td><%=p.getSku() %></td><td><%=p.getName() %></td><td> <%=p.getPrice() %></td></tr>
                <% total += p.getPrice(); %>
                <% } %>
            <tr><td></td><td>Total</td> <td><%=total %></td></tr>
                <% } %>
</table>
            <form action="Checkout" method="post">
                <input type="submit" value="Checkout">
            </form>

</body>
</html>