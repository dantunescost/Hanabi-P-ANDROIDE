package model;

import model.Couleur.CardColor;

/**
 * Represente les cartes du jeu Hanabi.
 */
public class Carte {
	/**
	 * La couleur de cette carte
	 * @see <a href="Couleur.html">Couleur</a>
	 */
	private final Couleur couleur;
	/**
	 * La valeur de cette carte
	 */
	private final int valeur;
	/**
	 * Indique si le joueur connait la couleur de cette carte dans sa main
	 * @see <a href="Main.html">Main</a>
	 * @see <a href="Joueur.html">Joueur</a>
	 */
	private boolean couleurConnue;
	/**
	 * Indique si le joueur connait la valeur de cette carte dans sa main
	 * @see <a href="Main.html">Main</a>
	 * @see <a href="Joueur.html">Joueur</a>
	 */
	private boolean valeurConnue;
	
	
	/**
	 * Constructeur d'une carte avec une couleur et une valeur definies
	 * @param couleur	La couleur voulue pour la carte
	 * @param valeur	La valeur voulue pour la carte
	 */
	public Carte(CardColor couleur, int valeur){
		this.couleur = new Couleur(couleur);
		this.valeur = valeur;
		this.valeurConnue = false;
		this.couleurConnue = false;
	}
	
	
	/**
	 * @return 	Indique si le joueur connait la couleur de cette carte
	 */
	public boolean isCouleurConnue() {
		return couleurConnue;
	}
	

	/**
	 * Permet de definir si le joueur connait la couleur de cette carte
	 * 
	 * @param couleurConnue	Vaut true quand le joueur recoit un indice
	 */
	public void setCouleurConnue(boolean couleurConnue) {
		this.couleurConnue = couleurConnue;
	}
	

	/**
	 * @return	Indique si le joueur connait la valeur de cette carte
	 */
	public boolean isValeurConnue() {
		return valeurConnue;
	}
	

	/**
	 * Permet de definir si le joueur connait la valeur de cette carte
	 * @param valeurConnue	Vaut true quand le joueur recoit un indice
	 */
	public void setValeurConnue(boolean valeurConnue) {
		this.valeurConnue = valeurConnue;
	}
	
	

	/**
	 * @return	La couleur de cette carte
	 */
	public CardColor getCouleur() {
		return couleur.getCouleur();
	}
	
	
	/**
	 * @return	La valeur de cette carte
	 */
	public int getValeur() {
		return valeur;
	}
}
