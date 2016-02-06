package model;

public class AdditionMainPleineException extends Exception {
	private static final long serialVersionUID = 1L;

	public AdditionMainPleineException(){
		super();
		System.out.println("Vous essayez d'ajouter une carte dans une main pleine!");
	}
}
