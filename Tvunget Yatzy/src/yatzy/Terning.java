package yatzy;
/**
 * @author     Prosjektgruppe 2
 * @version     12                 
 * @since       12    
 */
public class Terning {
	/** Simulerer en terning
	 * 
	 * @return Terningens verdi
	 */
	public int trill() {
		return 1 + (int)(Math.random()*6);
	}	
	
}
