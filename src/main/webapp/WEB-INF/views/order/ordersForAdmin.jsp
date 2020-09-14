<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin's Order</title>
</head>
<body>
<h1>Order for Admin</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>User ID</th>
        <th>Details</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.id}"/>
            </td>
            <td>
                <c:out value="${order.userId}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/orders/details?id=${order.id}">Details</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/orders/delete?id=${order.id}">Delete</a>
            </td>

        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/">Home</a>
</body>
</html>
