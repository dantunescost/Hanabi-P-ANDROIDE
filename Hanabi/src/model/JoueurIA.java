package model;

public class JoueurIA extends Joueur {

	public JoueurIA(String nom,int nbCartes, Partie p, int id){
		super(nom,nbCartes, p, id);
	}

    // Regarde s'il y a une carte qu'elle connait qu'elle peut jouer
    public Carte obviousCoup (Partie p) {
        for (Carte c : this.getMain().main){
            if (c.isCouleurConnue() && c.isValeurConnue()) {
                for (int i=0; i<p.cartesJouees.size(); i++) {
                    if (p.cartesJouees.get(c.getCouleur()).size()==(c.getValeur()-1)/* && !(p.cartesJouees.get(c.getCouleur()).size()+1==(c.getValeur())))*/){
                        return c;
                    }
                }
            }
        }
        return null;
    }


    public Carte obviousDefaussable (Partie p) {
        for (Carte c : this.getMain().main){
            if (c.isCouleurConnue() && c.isValeurConnue()) {
                for (int i=0; i<p.cartesJouees.size(); i++) {
                    if (p.cartesJouees.get(c.getCouleur()).size()>=(c.getValeur())){
                        return c;
                    }
                }
            }
        }
        return null;
    }
}
