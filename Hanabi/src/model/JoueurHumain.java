package model;

/**
 * Sous-classe pour le joueur humain
 */
public class JoueurHumain extends Joueur {
	
	/**
	 * Constructeur pour le joueur humain	
	 * @param nom		Le nom du joueur
	 * @param nbCartes	Nombre maximum de cartes de sa main
	 * @param p         La partie dans laquelle il joue
	 * @param id		Le numero du joueur dans la partie
	 */
	public JoueurHumain(String nom, Partie p, int id){
		super(nom, p, id);
	}
	
}
