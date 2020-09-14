<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<h1>Hello world!</h1>

<a href="${pageContext.request.contextPath}/inject-data">Inject test data to DB </a><br/>
<a href="${pageContext.request.contextPath}/users/all">All users </a><br/>
<a href="${pageContext.request.contextPath}/registration">Register new user </a><br/>
<a href="${pageContext.request.contextPath}/products/add">Add new product </a><br/>
<a href="${pageContext.request.contextPath}/products/all">All products </a><br/>
<a href="${pageContext.request.contextPath}/products/manage">All products for Admin</a><br/>
<a href="${pageContext.request.contextPath}/user/orders">Order </a><br/>
<a href="${pageContext.request.contextPath}/orders">Order for Admin</a><br/>

</body>
</html>
