package model;

import java.util.ArrayList;
import java.util.Scanner;

import model.Couleur.CardColor;

public class PartieTerminal extends Partie{
	public PartieTerminal(int nbJoueurs, int maxIndices, boolean multicolor) {
		super(nbJoueurs, maxIndices, multicolor);
		// TODO Auto-generated constructor stub
	}

	public void afficherPartie() throws EnleverCarteInexistanteException{
		System.out.println("Les cartes de "+this.joueurs[0].getNom()+" :");
		for(int j=0; j<this.nbCartes; j++){
			afficherCarteAvecIndice(this.joueurs[0].getMain().getCarte(j));
			System.out.print(" ");
		}
		System.out.println();
		for(int i=1;i<this.joueurs.length;i++){
			System.out.println("Les cartes de "+this.joueurs[i].getNom()+" :");
			for(int j=0; j<this.nbCartes; j++){
				afficherCarte(this.joueurs[i].getMain().getCarte(j));
				System.out.print(" ");
			}
			System.out.println();
		}

		System.out.println("La défausse : ");
		afficherPile(this.defausse);
		System.out.println("Les cartes jouées : ");
		afficherPile(this.cartesJouees.get(CardColor.BLANC));
		afficherPile(this.cartesJouees.get(CardColor.BLEU));
		afficherPile(this.cartesJouees.get(CardColor.VERT));
		afficherPile(this.cartesJouees.get(CardColor.ROUGE));
		afficherPile(this.cartesJouees.get(CardColor.JAUNE));
		if(this.multicolor){
			afficherPile(this.cartesJouees.get(CardColor.MULTI));
		}
		System.out.println("Indices restants : "+this.jetonIndice);
		System.out.println("Fautes (éclairs) : "+this.jetonEclair);
	}
	
	// affiche une carte avec l'initial du nom de la couleur en anglais
	// pour éviter toutes ambiguités ex. 2 jaune = "[2Y]"
	public void afficherCarte(Carte c){
		String couleur = "";
		if(c.getCouleur()==CardColor.BLANC){
			couleur = "W";
		} 
		else if(c.getCouleur()==CardColor.BLEU){
			couleur = "B";
		} 
		else if(c.getCouleur()==CardColor.VERT){
			couleur = "G";
		} 
		else if(c.getCouleur()==CardColor.JAUNE){
			couleur = "Y";
		} 
		else if(c.getCouleur()==CardColor.ROUGE){
			couleur = "R";
		} 
		else if(c.getCouleur()==CardColor.MULTI){
			couleur = "M";
		}
		System.out.print("["+c.getValeur()+couleur+"]");
	}
	
	// Affiche les cartes dos au joueur humain (avec les indices)
	public void afficherCarteAvecIndice(Carte c){
		String couleur = " ";
		if(c.isCouleurConnue()){
			if(c.getCouleur()==CardColor.BLANC){
				couleur = "W";
			} 
			else if(c.getCouleur()==CardColor.BLEU){
				couleur = "B";
			} 
			else if(c.getCouleur()==CardColor.VERT){
				couleur = "G";
			} 
			else if(c.getCouleur()==CardColor.JAUNE){
				couleur = "Y";
			} 
			else if(c.getCouleur()==CardColor.ROUGE){
				couleur = "R";
			} 
			else if(c.getCouleur()==CardColor.MULTI){
				couleur = "M";
			}
		}
		String valeur = " ";
		if(c.isValeurConnue()){
			valeur = Integer.toString(c.getValeur());
		}
		System.out.print("["+valeur+couleur+"]");
	}
	
	public void afficherPile(ArrayList<Carte> pile){
		for(int i=0; i<pile.size(); i++){
			afficherCarte(pile.get(i));
			System.out.print(" ");
		}
		System.out.println();
	}
	
	public void setJoueurs(Joueur[] joueurs){
		this.joueurs = joueurs;
	}
	
	public Joueur[] getJoueurs() {
		return joueurs;
	}

	public int getaQuiLeTour() {
		return aQuiLeTour;
	}

	public int getJetonEclair() {
		return jetonEclair;
	}

	public int getJetonIndice() {
		return jetonIndice;
	}

	public static void main(String[] args){
	    Scanner in = new Scanner(System.in);
	    PartieTerminal game = new PartieTerminal(2,8,false);
	    String[] noms = new String[2];
	    noms[0] = "Holmes";
	    noms[1] = "Watson";
	    try {
			game.initPartie(noms);
		} catch (AdditionMainPleineException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (PiocheVideException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	    boolean gameover = false;
	    try {
			game.afficherPartie();
		} catch (EnleverCarteInexistanteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    while(!gameover){
		    System.out.println("Entrez 'j', 'd' ou 'i': ");
		    String txt = in.nextLine();
		    switch(txt){
		    	case "j":
		    		System.out.println("Entrez l'indice de la carte que vous voulez jouer: ");
				    int index = in.nextInt();
				    in.nextLine();
					try {
						game.joueCarte(game.getJoueurs()[game.getaQuiLeTour()],index);
					} catch (AdditionMainPleineException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (PiocheVideException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (EnleverCarteInexistanteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (PartiePerdueException e) {
						System.out.println("Vous avez perdu!");
						gameover = true;
					}
					break;
		    	case "d":
		    		System.out.println("Entrez l'indice de la carte que vous voulez défausser: ");
		    		int def = in.nextInt();
		    		in.nextLine();
					try {
						game.defausse(game.getJoueurs()[game.getaQuiLeTour()],def);
					} catch (AdditionMainPleineException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (PiocheVideException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (EnleverCarteInexistanteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
		    	case "i":
		    		if(game.getJetonIndice() != 0){
			    		System.out.println("Entrez l'indice du joueur auquel vous donner l'indice : ");
			    		int jou = in.nextInt();
			    		in.nextLine();
			    		System.out.println("Entrez 'c' pour un indice couleur, 'v' pour un indice valeur : ");
			    		String type = in.nextLine();
			    		if(type.equals("c")){
				    		System.out.println("Entrez la couleur : ");
				    		String cou = in.nextLine();
				    		CardColor couleur = Couleur.convertirEnCouleur(cou);
				    		try {
								game.indiceCouleur(game.getJoueurs()[jou], couleur);
							} catch (IndiceSoitMemeException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			    		}
			    		else if(type.equals("v")){
			    			System.out.println("Entrez la valeur : ");
				    		int val = in.nextInt();
				    		in.nextLine();
				    		try {
								game.indiceValeur(game.getJoueurs()[jou], val);
							} catch (IndiceSoitMemeException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			    		}
		    		}
		    }
		    try {
		    	if(!gameover)
		    		game.afficherPartie();
			} catch (EnleverCarteInexistanteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    if(game.getJetonEclair() != 3){
	    	System.out.println("Score final : "+game.calculerPoints());
	    }
	    in.close();
	}
}
