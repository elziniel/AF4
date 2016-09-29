public class Feuille extends Arbre{
    public Feuille(char symbole){
		this.symbole = symbole;
    }

    public String toString(){
        return ""+this.symbole;
    }
}
