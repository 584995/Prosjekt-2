package yatzy;

import java.util.ArrayList;
import java.util.List;

public class Terningkast {
	private Terning terning;
	
	public Terningkast () {
		terning = new Terning();
	}
	
	public List<Integer> kast(int antall) {
		List<Integer> terningkast = new ArrayList<Integer>();
		for (int i = 0; i < antall; i++)
			terningkast.add(terning.trill());
		return terningkast;
	}
	

}
