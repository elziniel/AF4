import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Stack;


public class Automate extends EnsEtat {

	private EnsEtat initiaux;

	public Automate() {
		super();
        	initiaux = new EnsEtat();
    	}

	public EnsEtat getInitiaux() {
	        return initiaux;
    	}

	public boolean ajouteEtatSeul(Etat e){
		if(this.contains(e))
			return false;
		this.add(e);
		if(e.isInit())
			initiaux.add(e);
		return true;
	}

	public boolean ajouteEtatRecursif(Etat e){
		boolean b=ajouteEtatSeul(e);		
		if(!b)
			return false;
		EnsEtat s = e.succ();
        for(Etat et: s)
            ajouteEtatRecursif(et);
		return true;
	}			
	
	public boolean estDeterministe(){
		Iterator<Etat> it = this.iterator();
		while(it.hasNext()){
			Etat e=it.next();
			Iterator<Character> keys=e.transitions.keySet().iterator();
			while(keys.hasNext()){
				if(e.succ(keys.next()).size()>1)
					return false;
			}
		}
		if(initiaux.size()>=2)
			return false;
		return true;	
			 
	}

	public String toString(){
		String s= "Nombre d'etats "+this.size()+"\n";
		for(Etat e: this){
            s+="Etat "+e.hashCode()+" : \n"+e.toString();
        }
        return s;
	}
    
    public Automate determinise(){
        Automate Adet=new Automate();
        HashMap<EnsEtat,Etat> hmdet=new HashMap<EnsEtat,Etat>();
        Stack<EnsEtat> stackdet=new Stack<EnsEtat>();
        HashSet<EnsEtat> setdet=new HashSet<EnsEtat>();
        Etat init=new Etat(true,false);
        hmdet.put(this.initiaux,init);
        setdet.add(this.initiaux);
        stackdet.push(this.initiaux);
        int i=1;
        while(stackdet.isEmpty()==false){
            EnsEtat eetmp=stackdet.pop(), tmp=new EnsEtat();
            Set<Character> ab=eetmp.alphabet();
            for(char c:ab){
                tmp=eetmp.succ(c);
                if(!setdet.contains(eetmp.succ(c))){
                    Etat etatint;
                    if(tmp.contientTerminal()){
                        etatint=new Etat(false,true,i);
                    }else{
                        etatint=new Etat(false,false,i);
                    }
                    Etat transit=hmdet.get(eetmp);
                    transit.ajouteTransition(c,etatint);
                    Adet.ajouteEtatSeul(etatint);
                    hmdet.put(tmp,etatint);
                    setdet.add(tmp);
                    stackdet.push(tmp);
                    i++;
                }else{
                    Etat e0=hmdet.get(eetmp);
                    Etat e1=hmdet.get(eetmp.succ(c));
                    e0.ajouteTransition(c,e1);
                }
            }
        }
        Adet.ajouteEtatSeul(init);
        
        return Adet;
    }
    
    public static void main(String[] args){
        Automate A=new Automate();
        Etat e0 = new Etat(true,false,0);
        Etat e1 = new Etat(false,true,1);
        Etat e2 = new Etat(false,true,2);
        e0.ajouteTransition('a',e1);
        e0.ajouteTransition('b',e2);
        e1.ajouteTransition('a',e2);
        e1.ajouteTransition('b',e0);
        e2.ajouteTransition('a',e0);
        e2.ajouteTransition('b',e1);

        A.ajouteEtatSeul(e0);
        A.ajouteEtatSeul(e1);
        A.ajouteEtatSeul(e2);
        Automate Adet=A.determinise();
        System.out.println(Adet.toString());
    }
}

