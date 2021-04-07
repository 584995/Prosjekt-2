<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tvunget Yatzy - Hovedmeny</title>
<link rel="stylesheet" href="css/main.css">
</head>
<body>
	<div id="main-wrapper">
		<h1>Velkommen til Tvunget Yatzy!</h1>

		<div id="info">
			<p>
				<strong>Regler:</strong> <br /> <br />2-6 spillere<br /> <br />Meningen
				med spillet er � oppn� en h�yest mulig poengsum. Poeng tildeles for
				15 forskjellige terningkombinasjonstyper. I hver runde f�r hver
				spiller inntil tre kast for � oppn� �n av disse typene. Spillerne
				f�lger spillearket (enere f�r toere osv.), men hvis man f�r yatzy s�
				kan dette tas uten � m�tte hensynta tvunget rekkef�lge, hvoretter
				man fortsetter tvungen rekkef�lge der man var f�r man fikk Yatzy.
			</p>
			<br>
		</div>
		<div id="spillmeny">
			<c:choose>
				<c:when test="${erISpill}">
					<fieldset>
						<legend>Aktivt spill</legend>
						<form action="Spill" method="get">
							<button type="submit" class="knapper">Fortsett spill</button>
						</form>
					</fieldset>
				</c:when>
				<c:otherwise>
					<fieldset>
						<legend>Aktive spill</legend>
						<form action="Hovedmeny" method="post">
							<select id="resultatId" name="resultatId">
								<option value="" selected>-----Velg_et_spill-----</option>
								<c:forEach items="${resultater}" var="r">
									<option value="${r.id}">${r.spillere[0].brukernavn}</option>
								</c:forEach>
							</select>
							<button type="submit" class="knapper">Bli med i spill</button>
						</form>
					</fieldset>
					<fieldset>
						<legend>Start nytt spill</legend>
						<form action="Nyttspill" method="post">
							<button type="submit" class="knapper">Nytt spill</button>
						</form>
					</fieldset>
				</c:otherwise>
			</c:choose>
			<fieldset>
				<legend>Reprise av ferdige spill</legend>
				<form action="Gjenspilling" method="post">
					<button type="submit" class="knapper">Gjenspilling</button>
				</form>
			</fieldset>
			<fieldset>
				<legend>Utlogging</legend>
				<form action="Utlogging" method="get" style="margin-left:1px;">
					<button type="submit" class="knapper">Logg ut</button>
				</form>
			</fieldset>
		</div>
	</div>
</body>
</html>