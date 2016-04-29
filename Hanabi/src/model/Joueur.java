package model;

import java.io.Serializable;

/**
 * Represente un joueur.
 */
public abstract class Joueur implements Serializable{
	private static final long serialVersionUID = 6204450811065130112L;
	/**
	 * La main du joueur
	 * @see <a href="Main.html">Main</a>
	 */
	protected Main main;
	/**
	 * Le nom du joueur
	 */
	protected String nom;

	/**
	 * La partie du joueur
	 */
	protected Partie p;

	/**
	 * Le numero du joueur
	 */
	protected int id;

	/**
	 * Constructeur d'un joueur avec un nom et une taille de main definis
	 * @param nom		Le nom du joueur
	 * @param nbCartes	Nombre maximum de cartes de sa main
	 * @param p         La partie dans laquelle il joue
	 * @param id		Le numero du joueur dans la partie
	 */
	public Joueur(String nom, Partie p, int id){
		this.nom = nom;
		this.main = new Main(p.nbCartes,id);
		this.p = p;
		this.id = id;
	}
	

	/**
	 * @return	La main de ce joueur
	 */
	public Main getMain(){
		return this.main;
	}

	/**
	 * @return	Le nom de ce joueur
	 */
	public String getNom(){
		return this.nom;
	}
	
	/**
	 * @return	Le numero de ce joueur
	 */
	public int getId(){
		return this.id;
	}
}
