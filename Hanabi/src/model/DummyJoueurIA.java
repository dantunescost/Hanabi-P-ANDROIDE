package model;


// DUMMY IA

import java.util.Random;

// Connait carte à 100% -> jouer
// Sinon, donner indice s'il y a des jetons restants
// Sinon defausser
    // s'il sait qu'il peut defausser une carte (carte déjà posée et connu),
    // sinon cartes sans indices,
    // sinon aléatoire
public class DummyJoueurIA extends JoueurIA {

    public DummyJoueurIA(String nom, int nbCartes, Partie p, int id) {
        super(nom, nbCartes, p, id);
    }

    public void quoiFaire() {
        Carte c = this.obviousCoup(p);
        Carte defaussable = this.obviousDefaussable(p);

        if (c != null) {
            // ************************* JOUER LA CARTE *******************************
            int j = this.getMain().getIndex(c);

            try {
                p.joueCarte(this, j);
            } catch (EnleverCarteInexistanteException e) {
                e.printStackTrace();
            } catch (PartiePerdueException e) {
                e.printStackTrace();
            } catch (AdditionMainPleineException e) {
                e.printStackTrace();
            } catch (PiocheVideException e) {
                e.printStackTrace();
            }

            // ************************ DONNER INDICE **********************************
        } else if (p.jetonIndice != 0) {

            Random r = new Random();
            int joueur;
            do {
                joueur = r.nextInt(p.getJoueurs().length);
            } while (joueur==this.getId());

            int number = r.nextInt(5);

            int cORi = r.nextInt(2);
            if (cORi==0) {
                if (number == 0) {
                    try {
                        p.indiceCouleur(p.getJoueurs()[joueur], Couleur.CardColor.BLANC);
                    } catch (IndiceSoitMemeException e) {
                        e.printStackTrace();
                    }
                }
                if (number == 1) {
                    try {
                        p.indiceCouleur(p.getJoueurs()[joueur], Couleur.CardColor.ROUGE);
                    } catch (IndiceSoitMemeException e) {
                        e.printStackTrace();
                    }
                }
                if (number == 2) {
                    try {
                        p.indiceCouleur(p.getJoueurs()[joueur], Couleur.CardColor.VERT);
                    } catch (IndiceSoitMemeException e) {
                        e.printStackTrace();
                    }
                }
                if (number == 3) {
                    try {
                        p.indiceCouleur(p.getJoueurs()[joueur], Couleur.CardColor.BLEU);
                    } catch (IndiceSoitMemeException e) {
                        e.printStackTrace();
                    }
                }
                if (number == 4) {
                    try {
                        p.indiceCouleur(p.getJoueurs()[joueur], Couleur.CardColor.JAUNE);
                    } catch (IndiceSoitMemeException e) {
                        e.printStackTrace();
                    }
                }

            }
            else {
                try {
                    p.indiceValeur(p.getJoueurs()[joueur], number+1);
                } catch (IndiceSoitMemeException e) {
                    e.printStackTrace();
                }
            }

            // ************************ DEFAUSSER **********************************
        } else {

            // ************************ DEFAUSSER CARTE CONNUE **********************************
            if (defaussable!=null) {

                int j=this.getMain().getIndex(defaussable);

                try {
                    p.defausse(this, j);
                } catch (EnleverCarteInexistanteException e) {
                    e.printStackTrace();
                } catch (AdditionMainPleineException e) {
                    e.printStackTrace();
                } catch (PiocheVideException e) {
                    e.printStackTrace();
                }
            }

            // ************************ DEFAUSSER CARTE SANS INDICE **********************************
            else {

                for (Carte i : this.getMain().main) {
                    int j = this.getMain().getIndex(i);

                    if (!i.isCouleurConnue() && !i.isValeurConnue()) {
                        try {
                            p.defausse(this, j);
                            break; // Once we "defausse" one card, we are finished, we don't want to defausse more
                        } catch (EnleverCarteInexistanteException e) {
                            e.printStackTrace();
                        } catch (AdditionMainPleineException e) {
                            e.printStackTrace();
                        } catch (PiocheVideException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }


            // ************************ DEFAUSSER CARTE ALEATOIREMENT **********************************

            Random r = new Random();
            try {
                try {
                    p.defausse(this, r.nextInt(this.getMain().nbCartes));
                } catch (AdditionMainPleineException e) {
                    e.printStackTrace();
                } catch (PiocheVideException e) {
                    e.printStackTrace();
                }
                } catch (EnleverCarteInexistanteException e) {
                e.printStackTrace();
            }

        }
    }
}