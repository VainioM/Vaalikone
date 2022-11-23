<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>

    <%@include file="templates/imports.jsp" %>
</head>
<body>
<div class="container">
	<h1>Dashboard</h1>
	<br/>
	<div align="center">
        <div class="row">
        	<div class="col-xs-6 text-left">
        		<caption><h2>Adminit</h2></caption>
       		</div>
        	<div class="col-xs-6 text-right">
        		<h2><a href='/dashboard/admin/new'>Uusi</a></h2>
        	</div>
        </div>
	    <table class="table table-striped">
	        <tr>
	            <th>ID</th>
				<th>Tunnus</th>
	            <th>Nimi</th>
	            <th>Muokkaa</th>
	        </tr>
	        <c:forEach var="admin" items="${listAdmins}">
	            <tr>
	                <td><c:out value="${admin.getId()}" /></td>
	                <td><c:out value="${admin.getTunnus()}" /></td>
					<td><c:out value="${admin.getNimi()}" /></td>
	                <td>
	                    <% out.println("<a href='/dashboard/admin/edit?q=");%><c:out value="${admin.getTunnus()}" /><%out.println("'>Muokkaa</a>"); %>
						-
						<% out.println("<a href='/dashboard/admin/delete?q=");%><c:out value="${admin.getTunnus()}" /><%out.println("'>Poista</a>"); %>
	                </td>
	            </tr>
	        </c:forEach>
	    </table>
	</div>
	<br/>
	<div align="center">
        <div class="row">
        	<div class="col-md-6 text-left">
        		<caption><h2>Ehdokkaat</h2></caption>
       		</div>
        	<div class="col-md-6 text-right">
        		<h2><a href='/dashboard/ehdokas/new'>Uusi</a></h2>
        	</div>
        </div>
	    <table class="table table-striped">

	        <tr>
	            <th>ID</th>
				<th>Tunnus</th>
	            <th>Etunimi</th>
	            <th>Sukunimi</th>
	            <th>Muokkaa</th>
	        </tr>
	        <c:forEach var="ehdokas" items="${listEhdokkaat}">
	            <tr>
	                <td><c:out value="${ehdokas.getId()}" /></td>
	                <td><c:out value="${ehdokas.getTunnus()}" /></td>
					<td><c:out value="${ehdokas.getEtunimi()}" /></td>
					<td><c:out value="${ehdokas.getSukunimi()}" /></td>
	                <td>
	                    <% out.println("<a href='/dashboard/ehdokas/edit?q=");%><c:out value="${ehdokas.getId()}" /><%out.println("'>Muokkaa</a>"); %>
						-
						<% out.println("<a href='/dashboard/ehdokas/delete?q=");%><c:out value="${ehdokas.getTunnus()}" /><%out.println("'>Poista</a>"); %>
	                </td>
	            </tr>
	        </c:forEach>
	    </table>
	</div>
		<br/>
	<div align="center">
        <div class="row">
        	<div class="col-md-6 text-left">
        		<caption><h2>Kysymykset</h2></caption>
       		</div>
        	<div class="col-md-6 text-right">
        		<h2><a href='/dashboard/kysymykset/new'>Uusi</a></h2>
        	</div>
        </div>
	    <table class="table table-striped">

	        <tr>
	            <th>ID</th>
				<th>Kysymys</th>
	            <th>Muokkaa</th>
	        </tr>
	        <c:forEach var="question" items="${listKysymykset}">
	            <tr>
	                <td><c:out value="${question.getQuestionID()}" /></td>
					<td><c:out value="${question.getQuestion()}" /></td>
	                <td>
	                    <% out.println("<a href='/dashboard/kysymykset/edit?q=");%><c:out value="${question.getQuestionID()}" /><%out.println("'>Muokkaa</a>"); %>
						-
						<% out.println("<a href='/dashboard/kysymykset/delete?q=");%><c:out value="${question.getQuestionID()}" /><%out.println("'>Poista</a>"); %>
	                </td>
	            </tr>
	        </c:forEach>
	    </table>
	</div>

</div>
</body>
</html>