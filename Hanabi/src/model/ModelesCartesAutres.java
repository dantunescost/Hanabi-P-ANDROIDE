package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import model.Couleur.CardColor;

public class ModelesCartesAutres implements Serializable {
	private static final long serialVersionUID = -12627846697951363L;
	private Partie partie;
	private int id;
	private ArrayList<ArrayList<Collection<Carte>>> modelesJoueurs;
	private ArrayList<HashMap<Carte, Integer>> cartesSortiesJoueurs;

	public ModelesCartesAutres(Partie p,int id){
		this.partie = p;
		this.id = id;
		modelesJoueurs = new ArrayList<ArrayList<Collection<Carte>>>(5);
		for(int i = 0;i<this.partie.nbJoueurs;i++){
			modelesJoueurs.add(new ArrayList<Collection<Carte>>());
		}
		cartesSortiesJoueurs = new ArrayList<HashMap<Carte, Integer>>(5);
		for(int i = 0;i<this.partie.nbJoueurs;i++){
			cartesSortiesJoueurs.add(new HashMap<Carte, Integer>());
		}
		initCartesSorties();
	}
	
	public void indiceCouleurRecu(int joueur,CardColor color){
		int i = 0;
		for(Carte c : this.partie.getJoueurs()[joueur].getMain().main){
			if(c.getCouleur().equals(color)){
				ArrayList<Carte> carteASupprimer = new ArrayList<Carte>();
				for(Carte c2 : this.modelesJoueurs.get(joueur).get(i)){
					if(!c2.getCouleur().equals(color)){
						carteASupprimer.add(c2);
					}
				}
				this.modelesJoueurs.get(joueur).get(i).removeAll(carteASupprimer);
			}
			else{
				ArrayList<Carte> carteASupprimer = new ArrayList<Carte>();
				for(Carte c2 : this.modelesJoueurs.get(joueur).get(i)){
					if(c2.getCouleur().equals(color)){
						carteASupprimer.add(c2);
					}
				}
				this.modelesJoueurs.get(joueur).get(i).removeAll(carteASupprimer);
			}
			i++;
		}
	}
	
	public void indiceValeurRecu(int joueur,int valeur){
		int i = 0;
		for(Carte c : this.partie.getJoueurs()[joueur].getMain().main){
			if(c.getValeur() == valeur){
				ArrayList<Carte> carteASupprimer = new ArrayList<Carte>();
				for(Carte c2 : this.modelesJoueurs.get(joueur).get(i)){
					if(!(c2.getValeur() == valeur)){
						carteASupprimer.add(c2);
					}
				}
				this.modelesJoueurs.get(joueur).get(i).removeAll(carteASupprimer);
			}
			else{
				ArrayList<Carte> carteASupprimer = new ArrayList<Carte>();
				for(Carte c2 : this.modelesJoueurs.get(joueur).get(i)){
					if(c2.getValeur() == valeur){
						carteASupprimer.add(c2);
					}
				}
				this.modelesJoueurs.get(joueur).get(i).removeAll(carteASupprimer);
			}
			i++;
		}
	}
	
	public void creerModele(int joueur){
		Collection<Carte> modele = new ArrayList<Carte>();
		for(Carte c : this.cartesSortiesJoueurs.get(joueur).keySet()){
			for(int i = 0;i<this.cartesSortiesJoueurs.get(joueur).get(c);i++){
				modele.add(c.clone());
			}
		}
		modelesJoueurs.get(joueur).add(modele);
	}
	
	public void majPioche(int joueur, Carte c){
		for(int i = 0;i<this.partie.nbJoueurs;i++){
			if(i!=joueur){
				for(Collection<Carte> m : modelesJoueurs.get(i)){
					m.remove(c);
				}
				this.cartesSortiesJoueurs.get(i).put(c.clone(),this.cartesSortiesJoueurs.get(i).get(c)-1);
			}
		}
	}
	
	public void majDefausseOuJouerTous(Carte c, int index){
		for(int i=0;i<this.partie.nbJoueurs;i++){
			if(i!=id){
				for(Collection<Carte> m : modelesJoueurs.get(i)){
					m.remove(c);
				}
				this.cartesSortiesJoueurs.get(i).put(c.clone(),this.cartesSortiesJoueurs.get(i).get(c)-1);
			}
		}
	}
			
	public void majDefausseOuJouer(int joueur,Carte c,int index){
		this.modelesJoueurs.get(joueur).remove(index);
		for(Collection<Carte> m : modelesJoueurs.get(joueur)){
			m.remove(c);
		}
		this.cartesSortiesJoueurs.get(joueur).put(c.clone(),this.cartesSortiesJoueurs.get(joueur).get(c)-1);
	}
	
	public void initCartesSorties(){
		for(HashMap<Carte, Integer> cartesSorties : this.cartesSortiesJoueurs){
			for(int i=1; i<6; i++){
				if(i==1){
					cartesSorties.put(new Carte(CardColor.BLANC,i),3);
					cartesSorties.put(new Carte(CardColor.JAUNE,i),3);
					cartesSorties.put(new Carte(CardColor.VERT,i),3);
					cartesSorties.put(new Carte(CardColor.BLEU,i),3);
					cartesSorties.put(new Carte(CardColor.ROUGE,i),3);
					if(partie.isMulticolor()){
						cartesSorties.put(new Carte(CardColor.MULTI,i),3);
					}
				}
				else if(i==5){
					cartesSorties.put(new Carte(CardColor.BLANC,i),1);
					cartesSorties.put(new Carte(CardColor.JAUNE,i),1);
					cartesSorties.put(new Carte(CardColor.VERT,i),1);
					cartesSorties.put(new Carte(CardColor.BLEU,i),1);
					cartesSorties.put(new Carte(CardColor.ROUGE,i),1);	
					if(partie.isMulticolor()){
						cartesSorties.put(new Carte(CardColor.MULTI,i),1);
					}
				}
				else{
					cartesSorties.put(new Carte(CardColor.BLANC,i),2);
					cartesSorties.put(new Carte(CardColor.JAUNE,i),2);
					cartesSorties.put(new Carte(CardColor.VERT,i),2);
					cartesSorties.put(new Carte(CardColor.BLEU,i),2);
					cartesSorties.put(new Carte(CardColor.ROUGE,i),2);
					if(partie.isMulticolor()){
						cartesSorties.put(new Carte(CardColor.MULTI,i),2);
					}
				}
			}
		}
	}
	
	public ArrayList<ArrayList<Collection<Carte>>> cloneModeles(){
		ArrayList<ArrayList<Collection<Carte>>> clone = new ArrayList<ArrayList<Collection<Carte>>>(5);
		for(int i=0;i<this.partie.nbJoueurs;i++){
			clone.add(new ArrayList<Collection<Carte>>());
			int j = 0;
			for(Collection<Carte> col : modelesJoueurs.get(i)){
				clone.get(i).add(new ArrayList<Carte>());
				for(Carte c : col){
					clone.get(i).get(j).add(c.clone());
				}
				j++;
			}
		}
		return clone;
	}
	
	public class Indice{
		public int bestPlayer;
		public int bestValue;
		public CardColor bestColor;
	}
	
	public Indice meilleurIndice(){
		Indice bestIndice = new Indice();
		CardColor goodColor = null;
		int goodValeur = -1;
		boolean couleurOverValeur = true;
		int max = 0;
		int cmp = 0;
		for(int i = 0; i<this.partie.nbJoueurs;i++){// pour tous les joueurs
			if(i!=id){
				for(CardColor color : CardColor.values()){// pour toutes les couleurs
					if(!color.equals(CardColor.MULTI) || this.partie.isMulticolor()){
						int k = 0;
						for(Carte c : this.partie.getJoueurs()[i].getMain().main){// test mondes supprimé pour cet indice couleur
							if(c.getCouleur().equals(color)){
								for(Carte c2 : this.modelesJoueurs.get(i).get(k)){
									if(!c2.getCouleur().equals(color)){
										cmp++;
									}
								}
								
							}
							else{
								for(Carte c2 : this.modelesJoueurs.get(i).get(k)){
									if(c2.getCouleur().equals(color)){
										cmp++;
									}
								}
							}
							k++;
						}
						if(max<cmp){
							max = cmp;
							bestIndice.bestPlayer = i;
							goodColor = color;
						}
						cmp = 0;
					}
				}
				for(int x=1;x<6;x++){
					int k = 0;
					cmp = 0;
					for(Carte c : this.partie.getJoueurs()[i].getMain().main){
						if(c.getValeur() == x){
							for(Carte c2 : this.modelesJoueurs.get(i).get(k)){
								if(!(c2.getValeur() == x)){
									cmp++;
								}
							}
						}
						else{
							for(Carte c2 : this.modelesJoueurs.get(i).get(k)){
								if(c2.getValeur() == x){
									cmp++;
								}
							}
						}
						k++;
					}
					if(max<cmp){
						max = cmp;
						bestIndice.bestPlayer = i;
						goodValeur = x;
						couleurOverValeur = false;
					}
				}
			}
		}
		if(couleurOverValeur){
			bestIndice.bestValue = -1;
			bestIndice.bestColor = goodColor;
		}
		else{
			bestIndice.bestValue = goodValeur;
		}
		return bestIndice;
	}

	public ArrayList<ArrayList<Collection<Carte>>> getModelesJoueurs() {
		return modelesJoueurs;
	}
}
