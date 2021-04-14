<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/main.css">
</head>
<body>
<div id="main-wrapper">
<footer>
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
						</footer>
    
    <form action="Hovedmeny" method="get">
            <button type="submit" >Gå tilbake til menyen</button>
        </form>

</body>
</html>