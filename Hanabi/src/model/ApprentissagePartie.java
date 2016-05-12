package model;

import java.util.Random;

public class ApprentissagePartie extends Partie {
	private static final long serialVersionUID = 595418792495031308L;

	public ApprentissagePartie(int nbJoueurs, int maxIndices, boolean multicolor) {
		super(nbJoueurs, maxIndices, multicolor);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ApprentissagePartie game;
		Random r = new Random();
		final int nbIndices = 8;
		Joueur[] joue;
		int nbSimulations=200;
		int nbJoueurs=3;
		boolean multi = false;
		
		
		for(int i=0; i<1000; i++)
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
		}
	}
}
