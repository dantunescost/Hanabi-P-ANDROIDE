package model;

import java.util.ArrayList;

import model.Couleur.CardColor;

public class Main {
	private ArrayList<Carte> main;
	private final int nbCartes; // Nombre de cartes par joueur
	
	public Main(int nbCartes, Carte[] cartes){
		this.nbCartes = nbCartes;
		main = new ArrayList<Carte>(nbCartes);
		for(int i=0; i<nbCartes; i++){
			main.add(cartes[i]);
		}
	}
	
	public Main(int nbCartes){
		this.nbCartes = nbCartes;
		main = new ArrayList<Carte>(nbCartes);
	}
	
	public void ajouterCarte(Carte c) throws AdditionMainPleineException{
		if(main.size() < this.nbCartes){
			main.add(c);
		}else{
			throw new AdditionMainPleineException();
		}
	}
	
	public Carte enleverCarte(int indice) throws EnleverCarteInexistanteException{
		if(main.size() > indice){
			return this.main.remove(indice);
		}
		else{
			throw new EnleverCarteInexistanteException();
		}
	}
	
	// Type = 1 pour un indice couleur, sinon indice valeur
	/*public void indice(int[] indices,int type){
		for(int i:indices){
			if(type == 1){
				this.main.get(i).setCouleurConnue(true);
			}else{
				this.main.get(i).setValeurConnue(true);
			}
		}
	}*/
	
	public void indiceCouleur(CardColor c){
		for(int i=0;i<this.nbCartes;i++){
			if(this.main.get(i).getCouleur()==c){
				this.main.get(i).setCouleurConnue(true);
			}
		}
	}
	
	public void indiceValeur(int val){
		for(int i=0;i<this.nbCartes;i++){
			if(this.main.get(i).getValeur()==val){
				this.main.get(i).setValeurConnue(true);
			}
		}
	}
	
	public Carte getCarte(int indice) throws EnleverCarteInexistanteException{
		if(main.size() > indice){
			return this.main.get(indice);
		}
		else{
			throw new EnleverCarteInexistanteException();
		}
	}
}
