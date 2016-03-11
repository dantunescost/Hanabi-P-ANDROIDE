package model;

/**
 *	Exception levee si une fonction tente de recuperer une carte dans la pioche alors que celle-ci est vide
 */
public class PiocheVideException extends Exception {
	private static final long serialVersionUID = 1L;

	public PiocheVideException(){
		super();
		System.out.println("Vous essayez de piocher une carte mais la pioche est vide!");
	}
}
