package yatzy;

import java.util.ArrayList;
import java.util.List;
/**
 * @author     Prosjektgruppe 2
 * @version     12                 
 * @since       12    
 */
public class Terningkast {
	private Terning terning;
	//Konstruktør
	public Terningkast () {
		terning = new Terning();
	}
	
	/** Simulerer et kast
	 * 
	 * @param antall Antall terning i kastet
	 * @return verdier fra terningkastet
	 */
	public List<Integer> kast(int antall) {
		List<Integer> terningkast = new ArrayList<Integer>();
		for (int i = 0; i < antall; i++)
			terningkast.add(terning.trill());
		return terningkast;
	}
	

}
