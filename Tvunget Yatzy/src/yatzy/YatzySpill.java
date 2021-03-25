package yatzy;

import java.util.List;

import database.Bruker;
import database.Resultat;

public class YatzySpill {
	private Terningkast terningkast;
	
	public YatzySpill () {
		terningkast = new Terningkast();
	}
	
	public void spillTur(Bruker bruker, Resultat resultat, List<Boolean> behold) {
		
		int antBehold = 5;

			for(boolean b : behold) {
				if(b) {
					antBehold--;
				}
			}
		List<Integer> verdier = terningkast.kast(antBehold);
		//display verdiene
		String verdi = velgTerninger(behold,verdier);
		
		
	
	}
	public void sendStartBeskjed() {
		
	}
	public void sendPurring(Bruker spiller) {
		
	}
	public void kastUtSpiller(Bruker spiller) {
		
	}
	public Bruker sinTur(Resultat resultat) {
		return null;
	}
	
	public String velgTerninger(List<Boolean> behold, List<Integer> verdier) {
		for(int i = 0; i< behold.size();i++) {
			if(behold.get(i)) {
				verdier.set(i,0);
			}
		}
		String verdi="";
		for(int i : verdier) {
			verdi+= i;
		}
		return verdi;
	}
	
}
