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
  	Ehdokkaat
  </h2>
</div>
<div class="container">

<c:forEach var="ehdokas" items="${Ehdokaslist}">
<div class="panel panel-default">
  <div class="panel-body">
	<div class="page-header">
	  <h1><a href="/ehdokas-sivu/<c:out value="${ehdokas.getId()}"></c:out>">
	  <c:out value="${ehdokas.getEtunimi()} ${ehdokas.getSukunimi()}"></c:out>
	  </a> <small><c:out value="${ehdokas.getPuolue()}"></c:out></small></h1>
	</div>
	<div>
	Lisätietoja
	</div>
  </div>
</div>
<br>
</c:forEach>

</div>
</body>
</html>