<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    


<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>ehdokkaat-sivu vaalikone ryhma 6</title>

  <%@include file="templates/imports.jsp" %>
</head>

<body>

<%@include file="templates/navbar.jsp" %>

<div class="jumbotron text-center">
  <h2>
  
  Results

  </h2>
  <p>Tulokset</p> 
</div>

<div class="container">
  <h3></h3>
  <div class="panel panel-default">
    <div class="panel-body">
	<c:forEach var="ehdokas" items="" >
		<h4><h4>
</c:forEach>
    </div>
  </div>
</div>

</body>
</html>