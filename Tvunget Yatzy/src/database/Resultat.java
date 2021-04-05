package database;

import java.time.LocalDate;
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
	private int spiller_tur;
	private int kast_tur;
	private int runde;
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
		spiller_tur = 0;
		kast_tur = 0;
		runde = 1;
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
			enere += "00000000000000000";
			toere += "00000000000000000";
			treere += "00000000000000000";
			firere += "00000000000000000";
			femmere += "00000000000000000";
			seksere += "00000000000000000";
			ett_par += "00000000000000000";
			to_par += "00000000000000000";
			tre_like += "00000000000000000";
			fire_like += "00000000000000000";
			liten_straight += "00000000000000000";
			stor_straight += "00000000000000000";
			hus += "00000000000000000";
			sjanse += "00000000000000000";
			yatzy += "00000000000000000";
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
		yatzy = fjernKolonnebit(yatzy, pos);

	}

	private String fjernKolonnebit(String rad, int pos) {
		if (pos * 17 == rad.length())
			return rad.substring(0, 0 + pos * 17);
		return rad.substring(0, 0 + pos * 17) + rad.substring(17 + pos * 17);
	}	

	public List<String> lagListe() {
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

	public void leggTilSpillere(List<Bruker> spillere) {
		for (Bruker e : spillere)
			this.spillere.add(e);
	}

	public void fjernSpiller(Bruker spiller) {
		int pos = spillerPos(spiller);
		fjernKolonne(pos);
		spillere.remove(spiller);
		kast_tur = 0;
		if (spiller_tur == spillere.size()) {
			spiller_tur = 0;
			runde++;
			if (runde == 16) {
				ferdig_dato = LocalDate.now().getDayOfMonth() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getYear();
			}
		}
	}
	
	public int spillerPos (Bruker spiller) {
		int i = 0;
		for (Bruker b : spillere) {
			if (b.getBrukernavn().equals(spiller.getBrukernavn()))
				return i;
			i++;
		}
		return -1;
	}

	public void lagreKast(String kast) {
		int rad = 0;
		for (String str : lagListe()) {			
			if (spiller_tur != spillere.size() - 1) {
				String helRunde = str.substring(spiller_tur * 17, spiller_tur * 17 + 17);
				if (helRunde.contains("00000")) 
					break;					
			} else {
				String helRunde = str.substring(spiller_tur * 17);
				if (helRunde.contains("00000")) 
					break;			
			}
			rad++;
		}
		
		if (rad == 0)
			enere = lagreHjelp(enere, kast);
		else if (rad == 1)
			toere = lagreHjelp(toere, kast);
		else if (rad == 2)
			treere = lagreHjelp(treere, kast);
		else if (rad == 3)
			firere = lagreHjelp(firere, kast);
		else if (rad == 4)
			femmere = lagreHjelp(femmere, kast);
		else if (rad == 5)
			seksere = lagreHjelp(seksere, kast);
		else if (rad == 6)
			ett_par = lagreHjelp(ett_par, kast);
		else if (rad == 7)
			to_par = lagreHjelp(to_par, kast);
		else if (rad == 8)
			tre_like = lagreHjelp(tre_like, kast);
		else if (rad == 9)
			fire_like = lagreHjelp(fire_like, kast);
		else if (rad == 10)
			liten_straight = lagreHjelp(liten_straight, kast);
		else if (rad == 11)
			stor_straight = lagreHjelp(stor_straight, kast);
		else if (rad == 12)
			hus = lagreHjelp(hus, kast);
		else if (rad == 13)
			sjanse = lagreHjelp(sjanse, kast);
		else if (rad == 14)
			yatzy = lagreHjelp(yatzy, kast);

	}

	private String lagreHjelp(String rad, String kast) {
		if (kast_tur == 0) {
			return rad.substring(0, (spiller_tur * 17) + (kast_tur * 5)) + String.format("%02d", runde) + kast
					+ rad.substring(7 + (spiller_tur * 17) + (kast_tur * 5));
		} else {
			return rad.substring(0, 2 + (spiller_tur * 17) + (kast_tur * 5)) + kast
					+ rad.substring(7 + (spiller_tur * 17) + (kast_tur * 5));
		}	
	}
	
	public void nesteKast() {
		kast_tur++;
		if (kast_tur == 3)
			nesteSpiller();
	}
	
	public void nesteSpiller() {
		spiller_tur++;
		kast_tur = 0;
		if (spiller_tur == spillere.size())
			nesteRunde();
	}
	
	public void nesteRunde() {
		runde++;
		spiller_tur = 0;
		if (runde == 16) {
			ferdig_dato = LocalDate.now().getDayOfMonth() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getYear();
		}
	}

	public Integer getId() {
		return id;
	}

	public boolean isStartet() {
		return startet;
	}
	
	public int getSpiller_tur() {
		return spiller_tur;
	}

	public int getKast_tur() {
		return kast_tur;
	}

	public int getRunde() {
		return runde;
	}

	public String getFerdig_dato() {
		return ferdig_dato;
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
