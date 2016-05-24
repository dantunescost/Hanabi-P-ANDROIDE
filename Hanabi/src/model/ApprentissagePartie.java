package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/*import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;*/

public class ApprentissagePartie extends Partie {
	private static final long serialVersionUID = 595418792495031308L;

	public ApprentissagePartie(int nbJoueurs, int maxIndices, boolean multicolor) {
		super(nbJoueurs, maxIndices, multicolor);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ApprentissagePartie game;
		//Random r = new Random();
		final int nbIndices = 8;
		Joueur[] joue;
		int nbSimulations=3000;
		int nbJoueurs=5;
		boolean multi = false;
		
		
		/*for(int i=0; i<1000; i++)
		{
			float scoreTotal = 0;
			float scoreMoyen = 0;
			int max = -1;
			int min = 42;
			
			game = new ApprentissagePartie(nbJoueurs, nbIndices, multi);
			joue = new Joueur[nbJoueurs];		
			double h[]= new double[8];
			h[0]=r.nextDouble()*50+50;
			for(int l=1; l<8; l++)
				h[l]=r.nextDouble()*100;
			
			ParamHeuristic param=new ParamHeuristic(h[0],h[1],h[2],h[3],h[4],h[5],h[6],h[7]);
			for(int l=0; l<nbJoueurs; l++)
			{
				joue[l] = new HeuristicJoueurIA(game, l,param);
			}
			
			
			for(int k=0; k<nbSimulations; k++)
			{			
				try {
					game.reinitPartie(joue);
				} catch (AdditionMainPleineException | PiocheVideException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				while(!game.getFinPartie())
				{
					int j = game.getaQuiLeTour();
					Joueur[] p = game.getJoueurs();
					HeuristicJoueurIA ia = (HeuristicJoueurIA) p[j];
					ia.jouerCoup();
	 			}
				
				int score = game.calculerPoints();
				scoreTotal += score;
				min = (score < min)? score:min;
				max = (score > max)? score:max;
				
				//System.out.println("Partie " + (i+1) + " terminee. Score : " + score);
			}
			scoreMoyen = scoreTotal / nbSimulations;
			if(scoreMoyen>10.0)
			{
				System.out.println(i+": Score max : " + max + "\nScore min : " + min + "\nScore moyen : " + scoreMoyen);
				System.out.println("Parametres : "+h[0]+" "+h[1]+" "+h[2]+" "+h[3]+" "+h[4]+" "+h[5]+" "+h[6]+" "+h[7]);

			}
		}*/
		game = new ApprentissagePartie(nbJoueurs, nbIndices, multi);
		joue = new Joueur[nbJoueurs];	
		double risque = 0.5,bestRisque = 0, scoreMoyen,scoreMax = 0.0;
		int min = 25,max = 0,scoreTotal = 0,erreursTotal = 0;
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("apprentissage-risque5.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while(risque <= 0.70001){
			min = 25;
			max = 0;
			scoreTotal = 0;
			erreursTotal = 0;
			for(int j = 0;j<nbJoueurs;j++){
				joue[j] = new EpistemicJoueurIA(game, j, risque);
			}	
			for(int i=0; i<nbSimulations; i++)
			{			
				try {
					game.reinitPartie(joue);
				} catch (AdditionMainPleineException | PiocheVideException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				while(!game.getFinPartie())
				{
					int j = game.getaQuiLeTour();
					Joueur[] p = game.getJoueurs();
					/*if(p[j].getClass().getName().equals(DummyJoueurIA.class.getName()))
					{
						DummyJoueurIA ia = (DummyJoueurIA) p[j];
						ia.jouerCoup();
					}
					else if(p[j].getClass().getName().equals(SemiDummyJoueurIA.class.getName()))
					{
						SemiDummyJoueurIA ia = (SemiDummyJoueurIA) p[j];
						ia.jouerCoup();
					}
					else if(p[j].getClass().getName().equals(HeuristicJoueurIA.class.getName()))
					{
						HeuristicJoueurIA ia = (HeuristicJoueurIA) p[j];
						ia.jouerCoup();
					}
					else */
					if(p[j].getClass().getName().equals(EpistemicJoueurIA.class.getName()))
					{
						EpistemicJoueurIA ia = (EpistemicJoueurIA) p[j];
						ia.jouerCoup();
					}
	 			}
				int erreurs = game.getJetonEclair();
				int score = game.calculerPoints();
				scoreTotal += score;
				erreursTotal += erreurs;
				min = (score < min)? score:min;
				max = (score > max)? score:max;
			}
			scoreMoyen = ((double)scoreTotal) / nbSimulations;
			bestRisque = (scoreMoyen>scoreMax)? risque:bestRisque;
			scoreMax = (scoreMoyen>scoreMax)? scoreMoyen:scoreMax;
			writer.println("Score max : " + max + "... Score min : " + min +"... risque = " + risque + "\nScore moyen : " + scoreMoyen+ "\nNombre d'erreurs en moyenne : " + ((double)erreursTotal)/nbSimulations);
			writer.println("Meilleur risque: "+bestRisque+"... score pour ce risque: "+scoreMax);
			System.out.println(scoreMoyen+"____________"+risque);
			writer.println("____________________________________________________________________");
			risque += 0.005;
		}
		writer.close();
	}	
}
