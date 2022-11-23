<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Question</title>

    <%@include file="templates/imports.jsp" %>
</head>
<body>
<div class="container" align="center">
	<h1><%= "Question" %>
	</h1>
	<br/>
	<c:if test="${question != null}">
		<form action="edit" method="post">
	</c:if>
	<c:if test="${question == null}">
		<form action="new" method="post">
	</c:if>
		
		<c:if test="${question != null}">
			<p>Id:</p>
			<input type="text" name="id" value="<c:out value='${question.getQuestionID() }'/>" readonly />
		</c:if>

		<p>Kysymys:</p>
		<c:if test="${question != null}">
			<input type="text" name="kysymys" value="<c:out value='${question.getQuestion() }'/>"  />
		</c:if>
		<c:if test="${question == null}">
			<input type="text" name="kysymys" value="" required />
		</c:if>

		<br>
		<c:if test="${question != null}">
			<input type="submit" value="Tallenna">
		</c:if>
		<c:if test="${question == null}">
			<input type="submit" value="Luo">
		</c:if>
	</form>
</div>
</body>
</html>