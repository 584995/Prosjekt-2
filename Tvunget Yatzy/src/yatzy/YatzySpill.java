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
	
	/** Triller terning for alle verdier som boolean false
	 * 
	 * @param resultat resultatet for spillet
	 * @param bruker Brukeren som kaster
	 * @param behold terningen som brukeren har valgt å beholde
	 */
	public void spillTur(Resultat resultat, Bruker bruker, List<Boolean> behold) {
		
		String forrigeKast = forrigeKast(resultat, bruker);		
		String terningkast = terningkast(forrigeKast, behold);
		resultat.lagreKast(terningkast);
		resultat.nesteKast();
		
	}
	
	/** Finner ut terninger sine verdier ved hjelp av forgje kast
	 * 
	 * @param resultat Resultat for spillet 
	 * @param bruker Brukeren som kaster
	 * @return returnerer terningene
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
	
	/** Sjekker hvilke terninger som har blitt beholdt, og triller resten.
	 * 
	 * @param forrigeKast henter verdiene fra forgje kast
	 * @param behold terninger som har blitt beholdt
	 * @return terningkastet
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
	
}
