<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Main</title>
</head>

<header>

<nav class="navbar navbar-inverse">
  <ul class="nav navbar-nav">
    <li><a href="#">Etusivu</a></li>
    <li><a href="#">Selaa ehdokkaita</a></li>
    <li><a href="#">Selaa puolueita</a></li>
  </ul>
</nav>

</header>
<body>
<h2>Test</h2>
<div class="col-md-12 school-options-dropdown text-center">
    <div class="dropdown btn-group">
      <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Valitse Kunta
        <span class="caret"></span>
      </button>
      <ul class="dropdown-menu scrollable-menu" role="menu">
		  <c:forEach var="kunta" items="${requestScope.kuntaList}">
		  <li>${kunta.Nimi}</li>
		  </c:forEach>	  
       </ul>
    </div>
</div>
</body>
</html>