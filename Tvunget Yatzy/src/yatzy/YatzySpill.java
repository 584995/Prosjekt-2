package yatzy;

import java.time.LocalDate;
import java.util.List;

import database.Bruker;
import database.Resultat;

public class YatzySpill {
	private Terning terning;
	
	public YatzySpill () {
		terning = new Terning();
	}
	
	public void spillTur(Resultat resultat, Bruker bruker, List<Boolean> behold) {
		
		String forrigeKast = forrigeKast(resultat, bruker);		
		String terningkast = terningkast(forrigeKast, behold);
		lagreKast(resultat, bruker, terningkast);
		resultat.setTur((resultat.getTur() + 1) % (resultat.getSpillere().size() * 3));
		
	}
	
	public int spillerPos (Resultat resultat, Bruker bruker) {
		int i = 0;
		for (Bruker b : resultat.getSpillere()) {
			if (b.getBrukernavn().equals(bruker.getBrukernavn()))
				return i;
			i++;
		}
		return -1;		
	}
	
	public String forrigeKast (Resultat resultat, Bruker bruker) {
		List<String> tabell = resultat.lagListe();
		int spillerPos = spillerPos(resultat, bruker);
		String forsteKast = null;
		String andreKast = null;
		for (String s : tabell) {
			forsteKast = s.substring(spillerPos * 15, spillerPos * 15 + 5);
			if (forsteKast.equals("00000"))
				return andreKast;
			
			andreKast = s.substring(spillerPos * 15 + 5, spillerPos * 15 + 10);
			if (andreKast.equals("00000"))
				return forsteKast;			
		}
		return null;
	}
	
	public String terningkast(String forrigeKast, List<Boolean> behold) {
		
		String terningkast = "";
		
		if (!behold.get(0))
			terningkast += terning.trill();
		else
			terningkast += forrigeKast.substring(0,1);
		if (!behold.get(1))
			terningkast += terning.trill();
		else
			terningkast += forrigeKast.substring(1,2);
		if (!behold.get(2))
			terningkast += terning.trill();
		else
			terningkast += forrigeKast.substring(2,3);
		if (!behold.get(3))
			terningkast += terning.trill();
		else
			terningkast += forrigeKast.substring(3,4);
		if (!behold.get(4))
			terningkast += terning.trill();
		else
			terningkast += forrigeKast.substring(4);
		return terningkast;
		
	}
	
	public void lagreKast(Resultat resultat, Bruker bruker, String terningkast) {
		List<String> tabell = resultat.lagListe();
		int spillerPos = spillerPos(resultat, bruker);
		for (int i = 0; i < tabell.size(); i++) {			
			if (spillerPos == resultat.getSpillere().size() - 1) {
				String helRunde = tabell.get(i).substring(spillerPos * 15, spillerPos * 15 + 15);
				if (helRunde.contains("00000")) {
					resultat.lagreKast(i, terningkast);
					return;
				}
					
			} else {
				String helRunde = tabell.get(i).substring(spillerPos * 15);
				if (helRunde.contains("00000")) {
					resultat.lagreKast(i, terningkast);
					return;
				}				
			}			
		}
		
	}
	
	/*public Bruker sinTur(Resultat resultat) {
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
	}*/

	
	/*public String velgTerninger(List<Boolean> behold, List<Integer> verdier) {
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
	}*/
	
}
