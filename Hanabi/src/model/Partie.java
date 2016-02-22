package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import model.Couleur.CardColor;

public class Partie {
	protected int nbJoueurs;
	protected int nbCartes;
	protected Joueur[] joueurs;
	protected int jetonEclair;
	protected int maxIndices; // le nombre de jetons indices dans le jeu
	protected int jetonIndice; // le nombre de jetons indices qui peuvent etre utilisé dans le jeu
	protected boolean multicolor;
	protected ArrayList<Carte> pioche; // les cartes dans lesquelles on peut piocher
	protected ArrayList<Carte> defausse;
	protected HashMap<CardColor, ArrayList<Carte>> cartesJouees;
	protected int aQuiLeTour;
	
	public Partie(int nbJoueurs, int maxIndices, boolean multicolor){
		this.nbJoueurs = nbJoueurs;
		if(nbJoueurs == 2 || nbJoueurs == 3){
			this.nbCartes = 5;
		}
		else if(nbJoueurs == 4 || nbJoueurs == 5){
			this.nbCartes = 4;
		}
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
	
	public void indiceCouleur(Joueur j, CardColor c) throws IndiceSoitMemeException{
		if(j != this.joueurs[this.aQuiLeTour]){
			j.getMain().indiceCouleur(c);
			this.jetonIndice--;
			this.aQuiLeTour = (this.aQuiLeTour+1)%this.nbJoueurs;
		}
		else{
			throw new IndiceSoitMemeException();
		}
	}
	
	public void indiceValeur(Joueur j, int val) throws IndiceSoitMemeException{
		if(j != this.joueurs[this.aQuiLeTour]){
			j.getMain().indiceValeur(val);
			this.jetonIndice--;
			this.aQuiLeTour = (this.aQuiLeTour+1)%this.nbJoueurs;
		}
		else{
			throw new IndiceSoitMemeException();
		}
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
		for(int i=0; i<this.nbJoueurs; i++){
			this.joueurs[i] = new JoueurHumain(nomsJoueurs[i],this.nbCartes);
		}
		creerLesCartes();
		for(int i=0; i<this.nbCartes; i++){
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
				for(int j=0;j<3;j++){
					deck.add(new Carte(CardColor.BLANC,i));
					deck.add(new Carte(CardColor.JAUNE,i));
					deck.add(new Carte(CardColor.VERT,i));
					deck.add(new Carte(CardColor.BLEU,i));
					deck.add(new Carte(CardColor.ROUGE,i));
					if(this.multicolor){
						deck.add(new Carte(CardColor.MULTI,i));
					}
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
				for(int j=0;j<2;j++){
					deck.add(new Carte(CardColor.BLANC,i));
					deck.add(new Carte(CardColor.JAUNE,i));
					deck.add(new Carte(CardColor.VERT,i));
					deck.add(new Carte(CardColor.BLEU,i));
					deck.add(new Carte(CardColor.ROUGE,i));
					if(this.multicolor){
						deck.add(new Carte(CardColor.MULTI,i));
					}
				}
			}
		}
		int deckSize = deck.size();
		Random rng = new Random();
		for(int i=0; i<deckSize; i++){
			int n = rng.nextInt(deck.size());
			this.pioche.add(deck.remove(n));
		}
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
}