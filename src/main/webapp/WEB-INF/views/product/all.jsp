<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All products</title>
</head>
<body>
<h1>All products our shop</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Add to Cart</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>
                <c:out value="${product.id}"/>
            </td>
            <td>
                <c:out value="${product.name}"/>
            </td>
            <td>
                <c:out value="${product.price}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/shopping-cart/add?id=${product.id}">Add to Cart</a>
            </td>
        </tr>

    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/">Home</a>
<a href="${pageContext.request.contextPath}/products/add">Add new product</a>
<a href="${pageContext.request.contextPath}/shopping-cart/cart">Cart</a>
</body>
</html>
