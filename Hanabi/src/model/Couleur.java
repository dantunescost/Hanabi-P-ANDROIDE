package model;

public class Couleur {
	public enum CardColor{BLANC, ROUGE, BLEU, VERT, JAUNE, MULTI}
	private CardColor couleur;
	
	public Couleur(CardColor c){
		this.couleur = c;
	}

	public CardColor getCouleur() {
		return couleur;
	}
	
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
