package model;

public class Carte {
	private final Couleur couleur;
	private final int valeur;
	private boolean couleurConnue;
	private boolean valeurConnue;
	
	public Carte(Couleur couleur, int valeur){
		this.couleur = couleur;
		this.valeur = valeur;
		this.valeurConnue = false;
		this.couleurConnue = false;
	}

	public boolean isCouleurConnue() {
		return couleurConnue;
	}

	public void setCouleurConnue(boolean couleurConnue) {
		this.couleurConnue = couleurConnue;
	}

	public boolean isValeurConnue() {
		return valeurConnue;
	}

	public void setValeurConnue(boolean valeurConnue) {
		this.valeurConnue = valeurConnue;
	}

	public Couleur getCouleur() {
		return couleur;
	}

	public int getValeur() {
		return valeur;
	}
}
