<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Hello! Enter your login and password, please!</h1>

<h4 style="color: red"> ${message}</h4>

<form method="post" action="${pageContext.request.contextPath}/registration">
    Enter your name: <input type="text" name="name"><br/>
    <br/>
    Enter your login: <input type="text" name="login"><br/>
    <br/>
    Enter your password: <input type="password" name="password"><br/>
    <br/>
    Repeat your password: <input type="password" name="passwordRepeat"><br/>
    <br/>

    <button type="submit">Registry</button>
</form>
<a href="${pageContext.request.contextPath}/">Home</a>
</body>
</html>
