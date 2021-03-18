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
	
	@ManyToMany
	@JoinTable(
			  name = "bruker_resultat", 
			  joinColumns = @JoinColumn(name = "brukernavn"), 
			  inverseJoinColumns = @JoinColumn(name = "id"))
	private List<Resultat> resultater;

	public Bruker () {
		
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
	
	
}
