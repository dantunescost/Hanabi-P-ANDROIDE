package view;

import model.AdditionMainPleineException;
import model.Joueur;
import model.JoueurHumain;
import model.Partie;
import model.PiocheVideException;
import model.SemiDummyJoueurIA;

public class PartieGraphique {

	/************************* MAIN *************************/
		public static void main(String[] args){
			Partie game = new Partie(5,8,false);
			Joueur[] joue = new Joueur[5];
		    joue[0] = new JoueurHumain("Holmes", game, 0);
		    joue[1] = new SemiDummyJoueurIA("Watson", game, 1);
		    joue[2] = new SemiDummyJoueurIA("Moriaty", game, 2);
		    joue[3] = new SemiDummyJoueurIA("Lestrade", game, 3);
		    joue[4] = new SemiDummyJoueurIA("Mycroft", game, 4);
		    try {
				game.initPartie(joue);
			} catch (AdditionMainPleineException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (PiocheVideException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		    /*game.getCartesJouees().get(CardColor.BLANC).add(new Carte(CardColor.BLANC,1));
		    game.getCartesJouees().get(CardColor.BLEU).add(new Carte(CardColor.BLEU,1));
		    game.getCartesJouees().get(CardColor.JAUNE).add(new Carte(CardColor.JAUNE,1));
		    game.getCartesJouees().get(CardColor.MULTI).add(new Carte(CardColor.MULTI,1));
		    game.getCartesJouees().get(CardColor.ROUGE).add(new Carte(CardColor.ROUGE,1));
		    game.getCartesJouees().get(CardColor.VERT).add(new Carte(CardColor.VERT,1));*/
		    new FenetrePartie(game);
		}
	
	
}
