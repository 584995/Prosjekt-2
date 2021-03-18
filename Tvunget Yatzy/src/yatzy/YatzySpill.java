package yatzy;

import java.util.List;

import database.Bruker;
import database.Resultat;

public class YatzySpill {
	private List<Bruker> spillere;
	private Resultat resultat;
	private Terningkast terningkast;
	
	public YatzySpill (List<Bruker> spillere) {
		this.spillere = spillere;
		resultat = new Resultat();
		resultat.leggTilKolonner(spillere.size());
		resultat.leggTilSpillere(spillere);
		for (Bruker e : spillere)
			e.leggTilResultat(resultat);
		terningkast = new Terningkast();
	}
	
	public void spillRunde() {
		
	}
	public void sendStartBeskjed() {
		
	}
	public void sendPurring(Bruker spiller) {
		
	}
	public void kastUtSpiller(Bruker spiller) {
		
	}
}
