package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Partie {
	private int nbJoueurs;
	private Joueur[] joueurs;
	private int jetonEclair;
	private int maxIndices;
	private int jetonIndice;
	private boolean multicolor;
	private ArrayList<Carte> pioche;
	private ArrayList<Carte> defausse;
	private HashMap<Couleur,ArrayList<Carte>> cartesJouees;
	
	public Partie(int nbJoueurs, int maxIndices, boolean multicolor){
		this.nbJoueurs = nbJoueurs;
		this.jetonEclair = 0;
		this.maxIndices = maxIndices;
		this.jetonIndice = maxIndices;
		this.multicolor = multicolor;
		this.defausse = new ArrayList<Carte>();
		this.pioche = new ArrayList<Carte>();
		this.cartesJouees = new HashMap<Couleur, ArrayList<Carte>>(5);
		this.cartesJouees.put(Couleur.BLANC, new ArrayList<Carte>());
		this.cartesJouees.put(Couleur.BLEU, new ArrayList<Carte>());
		this.cartesJouees.put(Couleur.VERT, new ArrayList<Carte>());
		this.cartesJouees.put(Couleur.ROUGE, new ArrayList<Carte>());
		this.cartesJouees.put(Couleur.JAUNE, new ArrayList<Carte>());
		if(this.multicolor){
			this.cartesJouees.put(Couleur.MULTI, new ArrayList<Carte>());
		}
	}
	
	public void pioche(Joueur j) throws AdditionMainPleineException, PiocheVideException{
		if(!this.pioche.isEmpty()){
			j.getMain().ajouterCarte(this.pioche.remove(this.pioche.size()-1));
		}else{
			throw new PiocheVideException();
		}
	}
	
	public void defausse(Joueur j, int index) throws EnleverCarteInexistanteException{
		Carte carte = j.getMain().enleverCarte(index);
		this.defausse.add(carte);
		if(this.jetonIndice != this.maxIndices){
			this.jetonIndice ++;
		}
	}
	
	public void joueCarte(Joueur j, int indice) throws EnleverCarteInexistanteException, PartiePerdueException{
		Carte carte = j.getMain().getCarte(indice);
		if(this.cartesJouees.get(carte.getCouleur()).size()+1 == carte.getValeur()){
			this.cartesJouees.get(carte.getCouleur()).add(carte);
		}
		else{
			this.defausse.add(carte);
			this.jetonEclair++;
			if(this.jetonEclair == 3){
				throw new PartiePerdueException();
			}
		}
	}
	
	public int calculerPoints(){
		int total = 0;
		total += this.cartesJouees.get(Couleur.BLANC).size();
		total += this.cartesJouees.get(Couleur.JAUNE).size();
		total += this.cartesJouees.get(Couleur.VERT).size();
		total += this.cartesJouees.get(Couleur.BLEU).size();
		total += this.cartesJouees.get(Couleur.ROUGE).size();
		if(this.multicolor){
			total += this.cartesJouees.get(Couleur.MULTI).size();
		}
		return total;
	}
	
	public void initPartie(String[] nomsJoueurs) throws AdditionMainPleineException, PiocheVideException{
		this.joueurs = new Joueur[this.nbJoueurs];
		for(int i=0; i<this.nbJoueurs; i++){
			this.joueurs[i] = new JoueurHumain(nomsJoueurs[i]);
		}
		creerLesCartes();
		int nbCartes;
		if(this.nbJoueurs == 4 || this.nbJoueurs == 5){
			nbCartes = 4;
		}
		else{
			nbCartes = 5;
		}
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
				deck.add(new Carte(Couleur.BLANC,i));
				deck.add(new Carte(Couleur.JAUNE,i));
				deck.add(new Carte(Couleur.VERT,i));
				deck.add(new Carte(Couleur.BLEU,i));
				deck.add(new Carte(Couleur.ROUGE,i));
				deck.add(new Carte(Couleur.BLANC,i));
				deck.add(new Carte(Couleur.JAUNE,i));
				deck.add(new Carte(Couleur.VERT,i));
				deck.add(new Carte(Couleur.BLEU,i));
				deck.add(new Carte(Couleur.ROUGE,i));
				deck.add(new Carte(Couleur.BLANC,i));
				deck.add(new Carte(Couleur.JAUNE,i));
				deck.add(new Carte(Couleur.VERT,i));
				deck.add(new Carte(Couleur.BLEU,i));
				deck.add(new Carte(Couleur.ROUGE,i));
				if(this.multicolor){
					deck.add(new Carte(Couleur.MULTI,i));
					deck.add(new Carte(Couleur.MULTI,i));
					deck.add(new Carte(Couleur.MULTI,i));
				}
			}
			else if(i==5){
				deck.add(new Carte(Couleur.BLANC,i));
				deck.add(new Carte(Couleur.JAUNE,i));
				deck.add(new Carte(Couleur.VERT,i));
				deck.add(new Carte(Couleur.BLEU,i));
				deck.add(new Carte(Couleur.ROUGE,i));	
				if(this.multicolor){
					deck.add(new Carte(Couleur.MULTI,i));
				}
			}
			else{
				deck.add(new Carte(Couleur.BLANC,i));
				deck.add(new Carte(Couleur.JAUNE,i));
				deck.add(new Carte(Couleur.VERT,i));
				deck.add(new Carte(Couleur.BLEU,i));
				deck.add(new Carte(Couleur.ROUGE,i));
				deck.add(new Carte(Couleur.BLANC,i));
				deck.add(new Carte(Couleur.JAUNE,i));
				deck.add(new Carte(Couleur.VERT,i));
				deck.add(new Carte(Couleur.BLEU,i));
				deck.add(new Carte(Couleur.ROUGE,i));
				if(this.multicolor){
					deck.add(new Carte(Couleur.MULTI,i));
					deck.add(new Carte(Couleur.MULTI,i));
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
		for(int i=1;i<this.joueurs.length;i++){
			System.out.println("Les cartes du joueur "+i+" :");
			for(int j=0; j<nbCartes; j++){
				afficherCarte(this.joueurs[i].getMain().getCarte(j));
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	// affiche une carte avec l'initial du nom de la couleur en anglais
	// pour éviter toutes ambiguités ex. 2 jaune = "[2Y]"
	public void afficherCarte(Carte c){
		String couleur = "";
		if(c.getCouleur()==Couleur.BLANC){
			couleur = "W";
		} 
		else if(c.getCouleur()==Couleur.BLEU){
			couleur = "B";
		} 
		else if(c.getCouleur()==Couleur.VERT){
			couleur = "G";
		} 
		else if(c.getCouleur()==Couleur.JAUNE){
			couleur = "Y";
		} 
		else if(c.getCouleur()==Couleur.ROUGE){
			couleur = "R";
		} 
		else if(c.getCouleur()==Couleur.MULTI){
			couleur = "M";
		}
		System.out.print("["+c.getValeur()+couleur+"]");
	}
	
	// Affiche les cartes dos au joueur humain (avec les indices)
	public void afficherCarteAvecIndice(Carte c){
		String couleur = " ";
		if(c.isCouleurConnue()){
			if(c.getCouleur()==Couleur.BLANC){
				couleur = "W";
			} 
			else if(c.getCouleur()==Couleur.BLEU){
				couleur = "B";
			} 
			else if(c.getCouleur()==Couleur.VERT){
				couleur = "G";
			} 
			else if(c.getCouleur()==Couleur.JAUNE){
				couleur = "Y";
			} 
			else if(c.getCouleur()==Couleur.ROUGE){
				couleur = "R";
			} 
			else if(c.getCouleur()==Couleur.MULTI){
				couleur = "M";
			}
		}
		String valeur = " ";
		if(c.isValeurConnue()){
			valeur = Integer.toString(c.getValeur());
		}
		System.out.print("["+valeur+couleur+"]");
	}
}
