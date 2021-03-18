package database;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
	@JoinTable(
			  name = "bruker_resultat", 
			  joinColumns = @JoinColumn(name = "brukernavn"), 
			  inverseJoinColumns = @JoinColumn(name = "id"))
	private List<Resultat> resultater;	
	
	public Bruker() {}
	
	public Bruker(String brukernavn, String passord, int mobil, String epost) {
		this.brukernavn = brukernavn;
		this.passord = passord;
		this.mobil = mobil;
		this.epost = epost;
		purringer = 0;
	}
	
	public void leggTilResultat (Resultat resultat) {
		resultater.add(resultat);
	}
	
	public void fjernResultat (Resultat resultat) {
		resultater.remove(resultat);
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
