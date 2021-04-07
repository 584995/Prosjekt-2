<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Yatzy</title>
<link rel="stylesheet" href="css/main.css">
</head>
<body>
	<div id="main-wrapper">
		<c:choose>
			<c:when test="${startet}">
					<table>
						<c:forEach items="${poengTabell}" var="p">
							<tr>
								<c:choose>
									<c:when test="${p[0].equals(poengTabell[0][0])}">
										<c:forEach items="${p}" var="t">
											<th>${t}</th>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<c:forEach items="${p}" var="t">
											<td>${t}</td>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
					</table>
					<c:choose>
						<c:when test="${sinTur}">
							<form action="Spill" method="post">
								<c:choose>
									<c:when test="${kastetur == 0}">
										<p>Første kast. Trill i vei.</p>
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
							<h2>Vent på din tur.</h2>
							<%
	    						// Set refresh, autoload time as 1 seconds
	    						response.setIntHeader("Refresh", 1);
	   	 					%>
						</c:otherwise>
					</c:choose>
			</c:when>
			<c:otherwise>
				<h2>Venter på flere spillere...</h2>
				<p>Spillere som er med så langt: </p><c:forEach items="${spillere}" var="s"><p>${s.brukernavn}</p></c:forEach>
				<legend>Manuell start (Må være minst 2)</legend>
				<form action="Startspill" method="post">
					<button type="submit" >Start spill</button>
				</form>
				<%
	    			// Set refresh, autoload time as 1 seconds
	    			response.setIntHeader("Refresh", 1);
	   	 		%>
			</c:otherwise>
		</c:choose>
		<form action="Hovedmeny" method="get">
			<button type="submit" >Hovedmeny</button>
		</form>
	</div>
</body>
</html>