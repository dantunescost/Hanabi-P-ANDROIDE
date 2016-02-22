package model;

public abstract class Joueur {
	protected Main main;
	protected String nom; 
	
	public Joueur(String nom,int nbCartes){
		this.nom = nom;
		this.main = new Main(nbCartes);
	}
	
	public Main getMain(){
		return this.main;
	}
	public String getNom(){
		return this.nom;
	}
}
