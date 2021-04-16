package database;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import validering.BCrypt;
/**
 * @author     Prosjektgruppe 2
 * @version     12                 
 * @since       12    
 */
@Entity
@Table(schema = "yatzy", name = "bruker")
public class Bruker {
	
	@Id
	private String brukernavn;
	private String passord;
	private int mobil;
	private String epost;
	private int purringer;
	
	@ManyToMany
	@JoinTable(name = "bruker_resultat", schema = "yatzy",
			  joinColumns = @JoinColumn(name = "bruker_id"), 
			  inverseJoinColumns = @JoinColumn(name = "resultat_id"))
	private List<Resultat> resultater;	
	
	public Bruker() {}
	
	public Bruker(String brukernavn, String passord, int mobil, String epost) {
		this.brukernavn = brukernavn;
		this.passord = BCrypt.hashpw(passord, BCrypt.gensalt(12));
		this.mobil = mobil;
		this.epost = epost;
		purringer = 0;
	}
	//Legger til resultat av yatzy spill eller runde for brukeren.
	public void leggTilResultat (Resultat resultat) {
		resultater.add(resultat);
	}
	
	//Fjerner resultat av yatzy spill eller runde for brukeren.
	public void fjernResultat (Resultat resultat) {
		int i = 0;
		for (Resultat r : resultater) {
			if (r.getId() == resultat.getId())
				break;
			i++;
		}
		resultater.remove(i);
	}
	
	/**Sjekker om brukeren er i et spill.
	 * 
	 * @return true dersom spiller er i et spill, ellers false
	 * 
	 */
	public boolean erISpill () {
		if(resultater.size() == 0) {
			return false;
		}
		
		for (Resultat e : resultater)
			if (e.getFerdig_dato() == null)
				return true;
		return false;
	}
	
	/** Returnerer brukerens resultat for aktiv runde.
	 * 
	 * @return resultatet som spiller er aktiv i, ellers null
	 * 
	 */
	public Resultat aktivtSpill () {
		for (int i = resultater.size()-1; i > -1; i--)
			if (resultater.get(i).getFerdig_dato() == null)
				return resultater.get(i);
		return null;
	}
	
	public String getBrukernavn() {
		return brukernavn;
	}

	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}

	public String getPassord() {
		return passord;
	}

	public void setPassord(String passord) {
		this.passord = passord;
	}

	public int getMobil() {
		return mobil;
	}

	public void setMobil(int mobil) {
		this.mobil = mobil;
	}

	public String getEpost() {
		return epost;
	}

	public void setEpost(String epost) {
		this.epost = epost;
	}

	public List<Resultat> getResultater() {
		return resultater;
	}

	public void setResultater(List<Resultat> resultater) {
		this.resultater = resultater;
	}

	public int getPurringer() {
		return purringer;
	}

	public void setPurringer(int purringer) {
		this.purringer = purringer;
	}
	
	
}
