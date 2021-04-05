package test;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*; 

import database.Bruker;
import database.BrukerDAO;

public class BrukerTester {
	
	private BrukerDAO dao;
	private EntityManager entityManager;
	
	@Before
	public void setup() {
		
		dao = new BrukerDAO();

		entityManager = mock(EntityManager.class);
		dao.setEntityManager(entityManager);
	}
		
		@Test
		public void LikeBrukernavn() {
			doAnswer(new Answer<Void>() {
				public Void answer(InvocationOnMock invocation) {					
					return null;
				}
			}).when(entityManager).persist(any(Bruker.class));

		Bruker bruker = new Bruker("Einstein", "ayy", 34343454, "ayy@gmail.com");
		Bruker bruker2 = new Bruker("Einstein", "ayy", 34343454, "ayy@gmail.com");	
		
		dao.lagreNyBruker(bruker);
		dao.lagreNyBruker(bruker2);
		
		assertEquals(bruker.getBrukernavn(), bruker2.getBrukernavn());
	}
		
		@Test
		public void finnBruker() {
			
		Bruker bruker = new Bruker("Einstein", "ayy", 34343454, "ayy@gmail.com");	
		
		when(entityManager.find(Bruker.class,"Einstein")).thenReturn(bruker);
		
		Bruker sok = dao.hentBruker("Einstein");
		assertEquals(bruker, sok);		
		}
		
		@Test
		public void BrukerfinnesIkke() {
			
			when(entityManager.find(Bruker.class,"Einstein")).thenReturn(null);
			
			Bruker sok = dao.hentBruker("Einstein");
			assertEquals(null, sok);	
		}
		
		
		
}