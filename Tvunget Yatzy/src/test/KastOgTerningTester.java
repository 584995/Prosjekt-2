package test;

import static org.junit.Assert.*;

import org.junit.Test;

import yatzy.Terning;
import yatzy.Terningkast;
/**
 * @author     Prosjektgruppe 2
 * @version     12                 
 * @since       12    
 */
public class KastOgTerningTester {

	public Terning terning = new Terning();
	public Terningkast terningKast1 = new Terningkast();
	public Terningkast terningKast2 = new Terningkast();
	
	@Test
	public void terningTest() {
		
	int øyne = terning.trill();
	Boolean bool = false;
	for(int i = 1; i < 6; i++) {
		
		if(øyne == i) {
			bool = true;
		}
	}
	assertTrue(bool);
	
	}
	
	@Test
	public void TerningkastTest() {
		
		terningKast1.kast(3);
		terningKast2.kast(3);
		assertNotEquals(terningKast1, terningKast2);
		
	}
}
