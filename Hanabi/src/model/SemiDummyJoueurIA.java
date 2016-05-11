package model;

/**
 * 	SEMIDUMMY IA
 *  <p>
 * 	Mode de fonctionnement :
 * 	<ul>
 * 		<li>Si une carte est connu a 100% et qu'elle est jouable alors jouer la carte
 * 		<li>Sinon donner un indice à un joueur aleatoire s'il y a des jetons restants
 *  	<li>Sinon defausser :
 *  </ul>
 *  	<ol>
 *  		<li>s'il sait qu'il peut defausser une carte (carte deja posee et connu)
 *  		<li>sinon cartes sans indices
 *  		<li>sinon aleatoire
 *  	</ol>
 */
public class SemiDummyJoueurIA extends JoueurIA {
	private static final long serialVersionUID = 2308625908918058364L;
	/**
	 * Constructeur pour joueur SemiDummy IA
	 * @param nom		Le nom associe au joueur
	 * @param nbCartes	Le nombre de cartes que doit contenir sa main (definit par le nombre de joueurs dans la partie)
	 * @param p			La partie a laquelle participe cette IA
	 * @param id		Le numero du joueur dans la partie
	 */
    public SemiDummyJoueurIA(String nom, Partie p, int id) {
        super(nom, p, id);
    }
    
    /**
     * Fonction qui decide et joue un coup dans la partie
     */
    public void jouerCoup() {
        boolean aJoue=false;
        // ************************* JOUER LA CARTE *******************************
        aJoue=this.jouerCarte();   
        // ************************ DONNER INDICE **********************************
        if((!aJoue)&&(p.jetonIndice > 0)){
        	int j=0;
            for(j=0; j<p.getJoueurs().length; j++)
            {
                if((p.getJoueurs()[j]!=this)&&(!aJoue)){
                    aJoue=this.donnerIndiceIntelligentA(j);
                }
            }
        }
        if((!aJoue)&&(p.jetonIndice > 0)){       	
            aJoue=this.donnerIndiceAleatoire(this.p.multicolor);
	    }
        // ************************ DEFAUSSER **********************************
        if(!aJoue){
            aJoue=this.defausserCarte();
        }
    } 
    
    
    public boolean jouerCarte(){
        Carte c = this.coupTrivial();
        if (c != null) {
            // ************************* JOUER LA CARTE *******************************
            int j = this.getMain().getIndex(c);

            try {
                p.joueCarte(this, j);
                return true;
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
        return false;
    }
    
    public boolean donnerIndiceAleatoire(boolean multi){
    	int joueur;
    	do {
    		joueur=r.nextInt(p.getNbJoueurs());
    	}while(joueur==this.id);
        int cORi = r.nextInt(2);
        int color=0;
        if(!multi)
        	color = r.nextInt(5);
        else if(multi)
        	color = r.nextInt(6);
	    if (cORi==0) {
	    	if (color == 0) {
	    		try {
	                p.indiceCouleur(p.getJoueurs()[joueur], Couleur.CardColor.BLANC);
                    return true;
	            } catch (IndiceSoitMemeException e) {
	                e.printStackTrace();
	            }
	        }
	        if (color == 1) {
	            try {
	                p.indiceCouleur(p.getJoueurs()[joueur], Couleur.CardColor.ROUGE);
                    return true;
	            } catch (IndiceSoitMemeException e) {
	                e.printStackTrace();
	            }
	        }
	        if (color == 2) {
	            try {
	                p.indiceCouleur(p.getJoueurs()[joueur], Couleur.CardColor.VERT);
                    return true;
	            } catch (IndiceSoitMemeException e) {
	                e.printStackTrace();
	            }
	        }
	        if (color == 3) {
	            try {
	                p.indiceCouleur(p.getJoueurs()[joueur], Couleur.CardColor.BLEU);
                    return true;
	            } catch (IndiceSoitMemeException e) {
	                e.printStackTrace();
	            }
	        }
	        if (color == 4) {
	            try {
	                p.indiceCouleur(p.getJoueurs()[joueur], Couleur.CardColor.JAUNE);
                    return true;
	            } catch (IndiceSoitMemeException e) {
	                e.printStackTrace();
	            }
	        }
	        if (color == 5) {
	            try {
	                p.indiceCouleur(p.getJoueurs()[joueur], Couleur.CardColor.MULTI);
                    return true;
	            } catch (IndiceSoitMemeException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    else {
	    	int number=r.nextInt(5);
	        try {
	            System.out.println("Voici tes cartes de valeur "+Integer.toString(number+1));
	            p.indiceValeur(p.getJoueurs()[joueur], number+1);
                return true;
	        } catch (IndiceSoitMemeException e) {
	            e.printStackTrace();
	        }
	    }
        return false;
    }
    public boolean defausserCarte(){
        // ************************ DEFAUSSER CARTE CONNUE **********************************
        Carte defaussable = this.defausseTriviale();
        if(defaussable==null){
        	//defaussable=this.carteInutile();
        }
        boolean discarded = false;
        if (defaussable!=null) {

            int j=this.getMain().getIndex(defaussable);
            try {
                p.defausse(this, j);
                discarded=true;
                return true;
            } catch (EnleverCarteInexistanteException e) {
                e.printStackTrace();
            } catch (AdditionMainPleineException e) {
                e.printStackTrace();
            } catch (PiocheVideException e) {
                e.printStackTrace();
            }
        }
        // ************************ DEFAUSSER CARTE SANS INDICE **********************************
        if(!discarded){

            int i = 0;
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
                        return true;
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
        }  	
        // ************************ DEFAUSSER CARTE ALEATOIREMENT **********************************
        if(!discarded){
            try {
                try {
                    p.defausse(this, r.nextInt(this.getMain().getNbCartes()));
                    return true;
                } catch (AdditionMainPleineException e) {
                    e.printStackTrace();
                } catch (PiocheVideException e) {
                    e.printStackTrace();
                }
            } catch (EnleverCarteInexistanteException e) {
                e.printStackTrace();
            }
        }
        return false;
    } 
}