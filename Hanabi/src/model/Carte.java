package model;


import java.io.Serializable;
import java.util.HashMap;

import model.Couleur.CardColor;

/**
 * Represente les cartes du jeu Hanabi.
 */
public class Carte implements Serializable{
	private static final long serialVersionUID = 1539421190585703606L;
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
	
	
	private HashMap<Couleur.CardColor, Boolean> couleursPossibles;
	
	private HashMap<Integer,Boolean> valeursPossibles;
			
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
		
		couleursPossibles= new HashMap<Couleur.CardColor, Boolean>();
		couleursPossibles.put(Couleur.CardColor.BLANC, true);
		couleursPossibles.put(Couleur.CardColor.ROUGE, true);
		couleursPossibles.put(Couleur.CardColor.VERT, true);
		couleursPossibles.put(Couleur.CardColor.BLEU, true);
		couleursPossibles.put(Couleur.CardColor.JAUNE, true);
		
		valeursPossibles= new HashMap<Integer,Boolean>();
		valeursPossibles.put(1, true);
		valeursPossibles.put(2, true);
		valeursPossibles.put(3, true);
		valeursPossibles.put(4, true);
		valeursPossibles.put(5, true);
	}
	/**
	 * Permet de definir si cette carte n'est pas d'une couleur
	 * 
	 * @param c	désigne la couleur concernée
	 */
	public void setCouleurImpossible(Couleur.CardColor c){
		couleursPossibles.put(c,false);
	}
	/**
	 * Permet de definir si cette carte n'est pas d'une valeur
	 * 
	 * @param v	désigne la valeur concernée
	 */
	public void setValeurImpossible(int v){
		valeursPossibles.put(v,false);
	}
	
	/**
	 * @param c	désigne la couleur concernée
	 * @return 	Indique si cette carte peut être de cette couleur
	 */
	public boolean couleurPossible(Couleur.CardColor c) {
		return couleursPossibles.get(c);
	}
	
	/**
	 * @param v	désigne la couleur concernée
	 * @return 	Indique si cette carte peut être de cette valeur
	 */
	public boolean valeurPossible(int v) {
		return valeursPossibles.get(v);
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
	
	/**
	 * @return	Le nom de la carte dans les ressources
	 */
	public String getCardName(){
		String res="";
		res += this.couleur.convertirEnChaine() + "_";
		res += Integer.toString(this.valeur) + ".png";
		return res; 
	}
	
	/**
	 * Permet de mettre à jour valeurConnue et couleurConnue en
	 * se basant sur les données de valeursImpossibles et
	 * couleursImpossibles
	 * 
	 * @param multi	vaut true si l'on est dans une partie avec des cartes multicolores
	 */
	public void majConnaissanceImmediate(boolean multi){
		int nbValeurs=5;
		int nbCImpossibles=0;
		int nbVImpossibles=0;

		for(int i=0; i<Couleur.getAllCouleurs(multi).size();i++)
		{
			if(!(this.couleurPossible(Couleur.getAllCouleurs(multi).get(i))))
			{
				nbCImpossibles++;
			}
		}
		if(nbCImpossibles==Couleur.getAllCouleurs(multi).size()-1)
		{
			this.setCouleurConnue(true);
		}
		for(int i=1; i<(nbValeurs+1); i++)
		{
			if(!(this.valeurPossible(i)))
			{
				nbVImpossibles++;
			}
		}
		if(nbVImpossibles==(nbValeurs-1))
		{
			this.setValeurConnue(true);
		}
	}
	public boolean isJouableOmniscient(Partie p)
	{
		return (p.cartesJouees.get(this.getCouleur()).size()==(this.getValeur()-1));
	}
	public boolean isDefaussableOmniscient(Partie p)
	{
		boolean res=false;
		if(p.cartesJouees.get(this.getCouleur()).size()>=(this.getValeur()))
		{
			res=true;
		}
		else
		{
			int compt[] = new int[this.getValeur()-1];
    		for(int i=0; i<p.defausse.size(); i++)
    		{
    			if((p.defausse.get(i).getCouleur() == this.getCouleur()) && (p.defausse.get(i).getValeur()<this.getValeur()))
    			{
    				compt[p.defausse.get(i).getValeur()-1]++;
    			}
    		}
    		for(int j=0; j<this.getValeur()-1 && !res; j++)
    		{
    			if(((j+2)/3 + compt[j])==3)
    				res=true;
    		}
		}
		return res;
	}
	
	public boolean equals(Object other){
		if(other instanceof Carte){
			Carte otherCard = (Carte) other;
    		return 
    		((  this.getCouleur() == otherCard.getCouleur() ||
    			( this.getCouleur() != null && otherCard.getCouleur() != null &&
    			  this.getCouleur().equals(otherCard.getCouleur())))
    			&&
    		 (	this.valeur == otherCard.getValeur() ) );
		}
		return false;		
	}
	
	public Carte clone(){
		return new Carte(getCouleur(),valeur);
	}
	
	public String toString(){
		String res = "[";
		res += Integer.toString(this.valeur) +",";
		res += this.couleur.convertirEnChaine();
		return res + "]";
	}
	
	public int hashCode(){
		int res;
		if(this.couleur.equals(CardColor.BLANC)){
			res = 0;
		} else if(this.couleur.equals(CardColor.BLEU)){
			res = 5;
		} else if(this.couleur.equals(CardColor.ROUGE)){
			res = 10;
		} else if(this.couleur.equals(CardColor.JAUNE)){
			res = 15;
		} else if(this.couleur.equals(CardColor.VERT)){
			res = 20;
		} else{
			res = 25;
		}
		return res+valeur;
	}
}
