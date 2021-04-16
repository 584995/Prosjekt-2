package database;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 * @author     Prosjektgruppe 2
 * @version     12                 
 * @since       12    
 */
@Stateless
public class ResultatDAO {

	@PersistenceContext(name = "resultatPU")
	private EntityManager em;
	
	public void lagreNyttResultat(Resultat nyttResultat) {
		em.persist(nyttResultat);
	}
	
	public void oppdaterResultat(Resultat resultat) {
		em.merge(resultat);
	}
	
	public List<Resultat> hentAlleResultat() {
		return em.createQuery("SELECT r FROM Resultat r",Resultat.class).getResultList();
	}
	
	public Resultat hentResultat(Integer id) {
		return em.find(Resultat.class, id);
	}
	
	public void fjernResultat (Resultat resultat) {
		em.remove(resultat);
	}
	
}
