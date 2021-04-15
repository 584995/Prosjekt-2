package yatzy;

import java.util.List;

import database.Bruker;
import database.Resultat;
/**
 * @author     Prosjektgruppe 2
 * @version     12                 
 * @since       12    
 */
public class YatzySpill {
	private Terning terning;
	
	public YatzySpill () {
		terning = new Terning();
	}
	
	/** 
	 * 
	 * @param resultat
	 * @param bruker
	 * @param behold
	 */
	public void spillTur(Resultat resultat, Bruker bruker, List<Boolean> behold) {
		
		String forrigeKast = forrigeKast(resultat, bruker);		
		String terningkast = terningkast(forrigeKast, behold);
		resultat.lagreKast(terningkast);
		resultat.nesteKast();
		
	}
	
	/**
	 * 
	 * @param resultat
	 * @param bruker
	 * @return
	 */
	public String forrigeKast (Resultat resultat, Bruker bruker) {
		List<String> tabell = resultat.lagListe();
		int spillerPos = resultat.getSpiller_tur();
		String forsteKast = null;
		String andreKast = null;
		for (String s : tabell) {
			forsteKast = s.substring(2 + spillerPos * 17, 2 + spillerPos * 17 + 5);
			if (forsteKast.equals("00000"))
				return andreKast;
			
			andreKast = s.substring(2 + spillerPos * 17 + 5, 2 + spillerPos * 17 + 10);
			if (andreKast.equals("00000"))
				return forsteKast;
		}
		if (resultat.getRunde() == 15 && resultat.getKast_tur() == 2 && spillerPos == resultat.getSpillere().size())
			return resultat.getYatzy().substring(2 + spillerPos * 17 + 5);
		if (resultat.getRunde() == 15 && resultat.getKast_tur() == 2)
			return resultat.getYatzy().substring(2 + spillerPos * 17 + 5, 2 + spillerPos * 17 + 10);
		return null;
	}
	
	/**
	 * 
	 * @param forrigeKast
	 * @param behold
	 * @return
	 */
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
