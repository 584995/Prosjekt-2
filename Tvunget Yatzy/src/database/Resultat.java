package database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "yatzy", name = "resultat")
public class Resultat {
	
	@Id
	@GeneratedValue
	private int id;
	private boolean startet;
	private int tur;
	private String ferdig_dato;
	private String enere;
	private String toere;
	private String treere;
	private String firere;
	private String femmere;
	private String seksere;
	private String ett_par;
	private String to_par;
	private String tre_like;
	private String fire_like;
	private String liten_straight;
	private String stor_straight;
	private String hus;
	private String sjanse;
	private String yatzy;

	@ManyToMany(mappedBy = "resultater")
	List<Bruker> spillere;

	public Resultat() {
		startet = false;
		tur = 0;
		ferdig_dato = null;
		enere = "";
		toere = "";
		treere = "";
		firere = "";
		femmere = "";
		seksere = "";
		ett_par = "";
		to_par = "";
		tre_like = "";
		fire_like = "";
		liten_straight = "";
		stor_straight = "";
		hus = "";
		sjanse = "";
		yatzy = "";
	}

	public void leggTilKolonner(int antall) {
		for (int i = 0; i < antall; i++) {
			enere += "000000000000000";
			toere += "000000000000000";
			treere += "000000000000000";
			firere += "000000000000000";
			femmere += "000000000000000";
			seksere += "000000000000000";
			ett_par += "000000000000000";
			to_par += "000000000000000";
			tre_like += "000000000000000";
			fire_like += "000000000000000";
			liten_straight += "000000000000000";
			stor_straight += "000000000000000";
			hus += "000000000000000";
			sjanse += "000000000000000";
			yatzy += "000000000000000";
		}
	}

	public void fjernKolonne(int pos) {

		enere = fjernKolonnebit(enere, pos);
		toere = fjernKolonnebit(toere, pos);
		treere = fjernKolonnebit(treere, pos);
		firere = fjernKolonnebit(firere, pos);
		femmere = fjernKolonnebit(femmere, pos);
		seksere = fjernKolonnebit(seksere, pos);
		ett_par = fjernKolonnebit(ett_par, pos);
		to_par = fjernKolonnebit(to_par, pos);
		tre_like = fjernKolonnebit(tre_like, pos);
		fire_like = fjernKolonnebit(fire_like, pos);
		liten_straight = fjernKolonnebit(liten_straight, pos);
		stor_straight = fjernKolonnebit(stor_straight, pos);
		hus = fjernKolonnebit(hus, pos);
		sjanse = fjernKolonnebit(sjanse, pos);
		yatzy =  fjernKolonnebit(yatzy, pos);
		
	}

	private String fjernKolonnebit (String rad, int pos) {
		if (pos*15 == rad.length())
			return rad.substring(0, 0 + pos*15);
		return rad.substring(0, 0 + pos*15) + rad.substring(15 + pos*15);
	}
	
	public void leggTilSpillere (List<Bruker> spillere) {
		for (Bruker e : spillere)
			this.spillere.add(e);
	}
	
	public void fjernSpiller (Bruker spiller) {
		spillere.remove(spiller);
	}
	
	public List<String> lagListe () {
		List<String> liste = new ArrayList<String>();
		liste.add(enere);
		liste.add(toere);
		liste.add(treere);
		liste.add(firere);
		liste.add(femmere);
		liste.add(seksere);
		liste.add(ett_par);
		liste.add(to_par);
		liste.add(tre_like);
		liste.add(fire_like);
		liste.add(liten_straight);
		liste.add(stor_straight);
		liste.add(hus);
		liste.add(sjanse);
		liste.add(yatzy);
		return liste;
	}
	
	public void lagreKast(int rad, String kast) {
		
		if (rad == 0) 
			enere = enere.substring(0, tur * 5) + kast + enere.substring(tur * 5 + 5);
		else if (rad == 1) 
			toere = toere.substring(0, tur * 5) + kast + toere.substring(tur * 5 + 5);
		else if (rad == 2) 
			treere = treere.substring(0, tur * 5) + kast + treere.substring(tur * 5 + 5);
		else if (rad == 3) 
			firere = firere.substring(0, tur * 5) + kast + firere.substring(tur * 5 + 5);
		else if (rad == 4) 
			femmere = femmere.substring(0, tur * 5) + kast + femmere.substring(tur * 5 + 5);
		else if (rad == 5) 
			seksere = seksere.substring(0, tur * 5) + kast + seksere.substring(tur * 5 + 5);
		else if (rad == 6) 
			ett_par = ett_par.substring(0, tur * 5) + kast + ett_par.substring(tur * 5 + 5);
		else if (rad == 7) 
			to_par = to_par.substring(0, tur * 5) + kast + to_par.substring(tur * 5 + 5);
		else if (rad == 8) 
			tre_like = tre_like.substring(0, tur * 5) + kast + tre_like.substring(tur * 5 + 5);
		else if (rad == 9) 
			fire_like = fire_like.substring(0, tur * 5) + kast + fire_like.substring(tur * 5 + 5);
		else if (rad == 10) 
			liten_straight = liten_straight.substring(0, tur * 5) + kast + liten_straight.substring(tur * 5 + 5);
		else if (rad == 11) 
			stor_straight = stor_straight.substring(0, tur * 5) + kast + stor_straight.substring(tur * 5 + 5);
		else if (rad == 12) 
			hus = hus.substring(0, tur * 5) + kast + hus.substring(tur * 5 + 5);
		else if (rad == 13) 
			sjanse = sjanse.substring(0, tur * 5) + kast + sjanse.substring(tur * 5 + 5);
		else if (rad == 14) 
			yatzy = yatzy.substring(0, tur * 5) + kast + yatzy.substring(tur * 5 + 5);
		
	}
	
	public Integer getId() {
		return id;
	}

	public boolean isStartet() {
		return startet;
	}

	public void setStartet(boolean ferdig) {
		this.startet = ferdig;
	}

	public int getTur() {
		return tur;
	}

	public void setTur(int tur) {
		this.tur = tur;
	}

	public String getFerdig_dato() {
		return ferdig_dato;
	}

	public void setFerdig_dato(String ferdig_dato) {
		this.ferdig_dato = ferdig_dato;
	}

	public String getEnere() {
		return enere;
	}

	public void setEnere(String enere) {
		this.enere = enere;
	}

	public String getToere() {
		return toere;
	}

	public void setToere(String toere) {
		this.toere = toere;
	}

	public String getTreere() {
		return treere;
	}

	public void setTreere(String treere) {
		this.treere = treere;
	}

	public String getFirere() {
		return firere;
	}

	public void setFirere(String firere) {
		this.firere = firere;
	}

	public String getFemmere() {
		return femmere;
	}

	public void setFemmere(String femmere) {
		this.femmere = femmere;
	}

	public String getSeksere() {
		return seksere;
	}

	public void setSeksere(String seksere) {
		this.seksere = seksere;
	}

	public String getEtt_par() {
		return ett_par;
	}

	public void setEtt_par(String ett_par) {
		this.ett_par = ett_par;
	}

	public String getTo_par() {
		return to_par;
	}

	public void setTo_par(String to_par) {
		this.to_par = to_par;
	}

	public String getTre_like() {
		return tre_like;
	}

	public void setTre_like(String tre_like) {
		this.tre_like = tre_like;
	}

	public String getFire_like() {
		return fire_like;
	}

	public void setFire_like(String fire_like) {
		this.fire_like = fire_like;
	}

	public String getLiten_straight() {
		return liten_straight;
	}

	public void setLiten_straight(String liten_straight) {
		this.liten_straight = liten_straight;
	}

	public String getStor_straight() {
		return stor_straight;
	}

	public void setStor_straight(String stor_straight) {
		this.stor_straight = stor_straight;
	}

	public String getHus() {
		return hus;
	}

	public void setHus(String hus) {
		this.hus = hus;
	}

	public String getSjanse() {
		return sjanse;
	}

	public void setSjanse(String sjanse) {
		this.sjanse = sjanse;
	}

	public String getYatzy() {
		return yatzy;
	}

	public void setYatzy(String yatzy) {
		this.yatzy = yatzy;
	}

	public List<Bruker> getSpillere() {
		return spillere;
	}

	public void setSpillere(List<Bruker> spillere) {
		this.spillere = spillere;
	}


}
