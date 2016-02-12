package model;

public abstract class Joueur {
	protected Main main;
	protected String nom; 
	
	public Main getMain(){
		return this.main;
	}
	public String getNom(){
		return this.nom;
	}
}
