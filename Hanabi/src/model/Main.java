package model;

import java.io.Serializable;
import java.util.ArrayList;

import model.Couleur.CardColor;

/**
 * Represente la main d'un joueur.
 */
public class Main implements Serializable{
	private static final long serialVersionUID = -6633148306428227300L;
	/**
	 * La liste de cartes qui composent cette main
	 *
	 * @see model.Carte    Carte
	 */
	protected ArrayList<Carte> main;
	/**
	 * Le nombre de cartes que peut contenir la main au maximum
	 */
	protected final int nbCartes; // Nombre de cartes par joueur
	/**
	 * L'identifiant du joueur auquel appartient cette main 
	 */
	protected final int id; // Nombre de cartes par joueur

	/**
	 * Constructeur d'une main avec les cartes donnees en parametre
	 *
	 * @param nbCartes Nombre de cartes que peut contenir cette main
	 * @param cartes   Cartes a ajouter dans cette main
	 */
	public Main(int nbCartes, Carte[] cartes, int id) {
		this.id = id;
		this.nbCartes = nbCartes;
		main = new ArrayList<Carte>(nbCartes);
		for (int i = 0; i < nbCartes; i++) {
			main.add(cartes[i]);
		}
	}


	/**
	 * Constructeur d'une main vide avec une limite de nombre de cartes donnee en parametre
	 *
	 * @param nbCartes Nombre de cartes que peut contenir cette main
	 */
	public Main(int nbCartes, int id) {
		this.id = id;
		this.nbCartes = nbCartes;
		main = new ArrayList<Carte>(nbCartes);
	}


	/**
	 * Ajoute une carte a cette main
	 *
	 * @param c Carte a ajouter a cette main
	 * @throws AdditionMainPleineException Si cette main est deja pleine
	 */
	public void ajouterCarte(Carte c) throws AdditionMainPleineException {
		if (main.size() < this.nbCartes) {
			main.add(c);
		} else {
			throw new AdditionMainPleineException();
		}
	}


	/**
	 * Enleve et renvoie la carte de cette main a l'indice donne
	 *
	 * @param indice Position de la carte dans cette main
	 * @throws EnleverCarteInexistanteException Si l'indice est superieur a {@link #nbCartes}
	 * @return La carte a l'indice donne
	 */
	public Carte enleverCarte(int indice) throws EnleverCarteInexistanteException {
		if (main.size() > indice) {
			return this.main.remove(indice);
		} else {
			throw new EnleverCarteInexistanteException();
		}
	}


	/**
	 * Applique un indice sur la couleur des cartes de cette main
	 *
	 * @param c Couleur indiquee
	 */
	public void indiceCouleur(CardColor c) {
		for (int i = 0; i < this.getNbCartes(); i++) {
			if (this.main.get(i).getCouleur() == c) {
				this.main.get(i).setCouleurConnue(true);
			}
			else{
				this.main.get(i).setCouleurImpossible(c);
			}	
		}
	}


	/**
	 * Applique un indice sur la valeur des cartes de cette main
	 *
	 * @param val La valeur indiquee
	 */
	public void indiceValeur(int val) {
		for (int i = 0; i < this.getNbCartes(); i++) {
			if (this.main.get(i).getValeur() == val) {
				this.main.get(i).setValeurConnue(true);
			}
			else{
				this.main.get(i).setValeurImpossible(val);
			}
		}
	}


	/**
	 * Renvoie la carte de cette main a l'indice donne
	 *
	 * @param indice Position de la carte dans cette main
	 * @throws EnleverCarteInexistanteException Si l'indice est superieur a {@link #nbCartes}
	 * @return La carte a l'indice donne
	 */
	public Carte getCarte(int indice) throws EnleverCarteInexistanteException {
		if (main.size() > indice) {
			return this.main.get(indice);
		} else {
			throw new EnleverCarteInexistanteException();
		}
	}

	public int getIndex(Carte carte) {
		int j = 0;
		for (Carte i : this.main) {
			if (i.equals(carte)) {
				return j;
			}
			j++;
		}
		return -1;
	}

	/**
	 * @return Le nombre de cartes dans la main
	 */
	public int getNbCartes() {
		return main.size();
	}
	/**
	 * Permet de déterminer si une carte est la seule avec sa valeur dans sa main
	 * @param v La valeur concernée
	 * @return vrai ou faux
	 */
	public boolean valeurUnique(int v)
	{
		int nbCartesValeurV=0;
		for (Carte c : this.main){
			if(c.getValeur()==v)
				nbCartesValeurV++;
		}
		if(nbCartesValeurV==1)
			return true;
		return false;
	}
	/**
	 * Permet de déterminer si une carte est la seule avec sa couleur dans sa main
	 * @param col La couleur concernée
	 * @return vrai ou faux
	 */
	public boolean couleurUnique(CardColor col)
	{
		int nbCartesCouleurCol=0;
		for (Carte c : this.main){
			if(c.getCouleur()==col)
				nbCartesCouleurCol++;
		}
		if(nbCartesCouleurCol==1)
			return true;
		return false;
	}
	/**
	 * Ce getter retourne l'identifiant du joueur auquel appartient cette main
	 * @return l'identifiant du joueur auquel appartient cette main
	 */
	
	public int getId() {
		return id;
	}


	public void clear()
	{
		this.main.clear();
	}
	
	public String toString() {
		String res = "";
		for(Carte c:main) {
			res += c.toString();
		}
		return res;
	}
	
	public Main clone() {
		Main m = new Main(this.nbCartes, this.id);
		for(Carte c:this.main) {
			m.main.add(c.clone());
		}
		return m;
	}
}

