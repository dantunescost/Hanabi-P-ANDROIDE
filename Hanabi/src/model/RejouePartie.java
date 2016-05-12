package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class RejouePartie extends Partie {

	private static final long serialVersionUID = -3202175390534995053L;

	public RejouePartie(int nbJoueurs, int maxIndices, boolean multicolor) {
		super(nbJoueurs, maxIndices, multicolor);
	}

	public static void main(String[] args) {
		int nbTests = 500;
		int scoreTotal = 0;
		double scoreMoyen = 0.0;
		
		for(int i=0; i<nbTests; i++)
		{
			SimulationPartie rp = loadFromFile("./Games_25/g_2p_866457884");
			while(!rp.getFinPartie())
			{
				int j = rp.getaQuiLeTour();
				Joueur[] p = rp.getJoueurs();
				EpistemicJoueurIA ia = (EpistemicJoueurIA) p[j];
				ia.jouerCoup();
			}
			int erreurs = rp.getJetonEclair();
			int score = rp.calculerPoints();
			System.out.println("Partie " + (i+1) + " terminee. Score : " + score + ", erreurs : " + erreurs);
			scoreTotal += score;
		}
		
		scoreMoyen = (double)scoreTotal/nbTests;
		System.out.println("Score moyen (devrait etre egal au score de la partie sauvegardee) : " + scoreMoyen);
		
	}




	public static SimulationPartie loadFromFile(String fileName){
		SimulationPartie p = null;
		if(fileName != null)
		{
			File file = new File(fileName);
			FileInputStream fileStream;
			try {
				fileStream = new FileInputStream(file);
				ObjectInputStream objectStream = new ObjectInputStream(fileStream);
				p = (SimulationPartie)objectStream.readObject();
				objectStream.close();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return p;
	}

}