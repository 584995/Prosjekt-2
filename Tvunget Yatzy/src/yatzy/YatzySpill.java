package yatzy;

import java.time.LocalDate;
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
		List<String> tabell = resultat.lagListe();
		List<Bruker> spillere = resultat.getSpillere();
		for (String s : tabell) {
			for (int i = 0; i < spillere.size(); i++) {
				if (i < spillere.size() - 1) {
					if (s.substring(i * 15 + 10, (i+1) * 15).equals("00000"))
						return spillere.get(i);
				} else {
					if (s.substring(i * 15 + 10).equals("00000"))
						return spillere.get(i);
				}
			}
		}
		resultat.setFerdig_dato(LocalDate.now().getDayOfMonth() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getYear());
		return null;
	}
	
	public int spillerPos (Resultat resultat, Bruker bruker) {
		int i = 0;
		for (Bruker b : resultat.getSpillere()) {
			if (b.equals(bruker))
				return i;
			i++;
		}
		return -1;		
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
