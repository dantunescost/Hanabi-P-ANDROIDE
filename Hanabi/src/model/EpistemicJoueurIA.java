package model;

public class EpistemicJoueurIA extends JoueurIA {
	private static final long serialVersionUID = 7686865017053076471L;
	private ModelesCartesJoueur mcj;

	public EpistemicJoueurIA(String nom, Partie p, int id) {
		super(nom, p, id);
		this.mcj = new ModelesCartesJoueur(p, id);
	}

	public ModelesCartesJoueur getMcj() {
		return mcj;
	}

}
