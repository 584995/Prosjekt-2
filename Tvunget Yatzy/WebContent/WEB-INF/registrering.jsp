<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrering</title>
</head>
<body>
	<h2>Registrering</h2>
	<form action="Registrering" method="post">
		<fieldset>
			<div>
				<label for="brukernavn">Brukernavn:</label> 
				<input type="text" name="brukernavn" value="${brukernavn}" placeholder="Brukernavn" required/>
				<font color="red">${brukernavnFeilmelding}</font> 
			</div>
			<div>
				<label for="passord">Passord:</label> 
				<input type="password" name="passord" value="${passord}" placeholder="Passord" required 
					onmouseover="this.parentElement.querySelector('#passordinfo').textContent = 'Passordstyrke regnes ut ifra lengde. 1-7 er ugyldig. 8-15 er svakt. 16-64 er sterkt.'"
					onmouseout="this.parentElement.querySelector('#passordinfo').textContent = ''"/>
					<font id="passordinfo" color="red"></font> 
			</div>
			<div>
				<label for="passordRepetert">Repeter passord:</label> 
				<input type="password" name="passordRepetert" value="${passordRepetert}" placeholder="Passord" required/> 
			</div>
			<div>
				<label for="mobil">Mobil (8 siffer):</label> 
				<input type="text" name="mobil" value="${mobil}" placeholder="Mobilnummer" required/> 
			</div>
			<div>
				<label for="epost">E-post:</label> 
				<input type="text" name="epost" value="${epost}" placeholder="E-post" required/> 
			</div>
			<div>
				<button type="submit">Registrer</button>
			</div>
		</fieldset>
	</form>
		<form action="Innlogging" method="get">
		<button type="submit" >Innlogging</button>
	</form>
</body>
</html>