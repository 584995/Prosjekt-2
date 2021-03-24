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
	<h2>Gjenspilling-meny</h2>
	<form action="Gjenspilling" method="post">
		<fieldset>
			<div>
				<label for="resultat">Velg et spill:</label>
				<select id="resultatId" name="resultatId">
					<option value="" selected>-----Velg_et_spill-----</option>
					<c:forEach items="${resultater}" var="d">
						<option value="${d.id}">${d.id}</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<button type="submit" >Gjenspill</button>
			</div>
		</fieldset>
	</form>
	<form action="Hovedmeny" method="get">
		<button type="submit" >Hovedmeny</button>
	</form>
</body>
</html>