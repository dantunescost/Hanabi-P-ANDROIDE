package model;

/**
 * 	Exception levee lorsqu'un joueur essaye de se donner un indice
 */
public class IndiceSoitMemeException extends Exception {
	private static final long serialVersionUID = 1L;

	public IndiceSoitMemeException(){
		super();
		System.out.println("Vous essayer de vous donner un indice sur votre main!");
	}
}
