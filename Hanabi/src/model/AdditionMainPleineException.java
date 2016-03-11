package model;

/**
 *	Exception levee lorsqu'une fonction essaye d'ajouter une carte a une main pleine
 */
public class AdditionMainPleineException extends Exception {
	private static final long serialVersionUID = 1L;

	public AdditionMainPleineException(){
		super();
		System.out.println("Vous essayez d'ajouter une carte dans une main pleine!");
	}
}
