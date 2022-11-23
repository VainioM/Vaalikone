<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vaalikone - Login</title>

    <%@include file="templates/imports.jsp" %>
</head>
<body>

<%@include file="templates/navbar.jsp" %>

<% if(request.getAttribute("alert") != null) { %>
	<%@ include file="templates/alerts.jsp" %>
<% } %>

<div class="container">

<form action="/login" method="post">
    <p>Username</p><br>
    <input type="text" name="username">
    <p>Password</p><br>
    <input type="password" name="password">
    <br>
    <input type="submit" value="Login">
</form>

</div>
</body>
</html>
