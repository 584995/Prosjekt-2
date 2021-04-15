package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import database.Bruker;
import database.Resultat;
/**
 * @author     Prosjektgruppe 2
 * @version     12                 
 * @since       12    
 */

public class ResultatTester {
	
	private Resultat resultat = new Resultat();
	private Bruker bruker1, bruker2;
	
	List<Bruker> mockSpillere;
	
	@Before
	public void setUp() {
		
	mockSpillere = Arrays.asList(bruker1, bruker2);	
	resultat.setSpillere(mockSpillere);
	
	//siste fem sifferene er antall enere
	//for spiller 1 "11122" og spiller 2 "55551"
	resultat.setEnere("01222224444411122" + "02222224444455551");
	resultat.setToere("01222224444411122" + "02222224444455551");
	resultat.setTreere("01222224444413333" + "02222224444455551");
	resultat.setFirere("01222224444413344" + "02222224444455551");
	resultat.setFemmere("01222224444413555" + "02222224444455551");
	resultat.setSeksere("01222224444466666" + "02222224444455551");
	resultat.setEtt_par("01222224444466111" + "02222224444412345");
	resultat.setTo_par("01222224444466111" + "02222224444412345");
	resultat.setTre_like("01222224444466111" + "02222224444412345");
	resultat.setFire_like("01222224444466661" + "02222224444412345");
	resultat.setLiten_straight("01222224444466661" + "02222224444412345");
	resultat.setStor_straight("01222224444466661" + "02222224444423456");
	resultat.setHus("01222224444411122" + "02222224444455566");
	resultat.setSjanse("01222224444412345" + "02222224444411111");
	resultat.setYatzy("01222224444412345" + "02222224444411111");
	
	}

	@Test
	public void testEnere() {
		
		int res1 = resultat.regnEnere(0); //skal være 3
		int res2 = resultat.regnEnere(1); //Skal være 1
		
		assertEquals(3, res1);
		assertNotEquals(2, res2);
	}
	
	@Test
	public void testToere() {
		
		int res1 = resultat.regnToere(0); //skal være 4
		int res2 = resultat.regnToere(1); //skal være 0
		
		assertEquals(4, res1);
		assertNotEquals(2, res2);
	}
	
	@Test
	public void testTreere() {
		
		int res1 = resultat.regnTreere(0); //skal være 12
		int res2 = resultat.regnTreere(1); //skal være 0
		
		assertEquals(12, res1);
		assertEquals(0, res2);
	}
	
	@Test
	public void testFirere() {
		
		int res1 = resultat.regnFirere(0); //skal være 8
		int res2 = resultat.regnFirere(1); //skal være 0
		
		assertEquals(8, res1);
		assertEquals(0, res2);
	}
	
	@Test
	public void testFemmere() {
		
		int res1 = resultat.regnFemmere(0); //skal være 15
		int res2 = resultat.regnFemmere(1); //skal være 20
		
		assertEquals(15, res1);
		assertEquals(20, res2);
	}
	
	@Test
	public void testSeksere() {
		
		int res1 = resultat.regnSeksere(0); //skal være 30
		int res2 = resultat.regnSeksere(1); //skal være 0
		
		assertEquals(30, res1);
		assertEquals(0, res2);
	}
	
	@Test
	public void testSum() {	
		
		int res1 = resultat.regnSum(0); //skal være 72
		int res2 = resultat.regnSum(1); //skal være 21
		
		assertEquals(72, res1);
		assertEquals(21, res2);
	}
	
	@Test
	public void testBonus() {	
		
		int res1 = resultat.regnBonus(0); //skal være 50
		int res2 = resultat.regnBonus(1); //skal være 0
		
		assertEquals(50, res1);
		assertEquals(0, res2);
	}
	
	@Test
	public void testEttPar() {
		
		int res1 = resultat.regnEttPar(0); //skal være 12
		int res2 = resultat.regnEttPar(1); //skal være 0
		
		assertEquals(12, res1);
		assertEquals(0, res2);
	}
	
	@Test
	public void testToPar() {
		
		int res1 = resultat.regnToPar(0); //skal være 14
		int res2 = resultat.regnToPar(1); //skal være 0
		
		assertEquals(14, res1);
		assertEquals(0, res2);
	}
	
	@Test
	public void testTreLike() {
		
		int res1 = resultat.regnTreLike(0); //skal være 3
		int res2 = resultat.regnTreLike(1); //skal være 0
		
		assertEquals(3, res1);
		assertEquals(0, res2);
	}
	
	@Test
	public void testFireLike() {
		
		int res1 = resultat.regnFireLike(0); //skal være 24
		int res2 = resultat.regnFireLike(1); //skal være 0
		
		assertEquals(24, res1);
		assertEquals(0, res2);
	}
	
	@Test
	public void testLitenStright() {
		
		int res1 = resultat.regnLitenStraight(0); //skal være 0
		int res2 = resultat.regnLitenStraight(1); //skal være 15
		
		assertEquals(0, res1);
		assertEquals(15, res2);
	}
	
	@Test
	public void testStorStright() {
		
		int res1 = resultat.regnStorStraight(0); //skal være 0
		int res2 = resultat.regnStorStraight(1); //skal være 20
		
		assertEquals(0, res1);
		assertEquals(20, res2);
	}
	
	@Test
	public void testHus() {
		
		int hus1 = resultat.regnHus(0); //skal være 7
		int hus2 = resultat.regnHus(1); //skal være 27
		
		assertEquals(7, hus1);
		assertEquals(27, hus2);
	}
	
	@Test
	public void testSjanse() {
		
		int res1 = resultat.regnSjanse(0); //skal være 15
		int res2 = resultat.regnSjanse(1); //skal være 5
		
		assertEquals(15, res1);
		assertEquals(5, res2);
	}
	
	@Test
	public void testYatzy() {
		
		int res1 = resultat.regnYatzy(0); //skal være 0
		int res2 = resultat.regnYatzy(1); //skal være 50
		
		assertEquals(0, res1);
		assertEquals(50, res2);
	}
	
	@Test
	public void testTotalt() {
		
		int res1 = resultat.regnTotalt(0); //skal være 197
		int res2 = resultat.regnTotalt(1); //skal være 138
		
		assertEquals(197, res1);
		assertEquals(138, res2);
	}
	
}
