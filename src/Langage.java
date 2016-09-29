
import java.util.Set;
import java.util.HashSet;

class Langage {

	Set<String> mots;

	Langage() {
		this.mots = new HashSet<String>();
	}

	Langage(String chaines[]) {
		this();
		for (int i = 0; i < chaines.length; i++) {
			ajoute(chaines[i]);
		}
	}

	public void ajoute(String u) {
	    this.mots.add(u);
	}

	public Langage inter(Langage L) {
	    Langage N = new Langage();
	    for(String s : this.mots){
		if(L.mots.contains(s)){
		    N.ajoute(s);
		}
	    }
	    return N;
	}

	public Langage union(Langage L) {
	    Langage N = new Langage();
	    N.mots.addAll(this.mots);
	    N.mots.addAll(L.mots);
	    return N;
	}

	public Langage concat(Langage L) {
	    Langage N = new Langage();
	    for(String s : this.mots){
		for(String t : L.mots){
		    N.ajoute(s+t);
		}
	    }
	    return N;
	}

	public Langage difference(Langage L) {
	    Langage N = new Langage();
	    for(String s : this.mots){
		if(!L.mots.contains(s)){
		    N.ajoute(s);
		}
	    }
	    return N;
	}

	public static String miroirMot(String u) {
	    String s = "";
	    for(int i = u.length()-1; i>=0; i--){
		s += ""+u.charAt(i);
	    return s;
	}

	public Langage miroir() {
	    Langage N = new Langage();
	    for(String s : this.mots){
		N.ajoute(this.miroirMot(s));
	    }
	    return N;
	}

	public String toString() {
		String res = "{ ";
		for (String u : mots) {
			if (u.length() == 0) {
				res += "mot_vide ";
			} else {
				res += u.toString() + " ";
			}
		}
		return res + "}";
	}

	public static void main(String args[]) {
		Langage L = new Langage(args);
		Langage M = new Langage();
		L.ajoute("abba");
		L.ajoute("a");
		L.ajoute("ab");
		M.ajoute("baab");
		M.ajoute("a");
		M.ajoute("ab");
		L = L.difference(M);
		System.out.println(L);
// tester chacune des methodes au fur et a mesure que vous les ecrivez
	}
}
