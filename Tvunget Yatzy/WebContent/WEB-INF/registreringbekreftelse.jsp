<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registreringbekreftelse</title>
</head>
<body>
	<h2>Registreringbekreftelse</h2>
	<p>Registreringen er fullført!</p>
	<p>
		${brukernavn}<br />
		${mobil}<br /> 
		${epost}
	</p>
	<form action="Hovedmeny" method="get">
		<button type="submit" >Gå til hovedmenyen</button>
	</form>
</body>
</html>