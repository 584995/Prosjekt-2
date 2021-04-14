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
				<select name="test">
					<option value="" selected>-----Velg_et_spill-----</option>
					<c:forEach items="${resultater}" var="d">
						<c:choose>
							<c:when test="${d.ferdig_dato != null}">
								<option value="${d.id}">Dato:  ${d.ferdig_dato}</option>
								
							</c:when>
						</c:choose>
					</c:forEach>
				</select>
				<input type="submit" value="Submit">
			</div>
			
		</fieldset>
	</form>
	<form action="Hovedmeny" method="get">
		<button type="submit" >Hovedmeny</button>
	</form>
</body>
</html>