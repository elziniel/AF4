public class Unaire extends Arbre {
    Arbre fils;

    public Unaire(Arbre fils){
		this.fils = fils;
		this.symbole = '*';
    }

    public String toString(){
        return "("+this.fils.toString()+")*";
    }
}
