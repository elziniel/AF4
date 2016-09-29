public class Binaire extends Arbre {
    Arbre gauche;
    Arbre droit;

    public Binaire(Arbre gauche, Arbre droit, char symbole){
		this.gauche = gauche;
		this.droit = droit;
		this.symbole = symbole;
    }

    public String toString(){
		if(this.symbole == '+'){
			return "("+this.gauche.toString()+"+"+this.droit.toString()+")";
		}
		return "("+this.gauche.toString()+this.droit.toString()+")";
    }
}
