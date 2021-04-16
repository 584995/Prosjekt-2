package test;

import static org.junit.Assert.*;

import org.junit.Test;

import yatzy.Terning;
/**
 * @author     Prosjektgruppe 2
 * @version     12                 
 * @since       12    
 */
public class KastOgTerningTester {

	public Terning terning = new Terning();
	
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

}
