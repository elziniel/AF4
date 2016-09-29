public class Main {
    public static void main(String[] args){
		Feuille g = new Feuille('a');
		Feuille d = new Feuille('b');
		Binaire b = new Binaire(g, d, '+');
		Unaire u = new Unaire(b);
		g = new Feuille('b');
		d = new Feuille('c');
		Binaire b2 = new Binaire(g, d, '.');
		Binaire b3 = new Binaire(u, b2, '.');

		System.out.println(b3.toString());
		System.out.println(Arbre.lirePostfixe("ab+*b.c").toString());
    }
}
