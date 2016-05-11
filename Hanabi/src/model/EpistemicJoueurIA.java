package model;

import java.util.ArrayList;

public class EpistemicJoueurIA extends JoueurIA {
	private static final long serialVersionUID = 7686865017053076471L;
	private ModelesCartesJoueur mcj;
	private float risque;

	public EpistemicJoueurIA(String nom, Partie p, int id, float risque) {
		super(nom, p, id);
		this.mcj = new ModelesCartesJoueur(p, id);
		this.risque = risque;
	}

	public ModelesCartesJoueur getMcj() {
		return mcj;
	}

	public void jouerCoup(){
		boolean aJoue = false;
        // ************************* JOUER UNE CARTE AVEC RISQUE *******************************
		int indice=jouerCarte();
		if(indice!=-1){
			try {
                p.joueCarte(this, indice);
                aJoue = true;
            } catch (EnleverCarteInexistanteException e) {
                e.printStackTrace();
            } catch (PartiePerdueException e) {
                e.printStackTrace();
            } catch (AdditionMainPleineException e) {
                e.printStackTrace();
            } catch (PiocheVideException e) {
                e.printStackTrace();
            }
		}
        // ************************* DONNER UN INDICE SUR CARTE JOUABLE *******************************
		if(!aJoue && this.p.getJetonIndice()>1 ){
			
		}
	}
	
	// Carte jouable avec risque, renvoye -1 si aucune carte n'est jouable directement
	public int jouerCarte(){
		int c = -1;
		int i = 0;
		ArrayList<Carte> cartesJouables = cartesJouablesDirectement();
		while(c == -1 && i<this.main.main.size()){
			int nbCartesJouables = 0;
			for(Carte monde : this.mcj.getModeles().get(i)){
				if(cartesJouables.contains(monde)){
					nbCartesJouables++;
				}
			}
			if(((float)nbCartesJouables/(float)this.mcj.getModeles().get(i).size()) >= risque){
				c=i;
			}
			i++;
		}
		return c;
	}
}
