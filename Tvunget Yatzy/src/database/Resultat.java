package database;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<List<String>> poengTabell() {
		List<List<String>> poengTabell = new ArrayList<List<String>>();
		List<String> poengKolonne = new ArrayList<String>();
		poengKolonne.add("Deltagere");
		poengKolonne.add("Enere");
		poengKolonne.add("Toere");
		poengKolonne.add("Treere");
		poengKolonne.add("Firere");
		poengKolonne.add("Femmere");
		poengKolonne.add("Seksere");
		poengKolonne.add("Sum");
		poengKolonne.add("Bonus");
		poengKolonne.add("Ett Par");
		poengKolonne.add("To Par");
		poengKolonne.add("Tre Like");
		poengKolonne.add("Fire Like");
		poengKolonne.add("Liten Straight");
		poengKolonne.add("Stor Straight");
		poengKolonne.add("Hus");
		poengKolonne.add("Sjanse");
		poengKolonne.add("Yatzy");
		poengKolonne.add("Totalt");
		poengTabell.add(poengKolonne);
		for (Bruker spiller : spillere) {
			poengKolonne = new ArrayList<String>();
			poengKolonne.add(spiller.getBrukernavn());
			int spillerPos = spillerPos(spiller);
			for (int poeng : regnEnereTilSeksere(spillerPos)) {
				if (poeng == -1)
					poengKolonne.add("");
				else
					poengKolonne.add("" + poeng);
			}
			poengKolonne.add("" + regnSum(spillerPos));
			poengKolonne.add("" + regnBonus(spillerPos));
			for (int poeng : regnEttParTilYatzy(spillerPos)) {
				if (poeng == -1)
					poengKolonne.add("");
				else
					poengKolonne.add("" + poeng);
			}
			poengKolonne.add("" + regnTotalt(spillerPos));
			poengTabell.add(poengKolonne);
		}
		return poengTabell;
	}
	
	private List<Integer> regnEnereTilSeksere (int spillerPos) {
		List<Integer> regnetEnereTilSeksere = new ArrayList<Integer>();
		regnetEnereTilSeksere.add(regnEnere(spillerPos));
		regnetEnereTilSeksere.add(regnToere(spillerPos));
		regnetEnereTilSeksere.add(regnTreere(spillerPos));
		regnetEnereTilSeksere.add(regnFirere(spillerPos));
		regnetEnereTilSeksere.add(regnFemmere(spillerPos));
		regnetEnereTilSeksere.add(regnSeksere(spillerPos));
		return regnetEnereTilSeksere;
	}
	
	private int regnEnere (int spillerPos) {
		int poeng = 0;
		String sisteKast = "";
		if (spillerPos != spillere.size())
			sisteKast = enere.substring(12 + 17 * spillerPos, 17 + 17 * spillerPos);
		else
			sisteKast = enere.substring(12 + 17 * spillerPos);
		if (sisteKast.equals("00000"))
			return -1;
		for (int i = 0; i < 4; i++)
			if (sisteKast.substring(i, i+1).contains("1"))
				poeng += 1;
		if (sisteKast.substring(4).contains("1"))
			poeng += 1;
		return poeng;
	}
	
	private int regnToere (int spillerPos) {
		int poeng = 0;
		String sisteKast = "";
		if (spillerPos != spillere.size())
			sisteKast = toere.substring(12 + 17 * spillerPos, 17 + 17 * spillerPos);
		else
			sisteKast = toere.substring(12 + 17 * spillerPos);
		if (sisteKast.equals("00000"))
			return -1;
		for (int i = 0; i < 4; i++)
			if (sisteKast.substring(i, i+1).contains("2"))
				poeng += 2;
		if (sisteKast.substring(4).contains("2"))
			poeng += 2;
		return poeng;
	}
	
	private int regnTreere (int spillerPos) {
		int poeng = 0;
		String sisteKast = "";
		if (spillerPos != spillere.size())
			sisteKast = treere.substring(12 + 17 * spillerPos, 17 + 17 * spillerPos);
		else
			sisteKast = treere.substring(12 + 17 * spillerPos);
		if (sisteKast.equals("00000"))
			return -1;
		for (int i = 0; i < 4; i++)
			if (sisteKast.substring(i, i+1).contains("3"))
				poeng += 3;
		if (sisteKast.substring(4).contains("3"))
			poeng += 3;
		return poeng;
	}
	
	private int regnFirere (int spillerPos) {
		int poeng = 0;
		String sisteKast = "";
		if (spillerPos != spillere.size())
			sisteKast = firere.substring(12 + 17 * spillerPos, 17 + 17 * spillerPos);
		else
			sisteKast = firere.substring(12 + 17 * spillerPos);
		if (sisteKast.equals("00000"))
			return -1;
		for (int i = 0; i < 4; i++)
			if (sisteKast.substring(i, i+1).contains("4"))
				poeng += 4;
		if (sisteKast.substring(4).contains("4"))
			poeng += 4;
		return poeng;
	}
	
	private int regnFemmere (int spillerPos) {
		int poeng = 0;
		String sisteKast = "";
		if (spillerPos != spillere.size())
			sisteKast = femmere.substring(12 + 17 * spillerPos, 17 + 17 * spillerPos);
		else
			sisteKast = femmere.substring(12 + 17 * spillerPos);
		if (sisteKast.equals("00000"))
			return -1;
		for (int i = 0; i < 4; i++)
			if (sisteKast.substring(i, i+1).contains("5"))
				poeng += 5;
		if (sisteKast.substring(4).contains("5"))
			poeng += 5;
		return poeng;
	}
	
	private int regnSeksere (int spillerPos) {
		int poeng = 0;
		String sisteKast = "";
		if (spillerPos != spillere.size())
			sisteKast = enere.substring(12 + 17 * spillerPos, 17 + 17 * spillerPos);
		else
			sisteKast = enere.substring(12 + 17 * spillerPos);
		if (sisteKast.equals("00000"))
			return -1;
		for (int i = 0; i < 4; i++)
			if (sisteKast.substring(i, i+1).contains("6"))
				poeng += 6;
		if (sisteKast.substring(4).contains("6"))
			poeng += 6;
		return poeng;
	}
	
	private int regnSum (int spillerPos) {
		int sum = 0;
		for (int poeng : regnEnereTilSeksere(spillerPos))
			sum += poeng;
		return sum;
	}
	
	private int regnBonus (int spillerPos) {
		int sum = regnSum(spillerPos);
		if (sum >= 63)
			return 50;
		return 0;
	}
	
	private List<Integer> regnEttParTilYatzy (int spillerPos) {
		List<Integer> regnetEttParTilYatzy = new ArrayList<Integer>();
		regnetEttParTilYatzy.add(regnEttPar(spillerPos));
		regnetEttParTilYatzy.add(regnToPar(spillerPos));
		regnetEttParTilYatzy.add(regnTreLike(spillerPos));
		regnetEttParTilYatzy.add(regnFireLike(spillerPos));
		regnetEttParTilYatzy.add(regnLitenStraight(spillerPos));
		regnetEttParTilYatzy.add(regnStorStraight(spillerPos));
		regnetEttParTilYatzy.add(regnHus(spillerPos));
		regnetEttParTilYatzy.add(regnSjanse(spillerPos));
		regnetEttParTilYatzy.add(regnYatzy(spillerPos));
		return regnetEttParTilYatzy;
	}
	
	private int regnEttPar (int spillerPos) {
		int poeng = 0;
		String sisteKast = "";
		if (spillerPos != spillere.size())
			sisteKast = ett_par.substring(12 + 17 * spillerPos, 17 + 17 * spillerPos);
		else
			sisteKast = ett_par.substring(12 + 17 * spillerPos);
		if (sisteKast.equals("00000"))
			return -1;
		List<String> liste = new ArrayList<String>();
		for (int i = 0; i < 4; i++)
			liste.add(sisteKast.substring(i, i+1));
		liste.add(sisteKast.substring(4));
		int i = 6;
		while (i > 0) {
			final int j = i;
			List<String> testeListe = liste.stream()
			.filter(a -> a.contains("" + j))
			.collect(Collectors.toList());
			if (testeListe.size() > 1) {
				poeng += i * 2;
				break;
			}
			i--;
		}
		return poeng;
	}
	
	private int regnToPar (int spillerPos) {
		int poeng = 0;
		boolean toParSjekk = false;
		String sisteKast = "";
		if (spillerPos != spillere.size())
			sisteKast = to_par.substring(12 + 17 * spillerPos, 17 + 17 * spillerPos);
		else
			sisteKast = to_par.substring(12 + 17 * spillerPos);
		if (sisteKast.equals("00000"))
			return -1;
		List<String> liste = new ArrayList<String>();
		for (int i = 0; i < 4; i++)
			liste.add(sisteKast.substring(i, i+1));
		liste.add(sisteKast.substring(4));
		int i = 6;
		while (i > 0) {
			final int j = i;
			List<String> testeListe = liste.stream()
			.filter(a -> a.contains("" + j))
			.collect(Collectors.toList());
			if (testeListe.size() > 1) {
				poeng += i * 2;
				i--;
				break;
			}
			i--;
		}
		while (i > 0) {
			final int j = i;
			List<String> testeListe = liste.stream()
			.filter(a -> a.contains("" + j))
			.collect(Collectors.toList());
			if (testeListe.size() > 1) {
				poeng += i * 2;
				toParSjekk = true;
				break;
			}
			i--;
		}
		if (toParSjekk)
			return poeng;
		return 0;
	}
	
	private int regnTreLike (int spillerPos) {
		int poeng = 0;
		String sisteKast = "";
		if (spillerPos != spillere.size())
			sisteKast = tre_like.substring(12 + 17 * spillerPos, 17 + 17 * spillerPos);
		else
			sisteKast = tre_like.substring(12 + 17 * spillerPos);
		if (sisteKast.equals("00000"))
			return -1;
		List<String> liste = new ArrayList<String>();
		for (int i = 0; i < 4; i++)
			liste.add(sisteKast.substring(i, i+1));
		liste.add(sisteKast.substring(4));
		int i = 6;
		while (i > 0) {
			final int j = i;
			List<String> testeListe = liste.stream()
			.filter(a -> a.contains("" + j))
			.collect(Collectors.toList());
			if (testeListe.size() > 2) {
				poeng += i * 3;
				break;
			}
			i--;
		}
		return poeng;
	}
	
	private int regnFireLike (int spillerPos) {
		int poeng = 0;
		String sisteKast = "";
		if (spillerPos != spillere.size())
			sisteKast = fire_like.substring(12 + 17 * spillerPos, 17 + 17 * spillerPos);
		else
			sisteKast = fire_like.substring(12 + 17 * spillerPos);
		if (sisteKast.equals("00000"))
			return -1;
		List<String> liste = new ArrayList<String>();
		for (int i = 0; i < 4; i++)
			liste.add(sisteKast.substring(i, i+1));
		liste.add(sisteKast.substring(4));
		int i = 6;
		while (i > 0) {
			final int j = i;
			List<String> testeListe = liste.stream()
			.filter(a -> a.contains("" + j))
			.collect(Collectors.toList());
			if (testeListe.size() > 3) {
				poeng += i * 4;
				break;
			}
			i--;
		}
		return poeng;
	}
	
	private int regnLitenStraight (int spillerPos) {
		String sisteKast = "";
		if (spillerPos != spillere.size())
			sisteKast = liten_straight.substring(12 + 17 * spillerPos, 17 + 17 * spillerPos);
		else
			sisteKast = liten_straight.substring(12 + 17 * spillerPos);
		if (sisteKast.equals("00000"))
			return -1;
		if (sisteKast.contains("1"))
			if (sisteKast.contains("2"))
				if (sisteKast.contains("3"))
					if (sisteKast.contains("4"))
						if (sisteKast.contains("5"))
							return 15;
		return 0;
	}
	
	private int regnStorStraight (int spillerPos) {
		String sisteKast = "";
		if (spillerPos != spillere.size())
			sisteKast = stor_straight.substring(12 + 17 * spillerPos, 17 + 17 * spillerPos);
		else
			sisteKast = stor_straight.substring(12 + 17 * spillerPos);
		if (sisteKast.equals("00000"))
			return -1;
		if (sisteKast.contains("2"))
			if (sisteKast.contains("3"))
				if (sisteKast.contains("4"))
					if (sisteKast.contains("5"))
						if (sisteKast.contains("6"))
							return 20;
		return 0;
	}
	
	private int regnHus (int spillerPos) {
		int poeng = 0;
		boolean husSjekk = false;
		int hjelpetall = 0;
		String sisteKast = "";
		if (spillerPos != spillere.size())
			sisteKast = hus.substring(12 + 17 * spillerPos, 17 + 17 * spillerPos);
		else
			sisteKast = hus.substring(12 + 17 * spillerPos);
		if (sisteKast.equals("00000"))
			return -1;
		List<String> liste = new ArrayList<String>();
		for (int i = 0; i < 4; i++)
			liste.add(sisteKast.substring(i, i+1));
		liste.add(sisteKast.substring(4));
		int i = 6;
		while (i > 0) {
			final int j = i;
			List<String> testeListe = liste.stream()
			.filter(a -> a.contains("" + j))
			.collect(Collectors.toList());
			if (testeListe.size() > 2) {
				poeng += i * 3;
				hjelpetall = 2;
				i--;
				break;
			} else if (testeListe.size() > 1) {
				poeng += i * 2;
				hjelpetall = 3;
				i--;
				break;
			}
			i--;
		}
		while (i > 0) {
			final int j = i;
			List<String> testeListe = liste.stream()
			.filter(a -> a.contains("" + j))
			.collect(Collectors.toList());
			if (testeListe.size() > hjelpetall) {
				poeng += i * hjelpetall;
				husSjekk = true;
				break;
			}
			i--;
		}
		if (husSjekk)
			return poeng;
		return 0;
	}
	
	private int regnSjanse (int spillerPos) {
		int poeng = 0;
		String sisteKast = "";
		if (spillerPos != spillere.size())
			sisteKast = sjanse.substring(12 + 17 * spillerPos, 17 + 17 * spillerPos);
		else
			sisteKast = sjanse.substring(12 + 17 * spillerPos);
		if (sisteKast.equals("00000"))
			return -1;
		for (int i = 0; i < 4; i++)
			poeng += Integer.parseInt(sisteKast.substring(i, i+1));
		poeng += Integer.parseInt(sisteKast.substring(4));
		return poeng;
	}
	
	private int regnYatzy (int spillerPos) {
		boolean yatzySjekk = false;
		String sisteKast = "";
		if (spillerPos != spillere.size())
			sisteKast = yatzy.substring(12 + 17 * spillerPos, 17 + 17 * spillerPos);
		else
			sisteKast = yatzy.substring(12 + 17 * spillerPos);
		if (sisteKast.equals("00000"))
			return -1;
		List<String> liste = new ArrayList<String>();
		for (int i = 0; i < 4; i++)
			liste.add(sisteKast.substring(i, i+1));
		liste.add(sisteKast.substring(4));
		int i = 6;
		while (i > 0) {
			final int j = i;
			List<String> testeListe = liste.stream()
			.filter(a -> a.contains("" + j))
			.collect(Collectors.toList());
			if (testeListe.size() > 4) {
				yatzySjekk = true;
				break;
			}
			i--;
		}
		if (yatzySjekk)
			return 50;
		return 0;
	}
	
	private int regnTotalt (int spillerPos) {
		return regnEnere(spillerPos) + regnToere(spillerPos) + regnTreere(spillerPos) + regnFirere(spillerPos) + regnFemmere(spillerPos) + 
				regnSeksere(spillerPos) + regnEttPar(spillerPos) + regnToPar(spillerPos) + regnTreLike(spillerPos) + regnFireLike(spillerPos) + 
				regnLitenStraight(spillerPos) + regnStorStraight(spillerPos) + regnHus(spillerPos) + regnSjanse(spillerPos) + regnYatzy(spillerPos);
	}

	public int getId() {
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
