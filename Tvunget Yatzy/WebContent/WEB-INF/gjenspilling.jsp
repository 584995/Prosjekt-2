<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<p>resultater for ${brukernavn}</p>
    <table>
        <c:forEach items="${resultatliste}" var="p">
            <tr>
                <th>${p}</th>
            </tr>
        </c:forEach>
    </table>
    
    <form action="Hovedmeny" method="get">
            <button type="submit" >Gå tilbake til menyen</button>
        </form>

</body>
</html>