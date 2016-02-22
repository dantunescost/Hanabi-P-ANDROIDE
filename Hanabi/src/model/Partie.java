package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import model.Couleur.CardColor;

public class Partie {
	private int nbJoueurs;
	private Joueur[] joueurs;
	private int jetonEclair;
	private int maxIndices; // le nombre de jetons indices dans le jeu
	private int jetonIndice; // le nombre de jetons indices qui peuvent etre utilisé dans le jeu
	private boolean multicolor;
	private ArrayList<Carte> pioche; // les cartes dans lesquelles on peut piocher
	private ArrayList<Carte> defausse;
	private HashMap<CardColor, ArrayList<Carte>> cartesJouees;
	private int aQuiLeTour;
	
	public Partie(int nbJoueurs, int maxIndices, boolean multicolor){
		this.nbJoueurs = nbJoueurs;
		this.jetonEclair = 0;
		this.aQuiLeTour = 0;
		this.maxIndices = maxIndices;
		this.jetonIndice = maxIndices;
		this.multicolor = multicolor;
		this.defausse = new ArrayList<Carte>();
		this.pioche = new ArrayList<Carte>();
		this.cartesJouees = new HashMap<CardColor, ArrayList<Carte>>(5);
		this.cartesJouees.put(CardColor.BLANC, new ArrayList<Carte>());
		this.cartesJouees.put(CardColor.BLEU, new ArrayList<Carte>());
		this.cartesJouees.put(CardColor.VERT, new ArrayList<Carte>());
		this.cartesJouees.put(CardColor.ROUGE, new ArrayList<Carte>());
		this.cartesJouees.put(CardColor.JAUNE, new ArrayList<Carte>());
		if(this.multicolor){
			this.cartesJouees.put(CardColor.MULTI, new ArrayList<Carte>());
		}
	}
	
	public void pioche(Joueur j) throws AdditionMainPleineException, PiocheVideException{
		if(!this.pioche.isEmpty()){
			j.getMain().ajouterCarte(this.pioche.remove(this.pioche.size()-1));
		}else{
			throw new PiocheVideException();
		}
	}
	
	public void defausse(Joueur j, int index) throws EnleverCarteInexistanteException, AdditionMainPleineException, PiocheVideException{
		Carte carte = j.getMain().enleverCarte(index);
		this.defausse.add(carte);

        // Rajoute un jeton indice s'il n'y a pas déjà tous les indices disponibles
		if(this.jetonIndice != this.maxIndices){
			this.jetonIndice ++;
		}
		pioche(j);
		this.aQuiLeTour = (this.aQuiLeTour+1)%this.nbJoueurs;
	}
	
	public void joueCarte(Joueur j, int indice) throws EnleverCarteInexistanteException, PartiePerdueException, AdditionMainPleineException, PiocheVideException{
		// Enleve la carte de la main du joueur
        Carte carte = j.getMain().enleverCarte(indice);

        // La carte est valide
		if(this.cartesJouees.get(carte.getCouleur()).size()+1 == carte.getValeur()){
			this.cartesJouees.get(carte.getCouleur()).add(carte);
		}

        // La carte n'est pas valide
		else{
			this.defausse.add(carte);
			this.jetonEclair++;
			if(this.jetonEclair == 3){
				throw new PartiePerdueException();
			}
		}
		pioche(j);
		this.aQuiLeTour = (this.aQuiLeTour+1)%this.nbJoueurs;
	}
	
	public void indiceCouleur(Joueur j, CardColor c){
		j.getMain().indiceCouleur(c);
		this.jetonIndice--;
		this.aQuiLeTour = (this.aQuiLeTour+1)%this.nbJoueurs;
	}
	
	public void indiceValeur(Joueur j, int val){
		j.getMain().indiceValeur(val);
		this.jetonIndice--;
		this.aQuiLeTour = (this.aQuiLeTour+1)%this.nbJoueurs;
	}
	
	public int calculerPoints(){
		int total = 0;
		total += this.cartesJouees.get(CardColor.BLANC).size();
		total += this.cartesJouees.get(CardColor.JAUNE).size();
		total += this.cartesJouees.get(CardColor.VERT).size();
		total += this.cartesJouees.get(CardColor.BLEU).size();
		total += this.cartesJouees.get(CardColor.ROUGE).size();
		if(this.multicolor){
			total += this.cartesJouees.get(CardColor.MULTI).size();
		}
		return total;
	}
	
	public void initPartie(String[] nomsJoueurs) throws AdditionMainPleineException, PiocheVideException{
		this.joueurs = new Joueur[this.nbJoueurs];
		int nbCartes = 4;
		if(this.nbJoueurs == 2 || this.nbJoueurs == 3){
			nbCartes = 5;
		}
		for(int i=0; i<this.nbJoueurs; i++){
			this.joueurs[i] = new JoueurHumain(nomsJoueurs[i],nbCartes);
		}
		creerLesCartes();
		for(int i=0; i<nbCartes; i++){
			for(Joueur j : this.joueurs){
				pioche(j);
			}
		}
	}

	//creates the cards and shuffles them into the deck
	private void creerLesCartes() {
		ArrayList<Carte> deck = new ArrayList<Carte>();
		for(int i=1; i<6; i++){
			if(i==1){
				deck.add(new Carte(CardColor.BLANC,i));
				deck.add(new Carte(CardColor.JAUNE,i));
				deck.add(new Carte(CardColor.VERT,i));
				deck.add(new Carte(CardColor.BLEU,i));
				deck.add(new Carte(CardColor.ROUGE,i));
				deck.add(new Carte(CardColor.BLANC,i));
				deck.add(new Carte(CardColor.JAUNE,i));
				deck.add(new Carte(CardColor.VERT,i));
				deck.add(new Carte(CardColor.BLEU,i));
				deck.add(new Carte(CardColor.ROUGE,i));
				deck.add(new Carte(CardColor.BLANC,i));
				deck.add(new Carte(CardColor.JAUNE,i));
				deck.add(new Carte(CardColor.VERT,i));
				deck.add(new Carte(CardColor.BLEU,i));
				deck.add(new Carte(CardColor.ROUGE,i));
				if(this.multicolor){
					deck.add(new Carte(CardColor.MULTI,i));
					deck.add(new Carte(CardColor.MULTI,i));
					deck.add(new Carte(CardColor.MULTI,i));
				}
			}
			else if(i==5){
				deck.add(new Carte(CardColor.BLANC,i));
				deck.add(new Carte(CardColor.JAUNE,i));
				deck.add(new Carte(CardColor.VERT,i));
				deck.add(new Carte(CardColor.BLEU,i));
				deck.add(new Carte(CardColor.ROUGE,i));	
				if(this.multicolor){
					deck.add(new Carte(CardColor.MULTI,i));
				}
			}
			else{
				deck.add(new Carte(CardColor.BLANC,i));
				deck.add(new Carte(CardColor.JAUNE,i));
				deck.add(new Carte(CardColor.VERT,i));
				deck.add(new Carte(CardColor.BLEU,i));
				deck.add(new Carte(CardColor.ROUGE,i));
				deck.add(new Carte(CardColor.BLANC,i));
				deck.add(new Carte(CardColor.JAUNE,i));
				deck.add(new Carte(CardColor.VERT,i));
				deck.add(new Carte(CardColor.BLEU,i));
				deck.add(new Carte(CardColor.ROUGE,i));
				if(this.multicolor){
					deck.add(new Carte(CardColor.MULTI,i));
					deck.add(new Carte(CardColor.MULTI,i));
				}
			}
		}
		int nbCartes = deck.size();
		Random rng = new Random();
		for(int i=0; i<nbCartes; i++){
			int n = rng.nextInt(deck.size());
			this.pioche.add(deck.remove(n));
		}
	}
	
	public void afficherPartie() throws EnleverCarteInexistanteException{
		int nbCartes;
		if(this.nbJoueurs > 3){
			nbCartes = 4;
		}
		else{
			nbCartes = 5;
		}
		System.out.println("Les cartes de "+this.joueurs[0].getNom()+" :");
		for(int j=0; j<nbCartes; j++){
			afficherCarteAvecIndice(this.joueurs[0].getMain().getCarte(j));
			System.out.print(" ");
		}
		System.out.println();
		for(int i=1;i<this.joueurs.length;i++){
			System.out.println("Les cartes de "+this.joueurs[i].getNom()+" :");
			for(int j=0; j<nbCartes; j++){
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
	    Partie game = new Partie(2,8,false);
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
		    System.out.println("Entrez 'j', 'd' ou 'i': "); // jouer une carte, defausser une carte, donner un indice
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
			    		System.out.println("Entrez l'indice du joueur auquel vous donnez l'indice : ");
			    		int jou = in.nextInt();
			    		in.nextLine();
			    		System.out.println("Entrez 'c' pour un indice couleur, 'v' pour un indice valeur : ");
			    		String type = in.nextLine();
			    		if(type.equals("c")){
				    		System.out.println("Entrez la couleur : ");
				    		String cou = in.nextLine();
				    		CardColor couleur = null;
				    		if(cou.toUpperCase().equals("BLANC") || cou.toUpperCase().equals("WHITE")){
			    				couleur = CardColor.BLANC;
			    			} else if(cou.toUpperCase().equals("BLEU") || cou.toUpperCase().equals("BLUE")){
			    				couleur = CardColor.BLEU;
			    			} else if(cou.toUpperCase().equals("VERT") || cou.toUpperCase().equals("GREEN")){
			    				couleur = CardColor.VERT;
			    			} else if(cou.toUpperCase().equals("ROUGE") || cou.toUpperCase().equals("RED")){
			    				couleur = CardColor.ROUGE;
			    			} else if(cou.toUpperCase().equals("JAUNE") || cou.toUpperCase().equals("YELLOW")){
			    				couleur = CardColor.JAUNE;
			    			} else if(cou.toUpperCase().equals("MULTI")){
			    				couleur = CardColor.MULTI;
			    			}
				    		game.indiceCouleur(game.getJoueurs()[jou], couleur);
			    		}
			    		else if(type.equals("v")){
			    			System.out.println("Entrez la valeur : ");
				    		int val = in.nextInt();
				    		in.nextLine();
				    		game.indiceValeur(game.getJoueurs()[jou], val);
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
