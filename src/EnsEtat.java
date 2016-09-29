
import java.util.HashSet;
import java.util.Set;

public class EnsEtat extends HashSet<Etat> {

	public EnsEtat() {
	}
    
    public String toString(){
        String s="{";
        for(Etat e: this){
            s+=e.hashCode()+", ";
        }
        s = s.substring(0, s.length()-2);
        s+="}\n";
        return s;
    }
    
    public EnsEtat succ(char c){
        EnsEtat ret=new EnsEtat();
        for(Etat e: this){
            if(e.transitions.get(c)!=null)
                ret.addAll(e.transitions.get(c));
        }
        return ret;
    }
    
    public EnsEtat succ(){
        EnsEtat es=new EnsEtat();
        for(Etat e:this){
            Set<Character> sc=e.transitions.keySet();
            for(char c:sc){
                EnsEtat tmp=e.succ(c);
                if(tmp!=null)
                    es.addAll(tmp);
            }
        }
        return es;
    }
    
    public boolean contientTerminal(){
        for(Etat e: this){
            if(e.isTerm()){
                return true;
            }
        }
        return false;
    }
    
    public boolean accepte(String s, int i){
        if(i>=s.length()){
            return contientTerminal();
        }
        EnsEtat es=this.succ(s.charAt(i));
        if(es==null || es.size()==0){
            return false;
        }
        return es.accepte(s,i+1);
    }
    
    public Set<Character> alphabet(){
        Set<Character> sc=new HashSet<Character>();
        for(Etat e:this){
            sc.addAll(e.alphabet());
        }
        return sc;
    }
}
