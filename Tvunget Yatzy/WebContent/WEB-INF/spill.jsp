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
	<c:choose>
		<c:when test="${sinTur}">
			<form action="Spill" method="post">
				<c:choose>
					<c:when test="${kastetur == 0}">
						<p>F�rste kast. Trill i vei.</p>
					</c:when>
					<c:otherwise>
						<p>Velg de du vil beholde:</p>
						<label for="terning1">${terning1}</label>
						<input type="checkbox" id="terning1" name="terning1" value="true"></input>
						<label for="terning2">${terning2}</label>
						<input type="checkbox" id="terning2" name="terning2" value="true"></input>
						<label for="terning3">${terning3}</label>
						<input type="checkbox" id="terning3" name="terning3" value="true"></input>
						<label for="terning4">${terning4}</label>
						<input type="checkbox" id="terning4" name="terning4" value="true"></input>
						<label for="terning5">${terning5}</label>
						<input type="checkbox" id="terning5" name="terning5" value="true"></input>
					</c:otherwise>
				</c:choose>
				<button type="submit" >Trill</button>
			</form>
		</c:when>
		<c:otherwise>
			<h2>Vent p� din tur.</h2>
			<form action="Hovedmeny" method="get">
				<button type="submit" >Hovedmeny</button>
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>