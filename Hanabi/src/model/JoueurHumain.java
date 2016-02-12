package model;

public class JoueurHumain extends Joueur {
	
	public JoueurHumain(String nom){
		this.nom = nom;
	}
	
	public void setMain(int nbCartes, Carte[] cartes){
		this.main = new Main(nbCartes, cartes);
	}
}
