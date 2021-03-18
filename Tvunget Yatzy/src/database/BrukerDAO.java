package database;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BrukerDAO {

	@PersistenceContext(name = "brukerPU")
	private EntityManager em;
	
	public void lagreNyBruker(Bruker nyBruker) {
		em.persist(nyBruker);
	}
	
	public List<Bruker> hentAlleBrukere() {
		return em.createQuery("SELECT b FROM bruker b ",Bruker.class).getResultList();
	}
	
	public Bruker hentBruker(String brukernavn) {
		return em.find(Bruker.class, brukernavn);
	}
	
}
