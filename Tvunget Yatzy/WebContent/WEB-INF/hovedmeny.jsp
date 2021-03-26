<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h2>Velkommen til Tvungen Yatzy!</h2>
	</div>
	<c:choose>
		<c:when test="${erISpill}">
			<form action="Spill" method="get">
				<button type="submit" >Fortsett spill</button>
			</form>
		</c:when>
		<c:otherwise>
			<form>
				<select id="resultatId" name="resultatId">
					<option value="" selected>-----Velg_et_spill-----</option>
					<c:forEach items="${resultater}" var="d">
						<option value="${d.id}">${d.spillere[0].brukernavn}</option>
					</c:forEach>
				</select>
				<button type="submit" >Bli med i spill</button>
			</form>
			<form action="Spill" method="get">
				<button type="submit" >Start nytt spill</button>
			</form>
		</c:otherwise>
	</c:choose>
	<form action="Gjenspilling" method="post">
		<button type="submit" >Gjenspilling</button>
	</form>
	<form action="Utlogging" method="get">
		<button type="submit" >Logg ut</button>
	</form>
</body>
</html>