<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login page</h1>

<h4 style="color: red"> ${errorMes}</h4>

<form method="post" action="${pageContext.request.contextPath}/login">
    Enter your login: <input type="text" name="login"><br/>
    <br/>
    Enter your password: <input type="password" name="password"><br/>
    <br/>
    <button type="submit">Login</button>
</form>
<a href="${pageContext.request.contextPath}/registration">Register new user </a><br/>
</body>
</html>
