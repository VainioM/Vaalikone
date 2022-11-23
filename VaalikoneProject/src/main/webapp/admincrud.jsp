<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Admin</title>

    <%@include file="templates/imports.jsp" %>
</head>
<body>
<div class="container" align="center">
	<h1><%= "Admin" %>
	</h1>
	<br/>
	<c:if test="${admin != null}">
		<form action="edit" method="post">
	</c:if>
	<c:if test="${admin == null}">
		<form action="new" method="post">
	</c:if>
		<p>Tunnus:</p>
		<c:if test="${admin != null}">
			<input type="text" name="tunnus" value="<c:out value='${admin.getTunnus() }'/>" readonly />
		</c:if>
		<c:if test="${admin == null}">
			<input type="text" name="tunnus" value="" required/>
		</c:if>
		<p>Nimi:</p>
		<c:if test="${admin != null}">
			<input type="text" name="name" value="<c:out value='${admin.getNimi() }'/>"  />
		</c:if>
		<c:if test="${admin == null}">
			<input type="text" name="name" value="" required />
		</c:if>
		<c:if test="${admin == null}">
			<p>Salasana:</p>
			<input type="password" name="salasana" required />
		</c:if>
		<br>
		<c:if test="${admin != null}">
			<input type="submit" value="Tallenna">
		</c:if>
		<c:if test="${admin == null}">
			<input type="submit" value="Luo">
		</c:if>
	</form>
</div>
</body>
</html>