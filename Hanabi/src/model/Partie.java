package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Partie {
	private int nbJoueurs;
	private Joueur[] joueurs;
	private int jetonEclair;
	private int jetonIndice;
	private ArrayList<Carte> pioche;
	private ArrayList<Carte> defausse;
	private HashMap<Couleur,ArrayList<Carte>> cartesJouees;
	
	public Partie(int nbJoueurs){
		this.nbJoueurs = nbJoueurs;
		this.jetonEclair = 0;
		this.jetonIndice = 8;
		this.defausse = new ArrayList<Carte>();
		this.pioche = new ArrayList<Carte>();
		this.cartesJouees = new HashMap<Couleur, ArrayList<Carte>>(5);
		this.cartesJouees.put(Couleur.BLANC, new ArrayList<Carte>());
		this.cartesJouees.put(Couleur.BLEU, new ArrayList<Carte>());
		this.cartesJouees.put(Couleur.VERT, new ArrayList<Carte>());
		this.cartesJouees.put(Couleur.ROUGE, new ArrayList<Carte>());
		this.cartesJouees.put(Couleur.JAUNE, new ArrayList<Carte>());
	}
	
}
