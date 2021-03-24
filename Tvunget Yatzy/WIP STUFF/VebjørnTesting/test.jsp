<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Test</title>
</head>
<body>
	<div>
		<font>${bruker.brukernavn}</font>
	</div>
	<div>
		<font>${resultat.id}</font>
	</div>
	<div>
		<c:forEach items="${brukere}" var="d">
			<font>${d.brukernavn}</font>
		</c:forEach>
	</div>
	<div>
		<c:forEach items="${resultater}" var="d">
			<font>${d.id}</font>
		</c:forEach>
	</div>
</body>
</html>