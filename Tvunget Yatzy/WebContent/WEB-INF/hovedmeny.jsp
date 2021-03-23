<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script defer>
	function loggUt() {
		document.requestForm.action = "Utlogging";
		document.requestForm.submit();
	}
</script>
</head>
<body>
	<div id="navigasjon">
		<a href=""><button>Start nytt spill</button></a>
		<a href=""><button>Pågående spill</button></a>
		<button onclick="loggUt()">Logg ut</button>
	</div>
	<div id="main-wrapper">
		<p>Velkommen til Tvungen Yatzy!
		</p>
	</div>
	<form action="Utlogging" method="post">
		<button type="submit" >Logg ut</button>
	</form>
</body>
</html>