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
	
	public synchronized void lagreNyttResultat(Resultat nyttResultat) {
		em.persist(nyttResultat);
	}
	
	public synchronized void oppdaterResultat(Resultat resultat) {
		em.merge(resultat);
	}
	
	public synchronized List<Resultat> hentAlleResultat() {
		return em.createQuery("SELECT r FROM Resultat r",Resultat.class).getResultList();
	}
	
	public synchronized Resultat hentResultat(Integer id) {
		return em.find(Resultat.class, id);
	}
	
	public synchronized void fjernResultat (Integer id) {
		em.createQuery("DELETE FROM Resultat r WHERE r.id = :id",Resultat.class).setParameter("id", id).executeUpdate();
	}
	
}
