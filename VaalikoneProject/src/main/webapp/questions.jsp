<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

 <%@ page import="java.util.ArrayList" %>  
 <%@ page import="Models.Question" %>   

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Vaalikone - Questions</title>
	<%@include file="templates/imports.jsp" %>
</head>
<body>
<%@include file="templates/navbar.jsp" %>

<div class="jumbotron text-center">
  <h2>Vastaa kysymyksiin</h2>
  <p>Kysymysten jälkeen saat tietää parhaan ehdokkaan</p> 
</div>

<form name="questionForm" method="post" action="answers">

<c:forEach var="question" items="${requestScope.Questionlist}">

<div class="container">
  <h4>Kysymys ${question.questionID }:</h4>
  <div class="panel panel-default">
  
    <div class="panel-body">
    ${question.question}
    </div>
    
    <div class="form-check form-check-inline">
    	<input class="form-check-input" type="radio" name="${question.questionID }" value=1>
    	<label class="form-check-label" for="inlineRadio1">1</label>
    	<input class="form-check-input" type="radio" name="${question.questionID }" value=2>
    	<label class="form-check-label" for="inlineRadio2">2</label>
    	<input class="form-check-input" type="radio" name="${question.questionID }" value=3>
    	<label class="form-check-label" for="inlineRadio3">3</label>
    	<input class="form-check-input" type="radio" name="${question.questionID }" value=4>
    	<label class="form-check-label" for="inlineRadio4">4</label>
    	<input class="form-check-input" type="radio" name="${question.questionID }" value=5>
    	<label class="form-check-label" for="inlineRadio5">5</label>
    </div>
  </div>
</div>

</c:forEach>

<div class="container">
<div class="panel-body">
<input type ="submit" value="Result" />
</div>
</div>

</form>


</html>