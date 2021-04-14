package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import database.Bruker;
import database.Resultat;
import yatzy.YatzySpill;

public class YatzySpillTester {

	private Resultat resultat = new Resultat();
	private YatzySpill yatzySpill = new YatzySpill();
	private Bruker bruker1, bruker2;
	private List<Boolean> behold = new ArrayList<Boolean>();
	
	List<Bruker> mockSpillere;
	
	@Before
	public void setUp() {
		
		mockSpillere = Arrays.asList(bruker1, bruker2);	
		resultat.setSpillere(mockSpillere);
		resultat.leggTilKolonne();
	
	}
	
	@Test
	public void testFørsteKast() {
		
		behold.add(false);behold.add(false);behold.add(false);behold.add(false);behold.add(false);
		
		yatzySpill.spillTur(resultat, bruker1, behold);
		int kastNr = resultat.getKast_tur();
		
		assertEquals(kastNr, 1);
	}
	
	@Test
	public void testEnereBeholdt() {
		
		//første kast har 2 enere vi vil ta vare på
		resultat.setEnere("01222110000000000");
		behold.add(false);behold.add(false);behold.add(false);behold.add(true);behold.add(true);
		
		String forrigeKast = yatzySpill.forrigeKast(resultat, bruker1);
		String kast = yatzySpill.terningkast(forrigeKast, behold);
		
		assertTrue(kast.contains("11"));
	}
	
	@Test
	public void testLagreTilResultat() {
		
		//beholder 5 enere, som skal lagres til resultat
		resultat.setEnere("01111111111100000");
		behold.add(true);behold.add(true);behold.add(true);behold.add(true);behold.add(true);
		
		resultat.nesteKast();
		resultat.nesteKast();
		
		yatzySpill.spillTur(resultat, bruker1, behold);
		
		assertEquals(resultat.regnEnere(0), 5);
	}
	
	
}
