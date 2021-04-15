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
	<div><p>Logget inn som: ${brukernavn}</p></div>	
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
											<c:choose>
												<c:when test="${spillerSinTur.equals(t)}">													
													<td bgcolor="#aaffaa">${t}</td>													
												</c:when>
												<c:otherwise>
													<td>${t}</td>
												</c:otherwise>
											</c:choose>
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
										<table class="center">
											<tr>
												<td>
													<label for="terning1">
														<c:choose>
															<c:when test="${terning1 == 6}">
																<img alt="1" src="bilder/Seks.png">
															</c:when>
															<c:when test="${terning1 == 5}">
																<img alt="2" src="bilder/Fem.png">
															</c:when>
															<c:when test="${terning1 == 4}">
																<img alt="3" src="bilder/Fire.png">
															</c:when>
															<c:when test="${terning1 == 3}">
																<img alt="4" src="bilder/Tre.png">
															</c:when>
															<c:when test="${terning1 == 2}">
																<img alt="5" src="bilder/To.png">
															</c:when>
															<c:when test="${terning1 == 1}">
																<img alt="5" src="bilder/En.png">
															</c:when>
															<c:otherwise>
																<p>Feilmelding</p>
															</c:otherwise>
														</c:choose>
													</label>
													<input type="checkbox" id="terning1" name="terning1" value="true"></input>
												</td>
												<td>
													<label for="terning2">
														<c:choose>
															<c:when test="${terning2 == 6}">
																<img alt="1" src="bilder/Seks.png">
															</c:when>
															<c:when test="${terning2 == 5}">
																<img alt="2" src="bilder/Fem.png">
															</c:when>
															<c:when test="${terning2 == 4}">
																<img alt="3" src="bilder/Fire.png">
															</c:when>
															<c:when test="${terning2 == 3}">
																<img alt="4" src="bilder/Tre.png">
															</c:when>
															<c:when test="${terning2 == 2}">
																<img alt="5" src="bilder/To.png">
															</c:when>
															<c:when test="${terning2 == 1}">
																<img alt="5" src="bilder/En.png">
															</c:when>
															<c:otherwise>
																<p>Feilmelding</p>
															</c:otherwise>
														</c:choose>
													</label>
													<input type="checkbox" id="terning2" name="terning2" value="true"></input>
												</td>
												<td>
													<label for="terning3">
														<c:choose>
															<c:when test="${terning3 == 6}">
																<img alt="1" src="bilder/Seks.png">
															</c:when>
															<c:when test="${terning3 == 5}">
																<img alt="2" src="bilder/Fem.png">
															</c:when>
															<c:when test="${terning3 == 4}">
																<img alt="3" src="bilder/Fire.png">
															</c:when>
															<c:when test="${terning3 == 3}">
																<img alt="4" src="bilder/Tre.png">
															</c:when>
															<c:when test="${terning3 == 2}">
																<img alt="5" src="bilder/To.png">
															</c:when>
															<c:when test="${terning3 == 1}">
																<img alt="5" src="bilder/En.png">
															</c:when>
															<c:otherwise>
																<p>Feilmelding</p>
															</c:otherwise>
														</c:choose>
													</label>
													<input type="checkbox" id="terning3" name="terning3" value="true"></input>
												</td>
												<td>
													<label for="terning4">
														<c:choose>
															<c:when test="${terning4 == 6}">
																<img alt="1" src="bilder/Seks.png">
															</c:when>
															<c:when test="${terning4 == 5}">
																<img alt="2" src="bilder/Fem.png">
															</c:when>
															<c:when test="${terning4 == 4}">
																<img alt="3" src="bilder/Fire.png">
															</c:when>
															<c:when test="${terning4 == 3}">
																<img alt="4" src="bilder/Tre.png">
															</c:when>
															<c:when test="${terning4 == 2}">
																<img alt="5" src="bilder/To.png">
															</c:when>
															<c:when test="${terning4 == 1}">
																<img alt="5" src="bilder/En.png">
															</c:when>
															<c:otherwise>
																<p>Feilmelding</p>
															</c:otherwise>
														</c:choose>
													</label>
													<input type="checkbox" id="terning4" name="terning4" value="true"></input>
												</td>
												<td>
													<label for="terning5">
														<c:choose>
															<c:when test="${terning5 == 6}">
																<img alt="1" src="bilder/Seks.png">
															</c:when>
															<c:when test="${terning5 == 5}">
																<img alt="2" src="bilder/Fem.png">
															</c:when>
															<c:when test="${terning5 == 4}">
																<img alt="3" src="bilder/Fire.png">
															</c:when>
															<c:when test="${terning5 == 3}">
																<img alt="4" src="bilder/Tre.png">
															</c:when>
															<c:when test="${terning5 == 2}">
																<img alt="5" src="bilder/To.png">
															</c:when>
															<c:when test="${terning5 == 1}">
																<img alt="5" src="bilder/En.png">
															</c:when>
															<c:otherwise>
																<p>Feilmelding</p>
															</c:otherwise>
														</c:choose>
													</label>
													<input type="checkbox" id="terning5" name="terning5" value="true"></input>
												</td>
											</tr>
										</table>
										
										
										
										
										
									</c:otherwise>
								</c:choose>
								<button type="submit" >Trill</button>
							</form>
						</c:when>
						<c:otherwise>
							<h2>Vent på din tur.</h2>
							<%
	    						// Set refresh, autoload time as 2 seconds
	    						response.setIntHeader("Refresh", 2);
	   	 					%>
	   	 					 <form action="Spill" method="post">
                            <h3>Venter ${spillerSinTur} for lenge? <input type="submit" name="vekk" value="Vekk"></h3>
                            <input type="hidden" name="spillerSinTur" value="${spillerSinTur}"> 
                            </form>
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