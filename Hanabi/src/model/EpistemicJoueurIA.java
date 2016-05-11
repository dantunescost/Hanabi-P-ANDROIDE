package model;

import java.util.ArrayList;
import java.util.Collection;

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
		if(!aJoue && this.p.getJetonIndice()>1){
			int joueur = (this.p.aQuiLeTour+1)%this.p.nbJoueurs;
			while(!aJoue && joueur != this.id){
				aJoue = donnerIndiceIntelligentA(joueur);
				joueur = (joueur+1)%this.p.nbJoueurs;
			}
		}
        // ************************* DEFAUSSER UNE CARTE INUTILE *******************************
		if(!aJoue){
			int carteADefausser = defausseEpistemic();
			if(carteADefausser != -1){
				try {
	                p.defausse(this, carteADefausser);
	                aJoue = true;
	            } catch (EnleverCarteInexistanteException e) {
	                e.printStackTrace();
	            } catch (AdditionMainPleineException e) {
	                e.printStackTrace();
	            } catch (PiocheVideException e) {
	                e.printStackTrace();
	            }
			}
		}
		// ************************* DONNER UN INDICE SUR CARTE JOUABLE SINON MEILLEUR INDICE  *******************************
		if(!aJoue && this.p.getJetonIndice()>0){
			int joueur = (this.p.aQuiLeTour+1)%this.p.nbJoueurs;
			while(!aJoue && joueur != this.id){
				aJoue = donnerIndiceIntelligentA(joueur);
				joueur = (joueur+1)%this.p.nbJoueurs;
			}
			/*
			 * 
			 * SINON DONNER MEILLEUR INDICE
			 * 
			 */
		}
		// ************************* DEFAUSSER CARTE SANS INDICE  *******************************
		if(!aJoue){
			aJoue = defausserCarteSansIndice();
		}
		// ************************* DEFAUSSER CARTE AVEC INDICE  *******************************
		if(!aJoue){
			aJoue = defausserCarteAvecIndice();
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
			if(((float)nbCartesJouables/(float)this.mcj.getModeles().get(i).size()) >= risque && this.p.jetonEclair<2){
				c=i;
			}
			else if(((float)nbCartesJouables/(float)this.mcj.getModeles().get(i).size()) == 1.0){
				c=i;
			}
			i++;
		}
		return c;
	}
	
	public int defausseEpistemic(){
		int i = 0;
		boolean trouve = false;
		while(i<this.main.getNbCartes() && !trouve){
			trouve = true;
			int j = 0;
			while(j<this.mcj.getModeles().get(i).size() && trouve){
				trouve = isDefaussableTrivial((Carte)this.mcj.getModeles().get(i).toArray()[j]);
				j++;
			}
			i++;
		}
		if(trouve){
			return i-1;
		}
		else{
			return -1;
		}
	}
	
	public boolean isDefaussableTrivial(Carte c){
		return (this.p.cartesJouees.get(c.getCouleur()).size()>=c.getValeur() || carteInutile(c));
	}
	
	public boolean carteInutile(Carte c)
    {
    	boolean res = false;
    	if(c.getValeur()>1){
			int compt[] = new int[c.getValeur()-1];
			for(int i=0; i<this.p.defausse.size(); i++)
			{
				if((this.p.defausse.get(i).getCouleur() == c.getCouleur()) && (this.p.defausse.get(i).getValeur()<c.getValeur()))
				{
					compt[this.p.defausse.get(i).getValeur()-1]++;
				}
			}
			for(int j=0; j<c.getValeur()-1 && !res; j++)
			{
				if(((j+2)/3 + compt[j])==3)
					res=true;
			}
    	}
    	return res;
    }
	
	public boolean defausserCarteSansIndice(){
		int i = 0;
		boolean discarded = false;
		while(i < this.main.getNbCartes() && !discarded){
			Carte card = null;
			try {
				card = this.main.getCarte(i);
			} catch (EnleverCarteInexistanteException e1) {
				e1.printStackTrace();
			}
			if (!card.isCouleurConnue() && !card.isValeurConnue()) {
				try {
					p.defausse(this, i);
					discarded = true;
				} catch (EnleverCarteInexistanteException e) {
					e.printStackTrace();
				} catch (AdditionMainPleineException e) {
					e.printStackTrace();
				} catch (PiocheVideException e) {
					e.printStackTrace();
				}
			}
			i++;
		}
		return discarded;
	}	
	
	private boolean defausserCarteAvecIndice() {
		int min = 50;
		int bestIndex = 0;
		int i = 0;
		for(Collection<Carte> m : mcj.getModeles()){
			int cmp = 0;
			for(Carte c : m){
				if(this.mcj.getCartesCritiques().contains(c)){
					cmp++;
				}
			}
			if(cmp<min){
				min = cmp;
				bestIndex = i;
			}
			i++;
		}
		try {
			p.defausse(this, bestIndex);
		} catch (EnleverCarteInexistanteException e) {
			e.printStackTrace();
		} catch (AdditionMainPleineException e) {
			e.printStackTrace();
		} catch (PiocheVideException e) {
			e.printStackTrace();
		}
		return false;
	}
}
