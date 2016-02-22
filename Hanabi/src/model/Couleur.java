package model;

/**
 * Represente les couleurs des cartes.
 */
public class Couleur {
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
}
