package model;

public class EnleverCarteInexistanteException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public EnleverCarteInexistanteException(){
		super();
		System.out.println("Vous essayer d'enlever une carte inexistante!");
	}

}
