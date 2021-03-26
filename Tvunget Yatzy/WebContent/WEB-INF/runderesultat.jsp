<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Yatzy</title>
</head>
<body>
	<div>
	<p>Du trillte: ${terning1} ${terning2} ${terning3} ${terning4} ${terning5}</p>
	</div>
	<form action="Spill" method="get">
		<button type="submit" >Neste tur</button>
	</form>
</body>
</html>