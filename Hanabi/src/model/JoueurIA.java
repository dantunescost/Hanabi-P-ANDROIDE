package model;

public class JoueurIA extends Joueur {

	/**
	 * Constructeur d'un joueur IA standard
	 * @param nom		Le nom associe au joueur
	 * @param nbCartes	Le nombre de cartes que doit contenir sa main (definit par le nombre de joueurs dans la partie)
	 * @param p			La partie a laquelle participe cette IA
	 * @param id		Le numero du joueur dans la partie
	 */
	public JoueurIA(String nom,int nbCartes, Partie p, int id){
		super(nom,nbCartes, p, id);
	}

	/**
	 * Permet a l'IA de savoir si une carte de sa main est connue et jouable dans la situation actuelle
	 * @return	La carte que l'IA peut jouer si elle existe
	 * 			null sinon
	 */
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

    /**
	 * Permet a l'IA de savoir si une carte de sa main est connue et defaussable (carte deja joue) dans la situation actuelle
	 * @return	La carte que l'IA peut defausser si elle existe
	 * 			null sinon
	 */
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
