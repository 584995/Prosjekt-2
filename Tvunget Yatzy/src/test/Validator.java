package test;

public class Validator {
	
	//godtar alle bokstaver, tall, bindestrek og mellomrom, skal ha lengde på 2-20
	public boolean brukernavnSjekk(String s) {
		
		return s != null && s.matches("^[-a-zA-ZæøåÆØÅ_0-9 ]{2,20}$");
	}
	
	//godtar bare siffer, skal ha lengde på 8
	public boolean mobilSjekk(String s) {
		
		return s != null && s.matches("^[0-9]{8}$");
	}
	
	//Godtar alt, minimumlengde på 8
	public boolean passordSjekk(String s) {
		
		return s != null && s.matches("^.{8,}$");
	}
	
	//godtar a-z, 0-9, _.- før @, og godtar alt etter
	public boolean epostSjekk(String s) {
		
		return s != null && s.matches("^[A-Za-z0-9+_.-]+@(.+)$");
	}
}
