<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User's order</title>
</head>
<body>
<h1>All user's orders</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Products</th>
    </tr>
<c:forEach var="order" items="${orders}">
    <tr>
        <td>
            <c:out value="${order.id}"/>
        </td>
        <td>
            <c:out value="${order.products}"/>
        </td>
    </tr>
</c:forEach>
</table>
<a href="${pageContext.request.contextPath}/">Home</a>
</body>
</html>
