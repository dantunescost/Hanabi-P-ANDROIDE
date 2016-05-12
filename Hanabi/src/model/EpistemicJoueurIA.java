package model;

import java.util.ArrayList;
import java.util.Collection;

//import model.ModelesCartesAutres.Indice;

public class EpistemicJoueurIA extends JoueurIA {
	private static final long serialVersionUID = 7686865017053076471L;
	private ModelesCartesJoueur mcj;
	private ModelesCartesAutres mca;
	private double risque;
	public static final String nom = "Lisa";
	private double risqueInit;

	public EpistemicJoueurIA(Partie p, int id, double risque) {
		super(nom, p, id);
		this.mcj = new ModelesCartesJoueur(p, id);
		this.mca = new ModelesCartesAutres(p, id);
		this.risque = risque;
		this.risqueInit = risque;
	}

	public ModelesCartesJoueur getMcj() {
		return mcj;
	}

	public ModelesCartesAutres getMca() {
		return mca;
	}

	public void jouerCoup(){
		boolean aJoue = false;
		if(this.p.dernierTour){
			this.risque = 0.2;
		}
		else {
			this.risque = this.risqueInit;
		}
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
			/*if(!aJoue){
				Indice bestIndice = this.mca.meilleurIndice();
				if(bestIndice.bestValue == -1){
					try {
	                    p.indiceCouleur(p.getJoueurs()[bestIndice.bestPlayer], bestIndice.bestColor);
	                    aJoue = true;
	                } catch (IndiceSoitMemeException e) {
	                    e.printStackTrace();
	                }
				}
				else{
					try {
	                    p.indiceValeur(p.getJoueurs()[bestIndice.bestPlayer], bestIndice.bestValue);
	                    aJoue = true;
	                } catch (IndiceSoitMemeException e) {
	                    e.printStackTrace();
	                }
				}
			}*/
		}
		
		// ************************* DONNER INDICE SUR UNE CARTE CRITIQUE *******************************

		if(!aJoue && this.p.getJetonIndice()>1){
			int joueur = (this.p.aQuiLeTour+1)%this.p.nbJoueurs;
			while(!aJoue && joueur != this.id){
				//System.out.println(joueur + " recoit de " + this.id + " jetons: "+this.p.jetonIndice+" a qui le tour "+this.p.aQuiLeTour);
				aJoue = donnerIndiceCarteCritique(joueur);
				joueur = (joueur+1)%this.p.nbJoueurs;
			}
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
		int i = 0;
		double max = 0;
		int bestCard = -1;
		ArrayList<Carte> cartesJouables = cartesJouablesDirectement();
		while(i<this.main.main.size()){
			int nbCartesJouables = 0;
			for(Carte monde : this.mcj.getModeles().get(i)){
				if(cartesJouables.contains(monde)){
					nbCartesJouables++;
				}
			}
			if(max<((double)nbCartesJouables/(double)this.mcj.getModeles().get(i).size())){
				max = ((double)nbCartesJouables/(double)this.mcj.getModeles().get(i).size());
				bestCard = i;
			}
			i++;
		}
		if(max >= risque && this.p.jetonEclair<2){
			return bestCard;
		}
		else if(max == 1.0){
			return bestCard;
		}
		else{
			return -1;
		}
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
	
	public boolean donnerIndiceCarteCritique(int joueur){
		boolean indiceDonne = false;
		for(Carte c : this.p.joueurs[joueur].main.main){
			if(!indiceDonne && this.mcj.getCartesCritiques().contains(c) && (!c.isCouleurConnue() || !c.isValeurConnue())){
				if(!(c.isValeurConnue()) && p.getJoueurs()[joueur].getMain().valeurUnique(c.getValeur())){
	        		try {
	                    p.indiceValeur(p.getJoueurs()[joueur], c.getValeur());
	                    indiceDonne = true;
	                } catch (IndiceSoitMemeException e) {
	                    e.printStackTrace();
	                }
	        	}
	        	else if(!(c.isCouleurConnue()) && p.getJoueurs()[joueur].getMain().couleurUnique(c.getCouleur())){
	        		try {
	                    p.indiceCouleur(p.getJoueurs()[joueur], c.getCouleur());
	                    indiceDonne = true;
	                } catch (IndiceSoitMemeException e) {
	                    e.printStackTrace();
	                }
	        	}
	        	else if(!(c.isValeurConnue())){
	        		try {
	                    p.indiceValeur(p.getJoueurs()[joueur], c.getValeur());
	                    indiceDonne = true;
	                } catch (IndiceSoitMemeException e) {
	                    e.printStackTrace();
	                }
	        	}
	        	else if(!(c.isCouleurConnue())){
	        		try {
	                    p.indiceCouleur(p.getJoueurs()[joueur], c.getCouleur());
	                    indiceDonne = true;
	                } catch (IndiceSoitMemeException e) {
	                    e.printStackTrace();
	                }
	        	}
			}
		}
		return indiceDonne;
	}
	
	public void setRisque(double r){
		this.risque = r;
	}

	public double getRisque() {
		return risque;
	}
	
	public EpistemicJoueurIA clone() {
		EpistemicJoueurIA j = new EpistemicJoueurIA(this.p, this.id, this.risque);
		j.main = this.main.clone();
		return j;
	}
}
