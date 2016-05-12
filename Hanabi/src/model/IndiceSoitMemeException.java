package model;

/**
 * 	Exception levee lorsqu'un joueur essaye de se donner un indice
 */
public class IndiceSoitMemeException extends Exception {
	private static final long serialVersionUID = 1L;

	public IndiceSoitMemeException(int joueur,int aQui, int jetons){
		super();
		if(joueur == aQui){
			System.out.println("Vous essayer de vous donner un indice sur votre main! "+joueur+ " == "+aQui);
		}
		else if (jetons == 0){
			System.out.println("Plus de jetons d'indice disponibles! "+jetons+" restants.");
		}
	}
}
