package model;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represente les couleurs des cartes.
 */
public class Couleur implements Serializable{ 
	private static final long serialVersionUID = 6840858689928410251L;

	/**
	 * Enumeration des couleurs possibles
	 */
	public enum CardColor{BLANC, ROUGE, BLEU, VERT, JAUNE, MULTI}
	/**
	 * Contient la valeur de cette couleur
	 */
	private CardColor couleur;
	
	/**
	 * Constructeur pour une couleur
	 * @param c	La couleur voulue
	 */
	public Couleur(CardColor c){
		this.couleur = c;
	}
	
	
	/**
	 * @return	La couleur de cette couleur
	 */
	public CardColor getCouleur() {
		return couleur;
	}
	
	
	/**
	 * Effectue la conversion entre une chaine de caractere et la couleur correspondante
	 * @param c	La chaine a convertir
	 * @return	La couleur obtenue par cette conversion
	 */
	public static CardColor convertirEnCouleur(String c){
		CardColor couleur = null;
		if(c.toUpperCase().equals("BLANC") || c.toUpperCase().equals("WHITE") || c.toUpperCase().equals("W")){
			couleur = CardColor.BLANC;
		} else if(c.toUpperCase().equals("BLEU") || c.toUpperCase().equals("BLUE") || c.toUpperCase().equals("B")){
			couleur = CardColor.BLEU;
		} else if(c.toUpperCase().equals("VERT") || c.toUpperCase().equals("GREEN") || c.toUpperCase().equals("G")){
			couleur = CardColor.VERT;
		} else if(c.toUpperCase().equals("ROUGE") || c.toUpperCase().equals("RED") || c.toUpperCase().equals("R")){
			couleur = CardColor.ROUGE;
		} else if(c.toUpperCase().equals("JAUNE") || c.toUpperCase().equals("YELLOW") || c.toUpperCase().equals("Y")){
			couleur = CardColor.JAUNE;
		} else if(c.toUpperCase().equals("MULTI") || c.toUpperCase().equals("M")){
			couleur = CardColor.MULTI;
		}
		return couleur;
	}
	
	/**
	 * Effectue la conversion entre une couleur et la chaine de caractere correspondante
	 * @return	La chaine de caracteres obtenue par cette conversion
	 */
	public String convertirEnChaine(){
		String res="";
		if(this.couleur.equals(CardColor.BLANC)){
			res = "Blanc";
		} else if(this.couleur.equals(CardColor.BLEU)){
			res = "Bleu";
		} else if(this.couleur.equals(CardColor.ROUGE)){
			res = "Rouge";
		} else if(this.couleur.equals(CardColor.JAUNE)){
			res = "Jaune";
		} else if(this.couleur.equals(CardColor.VERT)){
			res = "Vert";
		} else if(this.couleur.equals(CardColor.MULTI)){
			res = "Multi";
		}
		return res;
	}
	
	public static ArrayList<CardColor> getAllCouleurs(boolean multi){
		ArrayList<CardColor> liste= new ArrayList<CardColor>();
		liste.add(CardColor.BLANC);
		liste.add(CardColor.BLEU);
		liste.add(CardColor.ROUGE);
		liste.add(CardColor.JAUNE);
		liste.add(CardColor.VERT);
		if(multi)
			liste.add(CardColor.MULTI);
		return liste;
	}
	
	public static CardColor intToCardColor(int inIndices) {
		switch(inIndices){
		case 6:
			return CardColor.ROUGE;
		case 7:
			return CardColor.BLEU;
		case 8:
			return CardColor.VERT;
		case 9:
			return CardColor.JAUNE;
		case 10:
			return CardColor.BLANC;
		case 11:
			return CardColor.MULTI;
		default: 
			return null;
		}
	}
	
	public static Color cardColorToColor(CardColor c) {
		switch(c){
		case ROUGE:
			return Color.RED;
		case BLEU:
			return Color.BLUE;
		case VERT:
			return Color.GREEN;
		case JAUNE:
			return Color.YELLOW;
		case BLANC:
			return Color.WHITE;
		case MULTI:
			return Color.MAGENTA;
		default: 
			return null;
		}
	}
}
