<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new product</title>
</head>
<body>

<form method="post" action="${pageContext.request.contextPath}/products/add">
    Product name: <input type="text" name="name">
    Price: <input type="text" name="price">

    <button type="submit">Add</button>
</form>
<a href="${pageContext.request.contextPath}/">Home</a>
</body>
</html>
