package model;

import java.util.Random;

// DUMMY IA

// Connait carte à 100% -> jouer
// Sinon, donner indice s'il y a des jetons restants
// Sinon defausser
        // s'il sait qu'il peut defausser une carte (carte déjà posée et connu),
        // sinon cartes sans indices,
        // sinon aléatoire

public class JoueurIA extends Joueur {

	public JoueurIA(String nom,int nbCartes, Partie p){
		super(nom,nbCartes, p);
	}

    // A REFAIRE
    // Choisi la carte à joueur, soit une 'obvious coup', soit une carte aléatoire
	public Carte choisirCarteAJouer (Partie p) {
        Carte c = obviousCoup(p);
        if (c!=null) {
            return c;
        }
        else {
            Random r = new Random();
            try {
                return this.getMain().getCarte(r.nextInt(this.getMain().nbCartes));
            }
            catch (EnleverCarteInexistanteException e){
                e.printStackTrace();
            }

        }
        return null;
	}

    // Regarde s'il y a une carte qu'elle connait qu'elle peut jouer
    public Carte obviousCoup (Partie p) {
        for (Carte c : this.getMain().main){
            if (c.isCouleurConnue() && c.isValeurConnue()) {
                for (int i=0; i<p.cartesJouees.size(); i++) {
                    if (p.cartesJouees.get(c.getCouleur()).size()+1==(c.getValeur()-1) && !(p.cartesJouees.get(c.getCouleur()).size()+1==(c.getValeur()))){
                        return c;
                    }
                }
            }
        }
        return null;
    }
}
