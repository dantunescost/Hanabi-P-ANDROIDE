package model;

public class JoueurHumain extends Joueur {
	
	public JoueurHumain(String nom,int nbCartes){
		this.nom = nom;
		this.main = new Main(nbCartes);
	}
	
	public void setMain(int nbCartes, Carte[] cartes){
		this.main = new Main(nbCartes, cartes);
	}
}
