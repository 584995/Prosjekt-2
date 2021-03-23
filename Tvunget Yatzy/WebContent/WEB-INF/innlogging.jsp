<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Innlogging</title>
</head>
<body>
	<h2>Innlogging</h2>
	<form action="Innlogging" method="post">
		<fieldset>
			<font color="red">${feilmelding}</font>
			<div>
				<label for="brukernavn">Brukernavn:</label>
				<input type="text" name="mobil" placeholder="Brukernavn"/>
			</div>
			<div>
				<label for="passord">Passord:</label> 
				<input type="password" name="passord" placeholder="Passord"/>
			</div>
			<div>
				<button type="submit" >Logg inn</button>
			</div>
		</fieldset>
	</form>
	<form action="Registrering" method="get">
		<button type="submit" >Registrering</button>
	</form>
</body>
</html>