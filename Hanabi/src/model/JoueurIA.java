package model;

import java.util.Random;

public class JoueurIA extends Joueur {
	private static final long serialVersionUID = 882515923322600787L;
	/**
	 * Generateur de nombre aleatoire de ce joueur IA
	 */
	Random r;

	/**
	 * Constructeur d'un joueur IA standard
	 * @param nom		Le nom associe au joueur
	 * @param nbCartes	Le nombre de cartes que doit contenir sa main (definit par le nombre de joueurs dans la partie)
	 * @param p			La partie a laquelle participe cette IA
	 * @param id		Le numero du joueur dans la partie
	 */
	public JoueurIA(String nom, Partie p, int id){
		super(nom, p, id);
		r = new Random();
	}
	
	/**
     * Fonction qui decide et joue un coup dans la partie
     */
    public void jouerCoup() {}

	/**
	 * Permet a l'IA de savoir si une carte de sa main est connue et jouable dans la situation actuelle
	 * @return	La carte que l'IA peut jouer si elle existe
	 * 			null sinon
	 */
    public Carte coupTrivial () {
        for (Carte c : this.getMain().main){
            if (c.isCouleurConnue() && c.isValeurConnue()) {
               // for (int i=0; i<this.p.cartesJouees.size(); i++) {
                    if (this.p.cartesJouees.get(c.getCouleur()).size()==(c.getValeur()-1)){
                        return c;
                 //   }
                }
            }
            else if(c.isValeurConnue()){
            	//Si toutes les cartes jouees ont la meme valeur(ou qu'il n'y a pas de cartes) et le joueur possede une carte de valeur +1
            	if ((this.p.cartesJouees.get(Couleur.CardColor.BLANC).size()==(c.getValeur()-1))
            		&&(this.p.cartesJouees.get(Couleur.CardColor.ROUGE).size()==(c.getValeur()-1))
            		&&(this.p.cartesJouees.get(Couleur.CardColor.VERT).size()==(c.getValeur()-1))
            		&&(this.p.cartesJouees.get(Couleur.CardColor.BLEU).size()==(c.getValeur()-1))
            		&&(this.p.cartesJouees.get(Couleur.CardColor.JAUNE).size()==(c.getValeur()-1)))
            	{
            		return c;
            	}
            }
        }
        return null;
    }
    
    public boolean isJouableTrivial (Carte c) {
    	boolean res=false;
    	if (c.isCouleurConnue() && c.isValeurConnue()) {
    		if (this.p.cartesJouees.get(c.getCouleur()).size()==(c.getValeur()-1)){
    			res=true;
    		}
    	}
    	else if(c.isValeurConnue()){
        //Si toutes les cartes jouees ont la meme valeur(ou qu'il n'y a pas de cartes) et le joueur possede une carte de valeur +1
            if ((this.p.cartesJouees.get(Couleur.CardColor.BLANC).size()==(c.getValeur()-1))
            	&&(this.p.cartesJouees.get(Couleur.CardColor.ROUGE).size()==(c.getValeur()-1))
            	&&(this.p.cartesJouees.get(Couleur.CardColor.VERT).size()==(c.getValeur()-1))
            	&&(this.p.cartesJouees.get(Couleur.CardColor.BLEU).size()==(c.getValeur()-1))
            	&&(this.p.cartesJouees.get(Couleur.CardColor.JAUNE).size()==(c.getValeur()-1)))
            {
            	res=true;
            }
    	}
        return res;
    }

    /**
	 * Permet a l'IA de savoir si une carte de sa main est connue et defaussable (carte deja joue) dans la situation actuelle
	 * @return	La carte que l'IA peut defausser si elle existe
	 * 			null sinon
	 */
    public Carte defausseTriviale () {
        for (Carte c : this.getMain().main){
            if (c.isCouleurConnue() && c.isValeurConnue()) {
                //for (int i=0; i<this.p.cartesJouees.size(); i++) {
                    if (this.p.cartesJouees.get(c.getCouleur()).size()>=(c.getValeur())){
                        return c;
                    }
                    else if(carteInutile(c)){
                    	System.out.println("je sais que j'ai une carte inutile");
                    	return c;
                    }
                //}
            }
            else if(c.isValeurConnue()){
            	//Si toutes les cartes jouees ont la meme valeur et le joueur possede une carte de valeur -
            	if ((this.p.cartesJouees.get(Couleur.CardColor.BLANC).size()<=(c.getValeur()))
            		&&(this.p.cartesJouees.get(Couleur.CardColor.ROUGE).size()<=(c.getValeur()))
            		&&(this.p.cartesJouees.get(Couleur.CardColor.VERT).size()<=(c.getValeur()))
            		&&(this.p.cartesJouees.get(Couleur.CardColor.BLEU).size()<=(c.getValeur()))
            		&&(this.p.cartesJouees.get(Couleur.CardColor.JAUNE).size()<=(c.getValeur())))
            	{
            		return c;
            	}
            
            }
            else if(c.isCouleurConnue() && (this.p.cartesJouees.get(c.getCouleur()).size()==5)){
            	return c;
            }
        }
        return null;
    }
    public boolean isDefaussableTrivial(Carte c){
    	boolean res=false;
    	if (c.isCouleurConnue() && c.isValeurConnue()) {
    		//for (int i=0; i<this.p.cartesJouees.size(); i++) {
            	if (this.p.cartesJouees.get(c.getCouleur()).size()>=(c.getValeur())){
            		res=true;
                }
                else if(carteInutile(c)){
                	System.out.println("je sais que j'ai une carte inutile");
                    res=true;
                }
            //}
    	}
        else if(c.isValeurConnue()){
            //Si toutes les cartes jouees ont la meme valeur et le joueur possede une carte de valeur -
            if ((this.p.cartesJouees.get(Couleur.CardColor.BLANC).size()<=(c.getValeur()))
            	&&(this.p.cartesJouees.get(Couleur.CardColor.ROUGE).size()<=(c.getValeur()))
            	&&(this.p.cartesJouees.get(Couleur.CardColor.VERT).size()<=(c.getValeur()))
            	&&(this.p.cartesJouees.get(Couleur.CardColor.BLEU).size()<=(c.getValeur()))
            	&&(this.p.cartesJouees.get(Couleur.CardColor.JAUNE).size()<=(c.getValeur())))
            {
            	res=true;
            }
        }
        else if(c.isCouleurConnue() && (this.p.cartesJouees.get(c.getCouleur()).size()==5)){
        	 res=true;
        }
    return res;
    }
    public boolean carteInutile(Carte c)
    {
    	boolean res = false;
    	if(c.isCouleurConnue() && c.isValeurConnue() && c.getValeur()>1)
    	{
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
    public int compterCartesJouables()
    {
    	int nb=0;
    	for(Carte c : this.getMain().main)
		{
			if(this.isJouableTrivial(c))
			{
				nb++;
			}
		}
    	for(Joueur j : p.joueurs)
    	{
    		if (j.getId()!=this.id)
    		{
    			for(Carte c : j.getMain().main)
    			{
    				if(c.isJouableOmniscient(p))
    				{
    					nb++;
    				}
    			}
    		}
    	}
    return nb;	
    }
    public int compterCartesDefaussables()
    {
    	int nb=0;
    	for(Carte c : this.getMain().main)
		{
			if(this.isDefaussableTrivial(c))
			{
				nb++;
			}
		}
    	for(Joueur j : p.joueurs)
    	{
    		if (j.getId()!=this.id)
    		{
    			for(Carte c : j.getMain().main)
    			{
    				if(c.isDefaussableOmniscient(p))
    				{
    					nb++;
    				}
    			}
    		}
    	}
    return nb;	
    }
    public int compterInfosJouables()
    {
    	int nb=0;
    	for(Carte c : this.getMain().main)
		{
			if(this.isJouableTrivial(c))
			{
				if (c.isCouleurConnue())
					nb++;
				if (c.isValeurConnue())
					nb++;
			}
		}
    	for(Joueur j : p.joueurs)
    	{
    		if (j.getId()!=this.id)
    		{
    			for(Carte c : j.getMain().main)
    			{
    				if(c.isJouableOmniscient(p))
    				{
    					if (c.isCouleurConnue())
    						nb++;
    					if (c.isValeurConnue())
    						nb++;
    				}
    			}
    		}
    	}
    return nb;	
    } 
    public int compterInfosDefaussables()
    {
    	int nb=0;
    	for(Carte c : this.getMain().main)
		{
			if(this.isDefaussableTrivial(c))
			{
				if (c.isCouleurConnue())
					nb++;
				if (c.isValeurConnue())
					nb++;
			}
		}
    	for(Joueur j : p.joueurs)
    	{
    		if (j.getId()!=this.id)
    		{
    			for(Carte c : j.getMain().main)
    			{
    				if(c.isDefaussableOmniscient(p))
    				{
    					if (c.isCouleurConnue())
    						nb++;
    					if (c.isValeurConnue())
    						nb++;
    				}
    			}
    		}
    	}
    return nb;	
    } 
    public int compterInfosNeutres()
    {
    	int nb=0;
    	for(Carte c : this.getMain().main)
		{
			if((!this.isDefaussableTrivial(c))&&(!this.isDefaussableTrivial(c)))
			{
				if (c.isCouleurConnue())
					nb++;
				if (c.isValeurConnue())
					nb++;
			}
		}
    	for(Joueur j : p.joueurs)
    	{
    		if (j.getId()!=this.id)
    		{
    			for(Carte c : j.getMain().main)
    			{
    				if((!c.isDefaussableOmniscient(p))&&(!c.isJouableOmniscient(p)))
    				{
    					if (c.isCouleurConnue())
    						nb++;
    					if (c.isValeurConnue())
    						nb++;
    				}
    			}
    		}
    	}
    return nb;	
    } 
}
