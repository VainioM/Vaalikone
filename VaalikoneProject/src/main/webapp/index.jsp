<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>

    <%@include file="templates/imports.jsp" %>
</head>
<body>

<%@include file="templates/navbar.jsp" %>


<% if(request.getAttribute("alert") != null) { %>
	<%@ include file="templates/alerts.jsp" %>
<% } %>

<div class="container">

<h1>Vaalikone</h1>
<br/>

<form action="add_cookies" method="post">
  <select name="kunta">
  	<c:forEach var="_kunta" items="${requestScope.kuntaList}">
	<option>${_kunta.nimi}</option>
	</c:forEach>
  </select>
  <input type="submit" value="Submit">
</form>
</div>

</body>
</html>