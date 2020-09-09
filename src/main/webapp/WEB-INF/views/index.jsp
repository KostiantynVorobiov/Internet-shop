<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello world!</h1>

<a href="${pageContext.request.contextPath}/users/all">All users</a>
<a href="${pageContext.request.contextPath}/injectData">Inject test data to DB</a>
<a href="${pageContext.request.contextPath}/registration">Register new user</a>
<a href="${pageContext.request.contextPath}/products/add">Add new product</a>
<a href="${pageContext.request.contextPath}/products/all">All products</a>

</body>
</html>
