import java.util.Stack;

public abstract class Arbre {
    char symbole;
	boolean contientMotVide;

	public static Arbre lirePostfixe(String expr){
		Stack<Arbre> a = new Stack<Arbre>();
		for(int i = 0; i < expr.length(); i++){
			if((int)expr.charAt(i) > 96 && (int)expr.charAt(i) < 123){
				a.add(new Feuille(expr.charAt(i)));
			}
			if(expr.charAt(i) == '+' || expr.charAt(i) == '.'){
				a.add(new Binaire(a.get(a.size()-2), a.get(a.size()-1), expr.charAt(i)));
			}
			if(expr.charAt(i) == '*'){
				a.add(new Unaire(a.get(a.size()-1)));
			}
		}
		return a.peek();
	}
}
