
import java.util.HashMap;
import java.util.Collection;
import java.util.Set;

public class Etat {

	HashMap<Character, EnsEtat> transitions;
	boolean init;
	boolean term;
	int id;

	public Etat() {
		this.transitions = new HashMap<Character, EnsEtat>();
	}

	public Etat(int id) {
		this.transitions = new HashMap<Character, EnsEtat>();
		this.id = id;
	}

	public Etat(boolean init, boolean term, int id) {
		this.transitions = new HashMap<Character, EnsEtat>();
		this.init = init;
		this.term = term;
		this.id = id;
	}

	public Etat(boolean estInit, boolean estTerm) {
		this.init = estInit;
		this.term = estTerm;
		this.transitions = new HashMap<Character, EnsEtat>();
	}

	public boolean isInit() {
		return init;
	}

	public boolean isTerm() {
		return term;
	}

	public void setInit(boolean init) {
		this.init = init;
	}

	public void setTerm(boolean term) {
		this.term = term;
	}

	public EnsEtat succ(char c){
		if(this.transitions.containsKey(c))		
			return this.transitions.get(c);
		return null;
	}

	public EnsEtat succ(){
		if(this.transitions.isEmpty()){
			EnsEtat es = new EnsEtat();
			Collection<EnsEtat> values = this.transitions.values();
			for(EnsEtat e: values){
				es.addAll(e);
			}
			return es;
		}
		return null;
	}
	
	public void ajouteTransition(char c, Etat e){
		EnsEtat es=this.succ(c);
		if(es!=null){
			es.add(e);
		}else{
			es = new EnsEtat();
			es.add(e);
			this.transitions.put(c,es);
		}			
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		} else {
			final Etat other = (Etat) obj;
			return (id == other.id);
		}
	}
    
    public Set<Character> alphabet(){
        return this.transitions.keySet();
    }

    public String toString(){
        String s="";
        Set<Character> sc=this.transitions.keySet();
        for(Character c: sc){
            s+=c+" : "+succ(c).toString();
        }
        return s;
    }




}
