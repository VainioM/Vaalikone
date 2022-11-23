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
  	<c:out value='${ehdokas.getEtunimi()} ${ehdokas.getSukunimi()}'/>
  </h2>
  <p>Ehdokkaan tai puolueen iskulause tms. tähän</p> 
</div>

<div class="container">
  <c:choose>
  <c:when test="${owner == true}"><button class="button"><a href="?edit">Muokkaa tietoja</a></button></c:when>
  </c:choose>
  <c:choose>
  <c:when test="${edit == true}">
  <form action="" method="post">
  <h3>Etunimi</h3>
  <div class="panel panel-default">
    <div class="panel-body">
	    <c:choose>
		<c:when test="${edit == true}"><input type="text" name="etunimi" value="<c:out value='${ehdokas.getEtunimi()}'/>"></c:when>
		</c:choose>
    </div>
  </div>
  <h3>Sukunimi</h3>
  <div class="panel panel-default">
    <div class="panel-body">
	    <c:choose>
		<c:when test="${edit == true}"><input type="text" name="sukunimi" value="<c:out value='${ehdokas.getSukunimi()}'/>"></c:when>
		</c:choose>
    </div>
  </div>
  </c:when>
  </c:choose>
  
  <h3>Puolue</h3>
  <div class="panel panel-default">
    <div class="panel-body">
	    <c:choose>
		<c:when test="${edit == true}"><input type="text" name="puolue" value="<c:out value='${ehdokas.getPuolue()}'/>"></c:when>
		<c:otherwise><c:out value='${ehdokas.getPuolue()}'/></c:otherwise>
		</c:choose>
    </div>
  </div>
  <h3>Paikkakunta</h3>
  <div class="panel panel-default">
    <div class="panel-body">
 		<c:choose>
		<c:when test="${edit == true}"><input type="text" name="paikkakunta" value="<c:out value='${ehdokas.getPaikkakunta()}'/>"></c:when>
		<c:otherwise><c:out value='${ehdokas.getPaikkakunta()}'/></c:otherwise>
		</c:choose>
    </div>
  </div>
  <h3>Ikä</h3>
  <div class="panel panel-default">
    <div class="panel-body">
	    <c:choose>
		<c:when test="${edit == true}"><input type="number" name="ika" value="<c:out value='${ehdokas.getIka()}'/>"></c:when>
		<c:otherwise><c:out value='${ehdokas.getIka()}'/></c:otherwise>
		</c:choose>
    </div>
  </div>
  <h3>Ammatti</h3>
  <div class="panel panel-default">
    <div class="panel-body">
	    <c:choose>
		<c:when test="${edit == true}"><input type="text" name="ammatti" value="<c:out value='${ehdokas.getPuolue()}'/>"></c:when>
		<c:otherwise><c:out value='${ehdokas.getAmmatti()}'/></c:otherwise>
		</c:choose>
    </div>
  </div>
  <c:choose>
  <c:when test="${edit == true}">
  <input type="submit" class="button" value="Tallenna">
  </form>
  </c:when>
  </c:choose>
</div>

<br>
<br>

<div class="container">
	<h2>Ehdokkaan vastaukset</h2>
	</div>

<div class="container">
  <div class="panel-group">
    <div class="panel panel-default">
      <div class="panel-heading">DB.kysymykset.kysymys_ID tähän, DB.kysymykset.kysymys tähän</div>
      <div class="panel-body">DB.vastaukset.vastaus, DB.vastaukset.selvennys tähän</div>
    </div>
  </div>
</div>

<br>

<div class="container">
  <div class="panel-group">
    <div class="panel panel-default">
      <div class="panel-heading">DB.kysymykset.kysymys_ID tähän, DB.kysymykset.kysymys tähän</div>
      <div class="panel-body">DB.vastaukset.vastaus, DB.vastaukset.selvennys tähän</div>
    </div>
  </div>
</div>

</body>
</html>