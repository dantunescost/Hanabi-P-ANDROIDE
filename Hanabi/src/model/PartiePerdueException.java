package model;


/**
 * 	Exception levee lorsque le nombre de fautes atteint 3, la partie se termine alors
 */
public class PartiePerdueException extends Exception {
	private static final long serialVersionUID = 1L;

	public PartiePerdueException(){
		super();
		System.out.println("Vous avez joué 3 mauvaises cartes, votre feu d'artifice est ruiné!");
	}
}
