package model;

public class PartiePerdueException extends Exception {
	private static final long serialVersionUID = 1L;

	public PartiePerdueException(){
		super();
		System.out.println("Vous joué 3 mauvaise cartes, vous avez perdu!");
	}
}
