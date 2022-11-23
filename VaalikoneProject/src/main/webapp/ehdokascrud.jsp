<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Ehdokas</title>

    <%@include file="templates/imports.jsp" %>
</head>
<body>
<div class="container" align="center">
	<h1><%= "Admin" %>
	</h1>
	<br/>
	<c:if test="${ehdokas != null}">
		<form action="edit" method="post">
	</c:if>
	<c:if test="${ehdokas == null}">
		<form action="new" method="post">
	</c:if>
		<p>Tunnus:</p>
		<c:if test="${ehdokas != null}">
			<input type="text" name="tunnus" value="<c:out value='${ehdokas.getTunnus() }'/>" readonly />
		</c:if>
		<c:if test="${ehdokas == null}">
			<input type="text" name="tunnus" value="" required/>
		</c:if>
		<c:if test="${ehdokas == null}">
			<p>Salasana:</p>
			<input type="password" name="salasana" required />
		</c:if>
		<br>
		<p>Etunimi:</p>
		<c:if test="${ehdokas != null}">
			<input type="text" name="etunimi" value="<c:out value='${ehdokas.getEtunimi() }'/>"  />
		</c:if>
		<c:if test="${ehdokas == null}">
			<input type="text" name="etunimi" value="" required />
		</c:if>
		<p>Sukunimi:</p>
		<c:if test="${ehdokas != null}">
			<input type="text" name="sukunimi" value="<c:out value='${ehdokas.getSukunimi() }'/>"  />
		</c:if>
		<c:if test="${ehdokas == null}">
			<input type="text" name="sukunimi" value="" required />
		</c:if>
		<p>Puolue:</p>
		<c:if test="${ehdokas != null}">
			<input type="text" name="puolue" value="<c:out value='${ehdokas.getPuolue() }'/>"  />
		</c:if>
		<c:if test="${ehdokas == null}">
			<input type="text" name="puolue" value="" required />
		</c:if>
		<p>Paikkakunta:</p>
		<c:if test="${ehdokas != null}">
			<input type="text" name="paikkakunta" value="<c:out value='${ehdokas.getPaikkakunta() }'/>"  />
		</c:if>
		<c:if test="${ehdokas == null}">
			<input type="text" name="paikkakunta" value="" required />
		</c:if>
		<p>Ikä:</p>
		<c:if test="${ehdokas != null}">
			<input type="number" name="ika" value="<c:out value='${ehdokas.getIka() }'/>"  />
		</c:if>
		<c:if test="${ehdokas == null}">
			<input type="text" name="ika" value="" required />
		</c:if>
		<p>Ammatti:</p>
		<c:if test="${ehdokas != null}">
			<input type="text" name="ammatti" value="<c:out value='${ehdokas.getAmmatti() }'/>"  />
		</c:if>
		<c:if test="${ehdokas == null}">
			<input type="text" name="ammatti" value="" required />
		</c:if>

		<br>
		<c:if test="${ehdokas != null}">
			<input type="submit" value="Tallenna">
		</c:if>
		<c:if test="${ehdokas == null}">
			<input type="submit" value="Luo">
		</c:if>
	</form>
</div>
</body>
</html>