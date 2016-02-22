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
}
