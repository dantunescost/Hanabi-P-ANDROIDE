package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import model.Couleur.CardColor;

public class ModelesCartesJoueur implements Serializable{
	private static final long serialVersionUID = -6313295563746440025L;
	private Partie partie;
	private int id;
	private ArrayList<Collection<Carte>> modeles;
	private HashMap<Carte, Integer> cartesSorties;
	private ArrayList<Carte> cartesCritiques = new ArrayList<Carte>(); 

	public ModelesCartesJoueur(Partie p, int id){
		this.partie = p;
		this.id = id;
		modeles = new ArrayList<Collection<Carte>>();
		cartesSorties = new HashMap<Carte, Integer>();
		initCartesSorties();
	}
	
	public void majPioche(Carte c){
		for(Collection<Carte> m : modeles){
			m.remove(c);
		}
		this.cartesSorties.put(c.clone(),this.cartesSorties.get(c)-1);
	}
	
	public void majDefausseOuJouer(Carte c,int index){
		this.modeles.remove(index);
		majPioche(c);
	}	
	
	public void creerModele(){
		Collection<Carte> modele = new ArrayList<Carte>();
		for(Carte c : this.cartesSorties.keySet()){
			for(int i = 0;i<this.cartesSorties.get(c);i++){
				modele.add(c.clone());
			}
		}
		modeles.add(modele);
	}
	
	public void indiceCouleurRecu(CardColor color){
		int i = 0;
		for(Carte c : this.partie.getJoueurs()[id].getMain().main){
			if(c.getCouleur().equals(color)){
				ArrayList<Carte> carteASupprimer = new ArrayList<Carte>();
				for(Carte c2 : this.modeles.get(i)){
					if(!c2.getCouleur().equals(color)){
						carteASupprimer.add(c2);
					}
				}
				this.modeles.get(i).removeAll(carteASupprimer);
			}
			else{
				ArrayList<Carte> carteASupprimer = new ArrayList<Carte>();
				for(Carte c2 : this.modeles.get(i)){
					if(c2.getCouleur().equals(color)){
						carteASupprimer.add(c2);
					}
				}
				this.modeles.get(i).removeAll(carteASupprimer);
			}
			i++;
		}
	}
	
	public void indiceValeurRecu(int valeur){
		int i = 0;
		for(Carte c : this.partie.getJoueurs()[id].getMain().main){
			if(c.getValeur() == valeur){
				ArrayList<Carte> carteASupprimer = new ArrayList<Carte>();
				for(Carte c2 : this.modeles.get(i)){
					if(!(c2.getValeur() == valeur)){
						carteASupprimer.add(c2);
					}
				}
				this.modeles.get(i).removeAll(carteASupprimer);
			}
			else{
				ArrayList<Carte> carteASupprimer = new ArrayList<Carte>();
				for(Carte c2 : this.modeles.get(i)){
					if(c2.getValeur() == valeur){
						carteASupprimer.add(c2);
					}
				}
				this.modeles.get(i).removeAll(carteASupprimer);
			}
			i++;
		}
	}
	
	public void initCartesSorties(){
		this.cartesCritiques.clear();
		for(CardColor c : CardColor.values()){
			if(!c.equals(CardColor.MULTI) || this.partie.isMulticolor()){
				this.cartesCritiques.add(new Carte(c, 5));
			}
		}
		for(int i=1; i<6; i++){
			if(i==1){
				this.cartesSorties.put(new Carte(CardColor.BLANC,i),3);
				this.cartesSorties.put(new Carte(CardColor.JAUNE,i),3);
				this.cartesSorties.put(new Carte(CardColor.VERT,i),3);
				this.cartesSorties.put(new Carte(CardColor.BLEU,i),3);
				this.cartesSorties.put(new Carte(CardColor.ROUGE,i),3);
				if(this.partie.isMulticolor()){
					this.cartesSorties.put(new Carte(CardColor.MULTI,i),3);
				}
			}
			else if(i==5){
				this.cartesSorties.put(new Carte(CardColor.BLANC,i),1);
				this.cartesSorties.put(new Carte(CardColor.JAUNE,i),1);
				this.cartesSorties.put(new Carte(CardColor.VERT,i),1);
				this.cartesSorties.put(new Carte(CardColor.BLEU,i),1);
				this.cartesSorties.put(new Carte(CardColor.ROUGE,i),1);	
				if(this.partie.isMulticolor()){
					this.cartesSorties.put(new Carte(CardColor.MULTI,i),1);
				}
			}
			else{
				this.cartesSorties.put(new Carte(CardColor.BLANC,i),2);
				this.cartesSorties.put(new Carte(CardColor.JAUNE,i),2);
				this.cartesSorties.put(new Carte(CardColor.VERT,i),2);
				this.cartesSorties.put(new Carte(CardColor.BLEU,i),2);
				this.cartesSorties.put(new Carte(CardColor.ROUGE,i),2);
				if(this.partie.isMulticolor()){
					this.cartesSorties.put(new Carte(CardColor.MULTI,i),2);
				}
			}
		}
	}
	
	public ArrayList<Collection<Carte>> getModeles() {
		return modeles;
	}

	public String toString(){
		String res = "{";
		for(Collection<Carte> m : this.modeles){
			for(Carte c : m){
				res += c.toString();
			}
		}
		return res + "}";
	}

	public void majCartesCritiques(Carte carte) {
		if(cartesCritiques.contains(carte)){
			cartesCritiques.remove(carte);
		}
		else if(!(this.partie.cartesJouees.get(carte.getCouleur()).size()>=carte.getValeur())){
			if(carte.getValeur()>1){
				cartesCritiques.add(carte);
			}
			else{
				int cmp = 0;
				for(int i=0; i<this.partie.defausse.size(); i++)
				{
					if(this.partie.defausse.get(i).equals(carte)){
						cmp++;
					}
				}
				if(cmp == 2){
					cartesCritiques.add(carte);
				}
			}
		}
	}

	public ArrayList<Carte> getCartesCritiques() {
		return cartesCritiques;
	}
}
